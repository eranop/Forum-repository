package GUI;
import java.awt.*;
import javax.swing.*;




public class FORUMWINDOWAFTERLOGIN extends JFrame {
	private String fn;
	private String username;
	private JLabel FORUM;
	private JButton button1;
	private JScrollPane scrollPane1;
	private JList list1;
	private JLabel label1;
	private JLabel label2;
	private JButton button4;
	private JButton button5;

	
	
	public FORUMWINDOWAFTERLOGIN(String fn,String username){
			this.fn=fn;
			this.username=username;
			FORUM = new JLabel();
			button1 = new JButton();
			scrollPane1 = new JScrollPane();
			list1 = new JList();
			label1 = new JLabel();
			label2 = new JLabel();
			button4 = new JButton();
			button5 = new JButton();

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
			label2.setText("HI " + username);
			contentPane.add(label2);
			label2.setBounds(520, 70, 78, 46);

			//---- button4 ----
			button4.setText("logout");
			contentPane.add(button4);
			button4.setBounds(490, 125, 115, 35);

			//---- button5 ----
			button5.setText("personal area");
			contentPane.add(button5);
			button5.setBounds(490, 170, 115, 35);

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
			setVisible(true);
		}
}
