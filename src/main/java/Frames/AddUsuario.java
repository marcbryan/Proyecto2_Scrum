package Frames;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class AddUsuario extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddUsuario frame = new AddUsuario();
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
	public AddUsuario() {
		setMaximizable(true);
		setResizable(true);
		setClosable(true);
		setBounds(100, 100, 635, 471);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombre.setForeground(Color.white);
		
		JLabel lblLoginGenerado = new JLabel("Login generado:");
		lblLoginGenerado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLoginGenerado.setForeground(Color.white);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setForeground(Color.white);
		
		JLabel lblRepitaPassword = new JLabel("Repita password:");
		lblRepitaPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRepitaPassword.setForeground(Color.white);
		
		JLabel lblMail = new JLabel("Mail:");
		lblMail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMail.setForeground(Color.white);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Generar");
		btnNewButton.setBackground(new Color(227,28,33));
		btnNewButton.setForeground(Color.white);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("Elige un tipo de usuario");
		
		JLabel lblTipoUsuario = new JLabel("Tipo usuario:");
		lblTipoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTipoUsuario.setForeground(Color.white);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.setBackground(new Color(227,28,33));
		btnCrear.setForeground(Color.white);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblRepitaPassword, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
							.addGap(45)
							.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTipoUsuario, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMail, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLoginGenerado)
								.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNombre))
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
									.addGroup(gl_panel.createSequentialGroup()
										.addGap(80)
										.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(80)
											.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
										.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
											.addGroup(gl_panel.createSequentialGroup()
												.addGap(80)
												.addComponent(textField, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
											.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
												.addGap(80)
												.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
													.addComponent(btnCrear, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
													.addComponent(comboBox, 0, 153, Short.MAX_VALUE))))))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(80)
									.addComponent(textField_4, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)))))
					.addGap(26)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
					.addGap(75))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(64)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLoginGenerado)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_2))
					.addGap(20)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRepitaPassword)
						.addComponent(textField_3))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMail)
						.addComponent(textField_4))
					.addGap(32)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox)
						.addComponent(lblTipoUsuario, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnCrear, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(66))
		);
		
		panel.setBackground(new Color(90,21,50));
		
		
		panel.setLayout(gl_panel);
		setBackground(new Color(90,21,50));

	}
}
