package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

	public class REGISTRATIONTEMP extends JFrame{
		private JLabel label1;
		private JLabel label2;
		private JTextField usernametext;
		private JLabel label3;
		private JPasswordField passwordField1;
		private JLabel label4;
		private JPasswordField passwordField2;
		private JTextField mailtextfield;
		private JLabel label5;
		private JLabel label6;
		private JLabel label7;
		private JLabel label8;
		private JLabel label9;
		private JButton button1;
		private JLabel label10;
		private JLabel systemstat;
		private JTextField ans1textfield;
		private JTextField ans2textfield;
		private JScrollPane scrollPane1;
		private JList list1;
		private JScrollPane scrollPane2;
		private JList list2;
		
		public REGISTRATIONTEMP(){
			label1 = new JLabel();
			label2 = new JLabel();
			usernametext = new JTextField();
			label3 = new JLabel();
			passwordField1 = new JPasswordField();
			label4 = new JLabel();
			passwordField2 = new JPasswordField();
			mailtextfield = new JTextField();
			label5 = new JLabel();
			label6 = new JLabel();
			label7 = new JLabel();
			label8 = new JLabel();
			label9 = new JLabel();
			button1 = new JButton();
			label10 = new JLabel();
			systemstat = new JLabel();
			ans1textfield = new JTextField();
			ans2textfield = new JTextField();
			scrollPane1 = new JScrollPane();
			list1 = new JList();
			scrollPane2 = new JScrollPane();
			list2 = new JList();

			//======== this ========
			Container contentPane = getContentPane();
			contentPane.setLayout(null);

			//---- label1 ----
			label1.setText("REGISTRATION");
			label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 11f));
			contentPane.add(label1);
			label1.setBounds(110, 5, 190, 35);

			//---- label2 ----
			label2.setText("USERNAME");
			label2.setFont(label2.getFont().deriveFont(label2.getFont().getStyle() | Font.BOLD, label2.getFont().getSize() + 1f));
			contentPane.add(label2);
			label2.setBounds(45, 75, 90, 35);
			contentPane.add(usernametext);
			usernametext.setBounds(160, 75, 165,usernametext.getPreferredSize().height);

			//---- label3 ----
			label3.setText("PASSWORD");
			label3.setFont(label3.getFont().deriveFont(label3.getFont().getStyle() | Font.BOLD, label3.getFont().getSize() + 1f));
			contentPane.add(label3);
			label3.setBounds(45, 120, 95, 35);
			contentPane.add(passwordField1);
			passwordField1.setBounds(160, 120, 165, passwordField1.getPreferredSize().height);

			//---- label4 ----
			label4.setText("RETYPE PASSWORD");
			label4.setFont(label4.getFont().deriveFont(label4.getFont().getStyle() | Font.BOLD, label4.getFont().getSize() + 1f));
			contentPane.add(label4);
			label4.setBounds(15, 165, 135, 35);
			contentPane.add(passwordField2);
			passwordField2.setBounds(160, 165, 165, 28);
			contentPane.add(mailtextfield);
			mailtextfield.setBounds(160, 215, 165, 28);

			//---- label5 ----
			label5.setText("E-MAIL");
			label5.setFont(label5.getFont().deriveFont(label5.getFont().getStyle() | Font.BOLD, label5.getFont().getSize() + 1f));
			contentPane.add(label5);
			label5.setBounds(70, 215, 45, 35);

			//---- label6 ----
			label6.setText("IDENTIFY QUESTION 1");
			label6.setFont(label6.getFont().deriveFont(label6.getFont().getStyle() | Font.BOLD, label6.getFont().getSize() + 1f));
			contentPane.add(label6);
			label6.setBounds(35, 275, 145, 35);

			//---- label7 ----
			label7.setText("IDENTIFY QUESTION 2");
			label7.setFont(label7.getFont().deriveFont(label7.getFont().getStyle() | Font.BOLD, label7.getFont().getSize() + 1f));
			contentPane.add(label7);
			label7.setBounds(30, 360, 145, 35);

			//---- label8 ----
			label8.setText("answer:");
			contentPane.add(label8);
			label8.setBounds(90, 320, 53, 31);

			//---- label9 ----
			label9.setText("answer:");
			contentPane.add(label9);
			label9.setBounds(85, 410, 53, 31);

			//---- button1 ----
			button1.setText("submit");
			contentPane.add(button1);
			button1.setBounds(145, 450, 95, 33);
			contentPane.add(label10);
			label10.setBounds(160, 40, 85, label10.getPreferredSize().height);

			//---- label11 ----
			systemstat.setText("***please enter your information");
			contentPane.add(systemstat);
			systemstat.setBounds(100, 50, 133,systemstat.getPreferredSize().height);
			contentPane.add(ans1textfield);
			ans1textfield.setBounds(210, 320, 137,ans1textfield.getPreferredSize().height);
			contentPane.add(ans2textfield);
			ans2textfield.setBounds(210, 410, 137, 28);

			//======== scrollPane1 ========
			{
				scrollPane1.setViewportView(list1);
			}
			contentPane.add(scrollPane1);
			scrollPane1.setBounds(195, 275, 160, 35);

			//======== scrollPane2 ========
			{
				scrollPane2.setViewportView(list2);
			}
			contentPane.add(scrollPane2);
			scrollPane2.setBounds(200, 360, 155, 35);

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
			pack();
			setLocationRelativeTo(getOwner());
			
			ActionListener pressed = new ActionListener(){
				public void actionPerformed(ActionEvent e){
					String name = usernametext.getText();
					String pass1 = passwordField1.getText();
					String pass2 = passwordField2.getText();
					String mail = mailtextfield.getText();
					String ans1 = ans1textfield.getText();
					String ans2 = ans1textfield.getText();
					
		
					boolean ans=REGISCONSTRAINTS.validRegistration(name, pass1, pass2, mail, ans1, ans2);
					
					System.out.println(ans);
					if (ans)
						systemstat.setText("***SUCCESS***");
					if (!ans)
						systemstat.setText("***SOMETHING IS WRONG***");
				}
			};
			button1.addActionListener(pressed);
			
			setVisible(true);
		}


	}

	

