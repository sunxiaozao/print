package com.lubian.cpf.common;

import java.util.List;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class BaseSqlMapDao{

	protected static final int PAGE_SIZE = 4;
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List getPaginatedList(String str, Object paraObj, int pageIndex, int pageSize){
		int ignore = pageIndex;//(pageIndex - 1) * pageSize;
		if(ignore < 0)ignore = 0;
		if(pageSize <=0) pageSize = PAGE_SIZE;
		return sqlSessionTemplate.selectList(str, paraObj, new RowBounds(ignore,pageSize));
	}
  
	public Object queryForObject(String str, Object paraObj){
		return sqlSessionTemplate.selectOne(str, paraObj);
	}
	
	public List queryForList(String str, Object paraObj){
		return sqlSessionTemplate.selectList(str, paraObj);
	}
	
	public void insert(String str, Object paraObj){
		sqlSessionTemplate.insert(str, paraObj);
	}
	
	public int update(String str, Object paraObj){
		return sqlSessionTemplate.update(str, paraObj);
	}
	
	public int delete(String str, Object paraObj){
		return sqlSessionTemplate.delete(str, paraObj);
	}

}
