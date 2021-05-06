package com.callor.book.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

	private String bk_isbn; 	// ISBN CHAR(13)
	private String bk_title; 	// 도서명 NAVARCHAR2(125)
	private String bk_cname; 	// 출판사명 NAVARCHAR2(125)
	private String bk_cceo; 	// 출판사대표 NAVARCHAR2(20)
	private String bk_auname;	//저자명 NAVARCHAR2(50)
	private String bk_autel; 	//저자연락처 NAVARCHAR2(20)
	private String bk_date; 	// 출판일 NUMBER
	private Integer bk_price; 	// 가격 NUMBER
	private Integer bk_pages; 	// 페이지 NUMBER
}
