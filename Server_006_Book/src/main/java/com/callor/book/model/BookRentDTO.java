package com.callor.book.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * 주문번호 NUMBER, 		br_seq
 * 회원코드	CHAR(5)			br_bcode
 * 회원명	NVARCHAR2(50)	br_bname
 * 대여일	VARCHAR2(10) 	br_sdate
 * 회원연락처 VARCHAR2(20)	br_tel
 * ISBN	CHAR(13)			br_isbn
 * 도서명	NVARCHAR2(125)	br_title
 * 반납일	VARCHAR2(10)	br_edate
 * 
 * 조회할때(SELECT) Service 각 method 가 return 할 type
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookRentDTO {
	
	private Long br_seq;
	private String br_bcode;
	private String br_bname;
	private String br_sdate;
	private String br_tel;;
	private String br_isbn;
	private String br_title;
	private String br_edate;
	private String br_price;
}
