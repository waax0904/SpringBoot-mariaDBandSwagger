package com.rdbms.book;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
public class BookController {

	@Autowired
	BookRepository bookRepository;

	// 取得所有資料
	@GetMapping(value="/book/all")
	@Operation(summary = "取得所有書籍資料", description = "Get all books.")
	public List<Book> findAll(){
		return bookRepository.findAll(); 
	}
	
	// 取出對應 id 的資料
	@GetMapping(value="/book/{id}")
	@Operation(summary = "依照書籍編號查找書籍資料(pathVariable)", description = "Get a book by book id.")
	public Book findOne(@Parameter(description = "書籍編號") @PathVariable("id") int bookId){
		return bookRepository.findById(bookId).orElse(null);
	}
	// 避免可能找不到資料也可以直接轉 Optional 來處理
//	public Optional<Book> findOne(@Parameter(description = "書籍編號") @PathVariable("id") int bookId){
//		return bookRepository.findById(bookId); 
//	}

  // @RequestParam 帶有變數的請求，request 要送出 bookId=1 才會處理
	@GetMapping(value="/book/")
	@Operation(summary = "依照書籍編號查找書籍資料(requestParam)", description = "Get a book by book id.")
	public Optional<Book> showBookParam(@Parameter(description = "書籍編號") @RequestParam("bookId") int BookId) {
		return bookRepository.findById(BookId);
	}
	
	// 增加一筆資料
	@PostMapping(value="/book/add")
	@Operation(summary = "增加一筆書籍資料(書籍名稱、作者名稱)", description = "Add a new book data.")
	public void addOne(HttpServletResponse response , @RequestBody Book book) throws IOException{
		bookRepository.save(book);
		response.sendRedirect("/book/all");
	}
	
	// 更新一筆資料
	@PostMapping(value="/book/update/{id}")
	@Operation(summary = "依書籍編號更新一筆既有的書籍資料", description = "Edit a book data by book id.")
	public void updateOne(HttpServletResponse response , 
			@Parameter(description = "書籍編號") @PathVariable("id") int bookId , @RequestBody Book book) throws IOException{
		Book bk = Book.builder()
				.bookid(bookId)
				.name(book.getName())
				.author(book.getAuthor())
				.build();
		bookRepository.save(bk);
		response.sendRedirect("/book/all");
	}
	
	// 刪除對應 id 的資料
	@DeleteMapping(value="/book/delete/{id}")
	@Operation(summary = "依書籍編號刪除一筆既有的書籍資料", description = "Delete a book data by book id.")
	public void deleteOne(HttpServletResponse response , @PathVariable("id") int bookId) throws IOException{
		bookRepository.deleteById(bookId);
	}
	
	//--------------------------------------------------------------------------------

	// 自定義 SQL 內容查詢所有 author 欄位為 Mr.A 的資料
	@GetMapping(value="/book/mra") 
	@Operation(summary = "尋找作者名稱為 Mr.A 的特定書籍資料", description = "Find the books that writen by Mr.A .")
	public List<Book> findMrA() {
		return bookRepository.findAllBooksByMrA();
	}
	
}