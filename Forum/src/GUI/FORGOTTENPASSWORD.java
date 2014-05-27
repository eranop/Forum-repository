package GUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class FORGOTTENPASSWORD extends JFrame{
		private final String username;
		private String question1;
		private String question2;
		
		private JLabel label1;
		private JLabel label2;
		private JTextField textField1;
		private JLabel label3;
		private JLabel label4;
		private JLabel label5;
		private JTextField textField2;
		private JLabel label6;
		private JButton button1;
		
		public FORGOTTENPASSWORD(final String username){
			this.username=username;
			question1 = CONNECTION.getQuestion1ForUser(username);
			question2 = CONNECTION.getQuestion2ForUser(username);
			label1 = new JLabel();
			label2 = new JLabel();
			textField1 = new JTextField();
			label3 = new JLabel();
			label4 = new JLabel();
			label5 = new JLabel();
			textField2 = new JTextField();
			label6 = new JLabel();
			button1 = new JButton();

			//======== this ========
			Container contentPane = getContentPane();
			contentPane.setLayout(null);

			//---- label1 ----
			label1.setText("forgotten password");
			label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 11f));
			contentPane.add(label1);
			label1.setBounds(210, 20, 215, 60);

			//---- label2 ----
			
			label2.setText(question1);
			contentPane.add(label2);
			label2.setBounds(new Rectangle(new Point(55, 90), label2.getPreferredSize()));
			contentPane.add(textField1);
			textField1.setBounds(150, 115, 80, textField1.getPreferredSize().height);

			//---- label3 ----
			label3.setText("answer1");
			contentPane.add(label3);
			label3.setBounds(55, 125, 64, 16);

			//---- label4 ----
			label4.setText(question2);
			contentPane.add(label4);
			label4.setBounds(50, 200, 75, 16);

			//---- label5 ----
			label5.setText("answer2");
			contentPane.add(label5);
			label5.setBounds(55, 225, 64, 16);
			contentPane.add(textField2);
			textField2.setBounds(145, 215, 80, 28);

			//---- label6 ----
			label6.setText("enter your submitted answers");
			contentPane.add(label6);
			label6.setBounds(220, 65, 190, 25);

			//---- button1 ----
			button1.setText("send password to my mail");
			contentPane.add(button1);
			button1.setBounds(210, 270, 205, 30);

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
			
			ActionListener submit_answers = new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if (question1.equals(textField1.getText()) && question2.equals(textField2.getText())){
						System.out.println("good answers");
						//send mail and wait for an ok sign
						boolean success = CONNECTION.sendNewPasswordToUser(username);
						if (success){
							dispose();
						}
						if (!success){
							label1.setText("for some reasone new password wasn't sent");
						}						
					}
					label1.setText("wrong answer!"); 
				}
			};
			button1.addActionListener(submit_answers);
			
			
			
			
			
			
			
			pack();
			setLocationRelativeTo(getOwner());
			setVisible(true);
		}		
	}

	

