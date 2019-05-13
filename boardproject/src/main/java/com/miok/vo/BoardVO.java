package com.miok.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class BoardVO {

	private String bgno, brdno, brdtitle, brdwriter, brdmemo, brddate, brdhit, brddeleteflag, filecnt;
	private List<MultipartFile> uploadfile;
	
	
	public String getBgno() {
		return bgno;
	}

	public void setBgno(String bgno) {
		this.bgno = bgno;
	}

	public String getBrdno() {
		return brdno;
	}

	public void setBrdno(String brdno) {
		this.brdno = brdno;
	}
	
	public String getBrdtitle() {
		return brdtitle;
	}

	public void setBrdtitle(String brdtitle) {
		this.brdtitle = brdtitle;
	}

	public String getBrdwriter() {
		return brdwriter;
	}

	public void setBrdwriter(String brdwriter) {
		this.brdwriter = brdwriter;
	}

	public String getBrdmemo() {
		/* 게시판 내용에 악의적으로 입력한 자바스크립트가 실행되지 않도록 설정
		 * 정규식에서(?i)는 대소문자 방지구분 없음. */
		return brdmemo.replaceAll("(?i)<script", "&lt;script");
	}

	public void setBrdmemo(String brdmemo) {
		this.brdmemo = brdmemo;
	}

	public String getBrddate() {
		return brddate;
	}

	public void setBrddate(String brddate) {
		this.brddate = brddate;
	}

	public String getBrdhit() {
		return brdhit;
	}

	public void setBrdhit(String brdhit) {
		this.brdhit = brdhit;
	}

	public String getBrddeleteflag() {
		return brddeleteflag;
	}

	public void setBrddeleteflag(String brddeleteflag) {
		this.brddeleteflag = brddeleteflag;
	}

	public String getFilecnt() {
		return filecnt;
	}

	public void setFilecnt(String filecnt) {
		this.filecnt = filecnt;
	}

	public List<MultipartFile> getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(List<MultipartFile> uploadfile) {
		this.uploadfile = uploadfile;
	}
	
	
}
