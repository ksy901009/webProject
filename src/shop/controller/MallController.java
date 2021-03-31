package shop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import shop.common.UtilMall;
import shop.common.UtilProduct;
import shop.model.dao.CartDAO;
import shop.model.dao.ProductDAO;
import shop.model.dto.CartDTO;
import shop.model.dto.ProductDTO;

@WebServlet("/mall_servlet/*")
public class MallController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MallController() {
    	
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}
	
	protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		UtilMall util = new UtilMall();
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
		
		ProductDAO productDao = new ProductDAO();
		ProductDTO productDto = new ProductDTO();
		
		CartDAO cartDao = new CartDAO();
		CartDTO cartDto = new CartDTO();
		
		PrintWriter out = response.getWriter();
		String page = "/main/main.jsp";
		
		if(url.indexOf("index.do") != -1) {
			request.setAttribute("menu_gubun", "mall_index");
			
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if(url.indexOf("cart_list.do") != -1) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("search_option", search_option);
			map.put("search_data", search_data);
			
			int pageSize = 12; //화면에 보여질 게시글의 갯수
			int blockSize = 10;
			int totalRecord = cartDao.getTotalRecord(map);
//			int totalRecord = dao.getTotalRecord();
			int[] pagerArray = util.pager(pageSize, blockSize, totalRecord, pageNumber);
			int jj = pagerArray[0];
			int startRecord = pagerArray[1];
			int lastRecord = pagerArray[2];
			int totalPage = pagerArray[3];
			int startPage = pagerArray[4];
			int lastPage = pagerArray[5];
			
//			ArrayList<BoardDTO> list = dao.getListAll(startRecord, lastRecord);
//			String resultHtml = dao.getListAllHTML();
			
			List<CartDTO> list = cartDao.getListAll(map, startRecord, lastRecord);
			request.setAttribute("list", list);
			request.setAttribute("menu_gubun", "product_list");
			
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
			
			page = "/shop/mall/cart_list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
//			out.println(resultHtml);
		} else if(url.indexOf("cart_deleteProc.do") != -1 || url.indexOf("cart_clear.do") != -1) {
			String chkNo = request.getParameter("chk_no"); //cart에서 선택한 cart 번호들(1,2,3,4,5,6)
			
			cartDto.setChkNo(chkNo);
			
			int result = 0;
			if(url.indexOf("cart_deleteProc.do") != -1) {
				request.setAttribute("menu_gubun", "cart_deleteProc");
			} else if(url.indexOf("cart_cleart.do") != -1) {
				request.setAttribute("menu_gubun", "cart_clear");
			}
			
			result = cartDao.deleteCart(cartDto);
			
			if(result > 0) {
//				System.out.println(result + "저장 하기 성공");
			} else {
//				System.out.println(result + "저장 하기 실패");
			}
		} else if(url.indexOf("mall_list.do") != -1) {
			Map<String, Object> map = new HashMap<>();
			map.put("search_option", search_option);
			map.put("search_data", search_data);
			
			int pageSize = 12; //화면에 보여질 게시글의 갯수
			int blockSize = 10;
			int totalRecord = productDao.getTotalRecord(map);
//			int totalRecord = dao.getTotalRecord();
			int[] pagerArray = util.pager(pageSize, blockSize, totalRecord, pageNumber);
			int jj = pagerArray[0];
			int startRecord = pagerArray[1];
			int lastRecord = pagerArray[2];
			int totalPage = pagerArray[3];
			int startPage = pagerArray[4];
			int lastPage = pagerArray[5];
			
//			ArrayList<BoardDTO> list = dao.getListAll(startRecord, lastRecord);
//			String resultHtml = dao.getListAllHTML();
			
			List<ProductDTO> list = productDao.getListAll(map, startRecord, lastRecord);
			request.setAttribute("list", list);
			request.setAttribute("menu_gubun", "product_list");
			
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
			
			page = "/shop/mall/mall_list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
//			out.println(resultHtml);
		} else if(url.indexOf("mall_view.do") != -1 || url.indexOf("mall_update.do") != -1 || url.indexOf("mall_delete.do") != -1) {
			productDto.setNo(no);
			productDto = productDao.getSelectOne(productDto);
			String gubunTemp = "";
			
			if(url.indexOf("mall_view.do") != -1) {
//				dao.setUpdateHit(dto);
				gubunTemp = "mall_view";
				page = "/shop/mall/mall_view.jsp";
			} else if(url.indexOf("mall_update.do") != -1) {
				gubunTemp = "mall_update";
				page = "/shop/mall/mall_update.jsp";
			} else if(url.indexOf("mall_delete.do") != -1) {
				gubunTemp = "mall_delete";
				page = "/shop/mall/mall_delete.jsp";
			}
			
			request.setAttribute("menu_gubun", gubunTemp);
			request.setAttribute("dto", productDto);
			
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if(url.indexOf("cart_writeProc.do") != -1 || url.indexOf("mall_updateProc.do") != -1 || url.indexOf("mall_deleteProc.do") != -1) {
			temp = request.getParameter("jumun_su");
			int jumun_su = util.numberCheck(temp, 0);
			
			cartDto.setMemberNo(cookNo);
			cartDto.setProductNo(no);
			cartDto.setAmount(jumun_su);
			
			int result = 0;
			if(url.indexOf("cart_writeProc.do") != -1) {
				request.setAttribute("menu_gubun", "cart_writeProc");
				result = cartDao.insertCart(cartDto);
			}
			
			if(result > 0) {
//				System.out.println(result + "저장 하기 성공");
			} else {
//				System.out.println(result + "저장 하기 실패");
			}
		} else if(url.indexOf("changeAmount.do") != -1) {
			temp = request.getParameter("amount");
			int amount = util.numberCheck(temp, 0);
			
			cartDto.setNo(no);
			cartDto.setAmount(amount);
			
			int result = 0;
			request.setAttribute("menu_gubun", "cart_updateCartAmount");
			result = cartDao.updateCartAmount(cartDto);
		}
	}
}