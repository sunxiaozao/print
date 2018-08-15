package com.lubian.cpf.service.cpf;

import java.util.List;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.vo.CdfFavorite;

/**
 * CdfFavoriteService
 * @author:Lusir<lusire@gmail.com>
 */
public interface CdfFavoriteService {
	public CdfFavorite findByPK( CdfFavorite vo );
	public PageModel freeFind( CdfFavorite vo );	
	public void insert( CdfFavorite vo );
	public void update( CdfFavorite vo );
	public void delete( CdfFavorite vo );
	
	
	public boolean count(Integer bookmarkId);
	
	/**
	 * 查询收藏记录
	 * @param vo
	 * @return list
	 */
	public List<CdfFavorite> listFreeFind( CdfFavorite vo );
	/**
	 * 该医生收藏记录的id 集合
	 * @param doctorId
	 * @return
	 */
	public String idsByDocotrId(Integer doctorId);
	/**
	 * 该医生收藏记录的caseId 集合
	 * @param doctorId
	 * @return
	 */
	public String caseIdByDocotrId(Integer doctorId);
	public String caseIdByDocotrId(Integer doctorId,Integer bookmarkId);
	/**
	 * 收藏删除
	 * @param vo
	 */
	public void deleteCollect( CdfFavorite vo );
}
