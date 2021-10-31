package com.example.pathfinder.service.impl;

import com.example.pathfinder.model.entities.CategoryEntity;
import com.example.pathfinder.model.entities.RouteEntity;
import com.example.pathfinder.model.entities.UserEntity;
import com.example.pathfinder.model.service.RouteServiceModel;
import com.example.pathfinder.model.view.RouteDetailsViewModel;
import com.example.pathfinder.model.view.RouteViewModel;
import com.example.pathfinder.repository.RouteRepository;
import com.example.pathfinder.service.interfaces.CategoryService;
import com.example.pathfinder.service.interfaces.RouteService;
import com.example.pathfinder.service.interfaces.UserService;
import com.example.pathfinder.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public RouteServiceImpl(RouteRepository routeRepository, UserService userService, CategoryService categoryService, ModelMapper modelMapper, CurrentUser currentUser) {
        this.routeRepository = routeRepository;
        this.userService = userService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public List<RouteViewModel> findAllRoutesView() {

        List<RouteEntity> allRoutes = this.routeRepository.findAll();
        List<RouteViewModel> collect = allRoutes.stream().map(routeEntity ->
        {
            RouteViewModel routeViewModel = this.modelMapper.map(routeEntity, RouteViewModel.class);
            if (routeEntity.getPictures().isEmpty()) {
                routeViewModel.setPictureURL("/images/pic4.jpg");
                return routeViewModel;
            } else {
                String url = routeEntity.getPictures().stream().findFirst().get().getUrl();
                routeViewModel.setPictureURL(url);
            }
//            Random rand = new Random();
//            Set<PictureEntity> pictures = routeEntity.getPictures();
//            int upper = pictures.size();
//            int r = rand.nextInt(upper);
//            System.out.println(r);
            return routeViewModel;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public boolean addNewRoute(RouteServiceModel routeServiceModel) {

        RouteEntity newRoute = this.modelMapper.map(routeServiceModel, RouteEntity.class);

        if (this.currentUser.isLogged()) {

            UserEntity userById = this.userService.findUserById(currentUser.getId());
            if (userById != null) {
                newRoute.setAuthor(userById);
            }

            Set<CategoryEntity> catSet = routeServiceModel
                    .getCategories()
                    .stream()
                    .map(this.categoryService::findCategoryByName)
                    .collect(Collectors.toSet());
            newRoute.setCategories(catSet);
            this.routeRepository.save(newRoute);

            return true;
        }
        return false;
    }

    @Override
    public RouteDetailsViewModel findRouteById(Long id) {
        RouteEntity routeEntity = this.routeRepository.findById(id).orElse(null);
        RouteDetailsViewModel viewRoute = this.modelMapper.map(routeEntity, RouteDetailsViewModel.class);
        return viewRoute;
    }
}
