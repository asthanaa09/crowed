package crowed;

import javax.swing.UIManager;

import crowed.ui.StarterFragment;

/**
 * Mian start page of application
 * 
 * @author Anurag Asthana
 *
 */
public class Application {
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new StarterFragment();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
