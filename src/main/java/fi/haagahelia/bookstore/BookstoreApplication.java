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
import fi.haagahelia.bookstore.domain.User;
import fi.haagahelia.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository catrepository,UserRepository urepository) {
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
			
			// Createusers: admin/adminuser/user
			User user1 =new User("user","$2y$12$LsDn3FX/LwKcqnoKRNF/aOAdc3A.Zaks66mX7S51elCVQxmUoWBKa","USER");
			User user2 =new User("admin","$2y$12$lTAqSKqYeMiEfv8Djr3mEeuPOxEo6fBKi0wkA7vGZaFOgcrVDDpnO","ADMIN");
			urepository.save(user1);
			urepository.save(user2);

		};
	}

}
