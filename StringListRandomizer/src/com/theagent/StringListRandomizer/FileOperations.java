package com.theagent.StringListRandomizer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileOperations {
	
	private Path selectedFile;
	
	public String openFile() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt"); // only txt files can be opened
		fileChooser.setFileFilter(filter);
		fileChooser.setDialogTitle("Select list file (.txt)");

		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			selectedFile = Path.of(fileChooser.getSelectedFile().getAbsolutePath());
			try {
				return Files.readString(selectedFile);
			} catch (IOException e1) {
				System.out.println("[Error] Something went wrong while trying to open this file!");
				e1.printStackTrace();
			}
		}
		
		return null; // if no file was chosen
	}
	
	public boolean saveFile(String content) {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt"); // can only be saved as txt file
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
				return true;
			} catch (IOException e1) {
				System.out.println("[Error] Something went wrong while trying to save the file!");
				e1.printStackTrace();
			}
		}
		return false;
	}
	
}
