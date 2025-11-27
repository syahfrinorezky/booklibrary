package com.example.booklibrary.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.booklibrary.Model.Books;
import com.example.booklibrary.Model.Categories;
import com.example.booklibrary.Repo.BooksRepo;
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
    @Autowired
    private BooksRepo booksRepo;

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
        Categories existingCategories = categoriesRepo.findByIdAndDeletedAtIsNull(id).orElse(null);

        if (existingCategories == null) {
            return ResponseEntity.status(404).body(
                    new ApiResponse("failed", "Category not found", null));
        }

        existingCategories.setName(newData.getName());
        existingCategories.setDescription(newData.getDescription());

        Categories updatedCategories = categoriesRepo.save(existingCategories);

        return ResponseEntity.ok(
                new ApiResponse("success", "Category updated successfully", updatedCategories));
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {
        Categories existingCategories = categoriesRepo.findByIdAndDeletedAtIsNull(id).orElse(null);

        if (existingCategories == null) {
            return ResponseEntity.status(404).body(
                    new ApiResponse("failed", "Category not found", null));
        }

        existingCategories.setDeletedAt(LocalDateTime.now());
        categoriesRepo.save(existingCategories);

        return ResponseEntity.ok(
                new ApiResponse("success", "Category deleted successfully", null));
    }

    @GetMapping("/books")
    public ResponseEntity<ApiResponse> getAllBooks() {
        List<Books> books = booksRepo.findAllByDeletedAtIsNull();

        return ResponseEntity.ok(
                new ApiResponse(
                        "success", "Books retrieved successfully", books));
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<ApiResponse> getBookById(@PathVariable Long id) {
        Books existingBook = booksRepo.findByIdAndDeletedAtIsNull(id).orElse(null);

        if (existingBook == null) {
            return ResponseEntity.status(404).body(
                    new ApiResponse("failed", "Book not found", null));
        }

        return ResponseEntity.ok(
                new ApiResponse("success", "Book retrieved successfully", existingBook));
    }

    @PostMapping("/books")
    public ResponseEntity<ApiResponse> addBook(@RequestBody Books book) {
        Books newBook = booksRepo.save(book);

        return ResponseEntity.status(201).body(
                new ApiResponse("success", "Book added successfully", newBook));
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<ApiResponse> updateBook(@PathVariable Long id, @RequestBody Books newData) {
        Books existingBook = booksRepo.findByIdAndDeletedAtIsNull(id).orElse(null);

        if (existingBook == null) {
            return ResponseEntity.status(404).body(
                    new ApiResponse("failed", "Book not found", null));
        }

        existingBook.setTitle(newData.getTitle());
        existingBook.setAuthor(newData.getAuthor());
        existingBook.setCategory(newData.getCategory());
        existingBook.setPublisher(newData.getPublisher());
        existingBook.setYear(newData.getYear());
        existingBook.setStock(newData.getStock());

        Books updatedBook = booksRepo.save(existingBook);

        return ResponseEntity.ok(
                new ApiResponse("success", "Book updated successfully", updatedBook));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<ApiResponse> deleteBook(@PathVariable Long id) {
        Books existingBook = booksRepo.findByIdAndDeletedAtIsNull(id).orElse(null);

        if (existingBook == null) {
            return ResponseEntity.status(404).body(
                    new ApiResponse("failed", "Book not found", null));
        }

        existingBook.setDeletedAt(LocalDateTime.now());
        booksRepo.save(existingBook);

        return ResponseEntity.ok(
                new ApiResponse("success", "Book deleted successfully", null));
    }
}
