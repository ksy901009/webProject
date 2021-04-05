package board.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import comment.model.dto.BoardCommentDTO;
import board.model.dto.BoardDTO;
import sqlmap.MybatisManager;

public class BoardDAO {
	String table_1 = "board";
	String table_2 = "board_comment";
	
	public BoardDAO() {
		
	}
	
	public int getMaxNum() {
		Map<String, Object> map = new HashMap<>();
		map.put("table_1", table_1);
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("board.getMaxNum", map);
		session.close();
		
		return result;
	}
	
	public int getMaxRefNo() {
		Map<String, Object> map = new HashMap<>();
		map.put("table_1", table_1);
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("board.getMaxRefNo", map);
		session.close();
		
		return result;
	}
	
	public int getMaxNoticeNo(String tbl) {
		Map<String, Object> map = new HashMap<>();
		map.put("table_1", table_1);
		map.put("tbl", tbl);
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("board.getMaxNoticeNo", map);
		session.close();
		
		return result;
	}
	
	public int insertBoard(BoardDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("table_1", table_1);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("board.insertBoard", map);
		session.commit();
		session.close();
		
		return result;
	}
	
	public int getTotalRecord(Map<String, Object> map) {
		map.put("table_1", table_1);
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("board.getTotalRecord", map);
		session.close();
		
		return result;
	}
	
	public List<BoardDTO> getListAll(Map<String, Object> map, int startRecord, int lastRecord) {
		map.put("table_1", table_1);
		map.put("startRecord", startRecord);
		map.put("lastRecord", lastRecord);
		SqlSession session = MybatisManager.getInstance().openSession();
		List<BoardDTO> list = session.selectList("board.getListAll", map);
		session.close();
		
		return list;
	}
	
	public BoardDTO getSelectOne(BoardDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("table_1", table_1);
		SqlSession session = MybatisManager.getInstance().openSession();
		dto = session.selectOne("board.getSelectOne", map);
		session.close();
		
		return dto;
	}
	
	public void setUpdateHit(BoardDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("table_1", table_1);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("board.setUpdateHit", map);
		session.commit();
		session.close();
	}
	
	public void setUpdateReLevel(BoardDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("table_1", table_1);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("board.setUpdateReLevel", map);
		session.commit();
		session.close();
	}
	
	public int updateBoard(BoardDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("table_1", table_1);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("board.updateBoard", map);
		session.commit();
		session.close();
		
		return result;
	}
	
	public String deleteBoard(BoardDTO dto) {
		String result = "";
		int deleteResult = 0;
		
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("table_1", table_1);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		deleteResult = session.delete("board.deleteBoard", map);
		
		if(deleteResult > 0) {
			result = "success";
			
			session.commit();
			session.close();
		} else {
			result = "fail";
		}
		
		return result;
	}
	
	public int insertBoardComment(BoardCommentDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("table_2", table_2);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("board.insertBoardComment", map);
		session.commit();
		session.close();
		
		return result;
	}
	
	public int getTotalRecordComment(Map<String, Object> map) {
		map.put("table_2", table_2);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("board.getTotalRecordComment", map);
		session.close();
		
		return result;
	}
	
	public List<BoardCommentDTO> getCommentListAll(Map<String, Object> map, int startRecord, int lastRecord) {
		map.put("table_2", table_2);
		map.put("startRecord", startRecord);
		map.put("lastRecord", lastRecord);
		SqlSession session = MybatisManager.getInstance().openSession();
		List<BoardCommentDTO> list = session.selectList("board.getCommentListAll", map);
		session.close();
		
		return list;
	}
	
//	public ArrayList<int[]> getSurveyAnswerListAll() {
//		ArrayList<int[]> list = new ArrayList<>();
//		
//		try {
//			String sql = "select survey_no, (select count(survey_answer) from survey_answer where survey_answer = 1 and survey_no = sa.survey_no) a,"
//					+ "(select count(survey_answer) from survey_answer where survey_answer = 2 and survey_no = sa.survey_no) b,"
//					+ "(select count(survey_answer) from survey_answer where survey_answer = 3 and survey_no = sa.survey_no) c,"
//					+ "(select count(survey_answer) from survey_answer where survey_answer = 4 and survey_no = sa.survey_no) d,"
//					+ "(select count(survey_answer) from survey_answer where survey_answer = 1 and survey_no = sa.survey_no) / count(sa.survey_answer) * 100   a1,"
//					+ "(select count(survey_answer) from survey_answer where survey_answer = 2 and survey_no = sa.survey_no) / count(sa.survey_answer) * 100   b1,"
//					+ "(select count(survey_answer) from survey_answer where survey_answer = 3 and survey_no = sa.survey_no) / count(sa.survey_answer) * 100   c1,"
//					+ "(select count(survey_answer) from survey_answer where survey_answer = 4 and survey_no = sa.survey_no) / count(sa.survey_answer) * 100   d1 "
//					+ "from survey_answer sa group by survey_no";
//			pstmt = conn.prepareStatement(sql);
//			
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
////				SurveyAnswerDTO dto = new SurveyAnswerDTO();
////				dto.setNo(rs.getInt("no"));
////				dto.setSurvey_no(rs.getInt("survey_no"));
////				dto.setSurvey_answer(rs.getInt("survey_answer"));
//				int[] arrayInt = new int[9];
//				
//				arrayInt[0] = rs.getInt("survey_no");
//				arrayInt[1] = rs.getInt("a");
//				arrayInt[2] = rs.getInt("b");
//				arrayInt[3] = rs.getInt("c");
//				arrayInt[4] = rs.getInt("d");
//				
//				arrayInt[5] = rs.getInt("a1");
//				arrayInt[6] = rs.getInt("b1");
//				arrayInt[7] = rs.getInt("c1");
//				arrayInt[8] = rs.getInt("d1");
//				
//				list.add(arrayInt);
//			}
//		} catch(Exception e) {
//			e.printStackTrace();
//		} finally {
//			getConnClose(rs, pstmt, conn);
//		}
//		
//		return list;
//	}
//	
//	
//	public int deleteSurvey(GuestBookDTO dto) {
//		int result = 0;
//		
//		try {
//			String sql = "delete from guestBook where no = ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, dto.getNo());
//			
//			result = pstmt.executeUpdate();
//		} catch(Exception e) {
//			e.printStackTrace();
//		} finally {
//			getConnClose(rs, pstmt, conn);
//		}
//		
//		return result;
//	}
}