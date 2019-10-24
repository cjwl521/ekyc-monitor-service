package group.chinasofti.ekyc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author jarries
 *
 */
@RunWith(SpringRunner.class)
public class ApplicationTest {
	/**
	 * test Main Application
	 */
	@Test
	public void testMain() {
		Application.main(new String[] {});
	}
}
