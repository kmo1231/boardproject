package com.miok.common;

public class PageVO {
    private int displayRowCount = 5;			// 출력할 데이터 개수
    private int displayPage = 2;				// 출력할 페이지 번호 수
    private int rowStart;                       // 시작행번호
    private int rowEnd;                         // 종료행 번호
    private int totPage;                        // 전체 페이수
    private int totRow = 0;                     // 전체 데이터 수
    private int page;                           // 현재 페이지
    private int pageStart;                      // 시작페이지
    private int pageEnd;                        // 종료페이지

    /**
     * 전체 데이터 개수(total)를 이용하여 페이지수 계산. 
     */
    public void pageCalculate(int total) {
        getPage();
        totRow  = total;
        totPage    = (int) ( total / displayRowCount );
        
        if ( total % displayRowCount > 0 ) {
            totPage++;
        }

        pageStart = ((page - 1) / displayPage) * displayPage + 1;
        //pageStart = (page - (page - 1) % 5) ;
        pageEnd = pageStart + displayPage - 1;
        //pageEnd = pageStart + 4;
        if (pageEnd > totPage) {
            pageEnd = totPage;
        }
        
        rowStart = ((page - 1) * displayRowCount) + 1 ;
        rowEnd   = rowStart + displayRowCount - 1;
    } 
    

    /**
     * 현재 페이지 번호. 
     */
    public int getPage() {
        if (page == 0) {
            page = 1;
        }
        
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRowStart() {
        return rowStart;
    }

    public void setRowStart(Integer rowStart) {
        this.rowStart = rowStart;
    }

    public Integer getRowEnd() {
        return rowEnd;
    }

    public void setRowEnd(Integer rowEnd) {
        this.rowEnd = rowEnd;
    }

    public Integer getDisplayRowCount() {
        return displayRowCount;
    }

    public void setDisplayRowCount(Integer displayRowCount) {
        this.displayRowCount = displayRowCount;
    }

    public Integer getTotPage() {
        return totPage;
    }

    public void setTotPage(Integer totPage) {
        this.totPage = totPage;
    }

    public Integer getTotRow() {
        return totRow;
    }

    public void setTotRow(Integer totRow) {
        this.totRow = totRow;
    }

    public Integer getPageStart() {
        return pageStart;
    }

    public void setPageStart(Integer pageStart) {
        this.pageStart = pageStart;
    }

    public Integer getPageEnd() {
        return pageEnd;
    }

    public void setPageEnd(Integer pageEnd) {
        this.pageEnd = pageEnd;
    }


	public Integer getDisplayPage() {
		return displayPage;
	}


	public void setDisplayPage(Integer displayPage) {
		this.displayPage = displayPage;
	}
    
    


    
}


