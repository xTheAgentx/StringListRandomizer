package com.theagent.StringListRandomizer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;

public class OutputWindow extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
	private JLabel lblSaveStatus;

	/**
	 * Create the dialog.
	 */
	public OutputWindow(String content) {
		setModal(true);
		setTitle("Output");
		setBounds(100, 100, 400, 800);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane);
			{
				JTextArea textArea = new JTextArea();
				scrollPane.setViewportView(textArea);
				textArea.setEditable(false);
				textArea.setFont(new Font("Consolas", Font.PLAIN, 20));
				textArea.setText(content);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton saveButton = new JButton("Save");
				saveButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						FileOperations fo = new FileOperations();
						if (fo.saveFile(content)) {
							lblSaveStatus.setText("File saved!");
							lblSaveStatus.setForeground(Color.GREEN);
						} else {
							lblSaveStatus.setText("File not saved!");
							lblSaveStatus.setForeground(Color.RED);
						}
					}
				});
				{
					lblSaveStatus = new JLabel("");
					lblSaveStatus.setForeground(Color.GREEN);
					buttonPane.add(lblSaveStatus);
				}
				saveButton.setToolTipText("Save as .txt");
				saveButton.setActionCommand("OK");
				buttonPane.add(saveButton);
				getRootPane().setDefaultButton(saveButton);
			}
			{
				JButton closeButton = new JButton("Close");
				closeButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				closeButton.setActionCommand("Cancel");
				buttonPane.add(closeButton);
			}
		}
	}

}
