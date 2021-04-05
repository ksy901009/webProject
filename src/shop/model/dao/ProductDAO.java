package shop.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import shop.model.dto.ProductDTO;
import sqlmap.MybatisManager;

public class ProductDAO {
	String table_1 = "product";
	
	public ProductDAO() {
		
	}
	
	public int insertProduct(ProductDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("table_1", table_1);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("product.insertProduct", map);
		session.commit();
		session.close();
		
		return result;
	}
	
	public int getTotalRecord(Map<String, Object> map) {
		map.put("table_1", table_1);
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("product.getTotalRecord", map);
		session.close();
		
		return result;
	}
	
	public List<ProductDTO> getListAll(Map<String, Object> map, int startRecord, int lastRecord) {
		map.put("table_1", table_1);
		map.put("startRecord", startRecord);
		map.put("lastRecord", lastRecord);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		List<ProductDTO> list = session.selectList("product.getListAll", map);
		session.close();
		
		return list;
	}
	
	public ProductDTO getSelectOne(ProductDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("table_1", table_1);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		dto = session.selectOne("product.getSelectOne", map);
		session.close();
		
		return dto;
	}
	
	public int updateProduct(ProductDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("table_1", table_1);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("product.updateProduct", map);
		session.commit();
		session.close();
		
		return result;
	}
	
	public int deleteProduct(ProductDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("table_1", table_1);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.delete("product.deleteProduct", map);
		session.commit();
		session.close();
		
		return result;
	}
}