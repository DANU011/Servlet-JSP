package model2.mvcboard;

import java.util.Date;

import org.apache.naming.java.javaURLContextFactory;

public class MVCBoardDTO {
	 private String idx;
	 private String name;
	 private String title;
	 private String content;
	 private Date postDate;
	 private String ofile;
	 private String sfile;
	 private int downcount;
	 private String pass;
	 private int visitcount;
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(java.sql.Date postDate) {
		this.postDate = postDate;
	}
	public String getOfile() {
		return ofile;
	}
	public void setOfile(String ofile) {
		this.ofile = ofile;
	}
	public String getSfile() {
		return sfile;
	}
	public void setSfile(String sfile) {
		this.sfile = sfile;
	}
	public int getDowncount() {
		return downcount;
	}
	public void setDowncount(int downcount) {
		this.downcount = downcount;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public int getVisitcount() {
		return visitcount;
	}
	public void setVisitcount(int visitcount) {
		this.visitcount = visitcount;
	}
	@Override
	public String toString() {
		return "MVCBoeardDTO [idx=" + idx + ", name=" + name + ", title=" + title + ", content=" + content
				+ ", postDate=" + postDate + ", ofile=" + ofile + ", sfile=" + sfile + ", downcount=" + downcount
				+ ", pass=" + pass + ", visitcount=" + visitcount + "]";
	}
	 
	 
}
