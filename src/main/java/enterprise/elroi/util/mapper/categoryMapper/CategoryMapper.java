package enterprise.elroi.util.mapper.categoryMapper;

import enterprise.elroi.data.models.Category;
import enterprise.elroi.dto.responses.CategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryResponse toResponse(Category category) {
        if (category == null) return null;
        CategoryResponse response = new CategoryResponse();
        response.setName(category.getName());
        response.setDescription(category.getDescription());
        response.setCreatedAt(category.getCreatedAt());
        return response;
    }
}