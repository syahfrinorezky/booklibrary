package com.example.booklibrary.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.booklibrary.Model.Categories;
import com.example.booklibrary.Repo.CategoriesRepo;
import com.example.booklibrary.Response.ApiResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class BookController {
    @Autowired
    private CategoriesRepo categoriesRepo;

    @GetMapping("/categories")
    public ResponseEntity<ApiResponse> getAllCategories() {
        List<Categories> categories = categoriesRepo.findAllByDeletedAtIsNull();

        return ResponseEntity.ok(
            new ApiResponse(
                "success", "Categories retrieved successfully", categories));
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long id) {
        Categories existingCategories = categoriesRepo.findByIdAndDeletedAtIsNull(id).orElse(null);

        if (existingCategories == null) {
            return ResponseEntity.status(404).body(
                    new ApiResponse("failed", "Category not found", null));
        }

        return ResponseEntity.ok(
                new ApiResponse("success", "Category retrieved successfully", existingCategories));
    }

    @PostMapping("/categories")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Categories categories) {
        Categories newCategories = categoriesRepo.save(categories);

        return ResponseEntity.status(201).body(
                new ApiResponse("success", "Category added successfully", newCategories));
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long id, @RequestBody Categories newData) {
        Categories existingCategories =  categoriesRepo.findByIdAndDeletedAtIsNull(id).orElse(null);

        if (existingCategories == null) {
            return ResponseEntity.status(404).body(
                new ApiResponse("failed", "Category not found", null)
            );
        }

        existingCategories.setName(newData.getName());
        existingCategories.setDescription(newData.getDescription());

        Categories updatedCategories = categoriesRepo.save(existingCategories);

        return ResponseEntity.ok(
            new ApiResponse("success", "Category updated successfully", updatedCategories)
        );
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {
        Categories existingCategories = categoriesRepo.findByIdAndDeletedAtIsNull(id).orElse(null);

        if (existingCategories == null) {
            return ResponseEntity.status(404).body(
                new ApiResponse("failed", "Category not found", null)
            );
        }

        existingCategories.setDeletedAt(LocalDateTime.now());
        categoriesRepo.save(existingCategories);

        return ResponseEntity.ok(
            new ApiResponse("success", "Category deleted successfully", null)
        );
    }
}
