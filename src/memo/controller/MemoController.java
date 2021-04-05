package memo.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import common.UtilMemo;
import memo.model.dao.MemoDAO;
import memo.model.dto.MemoDTO;

@WebServlet("/memo_servlet/*")
public class MemoController extends HttpServlet {
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
		
		UtilMemo util = new UtilMemo();
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
		
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		MemoDAO dao = new MemoDAO();
		MemoDTO dto = new MemoDTO();
		
		PrintWriter out = response.getWriter();
		String page = "/main/main.jsp";
		
		if(url.indexOf("index.do") != -1) {
			request.setAttribute("menu_gubun", "memo_index");
			
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if(url.indexOf("writeProc.do") != -1 || url.indexOf("updateProc.do") != -1 || url.indexOf("deleteProc.do") != -1) {
			HttpSession session = request.getSession();
			
			int memberNo = (Integer) session.getAttribute("cookNo");
			
			dto.setNo(no);
			dto.setMemberNo(memberNo);
			dto.setWriter(writer);
			dto.setContent(content);
			
			int result = 0;
			if(url.indexOf("writeProc.do") != -1) {
				result = dao.insertMemo(dto);
			} else if(url.indexOf("updateProc.do") != -1) {
				result = dao.updateMemo(dto);
			} else if(url.indexOf("deleteProc.do") != -1) {
				result = dao.deleteMemo(dto);
			}
			
			if(result > 0) {
//				System.out.println("등록되었습니다.");
			} else {
//				System.out.println("결과코드 : " + result);
			}
		} else if(url.indexOf("list.do") != -1) {
			HttpSession session = request.getSession();
			int memberNo = 0;
			if(session.getAttribute("cookNo") != null) {
				memberNo = (Integer) session.getAttribute("cookNo");
			}
			dto.setMemberNo(memberNo);
			
			
			int pageSize = 10;
			int blockSize = 10;
			int totalRecord = dao.getTotalRecord(dto);
			int jj = totalRecord - pageSize * (pageNumber - 1);
			
			int startRecord = pageSize * (pageNumber - 1) + 1;
			int lastRecord = pageSize * pageNumber;
			
			int totalPage = 0;
			int startPage = 1;
			int lastPage = 1;
			if(totalRecord > 0) {
				totalPage = totalRecord / pageSize + (totalRecord % pageSize == 0 ? 0 : 1);
				startPage = (pageNumber / blockSize - (pageNumber % blockSize != 0 ? 0 : 1)) * blockSize + 1;
				
//				if(pageNumber % blockSize != 0) {
//					startPage = (int) (pageNumber / blockSize) * blockSize + 1;
//				} else {
//					startPage = ((int)(pageNumber / blockSize) - 1) * blockSize + 1;
//				}
				
				lastPage = startPage + blockSize - 1;
				if(lastPage > totalPage) {
					lastPage = totalPage;
				}
			}
			
//			ArrayList<MemoDTO> list = dao.getListAll(dto);
			List<MemoDTO> list = dao.getListAll(dto, startRecord, lastRecord);
			request.setAttribute("list", list);
			request.setAttribute("menu_gubun", "memo_list");
			
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
			
			page = "/memo/ajaxMapping.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
	}
}