package group.chinasofti.ekyc.util.http;

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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import group.chinasofti.ekyc.Application;
import group.chinasofti.ekyc.task.bean.DiskSpace;
import group.chinasofti.ekyc.task.bean.HealthBean;
import group.chinasofti.ekyc.task.bean.Mail;

/**
 * 测试HttpGetRequest方法
 * 
 * @author jarries
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpGetRequestTest {

	@Autowired
	HttpRequestUtil httpRequest;

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * 检查HTTP Get Request的工具类是否正常
	 * 
	 * @throws Exception 
	 */
	@Test
	public void httpGetHealthSuccessfulRequestTest() throws Exception {

		MockRestServiceServer server = MockRestServiceServer.bindTo(restTemplate).build();

		String url = "http://localhost:8082/ekyc-monitor-service/health/test1";
		HealthBean healthBean = this.healthBean(new HealthBean());
		String str = new ObjectMapper().writeValueAsString(healthBean);
		server.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess(str, MediaType.APPLICATION_JSON));
		healthBean = httpRequest.httpGetHealthRequest(url, HealthBean.class);

		Assert.isInstanceOf(HealthBean.class, healthBean);
	}
	
	/**
	 * healthBean
	 * @param healthBean 
	 * @return HealthBean
	 */
	private HealthBean healthBean(HealthBean healthBean) {

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
}
