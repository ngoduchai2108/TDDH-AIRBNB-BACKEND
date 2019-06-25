package com.codegym.tddh.airbnb.api;

import com.codegym.tddh.airbnb.model.Categories;
import com.codegym.tddh.airbnb.payload.form.CreateCategoriesForm;
import com.codegym.tddh.airbnb.payload.form.UpdateCategoriesForm;
import com.codegym.tddh.airbnb.payload.response.ResponseMessage;
import com.codegym.tddh.airbnb.service.CategoriesHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class CategoriesApi {
    @Autowired
    CategoriesHouseService categoriesHouseService;

    //-------------------Retrieve All Categories--------------------------------------------------------
    @GetMapping("/categories")
    public ResponseEntity<List<Categories>> listAllCategories() {
        List<Categories> categories = categoriesHouseService.findAll();
        if (categories.isEmpty()) {
            return new ResponseEntity<List<Categories>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Categories>>(categories, HttpStatus.OK);
    }

    //-------------------Retrieve Single Categories--------------------------------------------------------
    @GetMapping("/categories/{id}")
    public ResponseEntity<Categories> getCategories(@PathVariable("id") long id) {
        System.out.println("Fetching Categories with id " + id);
        Optional<Categories> category = categoriesHouseService.findById(id);
        if (!category.isPresent()) {
            System.out.println("Categories with id " + id + " not found");
            return new ResponseEntity<Categories>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Categories>(category.get(), HttpStatus.OK);
    }

    //-------------------Create a Categories--------------------------------------------------------
    @PostMapping("/categories")
//    public ResponseEntity<?> createCategories(@Valid @RequestBody CreateCategoriesForm createCategoriesRequest) {
//        if (categoriesHouseService.existsByName(createCategoriesRequest.getName())) {
//            return new ResponseEntity<>(new ResponseMessage("Fail -> Categories Name is already taken!"),
//                    HttpStatus.BAD_REQUEST);
//        }
//
//        // Creating Categories House
//        Categories categories = new Categories(createCategoriesRequest.getName());
//        categoriesHouseService.save(categories);
//
//        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
//    }

    public ResponseEntity<?> createCategories(@Valid @RequestBody CreateCategoriesForm createCategoriesRequest, UriComponentsBuilder ucBuilder) {
        if (categoriesHouseService.existsByName(createCategoriesRequest.getName())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Categories Name is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        System.out.println("Creating Categories " + createCategoriesRequest.getName());
        Categories categories = new Categories(createCategoriesRequest.getName());
        categoriesHouseService.save(categories);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/categories/{id}").buildAndExpand(categories.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
    //------------------- Update a Categories --------------------------------------------------------
    @PostMapping("/categories/{id}")
    public ResponseEntity<Categories> updateCategories(@PathVariable("id") long id,
                                                       @RequestBody UpdateCategoriesForm updateCategoriesRequest) {
        System.out.println("Updating Categories " + id);

        Optional<Categories> currentCategories = categoriesHouseService.findById(id);

        if (!currentCategories.isPresent()) {
            System.out.println("Categories with id " + id + " not found");
            return new ResponseEntity<Categories>(HttpStatus.NOT_FOUND);
        }

        currentCategories.get().setName(updateCategoriesRequest.getName());
        categoriesHouseService.save(currentCategories.get());
        return new ResponseEntity<Categories>(currentCategories.get(), HttpStatus.OK);
    }

    //------------------- Delete a Categories --------------------------------------------------------
    @PostMapping("/categories/{id}/delete")
    public ResponseEntity<Categories> deleteCategories(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Categories with id " + id);

        Optional<Categories> categories = categoriesHouseService.findById(id);
        if (!categories.isPresent()) {
            System.out.println("Unable to delete. Categories with id " + id + " not found");
            return new ResponseEntity<Categories>(HttpStatus.NOT_FOUND);
        }

        categoriesHouseService.remove(id);
        return new ResponseEntity<Categories>(HttpStatus.NO_CONTENT);
    }
}
