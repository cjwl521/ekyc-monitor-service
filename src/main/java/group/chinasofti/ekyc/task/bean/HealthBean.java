package group.chinasofti.ekyc.task.bean;

/**
 * ekyc-service服务监控响应参数定义
 * @author jarries
 *
 */
public class HealthBean {

	//ekyc-service服务状态
	private String status;
	
	//ekyc-service对应的mail监控信息
	private Mail mail;
	
	//ekyc-service对应的diskSpace状态监控信息
	private DiskSpace diskSpace;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Mail getMail() {
		return mail;
	}

	public void setMail(Mail mail) {
		this.mail = mail;
	}

	public DiskSpace getDiskSpace() {
		return diskSpace;
	}

	public void setDiskSpace(DiskSpace diskSpace) {
		this.diskSpace = diskSpace;
	}

}
