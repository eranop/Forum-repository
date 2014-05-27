package GUI;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

	public class FORUMWINDOWBEFORELOGINTEMP extends JFrame{
		private String fn;
		private JLabel FORUM;
		private JButton button1;
		private JScrollPane scrollPane1;
		private JList list1;
		private JLabel label1;
		private JLabel label2;
		private JButton button2;
		private JLabel label3;
		private JTextField user;
		private JPasswordField passwordField1;
		private JButton button3;
		private JButton button4;
	
		public FORUMWINDOWBEFORELOGINTEMP(String fn){
			this.fn=fn;
			FORUM = new JLabel();
			button1 = new JButton();
			scrollPane1 = new JScrollPane();
			String[] sub_forums = CONNECTION.getSubsOfForum(this.fn);
			list1 = new JList(sub_forums);
			label1 = new JLabel();
			label2 = new JLabel();
			button2 = new JButton();
			label3 = new JLabel();
			user = new JTextField();
			passwordField1 = new JPasswordField();
			button3 = new JButton();
			button4 = new JButton();

			//======== this ========
			Container contentPane = getContentPane();
			contentPane.setLayout(null);

			//---- FORUM ----
			FORUM.setText(fn);
			FORUM.setFont(FORUM.getFont().deriveFont(FORUM.getFont().getSize() + 20f));
			contentPane.add(FORUM);
			FORUM.setBounds(275, 25, 140, 55);

			//---- button1 ----
			button1.setText("ENTRANCE");
			contentPane.add(button1);
			button1.setBounds(275, 305, 120, 33);

			//======== scrollPane1 ========
			{
				scrollPane1.setViewportView(list1);
			}
			contentPane.add(scrollPane1);
			scrollPane1.setBounds(205, 125, 250, scrollPane1.getPreferredSize().height);

			//---- label1 ----
			label1.setText("PLEASE SELECT A SUB-FORUM:");
			contentPane.add(label1);
			label1.setBounds(205, 95, 230, 25);

			//---- label2 ----
			label2.setText("HI GUEST,");
			contentPane.add(label2);
			label2.setBounds(520, 70, 78, 46);

			//---- button2 ----
			button2.setText("REGISTRATION");
			contentPane.add(button2);
			button2.setBounds(480, 125, 130, 30);

			//---- label3 ----
			label3.setText("LOGIN:");
			contentPane.add(label3);
			label3.setBounds(485, 155, 78, 46);
			contentPane.add(user);
			user.setBounds(485, 190, 90, 30);
			contentPane.add(passwordField1);
			passwordField1.setBounds(485, 220, 90, 35);

			//---- button3 ----
			button3.setText("forgotten password?");
			contentPane.add(button3);
			button3.setBounds(475, 300, 150, 35);

			//---- button4 ----
			button4.setText("login");
			contentPane.add(button4);
			button4.setBounds(485, 255, 90, button4.getPreferredSize().height);

			{ // compute preferred size
				Dimension preferredSize = new Dimension();
				for(int i = 0; i < contentPane.getComponentCount(); i++) {
					Rectangle bounds = contentPane.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
				}
				Insets insets = contentPane.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				contentPane.setMinimumSize(preferredSize);
				contentPane.setPreferredSize(preferredSize);
			}
			
			
			ActionListener register = new ActionListener(){
				public void actionPerformed(ActionEvent e){
					REGISTRATIONTEMP rg = new REGISTRATIONTEMP();
				}
			};
			
			button2.addActionListener(register);
			
			ActionListener login = new ActionListener(){
				public void actionPerformed(ActionEvent e){
					String name = user.getText();
					String pass = passwordField1.getText();
					boolean login_constraint=LOGINCONSTRAINTS.loginValidation(name,pass);
					System.out.println("LOGIN IS COSTRAINED");
					if (login_constraint){
						FORUMWINDOWAFTERLOGIN FA = new FORUMWINDOWAFTERLOGIN(getFName(), name);
						dispose();
					}
				}
			};
			
			ActionListener forgot = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					forgottenpasswordmediator fm = new forgottenpasswordmediator();					
				}
			};
			button3.addActionListener(forgot);
			
			
			
			
			
			button4.addActionListener(login);
			
			pack();
			setLocationRelativeTo(getOwner());
			setVisible(true);
		}
		
		public String getFName(){
			return this.fn;
		}

		
	}

	
	

