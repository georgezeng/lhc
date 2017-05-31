package lhc.dto.query;

public class QueryInfo<T> {
	private T object;
	private PageInfo pageInfo;
	private boolean toReverse = true;

	public boolean isToReverse() {
		return toReverse;
	}

	public void setToReverse(boolean toReverse) {
		this.toReverse = toReverse;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

}
