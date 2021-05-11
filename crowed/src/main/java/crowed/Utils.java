package crowed;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Utils {

	public static enum DateFormat {
		FORMAT_TYPE_1("MM/dd/yyyy hh:mm");
		
		String format;
		DateFormat(String format) {
			this.format = format;
		}
		
		public String getFormat() {
			return this.format;
		}
	}
	
	public static final String ICON = "img/searcher.jpg";
	public static final String APPLICATION_TITLE = "Crowed";
	public static final int  APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 250;
	
	public static final int TEXT_FIELD_WIDTH = 100;
	public static final int TEXT_FIELD_HEIGHT = 20;
	
	public static final int LABEL_FONT_SIZE = 11;
	
	public static final String KEY_OS_NAME = "os.name";
	
	public static Date now() {
		return new Date();
	}
	
	/**
	 * TODO: Is to handle File check ?
	 * 
	 * @param fileNameToSearch
	 * @param extensionToSearch
	 * @param file
	 * @return
	 */
	public static boolean isFileNameMatched(String fileNameToSearch, String extensionToSearch, File file) {
		boolean isMatched = false;

		try {
			
			String fileName = file.getName();
			String extension = fileName.substring((fileName.indexOf(".") + 1), fileName.length());
			
			boolean isFileNameMatched = false;
			boolean isExtensionMatahced = true;
			if(extensionToSearch != null && !extensionToSearch.isBlank() && !extensionToSearch.isEmpty()
					&& !extension.equalsIgnoreCase(extensionToSearch)) 
				isExtensionMatahced = false;
			
			if(fileName.toUpperCase().contains(fileNameToSearch.toUpperCase()))
				isFileNameMatched = true;
			
			isMatched = (isFileNameMatched && isExtensionMatahced);
			
		} catch(Exception e) {
			// TODO: It is needed to show exception message
		}
		
		return isMatched;
	}
	
	public static String dateToString(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String ourformat = formatter.format(date.getTime());
		return ourformat;
	}
	
	public static int getHour(Date startTime, Date endTime) {
		long duration = endTime.getTime() - startTime.getTime();
		long hrDiff = (duration / (1000 * 60 * 60)) % 24;
		return (int) hrDiff;
	}
	
	public static int getMin(Date startTime, Date endTime) {
		long duration = endTime.getTime() - startTime.getTime();
		long minDiff = (duration / (1000 * 60)) % 60;
		return (int) minDiff;
	}
	
	public static int getSec(Date startTime, Date endTime) {
		long duration = endTime.getTime() - startTime.getTime();
		long secDiff = (duration / 1000) % 60;
		return (int) secDiff;
	}
	
	public static int getMiliSec(Date startTime, Date endTime) {
		long duration = endTime.getTime() - startTime.getTime();
		long mlSDiff = TimeUnit.MILLISECONDS.toMillis(duration);
		return (int) mlSDiff;
	}
	
	
}
