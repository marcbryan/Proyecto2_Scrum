package Frames;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
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

	/**
	 * Launch the application.
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
	 * Create the frame.
	 */
	public FramePrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 688, 502);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnProyectos = new JMenu("Proyectos");
		menuBar.add(mnProyectos);
		
		JMenuItem mntmAddProject = new JMenuItem("Crear proyecto");
		mnProyectos.add(mntmAddProject);
		
		JMenuItem mntmShowProject = new JMenuItem("Mostrar proyectos");
		mnProyectos.add(mntmShowProject);

		menu_Usuarios = new JMenu("Usuarios");
		menuBar.add(menu_Usuarios);
		
		mnItem_AddU  = new JMenuItem("Nuevo usuario");
		menu_Usuarios.add(mnItem_AddU);
		
		JMenuItem mntmSearchUsu = new JMenuItem("Buscar/modificar usuario");
		menu_Usuarios.add(mntmSearchUsu);
		
		
		
		
		
		

		//menu_Usuarios.add(mnItem_AddU);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		ImageIcon icon = new ImageIcon(getClass().getResource("fondo.jpg"));
		final Image image = icon.getImage();

		final JDesktopPane desktopPane = new JDesktopPane() {
			public void paintComponent(Graphics g) {
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};

		Login IFramelog = new Login();
		desktopPane.add(IFramelog);
		IFramelog.setVisible(true);
		
		
		mnItem_AddU.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				AddUsuario IFrameAddU = new AddUsuario();

				desktopPane.add(IFrameAddU);
				IFrameAddU.setVisible(true);
			}
		});
		
		lbl_Usuario = new JLabel("Usuario: ");
		lbl_Usuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		
		
		JButton btnSalir = new JButton("SALIR");
		
		btnSalir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(266, Short.MAX_VALUE)
					.addComponent(lbl_Usuario, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSalir)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_Usuario)
						.addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
