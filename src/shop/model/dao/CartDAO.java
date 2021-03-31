package shop.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import shop.model.dto.CartDTO;
import shop.model.dto.ProductDTO;
import sqlmap.MybatisManager;

public class CartDAO {
	String table_1 = "cart";
	
	public CartDAO() {
		
	}
	
	public int insertCart(CartDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("table_1", table_1);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("cart.insertCart", map);
		session.commit();
		session.close();
		
		return result;
	}
	
	public int getTotalRecord(Map<String, Object> map) {
		map.put("table_1", table_1);
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("cart.getTotalRecord", map);
		session.close();
		
		return result;
	}
	
	public List<CartDTO> getListAll(Map<String, Object> map, int startRecord, int lastRecord) {
		map.put("table_1", table_1);
		map.put("startRecord", startRecord);
		map.put("lastRecord", lastRecord);
		SqlSession session = MybatisManager.getInstance().openSession();
		List<CartDTO> list = session.selectList("cart.getListAll", map);
		session.close();
		
		return list;
	}
	
	public ProductDTO getSelectOne(ProductDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("table_1", table_1);
		SqlSession session = MybatisManager.getInstance().openSession();
		dto = session.selectOne("cart.getSelectOne", map);
		session.close();
		
		return dto;
	}
	
	public int deleteCart(CartDTO dto) { // 체크한 갯수만큼 카트 제거
		Map<String, Object> map = new HashMap<>();
		String temp = dto.getChkNo();
		String[] arrayChkNo_ = temp.split(",");
		map.put("dto", dto);
		map.put("table_1", table_1);
		map.put("array", arrayChkNo_);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.delete("cart.deleteCart", map);
		session.commit();
		session.close();
		
		return result;
		
//		int result = 0;
//		
//		try {
//			String temp = dto.getChkNo();
//			String[] arrayChkNo_ = temp.split(",");
//			int[] arrayChkNo = new int[arrayChkNo_.length];
//			
//			temp = "";
//			String sql = "";
//			sql += "delete from " + tableName01 + " where no in(";
//			for(int i = 0; i < arrayChkNo_.length; i++) {
//				arrayChkNo[i] = Integer.parseInt(arrayChkNo_[i]);
//				temp += ",?"; 
//			}
//			temp = temp.substring(1);
//			sql += temp;
//			sql += ")";
//			
//			pstmt = conn.prepareStatement(sql);
//			for(int i = 0; i < arrayChkNo_.length; i++) {
//				pstmt.setInt(i + 1, arrayChkNo[i]);
//			}
//			
//			result = pstmt.executeUpdate();
//		} catch(Exception e) {
//			e.printStackTrace();
//		} finally {
//			getConnClose(rs, pstmt, conn);
//		}
//		
//		return result;
	}
	
	public int updateCartAmount(CartDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("table_1", table_1);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("cart.updateCartAmount", map);
		session.commit();
		session.close();
		
		return result;
	}
	
	public List<CartDTO> getListCartProductGroup() {
		Map<String, Object> map = new HashMap<>();
		map.put("table_1", table_1);
		SqlSession session = MybatisManager.getInstance().openSession();
		List<CartDTO> list = session.selectList("cart.getListCartProductGroup", map);
		session.close();
		
		return list;
	}
}