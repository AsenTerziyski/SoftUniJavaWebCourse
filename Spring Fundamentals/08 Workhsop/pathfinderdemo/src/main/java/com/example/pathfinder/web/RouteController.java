package com.example.pathfinder.web;

import com.example.pathfinder.model.binding.RouteAddBindingModel;
import com.example.pathfinder.model.service.RouteServiceModel;
import com.example.pathfinder.model.view.RouteDetailsViewModel;
import com.example.pathfinder.model.view.RouteViewModel;
import com.example.pathfinder.service.interfaces.RouteService;
import com.example.pathfinder.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;

    public RouteController(RouteService routeService, CurrentUser currentUser, ModelMapper modelMapper) {
        this.routeService = routeService;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute
    public RouteAddBindingModel routeAddBindingModel() {
        return new RouteAddBindingModel();
    }

    @GetMapping("/all")
    public String allRoutes(Model model) {
        List<RouteViewModel> routeViewModelList = this.routeService.findAllRoutesView();
        model.addAttribute("routes", routeViewModelList);
        return "routes";
    }

    @GetMapping("/add")
    public String add() {

        return "add-route";
    }

    @PostMapping("/add")
    public String addPost(@Valid RouteAddBindingModel routeAddBindingModel,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("routeAddBindingModel", routeAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.routeAddBindingModel", bindingResult);
            return "redirect:add";
        }

        System.out.println();
        RouteServiceModel map = this.modelMapper.map(routeAddBindingModel, RouteServiceModel.class);
        String coordinates = new String(routeAddBindingModel.getGpxCoordinates().getBytes());
        RouteServiceModel routeServiceModel = map.setGpxCoordinates(coordinates);
        this.routeService.addNewRoute(routeServiceModel);


        return "redirect:all";
    }

    @GetMapping("/details/{id}")
    public String getDetails(@PathVariable Long id, Model model) {
        RouteDetailsViewModel routeViewById = this.routeService.findRouteById(id);
        System.out.println();
        model.addAttribute("routeView", routeViewById);
        return "route-details";
    }

}
