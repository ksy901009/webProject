package survey.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import sqlmap.MybatisManager;
import survey.model.dto.SurveyDTO;
import surveyAnswer.model.dto.SurveyAnswerDTO;

public class SurveyDAO {
	String table_1 = "survey";
	String table_2 = "survey_answer";
	
	public SurveyDAO() {
		
	}
	
	public int insertSurvey(SurveyDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("table_1", table_1);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("survey.insertSurvey", map);
		session.commit();
		session.close();
		
		return result;
	}
	
	public int getTotalRecord(Map<String, Object> map) {
		map.put("table_1", table_1);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("survey.getTotalRecord", map);
		session.close();
		
		return result;
	}
	
	public List<SurveyDTO> getListAll(Map<String, Object> map, int startRecord, int lastRecord) {
		map.put("table_1", table_1);
		map.put("table_2", table_2);
		map.put("startRecord", startRecord);
		map.put("lastRecord", lastRecord);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		List<SurveyDTO> list = session.selectList("survey.getListAll", map);
		session.close();
		
		return list;
	}
	
	public SurveyDTO getSelectOne(SurveyDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("table_1", table_1);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		dto = session.selectOne("survey.getSelectOne", map);
		session.close();
		
		return dto;
	}
	
	public int updateSurvey(SurveyDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("table_1", table_1);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("survey.updateSurvey", map);
		session.commit();
		session.close();
		
		return result;
	}
	
	public int deleteSurvey(SurveyDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("table_1", table_1);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.delete("survey.deleteSurvey", map);
		session.commit();
		session.close();
		
		return result;
	}
	
	
	public int insertSurveyAnswer(SurveyAnswerDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("table_2", table_2);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("survey.insertSurveyAnswer", map);
		session.commit();
		session.close();
		
		return result;
	}
}