package enterprise.elroi.controllers;

import enterprise.elroi.dto.requests.CategoryRequests;
import enterprise.elroi.dto.responses.ApiResponse;
import enterprise.elroi.dto.responses.CategoryResponse;
import enterprise.elroi.exceptions.categoryServiceExceptions.GetCategoryByIdNotFoundException;
import enterprise.elroi.services.categoryServices.CategoryServicesInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryServicesInterface categoryService;

    @Autowired
    public CategoryController(CategoryServicesInterface categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequests request) {
        try {
            CategoryResponse response = categoryService.createCategory(request);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCategories() {
        try {
            List<CategoryResponse> responses = categoryService.getAllCategories();
            return new ResponseEntity<>(new ApiResponse(true, responses), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable String id) {
        try {
            CategoryResponse response = categoryService.getCategoryById(id);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.OK);
        } catch (GetCategoryByIdNotFoundException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable String id, @RequestBody CategoryRequests request) {
        try {
            CategoryResponse response = categoryService.updateCategory(id, request);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable String id) {
        try {
            categoryService.deleteCategory(id);
            return new ResponseEntity<>(new ApiResponse(true, "Category deleted successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
