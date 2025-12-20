package enterprise.elroi.services.servicesImplementation.categoryServicesImpl;

import enterprise.elroi.data.models.Category;
import enterprise.elroi.data.repositories.CategoryRepository;
import enterprise.elroi.dto.requests.CategoryRequests;
import enterprise.elroi.dto.responses.CategoryResponse;
import enterprise.elroi.exceptions.categoryServiceExceptions.GetCategoryByIdNotFoundException;
import enterprise.elroi.exceptions.categoryServiceExceptions.UpdateCategoryNotFoundException;
import enterprise.elroi.services.categoryServices.CategoryServicesInterface;
import enterprise.elroi.util.mapper.categoryMapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryServicesInterface {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CategoryResponse createCategory(CategoryRequests request) {
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setCreatedAt(LocalDateTime.now());

        Category savedCategory = repository.save(category);
        return mapper.toResponse(savedCategory);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = repository.findAll();
        List<CategoryResponse> responses = new ArrayList<>();

        for (Category category : categories) {
            responses.add(mapper.toResponse(category));
        }

        return responses;
    }

    @Override
    public CategoryResponse getCategoryById(String categoryId) {
        Category category = repository.findById(categoryId)
                .orElseThrow(() -> new GetCategoryByIdNotFoundException("Category not found"));

        return mapper.toResponse(category);
    }

    @Override
    public CategoryResponse updateCategory(String categoryId, CategoryRequests request) {
        Category category = repository.findById(categoryId)
                .orElseThrow(() -> new UpdateCategoryNotFoundException("Category not found"));

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        Category updatedCategory = repository.save(category);
        return mapper.toResponse(updatedCategory);
    }

    @Override
    public void deleteCategory(String categoryId) {
        Category category = repository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        repository.delete(category);
    }
}
