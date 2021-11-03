package org_treathunter.java_university_7th_semester_ip_lab1_json_file_autontification_app;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org_treathunter.java_university_7th_semester_ip_lab1_json_file_autontification_app.ui.MainFrame;

public class Main {
	public static void main(String[] args) 
	{
		try
		{
			new MainFrame();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), "Exception: "+ e.getMessage());
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
	}
}
