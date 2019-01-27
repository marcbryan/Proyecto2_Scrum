package Frames;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import daoImpl.SQLiteDAOImpl;
import daoImpl.ScrumDAOImpl;
import idao.IScrumConfig;
import model.Usuario;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class Login extends JInternalFrame {
	private TextField tf_Usuario;
	private JPasswordField passwordField;
	private JLabel lbl_statusDB;
	
	/** Aquí guardaremos el estado de la base de datos remota (<b>OFFLINE</b> o <b>ONLINE</b>) para ver con que base de datos se tendrá que trabajar */
	public static String statusDB = "OFFLINE";
	
	private IScrumConfig remotaDAO;
	private IScrumConfig embebidaDAO;

	/**
	 * Crea el JInternalFrame del login
	 * @author David
	 */
	public Login() {
		remotaDAO = new ScrumDAOImpl();
		
		setMaximizable(true);
		setResizable(true);
		setClosable(true);
		setBounds(100, 100, 450, 300);
		setTitle("Log in");
		
		// Metodo para poner el cursor personalizado con una imagen nuestra
		cambiarCursor();
		
		final JPanel panel = new JPanel();
		
		// Texto ONLINE/OFFLINE en el InternalFrame 
		lbl_statusDB = new JLabel("<html><font color=red>OFFLINE</font></html>");
		if (remotaDAO.bd_online()) {
			lbl_statusDB.setText("<html><font color=green>ONLINE</font></html>");
			statusDB = "ONLINE";
		}
		
		// Lo hago aquí porque necesito saber el estado de la base de datos remota
		embebidaDAO = new SQLiteDAOImpl();
		
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
		// Añadimos el listener de teclado para detectar el boton Enter cuando tenemos el focus en el campo de contraseña.
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
	 * @author Marc
	 */
	private void login() {
		String pass = String.valueOf(passwordField.getPassword());
		if(!tf_Usuario.getText().equals("") && !pass.equals("")) {
			// Si la base de datos está OFFLINE nos saltamos el paso de comprobar si la base de datos está online, para acceder más rápido
			if (lbl_statusDB.getText().contains("OFFLINE")) {
				// Si no hay conexión con la base de datos REMOTA, se insertarán los datos en la base de datos embebida (nosotros utilizaremos SQLite)
				Usuario user = embebidaDAO.login(tf_Usuario.getText(), pass);
				if (user != null) {
					darPermisos(user);
				} else {
					JOptionPane.showMessageDialog(this, "Introduce un nombre de usuario y contraseña válidos", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			// Si la base de datos remota está online, comprobamos si hay conexión
			else if (remotaDAO.bd_online()) {
				Usuario user = remotaDAO.login(tf_Usuario.getText(), pass);
				if (user != null) {
					darPermisos(user);
				} else {
					JOptionPane.showMessageDialog(this, "Introduce un nombre de usuario y contraseña válidos", "Error", JOptionPane.ERROR_MESSAGE);
				}
			/* Si se ha ido la conexión con la base de datos remota, en el tiempo en que se pone el texto ONLINE en el InternalFrame
			 * y entre que se haga click en el botón de login, utilizaremos la embebida (para que no haya errores) */
			} else {
				lbl_statusDB.setText("<html><font color=red>OFFLINE</font></html>");
				statusDB = "OFFLINE";
				// Si no hay conexión con la base de datos REMOTA, se insertarán los datos en la base de datos embebida (nosotros utilizaremos SQLite)
				Usuario user = embebidaDAO.login(tf_Usuario.getText(), pass);
				if (user != null) {
					darPermisos(user);
				} else {
					JOptionPane.showMessageDialog(this, "Introduce un nombre de usuario y contraseña válidos", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}else {
			JOptionPane.showMessageDialog(this, "Introduce un nombre de usuario y contraseña válidos", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/** 
	 * Método para poner el cursor personalizado con una imagen nuestra 
	 * @author David 
	 */
	public void cambiarCursor() {
		Image customimage;
        Cursor customCursor;
		try {
			customimage = ImageIO.read(new File("src"+File.separator+"main"+File.separator+"resources"+File.separator+"cursor2.png"));
			customCursor = Toolkit.getDefaultToolkit().createCustomCursor(customimage, new Point(0, 0), "customCursor");
			this.setCursor(customCursor);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Dependiendo del tipo de usuario que sea, tendrá unos permisos o otros 
	 * @param user - El usuario con el que hemos hecho login satisfactoriamente
	 * @author Marc
	 */
	private void darPermisos(Usuario user) {
		FramePrincipal.lbl_Usuario.setText("Usuario: "+tf_Usuario.getText()+" ("+user.getTipo_usuario()+")");
		tf_Usuario.setText("");
		passwordField.setText("");
		// Si el usuario es Administrador podrá añadir usuarios, si no esta función no estará disponible
		if (user.getTipo_usuario().equals("Administrator")) {
			FramePrincipal.mnItem_AddU.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					AddUsuario IFrameAddU = new AddUsuario();
					FramePrincipal.desktopPane.add(IFrameAddU);
					IFrameAddU.setVisible(true);
				}
			});
		}
		// Si el usuario es Scrum Master podrá añadir proyectos, si no esta función no estará disponible
		else if (user.getTipo_usuario().equals("Scrum Master")) {
			FramePrincipal.mnItem_AddProject.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					AddProyecto IFrameAddP = new AddProyecto();
					FramePrincipal.desktopPane.add(IFrameAddP);
					IFrameAddP.setVisible(true);
				}
			});
		}
		Login.this.setVisible(false);
	}
}
