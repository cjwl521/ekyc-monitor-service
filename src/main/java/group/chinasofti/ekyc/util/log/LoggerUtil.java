package group.chinasofti.ekyc.util.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import group.chinasofti.ekyc.util.http.HttpRequestUtil;

/**
 * @author jarries
 *
 */
public abstract class LoggerUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpRequestUtil.class);

	/**
	 * LoggerUtil构造函数
	 */
	private LoggerUtil() {
		
	}
	/**
	 * LogInfoMethod
	 * 
	 * @param front 
	 * @param behind 
	 */
	public static void logInfoMethod(String front, String behind) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info(front, behind);
		}
	}

	/**
	 * LogInfoMethod
	 * 
	 * @param format 
	 */
	public static void logInfoMethod(String format) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info(format);
		}
	}

	/**
	 * logErrorMethod 
	 * 
	 * @param format 
	 */
	public static void logErrorMethod(String format) {
		if (LOGGER.isErrorEnabled()) {
			LOGGER.error(format);
		}
	}

	/**
	 * logErrorMethod
	 * 
	 * @param message 
	 * @param e 
	 */
	public static void logErrorMethod(String message, Exception e) {
		if (LOGGER.isErrorEnabled()) {
			LOGGER.error(message, e);
		}
	}
}
