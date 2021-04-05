package surveyAnswer.controller;

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

import common.Util;
import survey.model.dao.SurveyDAO;
import survey.model.dto.SurveyDTO;
import surveyAnswer.model.dao.SurveyAnswerDAO;
import surveyAnswer.model.dto.SurveyAnswerDTO;

@WebServlet("/surveyAnswer_servlet/*")
public class SurveyAnswerController extends HttpServlet {
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
		
		Util util = new Util();
		
		SurveyAnswerDTO dto = new SurveyAnswerDTO();
		SurveyAnswerDAO dao = new SurveyAnswerDAO();
		
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
		
		temp = request.getParameter("list_gubun");
		String list_gubun = util.list_gubunCheck(temp);
		
		String search_option = request.getParameter("search_option");
		String search_data = request.getParameter("search_data");
		String search_date_s = request.getParameter("search_date_s");
		String search_date_e = request.getParameter("search_date_e");
		String search_date_check = request.getParameter("search_date_check");
		String[] searchArray = util.searchCheck(search_option, search_data, search_date_s, search_date_e, search_date_check);
		search_option = searchArray[0];
		search_data = searchArray[1];
		search_date_s = searchArray[2];
		search_date_e = searchArray[3];
		search_date_check = searchArray[4];
		
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
		
		PrintWriter out = response.getWriter();
		String page = "/main/main.jsp";
		
		if(url.indexOf("index.do") != -1) {
			request.setAttribute("menu_gubun", "surveyAnswer_index");
			
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if(url.indexOf("list.do") != -1) {
			HttpSession session = request.getSession();
//			int memberNo = 0;
//			if(session.getAttribute("cookNo") != null) {
//				memberNo = (Integer) session.getAttribute("cookNo");
//			}
//			dto.setMemberNo(memberNo);
			
			Map<String, Object> map = new HashMap<>();
			map.put("list_gubun", list_gubun);
			map.put("search_option", search_option);
			map.put("search_data", search_data);
			map.put("search_date_s", search_date_s);
			map.put("search_date_e", search_date_e);
			map.put("search_date_check", search_date_check);
			
			int pageSize = 10;
			int blockSize = 10;
			int totalRecord = dao.getTotalRecord(map);
//			int totalRecord = dao.getTotalRecord();
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
			
//			ArrayList<SurveyDTO> list = dao.getListAll(startRecord, lastRecord);
//			String resultHtml = dao.getListAllHTML();
			
			List<SurveyDTO> list = dao.getListAll(map, startRecord, lastRecord);
			request.setAttribute("list", list);
			request.setAttribute("menu_gubun", "surveyAnswer_list");
			
			request.setAttribute("list_gubun", list_gubun);
			request.setAttribute("search_option", search_option);
			request.setAttribute("search_data", search_data);
			request.setAttribute("search_date_s", search_date_s);
			request.setAttribute("search_date_e", search_date_e);
			request.setAttribute("search_date_check", search_date_check);
			
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
			
			page = "/surveyAnswer/list.jsp";
			
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if(url.indexOf("writeProc.do") != -1) {
			String answer_total = request.getParameter("answer_total");
			String[] answer_totalArray = answer_total.split("[|]");
			
			for(int i = 0; i < answer_totalArray.length; i++) {
				String[] imsiArr = answer_totalArray[i].split(":");
				int tempNo = Integer.parseInt(imsiArr[0]);
				int tempAnswer = Integer.parseInt(imsiArr[1]);
				
				SurveyAnswerDTO answerDto = new SurveyAnswerDTO();
				answerDto.setNo(tempNo);
				answerDto.setAnswer(tempAnswer);
				
				dao.insertSurveyAnswer(answerDto);
			}
		}
	}
}