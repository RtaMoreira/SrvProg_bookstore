package fi.haagahelia.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.domain.CategoryRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CatagoryRepositoryTests {
	
	@Autowired
	private CategoryRepository repository;
	
	   @Test
	    public void findByNameShouldReturnCategory() {
	       	List<Category> cat = repository.findByName("Comedy");
	        
	        assertThat(cat).hasSize(1);
	        assertThat(cat.get(0).getId()).isNotNull();
	    }
	   
		@Test
	    public void createNewCategory() {
	    	Category cat = new Category("Thriller2");
	    	repository.save(cat);
	    	assertThat(cat.getId()).isNotNull();
	    }
		
	    @Test
	    public void deleteCategory() {
	    	List<Category> cat = repository.findByName("Thriller");
	        repository.delete(cat.get(0));
	        cat = repository.findByName("Thriller");
	    	assertThat(cat.isEmpty());
	    }

}
