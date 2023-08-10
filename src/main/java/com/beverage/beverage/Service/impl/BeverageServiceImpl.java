package com.beverage.beverage.Service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.beverage.beverage.Repo.*;
import com.beverage.beverage.Service.BeverageService;
import com.beverage.beverage.dto.BeverageDto;
import com.beverage.beverage.entity.Beverage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BeverageServiceImpl implements BeverageService {

    private final BeverageRepo beverageRepo;
    private final CategoryRepo categoryRepo;

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/beverage_img";

    @Override
    public void saveData(BeverageDto beverageDto) throws IOException {
        Beverage beverage = new Beverage();
        beverage.setName(beverageDto.getName());
        beverage.setDescription(beverageDto.getDescription());
        beverage.setPrice(beverageDto.getPrice());

        int id = beverageDto.getCategoryId();
        beverage.setCategory(categoryRepo.findById(id).get());

        if (beverageDto.getImage() != null) {
            // Generate a unique image file name using user email and item name
            String imageName = beverageDto.getName() + "_" + System.currentTimeMillis();
            System.out.println(imageName);
            String originalFilename = beverageDto.getImage().getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));

            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, imageName + fileExtension);
            fileNames.append(imageName + fileExtension);
            Files.write(fileNameAndPath, beverageDto.getImage().getBytes());

            beverage.setImage(imageName + fileExtension);
        }
        beverageRepo.save(beverage);

    }

    @Override
    public java.util.List<Beverage> getAllData() {
        return beverageRepo.findAll();
    }

    @Override
    public void deleteBeverage(int id) {
        beverageRepo.deleteById(id);
    }

    @Override
    public int countAllItems(String partialName) {
        return beverageRepo.countAllItems(partialName);
    }

    @Override
    public int countAllItemsByCategoryId(int id, String partialName) {
        return beverageRepo.countAllByCategoryId(id,partialName);
    }

    @Override
    public List<Beverage> getSixItems(int page, String partialName) {
        int offset = (page - 1) * 6;
        return beverageRepo.findSixItems(offset,partialName);
    }

    @Override
    public List<Beverage> getSixItemsByCategoryId(int id, int page, String partialName) {
        int offset = (page - 1) * 6;
        return beverageRepo.findSixItemsByCategoryId(id,offset,partialName);
    }

    @Override
    public Optional<Beverage> getItemById(int id) {
        return beverageRepo.findById(id);
    }

    @Override
    public List<Beverage> getLatestThreeData() {
        return beverageRepo.findLatestThreeData();
    }

}
