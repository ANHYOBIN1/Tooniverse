package com.tooniverse;

public class ReplyBean {

	private int no;
	private String content;
	private int ref;
	private String name;
	private String redate;
	private int re_like;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRedate() {
		return redate;
	}
	public void setRedate(String redate) {
		this.redate = redate;
	}
	public int getRe_like() {
		return re_like;
	}
	public void setRe_like(int re_like) {
		this.re_like = re_like;
	}
	
	
	public ReplyBean(int no, String content, int ref, String name, String redate, int re_like) {
		this.no = no;
		this.content = content;
		this.ref = ref;
		this.name = name;
		this.redate = redate;
		this.re_like = re_like;
	}
	
	
}
