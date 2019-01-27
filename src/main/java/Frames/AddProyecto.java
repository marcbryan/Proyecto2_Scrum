package Frames;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import daoImpl.SQLiteDAOImpl;
import daoImpl.ScrumDAOImpl;
import idao.IScrumConfig;
import model.Proyecto;

import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class AddProyecto extends JInternalFrame {
	private JTextField tf_nombreProyecto;
	private JTextArea ta_Descripcion;
	private JComboBox cb_ScrumMaster;
	private JComboBox cb_ProductOwner;
	
	private IScrumConfig remotaDAO;
	private IScrumConfig embebidaDAO;
	
	/**
	 * Crea el JInternalFrame para añadir un proyecto
	 * @author Ali
	 */
	public AddProyecto() {
		remotaDAO = new ScrumDAOImpl();
		embebidaDAO = new SQLiteDAOImpl();
		
		setTitle("Nuevo Proyecto");
		setClosable(true);
		setResizable(true);
		setMaximizable(true);
		setBounds(100, 100, 580, 405);
		setBackground(new Color(90,21,50));
		
		//Metodo para poner el cursor personalizado con una imagen nuestra
		cambiarCursor();
		
		JLabel lbl_nombreProyecto = new JLabel("Nombre proyecto:");
		lbl_nombreProyecto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_nombreProyecto.setForeground(Color.white);
		
		tf_nombreProyecto = new JTextField();
		tf_nombreProyecto.setColumns(10);
		
		JLabel lbl_Descripcion = new JLabel("Descripci\u00F3n:");
		lbl_Descripcion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_Descripcion.setForeground(Color.white);
		
		ta_Descripcion = new JTextArea();
		
		JLabel lbl_ScrumMaster = new JLabel("Scrum Master:");
		lbl_ScrumMaster.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_ScrumMaster.setForeground(Color.white);
		
		cb_ScrumMaster = new JComboBox();
		//Falta añadir los scrum masters
		cb_ScrumMaster.setBackground(new Color(227, 28, 33));
		cb_ScrumMaster.setForeground(Color.white);
		
		JLabel lbl_ProductOwner = new JLabel("Product Owner:");
		lbl_ProductOwner.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_ProductOwner.setForeground(Color.white);
		
		cb_ProductOwner = new JComboBox();
		//Falta añadir los product owners 
		cb_ProductOwner.setBackground(new Color(227, 28, 33));
		cb_ProductOwner.setForeground(Color.white);
		
		JButton btn_Anadir = new JButton("A\u00F1adir");
		btn_Anadir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (!tf_nombreProyecto.getText().equals("") && !ta_Descripcion.getText().equals("")) {
					// Si la base de datos está OFFLINE nos saltamos el paso de comprobar si la base de datos está online, para acceder más rápido
					if (Login.statusDB.equals("OFFLINE")) {
						
					}
					// Comprobamos si está online con la BBDD remota
					else if (remotaDAO.bd_online()) {
						
					}
					/* Si se ha ido la conexión con la base de datos remota, en el tiempo en que se pone el texto ONLINE en el InternalFrame
					 * y entre que se haga click en el botón de crear proyecto, utilizaremos la embebida (para que no haya errores) */
					else {
						
					}					
				} else {
					JOptionPane.showMessageDialog(AddProyecto.this, "Faltan campos por rellenar", "Alerta",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btn_Anadir.setBackground(new Color(227, 28, 33));
		btn_Anadir.setForeground(Color.white);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lbl_nombreProyecto)
						.addComponent(lbl_ProductOwner)
						.addComponent(lbl_Descripcion, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbl_ScrumMaster))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btn_Anadir)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(cb_ProductOwner, 0, 367, Short.MAX_VALUE)
								.addComponent(cb_ScrumMaster, Alignment.TRAILING, 0, 367, Short.MAX_VALUE)
								.addComponent(ta_Descripcion, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
								.addComponent(tf_nombreProyecto, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE))
							.addGap(33))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_nombreProyecto)
						.addComponent(tf_nombreProyecto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lbl_Descripcion)
						.addComponent(ta_Descripcion, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(cb_ScrumMaster, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbl_ScrumMaster))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(cb_ProductOwner, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbl_ProductOwner))
					.addGap(27)
					.addComponent(btn_Anadir)
					.addContainerGap(37, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

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
