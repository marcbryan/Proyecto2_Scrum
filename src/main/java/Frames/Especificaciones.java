package Frames;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.BoxLayout;
import javax.swing.ScrollPaneConstants;

public class Especificaciones extends JInternalFrame {
	
	List <EspecPanel> paneles;
	JPanel panel;

	/**
	 * Crea el JIntenalFrame de las especificaciones.
	 * @param id_proyecto - El id del proyecto (para saber a que proyecto pertence y poder pasarselo como parametro al InternalFrame de Añadir especificaciones)
	 * @param nombre_proyecto - El nombre del proyecto (para saber a que proyecto pertenece)
	 * @author David
	 */
	public Especificaciones(final int id_proyecto, final String nombre_proyecto) {
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		setTitle("Especificaciones "+nombre_proyecto);
		setBounds(150, 150, 494, 324);
		setBackground(new Color(90,21,50));
		
		//Asignamos esta imagen como icono del Internal Frame
		ImageIcon img = new ImageIcon("src"+File.separator+"main"+File.separator+"resources"+File.separator+"iconoInternalFrames.png");
		setFrameIcon(img);
		
		JButton btnGuardar = new JButton("Guardar");
		
		btnGuardar.setBackground(new Color(255,69,28));
		btnGuardar.setForeground(Color.white);
		
		JButton btnAnadir = new JButton("A\u00F1adir");
		// Solo si es Product Owner podrá añadir especificaciones
		if (FramePrincipal.lbl_Usuario.getText().contains("Product Owner")) {
			btnAnadir.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					AddEspecificacion addEspec = new AddEspecificacion(nombre_proyecto, id_proyecto, Especificaciones.this);
					FramePrincipal.desktopPane.add(addEspec);
					addEspec.setVisible(true);
				}
			});
		}
		btnAnadir.setBackground(new Color(255,69,28));
		btnAnadir.setForeground(Color.white);
		
		JButton btnElimin = new JButton("Eliminar");
		btnElimin.setBackground(new Color(255,69,28));
		btnElimin.setForeground(Color.white);
		
		//EspecPanel ePane;
		panel = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		//panel.setBackground(new Color(33,0,17)); //color anterior
		panel.setBackground(new Color(90,21,50));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		scrollPane.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		paneles = new ArrayList<EspecPanel>();
		/*
		for (int i = 0; i < 10; i++) {
			ePane = new EspecPanel();
			panel.add(ePane);
			paneles.add(ePane);
			//scrollPane.setViewportView(ePane);
		}*/
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 478, GroupLayout.PREFERRED_SIZE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(90)
					.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAnadir, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnElimin, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(113, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(8)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGuardar)
						.addComponent(btnAnadir)
						.addComponent(btnElimin))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
					.addGap(0))
		);
		
		getContentPane().setLayout(groupLayout);
		
	}
}
