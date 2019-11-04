package cn.shineiot.blog.bean;

import java.util.List;

/**
 * @author GF63
 * 服务器返回的公众号历史数据
 */
public class WxPublicData {

	/**
	 * curPage : 2
	 * datas : []
	 * offset : 20
	 * over : true
	 * pageCount : 2
	 * size : 20
	 * total : 27
	 */

	private int curPage;
	private int offset;
	private boolean over;
	private int pageCount;
	private int size;
	private int total;
	private List<WxArticle> datas;

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public boolean isOver() {
		return over;
	}

	public void setOver(boolean over) {
		this.over = over;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<WxArticle> getDatas() {
		return datas;
	}

	public void setDatas(List<WxArticle> datas) {
		this.datas = datas;
	}
}
