package group.chinasofti.ekyc.util.mail;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import group.chinasofti.ekyc.exception.BusinessException;
import group.chinasofti.ekyc.util.log.LoggerUtil;

/**
 * 邮件发送服务
 * @author jarries
 *
 */
@Component
public class EmailUtil {

	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private SpringTemplateEngine templateEngine;

	@Value("${hsbc.mail.sender}")
	private String localSender;
	@Value("${hsbc.mail.reciever}")
	private String reciever;
	private static final String FORM = "form";

	/**
	 * 执行邮件发送功能
	 * @param obj  
	 * @param subject  
	 * @param templateName  
	 */
	public void sendEmail(Object obj, String subject, String templateName) {
		Assert.notNull(obj, "[Assertion failed] - this argument [obj] is required; it must not be null");
		MimeMessage message = null;
		long startEmailTime = System.currentTimeMillis();
		try {
			message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			Context context = new Context();
			context.setVariable(FORM, obj);

			//设置接收邮箱地址
			helper.setTo(reciever);
			//设置邮件发送主题
			helper.setSubject(subject);
			// 设置发送邮箱地址
			helper.setFrom(localSender);
			helper.setText(templateEngine.process(templateName, context), true);

			LoggerUtil.logInfoMethod("Start send email:{}", subject);

			javaMailSender.send(message);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage(), e);
		}
			LoggerUtil.logInfoMethod("The email send is successful and spend time is: {}ms",
					String.valueOf(System.currentTimeMillis() - startEmailTime));
	}
}
