package cn.myapp.model;

public class Page {
	
	private Integer currentPage;
	private Integer prePage;
	private Integer nextPage;
	private Integer pageCount;
	private Integer pageSize;
	
	private Integer pageOffset;
	
	public Integer getPageOffset() {
		return pageOffset;
	}
	public void setPageOffset() {
		this.pageOffset = (currentPage-1)*pageSize;
	}
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPrePage() {
		return prePage;
	}
	public void setPrePage(Integer prePage) {
		this.prePage = prePage;
	}
	public Integer getNextPage() {
		return nextPage;
	}
	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	

}
