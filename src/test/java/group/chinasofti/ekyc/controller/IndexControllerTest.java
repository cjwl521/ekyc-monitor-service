package group.chinasofti.ekyc.controller;

import org.junit.Test;
import org.springframework.util.Assert;

/**
 * @author jarries
 *
 */
public class IndexControllerTest {

	/**
	 * indexTest
	 */
	@Test
	public void indexTest() {
		IndexController indexController = new IndexController();
		String result = indexController.index();
		Assert.hasText("It works", result);
	}
}
