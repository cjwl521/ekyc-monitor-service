package group.chinasofti.ekyc.util.task;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import group.chinasofti.ekyc.task.SchedulerTask;
import group.chinasofti.ekyc.task.bean.DiskSpace;
import group.chinasofti.ekyc.task.bean.HealthBean;
import group.chinasofti.ekyc.task.bean.Mail;

/**
 * SchedulerTaskTest
 * @author jarries
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("unittest")
public class SchedulerTaskTest {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private SchedulerTask schedulerTask;

	/**
	 * taskProcessTest1
	 * @throws Exception 
	 */
	@Test
	public void taskProcessTest1() throws Exception {
		MockRestServiceServer server = MockRestServiceServer.bindTo(restTemplate).build();
		String url = "http://localhost:8082/ekyc-monitor-service/health/test";
		HealthBean healthBean = this.healthBeanDiskSpaceDown(new HealthBean());
		String str = new ObjectMapper().writeValueAsString(healthBean);
		server.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess(str, MediaType.APPLICATION_JSON));
		schedulerTask.taskProcess();
	}
	
	/**
	 * taskProcessTest2
	 * @throws Exception 
	 */
	@Test
	public void taskProcessTest2() throws Exception {
		MockRestServiceServer server = MockRestServiceServer.bindTo(restTemplate).build();
		String url = "http://localhost:8082/ekyc-monitor-service/health/test";
		HealthBean healthBean = this.healthBeanMailDown(new HealthBean());
		String str = new ObjectMapper().writeValueAsString(healthBean);
		server.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess(str, MediaType.APPLICATION_JSON));
		schedulerTask.taskProcess();
	}
	/**
	 * taskProcessTest3
	 * @throws Exception 
	 */
	
	@Test
	public void taskProcessTest3() throws Exception {
		MockRestServiceServer server = MockRestServiceServer.bindTo(restTemplate).build();
		String url = "http://localhost:8082/ekyc-monitor-service/health/test";
		HealthBean healthBean = this.healthBeanUP(new HealthBean());
		String str = new ObjectMapper().writeValueAsString(healthBean);
		server.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess(str, MediaType.APPLICATION_JSON));
		schedulerTask.taskProcess();
	}
	
	/**
	 * healthBean
	 * @param healthBean 
	 * @return HealthBean
	 */
	private HealthBean healthBeanDiskSpaceDown(HealthBean healthBean) {

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

		return healthBean;
	}
	
	/**
	 * healthBean
	 * @param healthBean 
	 * @return HealthBean
	 */
	private HealthBean healthBeanMailDown(HealthBean healthBean) {

		DiskSpace diskSpace = new DiskSpace();
		diskSpace.setFree("1223");
		diskSpace.setStatus("UP");
		diskSpace.setThreshold("1234");
		diskSpace.setTotal("13243424");
		healthBean.setDiskSpace(diskSpace);

		Mail mail = new Mail();
		mail.setError("javax.mail.AuthenticationFailedException: 535 Error: authentication failed\n");
		mail.setLocation("smtp.163.com:-1");
		mail.setStatus("DOWN");
		healthBean.setMail(mail);
		healthBean.setStatus("DOWN");

		return healthBean;
	}
	
	/**
	 * healthBean
	 * @param healthBean 
	 * @return HealthBean
	 */
	private HealthBean healthBeanUP(HealthBean healthBean) {

		DiskSpace diskSpace = new DiskSpace();
		diskSpace.setFree("1223");
		diskSpace.setStatus("UP");
		diskSpace.setThreshold("1234");
		diskSpace.setTotal("13243424");
		healthBean.setDiskSpace(diskSpace);

		Mail mail = new Mail();
		mail.setError("javax.mail.AuthenticationFailedException: 535 Error: authentication failed\n");
		mail.setLocation("smtp.163.com:-1");
		mail.setStatus("UP");
		healthBean.setMail(mail);
		healthBean.setStatus("UP");

		return healthBean;
	}
}
