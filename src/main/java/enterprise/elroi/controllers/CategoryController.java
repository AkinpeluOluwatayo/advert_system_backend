package enterprise.elroi.controllers;

import enterprise.elroi.dto.requests.CategoryRequests;
import enterprise.elroi.dto.responses.ApiResponse;
import enterprise.elroi.dto.responses.CategoryResponse;
import enterprise.elroi.services.categoryServices.CategoryServicesInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/category")
public class CategoryController {

    private final CategoryServicesInterface categoryService;

    @Autowired
    public CategoryController(CategoryServicesInterface categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CategoryResponse>> createCategory(@RequestBody CategoryRequests request) {
        CategoryResponse response = categoryService.createCategory(request);
        return ResponseEntity.status(201).body(new ApiResponse<>(true, response));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getAllCategories() {
        List<CategoryResponse> responses = categoryService.getAllCategories();
        return ResponseEntity.ok(new ApiResponse<>(true, responses));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> getCategoryById(@PathVariable("id") String id) {
        CategoryResponse response = categoryService.getCategoryById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, response));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> updateCategory(
            @PathVariable("id") String id,
            @RequestBody CategoryRequests request) {
        CategoryResponse response = categoryService.updateCategory(id, request);
        return ResponseEntity.ok(new ApiResponse<>(true, response));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCategory(@PathVariable("id") String id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Category deleted successfully"));
    }
}
