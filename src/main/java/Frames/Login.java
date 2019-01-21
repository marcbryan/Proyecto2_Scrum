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
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class Login extends JInternalFrame {
	private TextField tf_Usuario;
	private JPasswordField passwordField;

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
		setTitle("Log in");
		
		final JPanel panel = new JPanel();
		
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
		
		JLabel lbl_Password = new JLabel("Password:");
		lbl_Password.setFont(new Font("Tw Cen MT", Font.PLAIN, 21));
		lbl_Password.setForeground(Color.white);
		
		JLabel lbl_Login = new JLabel("Login:");
		lbl_Login.setFont(new Font("Tw Cen MT", Font.PLAIN, 21));
		lbl_Login.setForeground(Color.white);
		
		tf_Usuario = new TextField();
		tf_Usuario.setColumns(10);
		
		
		JButton btn_Entrar = new JButton("Entrar");
		
		btn_Entrar.setBackground(new Color(227,28,33));
		btn_Entrar.setForeground(Color.white);
		
		btn_Entrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String pass = String.valueOf(passwordField.getPassword());
				
				// TODO Auto-generated method stub
				if(tf_Usuario.getText()!=null && pass!=null) {
					
					FramePrincipal.lblUsuario.setText("Usuario: "+tf_Usuario.getText());
					
					
					
				}else {
					
					JOptionPane.showMessageDialog(panel, "Introduce un nombre de usuario y contraseña validos", "Error", JOptionPane.ERROR_MESSAGE);
					
				}
				
				
			}
		});
		
		passwordField = new JPasswordField();
		
		
		//Añadimos el listener de teclado para detectar el boton Enter cuando tenemos el focus en el campo de contraseña.
		passwordField.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				     
					String pass = String.valueOf(passwordField.getPassword());
					
					// TODO Auto-generated method stub
					if(!tf_Usuario.getText().equals("") && !pass.equals("")) {
						
						
						FramePrincipal.lblUsuario.setText("Usuario: "+tf_Usuario.getText());
						
						
					}else {
						
						JOptionPane.showMessageDialog(panel, "Introduce un nombre de usuario y contraseña validos", "Error", JOptionPane.ERROR_MESSAGE);
						
					}
					
					
					
					
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
}
