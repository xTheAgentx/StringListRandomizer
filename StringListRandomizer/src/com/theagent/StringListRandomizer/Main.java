package com.theagent.StringListRandomizer;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Cursor;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Dimension;

public class Main {

	private JFrame frmStringlistrandomizerBytheagent;

	private String content; // file content OR randomized content

	private JButton btnSelectFile;
	private JButton btnRandomize;
	private JButton btnSave;
	private JButton btnShowOutput;

	private JLabel lblWordCount;

	private JPanel panelOpen;
	private JPanel panelRandomize;
	private JPanel panelStatus;
	private JPanel panelOutput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmStringlistrandomizerBytheagent.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmStringlistrandomizerBytheagent = new JFrame();
		frmStringlistrandomizerBytheagent.setResizable(false);
		frmStringlistrandomizerBytheagent.setMaximumSize(new Dimension(500, 800));
		frmStringlistrandomizerBytheagent.setMinimumSize(new Dimension(200, 300));
		frmStringlistrandomizerBytheagent.setTitle("StringListRandomizer by _The_Agent_");
		frmStringlistrandomizerBytheagent.setBounds(100, 100, 300, 500);
		frmStringlistrandomizerBytheagent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStringlistrandomizerBytheagent.getContentPane().setLayout(new GridLayout(4, 1, 0, 0));

		panelOpen = new JPanel();
		panelOpen.setBorder(new TitledBorder(null, "1. Step", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmStringlistrandomizerBytheagent.getContentPane().add(panelOpen);
		panelOpen.setLayout(new GridLayout(1, 1, 5, 0));

		btnSelectFile = new JButton("Select File");
		panelOpen.add(btnSelectFile);
		btnSelectFile.setFocusable(false);
		btnSelectFile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSelectFile.setToolTipText(
				"<html>\r\n\t<p><b>Format (.txt):</b></p>\r\n\t<p>\r\n\t\tItem one<br/>\r\n\t\tItem two<br/>\r\n\t\tItem three<br/>\r\n\t\t...\r\n\t</p>\r\n</html>");
		btnSelectFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileOperations fo = new FileOperations();
				content = fo.openFile(); // open txt file
				activateButtons();
			}
		});

		panelRandomize = new JPanel();
		panelRandomize.setBorder(new TitledBorder(null, "2. Step", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmStringlistrandomizerBytheagent.getContentPane().add(panelRandomize);
		panelRandomize.setLayout(new GridLayout(0, 1, 0, 0));

		btnRandomize = new JButton("Let's shuffle!");
		panelRandomize.add(btnRandomize);
		btnRandomize.setFocusable(false);
		btnRandomize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Randomizer random = new Randomizer();
				content = random.randomize(content); // randomize content
				lblWordCount.setText("Processed " + random.count() + " lines"); // print number of processed lines
			}
		});
		btnRandomize.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRandomize.setBackground(Color.RED);
		btnRandomize.setEnabled(false);

		panelStatus = new JPanel();
		panelStatus.setBorder(new TitledBorder(null, "Status", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmStringlistrandomizerBytheagent.getContentPane().add(panelStatus);
		panelStatus.setLayout(new GridLayout(1, 1, 5, 0));

		lblWordCount = new JLabel("Waiting...");
		panelStatus.add(lblWordCount);
		lblWordCount.setFocusable(false);
		lblWordCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblWordCount.setFont(new Font("Tahoma", Font.PLAIN, 20));

		panelOutput = new JPanel();
		panelOutput.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"3. Step(s)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		frmStringlistrandomizerBytheagent.getContentPane().add(panelOutput);
		panelOutput.setLayout(new GridLayout(2, 1, 5, 0));

		btnShowOutput = new JButton("Show result");
		btnShowOutput.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnShowOutput.setFocusable(false);
		panelOutput.add(btnShowOutput);
		btnShowOutput.setEnabled(false);

		btnSave = new JButton("Save");
		btnSave.setFocusable(false);
		btnSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelOutput.add(btnSave);
		btnSave.setEnabled(false);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileOperations fo = new FileOperations();
				fo.saveFile(content); // export results to a txt file
			}
		});
		btnSave.setToolTipText("Save as .txt");
		btnShowOutput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OutputWindow ow = new OutputWindow(content);
				ow.setVisible(true); // show new window with the results
			}
		});
	}

	/**
	 * Activates all the other functions if a file was loaded correctly.
	 */
	private void activateButtons() {
		if (content != null) {
			btnSelectFile.setText("File selected");
			btnRandomize.setBackground(Color.GREEN);
			btnRandomize.setEnabled(true);
			btnShowOutput.setEnabled(true);
			btnSave.setEnabled(true);
		} else {
			btnSelectFile.setText("Select File");
			btnSelectFile.setForeground(Color.BLACK);
			btnRandomize.setBackground(Color.RED);
			btnRandomize.setEnabled(false);
			btnShowOutput.setEnabled(false);
			btnSave.setEnabled(false);
		}
	}

}
