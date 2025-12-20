package enterprise.elroi.services.servicesImplementation.categoryServicesImpl;

import enterprise.elroi.data.models.Category;
import enterprise.elroi.data.repositories.CategoryRepository;
import enterprise.elroi.dto.requests.CategoryRequests;
import enterprise.elroi.dto.responses.CategoryResponse;
import enterprise.elroi.exceptions.categoryServiceExceptions.GetCategoryByIdNotFoundException;
import enterprise.elroi.exceptions.categoryServiceExceptions.UpdateCategoryNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        categoryRepository.deleteAll();
    }


    @Test
    void testCreateCategory() {
        CategoryRequests request = new CategoryRequests();
        request.setName("Electronics");
        request.setDescription("Electronic devices");

        CategoryResponse response = categoryService.createCategory(request);

        assertEquals("Electronics", response.getName());
        assertEquals("Electronic devices", response.getDescription());
        assertNotNull(response.getCreatedAt());
        assertEquals(1, categoryRepository.findAll().size());
    }



    @Test
    void testGetAllCategories() {
        Category category1 = new Category();
        category1.setName("Cars");
        category1.setDescription("Vehicles");

        Category category2 = new Category();
        category2.setName("Houses");
        category2.setDescription("Real estate");

        categoryRepository.save(category1);
        categoryRepository.save(category2);

        List<CategoryResponse> responses = categoryService.getAllCategories();

        assertEquals(2, responses.size());
    }
    @Test
    void testGetCategoryById() {
        Category category = new Category();
        category.setName("Fashion");
        category.setDescription("Clothing");

        Category saved = categoryRepository.save(category);

        CategoryResponse response = categoryService.getCategoryById(saved.getId());

        assertEquals("Fashion", response.getName());
        assertEquals("Clothing", response.getDescription());
    }

    @Test
    void testGetCategoryById_whenNotFound_shouldThrowException() {
        assertThrows(GetCategoryByIdNotFoundException.class, () -> categoryService.getCategoryById("invalid-id")
        );
    }



    @Test
    void testUpdateCategory() {
        Category category = new Category();
        category.setName("Old Name");
        category.setDescription("Old Description");

        Category saved = categoryRepository.save(category);

        CategoryRequests request = new CategoryRequests();
        request.setName("New Name");
        request.setDescription("New Description");

        CategoryResponse response =
                categoryService.updateCategory(saved.getId(), request);

        assertEquals("New Name", response.getName());
        assertEquals("New Description", response.getDescription());
    }

    @Test
    void testUpdateCategory_whenNotFound_shouldThrowException() {
        CategoryRequests request = new CategoryRequests();
        request.setName("Name");
        request.setDescription("Description");

        assertThrows(UpdateCategoryNotFoundException.class, () -> categoryService.updateCategory("invalid-id", request)
        );
    }



    @Test
    void testDeleteCategory() {
        Category category = new Category();
        category.setName("To Delete");
        category.setDescription("Delete me");

        Category saved = categoryRepository.save(category);

        categoryService.deleteCategory(saved.getId());

        assertTrue(categoryRepository.findById(saved.getId()).isEmpty());
    }
    @Test
    void testDeleteCategory_whenNotFound_shouldThrowException() {
        assertThrows(RuntimeException.class, () -> categoryService.deleteCategory("Invalid Id")
        );
    }
}