package Frames;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class FramePrincipal extends JFrame {

	private JPanel contentPane;
	static JLabel lbl_Usuario;
	
	static JMenu menu_Usuarios;
	static JMenuItem mnItem_AddU;
	
	static JMenu menu_Proyectos;
	static JMenuItem mnItem_AddProject;
	
	static JDesktopPane desktopPane;

	/**
	 * Main para ejecutar la aplicación.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FramePrincipal frame = new FramePrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Crea el frame principal del gestor de Scrum
	 * @author David
	 */
	public FramePrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 688, 502);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		
		ImageIcon img = new ImageIcon("src"+File.separator+"main"+File.separator+"resources"+File.separator+"icono.png");
		
		setIconImage(img.getImage());
		//setIconImages(new ("src"+File.separator+"main"+File.separator+"resources"+File.separator+"iconoPro.png"));

		// Metodo para poner el cursor personalizado con una imagen nuestra
		cambiarCursor();
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menuBar.setBackground(new Color(33,0,17));
		menuBar.setForeground(Color.white);

		menu_Proyectos = new JMenu("Proyectos");
		menu_Proyectos.setBackground(new Color(33,0,17));
		menu_Proyectos.setForeground(Color.white);
		menuBar.add(menu_Proyectos);
		
		
		mnItem_AddProject = new JMenuItem("Crear proyecto");
		mnItem_AddProject.setBackground(new Color(33,0,17));
		mnItem_AddProject.setForeground(Color.white);
		menu_Proyectos.add(mnItem_AddProject);
		
		JMenuItem mntmShowProject = new JMenuItem("Mostrar proyectos");
		mntmShowProject.setBackground(new Color(33,0,17));
		mntmShowProject.setForeground(Color.white);
		menu_Proyectos.add(mntmShowProject);

		menu_Usuarios = new JMenu("Usuarios");
		menu_Usuarios.setBackground(new Color(33,0,17));
		menu_Usuarios.setForeground(Color.white);
		menuBar.add(menu_Usuarios);
		
		mnItem_AddU  = new JMenuItem("Nuevo usuario");
		mnItem_AddU.setBackground(new Color(33,0,17));
		mnItem_AddU.setForeground(Color.white);
		menu_Usuarios.add(mnItem_AddU);
		
		JMenuItem mntmSearchUsu = new JMenuItem("Buscar/modificar usuario");
		mntmSearchUsu.setBackground(new Color(33,0,17));
		mntmSearchUsu.setForeground(Color.white);
		menu_Usuarios.add(mntmSearchUsu);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255,69,28));
		setContentPane(contentPane);

		ImageIcon icon = new ImageIcon(getClass().getResource("fondo.jpg"));
		final Image image = icon.getImage();

		desktopPane = new JDesktopPane() {
			public void paintComponent(Graphics g) {
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};

		Login IFramelog = new Login();
		desktopPane.add(IFramelog);
		IFramelog.setVisible(true);
		
		lbl_Usuario = new JLabel("Usuario: ");
		lbl_Usuario.setFont(new Font("Times new roman", Font.BOLD, 20));
		lbl_Usuario.setForeground(Color.white);
		
		
		// Para cerrar sesión y volver a hacer login
		JButton btnSalir = new JButton("SALIR");
		
		btnSalir.setBackground(new Color(33,0,17));
		btnSalir.setForeground(Color.white);
		
		btnSalir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				FramePrincipal principal = new FramePrincipal();
				principal.setVisible(true);
				FramePrincipal.this.dispose();
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(236, Short.MAX_VALUE)
					.addComponent(lbl_Usuario, GroupLayout.PREFERRED_SIZE, 337, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnSalir)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbl_Usuario))
					.addGap(29)
					.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
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
	
	
}
