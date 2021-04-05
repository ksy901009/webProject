package memo.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import memo.model.dto.MemoDTO;
import sqlmap.MybatisManager;

public class MemoDAO {
	String table_1 = "memo";
	
	public MemoDAO() {
		
	}

	public int insertMemo(MemoDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("table_1", table_1);
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("memo.insertMemo", map);
		session.commit();
		session.close();
		
		return result;
	}
	
	public List<MemoDTO> getListAll(MemoDTO dto, int startRecord, int lastRecord) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("table_1", table_1);
		map.put("startRecord", startRecord);
		map.put("lastRecord", lastRecord);
		SqlSession session = MybatisManager.getInstance().openSession();
		List<MemoDTO> list = session.selectList("memo.getListAll", map);
		session.close();
		
		return list;
	}
	
	public int getTotalRecord(MemoDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("table_1", table_1);
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("memo.getTotalRecord", map);
		session.close();
		
		return result;
	}
	
	public int updateMemo(MemoDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("table_1", table_1);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("memo.updateMemo", map);
		session.commit();
		session.close();
		
		return result;
	}
	
	public int deleteMemo(MemoDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("table_1", table_1);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.delete("memo.deleteMemo", map);
		session.commit();
		session.close();
		
		return result;
	}
}