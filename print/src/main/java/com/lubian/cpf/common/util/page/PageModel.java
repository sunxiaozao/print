package com.lubian.cpf.common.util.page;

import java.io.Serializable;
import java.util.List;

public class PageModel implements Serializable{   
  
	private static final long serialVersionUID = 1L;
	/**
	 * 总页数
	 */
	private int pageCount;
	/**  
     * 总记录数  
     */  
    private int total;   
    /**  
     * 当前页的记录集  
     */  
    @SuppressWarnings("unchecked")
	private List datas;   
  
    @SuppressWarnings("unchecked")
	public List getDatas() {   
        return datas;   
    }   
  
    public void setDatas(List<?> datas) {   
        this.datas = datas;   
    }   
  
    public int getTotal() {
        return total;   
    }
  
    public void setTotal(int total) {   
        this.total = total;   
        generatePageCount();
    }   
    
    private void generatePageCount(){
		int pageSize = SystemContext.getPagesize();		
		if (total < pageSize) {
			this.pageCount = 1;
		} else {
			if (total % pageSize == 0) {
				this.pageCount = total / pageSize;
			} else {
				this.pageCount = total / pageSize + 1;
			}
		}
    }

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
}  



