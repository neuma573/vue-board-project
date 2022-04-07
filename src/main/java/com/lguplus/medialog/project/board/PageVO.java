package com.lguplus.medialog.project.board;

import lombok.Data;

@Data
public class PageVO {
    private Integer displayRowCount;		    // 출력할 데이터 개수
    private Integer rowStart;                       // 시작행번호
    private Integer rowEnd;                         // 종료행 번호
    private Integer totPage;                        // 전체 페이수
    private Integer totRow = 0;                     // 전체 데이터 수
    private Integer page;                           // 현재 페이지
    private Integer pageStart;                      // 시작페이지
    private Integer pageEnd;                        // 종료페이지
    private boolean hasNext, hasPrev;
	private boolean selectOrigin = false;
	private boolean selectRe = false;
	private boolean pagingByNew;
    /**
     * 전체 데이터 개수(total)를 이용하여 페이지수 계산. 
     */
	public void pageCalculate(Integer total) {
		getPage();
		totRow = total;
		totPage = (int) (total / displayRowCount);

		if (total % displayRowCount > 0) {
			totPage++;
		}

		pageStart = (page - (page - 1) % 10);
		pageEnd = pageStart + 9;
		if (pageEnd > totPage) {
			pageEnd = totPage;
		}

		rowStart = ((page - 1) * displayRowCount) + 1;
		rowEnd = rowStart + displayRowCount - 1;
		
		if(pageStart==1) {
			hasPrev=false;
		}
		else {
			hasPrev=true;
		}
		if(pageEnd<totPage) {
			hasNext=true;
		}
		else {
			hasNext=false;
		}
	}

    /**
     * 현재 페이지 번호. 
     */
	public Integer getPage() {
		if (page == null || page == 0) {
			page = 1;
		}

		return page;
	}

	
}


