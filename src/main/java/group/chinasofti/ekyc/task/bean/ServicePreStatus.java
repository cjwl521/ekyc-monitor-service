package group.chinasofti.ekyc.task.bean;
/**
 * 监控状态缓存
 * @author jarries
 *
 */
public class ServicePreStatus {
	
	private static ServicePreStatus instance;
	
	/**
	 * @return 返回 ServicePreStatus
	 */
	public static synchronized  ServicePreStatus getInstance() {
		if (null == instance) {
			instance = new ServicePreStatus();
		}
		return instance;
	}

	/**
	 * 被监控服务的历史状态
	 */
	private  Status preStatus = Status.UP;
	
	public Status getPreStatus() {
		return preStatus;
	}

	public void setPreStatus(Status preStatus) {
		this.preStatus = preStatus;
	}
}
