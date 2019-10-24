package group.chinasofti.ekyc.util.http;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import group.chinasofti.ekyc.Application;
import group.chinasofti.ekyc.task.bean.HealthBean;

/**
 * 测试HttpGetRequest方法
 * 
 * @author jarries
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpGetFailRequestTest {

	@Autowired
	HttpRequestUtil httpRequest;


	/**
	 * httpGetHealthFailRequestTest
	 * @throws Exception 
	 */
	@Test(expected = Exception.class)
	public void httpGetHealthFailRequestTest() throws Exception {

		String url = "http://localhost:8082/ekyc-monitor-service/health/test";
		httpRequest.httpGetHealthRequest(url, HealthBean.class);

	}
}
