package group.chinasofti.ekyc.task;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

import com.fasterxml.jackson.databind.ObjectMapper;

import group.chinasofti.ekyc.task.bean.HealthBean;
import group.chinasofti.ekyc.task.bean.ServicePreStatus;
import group.chinasofti.ekyc.task.bean.Status;
import group.chinasofti.ekyc.util.http.HttpRequestUtil;
import group.chinasofti.ekyc.util.log.LoggerUtil;
import group.chinasofti.ekyc.util.mail.EmailUtil;
import group.chinasofti.ekyc.vo.HealthInfo;

/**
 * 启动定时任务
 * 
 * @author jarries
 *
 */
@Component
public class SchedulerTask {

	private static final String NULL = "NULL";

	@Autowired
	private EmailUtil emailUtil;

	@Autowired
	private HttpRequestUtil httpGetRequest;

	@Value("${hsbc.mail.url}")
	private String url;

	@Value("${hsbc.mail.ekycStatusSubject}")
	private String ekycStatusSubject;

	@Value("${hsbc.mail.ekycNetworkSubject}")
	private String ekycNetworkSubject;

	private static final String STATUS_TEMPLATE_NAME = "StatusAbnormal";

	private static final String NETWORK_TEMPLATE_NAME = "NetworkAbnormal";

	private static final String FORMAT = "%s";
	
	private static final int MEGABYTES = 1024 * 1024;

	/**
	 * 执行定时任务
	 * 
	 * @param hsbc.mail.cron
	 * @return
	 */
	@Scheduled(cron = "${hsbc.mail.cron}")
	public void taskProcess() {

		HealthBean healthBean = new HealthBean();
		Status preStatus = ServicePreStatus.getInstance().getPreStatus();
		try {
			healthBean = httpGetRequest.httpGetHealthRequest(url, healthBean.getClass());
			if (null != healthBean) {
				LoggerUtil.logInfoMethod("The ekyc-service info is {}",
						new ObjectMapper().writeValueAsString(healthBean));
				Status status = getCurrentStatus(healthBean);
				if (null != status && status != preStatus) {
					LoggerUtil.logInfoMethod(String.format(FORMAT, "The ekyc-service status has changed."));
					if (status != Status.UP) {
						LoggerUtil.logInfoMethod("The ekyc-service status is [ '{}' ].", status.name());
						emailUtil.sendEmail(dataMapping(healthBean), ekycStatusSubject, STATUS_TEMPLATE_NAME);
					}
					ServicePreStatus.getInstance().setPreStatus(status);
				}
			}
		} catch (ResourceAccessException e) {
			if (preStatus != Status.BROKENNETWORK) {
				emailUtil.sendEmail("", ekycNetworkSubject, NETWORK_TEMPLATE_NAME);
				ServicePreStatus.getInstance().setPreStatus(Status.BROKENNETWORK);
			}
			LoggerUtil.logErrorMethod(String.format(FORMAT, "The ekyc-service network is abnormal!"));
		} catch (Exception e) {
			LoggerUtil.logErrorMethod(e.getMessage(), e);
		}
		LoggerUtil.logInfoMethod(String.format("%s%n",
				"*******************************End method taskProcess*******************************"));
	}

	/**
	 * 获取ekyc-service服务当前状态
	 * 
	 * @param healthBean
	 *            ekyc-service服务监控响应参数
	 * @return ekyc-service服务监控的常状态返回
	 */
	private Status getCurrentStatus(HealthBean healthBean) {
		Status status = null;
		if (Status.DOWN.name().equalsIgnoreCase(healthBean.getStatus())) {
			if (null != healthBean.getMail() && null != healthBean.getDiskSpace()) {
				if (Status.DOWN.name().equalsIgnoreCase(healthBean.getMail().getStatus())
						&& Status.DOWN.name().equalsIgnoreCase(healthBean.getDiskSpace().getStatus())) {
					status = Status.DOWN;
				} else if (Status.DOWN.name().equalsIgnoreCase(healthBean.getMail().getStatus())
						&& Status.UP.name().equalsIgnoreCase(healthBean.getDiskSpace().getStatus())) {
					status = Status.MAILDOWN;
				} else {
					status = Status.DISKSPACEDOWN;
				}
			}
		} else {
			status = Status.UP;
		}
		return status;
	}

	/**
	 * 将HTTP相应的数据转换为邮件发送数据
	 * 
	 * @param healthBean
	 *            HTTP相应的健康数据
	 * @return ekyc-service服务监控的邮件信息
	 */
	private HealthInfo dataMapping(HealthBean healthBean) {

		HealthInfo healthInfo = new HealthInfo();
		healthInfo.setStatus(healthBean.getStatus());
		healthInfo.setMailStatus(healthBean.getMail().getStatus());
		healthInfo.setMailLocation(healthBean.getMail().getLocation());
		healthInfo.setMailError(null == healthBean.getMail().getError() ? NULL : healthBean.getMail().getError());

		healthInfo.setDiskSpaceStatus(healthBean.getDiskSpace().getStatus());
		healthInfo.setDiskSpaceTotal(conversionData(healthBean.getDiskSpace().getTotal()));
		healthInfo.setDiskSpaceFree(conversionData(healthBean.getDiskSpace().getFree()));
		healthInfo.setDiskSpaceThreshold(conversionData(healthBean.getDiskSpace().getThreshold()));
		return healthInfo;
	}
	
	/**
	 * conversionData
	 * @param str 
	 * @return String
	 */
	private static String conversionData(String str) {
		int num = new BigDecimal(str).divide(new BigDecimal(String.valueOf(MEGABYTES))).intValue();
		return String.valueOf((int) Math.floor(num));
	}
}
