package com.example.examsf.web;

import com.example.examsf.model.binding.ShipAddBindingModel;
import com.example.examsf.model.service.ShipServiceModel;
import com.example.examsf.service.ShipService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/ships")
public class ShipController {
    private final ModelMapper modelMapper;
    private final ShipService shipService;


    public ShipController(ModelMapper modelMapper, ShipService shipService) {
        this.modelMapper = modelMapper;
        this.shipService = shipService;
    }


    @GetMapping("/add")
    public String addShip() {
        return "ship-add";
    }

    @ModelAttribute
    public ShipAddBindingModel shipAddBindingModel() {
        return new ShipAddBindingModel();
    }

    @PostMapping("/add")
    public String addShipPost(@Valid ShipAddBindingModel shipAddBindingModel,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        System.out.println();

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("shipAddBindingModel",
                            shipAddBindingModel);
            redirectAttributes
                    .addFlashAttribute(
                            "org.springframework.validation.BindingResult.shipAddBindingModel",
                            bindingResult);
            return "redirect:add";
        }

        ShipServiceModel shipServiceModel = this.modelMapper.map(shipAddBindingModel, ShipServiceModel.class);
        boolean shipIsSaved = this.shipService.saveShipInDatabase(shipServiceModel);
        System.out.println();
        return "redirect:/";

    }

}
