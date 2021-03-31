package guestBook.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import common.UtilGuestBook;
import guestBook.model.dao.GuestBookDAO;
import guestBook.model.dto.GuestBookDTO;
import member.model.dto.MemberDTO;

@WebServlet("/guestBook_servlet/*")
public class GuestBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}
	
	protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		UtilGuestBook util = new UtilGuestBook();
		int[] nalja = util.getDateTime();
		Map<String, Integer> naljaMap = new HashMap<>();
		naljaMap.put("now_y", nalja[0]);
		naljaMap.put("now_m", nalja[1]);
		naljaMap.put("now_d", nalja[2]);
		
		String serverInfo[] = util.getServerInfo(request); // request.getContextPath();
		String refer = serverInfo[0];
		String path = serverInfo[1];
		String url = serverInfo[2];
		String uri = serverInfo[3];
		String ip = serverInfo[4];
//		String ip6 = serverInfo[5];
		
		String temp = "";
		
		temp = request.getParameter("pageNumber");
		int pageNumber = util.numberCheck(temp, 1);
		
		temp = request.getParameter("no");
		int no = util.numberCheck(temp, 0);
		
		String search_option = request.getParameter("search_option");
		String search_data = request.getParameter("search_data");
		String[] searchArray = util.searchCheck(search_option, search_data);
		search_option = searchArray[0];
		search_data = searchArray[1];
		
		String[] sessionArray = util.sessionCheck(request);
		int cookNo = Integer.parseInt(sessionArray[0]);
		String cookId = sessionArray[1];
		String cookName = sessionArray[2];
		
		request.setAttribute("naljaMap", naljaMap);
		request.setAttribute("ip", ip);
		request.setAttribute("pageNumber", pageNumber);
