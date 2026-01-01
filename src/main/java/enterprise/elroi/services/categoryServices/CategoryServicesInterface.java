package enterprise.elroi.services.categoryServices;

import enterprise.elroi.dto.requests.CategoryRequests;
import enterprise.elroi.dto.responses.CategoryResponse;

import java.util.List;

public interface CategoryServicesInterface {

    CategoryResponse createCategory(CategoryRequests request);
    List<CategoryResponse> getAllCategories();
    CategoryResponse getCategoryById(String categoryId);
    CategoryResponse updateCategory(String categoryId, CategoryRequests request);
    void deleteCategory(String categoryId);
}
