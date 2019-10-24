package group.chinasofti.ekyc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 预留Controller接口
 * @author jarries
 *
 */
@Controller
public class IndexController {
	/**
	 * 预留index方法
	 * @return String
	 */
	@RequestMapping("/")
	@ResponseBody
	public String index() {
		return "It works";
	}

}
