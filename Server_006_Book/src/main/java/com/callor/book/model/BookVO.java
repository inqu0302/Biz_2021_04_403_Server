package com.callor.book.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
// 도서정보 Insert, Update를 위한 객체
public class BookVO {
	
	private String bk_isbn;
	private String bk_title;
	private String bk_ccode;
	private String bk_acode;
	private String bk_auname;
	private String bk_autel;
	private String bk_date;
	private Integer bk_price;
	private Integer bk_pages;
}
