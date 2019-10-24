package group.chinasofti.ekyc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * e-KYC 项目启动类
 * @author jarries
 *
 */
@SpringBootApplication
public class Application {

	@Autowired  
	private RestTemplateBuilder builder;  
	
	/**
	 * @return 注入RestTemplate
	 */
    @Bean  
    public RestTemplate restTemplate() {  
        return builder.build();  
    }  
    /**
	 * 主方法
	 * @param args 主方法参数
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
