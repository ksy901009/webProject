package board.controller;

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

import common.UtilBoard;
import comment.model.dto.BoardCommentDTO;
import board.model.dao.BoardDAO;
import board.model.dto.BoardDTO;

@WebServlet("/board_servlet/*")
public class BoardController extends HttpServlet {
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
		
		UtilBoard util = new UtilBoard();
		int[]  nalja = util.getDateTime();
		HashMap<String, Integer> naljaMap = new HashMap<>();
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
		
		temp = request.getParameter("tbl");
		String tbl = util.tblCheck(temp, "freeboard");
		
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
		request.setAttribute("tbl", tbl);
		request.setAttribute("pageNumber", pageNumber);
//		request.setAttribute("no", no);
		request.setAttribute("search_option", search_option);
		request.setAttribute("search_data", search_data);
		
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = new BoardDTO();
		
		PrintWriter out = response.getWriter();
		String page = "/main/main.jsp";
		
		if(url.indexOf("index.do") != -1) {
			request.setAttribute("menu_gubun", "board_index");
			
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if(url.indexOf("write.do") != -1 || url.indexOf("reply.do") != -1) {
			request.setAttribute("menu_gubun", "board_write");
			
			if(url.indexOf("reply.do") != -1) { //답변
				dto.setNo(no);
				dto = dao.getSelectOne(dto);
				
				temp = "[" + dto.getWriter() + "]님이 작성한 글입니다.\n";
				temp += dto.getContent();
				temp = temp.replace("\n", "\n> ");
				temp += "\n----------------------------\n";
				dto.setContent(temp);
				
				request.setAttribute("dto", dto);
			}
			
			page = "/board/write.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} 
		else if(url.indexOf("list.do") != -1) {
			HttpSession session = request.getSession();
			
			HashMap<String, Object> map = new HashMap<>();
			map.put("search_option", search_option);
			map.put("search_data", search_data);
			map.put("tbl", tbl);
			
			int pageSize = 10; //화면에 보여질 게시글의 갯수
			int blockSize = 10;
			int totalRecord = dao.getTotalRecord(map);
//			int totalRecord = dao.getTotalRecord();
			int[] pagerArray = util.pager(pageSize, blockSize, totalRecord, pageNumber);
			int jj = pagerArray[0];
			int startRecord = pagerArray[1];
			int lastRecord = pagerArray[2];
			int totalPage = pagerArray[3];
			int startPage = pagerArray[4];
			int lastPage = pagerArray[5];
			
//			ArrayList<BoardDTO> list = dao.getListAll(startRecord, lastRecord);
			
			List<BoardDTO> list = dao.getListAll(map, startRecord, lastRecord);
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
			
			page = "/board/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if(url.indexOf("view.do") != -1 || url.indexOf("update.do") != -1 || url.indexOf("delete.do") != -1) {
			dto.setNo(no);
			dto = dao.getSelectOne(dto);
			String gubunTemp = "";
			
			if(url.indexOf("view.do") != -1) {
				dao.setUpdateHit(dto);
				String imsiPage = "viewPage";
				if(dto.getSecretGubun().equals("T")) { //비밀글이면
					String view_passwd = util.nullCheck(request.getParameter("view_passwd"));
					if(dto.getPasswd().equals(view_passwd) && !dto.getPasswd().equals("")) {
						
					} else {
						imsiPage = "viewPasswdPage";
					}
				}
				request.setAttribute("imsiPage", imsiPage);
				gubunTemp = "board_view";
				page = "/board/view.jsp";
			} else if(url.indexOf("update.do") != -1) {
				gubunTemp = "board_update";
				page = "/board/update.jsp";
			} else if(url.indexOf("delete.do") != -1) {
				gubunTemp = "board_delete";
				page = "/board/delete.jsp";
			}
			
			temp = dto.getContent();
			temp = temp.replace("\n", "<br>");
			dto.setContent(temp);
			
			request.setAttribute("menu_gubun", gubunTemp);
			request.setAttribute("dto", dto);
			
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if(url.indexOf("updateProc.do") != -1 || url.indexOf("deleteProc.do") != -1 || url.indexOf("writeProc.do") != -1) {
			String writer = request.getParameter("writer");
			String email = request.getParameter("email");
			String passwd = request.getParameter("passwd");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String noticeGubun = request.getParameter("noticeGubun");
			
			dto.setNo(no);
			dto.setWriter(writer);
			dto.setSubject(subject);
			dto.setContent(content);
			dto.setPasswd(passwd);
			dto.setEmail(email);
			
			int result = 0;
			if(url.indexOf("writeProc.do") != -1) {
				int noticeNo = 0;
				if(noticeGubun == null || noticeGubun.trim().equals("") || !noticeGubun.equals("T")) {
					noticeNo = 0;
				} else {
					noticeNo = dao.getMaxNoticeNo(tbl) + 1;
				}
				
				String secretGubun = request.getParameter("secretGubun");
				if(secretGubun == null || secretGubun.trim().equals("") || !secretGubun.equals("T")) {
					secretGubun = "F";
				} else {
					secretGubun = "T";
				}
				
				int num = dao.getMaxNum() + 1;
				int refNo = dao.getMaxRefNo() + 1; //글 그룹을 의미 = 쿼리를 실행시켜서 가장 큰 ref 값을 가져온 후 +1을 해주면 됨.
				int stepNo = 1;
				int levelNo = 1;
				int parentNo = 0;
				
				if(no > 0) { // 답변글
					BoardDTO dto2 = new BoardDTO();
					dto2.setNo(no);
					dto2 = dao.getSelectOne(dto2);
					dao.setUpdateReLevel(dto2); //답변글.. 부모 글보다 큰 levelNo 값을 전부 1씩 증가시켜준다.
					refNo = dto2.getRefNo();
					stepNo = dto2.getStepNo() + 1;
					levelNo = dto2.getLevelNo() + 1;
					parentNo = dto2.getNo();
				}
				
				int hit = 0;
				
				dto.setNo(no);
				dto.setNum(num);
				dto.setTbl(tbl);
				dto.setWriter(writer);
				dto.setSubject(subject);
				dto.setContent(content);
				dto.setEmail(email);
				dto.setPasswd(passwd);
				
				dto.setRefNo(refNo);
				dto.setStepNo(stepNo);
				dto.setLevelNo(levelNo);
				dto.setParentNo(parentNo);
				dto.setHit(hit);
				dto.setIp(ip);
				
				dto.setMemberNo(no);
				dto.setNoticeNo(noticeNo);
				dto.setSecretGubun(secretGubun);
				
				result = dao.insertBoard(dto);
			} else if(url.indexOf("deleteProc.do") != -1) {
				String insertPW = request.getParameter("passwd");
				dto = dao.getSelectOne(dto);
				String DBpw = dto.getPasswd();
			   
				if(insertPW.equals(DBpw)) {
					String deleteResult = dao.deleteBoard(dto);
					if(deleteResult.equals("success")) {
						out.println("<script>$('#span_passwd').text('O');</script>");
					}
				} else {
					out.println("<script>$('#span_passwd').text('X');</script>");
				}
				out.flush();
				out.close();
			} else if(url.indexOf("updateProc.do") != -1) {
				result = dao.updateBoard(dto);
				
				if(result > 0) {
//					System.out.println(result + "수정하기 성공");
				} else {
//					System.out.println(result + "수정하기 실패");
				}
			}
		} else if(url.indexOf("commentProc.do") != -1) {
			String writer = request.getParameter("writer");
			String passwd = request.getParameter("passwd");
			String content = request.getParameter("content");
			
			BoardCommentDTO commentDto = new BoardCommentDTO();
			
			commentDto.setBoard_no(no);
			commentDto.setWriter(writer);
			commentDto.setPasswd(passwd);
			commentDto.setContent(content);
			commentDto.setIp(ip);
			commentDto.setMemberNo(0);
			
			int result = dao.insertBoardComment(commentDto);
		} else if(url.indexOf("commentList.do") != -1) {
			temp = request.getParameter("commentPageNumber");
			int commentPageNumber = util.numberCheck(temp, 1);
			
			Map<String, Object> map = new HashMap<>();
			map.put("no", no);
			
			int pageSize = 5; //화면에 보여질 게시글의 갯수
			int blockSize = 10;
			int totalRecord = dao.getTotalRecordComment(map);
//			int totalRecord = dao.getTotalRecordComment();
			int[] pagerArray = util.pager(pageSize, blockSize, totalRecord, commentPageNumber);
			int jj = pagerArray[0];
			int startRecord = pagerArray[1];
			int lastRecord = pagerArray[2];
			int totalPage = pagerArray[3];
			int startPage = pagerArray[4];
			int lastPage = pagerArray[5];
			
//			ArrayList<BoardCommentDTO> list = dao.getCommentListAll(startRecord, lastRecord);
			
			List<BoardCommentDTO> list = dao.getCommentListAll(map, startRecord, lastRecord);
			request.setAttribute("list", list);
			request.setAttribute("menu_gubun", "board_CommentList");
			
			request.setAttribute("commentPageNumber", commentPageNumber);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("totalRecord", totalRecord);
			request.setAttribute("jj", jj);
			request.setAttribute("startRecord", startRecord);
			request.setAttribute("lastRecord", lastRecord);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("lastPage", lastPage);
			
			page = "/board/commentList.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
	}
}