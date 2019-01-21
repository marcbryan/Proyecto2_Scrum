package Frames;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import daoImpl.ScrumDAOImpl;
import idao.IScrumConfig;
import model.Usuario;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AddUsuario extends JInternalFrame {
	private JTextField tf_Nombre;
	private JTextField tf_loginG;
	private JTextField tf_Pass;
	private JTextField tf_Pass2;
	private JTextField tf_Mail;
	
	private IScrumConfig dao;

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
		//DAO
		dao = new ScrumDAOImpl();
		
		setMaximizable(true);
		setResizable(true);
		setClosable(true);
		setBounds(100, 100, 635, 471);
		setTitle("Añade un usuario");
		
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
		
		tf_Nombre = new JTextField("");
		tf_Nombre.setColumns(10);
		
		JButton btnNewButton = new JButton("Generar");
		btnNewButton.setBackground(new Color(227,28,33));
		btnNewButton.setForeground(Color.white);
		
		JComboBox cBox_TipoUsuario = new JComboBox();
		cBox_TipoUsuario.setModel(new DefaultComboBoxModel(new String[] {"Elige un tipo usuario", "Product Owner", "Scrum Master", "Developer", "Administrator"}));
		cBox_TipoUsuario.setToolTipText("Elige un tipo de usuario");
		
		cBox_TipoUsuario.setBackground(new Color(227,28,33));
		cBox_TipoUsuario.setForeground(Color.white);
		
		
		
		
		
		JLabel lblTipoUsuario = new JLabel("Tipo usuario:");
		lblTipoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTipoUsuario.setForeground(Color.white);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (!tf_Nombre.getText().equals("") && !tf_loginG.getText().equals("") && !tf_Pass.getText().equals("") && !tf_Pass2.getText().equals("") && !tf_Mail.getText().equals("")) {
					if (dao.bd_online()) {
						//Usuario user = new Usuario(tf_loginG.getText(), tf_Pass.getText(), tf_Nombre.getText(), tipo_usuario, tf_Mail.getText(), 1);
					} else {
						//si no hay conexión con la base de datos REMOTA, se insertarán los datos en la base de datos embebida (nosotros utilizaremos SQLite)
					}
				} else {
					JOptionPane.showMessageDialog(AddUsuario.this, "Introduce un nombre de usuario y contraseña validos", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCrear.setBackground(new Color(227,28,33));
		btnCrear.setForeground(Color.white);
		
		tf_loginG = new JTextField("");
		tf_loginG.setColumns(10);
		
		tf_Pass = new JTextField("");
		tf_Pass.setColumns(10);
		
		tf_Pass2 = new JTextField("");
		tf_Pass2.setColumns(10);
		
		tf_Mail = new JTextField("");
		tf_Mail.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTipoUsuario, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMail, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLoginGenerado)
						.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNombre)
						.addComponent(lblRepitaPassword, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(tf_Pass2, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
						.addComponent(tf_Pass, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
						.addComponent(tf_loginG, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
						.addComponent(tf_Nombre, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
						.addComponent(btnCrear, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
						.addComponent(tf_Mail, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
						.addComponent(cBox_TipoUsuario, Alignment.TRAILING, 0, 193, Short.MAX_VALUE))
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
						.addComponent(tf_Nombre, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLoginGenerado)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(tf_loginG, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(tf_Pass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(20)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRepitaPassword)
						.addComponent(tf_Pass2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMail)
						.addComponent(tf_Mail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cBox_TipoUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
