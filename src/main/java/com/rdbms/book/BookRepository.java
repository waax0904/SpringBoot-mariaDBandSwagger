package com.rdbms.book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book , Integer> {
	// 自訂一個搜尋 SQL 式
	@Query(value = "SELECT * FROM BOOK WHERE author = 'Mr.A'", nativeQuery = true)
	List<Book> findAllBooksByMrA();
}
