package Frames;

import java.awt.BorderLayout;
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
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;

public class FramePrincipal extends JFrame {

	private JPanel contentPane;

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

		JMenu mnUsuarios = new JMenu("Usuarios");
		menuBar.add(mnUsuarios);

		JMenuItem mntmAddU = new JMenuItem("Añadir Usuario");

		mnUsuarios.add(mntmAddU);
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

		mntmAddU.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddUsuario IFrameAddU = new AddUsuario();

				desktopPane.add(IFrameAddU);
				IFrameAddU.setVisible(true);
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane.createSequentialGroup()
						.addGap(58).addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
	}
}
