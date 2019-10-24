package group.chinasofti.ekyc.vo;

/**
 * HealthInfo健康检查发送邮件信息
 * @author jarries
 */
public class HealthInfo {
	
	//健康检查状态字段
	private String status;
	
	//邮件服务健康状态
	private String mailStatus;
	
	//邮件服务地址信息
	private String mailLocation;
	
	//邮件服务异常信息
	private String mailError;
	
	//磁盘健康状态字段
	private String diskSpaceStatus;
	
	//磁盘容量信息
	private String diskSpaceTotal;
	
	//磁盘剩余容量信息
	private String diskSpaceFree;
	
	//磁盘剩余容量报警阈值
	private String diskSpaceThreshold;
	
	public String getMailStatus() {
		return mailStatus;
	}

	public void setMailStatus(String mailStatus) {
		this.mailStatus = mailStatus;
	}

	public String getMailLocation() {
		return mailLocation;
	}

	public void setMailLocation(String mailLocation) {
		this.mailLocation = mailLocation;
	}

	public String getMailError() {
		return mailError;
	}

	public void setMailError(String mailError) {
		this.mailError = mailError;
	}

	public String getDiskSpaceStatus() {
		return diskSpaceStatus;
	}

	public void setDiskSpaceStatus(String diskSpaceStatus) {
		this.diskSpaceStatus = diskSpaceStatus;
	}

	public String getDiskSpaceTotal() {
		return diskSpaceTotal;
	}

	public void setDiskSpaceTotal(String diskSpaceTotal) {
		this.diskSpaceTotal = diskSpaceTotal;
	}

	public String getDiskSpaceFree() {
		return diskSpaceFree;
	}

	public void setDiskSpaceFree(String diskSpaceFree) {
		this.diskSpaceFree = diskSpaceFree;
	}

	public String getDiskSpaceThreshold() {
		return diskSpaceThreshold;
	}

	public void setDiskSpaceThreshold(String diskSpaceThreshold) {
		this.diskSpaceThreshold = diskSpaceThreshold;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}

