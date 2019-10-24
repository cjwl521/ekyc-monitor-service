package group.chinasofti.ekyc.util.exception;

import org.junit.Test;

import group.chinasofti.ekyc.exception.BusinessException;

/**
 * @author jarries
 *
 */
/**
 * 测试HttpGetRequest方法
 * 
 * @author jarries
 *
 */
public class BusinessExceptionTest {

	/**
	 * BusinessExceptionTest
	 */
	@Test(expected = BusinessException.class)
	public void businessExceptionTest() {
		throw new BusinessException();
	}

	/**
	 * businessException1Test
	 */
	@Test(expected = BusinessException.class)
	public void businessExceptionsTest() {
			throw new BusinessException("TEST BusinessException", null, true, true);
	}

	/**
	 * businessException2Test
	 */
	@Test(expected = BusinessException.class)
	public void businessException2Test() {
		throw new BusinessException("TEST BusinessException", null);
	}

	/**
	 * businessException3Test 
	 */
	@Test(expected = BusinessException.class)
	public void businessException3Test() {
		throw new BusinessException("TEST BusinessException");
	}

	/**
	 * businessException3Test 
	 */
	@Test(expected = BusinessException.class)
	public void businessException4Test() {
		throw new BusinessException(new Throwable());
	}
}
