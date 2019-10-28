package fi.haagahelia.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.domain.CategoryRepository;
import fi.haagahelia.bookstore.domain.Book;


@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTests {
	@Autowired
	private BookRepository repository;
	@Autowired
	private CategoryRepository catrepository;
	
    @Test
    public void findByTitleShouldReturnBook() {
        List<Book> books = repository.findByTitle("Title book");
        
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getId()).isNotNull();
    }
    
	@Test
    public void createNewBook() {
    	Book book = new Book("Martin3","Rowlings","2007","PQL-158",16.9,catrepository.findByName("Thriller").get(0));
    	repository.save(book);
    	assertThat(book.getId()).isNotNull();
    }
    
    @Test
    public void deleteBook() {
        List<Book> books = repository.findByTitle("Title book");
        repository.delete(books.get(0));
        books = repository.findByTitle("Title book");
    	assertThat(books.isEmpty());
    }
}
