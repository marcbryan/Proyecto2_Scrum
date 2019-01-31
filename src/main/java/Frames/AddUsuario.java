package Frames;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import daoImpl.SQLiteDAOImpl;
import daoImpl.ScrumDAOImpl;
import idao.IScrumConfig;
import model.Usuario;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;

public class AddUsuario extends JInternalFrame {
	private JTextField tf_Nombre;
	private JTextField tf_LoginGen;
	private JPasswordField passwordField;
	private JPasswordField passwordField2;
	private JTextField tf_Mail;
	private String CaracteresPass = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private String password = "", nombre, logGene;
	private JLabel lbl_Error;
	boolean emailOk;
	private String array_Nombre[];

	private JComboBox cBox_TipoUsuario;

	private IScrumConfig remotaDAO;
	private IScrumConfig embebidaDAO;

	/**
	 * Crea el JInternalFrame para añadir un usuario
	 * @author David
	 */
	public AddUsuario() {
		remotaDAO = new ScrumDAOImpl();
		embebidaDAO = new SQLiteDAOImpl();

		setMaximizable(true);
		setResizable(true);
		setClosable(true);
		setBounds(100, 100, 700, 471);
		setTitle("Añade un usuario");
		
		//Asignamos esta imagen como icono del Internal Frame
		ImageIcon img = new ImageIcon("src"+File.separator+"main"+File.separator+"resources"+File.separator+"iconoInternalFrames.png");
		setFrameIcon(img);

		// Metodo para poner el cursor personalizado con una imagen nuestra
		cambiarCursor();

		final JPanel panel = new JPanel();
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

		tf_LoginGen = new JTextField("");
		tf_LoginGen.setColumns(10);

		tf_Nombre.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				nombre = tf_Nombre.getText();

				array_Nombre = nombre.split(" ");

				if (array_Nombre.length > 3) {
					logGene = array_Nombre[0].charAt(0) + array_Nombre[array_Nombre.length - 2];
				} else {
					logGene = array_Nombre[0].charAt(0) + array_Nombre[1];
				}

				tf_LoginGen.setText(logGene);
			}
		});

		JButton btnGenerar = new JButton("Generar");
		btnGenerar.setBackground(new Color(255,69,28));
		btnGenerar.setForeground(Color.white);

		btnGenerar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				passwordField.setText("");
				passwordField2.setText("");
				password = "";
				for (int i = 0; i < 6; i++) {
					password = password + CaracteresPass.charAt(0 + (int) (Math.random() * 62));
				}
				
				passwordField.setText(password);
				passwordField2.setText(password);

				JOptionPane.showInternalMessageDialog(panel, "La contraseña autogenerada es: " + password,
						"Información", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		cBox_TipoUsuario = new JComboBox();
		cBox_TipoUsuario.setModel(new DefaultComboBoxModel(new String[] { // "Elige un tipo usuario",
				"Product Owner", "Scrum Master", "Developer", "Administrator" }));
		cBox_TipoUsuario.setToolTipText("Elige un tipo de usuario");

		cBox_TipoUsuario.setBackground(new Color(255,69,28));

		cBox_TipoUsuario.setForeground(Color.white);

		JLabel lblTipoUsuario = new JLabel("Tipo usuario:");
		lblTipoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTipoUsuario.setForeground(Color.white);

		passwordField = new JPasswordField("");
		passwordField.setColumns(10);

		passwordField2 = new JPasswordField("");
		passwordField2.setColumns(10);

		tf_Mail = new JTextField("");
		tf_Mail.setColumns(10);

		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String pass = String.valueOf(passwordField.getPassword());
				String pass2 = String.valueOf(passwordField2.getPassword());
				if (!pass.equals(pass2)) {
					lbl_Error.setText("Las contraseñas no coinciden.");
				} else if (!tf_Nombre.getText().equals("") && !tf_LoginGen.getText().equals("")
						&& !pass.equals("") && !pass2.equals("")
						&& !tf_Mail.getText().equals("")) {
					// Una vez comprobado si todos los campos han sido rellenados, comprobamos si el
					// email tiene el formato correcto
					if (comprobarEmail(tf_Mail.getText()) == true) {
						// Si la base de datos está OFFLINE nos saltamos el paso de comprobar si la base de datos está online, para acceder más rápido
						if (Login.statusDB.equals("OFFLINE")) {
							// Comprobamos si el nombre de usuario está disponible
							if (embebidaDAO.comprobarUsuario(tf_LoginGen.getText()) == false) {
								lbl_Error.setText(" ");
								String tipo_usuario = String.valueOf(cBox_TipoUsuario.getSelectedItem().toString());
								Usuario user = new Usuario(tf_LoginGen.getText(), pass, tf_Nombre.getText(),
										tipo_usuario, tf_Mail.getText(), 1);
								// Insertamos el usuario creado en la base de datos remota y en la embebida
								embebidaDAO.insertarUsuario(user);
								JOptionPane.showMessageDialog(AddUsuario.this,
										"Usuario " + user.getNombre_usuario() + " añadido correctamente en el modo OFFLINE!", "Información",
										JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(null, "El usuario "+tf_LoginGen.getText()+" ya existe. Prueba con otro nombre de usuario.", "Error",
										JOptionPane.ERROR_MESSAGE);
							}
						}
						// Ahora comprobamos si está online con la bbdd remota
						else if (remotaDAO.bd_online()) {
							// Comprobamos si el nombre de usuario está disponible
							if (remotaDAO.comprobarUsuario(tf_LoginGen.getText()) == false) {
								lbl_Error.setText(" ");
								String tipo_usuario = String.valueOf(cBox_TipoUsuario.getSelectedItem().toString());
								Usuario user = new Usuario(tf_LoginGen.getText(), pass, tf_Nombre.getText(),
										tipo_usuario, tf_Mail.getText(), 1);
								// Insertamos el usuario creado en la base de datos remota y en la embebida
								remotaDAO.insertarUsuario(user);
								embebidaDAO.insertarUsuario(user);
								JOptionPane.showMessageDialog(AddUsuario.this,
										"Usuario " + user.getNombre_usuario() + " añadido correctamente!", "Información",
										JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(null, "El usuario "+tf_LoginGen.getText()+" ya existe. Prueba con otro nombre de usuario.", "Error",
										JOptionPane.ERROR_MESSAGE);
							}
						/* Si se ha ido la conexión con la base de datos remota, en el tiempo en que se pone el texto ONLINE en el InternalFrame
						 * y entre que se haga click en el botón de crear usuario, utilizaremos la embebida (para que no haya errores) */
						} else {
							// Si no hay conexión con la base de datos REMOTA, se insertarán los datos en la
							// base de datos embebida (nosotros utilizaremos SQLite)
							
							// Comprobamos si el nombre de usuario está disponible
							if (embebidaDAO.comprobarUsuario(tf_LoginGen.getText()) == false) {
								lbl_Error.setText(" ");
								String tipo_usuario = String.valueOf(cBox_TipoUsuario.getSelectedItem().toString());
								Usuario user = new Usuario(tf_LoginGen.getText(), pass, tf_Nombre.getText(),
										tipo_usuario, tf_Mail.getText(), 1);
								// Insertamos el usuario creado en la base de datos remota y en la embebida
								embebidaDAO.insertarUsuario(user);
								JOptionPane.showMessageDialog(AddUsuario.this,
										"Usuario " + user.getNombre_usuario() + " añadido correctamente en el modo OFFLINE!", "Información",
										JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(null, "El usuario "+tf_LoginGen.getText()+" ya existe. Prueba con otro nombre de usuario.", "Error",
										JOptionPane.ERROR_MESSAGE);
							}
						}

					} else {
						JOptionPane.showMessageDialog(null, "El email ingresado es inválido.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(AddUsuario.this, "Faltan campos por rellenar", "Alerta",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		btnCrear.setBackground(new Color(255,69,28));

		btnCrear.setForeground(Color.white);
		final JCheckBox showhide_password = new JCheckBox("");
		showhide_password.setBackground(null);
		final char defaultChar = passwordField.getEchoChar(); 
		showhide_password.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (showhide_password.isSelected()) {
					passwordField.setEchoChar((char) 0);
					passwordField2.setEchoChar((char) 0);
				} else {
					passwordField.setEchoChar(defaultChar);
					passwordField2.setEchoChar(defaultChar);
				}
			}
		});		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(90,21,50));
		

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
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnCrear, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(passwordField2, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
								.addComponent(passwordField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
								.addComponent(tf_LoginGen, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
								.addComponent(tf_Nombre, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
								.addComponent(tf_Mail, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
								.addComponent(cBox_TipoUsuario, Alignment.TRAILING, 0, 235, Short.MAX_VALUE))
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(6)
									.addComponent(showhide_password, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnGenerar, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(18)
									.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)))
							.addGap(52))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(64)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(tf_Nombre, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLoginGenerado)
						.addComponent(tf_LoginGen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnGenerar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addComponent(showhide_password, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblRepitaPassword)
							.addComponent(passwordField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMail)
						.addComponent(tf_Mail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cBox_TipoUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTipoUsuario, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnCrear, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(80))
		);
		
				lbl_Error = new JLabel(" ");
				panel_1.add(lbl_Error);
				lbl_Error.setForeground(new Color(255, 0, 0));

		panel.setBackground(new Color(90, 21, 50));

		panel.setLayout(gl_panel);
		setBackground(new Color(90, 21, 50));

	}
	
	public boolean comprobarEmail(String correo) {

		// Patrón para validar el email
		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		// El email a validar
		String email = correo;
		Matcher mather = pattern.matcher(email);

		if (!correo.equals("")) {
			if (mather.find() == true) {
				emailOk = true;
			} else {
				emailOk = false;
			}

		}
		return emailOk;
	}
	
	/** 
	 * Método para poner el cursor personalizado con una imagen nuestra 
	 * @author David
	 */
	public void cambiarCursor() {
		Image customimage;
		Cursor customCursor;
		try {
			customimage = ImageIO.read(new File(
					"src" + File.separator + "main" + File.separator + "resources" + File.separator + "cursor2.png"));
			customCursor = Toolkit.getDefaultToolkit().createCustomCursor(customimage, new Point(0, 0), "customCursor");
			this.setCursor(customCursor);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
