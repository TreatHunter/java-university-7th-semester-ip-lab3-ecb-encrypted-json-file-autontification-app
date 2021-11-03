package org_treathunter.java_university_7th_semester_ip_lab1_json_file_autontification_app.ui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoDialog  extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btnLogin;


    public InfoDialog(Frame parent) {
        super(parent, "Информация", true);
        //
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        lbUsername = new JLabel("Студент: Занин М.А ИДБ-18-02 ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbUsername, cs);


        lbPassword = new JLabel("Вариант: 7 Наличие цифр и знаков арифметических операций. ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);


        btnLogin = new JButton("Ок");

        btnLogin.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JPanel bp = new JPanel();
        bp.add(btnLogin);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }
}
