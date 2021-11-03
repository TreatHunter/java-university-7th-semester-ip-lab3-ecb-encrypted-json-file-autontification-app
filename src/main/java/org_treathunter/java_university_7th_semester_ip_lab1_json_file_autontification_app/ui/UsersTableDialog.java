package org_treathunter.java_university_7th_semester_ip_lab1_json_file_autontification_app.ui;

import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org_treathunter.java_university_7th_semester_ip_lab1_json_file_autontification_app.DataBaseSystem.DatabaseSystem;

public class UsersTableDialog extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DatabaseSystem db;
	
    public UsersTableDialog(Frame parent, DatabaseSystem db) 
    {
        super(parent, "Cписок пользователей", true);
        this.db = db;
        UsersTableModel model = new UsersTableModel(db);
        JTable usersTable = new JTable(model);
        getContentPane().add(new JScrollPane(usersTable));
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
