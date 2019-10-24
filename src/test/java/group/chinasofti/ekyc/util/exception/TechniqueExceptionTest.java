package group.chinasofti.ekyc.util.exception;

import org.junit.Test;

import group.chinasofti.ekyc.exception.TechniqueException;
/**
 * 
 * @author jarries
 *
 */
public class TechniqueExceptionTest {

	/**
	 * techniqueException
	 */
	@Test(expected = TechniqueException.class)
	public void techniqueException() {
		throw new TechniqueException();
	}
	
	/**
	 * techniqueException
	 */
	@Test(expected = TechniqueException.class)
	public void techniqueException1() {
		throw new TechniqueException("TEST TechniqueException", null, true, true);
	}
	
	
	/**
	 * techniqueException
	 */
	@Test(expected = TechniqueException.class)
	public void techniqueException2() {
		throw new TechniqueException("TEST TechniqueException", null);
	}
	
	/**
	 * techniqueException
	 */
	@Test(expected = TechniqueException.class)
	public void techniqueException3() {
		throw new TechniqueException("TEST TechniqueException");
	}
	
	/**
	 * techniqueException
	 */
	@Test(expected = TechniqueException.class)
	public void techniqueException4() {
		throw new TechniqueException(new Throwable());
	}
}
