package shop.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import shop.common.UtilProduct;
import shop.model.dao.ProductDAO;
import shop.model.dto.ProductDTO;

@WebServlet("/product_servlet/*")
public class ProductController extends HttpServlet {
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
		
		UtilProduct util = new UtilProduct();
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
		
		ProductDAO dao = new ProductDAO();
		ProductDTO dto = new ProductDTO();
		
		PrintWriter out = response.getWriter();
		String page = "/main/main.jsp";
		
		if(url.indexOf("index.do") != -1) {
			request.setAttribute("menu_gubun", "product_index");
			
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if(url.indexOf("write.do") != -1 || url.indexOf("reply.do") != -1) {
			request.setAttribute("menu_gubun", "product_write");
			
			page = "/shop/product/write.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if(url.indexOf("writeProc.do") != -1 || url.indexOf("updateProc.do") != -1 || url.indexOf("deleteProc.do") != -1) {
			String img_path01 = request.getSession().getServletContext().getRealPath("/attach/product_img/"); //실제경로
			java.io.File isDir = new java.io.File(img_path01);
			if(!isDir.isDirectory()) {
//				System.out.println("디레토리가 존재하지 않습니다. 디렉토리를 생성합니다.");
				isDir.mkdir();
			}
			
			String img_path02 = img_path01.replace("\\", "/");
			String img_path03 = img_path01.replace("\\", "\\\\");
			int max_size = 10 * 1024 * 1024; //10MB
			
//			System.out.println("img_path03 >>> " + img_path03);
			
			MultipartRequest multi = new MultipartRequest(request, img_path03, max_size, "utf-8", new DefaultFileRenamePolicy());
			//이 순간에 파일이 저장됨
			
			Enumeration files = multi.getFileNames();
			int arrayCounter = 3;
			String[] array = new String[arrayCounter];
			
			for(int i =0; i < array.length; i++){
				array[i] = "-";
			}
			
			String[] arrayFileType = new String[arrayCounter];
			while(files.hasMoreElements()) { 								//다음요소가 있으면
				String formName = (String) files.nextElement();				//폼의 이름
				String fileName = multi.getFilesystemName(formName);		//파일 이름
				String fileType = multi.getContentType(formName);			//파일 타입
				
//				System.out.println("fileType >>> " + fileType);
				if(fileName == null || fileName.trim().equals("")) {
					fileName = "-";
				}
				
//				String fileOrgName = multi.getOriginalFileName(formName);
//				String fileType = multi.getContentType(formName);
//				System.out.println(formName+"/"+fileName);
//				System.out.println(fileOrgName+"/"+fileType);
				
				int k = Integer.parseInt(formName);
				array[k] = fileName;
				arrayFileType[k] = fileType;
			}
			
			for(int i = 0; i < array.length; i++) {
				temp = array[i];
				if(temp.equals("")) {
					continue;
				}
				String old_path = img_path03 + temp; //원본이 업로드된 절대경로와 파일명을 구한다.
//				System.out.println("old_path :"+old_path);
				java.io.File f1 = new java.io.File(old_path);
				if(!f1.exists()) {
					array[i] = "-";
					continue;
				}
				
				int point_index = temp.lastIndexOf(".");
				if(point_index == -1) {
					f1.delete();
					array[i] = "-";
					continue;
				}
				String fileType = arrayFileType[i].substring(6).toLowerCase(); // image/* 이미지/ 다음글자 (확장자) 가져오는 변수
				
//				System.out.println("for > fileType >>> " + fileType);
				if(!(fileType.equals("jpg") || fileType.equals("jpeg") || fileType.equals("gif") || fileType.equals("png"))) {
					f1.delete();
					array[i] = "-";
					continue;
				}
				
				String uuid = util.create_uuid();
				String newFileName = util.getDateTimeType() + "_" + uuid + "." + fileType;
				java.io.File newFile = new java.io.File(img_path03 + newFileName);
				f1.renameTo(newFile); //파일이동
				array[i] = array[i] + "|" + newFileName;
			}
			
			String str = "";
			for(int i = 0 ; i < array.length; i++) {
				str += "," + array[i];
			}
			str = str.substring(1);
//			System.out.println("str : " + str);
			
			temp = multi.getParameter("no");
			no = util.numberCheck(temp, 0);
			String name = multi.getParameter("name");
			temp = multi.getParameter("price");
			int price = util.numberCheck(temp, 0);
			String description = multi.getParameter("description");
			
			dto.setNo(no);
			dto.setName(name);
			dto.setPrice(price);
			dto.setDescription(description);
			int result = 0;
			if(url.indexOf("writeProc.do") != -1) {
				request.setAttribute("menu_gubun", "product_writeProc");
				dto.setProduct_img(str);
				result = dao.insertProduct(dto);
			} else if(url.indexOf("deleteProc.do") != -1) {
				request.setAttribute("menu_gubun", "product_deleteProc");
				ProductDTO dto2 = dao.getSelectOne(dto);
				String db_product_img = dto2.getProduct_img();
				dto.setNo(no);
				
				String deleteFileName = "";
				String[] dbArray = db_product_img.split(",");
				
				for(int i = 0; i < array.length; i++) {
					if(!dbArray[i].equals("-")) {
						deleteFileName += "," + dbArray[i].substring(dbArray[i].lastIndexOf("|") + 1);
					}
				}
				deleteFileName = deleteFileName.substring(1);
				
				result = dao.deleteProduct(dto);
				
				String[] arrayDelete = deleteFileName.split(",");
				for(int i = 0; i < arrayDelete.length; i++) {
					if(!arrayDelete[i].trim().equals("-")) {
						java.io.File f1 = new java.io.File(img_path03 + arrayDelete[i]);
						f1.delete();
					}
				}
			} else { // 수정
				request.setAttribute("menu_gubun", "product_updateProc");
				ProductDTO dto2 = dao.getSelectOne(dto);
				String db_product_img = dto2.getProduct_img();
				dto.setNo(no);
				dto.setName(name);
				dto.setPrice(price);
				dto.setDescription(description);
				
				String deleteFileName = "";
				if(str.trim().equals("-,-,-")) { //첨부파일이 없을 경우
					dto.setProduct_img(db_product_img);
				} else { //첨부파일이 있을 경우, 순서 고민, 반복문
					temp = "";
					String[] dbArray = db_product_img.split(",");
					
					for(int i = 0; i < array.length; i++) {
						if(array[i].equals("-")) {
							temp += "," + dbArray[i];
						} else {
							temp += "," + array[i];
							deleteFileName += "," + dbArray[i].substring(dbArray[i].lastIndexOf("|") + 1);
						}
					}
					deleteFileName = deleteFileName.substring(1);
					temp = temp.substring(1);
					dto.setProduct_img(temp);
				}
				
				result = dao.updateProduct(dto);
				
				String[] arrayDelete = deleteFileName.split(",");
				for(int i = 0; i < arrayDelete.length; i++) {
					if(!arrayDelete[i].trim().equals("-")) {
						java.io.File f1 = new java.io.File(img_path03 + arrayDelete[i]);
						f1.delete();
					}
				}
			}
			
			if(result > 0) {
//				System.out.println(result + "저장 하기 성공");
			} else {
//				System.out.println(result + "저장 하기 실패");
			}
		} else if(url.indexOf("list.do") != -1) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("search_option", search_option);
			map.put("search_data", search_data);
			
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
//			String resultHtml = dao.getListAllHTML();
			
			List<ProductDTO> list = dao.getListAll(map, startRecord, lastRecord);
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
			
			page = "/shop/product/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if(url.indexOf("view.do") != -1 || url.indexOf("update.do") != -1 || url.indexOf("delete.do") != -1) {
			dto.setNo(no);
			dto = dao.getSelectOne(dto);
			String gubunTemp = "";
			
			if(url.indexOf("view.do") != -1) {
				gubunTemp = "product_view";
				page = "/shop/product/view.jsp";
			} else if(url.indexOf("update.do") != -1) {
				gubunTemp = "product_update";
				page = "/shop/product/update.jsp";
			} else if(url.indexOf("delete.do") != -1) {
				gubunTemp = "product_delete";
				page = "/shop/product/delete.jsp";
			}
			
			request.setAttribute("menu_gubun", gubunTemp);
			request.setAttribute("dto", dto);
			
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} 
//		else if(url.indexOf("commentProc.do") != -1) {
//			String writer = request.getParameter("writer");
//			String passwd = request.getParameter("passwd");
//			String content = request.getParameter("content");
//			
//			BoardCommentDTO commentDto = new BoardCommentDTO();
//			
//			commentDto.setBoard_no(no);
//			commentDto.setWriter(writer);
//			commentDto.setPasswd(passwd);
//			commentDto.setContent(content);
//			commentDto.setIp(ip);
//			commentDto.setMemberNo(0);
//			
//			int result = dao.insertBoardComment(commentDto);
//		} else if(url.indexOf("commentList.do") != -1) {
//			temp = request.getParameter("commentPageNumber");
//			int commentPageNumber = util.numberCheck(temp, 1);
//			
//			HashMap<String, Object> map = new HashMap<>();
//			map.put("no", no);
//			
//			int pageSize = 5; //화면에 보여질 게시글의 갯수
//			int blockSize = 10;
//			int totalRecord = dao.getTotalRecordComment(map);
////			int totalRecord = dao.getTotalRecordComment();
//			int[] pagerArray = util.pager(pageSize, blockSize, totalRecord, commentPageNumber);
//			int jj = pagerArray[0];
//			int startRecord = pagerArray[1];
//			int lastRecord = pagerArray[2];
//			int totalPage = pagerArray[3];
//			int startPage = pagerArray[4];
//			int lastPage = pagerArray[5];
//			
////			ArrayList<BoardCommentDTO> list = dao.getCommentListAll(startRecord, lastRecord);
////			String resultHtml = dao.getListAllHTML();
//			
//			ArrayList<BoardCommentDTO> list = dao.getCommentListAll(map, startRecord, lastRecord);
//			request.setAttribute("list", list);
//			request.setAttribute("menu_gubun", "board_CommentList");
//			
//			request.setAttribute("commentPageNumber", commentPageNumber);
//			request.setAttribute("pageSize", pageSize);
//			request.setAttribute("blockSize", blockSize);
//			request.setAttribute("totalRecord", totalRecord);
//			request.setAttribute("jj", jj);
//			request.setAttribute("startRecord", startRecord);
//			request.setAttribute("lastRecord", lastRecord);
//			request.setAttribute("totalPage", totalPage);
//			request.setAttribute("startPage", startPage);
//			request.setAttribute("lastPage", lastPage);
//			
//			page = "/board/commentList.jsp";
//			RequestDispatcher rd = request.getRequestDispatcher(page);
//			rd.forward(request, response);
////			out.println(resultHtml);
//		}
	}
}