package group.chinasofti.ekyc.task.bean;

/**
 * DiskSpace的参数
 * @author jarries
 *
 */
public class DiskSpace {
	
	private String status;
	
	private String total;
	
	private String free;
	
	private String threshold;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getFree() {
		return free;
	}

	public void setFree(String free) {
		this.free = free;
	}

	public String getThreshold() {
		return threshold;
	}

	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}
}
