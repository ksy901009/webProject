package member.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import member.model.dto.MemberDTO;
import sqlmap.MybatisManager;

public class MemberDAO {
	String table_1 = "member";
	
	public MemberDAO() {
		
	}

	public int insertMember(MemberDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("table_1", table_1);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("member.insertMember", map);
		session.commit();
		session.close();
		
		return result;
	}
	
	public List<MemberDTO> getListAll(Map<String, Object> map, int startRecord, int lastRecord) {
		map.put("table_1", table_1);
		map.put("startRecord", startRecord);
		map.put("lastRecord", lastRecord);
		SqlSession session = MybatisManager.getInstance().openSession();
		List<MemberDTO> list = session.selectList("member.getListAll", map);
		session.close();
		
		return list;
	}
	
	public MemberDTO getSelectOne(Map<String, Object> map, MemberDTO dto) { // 이전글, 다음글 확인용 view
		map.put("dto", dto);
		map.put("table_1", table_1);
		SqlSession session = MybatisManager.getInstance().openSession();
		dto = session.selectOne("member.getSelectOneSearch", map);
		session.close();
		
		return dto;
	}
	
	public MemberDTO getSelectOne(MemberDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("table_1", table_1);
		SqlSession session = MybatisManager.getInstance().openSession();
		dto = session.selectOne("member.getSelectOne", map);
		session.close();
		
		return dto;
	}
	
	public int updateMember(MemberDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("table_1", table_1);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("member.updateMember", map);
		session.commit();
		session.close();
		
		return result;
	}
	
	public String deleteMember(MemberDTO dto) {
		String result = "";
		int deleteResult = 0;
		
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("table_1", table_1);
		String dbPw = pwdCheck(dto);
		
		if(dto.getPasswd().equals(dbPw)) {
			SqlSession session = MybatisManager.getInstance().openSession();
			deleteResult = session.delete("member.deleteMember", map);
			
			if(deleteResult > 0) {
				result = "success";
				
				session.commit();
				session.close();
			} else {
				result = "DBFail";
			}
		} else {
			result = "passwdFail";
		}
		
		return result;
	}
	
	public int idCheck(MemberDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("table_1", table_1);
		map.put("id", dto.getId());
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("member.idCheck", map);
		session.close();
		
		return result;
	}
	
	public String idCheckResultString(MemberDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("table_1", table_1);
		map.put("id", dto.getId());
		SqlSession session = MybatisManager.getInstance().openSession();
		String result = session.selectOne("member.idCheckResultString", map);
		session.close();
		
		return result;
	}
	
	public String pwdCheck(MemberDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("table_1", table_1);
		map.put("dto", dto);
		SqlSession session = MybatisManager.getInstance().openSession();
		String result = session.selectOne("member.pwdCheck", map);
		session.close();
		
		return result;
	}
	
	public MemberDTO login(MemberDTO inputDto) {
		MemberDTO dto = new MemberDTO();
		Map<String, Object> map = new HashMap<>();
		map.put("dto", inputDto);
		map.put("table_1", table_1);
		SqlSession session = MybatisManager.getInstance().openSession();
		dto = session.selectOne("member.login", map);
		
		if(dto != null) {
			String dbPw = dto.getPasswd();
			
			if(!inputDto.getPasswd().equals(dbPw)) { // 비번틀림
				dto = null;
			} else { // 로그인성공
				
			}
		} else { // DB오류
			dto = null;
		}
		session.close();
		
		return dto;
	}
	
	public int getTotalRecord(Map<String, Object> map) {
		map.put("table_1", table_1);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("member.getTotalRecord", map);
		session.close();
		
		return result;
	}
}