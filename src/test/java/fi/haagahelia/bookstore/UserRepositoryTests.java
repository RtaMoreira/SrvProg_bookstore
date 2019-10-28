package fi.haagahelia.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.User;
import fi.haagahelia.bookstore.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTests {
	@Autowired
	private UserRepository repository;
	
    @Test
    public void findByUsernameShouldReturnUser() {
        User user = repository.findByUsername("user");
        assertThat(user).isNotNull();
    }
    
	@Test
    public void createUser() {
		User user = new User("user2", "$2a$10$pcyvJXKC3KyOBx988OOBB.Zx6qTNCWJoUNWW7rAJQPpOWPz0mo/n2", "USER");
    	repository.save(user);
    	assertThat(user.getId()).isNotNull();
    }
	
    @Test
    public void deleteUser() {
        User user = repository.findByUsername("user");
        repository.delete(user);
        user = repository.findByUsername("user");
    	assertThat(user).isNull();
    }

}
