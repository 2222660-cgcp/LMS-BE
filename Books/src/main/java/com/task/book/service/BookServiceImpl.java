package com.task.book.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.task.author.exceptionhandling.AuthorNotFoundException;
import com.task.author.model.Author;
import com.task.book.exceptionhandling.BookAlreadyReservedException;
import com.task.book.exceptionhandling.BookNotFoundException;
import com.task.book.feignClient.AuthorFeignClient;
import com.task.book.feignClient.CategoryFeignClient;
import com.task.book.model.Book;
import com.task.book.model.UserAvailableBooks;
import com.task.book.repository.BookRepository;
import com.task.category.exceptionhandling.CategoryNotFoundException;
import com.task.category.model.Category;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookRepository bookRepository;
	
    private final AuthorFeignClient authorFeignClient;

	private final CategoryFeignClient categoryFeignClient;
	
	public BookServiceImpl(AuthorFeignClient authorFeignClient, CategoryFeignClient categoryFeignClient) {
        this.authorFeignClient = authorFeignClient;
        this.categoryFeignClient = categoryFeignClient;
    }
	
	@Override
	public List<Book> viewAllBooks(String token) {
		return bookRepository.findAll();
	}
	
	@Override
	public Book viewBookById(int book_id, String token) {
		return bookRepository.findById(book_id).orElseThrow(()-> new BookNotFoundException("Book not found"));
	}

	@Override
	public Book addBook(Book book, String token) {
		Optional<Author> author = Optional.of(authorFeignClient.viewAuthorbyId(book.getAuthorId(), token));
			Optional<Category> category = Optional.of(categoryFeignClient.viewCategorybyId(book.getCategoryId(), token));
		if(!author.isPresent()){
			throw new AuthorNotFoundException("Author not found");
		} else if(!category.isPresent()) {
			throw new CategoryNotFoundException("Category not found");
		} else {
			Book bookObj = new Book();
			bookObj.setAuthorId(book.getAuthorId());
			bookObj.setBookName(book.getBookName());
			bookObj.setBookNo(book.getBookNo());
			bookObj.setBookPrice(book.getBookPrice());
			bookObj.setCategoryId(book.getCategoryId());
			bookObj.setReserved(false);
			return bookRepository.save(book);
		}
	}

	@Override
	public Book editBook(int book_id, Book book, String token) {
		Optional<Book> optionalBook = bookRepository.findById(book_id);
		if(!optionalBook.isPresent()) {
			throw new BookNotFoundException("Book not found");
		}
		
		Book bookObject = optionalBook.get();
		if(book.getBookName() != null) {
			bookObject.setBookName(book.getBookName());
		}
		if(book.getAuthorId() != 0) {
			bookObject.setAuthorId(book.getAuthorId());
		}
		if(book.getBookNo() != 0) {
			bookObject.setBookNo(book.getBookNo());
		}
		if(book.getBookPrice() != 0) {
			bookObject.setBookPrice(book.getBookPrice());
		}
		if(book.getCategoryId() != 0) {
			bookObject.setCategoryId(book.getCategoryId());
		}
		bookRepository.save(bookObject);
		return bookObject;
	}

	@Override
	public String deleteBook(int book_id, String token) {
		Optional<Book> optionalBook = bookRepository.findById(book_id);
		if(!optionalBook.isPresent()) {
			throw new BookNotFoundException("Book not found");
		}
		bookRepository.deleteById(book_id);
		return "Book deleted";
	}

	@Override
	public Book viewBookByName(String book_name, String token) {
		Book book = bookRepository.viewBookByName(book_name);
		if(book==null) {
			throw new BookNotFoundException("Book not found");
		}
		return book;
	}

	@Override
	public Book reserveBook(int book_id, String token) {
		Book book = bookRepository.findById(book_id).orElseThrow(()-> new BookNotFoundException("Book not found"));
		if(!book.isReserved()) {
			book.setReserved(true);
			bookRepository.save(book);
		} else {
			throw new BookAlreadyReservedException("Book already reserved");
		}
		return book;	
	}
	
	
//	------------------------------------------------------------------------------------------------------------

	@Override
	public void returnBook(int book_id, String token) {
		Book book = bookRepository.findById(book_id).orElseThrow(()-> new BookNotFoundException("Book not found"));
		if(book.isReserved()) {
			book.setReserved(false);
			bookRepository.save(book);
		} else {
			throw new RuntimeException("Book is not reserved and cannot be returned");
		}
	}
	
	@Override
	public List<UserAvailableBooks> availableBooksForUser(String token) {
		List<Book> books = viewAllBooks(token);
		List<UserAvailableBooks> userAvailableBooks = new ArrayList<> ();
		for(Book book:books) {
			Author author = authorFeignClient.viewAuthorbyId(book.getAuthorId(), token);
			Category category = categoryFeignClient.viewCategorybyId(book.getCategoryId(), token);
			UserAvailableBooks obj = new UserAvailableBooks(book.getBookId(),book.getBookName(),book.getBookNo(),
					author.getAuthorName(),category.getCategoryName(),book.isReserved());
			
			userAvailableBooks.add(obj);
		}	
		return userAvailableBooks;
	}

	@Override
	public UserAvailableBooks searchBooksForUser(String book_name, String token) {
		Book book = bookRepository.viewBookByName(book_name);
		Author author = authorFeignClient.viewAuthorbyId(book.getAuthorId(), token);
		Category category = categoryFeignClient.viewCategorybyId(book.getCategoryId(), token);
		UserAvailableBooks obj = new UserAvailableBooks();
		obj.setBookId(book.getBookId());
		obj.setBookName(book.getBookName());
		obj.setBookNo(book.getBookNo());
		obj.setReserved(book.isReserved());
		obj.setAuthorName(author.getAuthorName());
		obj.setCategoryName(category.getCategoryName());
		return obj;
	}
		
}
