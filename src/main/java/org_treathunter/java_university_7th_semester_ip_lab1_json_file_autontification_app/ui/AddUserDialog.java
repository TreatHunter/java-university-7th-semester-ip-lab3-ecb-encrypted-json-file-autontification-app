package org_treathunter.java_university_7th_semester_ip_lab1_json_file_autontification_app.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class AddUserDialog extends JDialog
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfUsername;
    private JLabel lbUsername;
    private JLabel lbPasswordRestrictions;
    private JCheckBox chPasswordRestrictions;
    private JButton btnLogin;
    private JButton btnCancel;
    private boolean loginAttempt = false;

    public AddUserDialog(Frame parent) 
    {
        super(parent, "Создать нового пользователя", true);
        //
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        lbUsername = new JLabel("Имя пользователя: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbUsername, cs);

        tfUsername = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(tfUsername, cs);
        
        lbPasswordRestrictions = new JLabel("Парольные ограничения: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbPasswordRestrictions, cs);

        chPasswordRestrictions = new JCheckBox();
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(chPasswordRestrictions, cs);        

        panel.setBorder(new LineBorder(Color.GRAY));

        btnLogin = new JButton("Вход");

        btnLogin.addActionListener(new ActionListener() 
        {

            public void actionPerformed(ActionEvent e) 
            {
            	loginAttempt = true;
                dispose();
            }
        });
        btnCancel = new JButton("Отмена");
        btnCancel.addActionListener(new ActionListener() 
        {

            public void actionPerformed(ActionEvent e) 
            {
            	loginAttempt = false;
                dispose();
            }
        });
        JPanel bp = new JPanel();
        bp.add(btnLogin);
        bp.add(btnCancel);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    public String getUsername() 
    {
        return tfUsername.getText().trim();
    }
    
    public boolean getPasswordRestrictions()
    {
    	return chPasswordRestrictions.isSelected();
    }
    
    public boolean isLoginAttempt() 
    {
        return loginAttempt;
    }
}
