package Frames;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class AddProyecto extends JInternalFrame {
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddProyecto frame = new AddProyecto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddProyecto() {
		setTitle("Nuevo Proyecto");
		setClosable(true);
		setResizable(true);
		setMaximizable(true);
		setBounds(100, 100, 580, 405);
		setBackground(new Color(90,21,50));
		
		JLabel lbl_nombreProyecto = new JLabel("Nombre proyecto:");
		lbl_nombreProyecto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_nombreProyecto.setForeground(Color.white);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lbl_Descripcion = new JLabel("Descripci\u00F3n:");
		lbl_Descripcion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_Descripcion.setForeground(Color.white);
		
		JTextArea ta_Descripcion = new JTextArea();
		
		JLabel lbl_ScrumMaster = new JLabel("Scrum Master:");
		lbl_ScrumMaster.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_ScrumMaster.setForeground(Color.white);
		
		JComboBox cb_ScrumMaster = new JComboBox();
		
		JLabel lbl_ProductOwner = new JLabel("Product Owner:");
		lbl_ProductOwner.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_ProductOwner.setForeground(Color.white);
		
		JComboBox cb_ProductOwner = new JComboBox();
		
		JButton btn_Anadir = new JButton("A\u00F1adir");
		btn_Anadir.setBackground(new Color(227,28,33));
		btn_Anadir.setForeground(Color.white);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lbl_nombreProyecto)
						.addComponent(lbl_ProductOwner)
						.addComponent(lbl_Descripcion, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbl_ScrumMaster))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btn_Anadir)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(cb_ProductOwner, 0, 369, Short.MAX_VALUE)
								.addComponent(cb_ScrumMaster, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 367, GroupLayout.PREFERRED_SIZE)
								.addComponent(ta_Descripcion, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE))
							.addGap(33))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_nombreProyecto)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lbl_Descripcion)
						.addComponent(ta_Descripcion, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(cb_ScrumMaster, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbl_ScrumMaster))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(cb_ProductOwner, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbl_ProductOwner))
					.addGap(27)
					.addComponent(btn_Anadir)
					.addContainerGap(45, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
}
