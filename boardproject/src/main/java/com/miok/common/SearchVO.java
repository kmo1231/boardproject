package com.miok.common;

public class SearchVO extends PageVO{
	private String brno;
	private String searchKeyword;
	private String searchType;
	private String[] searchTypeArr;
	
	public String getBrno() {
		return brno;
	}
	public void setBrno(String brno) {
		this.brno = brno;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	
    public String[] getSearchTypeArr() {
        return searchType.split(",");
    }
    
	
}
