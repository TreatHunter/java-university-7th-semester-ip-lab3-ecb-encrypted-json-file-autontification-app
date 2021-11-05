package org_treathunter.java_university_7th_semester_ip_lab1_json_file_autontification_app.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;

public class DataBasePasswordDialog extends JDialog 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPasswordField pfPassword;
    private JLabel lbPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private boolean loginAttempt = false;

    public DataBasePasswordDialog(Frame parent) 
    {
        super(parent, "Ввод пароля от базы данных", true);
        //
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        lbPassword = new JLabel("Пароль от бд: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);

        pfPassword = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(pfPassword, cs);

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

    public String getPassword() {
        return new String(pfPassword.getPassword());
    }

    public boolean isLoginAttempt() 
    {
        return loginAttempt;
    }
}
