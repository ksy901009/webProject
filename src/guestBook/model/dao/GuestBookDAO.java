package guestBook.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;

import guestBook.model.dto.GuestBookDTO;
import sqlmap.MybatisManager;

public class GuestBookDAO {
	String table_1 = "guestBook";
	
	public GuestBookDAO() {
		
	}
	
	public int insertGuestBook(GuestBookDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("table_1", table_1);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("guestBook.insertGuestBook", map);
		session.commit();
		session.close();
		
		return result;
	}
	
	public int getTotalRecord(Map<String, Object> map) {
		map.put("table_1", table_1);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("guestBook.getTotalRecord", map);
		session.close();
		
		return result;
	}
	
	public List<GuestBookDTO> getListAll(Map<String, Object> map, int startRecord, int lastRecord) {
		map.put("table_1", table_1);
		map.put("startRecord", startRecord);
		map.put("lastRecord", lastRecord);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		List<GuestBookDTO> list = session.selectList("guestBook.getListAll", map);
		session.close();
		
		return list;
	}
	
	public GuestBookDTO getSelectOne(GuestBookDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("table_1", table_1);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		dto = session.selectOne("guestBook.getSelectOne", map);
		session.close();
		
		return dto;
	}
	
	public int updateGuestBook(GuestBookDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("table_1", table_1);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("guestBook.updateGuestBook", map);
		session.commit();
		session.close();
		
		return result;
	}
	
	public String deleteGuestBook(GuestBookDTO dto) {
		String result = "";
		int deleteResult = 0;
		
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("table_1", table_1);
		SqlSession session = MybatisManager.getInstance().openSession();
		deleteResult = session.delete("guestBook.deleteGuestBook", map);
		
		if(deleteResult > 0) {
			result = "success";
			
			session.commit();
			session.close();
		} else {
			result = "fail";
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getDbPwd(GuestBookDTO dto) {
		JSONObject obj = new JSONObject();
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("table_1", table_1);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		String result = session.selectOne("guestBook.getDbPwd", map);
		session.close();
		
		if(!result.equals("") && result != null) {
			obj.put("passwd", result);
		} else {
			obj.put("passwd", "notMember");
		}
		
		return obj;
	}
}