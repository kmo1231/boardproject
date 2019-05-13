package com.miok.vo;

public class BoardGroupVO {

    private String bgno; 			// 게시판그룹번
    private String bgname; 			// 게시판 그룹명
    private String bglevel; 
    private String bgparent;		// 게시판 그룹 부모
    private String bgdeleteflag; 	// 삭제 여부
    private String bgused; 			// 사용 여부
    private String bgreply; 		// 댓글 사용 여부
    private String bgreadonly; 		// 글쓰기 가능 여부
    private String bgdate; 			// 생성일자

    public String getBgno() {
        return bgno;
    }

    public void setBgno(String bgno) {
        this.bgno = bgno;
    }

    public String getBgname() {
        return bgname;
    }

    public void setBgname(String bgname) {
        this.bgname = bgname;
    }

    public String getBglevel() {
        return bglevel;
    }

    public void setBglevel(String bglevel) {
        this.bglevel = bglevel;
    }

    public String getBgparent() {
        return bgparent;
    }

    public void setBgparent(String bgparent) {
        this.bgparent = bgparent;
    }

    public String getBgdeleteflag() {
        return bgdeleteflag;
    }

    public void setBgdeleteflag(String bgdeleteflag) {
        this.bgdeleteflag = bgdeleteflag;
    }

    public String getBgused() {
        return bgused;
    }

    public void setBgused(String bgused) {
        this.bgused = bgused;
    }

    public String getBgreply() {
        return bgreply;
    }

    public void setBgreply(String bgreply) {
        this.bgreply = bgreply;
    }

    public String getBgreadonly() {
        return bgreadonly;
    }

    public void setBgreadonly(String bgreadonly) {
        this.bgreadonly = bgreadonly;
    }

    public String getBgdate() {
        return bgdate;
    }

    public void setBgdate(String bgdate) {
        this.bgdate = bgdate;
    }
}
