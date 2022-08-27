package com.theagent.StringListRandomizer;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Cursor;

public class Main {

	private JFrame frmStringlistrandomizerBytheagent;
	private Path selectedFile;
	private String content;
	private int wordCount;

	private JButton btnSelectFile;
	private JButton btnRandomize;
	private JLabel lblWordCount;

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
		frmStringlistrandomizerBytheagent.setTitle("StringListRandomizer by _The_Agent_");
		frmStringlistrandomizerBytheagent.setBounds(100, 100, 400, 300);
		frmStringlistrandomizerBytheagent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		btnSelectFile = new JButton("Select File");
		btnSelectFile.setFocusable(false);
		btnSelectFile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSelectFile.setToolTipText(
				"<html>\r\n\t<p><b>Format (.txt):</b></p>\r\n\t<p>\r\n\t\tItem one<br/>\r\n\t\tItem two<br/>\r\n\t\tItem three<br/>\r\n\t\t...\r\n\t</p>\r\n</html>");
		btnSelectFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
				fileChooser.setFileFilter(filter);
				fileChooser.setDialogTitle("Select list file (.txt)");

				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					selectedFile = Path.of(fileChooser.getSelectedFile().getAbsolutePath());
					try {
						content = Files.readString(selectedFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				if (content != null) {
					btnSelectFile.setText("File selected");
					btnSelectFile.setForeground(Color.GREEN);
					btnRandomize.setBackground(Color.GREEN);
					btnRandomize.setEnabled(true);
				} else {

					btnSelectFile.setText("Select File");
					btnSelectFile.setForeground(Color.BLACK);
					btnRandomize.setBackground(Color.RED);
					btnRandomize.setEnabled(false);
				}
			}
		});
		frmStringlistrandomizerBytheagent.getContentPane().setLayout(new GridLayout(3, 1, 0, 0));
		frmStringlistrandomizerBytheagent.getContentPane().add(btnSelectFile);

		btnRandomize = new JButton("Let's shuffle!");
		btnRandomize.setFocusable(false);
		btnRandomize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Randomizer random = new Randomizer(content);
				content = random.getList();
				wordCount = random.getNumberOfWords();

				JFileChooser fileChooser = new JFileChooser("C:\\%USER%\\Desktop\\");

				FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
				fileChooser.setFileFilter(filter);
				fileChooser.setDialogTitle("Save randomized list file (.txt)");
				File newFile;
				if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					newFile = fileChooser.getSelectedFile();

					try {
						FileWriter fw = new FileWriter(newFile);
						fw.write(content);
						fw.flush();
						fw.close();
						lblWordCount.setText("Processed " + wordCount + " lines");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		btnRandomize.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRandomize.setBackground(Color.RED);
		btnRandomize.setEnabled(false);
		frmStringlistrandomizerBytheagent.getContentPane().add(btnRandomize);

		lblWordCount = new JLabel("Waiting...");
		lblWordCount.setFocusable(false);
		lblWordCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblWordCount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frmStringlistrandomizerBytheagent.getContentPane().add(lblWordCount);
	}

}
