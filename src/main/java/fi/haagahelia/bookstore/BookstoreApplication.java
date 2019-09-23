package fi.haagahelia.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository catrepository) {
		return (args) -> {
			log.info("save a couple of books");
			catrepository.save(new Category("Thriller"));
			catrepository.save(new Category("Comedy"));
			catrepository.save(new Category("Fiction"));
			catrepository.save(new Category("Romance"));

			repository.save(new Book("Title book", "Rita Moreira", "2019", "BS589-302", 16.90,catrepository.findByName("Thriller").get(0)));
			repository.save(new Book("blablabla", "Bob bob", "2019", "BS589-302", 16.90,catrepository.findByName("Romance").get(0)));
			
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}

}
