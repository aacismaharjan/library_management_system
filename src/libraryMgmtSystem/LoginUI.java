package libraryMgmtSystem;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginUI{
	JTextField userNameField;
	JTextField passwordField;
	public LoginUI() {
		JFrame jf = new JFrame("Sign In");
		JPanel jp = new JPanel();
		
		
		BorderLayout mainLayout = new BorderLayout();
		GridLayout gl = new GridLayout(2,2,10,10);
		JPanel formPanel = new JPanel(gl);
		FlowLayout buttonGrpLayout = new FlowLayout(FlowLayout.CENTER, 20, 20);
		JPanel actionPanel = new JPanel(buttonGrpLayout);
		
		
		jf.setLocationRelativeTo(null);
		
		jp.setLayout(mainLayout);
		
		 
		//creating components	
		JLabel jl = new JLabel("User Name : ");
		JLabel jl3 = new JLabel("Password : ");
		userNameField = new JTextField(10);
		passwordField = new JPasswordField(10);
		
		
		JButton signBtn=new JButton("Sign in");
		JButton cancelBtn = new JButton("Cancel");
		JButton registerBtn=new JButton("Register");
		
		
		JPanel btnGrpPanel = new JPanel(buttonGrpLayout);
		
		btnGrpPanel.add(signBtn);
		btnGrpPanel.add(cancelBtn);
		btnGrpPanel.add(registerBtn);
		
		
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
			}
		});
		
		
		
		//while adding components to container order is important
		formPanel.add(jl); //label username [0,0] (row,column) position
		formPanel.add(userNameField); //textfield username [0,1] (row,column) position
		formPanel.add(jl3);//label password [1,0] (row,column) position
		formPanel.add(passwordField);  //textfield password [1,1] (row,column) position
		
		actionPanel.add(btnGrpPanel);
		
		formPanel.setPreferredSize(new Dimension(400, 80));
		
		jp.add(formPanel, BorderLayout.NORTH);
		jp.add(actionPanel, BorderLayout.CENTER);
		jp.setBorder(new EmptyBorder(10, 10, 10, 10));
		actionPanel.setPreferredSize(new Dimension(400, 50));
		
	
		
		signBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String userName;
				String password;
			
				userName= userNameField.getText().toString();
				password= passwordField.getText().toString();
				
				//read registerd username and password
				String uName;
				String pass;
				
				String line = null;
				try {
					BufferedReader reader = new BufferedReader(new FileReader("login.txt"));
					line =reader.readLine();
					boolean b=false;
					while(line!=null) {
						String credintials[] = line.split("\t");
						uName=credintials[0];
						pass=credintials[1];
						if(userName.equals(uName) && password.equals(pass)) {
							b=true;
							break;					
						}					
						line =reader.readLine();
					}
					if(b) {
						DashBoard db = new DashBoard();
						jf.dispose();
						JOptionPane.showMessageDialog(null,"username and password matched","Status",JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(null,"username and password didn't match","Status",JOptionPane.INFORMATION_MESSAGE);
					}
							
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
				
			}
			
		});
		registerBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String userName;
				String password;
					try {
						Writer writer = new FileWriter("login.txt",false);
						userName= userNameField.getText().toString();
						password= passwordField.getText().toString();
						//write username and password to a file
						writer.write(userName+"\t"+password+"\n");
						writer.flush();
						writer.close();
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	 
			}
		});
		
		//jf.pack();
		jf.setContentPane(jp);
		jf.setSize(new Dimension(400, 220)); //setting size of the from to be displayed
		jf.setVisible(true); //enabling the jframe to be displayed
		
	}
	

}