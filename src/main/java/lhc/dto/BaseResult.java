package lhc.dto;

public class BaseResult {
	public static final BaseResult EMPTY = new BaseResult();
	
	private Object data;
	private int status;
	private String msg;

	public BaseResult() {
		status = 1;
	}

	public BaseResult(Object data) {
		status = 1;
		this.data = data;
	}

	public BaseResult(String msg) {
		status = 0;
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
