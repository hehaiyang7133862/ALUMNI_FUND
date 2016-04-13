package com.laungee.proj.common.util;

import java.util.HashSet;
import java.util.Set;

public class PaginationDto {
	// ---必须设置项
	/**
	 * 设置DTO
	 */
	private String name = "p";
	/**
	 * 序号
	 */
	private String s="";
	/**
	 * 设置排序字段标识符
	 */
	private String sst = "st";
	/**
	 * 设置排序类型标识符
	 */
	private String sor = "order";
	/**
	 * 当前条目标识符
	 */
	private String ssize = "size";
	/**
	 * 当前页数标识符
	 */
	private String scurr = "curr";
	/**
	 * 显示条目1标识符
	 */
	private String sitem1 = "item1";
	/**
	 * 显示条目2标识符
	 */
	private String sitem2 = "item2";
	/**
	 * 显示条目3标识符
	 */
	private String sitem3 = "item3";
	/**
	 * 移除
	 */
	private Set remove;
	/* 分页参数 */
	private int curr = 1;
	private int size = 10;
	private int item1 = 10;
	private int item2 = 25;
	private int item3 = 50;
	/* 需计算数据 */
	private int count = 0;
	private int total = 0;
	/* 页面参数集 */
	private String part;
	private String pars;
	/* 排序字段 */
	private String st;
	private String or;
	
	// 构建方法
	public PaginationDto(){
	}
	public PaginationDto(String n){
		s=n;
	}
	
	// 集合信息
	public Set getRemove() {
		if (null == remove) {
			remove = new HashSet();
			remove.add(getScurr());
			remove.add(getSsize());
			remove.add(getSitem1());
			remove.add(getSitem2());
			remove.add(getSitem3());
			remove.add(getSst());
			remove.add(getSor());
		}
		return remove;
	}
	public void setRemove(Set remove) {
		this.remove = remove;
	}
	
	// 只有GET方法
	public String getSst() {
		return sst+s;
	}
	public String getSor() {
		return sor+s;
	}
	public String getSsize() {
		return ssize+s;
	}
	public String getScurr() {
		return scurr+s;
	}
	public String getSitem1() {
		return sitem1+s;
	}

	public String getSitem2() {
		return sitem2+s;
	}

	public String getSitem3() {
		return sitem3+s;
	}
	
	// SET和GET方法
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCurr() {
		return curr;
	}
	public void setCurr(int curr) {
		this.curr = curr;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getItem1() {
		return item1;
	}
	public void setItem1(int item1) {
		this.item1 = item1;
	}
	public int getItem2() {
		return item2;
	}
	public void setItem2(int item2) {
		this.item2 = item2;
	}
	public int getItem3() {
		return item3;
	}
	public void setItem3(int item3) {
		this.item3 = item3;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public String getPars() {
		return pars;
	}
	public void setPars(String pars) {
		this.pars = pars;
	}
	public String getSt() {
		return st;
	}
	public void setSt(String st) {
		this.st = st;
	}
	public String getOr() {
		return or;
	}
	public void setOr(String or) {
		this.or = or;
	}
}
