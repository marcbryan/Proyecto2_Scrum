package Frames;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JInternalFrame;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import daoImpl.SQLiteDAOImpl;
import daoImpl.RemotaDAOImpl;
import idao.IScrumConfig;
import model.Proyecto;

import javax.swing.JTextField;

public class InfoProyectos extends JInternalFrame {
	private JTextField tf_NombreProyecto;
	private JTextField tf_ProductOwner;
	private JTextField tf_ScrumMaster;
	private IScrumConfig remotaDAO;
	private IScrumConfig embebidaDAO;
	private List<Proyecto> lista_Proyectos;
	private JTextArea ta_Descripcion;
	JList<String> list;

	/**
	 * Crea el JIntenalFrame que muestra la información de los proyectos.
	 * @author Ali
	 */
	public InfoProyectos() {
		setTitle("Proyectos");
		setResizable(true);
		setClosable(true);
		setMaximizable(true);
		remotaDAO = new RemotaDAOImpl();
		embebidaDAO = new SQLiteDAOImpl();

		setBounds(100, 100, 738, 495);
		setBackground(new Color(90, 21, 50));
		
		// Metodo para poner el cursor personalizado con una imagen nuestra
		cambiarCursor();
		
		//Asignamos esta imagen como icono del Internal Frame
		ImageIcon img = new ImageIcon("src"+File.separator+"main"+File.separator+"resources"+File.separator+"iconoInternalFrames.png");
		setFrameIcon(img);

		ta_Descripcion = new JTextArea();
		ta_Descripcion.setLineWrap(true);
		ta_Descripcion.setEditable(false);

		DefaultListModel<String> model = new DefaultListModel<String>();
		list = new JList<String>(model);
		list.setValueIsAdjusting(true);
		list.setBackground(new Color(90, 21, 50));
		list.setForeground(Color.WHITE);
		list.setBorder(new BevelBorder(BevelBorder.RAISED));

		if (Login.statusDB.equals("OFFLINE")) {
			if (FramePrincipal.lbl_Usuario.getText().contains("Developer")
					|| FramePrincipal.lbl_Usuario.getText().contains("Product Owner")) {
				int idgrupo = 0, id_po = 0;
				String[] linea_array = FramePrincipal.lbl_Usuario.getText().split(" ");				
				if (linea_array.length == 4 && FramePrincipal.lbl_Usuario.getText().contains("Product Owner")) {
					id_po = embebidaDAO.getIdProductOwner(linea_array[1]);
					lista_Proyectos = embebidaDAO.getProyectosPO(id_po);
				} else {
					idgrupo = embebidaDAO.getIdGrupo(linea_array[1]);
					lista_Proyectos = embebidaDAO.getProyectos(idgrupo);
				}
				
				for (int i = 0; i < lista_Proyectos.size(); i++) {
					model.addElement(lista_Proyectos.get(i).getNombre_proyecto());
				}
				list.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						if (!event.getValueIsAdjusting()) {
							JList source = (JList) event.getSource();
							int selected = source.getSelectedIndex();
							Proyecto p = lista_Proyectos.get(selected);
							tf_NombreProyecto.setText(p.getNombre_proyecto());
							tf_ProductOwner.setText(embebidaDAO.getNombre(p.getProduct_owner_proyecto()));
							tf_ScrumMaster.setText(embebidaDAO.getNombre(p.getScrum_master_proyecto()));
							ta_Descripcion.setText(p.getDescripcion_proyecto());
						}
					}
				});
			} else {
				lista_Proyectos = embebidaDAO.getProyectos();
				for (int i = 0; i < lista_Proyectos.size(); i++) {
					model.addElement(lista_Proyectos.get(i).getNombre_proyecto());
				}
				list.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						if (!event.getValueIsAdjusting()) {
							JList source = (JList) event.getSource();
							int selected = source.getSelectedIndex();
							Proyecto p = lista_Proyectos.get(selected);
							tf_NombreProyecto.setText(p.getNombre_proyecto());
							tf_ProductOwner.setText(embebidaDAO.getNombre(p.getProduct_owner_proyecto()));
							tf_ScrumMaster.setText(embebidaDAO.getNombre(p.getScrum_master_proyecto()));
							ta_Descripcion.setText(p.getDescripcion_proyecto());
						}
					}
				});
			}
		} else {
			if (remotaDAO.bd_online()) {
				int idgrupo = 0, id_po = 0;
				if (FramePrincipal.lbl_Usuario.getText().contains("Developer")
						|| FramePrincipal.lbl_Usuario.getText().contains("Product Owner")) {
					String[] linea_array = FramePrincipal.lbl_Usuario.getText().split(" ");
					if (linea_array.length == 4 && FramePrincipal.lbl_Usuario.getText().contains("Product Owner")) {
						id_po = remotaDAO.getIdProductOwner(linea_array[1]);
						lista_Proyectos = remotaDAO.getProyectosPO(id_po);
					} else {
						idgrupo = remotaDAO.getIdGrupo(linea_array[1]);
						lista_Proyectos = remotaDAO.getProyectos(idgrupo);
					}
					for (int i = 0; i < lista_Proyectos.size(); i++) {
						model.addElement(lista_Proyectos.get(i).getNombre_proyecto());
					}
					list.addListSelectionListener(new ListSelectionListener() {
						public void valueChanged(ListSelectionEvent event) {
							if (!event.getValueIsAdjusting()) {
								JList source = (JList) event.getSource();
								int selected = source.getSelectedIndex();
								Proyecto p = lista_Proyectos.get(selected);
								tf_NombreProyecto.setText(p.getNombre_proyecto());
								tf_ProductOwner.setText(remotaDAO.getNombre(p.getProduct_owner_proyecto()));
								tf_ScrumMaster.setText(remotaDAO.getNombre(p.getScrum_master_proyecto()));
								ta_Descripcion.setText(p.getDescripcion_proyecto());
							}
						}
					});

				} else {
					lista_Proyectos = remotaDAO.getProyectos();
					for (int i = 0; i < lista_Proyectos.size(); i++) {
						model.addElement(lista_Proyectos.get(i).getNombre_proyecto());
					}
					list.addListSelectionListener(new ListSelectionListener() {
						public void valueChanged(ListSelectionEvent event) {
							if (!event.getValueIsAdjusting()) {
								JList source = (JList) event.getSource();
								int selected = source.getSelectedIndex();
								Proyecto p = lista_Proyectos.get(selected);
								tf_NombreProyecto.setText(p.getNombre_proyecto());
								tf_ProductOwner.setText(remotaDAO.getNombre(p.getProduct_owner_proyecto()));
								tf_ScrumMaster.setText(remotaDAO.getNombre(p.getScrum_master_proyecto()));
								ta_Descripcion.setText(p.getDescripcion_proyecto());
							}
						}
					});
				}
			}
		}
		
		JButton btn_MostrarEspec = new JButton("Mostrar Especificaciones");
		btn_MostrarEspec.setBackground(new Color(227, 28, 33));
		btn_MostrarEspec.setForeground(Color.white);
		btn_MostrarEspec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Si no se ha seleccionado nada, se mostrará un optionpane
				if (list.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(InfoProyectos.this, "Selecciona un proyecto para ver sus especificaciones", "Alerta", JOptionPane.WARNING_MESSAGE);
				} else {
					Especificaciones espec = new Especificaciones(lista_Proyectos.get(list.getSelectedIndex()).getId_proyecto(), list.getSelectedValue());
					FramePrincipal.desktopPane.add(espec);
					espec.setVisible(true);
				}
			}
		});

		JLabel lbl_NombreProyecto = new JLabel("Nombre Proyecto:");
		lbl_NombreProyecto.setForeground(new Color(255, 255, 255));

		JLabel lbl_ProductOwner = new JLabel("Product Owner:");
		lbl_ProductOwner.setForeground(new Color(255, 255, 255));

		JLabel lbl_ScrumMaster = new JLabel("Scrum Master:");
		lbl_ScrumMaster.setForeground(new Color(255, 255, 255));

		tf_NombreProyecto = new JTextField();
		tf_NombreProyecto.setEditable(false);
		tf_NombreProyecto.setColumns(10);

		tf_ProductOwner = new JTextField();
		tf_ProductOwner.setEditable(false);
		tf_ProductOwner.setColumns(10);

		tf_ScrumMaster = new JTextField();
		tf_ScrumMaster.setEditable(false);
		tf_ScrumMaster.setColumns(10);

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addComponent(list, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE).addGap(29)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addComponent(btn_MostrarEspec).addContainerGap())
						.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
								.createParallelGroup(Alignment.LEADING).addComponent(ta_Descripcion)
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lbl_ScrumMaster, GroupLayout.PREFERRED_SIZE, 87,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lbl_NombreProyecto).addComponent(lbl_ProductOwner,
														GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(tf_ScrumMaster, GroupLayout.DEFAULT_SIZE, 321,
														Short.MAX_VALUE)
												.addComponent(tf_ProductOwner, GroupLayout.DEFAULT_SIZE, 321, 
														Short.MAX_VALUE)
												.addComponent(tf_NombreProyecto, GroupLayout.DEFAULT_SIZE, 321,
														Short.MAX_VALUE))))
								.addGap(61)))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(Alignment.LEADING,
								groupLayout.createSequentialGroup().addContainerGap().addComponent(list,
										GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING,
								groupLayout.createSequentialGroup().addGap(49)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(lbl_NombreProyecto)
												.addComponent(tf_NombreProyecto, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(38)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(lbl_ProductOwner)
												.addComponent(tf_ProductOwner, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(41)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(lbl_ScrumMaster).addComponent(tf_ScrumMaster,
														GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
										.addGap(38).addComponent(ta_Descripcion, GroupLayout.PREFERRED_SIZE, 151,
												GroupLayout.PREFERRED_SIZE)))
				.addGap(26).addComponent(btn_MostrarEspec).addContainerGap(39, Short.MAX_VALUE)));
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
