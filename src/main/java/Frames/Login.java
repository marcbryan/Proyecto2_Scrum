package Frames;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.LayoutStyle.ComponentPlacement;

import daoImpl.ScrumDAOImpl;
import idao.IScrumConfig;
import model.Usuario;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class Login extends JInternalFrame {
	private TextField tf_Usuario;
	private JPasswordField passwordField;
	private IScrumConfig dao;

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
		//DAO
		dao = new ScrumDAOImpl();
		
		setMaximizable(true);
		setResizable(true);
		setClosable(true);
		setBounds(100, 100, 450, 300);
		setTitle("Log in");
		
		final JPanel panel = new JPanel();
		
		JLabel lbl_statusDB = new JLabel("<html><font color=red>OFFLINE</font></html>");
		if (dao.bd_online()) {
			lbl_statusDB.setText("<html><font color=green>ONLINE</font></html>");
		}
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lbl_statusDB))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(68)
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)))
					.addGap(44))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbl_statusDB)
					.addGap(10)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
					.addGap(45))
		);
		
		JLabel lbl_Password = new JLabel("Password:");
		lbl_Password.setFont(new Font("Tw Cen MT", Font.PLAIN, 21));
		lbl_Password.setForeground(Color.white);
		
		JLabel lbl_Login = new JLabel("Login:");
		lbl_Login.setFont(new Font("Tw Cen MT", Font.PLAIN, 21));
		lbl_Login.setForeground(Color.white);
		
		tf_Usuario = new TextField("");
		tf_Usuario.setColumns(10);
		tf_Usuario.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {}
			
			public void keyReleased(KeyEvent e) {}
			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
			}
		});
		
		
		JButton btn_Entrar = new JButton("Entrar");
		btn_Entrar.setBackground(new Color(227,28,33));
		btn_Entrar.setForeground(Color.white);
		
		btn_Entrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		
		btn_Entrar.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {}
			
			public void keyReleased(KeyEvent e) {}
			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}				
			}
		});
		
		passwordField = new JPasswordField("");
		
		
		//Añadimos el listener de teclado para detectar el boton Enter cuando tenemos el focus en el campo de contraseña.
		passwordField.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {}
			
			public void keyReleased(KeyEvent e) {}
			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
			}
		});

		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(43)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lbl_Login)
								.addComponent(lbl_Password))
							.addGap(33)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(passwordField)
								.addComponent(tf_Usuario)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(118)
							.addComponent(btn_Entrar)))
					.addContainerGap(44, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_Login)
						.addComponent(tf_Usuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_Password)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btn_Entrar)
					.addContainerGap(62, Short.MAX_VALUE))
		);
		panel.setBackground(new Color(90,21,50));
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		
		setBackground(new Color(90,21,50));
	}
	
	/**
	 * Sirve para hacer login en el gestor de Scrum
	 */
	private void login() {
		String pass = String.valueOf(passwordField.getPassword());
		if(!tf_Usuario.getText().equals("") && !pass.equals("")) {
			if (dao.bd_online()) {
				Usuario user = dao.login(tf_Usuario.getText(), pass);
				if (user != null) {
					FramePrincipal.lbl_Usuario.setText("Usuario: "+tf_Usuario.getText());
					tf_Usuario.setText("");
					passwordField.setText("");
					//Si el usuario es Administrador podrá añadir usuarios, si no esta funcion no estará disponible
					if (user.getTipo_usuario().equals("Administrator")) {
						FramePrincipal.menu_Usuarios.add(FramePrincipal.mnItem_AddU);
					}
					Login.this.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(this, "Introduce un nombre de usuario y contraseña validos", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				//si no hay conexión con la base de datos REMOTA, se insertarán los datos en la base de datos embebida (nosotros utilizaremos SQLite) 
			}
		}else {
			JOptionPane.showMessageDialog(this, "Introduce un nombre de usuario y contraseña validos", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
