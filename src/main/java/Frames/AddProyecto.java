package Frames;

import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Cursor;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import daoImpl.SQLiteDAOImpl;
import daoImpl.RemotaDAOImpl;
import idao.IScrumConfig;
import model.Proyecto;
import model.Usuario;

import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class AddProyecto extends JInternalFrame {
	private JTextField tf_nombreProyecto;
	private JTextArea ta_Descripcion;
	private JLabel lblError;
	private JComboBox cb_ScrumMaster;
	private JComboBox cb_ProductOwner;
	
	private List<Object[]> lista_SM;
	private List<Object[]> lista_PO;
	
	private IScrumConfig remotaDAO;
	private IScrumConfig embebidaDAO;
	
	/**
	 * Crea el JInternalFrame para añadir un proyecto
	 * @author Ali
	 */
	public AddProyecto() {
		remotaDAO = new RemotaDAOImpl();
		embebidaDAO = new SQLiteDAOImpl();
		
		setTitle("Nuevo Proyecto");
		setClosable(true);
		setResizable(true);
		setMaximizable(true);
		setBounds(100, 100, 595, 405);
		setBackground(new Color(90,21,50));
		
		//Asignamos esta imagen como icono del Internal Frame
		ImageIcon img = new ImageIcon("src"+File.separator+"main"+File.separator+"resources"+File.separator+"iconoInternalFrames.png");
		setFrameIcon(img);
		
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
		cb_ScrumMaster.setBackground(new Color(255,69,28));
		cb_ScrumMaster.setForeground(Color.white);
		
		// Para recoger los Scrum Masters
		if (Login.statusDB.equals("ONLINE")) {
			// Comprobar la remota si esta online
			if (remotaDAO.bd_online()) {
				lista_SM = remotaDAO.getScrumMasters();
			} else {
				// Si esta offline usaremos la embebida
				lista_SM = embebidaDAO.getScrumMasters();
			}
		} else {
			// Si esta offline usaremos la embebida
			lista_SM = embebidaDAO.getScrumMasters();
		}
		
		List<String> lista_ScrumMasters = new ArrayList<String>();
		for (Object[] sm : lista_SM) {
			//La posición 1 (el array empieza por la 0) del array de Object tiene el nombre del Scrum Master 
			lista_ScrumMasters.add(sm[1].toString());
		}
		cb_ScrumMaster.setModel(new DefaultComboBoxModel(lista_ScrumMasters.toArray()));
		
		JLabel lbl_ProductOwner = new JLabel("Product Owner:");
		lbl_ProductOwner.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_ProductOwner.setForeground(Color.white);
		
		cb_ProductOwner = new JComboBox();
		cb_ProductOwner.setBackground(new Color(255,69,28));
		cb_ProductOwner.setForeground(Color.white);
		
		// Para recoger los Product Owners
		if (Login.statusDB.equals("ONLINE")) {
			// Comprobar la remota si esta online
			if (remotaDAO.bd_online()) {
				lista_PO = remotaDAO.getProductOwners();
			} else {
				// Si esta offline usaremos la embebida
				lista_PO = embebidaDAO.getProductOwners();
			}
		} else {
			// Si esta offline usaremos la embebida
			lista_PO = embebidaDAO.getProductOwners();
		}
		
		List<String> lista_ProductOwners = new ArrayList<String>();
		for (Object[] po : lista_PO) {
			//La posición 1 (el array empieza por la 0) del array de Object tiene el nombre del Product Owner
			lista_ProductOwners.add(po[1].toString());
		}
		cb_ProductOwner.setModel(new DefaultComboBoxModel(lista_ProductOwners.toArray()));
		
		JButton btn_Anadir = new JButton("A\u00F1adir");
		btn_Anadir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				lblError.setText(" ");
				if (!tf_nombreProyecto.getText().equals("") && !ta_Descripcion.getText().equals("")) {
					// Si la base de datos está OFFLINE nos saltamos el paso de comprobar si la base de datos está online, para acceder más rápido
					if (Login.statusDB.equals("OFFLINE")) {
						if (embebidaDAO.comprobarProyecto(tf_nombreProyecto.getText()) == false) {
							Object[] id_sm = lista_SM.get(cb_ScrumMaster.getSelectedIndex());
							Object[] id_po = lista_PO.get(cb_ProductOwner.getSelectedIndex());
							//La posición 0 del array de object es el id del scrum master o product owner
							Proyecto pr = new Proyecto(tf_nombreProyecto.getText(), ta_Descripcion.getText(), null, null, Integer.valueOf(id_sm[0].toString()), Integer.valueOf(id_po[0].toString()), 1);
							embebidaDAO.insertarProyecto(pr);
							JOptionPane.showMessageDialog(AddProyecto.this,
									"Proyecto " + pr.getNombre_proyecto() + " añadido correctamente en el modo OFFLINE!", "Información",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							lblError.setText("El nombre del proyecto ya está asignado. Cambia de nombre.");
						}
					}
					// Comprobamos si está online con la BBDD remota
					else if (remotaDAO.bd_online()) {
						if (remotaDAO.comprobarProyecto(tf_nombreProyecto.getText()) == false) {
							Object[] id_sm = lista_SM.get(cb_ScrumMaster.getSelectedIndex());
							Object[] id_po = lista_PO.get(cb_ProductOwner.getSelectedIndex());
							//La posición 0 del array de object es el id del scrum master o product owner
							Proyecto proyecto = new Proyecto(tf_nombreProyecto.getText(), ta_Descripcion.getText(), null, null, Integer.valueOf(id_sm[0].toString()), Integer.valueOf(id_po[0].toString()), 1);
							//Lo insertamos en la remota y en la embebida
							remotaDAO.insertarProyecto(proyecto);
							embebidaDAO.insertarProyecto(proyecto);
							JOptionPane.showMessageDialog(AddProyecto.this,
									"Proyecto " + proyecto.getNombre_proyecto() + " añadido correctamente!", "Información",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							lblError.setText("El nombre del proyecto ya está asignado. Cambia de nombre.");
						}
					}
					/* Si se ha ido la conexión con la base de datos remota, en el tiempo en que se pone el texto ONLINE en el InternalFrame
					 * y entre que se haga click en el botón de crear proyecto, utilizaremos la embebida (para que no haya errores) */
					else {
						if (embebidaDAO.comprobarProyecto(tf_nombreProyecto.getText()) == false) {
							Object[] id_sm = lista_SM.get(cb_ScrumMaster.getSelectedIndex());
							Object[] id_po = lista_PO.get(cb_ProductOwner.getSelectedIndex());
							//La posición 0 del array de object es el id del scrum master o product owner
							Proyecto pr = new Proyecto(tf_nombreProyecto.getText(), ta_Descripcion.getText(), null, null, Integer.valueOf(id_sm[0].toString()), Integer.valueOf(id_po[0].toString()), 1);
							embebidaDAO.insertarProyecto(pr);
							JOptionPane.showMessageDialog(AddProyecto.this,
									"Proyecto " + pr.getNombre_proyecto() + " añadido correctamente en el modo OFFLINE!", "Información",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							lblError.setText("El nombre del proyecto ya está asignado. Cambia de nombre.");
						}
					}					
				} else {
					JOptionPane.showMessageDialog(AddProyecto.this, "Faltan campos por rellenar", "Alerta",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btn_Anadir.setBackground(new Color(255,69,28));

		btn_Anadir.setForeground(Color.white);
		
		lblError = new JLabel(" ");
		lblError.setForeground(new Color(227, 28, 33));
		lblError.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
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
								.addComponent(cb_ProductOwner, 0, 369, Short.MAX_VALUE)
								.addComponent(cb_ScrumMaster, Alignment.TRAILING, 0, 369, Short.MAX_VALUE)
								.addComponent(ta_Descripcion, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
								.addComponent(tf_nombreProyecto, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
								.addComponent(lblError, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE))
							.addGap(33))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_nombreProyecto)
						.addComponent(tf_nombreProyecto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblError)
					.addGap(14)
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
					.addContainerGap(41, Short.MAX_VALUE))
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