//		request.setAttribute("no", no);
		request.setAttribute("search_option", search_option);
		request.setAttribute("search_data", search_data);
		
		GuestBookDAO dao = new GuestBookDAO();
		GuestBookDTO dto = new GuestBookDTO();
		
		PrintWriter out = response.getWriter();
		String page = "/main/main.jsp";
		
		if(url.indexOf("index.do") != -1) {
			request.setAttribute("menu_gubun", "guestBook_index");
			
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if(url.indexOf("write.do") != -1) {
			request.setAttribute("menu_gubun", "guestBook_write");
			
			page = "/guestBook/write.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if(url.indexOf("writeProc.do") != -1) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String passwd = request.getParameter("passwd");
			String content = request.getParameter("content");
			
			
//			Custom cs = new Custom();
//			
//			passwd = cs.toReplace(passwd);
//			passwdChk = cs.toReplace(passwdChk);
			
			dto.setName(name);
			dto.setEmail(email);
			dto.setPasswd(passwd);
			dto.setContent(content);
			
			int result = dao.insertGuestBook(dto);
			
			temp = ""; // page 변수
			if(result > 0) {
				temp = path + "/guestBook_servlet/list.do";
			} else {
				temp = path + "/guestBook_servlet/write.do";
			}
			
			response.sendRedirect(temp);
		} else if(url.indexOf("list.do") != -1) {
			Map<String, Object> map = new HashMap<>();
			map.put("search_option", search_option);
			map.put("search_data", search_data);
			
			int pageSize = 10; //화면에 보여질 게시글의 갯수
			int blockSize = 10;
			int totalRecord = dao.getTotalRecord(map);
			int[] pagerArray = util.pager(pageSize, blockSize, totalRecord, pageNumber);
			int jj = pagerArray[0];
			int startRecord = pagerArray[1];
			int lastRecord = pagerArray[2];
			int totalPage = pagerArray[3];
			int startPage = pagerArray[4];
			int lastPage = pagerArray[5];
			
//			ArrayList<BoardDTO> list = dao.getListAll(startRecord, lastRecord);
//			String resultHtml = dao.getListAllHTML();
			
			List<GuestBookDTO> list = dao.getListAll(map, startRecord, lastRecord);
			request.setAttribute("list", list);
			request.setAttribute("menu_gubun", "guestBook_list");
			
			request.setAttribute("search_option", search_option);
			request.setAttribute("search_data", search_data);
			
			request.setAttribute("pageNumber", pageNumber);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("totalRecord", totalRecord);
			request.setAttribute("jj", jj);
			request.setAttribute("startRecord", startRecord);
			request.setAttribute("lastRecord", lastRecord);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("lastPage", lastPage);
			
			page = "/guestBook/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if(url.indexOf("view.do") != -1) {
			dto.setNo(no);
			
			dto = dao.getSelectOne(dto);
			
			if(dto != null) {
				request.setAttribute("menu_gubun", "guestBook_view");
				request.setAttribute("dto", dto);
				
				page = "/guestBook/view.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);
			}
		} else if(url.indexOf("update.do") != -1) {
			HttpSession session = request.getSession();
			String no_ = request.getParameter("no");
			
			if(session.getAttribute("cookNo") == null || session.getAttribute("cookNo").equals("")) {
				response.setContentType("text/html; charset=utf-8");
				out.println("<script>");
				out.println("alert('로그인 되지 않은 상태로 잘못 접근 하였습니다.\\n로그인 페이지로 이동합니다.');");
				out.println("location.href='" + path + "/member_servlet/login.do';");
				out.println("</script>");
				
				return;
			} else if(no_ == null || no_.equals("") || no_.length() == 0) {
				response.setContentType("text/html; charset=utf-8");
				out.println("<script>");
				out.println("alert('잘못된 접근입니다.\\n로그인 페이지로 이동합니다.');");
				out.println("location.href='" + path + "/member_servlet/login.do';");
				out.println("</script>");
				
				session.invalidate();
				
				return;
			} else {
				no = Integer.parseInt(no_);
			}
			
			dto.setNo(no);
			
			dto = dao.getSelectOne(dto);
			
			if(dto != null) {
				request.setAttribute("menu_gubun", "guestBook_update");
				request.setAttribute("dto", dto);
				
				page = "/guestBook/update.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);
			}
		} else if(url.indexOf("updateProc.do") != -1) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String passwd = request.getParameter("passwd");
			String content = request.getParameter("content");
			
			dto.setNo(no);
			dto.setName(name);
			dto.setEmail(email);
			dto.setPasswd(passwd);
			dto.setContent(content);
			
			int result = dao.updateGuestBook(dto);
			
			temp = ""; // page 변수
			if(result > 0) {
				temp = path + "/guestBook_servlet/view.do?no=" + no;
			} else {
				temp = path + "/guestBook_servlet/update.do?no=" + no;
			}
			
			response.sendRedirect(temp);
		} else if(url.indexOf("delete.do") != -1) {
			String no_ = request.getParameter("no");
			if(no_ == null || no_.equals("") || no_.length() == 0) {
				response.setContentType("text/html; charset=utf-8");
				out.println("<script>");
				out.println("alert('잘못된 접근입니다.\\n로그인 페이지로 이동합니다.');");
				out.println("location.href='" + path + "/member_servlet/login.do';");
				out.println("</script>");
				
				HttpSession session = request.getSession();
				session.invalidate();
				
				return;
			} else {
				no = Integer.parseInt(no_);
			}
			
			dto.setNo(no);
			
			dto = dao.getSelectOne(dto);
			
			if(dto != null) {
				request.setAttribute("menu_gubun", "guestBook_delete");
				request.setAttribute("dto", dto);
				
				page = "/guestBook/delete.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);
			}
		} else if(url.indexOf("deleteProc.do") != -1) {
			String passwd = request.getParameter("passwd");
			String passwdChk = request.getParameter("passwdChk");
			
			if(passwdChk.equals("T")) {
				dto.setNo(no);
				dto.setPasswd(passwd);
//				System.out.println("dto >>> " + dto);
				
				String result = dao.deleteGuestBook(dto);
				
//				temp = ""; // page 변수
				if(result.equals("success")) {
					out.println("success");
//					temp = path + "/guestBook_servlet/list.do";
				} else {
					out.println("dbFail");
//					temp = path + "/guestBook_servlet/delete.do?no=" + no;
				}
				
//				response.sendRedirect(temp);
			} else {
				out.println("passwordFail");
			}
			
		} else if(url.indexOf("getPassword.do") != -1) {
			String passwd = request.getParameter("passwd");
			
			dto.setNo(no);
			dto.setPasswd(passwd);
			
			JSONObject obj = dao.getDbPwd(dto);
			out.println(obj.get("passwd"));
		} 
		
		
	}
}