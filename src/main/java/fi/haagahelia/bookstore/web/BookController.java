package fi.haagahelia.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.CategoryRepository;

@Controller 
public class BookController {

	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	
	// RESTFUL service to get all books
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> BooksListRest() {	
        return (List<Book>) repository.findAll();
    } 
    
	// RESTFUL service to get book by id
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
    	return repository.findById(bookId);
    }  
    
    //show all books
	@RequestMapping(value = "/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}

	//add new book
	@RequestMapping(value = "/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories",categoryRepository.findAll());
		return "addbook";
	}

	//save new book
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}
	
	//delete book (admin rights)
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value ="/delete/{id}",method =RequestMethod.GET)
	public String deleteBook(@PathVariable("id")Long bookId,Model model){
	repository.deleteById(bookId);
	return"redirect:../booklist";
	}
	
    //Edit book
	@RequestMapping(value ="/edit/{id}",method =RequestMethod.GET)
	public String editBook(@PathVariable("id")Long bookId,Model model){
		model.addAttribute("book", repository.findById(bookId));
		model.addAttribute("categories",categoryRepository.findAll());
	return "editBook";
	}
	
    @RequestMapping(value="/login")
	public String login() {
		return "login";
	} 

}
