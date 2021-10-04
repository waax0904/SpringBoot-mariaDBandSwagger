package com.rdbms.book;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Book {
	public Book() {
	}

	@Id
	private int bookid;
	// 如果這裡變數名稱跟資料表欄位名稱不同時，可以使用 @Column(name = "資料表中的欄位名稱") 來校正
	private String name;
	// 使用 JsonProperty 可以校正 json 格式的名稱
	@JsonProperty("bookAuthor")
	private String author;
}