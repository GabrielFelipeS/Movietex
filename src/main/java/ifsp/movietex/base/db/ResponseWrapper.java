package ifsp.movietex.base.db;

public class ResponseWrapper{
	private int status;
	private Object data;


	public void setStatus(int status) {
		this.status = status;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
