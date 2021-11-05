package org_treathunter.java_university_7th_semester_ip_lab1_json_file_autontification_app.ui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org_treathunter.java_university_7th_semester_ip_lab1_json_file_autontification_app.DataBaseSystem.DatabaseSystem;
import org_treathunter.java_university_7th_semester_ip_lab1_json_file_autontification_app.file_json_storage_system.User;

public class MainFrame 
{
	JFrame fr;
	JButton loginButton;
	DatabaseSystem db;
	JMenu usersMenu;
	JMenuItem passwordChange ;
	JMenuItem addUser;
	JMenuItem showUsers;
	JMenuItem exit;	
	User currientUser = null;
	
	public MainFrame() throws Exception
	{

		fr = new JFrame("Лабораторная 1");
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = fr.getContentPane();
		pane.setLayout(new BoxLayout(pane,BoxLayout.X_AXIS));
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(createUsersMenu());
		usersMenu.setVisible(false);
		menuBar.add(createReferenceMenu());
		
		loginButton = new JButton("Вход в систему");
		//button action listener
		loginButton.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) {
						User user = null;
						while(user == null)
						{
							UsernameDialog usernameDlg = new UsernameDialog(fr);
							usernameDlg.setVisible(true);
	                        if(usernameDlg.isLoginAttempt())
	                        {
	                        	user = db.getUserByUsername(usernameDlg.getUsername());
	                        	if(user == null)
	                        	{
	                        		JOptionPane.showMessageDialog(fr,
		                                    "Такого пользователя не существует",
		                                    "Пользователь не найден",
		                                    JOptionPane.ERROR_MESSAGE);
	                        	}
	                        	else if(user.isBanned())
	                        	{
	                        		JOptionPane.showMessageDialog(fr,
		                                    "Пользователь забанен",
		                                    "Пользователь забанен",
		                                    JOptionPane.ERROR_MESSAGE);	
	                        		user = null;
	                        	}
	                        }
	                        else 
	                        {
	                        	return;
	                        }        
						}
						if(user.getPassword().length() == 0 | !user.isPasswordFollowsPasswordRestrictionsOfUser(user.getPassword()))
						{
							while(user.getPassword().length() == 0 | !user.isPasswordFollowsPasswordRestrictionsOfUser(user.getPassword()))
							{
								PasswordChangeDialog pswrdChngDlg = new PasswordChangeDialog(fr);
								pswrdChngDlg.setVisible(true);
		                        if(pswrdChngDlg.isLoginAttempt())
		                        {
		                        	String password = pswrdChngDlg.getPassword();
		                        	String passwordConfirm = pswrdChngDlg.getPasswordConfirm();
		                           	if(!password.equals(passwordConfirm))
		                        	{
		                        		JOptionPane.showMessageDialog(fr,
		                                        "Пароли не совпадают",
		                                        "Пароли не совпадают",
		                                        JOptionPane.ERROR_MESSAGE);
		                        	}
		                        	else if(password.length() == 0)
		                        	{
		                        		JOptionPane.showMessageDialog(fr,
		                                        "Пароль не может быть пустым",
		                                        "Пароль не может быть пустым",
		                                        JOptionPane.ERROR_MESSAGE);            		
		                        	}
		                        	else if(!user.isPasswordFollowsPasswordRestrictionsOfUser(password))
		                        	{
		                        		JOptionPane.showMessageDialog(fr,
		                        				"Пароль должен содержать числа и знаки / * + -",
		                        				"Пароль не cовпадает по требованиям",
		                                        
		                                        JOptionPane.ERROR_MESSAGE);              		
		                        	}else
		                        	{
		                        		user.setPassword(password);
		                        	}
		                        }
		                        else 
		                        {
		                        	return; 
		                        }  								
							}
							try {
								db.SaveChangesToFile();
								return;
							} catch (Exception ex) {
								JOptionPane.showMessageDialog(new JFrame(), "Exception: "+ ex.getMessage());
								System.out.println(ex.getMessage());
								System.exit(0);
							}
						}
						else 
						{
							int atemtNumber = 3;	
							do
							{
								PasswordDialog pswrdDlg = new PasswordDialog(fr);
								pswrdDlg.setVisible(true);
								try {
									Thread.sleep(100);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
		                        if(pswrdDlg.isLoginAttempt())
		                        {
		                        	if(user.getPassword().equals(pswrdDlg.getPassword()))
		                        	{
		                        		break;
		                        	}else {
										atemtNumber--;
										if(atemtNumber == 0)
										{
											return;
										}
		                        		JOptionPane.showMessageDialog(fr,
			                                    "Не правильный пароль. Осталось попыток: " + atemtNumber,
			                                    "Не правильный пароль",
			                                    JOptionPane.ERROR_MESSAGE);
		                        	}
		                        }
		                        else 
		                        {
		                        	return; 
		                        }  
							}while(atemtNumber != 0);
						}
                		JOptionPane.showMessageDialog(fr,
                                user.getUsername()+", Вы вошли в систему",
                                "Успешный вход",
                                JOptionPane.INFORMATION_MESSAGE
                                );
                		currientUser = user;
                		loginButton.setVisible(false);
                		usersMenu.setVisible(true);
                		if(user.getRole().equals(User.Role.user))
                		{
                			passwordChange.setVisible(true);
                			addUser.setVisible(false);
                			showUsers.setVisible(false);
                			exit.setVisible(true);
                		}else {
                			passwordChange.setVisible(true);
                			addUser.setVisible(true);
                			showUsers.setVisible(true);
                			exit.setVisible(true);
                		}
						
					}
				});
		loginButton.setPreferredSize(new Dimension(100,50));
		pane.add(Box.createRigidArea(new Dimension(500, 0)));
		pane.add(loginButton);

		
		fr.setJMenuBar(menuBar);
		fr.setSize(1280,720);
		fr.setVisible(true);	
		initDatabase();
	}

	private JMenu createUsersMenu()
	{
		usersMenu = new JMenu("Пользователи");
		
		passwordChange = new JMenuItem("Смена пароля");
		addUser = new JMenuItem("Добавить пользователя");
		showUsers = new JMenuItem("Показать,настроить пользователей");
		exit = new JMenuItem("Выход");
		
		//action listeners
		showUsers.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						UsersTableDialog tableDialog = new UsersTableDialog(fr,db);
						tableDialog.setVisible(true);
						
					}
				});
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				currientUser = null;
				usersMenu.setVisible(false);
				loginButton.setVisible(true);
			}
		});
		
		addUser.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						do
						{
							AddUserDialog addUserDlg = new AddUserDialog(fr);
							addUserDlg.setVisible(true);
							if(!addUserDlg.isLoginAttempt())
							{
								return;
							}
							if(db.getUserByUsername(addUserDlg.getUsername()) == null )
							{
								db.getUsers().add(new User(addUserDlg.getUsername(),"",User.Role.user,false,addUserDlg.getPasswordRestrictions()));
								try {
									db.SaveChangesToFile();
								} catch (Exception e1) {
									JOptionPane.showMessageDialog(new JFrame(), "Exception: "+ e1.getMessage());
									System.out.println(e1.getMessage());
									return;
								}
		                		JOptionPane.showMessageDialog(fr,
		                                "Пользователь добавлен",
		                                "Успешное добавление",
		                                JOptionPane.INFORMATION_MESSAGE
		                                );
							}
							else
							{
                        		JOptionPane.showMessageDialog(fr,
	                                    "Это имя пользователя уже занято",
	                                    "Ошибка",
	                                    JOptionPane.ERROR_MESSAGE);
							}
						}while(true);
					}
				});
		
		passwordChange.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{	
						do
						{
							PasswordDialog pswrdDlg = new PasswordDialog(fr);
							pswrdDlg.setVisible(true);
							try {
								Thread.sleep(100);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
	                        if(pswrdDlg.isLoginAttempt())
	                        {
	                        	if(currientUser.getPassword().equals(pswrdDlg.getPassword()))
	                        	{
	                        		break;
	                        	}else {

	                        		JOptionPane.showMessageDialog(fr,
		                                    "Не правильный пароль",
		                                    "Не правильный пароль",
		                                    JOptionPane.ERROR_MESSAGE);
	                        	}
	                        }
	                        else 
	                        {
	                        	return; 
	                        }  
						}while(true);	
						
						String newPassword = "";

						while(newPassword.length() == 0 | !currientUser.isPasswordFollowsPasswordRestrictionsOfUser(newPassword))
						{
							PasswordChangeDialog pswrdChngDlg = new PasswordChangeDialog(fr);
							pswrdChngDlg.setVisible(true);
		                    if(!pswrdChngDlg.isLoginAttempt())
		                    {
		                        return; 
		                    }else
		                    {
		                       	String password = pswrdChngDlg.getPassword();
		                       	String passwordConfirm = pswrdChngDlg.getPasswordConfirm();
		                       	if(!password.equals(passwordConfirm))
		                       	{
		                       		JOptionPane.showMessageDialog(fr,
		                                     "Пароли не совпадают",
		                                     "Пароли не совпадают",
		                                     JOptionPane.ERROR_MESSAGE);
		                       	}
		                       	else if(password.length() == 0)
		                       	{
		                       		JOptionPane.showMessageDialog(fr,
		                                     "Пароль не может быть пустым",
		                                     "Пароль не может быть пустым",
		                                     JOptionPane.ERROR_MESSAGE);            		
		                        }
		                       	else if(!currientUser.isPasswordFollowsPasswordRestrictionsOfUser(password))
		                       	{
		                       		JOptionPane.showMessageDialog(fr,
		                       				"Пароль должен содержать числа и знаки / * + -",
		                       				"Пароль не cовпадает по требованиям",                      
		                                    JOptionPane.ERROR_MESSAGE);              		
		                        }else
		                       	{
		                       		newPassword = password;
		                       	}
		                    }
						}
						try {
							currientUser.setPassword(newPassword);
							db.SaveChangesToFile();
	                		JOptionPane.showMessageDialog(fr,
	                                "Пароль изменён",
	                                "Пароль изменён",
	                                JOptionPane.INFORMATION_MESSAGE
	                                );							
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(new JFrame(), "Exception: "+ ex.getMessage());
							System.out.println(ex.getMessage());
							System.exit(0);
												
						}
					}
				});
		
		usersMenu.add(passwordChange);
		usersMenu.add(addUser);
		usersMenu.add(showUsers);
		usersMenu.add(exit);
		return usersMenu;
	}
	
	private JMenu createReferenceMenu()
	{
		JMenu reference = new JMenu("Справка");
		
		JMenuItem aboutProgram = new JMenuItem("О прогамме");
		
		//action listeners
		aboutProgram.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						InfoDialog info = new InfoDialog(fr);
						info.setVisible(true);
					}
				});
		
		reference.add(aboutProgram);
		return reference;
	}

	private void initDatabase() throws Exception 
	{
		db = new DatabaseSystem();
		if(db.isExistDatabse())
		{
			DataBasePasswordDialog pswrdDlg = new DataBasePasswordDialog(fr);
			pswrdDlg.setVisible(true);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            if(pswrdDlg.isLoginAttempt())
            {
            	db.setKey(pswrdDlg.getPassword());
            }
            else 
            {
            	System.exit(0);
            }  
			
		}else
		{
			String newPassword = "";

			while(newPassword.length() == 0)
			{
				DatabasePasswordSetDialog pswrdChngDlg = new DatabasePasswordSetDialog(fr);
				pswrdChngDlg.setVisible(true);
                if(!pswrdChngDlg.isLoginAttempt())
                {
                    System.exit(0);
                }else
                {
                   	String password = pswrdChngDlg.getPassword();
                   	String passwordConfirm = pswrdChngDlg.getPasswordConfirm();
                   	if(!password.equals(passwordConfirm))
                   	{
                   		JOptionPane.showMessageDialog(fr,
                                 "Пароли не совпадают",
                                 "Пароли не совпадают",
                                 JOptionPane.ERROR_MESSAGE);
                   	}
                   	else if(password.length() == 0)
                   	{
                   		JOptionPane.showMessageDialog(fr,
                                 "Пароль не может быть пустым",
                                 "Пароль не может быть пустым",
                                 JOptionPane.ERROR_MESSAGE);            		
                    }else
                   	{
                   		newPassword = password;
                   	}
                }
			}
			db.CreateDataBase(newPassword);
		}
	}
}