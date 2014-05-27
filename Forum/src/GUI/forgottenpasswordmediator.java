package GUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.AncestorListener;

public class forgottenpasswordmediator extends JFrame{
	private JLabel label1;
	private JLabel label2;
	private JTextField textField1;
	private JButton button1;

	public forgottenpasswordmediator(){
			label1 = new JLabel();
			label2 = new JLabel();
			textField1 = new JTextField();
			button1 = new JButton();

			//======== this ========
			Container contentPane = getContentPane();
			contentPane.setLayout(null);

			//---- label1 ----
			label1.setText("forgot your password?");
			label1.setFont(label1.getFont().deriveFont(Font.BOLD|Font.ITALIC, label1.getFont().getSize() + 5f));
			contentPane.add(label1);
			label1.setBounds(100, 5, 210, 45);

			//---- label2 ----
			label2.setText("enter your username");
			contentPane.add(label2);
			label2.setBounds(new Rectangle(new Point(125, 55), label2.getPreferredSize()));
			contentPane.add(textField1);
			textField1.setBounds(145, 80, 85, textField1.getPreferredSize().height);

			//---- button1 ----
			button1.setText("continue");
			contentPane.add(button1);
			button1.setBounds(140, 120, 100, button1.getPreferredSize().height);

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
			
			ActionListener name_submit = new ActionListener(){
				public void actionPerformed(ActionEvent e){
					String user=textField1.getText();
					if (FORGOTTENPASSWORDCONSTRAINTS.isValid(user)){
						FORGOTTENPASSWORD FP = new FORGOTTENPASSWORD(user);
						dispose();
					}
					if (!FORGOTTENPASSWORDCONSTRAINTS.isValid(user)){
						label2.setText("unvalid username. try again");
					}
				}
			};
			button1.addActionListener(name_submit);
			pack();
			setLocationRelativeTo(getOwner());
			setVisible(true);
		}
	}
