package surveyAnswer.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import sqlmap.MybatisManager;
import survey.model.dto.SurveyDTO;
import surveyAnswer.model.dto.SurveyAnswerDTO;

public class SurveyAnswerDAO {
	String table_1 = "survey";
	String table_2 = "survey_answer";
	
	public SurveyAnswerDAO() {
		
	}
	
	public int getTotalRecord(Map<String, Object> map) {
		map.put("table_1", table_1);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("surveyAnswer.getTotalRecord", map);
		session.close();
		
		return result;
	}
	
	public List<SurveyDTO> getListAll(Map<String, Object> map, int startRecord, int lastRecord) {
		map.put("table_1", table_1);
		map.put("startRecord", startRecord);
		map.put("lastRecord", lastRecord);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		List<SurveyDTO> list = session.selectList("surveyAnswer.getListAll", map);
		session.close();
		
		return list;
	}
	
	public int insertSurveyAnswer(SurveyAnswerDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("table_2", table_2);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("surveyAnswer.insertSurveyAnswer", map);
		session.commit();
		session.close();
		
		return result;
	}
}