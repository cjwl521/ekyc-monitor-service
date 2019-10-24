package group.chinasofti.ekyc.util.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import group.chinasofti.ekyc.exception.BusinessException;
import group.chinasofti.ekyc.util.log.LoggerUtil;
import group.chinasofti.ekyc.vo.HealthInfo;

/**
 * EmailServiceTest
 * @author jarries
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("unittest")
public class EmailUtilTest {

	@Autowired
	private EmailUtil emailService;
	
	@Value("${hsbc.mail.ekycStatusSubject}")
	private String subject;
	
	private static final String TEMPLATE_NAME = "StatusAbnormal";
	/**
	 * 检查执行邮件发送工具类是否正常
	 * @throws Exception  
	 */
	@Test
	public void sendEmailTest() throws Exception {
		HealthInfo data = new HealthInfo();
		data.setStatus("DOWN");
		data.setMailStatus("DOWN");
		data.setMailLocation("smtp.163.com:-1");
		data.setMailError("javax.mail.AuthenticationFailedException: 535 Error: authentication failed\n");
		data.setDiskSpaceStatus("UP");
		data.setDiskSpaceTotal("102670");
		data.setDiskSpaceFree("11881");
		data.setDiskSpaceThreshold("1024");
		emailService.sendEmail(data, subject, TEMPLATE_NAME);
	}
	
	/**
	 * 检查执行邮件发送工具类的异常情况
	 * @throws Exception  
	 */
	@Test(expected = BusinessException.class)
	public void sendEmail2Test() {
		try {
			emailService.sendEmail(String.class, subject, TEMPLATE_NAME);
		} catch (Exception e) {
			LoggerUtil.logErrorMethod(e.getMessage(), e);
			throw e;
		}
	}
}
