package bg.softuni.cloidinaryDemo.web;

import bg.softuni.cloidinaryDemo.model.binding.PictureBindingModel;
import bg.softuni.cloidinaryDemo.model.entity.PictureEntity;
import bg.softuni.cloidinaryDemo.model.view.PictureViewModel;
import bg.softuni.cloidinaryDemo.repository.PictureRepository;
import bg.softuni.cloidinaryDemo.service.CloudinaryImage;
import bg.softuni.cloidinaryDemo.service.CloudinaryService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PictureController {

    private final CloudinaryService cloudinaryService;
    private final PictureRepository pictureRepository;

    public PictureController(CloudinaryService cloudinaryService, PictureRepository pictureRepository) {
        this.cloudinaryService = cloudinaryService;
        this.pictureRepository = pictureRepository;
    }

    @GetMapping("/pictures/add")
    public String addPicture() {
        return "add";
    }

    @PostMapping("pictures/add")
    public String addPicture(PictureBindingModel pictureBindingModel) throws IOException {

        System.out.println();
        MultipartFile picture = pictureBindingModel.getPicture();
        String title = pictureBindingModel.getTitle();
        PictureEntity pictureEntity = createPictureEntity(picture, title);
        this.pictureRepository.save(pictureEntity);
        return "redirect:/pictures/all";
    }

    private PictureEntity createPictureEntity(MultipartFile file, String title) throws IOException {
        CloudinaryImage uploaded = this.cloudinaryService.upload(file);

        return new PictureEntity()
                .setPublicId(uploaded.getPublicId())
                .setTitle(title)
                .setUrl(uploaded.getUrl());
    }

    @Transactional
    @DeleteMapping("/pictures/delete")
    public String delete(@RequestParam("public_id") String public_id) {
        if (this.cloudinaryService.delete(public_id)) {
            pictureRepository.deleteAllByPublicId(public_id);
        }
        return "redirect:/pictures/all";
    }

    @GetMapping("/pictures/all")
    public String all(Model model) {

        List<PictureEntity> all = this.pictureRepository.findAll();
        List<PictureViewModel> allPictures = all
                .stream().map(this::mapToViewAllPics)
                .collect(Collectors.toList());

        model.addAttribute("allPictures", allPictures);
        return "all";
    }

    private PictureViewModel mapToViewAllPics(PictureEntity pictureEntity) {
        String publicId = pictureEntity.getPublicId();
        String url = pictureEntity.getUrl();
        String title = pictureEntity.getTitle();
        return new PictureViewModel()
                .setPublicId(publicId)
                .setUrl(url)
                .setTitle(title);
    }
}
