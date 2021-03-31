package member.controller;

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

import common.Util;
import member.model.dao.MemberDAO;
import member.model.dto.MemberDTO;
import member.util.MemberUtil;

@WebServlet("/member_servlet/*")
public class MemberController extends HttpServlet {
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
		
		MemberUtil util = new MemberUtil();
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
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();
		
		PrintWriter out = response.getWriter();
		String page = "/main/main.jsp";
		
		if(url.indexOf("index.do") != -1) {
			String proc = request.getParameter("proc");
			if(proc != null) {
				request.setAttribute("proc", proc);
				request.setAttribute("cookNo", cookNo);
			}
			request.setAttribute("menu_gubun", "member_index");
			
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
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
			
			List<MemberDTO> list = dao.getListAll(map, startRecord, lastRecord);
			request.setAttribute("list", list);
			request.setAttribute("menu_gubun", "board_list");
			
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
			
			page = "/member/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if(url.indexOf("write.do") != -1) {
			request.setAttribute("menu_gubun", "member_write");
			
			page = "/member/write.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if(url.indexOf("writeProc.do") != -1) {
			String inputId = request.getParameter("id");
			String inputPasswd = request.getParameter("passwd");
			String inputPasswdChk = request.getParameter("passwdChk");
			String inputName = request.getParameter("name");
			String gender = request.getParameter("gender");
			String bornYear_ = request.getParameter("bornYear");
			
			String postCode = request.getParameter("sample6_postcode");
			String address = request.getParameter("sample6_address");
			String detailAddress = request.getParameter("sample6_detailAddress");
			String extraAddress = request.getParameter("sample6_extraAddress");
			
			int bornYear = Integer.parseInt(bornYear_);
			
			String id = inputId.replace(" ", "");
			String passwd = inputPasswd.replace(" ", "");
			String passwdChk = inputPasswdChk.replace(" ", "");
			String name = inputName.replace(" ", "");
			
			passwd = util.toReplace(passwd);
			passwdChk = util.toReplace(passwdChk);
			
			response.setContentType("text/html; charset=utf-8");
			
//			String[] words = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
//					"q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
//			
//			if(inputId.length() != id.length() || inputId.length() == 0) {
//				out.println("<script>");
//				out.println("alert('아이디에 공백을 입력할 수 없습니다.');");
//				out.println("location.href='" + path + "/member_servlet/write.do';");
//				out.println("</script>");
//				
//				return;
//			}
//			
//			if(inputPasswd.length() != passwd.length() || inputPasswd.length() == 0) {
//				out.println("<script>");
//				out.println("alert('비밀번호는 공백을 입력할 수 없습니다.');");
//				out.println("location.href='" + path + "/member_servlet/write.do';");
//				out.println("</script>");
//				
//				return;
//			}
//			
//			if(inputPasswdChk.length() != passwdChk.length() || inputPasswdChk.length() == 0) {
//				out.println("<script>");
//				out.println("alert('비밀번호 확인은 공백을 입력할 수 없습니다.');");
//				out.println("location.href='" + path + "/member_servlet/write.do';");
//				out.println("</script>");
//				
//				return;
//			}
//			
//			if(inputName.length() != name.length() || inputName.length() == 0) {
//				out.println("<script>");
//				out.println("alert('이름은 공백을 입력할 수 없습니다.');");
//				out.println("location.href='" + path + "/member_servlet/write.do';");
//				out.println("</script>");
//				
//				return;
//			}
//			
//			for(int i = 0; i < name.length(); i++) {
//				String nameSubString = name.substring(i, i + 1);
//				
//				for(int j = 0; j < words.length; j++) {
//					if(nameSubString.toLowerCase().equals(words[j])) {
//						out.println("<script>");
//						out.println("alert('한글만 입력할 수 있습니다.');");
//						out.println("location.href='" + path + "/member_servlet/write.do';");
//						out.println("</script>");
//						
//						return;
//					}
//				}
//			}
//			
//			
//			if(!gender.toUpperCase().equals("F") || !gender.toUpperCase().equals("M")) {
//				out.println("<script>");
//				out.println("alert('성별을 잘못 입력하였습니다.');");
//				out.println("location.href='" + path + "/member_servlet/write.do';");
//				out.println("</script>");
//				
//				return;
//			}
			
			
			
			dto.setId(id);
			dto.setPasswd(passwd);
			dto.setPasswdChk(passwdChk);
			dto.setName(name);
			dto.setGender(gender);
			dto.setBornYear(bornYear);
			
			dto.setPostCode(postCode);
			dto.setAddress(address);
			dto.setDetailAddress(detailAddress);
			dto.setExtraAddress(extraAddress);
			
			int result = dao.insertMember(dto);
			
//			temp = ""; // page 변수
//			if(result > 0) {
//				temp = path + "/member_servlet/login.do";
//			} else {
//				temp = path + "/member_servlet/write.do";
//			}
//			
//			response.sendRedirect(temp);
		} else if(url.indexOf("login.do") != -1) {
			request.setAttribute("menu_gubun", "member_login");
			
			page = "/member/login.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if(url.indexOf("loginProc.do") != -1) {
			String id = request.getParameter("id");
			String passwd = request.getParameter("passwd");
			
			dto.setId(id);
			dto.setPasswd(passwd);
			
			MemberDTO resultDto = dao.login(dto);
			
			temp = ""; // page 변수
			if(resultDto != null) {
				HttpSession session = request.getSession();
				session.setAttribute("cookNo", resultDto.getNo());
				session.setAttribute("cookId", resultDto.getId());
				session.setAttribute("cookName", resultDto.getName());
				
				temp = path + "/index.do";
			} else {
				temp = path + "/member_servlet/login.do";
			}
			
			response.sendRedirect(temp);
		} else if(url.indexOf("logout.do") != -1) {
			HttpSession session = request.getSession();
			session.invalidate();
			
			response.setContentType("text/html; charset=utf-8");
			out.println("<script>");
			out.println("alert('로그아웃 되었습니다.\\n즐거운 하루 되세요.');");
			out.println("location.href='" + path + "';");
			out.println("</script>");
		} else if(url.indexOf("view.do") != -1) {
			Map<String, Object> map = new HashMap<>();
			map.put("search_option", search_option);
			map.put("search_data", search_data);
			
			dto.setNo(no);
			dto = dao.getSelectOne(map, dto);
			
			if(dto != null) {
				request.setAttribute("menu_gubun", "member_view");
				request.setAttribute("dto", dto);
				
				page = "/member/view.jsp";
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
				request.setAttribute("menu_gubun", "member_update");
				request.setAttribute("dto", dto);
				
				page = "/member/update.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);
			}
		} else if(url.indexOf("updateProc.do") != -1) {
			String passwd = request.getParameter("passwd");
			String gender = request.getParameter("gender");
			String bornYear_ = request.getParameter("bornYear");
			int bornYear = Integer.parseInt(bornYear_);
			
			String postCode = request.getParameter("sample6_postcode");
			String address = request.getParameter("sample6_address");
			String detailAddress = request.getParameter("sample6_detailAddress");
			String extraAddress = request.getParameter("sample6_extraAddress");
			
			dto.setNo(no);
			dto.setPasswd(passwd);
			dto.setGender(gender);
			dto.setBornYear(bornYear);
			
			dto.setPostCode(postCode);
			dto.setAddress(address);
			dto.setDetailAddress(detailAddress);
			dto.setExtraAddress(extraAddress);
			
			int result = dao.updateMember(dto);
			
//			temp = ""; // page 변수
//			if(result > 0) {
//				temp = path + "/member_servlet/view.do?no=" + no;
//			} else {
//				temp = path + "/member_servlet/update.do?no=" + no;
//			}
//			
//			response.sendRedirect(temp);
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
				request.setAttribute("menu_gubun", "member_delete");
				request.setAttribute("dto", dto);
				
				page = "/member/delete.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);
			}
		} else if(url.indexOf("deleteProc.do") != -1) {
			String passwd = request.getParameter("passwd");
			
			dto.setNo(no);
			dto.setPasswd(passwd);
			
			String result = dao.deleteMember(dto);
			
			if(result.equals("success")) {
				out.println("success");
				HttpSession session = request.getSession();
				session.invalidate();
			} else if(result.equals("passwdFail")) {
				out.println("password");
			} else {
				out.println("dbFail");
			}
		} else if(url.indexOf("id_check.do") != -1) {
			String id = request.getParameter("id");
			dto.setId(id);
			int result = dao.idCheck(dto);
			
			out.println(result);
		} else if(url.indexOf("id_check_win.do") != -1) {
			
			page = "/member/id_check.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if(url.indexOf("id_check_win_proc.do") != -1) {
			String id = request.getParameter("id");
			dto.setId(id);
			String result = dao.idCheckResultString(dto);
			
			if(result == null || result.equals("")) {
				result = id;
			} else {
				result = "";
			}
			
			out.println(result);
		}
	}
}