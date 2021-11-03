package org_treathunter.java_university_7th_semester_ip_lab1_json_file_autontification_app.ui;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import org_treathunter.java_university_7th_semester_ip_lab1_json_file_autontification_app.DataBaseSystem.DatabaseSystem;
import org_treathunter.java_university_7th_semester_ip_lab1_json_file_autontification_app.file_json_storage_system.User;

public class UsersTableModel extends AbstractTableModel
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DatabaseSystem db;
     
    private final String[] columnNames = new String[] {
            "Имя пользователя", "Ограничение на пароль", "Блокирован"
    };
    private final Class[] columnClass = new Class[] {
        String.class, Boolean.class, Boolean.class
    };
 
    public UsersTableModel(DatabaseSystem db)
    {
        this.db = db;
    }
     
    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }
 
    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return columnClass[columnIndex];
    }
 
    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }
 
    @Override
    public int getRowCount()
    {
        return db.getUsers().size();
    }
 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        User user = db.getUsers().get(rowIndex);
        if(0 == columnIndex) {
            return user.getUsername();
        }
        else if(1 == columnIndex) {
            return user.isPasswordRestictions();
        }
        else if(2 == columnIndex) {
            return user.isBanned();
        }
        return null;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        User user = db.getUsers().get(row);
		if(user.getRole() == User.Role.admin)
			   return;
			if(col == 1)
			{
				user.setPasswordRestictions(!user.isPasswordRestictions());
			}else if(col == 2){
				user.setBanned(!user.isBanned());
			}
			try {
				db.SaveChangesToFile();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(new JFrame(), "Exception: "+ e1.getMessage());
				System.out.println(e1.getMessage());
			}
        fireTableCellUpdated(row, col);
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
    	if(columnIndex > 0) {
    		return true;
    	}
    	else
    		return false;
    }
}
