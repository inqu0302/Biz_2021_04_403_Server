package com.callor.book.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.callor.book.model.BookRentDTO;
import com.callor.book.model.BookRentVO;
import com.callor.book.model.BuyerDTO;
import com.callor.book.service.BookRentService;
import com.callor.book.service.BuyerService;
import com.callor.book.service.impl.BookRentServiceImplV1;
import com.callor.book.service.impl.BuyerServiceImplV1;

/*
 * Web Browser의 Request를 처리할 클래스
 */

@WebServlet("/rent/*")
public class BookRentController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected BookRentService brService;
	protected BuyerService buService;
	
	public BookRentController() {
		brService = new BookRentServiceImplV1();
		buService = new BuyerServiceImplV1();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// rent/* 로 요청이 되면 * 위치에 부착되는 Sub요청을 분리한다
		// rent/seq라고 요청을 하면 subPath에는 / seq라는 문자열이 담길것이다
		String subPath = req.getPathInfo();
		
		// outputSteram을 사용하여 문자열 방식으로 응답을 하기위한 준비
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		// rent/seq 로 요청이 들어오면		
		if(subPath.equals("/seq")) {
			// 주문번호로 찾기
			
			String strSeq = req.getParameter("id");
			
			if(strSeq == null || strSeq.equals("")) {
				out.println("주문번호가 없음");
				out.close();
			} else {
				Long nSeq = Long.valueOf(strSeq);
				BookRentDTO brDTO = brService.findById(nSeq);
				
				// view 에서 보여줄 데이터 생성
				/*
				 * ServletContext
				 * Tomcat을 기반으로 작성된 Web App Service에서 요청(Req) 응답(Res)를 총괄하는
				 * 정보가 담긴 객체
				 * Web App Service를 구현하기 위하여 Req, Res 를 처리하는 여러가지 기능을 구현해야 하는데
				 * 그러한 기능을 미리 구현해 놓았기 때문에 ServletContext를 getter하는 것만으로 충분하다
				 * 
				 * DB등으로 부터 조회된 데이터를 Web에게 응답하고자 할때 쉬운 방법으로 전달할 수 있도록
				 * 하는 기능이 미리 구현되어 있다.
				 */
				
				ServletContext app = this.getServletContext();
				
				// bService가 return 한 brDTO를 app객체에 BOOK이라는 속성변수로 세팅하기
				// app 객체에 BOOK 이라는 객체변수를 생성하고 BOOK 변수에 brDTO값을 저장한다
				// BookRentDTO BOOK = brDTO 이런 형식의 코드가 실행된다.
				// 셋팅된 BOOK 객체변수는 jsp 파일에서 참조하여 값을 표현할 수 있다.
				app.setAttribute("BOOK", brDTO);
				
				// book.jsp 파일을 읽어서 app 에 setting한 BOOK 변수와 함께
				// Rendering 하라
				// webapp/WEB-INF/view/book.jsp 파일을 읽어서 JAVA코드로 변환하고, 실행할 준비를 해라
				RequestDispatcher disp = app.getRequestDispatcher("/WEB-INF/views/book.jsp");
				
				//Rendering 된 view 데이터를 Web browser로 response하라
				disp.forward(req, resp);
			}
			
		} else if(subPath.equals("/list")) {
			// 도서대여 전체 목록
			out.println("도서대여 전체목록 보기");
			brService.selectAll();
		
		} else if (subPath.equals("/isbn")) {
			// 도서코드로 찾기
			brService.findByISBN("isbn");
			
		} else if (subPath.equals("/buyer")) {
			// 회원코드로 찾기
			brService.findByBuyerCode("buyercode");
			
			// rent/order 로 요청하면 주문서 작성처음화면 보여주기
			// 회원이름을 입력하는 화면을 보여주기
		} else if (subPath.equals("/order")) {
			// 대여정보 추가
			
			RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/views/order.jsp");
			
			disp.forward(req, resp);
			
			
		} else if (subPath.equals("/order/page1")) {
			String bu_name = req.getParameter("bu_name");
			
			if(bu_name == null || bu_name.equals("")) {
				out.println("회원이름을 반드시 입력해야 합니다.");
				out.close();
			} else {
				List<BuyerDTO> buList = buService.findByName(bu_name);
				
				// service에서 전달된 데이터 확인용
				System.out.println("=".repeat(50));
				for(BuyerDTO d : buList) {
					System.out.println(d.toString());
				}
				System.out.println("=".repeat(50));
				
				ServletContext app = req.getServletContext();
				app.setAttribute("BUYERS", buList);
				
				RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/views/page1.jsp");
				disp.forward(req, resp);
				
			}
			
		} else if (subPath.equals("/order/page2")) {
			String bu_code = req.getParameter("bu_code");
			
			BuyerDTO buyerDTO = buService.findById(bu_code);
			ServletContext app = req.getServletContext();
			
			app.setAttribute("BUYER", buyerDTO);
			RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/views/page2.jsp");
			
			disp.forward(req, resp);
			
		} else if (subPath.equals("/return")) {
			// 반납하기
			BookRentVO bookRentVO = new BookRentVO();
			brService.update(bookRentVO);
			
		} else {
			// 그만하기
		}
	}
}