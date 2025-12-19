package enterprise.elroi.services.categoryServices;

import enterprise.elroi.dto.requests.CategoryRequests;
import enterprise.elroi.dto.responses.CategoryResponse;

import java.util.List;

public interface CategoryServicesInterface {

    //admin use only
    CategoryResponse createCategory(CategoryRequests request);

    List<CategoryResponse> getAllCategories();

    CategoryResponse getCategoryById(String categoryId);

    //admin use only
    CategoryResponse updateCategory(String categoryId, CategoryRequests request);

    // admin use only
    void deleteCategory(String categoryId);
}
