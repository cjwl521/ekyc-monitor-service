package group.chinasofti.ekyc.util.bean;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import group.chinasofti.ekyc.task.bean.DiskSpace;
import group.chinasofti.ekyc.task.bean.HealthBean;
import group.chinasofti.ekyc.task.bean.Mail;
import group.chinasofti.ekyc.util.http.HttpRequestUtil;

/**
 * 
 * @author jarries
 *
 */
public class BeanTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpRequestUtil.class);

	/**
	 * BusinessExceptionTest
	 * 
	 * @throws JsonProcessingException 
	 */
	@Test
	public void healthBeanTest() throws JsonProcessingException {

		HealthBean healthBean = new HealthBean();
		DiskSpace diskSpace = new DiskSpace();
		diskSpace.setFree("1223");
		diskSpace.setStatus("DOWN");
		diskSpace.setThreshold("1234");
		diskSpace.setTotal("13243424");
		healthBean.setDiskSpace(diskSpace);

		Mail mail = new Mail();
		mail.setError("javax.mail.AuthenticationFailedException: 535 Error: authentication failed\n");
		mail.setLocation("smtp.163.com:-1");
		mail.setStatus("UP");
		healthBean.setMail(mail);
		healthBean.setStatus("DOWN");
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("diskSpaceBeanTest:{} ", new ObjectMapper().writeValueAsString(healthBean));
		}
		Assert.isInstanceOf(HealthBean.class, healthBean);
	}
}
