package com.example.myproject.web;


import com.example.myproject.model.binding.PictureBindingModel;
import com.example.myproject.model.entities.PictureEntity;
import com.example.myproject.model.view.PictureViewModel;
import com.example.myproject.repository.PictureRepository;
import com.example.myproject.service.CloudinaryService;
import com.example.myproject.service.PictureService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PictureController {

    private final CloudinaryService cloudinaryService;
    private final PictureRepository pictureRepository;
    private final PictureService pictureService;

    public PictureController(CloudinaryService cloudinaryService, PictureRepository pictureRepository, PictureService pictureService) {
        this.cloudinaryService = cloudinaryService;
        this.pictureRepository = pictureRepository;
        this.pictureService = pictureService;
    }


    @GetMapping("/pictures/add")
    public String addPicture(Model model) {
        if (!model.containsAttribute("pictureIsEmpty")) {
            model.addAttribute("pictureIsEmpty", false);
        }
        return "picture-add";
    }

    @ModelAttribute
    public PictureBindingModel pictureBindingModel (){
        return new PictureBindingModel();
    }

    @PostMapping("/pictures/add")
    public String addPicture(@Valid PictureBindingModel pictureBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) throws IOException {

        MultipartFile picture = pictureBindingModel.getPicture();
        if (picture.isEmpty()) {
            redirectAttributes
                    .addFlashAttribute("pictureBindingMode", pictureBindingModel)
                    .addFlashAttribute("pictureIsEmpty", true);
            return "picture-add";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("pictureBindingModel", pictureBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.bookingBindingModel",
                            pictureBindingModel);
            return "picture-add";
        }



        this.pictureService.addNewPicture(pictureBindingModel);
        return "redirect:/pictures/all";
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
        return "pictures-all";
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
