package org_treathunter.java_university_7th_semester_ip_lab1_json_file_autontification_app.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;

public class PasswordChangeDialog extends JDialog
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private JPasswordField pfPassword;
    private JPasswordField pfPasswordConfirm;
    private JLabel lbPassword;
    private JLabel lbPasswordConfirm;
    private JButton btnLogin;
    private JButton btnCancel;
    private boolean loginAttempt = false;
    
    public PasswordChangeDialog(Frame parent) 
    {
        super(parent, "Ввод нового пароля", true);
        //
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        lbPassword = new JLabel("Пароль: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);

        pfPassword = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(pfPassword, cs);

        lbPasswordConfirm = new JLabel("Подтверждение: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbPasswordConfirm, cs);

        pfPasswordConfirm = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(pfPasswordConfirm, cs);
        panel.setBorder(new LineBorder(Color.GRAY));

        btnLogin = new JButton("Принять");

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

    public String getPassword() 
    {
        return new String(pfPassword.getPassword());
    }    
    
    public String getPasswordConfirm() 
    {
        return new String(pfPasswordConfirm.getPassword());
    }

    public boolean isLoginAttempt() 
    {
        return loginAttempt;
    }
}
