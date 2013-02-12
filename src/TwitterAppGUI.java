import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.*;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import java.util.List;

public class TwitterAppGUI extends JFrame implements ActionListener {
	//Main Client Components
	private TwitterEngine twitterEngine;
	private JTextArea timeLine;
	private JTextArea tweetText;
	private JButton tweetButton;
	private JButton getTimeLineButton;
	private Twitter twitter;
	private List<Status> statuses;
	private JPanel mainClient;
	private LoginDialog x;
	private JButton refreshButton;
	private JPanel tweetPanel;
	private JPanel timeLinePanel;
	public boolean login = false;
	//private TweetsTimeLine tweetsTimeLine;
	private UsersTimeLine usersTimeLine;


	public TwitterAppGUI (Twitter t) throws TwitterException, IllegalStateException, MalformedURLException {	
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500, 1000);
		//this.setMaximumSize(getSize());
		//this.setPreferredSize(getSize());
		this.setMaximizedBounds(new Rectangle(500, 1000));
		this.setResizable(true);
		setTitle("Twitter");
		this.twitter = t;
		//tweetsTimeLine = new TweetsTimeLine(twitter);
		usersTimeLine = new UsersTimeLine(twitter);
		
		//timeLinePanel = new JPanel(new BorderLayout());
		setLayout(new BorderLayout());
		tweetPanel();
		timeLinePanel();
		this.pack();
		//getContentPane().setLayout(new BorderLayout());
		setVisible(true);

	}
	public void tweetPanel() throws TwitterException{
		tweetPanel = new JPanel(new BorderLayout());
		tweetText = new JTextArea(3,27);
		tweetText.setLineWrap(true);
		tweetText.setWrapStyleWord(true);
		JScrollPane scroll = new JScrollPane(tweetText);
		tweetButton = new JButton("Tweet!");
		tweetButton.setSize(90, 20);
		tweetButton.addActionListener(this);
		tweetPanel.setLayout(new FlowLayout());
		tweetPanel.add(scroll, BorderLayout.NORTH);
		tweetPanel.add(tweetButton, BorderLayout.EAST);
		getContentPane().add(tweetPanel, BorderLayout.NORTH);
	}

	public void timeLinePanel(){
		
		timeLinePanel = new JPanel(new BorderLayout());
		timeLinePanel.setSize(500, 600);
//		refreshButton = new JButton("Refresh");
//		refreshButton.setSize(90, 20);
//		refreshButton.addActionListener(this);
//		timeLinePanel.add(refreshButton, BorderLayout.NORTH);
//		timeLinePanel.add(tweetsTimeLine.getTimeLinePanel(), BorderLayout.SOUTH);
		timeLinePanel.add(usersTimeLine.getTimeLinePanel(), BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane(timeLinePanel,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, // vertical bar
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setSize(500, 600);
		//jPanel1.add(scrollPane);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		timeLinePanel.validate();
		validate();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == tweetButton){
			try {
				Status status = twitter.updateStatus(tweetText.getText());
				tweetText.setText("");
			} catch (TwitterException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
//		else if (e.getSource() == refreshButton){
//			try {
//				tweetsTimeLine.updateTimePanel();
//				timeLinePanel.remove(1);
//				timeLinePanel.validate();
//				timeLinePanel.add(tweetsTimeLine.getTimeLinePanel());
//				timeLinePanel.validate();
//				
//			} catch (TwitterException e1) {
//			}
//		}
	}

}
