package crowed.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import crowed.Utils;
import crowed.processor.FileSearcher;

public class StarterFragment extends JFrame {
	
	/* 
	 * Helps in deserialization Process. If not defined then probabily get 
	 * Deserialization Exception.
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Result Process Response: Result (Started At: [Start Time] [HH:MM:SS:MS]) 
	public static final String mResultLabelText = "Result";
	
	public StarterFragment mStarterFragment;
	public JTextField mFileName;
	public JTextField mFileExtension;
	private JButton mSubmitBtn;
	public JLabel mResultLabel;
	
	// Displayed searched location
	public JLabel mProcessLabel;
	
	// Location of file
	public JTextArea mResults;
	
	// JPanels
	private JPanel mContentBody;
	
	private FileSearcher mFileSearcher;
	
	public StarterFragment() {
		mStarterFragment = this; 
		mFileSearcher = new FileSearcher(this);
		init();
	}
	
	/**
	 * Inserting UI elements
	 * 
	 */
	private void init() {
		mStarterFragment.setTitle(Utils.APPLICATION_TITLE);
		
		// Application Main Fragment Properties
		mStarterFragment.setSize(Utils.APPLICATION_WIDTH, Utils.APPLICATION_HEIGHT);
		ImageIcon icon = new ImageIcon(Utils.ICON);
		mStarterFragment.setIconImage(icon.getImage());
		mStarterFragment.getContentPane().add(getContentBody(), BorderLayout.CENTER);
		
		mStarterFragment.setVisible(true);
		mStarterFragment.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mStarterFragment.setResizable(false);
		mStarterFragment.pack();
		
		mStarterFragment.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				 System.runFinalization();
				 System.exit(0);
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		setApplicationFramePosition();
	}
	
	/**
	 * Adds content of UI body in Application
	 * such file name text field, btns etc.
	 * 
	 * @return
	 */
	private JPanel getContentBody() {
		if(mContentBody != null)
			return mContentBody;
		
		// Init Content body
		mContentBody = new JPanel();
		JPanel body = new JPanel();
		mContentBody.add(body);
		
		body.setLayout(new GridBagLayout());
		// Sets margin
		body.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		GridBagConstraints gbc = new GridBagConstraints();
		JLabel l1 = new JLabel("File Name");
		Font font = l1.getFont();
		l1.setFont(new Font(font.getName(), Font.BOLD, Utils.LABEL_FONT_SIZE));
		l1.setPreferredSize(new Dimension(Utils.TEXT_FIELD_WIDTH, Utils.TEXT_FIELD_HEIGHT));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 2, 2, 2);
		body.add(l1, gbc);
		
		mFileName = new JTextField();
		mFileName.setPreferredSize(new Dimension(Utils.TEXT_FIELD_WIDTH, Utils.TEXT_FIELD_HEIGHT));
		gbc.gridx = 0;
		gbc.gridy = 1;
		body.add(mFileName, gbc);
				
		JLabel l2 = new JLabel("Extension");
		font = l1.getFont();
		l2.setFont(new Font(font.getName(), Font.BOLD, Utils.LABEL_FONT_SIZE));
		l2.setPreferredSize(new Dimension(Utils.TEXT_FIELD_WIDTH, Utils.TEXT_FIELD_HEIGHT));
		gbc.gridx = 1;
		gbc.gridy = 0;
		body.add(l2, gbc);
		
		mFileExtension = new JTextField();
		mFileExtension.setPreferredSize(new Dimension(Utils.TEXT_FIELD_WIDTH, Utils.TEXT_FIELD_HEIGHT));
		gbc.gridx = 1;
		gbc.gridy = 1;
		body.add(mFileExtension, gbc);
		
		mSubmitBtn = new JButton("Submit");
		mSubmitBtn.setPreferredSize(new Dimension(80, 20));
		mSubmitBtn.addActionListener(e -> {
			try {
				
				Thread t = new Thread(() -> {
					mFileSearcher.startSearch();
				});
				
				t.start();
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		
		gbc.gridx = 2;
		gbc.gridy = 1;
		body.add(mSubmitBtn, gbc);
		mProcessLabel = new JLabel("ssds");
		mProcessLabel.setFont(new Font("Serif", Font.PLAIN, 15));
		mProcessLabel.setForeground(Color.decode("#999999"));
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.insets = new Insets(30, 5, 5, 5);
		gbc.gridwidth = 3;
		gbc.weightx = 0.2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		body.add(mProcessLabel, gbc);
		
		mResultLabel = new JLabel(mResultLabelText);
		font = l1.getFont();
		mResultLabel.setFont(new Font(font.getName(), Font.BOLD, Utils.LABEL_FONT_SIZE));
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.insets = new Insets(2, 5, 0, 5);
		body.add(mResultLabel, gbc);
		
		mResults = new JTextArea(5, 2);
		JScrollPane scrollPane = new JScrollPane(mResults, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(2, 2, 2, 0);
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
		scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(10, 0));
		scrollPane.setPreferredSize(new Dimension(290, 60));
		
		mResults.setEnabled(false);
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.insets = new Insets(10, 5, 5, 5);
		gbc.gridwidth = 3;
		body.add(scrollPane, gbc);
		
		return mContentBody;
	}
	
	/**
	 * TODO: Set position, Right Corner At bottom
	 */
	private void setApplicationFramePosition() {
		mStarterFragment.setLocationRelativeTo(null);
		GraphicsConfiguration config = mStarterFragment.getGraphicsConfiguration();
	    Rectangle bounds = config.getBounds();
	    Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(config);

	    int x = bounds.x + bounds.width - insets.right - mStarterFragment.getWidth();
	    int y = bounds.y + insets.top;
	    mStarterFragment.setLocation(x, y);
	}
	
	public void resetForm() {
		mResultLabel.setText(StarterFragment.mResultLabelText);
		mResults.setText("");
		mResults.setEnabled(false);
	}
	
	public void updateLocationText() {
		updateLocationText(null);
	}
	
	public void updateLocationText(String location) {
		if(location == null || location.isBlank() || location.isEmpty()) {
			location = "";
		}
		
		System.err.println(location);
		mProcessLabel.setText(null);
		mProcessLabel.setText(location);
		mProcessLabel.paintImmediately(mProcessLabel.getVisibleRect());
	}
}
