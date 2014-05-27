package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.*;



public class WINDOWONE extends JFrame {
	public int state = 0;
	private JLabel label1;
	private JLabel label2;
	private JScrollPane scrollPane1;
	private JList list1;
	private JButton button1;
	//private CONNECTION connection;
		
	public WINDOWONE(){
		//this.connection = connection;
		label1 = new JLabel();
		label2 = new JLabel();
		scrollPane1 = new JScrollPane();
		
		String[] forums = CONNECTION.getForums();
		list1 = new JList(forums);
		button1 = new JButton();

		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		label1.setText("welcome to forum system");
		label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 18f));
		contentPane.add(label1);
		label1.setBounds(100, 10, 405, 75);

		label2.setText("please choose a forum");
		label2.setFont(label2.getFont().deriveFont(label2.getFont().getStyle() & ~Font.BOLD, label2.getFont().getSize() + 15f));
		contentPane.add(label2);
		label2.setBounds(135, 70, 315, 75);

		{
			scrollPane1.setViewportView(list1);
		}
		contentPane.add(scrollPane1);
		scrollPane1.setBounds(135, 150, 260, 200);

		button1.setText("entrance");
		button1.setFont(button1.getFont().deriveFont(button1.getFont().getStyle() | Font.BOLD, button1.getFont().getSize() + 4f));
		contentPane.add(button1);
		button1.setBounds(210, 350, 121, 50);
		
		
		
		ActionListener entrance = new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String FN = (String) list1.getSelectedValue();
				FORUMWINDOWBEFORELOGINTEMP fw = new FORUMWINDOWBEFORELOGINTEMP(FN);
				dispose();
			}
		};
		
		button1.addActionListener(entrance);
		
		{
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

