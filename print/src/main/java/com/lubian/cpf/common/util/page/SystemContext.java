package com.lubian.cpf.common.util.page;


public class SystemContext {
	//用于query.setFirstResult(offset)
	private static ThreadLocal<Integer> offset = 
		new ThreadLocal<Integer>();
	//pagesize每页显示的行数用于query.setMaxResults(pagesize);
	private static ThreadLocal<Integer> pagesize = 
		new ThreadLocal<Integer>();

	
	//ThreadLocal线程局部变量,就是为每一个使用该变量的线程都提供一个变量值的副本，
	//每一个线程都可以独立地改变自己的副本，
	//而不会和其它线程的副本冲突,也就是每一个用户都可以改变自己
	//的每页显示的行数而不会影响到其他人

	private SystemContext() {
		
	}
	
	public static void setOffset(int offsetvalue) {
		offset.set(offsetvalue);
	}

	public static int getOffset() {
		Integer ov = offset.get();
		if (ov == null) {
			return 0;
		}
		return ov;
	}

	public static void setPagesize(int pagesizevalue) {
		pagesize.set(pagesizevalue);
	}

	public static int getPagesize() {
		Integer ps = pagesize.get();
		if (ps == null) {
			return Integer.MAX_VALUE;//将pagesize设置为无穷大，也就是显示在一页上
		}
		return ps;
	}

	public static void removeOffset() {//清除为每个用户分配的副本
		offset.remove();
	}

	public static void removePagesize() {
		pagesize.remove();
	}

}
