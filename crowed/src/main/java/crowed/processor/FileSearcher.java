package crowed.processor;

import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import crowed.Utils;
import crowed.ui.StarterFragment;

public class FileSearcher {

	private static final String PROCESS_TIME = " (Started: %s [%s Hr, %s Min, %s Sec])";
	
	private StarterFragment mStarterFragment;
	private Set<String> mFileLocations = new HashSet<String>();
	// Search Process Start Time
	private Date mStartTime;
	
	private enum SupportedOS {
		WINDOWS;
	}
	
	public FileSearcher(StarterFragment fragmen) {
		this.mStarterFragment = fragmen;
	}
	
	/**
	 * TODO: 
	 * 	1. IS need to seggregate UI logic.
	 *  2. On click of location directory or file will open
	 *  	ref : https://www.javatpoint.com/how-to-open-a-file-in-java
	 */
	public void startSearch() {
		String fileName = mStarterFragment.mFileName.getText();
		String extension = mStarterFragment.mFileExtension.getText();
		
		if(!isSupportedOSPlatform()) {
			// TODO: Error message
			return;
		}
		
		mStartTime = Utils.now(); 
		mStarterFragment.resetForm();
		
		File[] files = File.listRoots();
		for(File file : files) {
			System.out.println("Root Dir : " + file.getAbsolutePath());
			search(fileName, extension, file);
		}
		
		Date processEndTime = Utils.now();
		String result = StarterFragment.mResultLabelText + String.format(PROCESS_TIME, 
				Utils.dateToString(mStartTime, Utils.DateFormat.FORMAT_TYPE_1.getFormat()), 
				Utils.getHour(mStartTime, processEndTime), 
				Utils.getMin(mStartTime, processEndTime), 
				Utils.getSec(mStartTime, processEndTime));
		//mStarterFragment.mResultLabel.setText(result);

		writeResult();
		mStarterFragment.updateLocationText();
		System.out.println(mFileLocations);
	}
	
	private void writeResult() {
		String result = "No result Found";
		if(mFileLocations.size() == 0) {
			mStarterFragment.mResults.setText(result);
			return;
		}
		
		result = "";
		mStarterFragment.mResults.setEnabled(true);
		for(String location : mFileLocations) {
			result += location + "\n";
		}
		
		mStarterFragment.mResults.setText(result);
	}
	
	/**
	 * Initiating process to search file
	 * TODO: To optimize process later include Mutithreading 
	 * 
	 * @param fileName
	 * @param extension
	 */
	private void search(String fileName, String extension, File file) {
		if(file.isFile()) {
			if(Utils.isFileNameMatched(fileName, extension, file))
				mFileLocations.add(file.getAbsolutePath());
			return;
		}
		// mStarterFragment.updateLocationText(file.getAbsolutePath());
		// Directory
		File[] directories = file.listFiles();
		if(directories == null || directories.length == 0)
			return;
		
		for(File subDir : directories) 
			search(fileName, extension, subDir);
	}
	
	private boolean isSupportedOSPlatform() {
		String osName = System.getProperty(Utils.KEY_OS_NAME);
		if(osName.toUpperCase().contains(SupportedOS.WINDOWS.toString()))
			return true;
		return false;
	}
}
