package group.chinasofti.ekyc.util.exception;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import group.chinasofti.ekyc.exception.GlobalExceptionHandler;

/**
 * @author jarries
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class GlobalExceptionHandlerTest {

	/**
	 * testBusinessExceptionHandler
	 */
	@Test
	public void testBusinessExceptionHandler() {
		try {
			String nonInt = "ac";
			Integer.parseInt(nonInt);
		} catch (NumberFormatException e) {
			new GlobalExceptionHandler().businessExceptionHandler(e);
		}

	}

	/**
	 * testHttpMessageConversionHandler
	 */
	@Test
	public void testHttpMessageConversionHandler() {
		try {
			FileUtils.cleanDirectory(new File("Non-Dir"));
		} catch (IllegalArgumentException e) {
			new GlobalExceptionHandler().lllegalArgumentExceptionHandler(e);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * testLllegalArgumentExceptionHandler
	 */
	@Test
	public void testLllegalArgumentExceptionHandler() {
		new GlobalExceptionHandler().httpMessageConversionHandler(new HttpMessageConversionException(
				"Could not read class [ java.lang.String]. Only DOMSource, SAXSource, StAXSource, and StreamSource are supported."));
	}
}
