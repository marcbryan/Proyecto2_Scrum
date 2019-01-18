package Frames;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Login extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setMaximizable(true);
		setResizable(true);
		setClosable(true);
		setBounds(100, 100, 450, 300);

		JPanel panel = new JPanel();
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(68)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
					.addGap(44))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
					.addGap(45))
		);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tw Cen MT", Font.PLAIN, 21));
		lblPassword.setForeground(Color.white);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Tw Cen MT", Font.PLAIN, 21));
		lblLogin.setForeground(Color.white);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		
		
		JButton btnNewButton = new JButton("Entrar");
		
		btnNewButton.setBackground(new Color(227,28,33));
		btnNewButton.setForeground(Color.white);
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(43)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblLogin)
								.addComponent(lblPassword))
							.addGap(33)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(118)
							.addComponent(btnNewButton)))
					.addContainerGap(44, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLogin)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnNewButton)
					.addContainerGap(62, Short.MAX_VALUE))
		);
		panel.setBackground(new Color(90,21,50));
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		
		setBackground(new Color(90,21,50));
		
		
		

	}
}
