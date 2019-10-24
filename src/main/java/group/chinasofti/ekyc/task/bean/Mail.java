package group.chinasofti.ekyc.task.bean;
/**
 * Mail相关健康参数
 * @author jarries
 *
 */
public class Mail {

	private String status;
	
	private String location;
	
	private String error;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
