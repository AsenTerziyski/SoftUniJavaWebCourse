package com.example.pathfinder.service.impl;

import com.example.pathfinder.model.entities.PictureEntity;
import com.example.pathfinder.repository.PictureRepository;
import com.example.pathfinder.service.interfaces.PictureService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PictureServiceImpl implements PictureService {
    private final PictureRepository pictureRepository;

    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public List<String> findAllUrls() {
//        този вариант 1 не е добър:
//        List<PictureEntity> allPicturesEntities = this.pictureRepository.findAll();
//        List<String> allUrls = allPicturesEntities.stream().map(PictureEntity::getUrl).collect(Collectors.toList());
//
//        вариант 2:
        List<String> allPictureUrls = this.pictureRepository.findAllPictureUrls();
        return allPictureUrls;
    }
}
