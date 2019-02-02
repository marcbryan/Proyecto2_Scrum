package Frames;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import daoImpl.SQLiteDAOImpl;
import daoImpl.ScrumDAOImpl;
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

	public InfoProyectos() {
		setTitle("Proyectos");
		setResizable(true);
		setClosable(true);
		setMaximizable(true);
		remotaDAO = new ScrumDAOImpl();
		embebidaDAO = new SQLiteDAOImpl();

		setBounds(100, 100, 738, 495);
		setBackground(new Color(90, 21, 50));

		ta_Descripcion = new JTextArea();
		ta_Descripcion.setLineWrap(true);

		DefaultListModel<String> model = new DefaultListModel<String>();
		list = new JList<String>(model);
		list.setValueIsAdjusting(true);
		list.setBackground(new Color(90, 21, 50));
		list.setForeground(Color.WHITE);
		list.setBorder(new BevelBorder(BevelBorder.RAISED));

		if (Login.statusDB.equals("OFFLINE")) {
			if (FramePrincipal.lbl_Usuario.getText().contains("Developer")
					|| FramePrincipal.lbl_Usuario.getText().contains("Product Owner")) {
				// ...
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
				int idgrupo=0;
				if (FramePrincipal.lbl_Usuario.getText().contains("Developer")
						|| FramePrincipal.lbl_Usuario.getText().contains("Product Owner")) {
					String linea = FramePrincipal.lbl_Usuario.getText();
					String[] linea_array = FramePrincipal.lbl_Usuario.getText().split(" ");
					if (linea_array.length > 4) {
						idgrupo=remotaDAO.getIdGrupo(linea_array[1]+linea_array[2]);
					} else {
						idgrupo = remotaDAO.getIdGrupo(linea_array[1]);
					}
					lista_Proyectos = remotaDAO.getProyectos(idgrupo);
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
				//Si no se ha seleccionado nada mostrará un optionpane
				if (list.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(InfoProyectos.this, "Selecciona un proyecto para ver sus especificaciones", "Alerta", JOptionPane.WARNING_MESSAGE);
				} else {
					Especificaciones espec = new Especificaciones();
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
												.addComponent(tf_ProductOwner, 321, 321, 321)
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
}
