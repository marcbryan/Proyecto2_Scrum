package Frames;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import daoImpl.SQLiteDAOImpl;
import daoImpl.ScrumDAOImpl;
import idao.IScrumConfig;
import model.Especificacion;

import java.awt.Font;

public class AddEspecificacion extends JInternalFrame {
	private JTextField tf_nombreE;
	private JTextArea ta_Descr;
	private JComboBox cb_Sprint;
	private JSpinner spinnerHrs;
	
	private IScrumConfig remotaDAO;
	private IScrumConfig embebidaDAO;

	/**
	 * Crea el JInternalFrame para añadir una especificación
	 * @param nombre_proyecto - El nombre del proyecto (para saber a que proyecto pertenece)
	 * @param id_proyecto - El id del proyecto (para saber a que proyecto pertence y poder realizar el insert)
	 * @param especIntFrame - El InternalFrame donde se ven las especificaciones (para añadir la especificación a este InternalFrame, si se crea)
	 */
	public AddEspecificacion(String nombre_proyecto, final int id_proyecto, final Especificaciones especIntFrame) {
		remotaDAO = new ScrumDAOImpl();
		embebidaDAO = new SQLiteDAOImpl();
		
		setClosable(true);
		setMaximizable(true);
		setResizable(true);
		setTitle("Nueva especificación de "+nombre_proyecto);
		setBounds(300, 300, 620, 315);
		setBackground(new Color(90, 21, 50));
			
		//Asignamos esta imagen como icono del Internal Frame
		ImageIcon img = new ImageIcon("src"+File.separator+"main"+File.separator+"resources"+File.separator+"iconoInternalFrames.png");
		setFrameIcon(img);
		
		cambiarCursor();
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{70, 70, 65, 115, 40, 0, 0};
		gridBagLayout.rowHeights = new int[]{50, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNombreEspec = new JLabel("Nombre especificaci\u00F3n:");
		lblNombreEspec.setForeground(Color.WHITE);
		lblNombreEspec.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNombreEspec = new GridBagConstraints();
		gbc_lblNombreEspec.gridwidth = 2;
		gbc_lblNombreEspec.anchor = GridBagConstraints.WEST;
		gbc_lblNombreEspec.insets = new Insets(0, 20, 5, 20);
		gbc_lblNombreEspec.gridx = 0;
		gbc_lblNombreEspec.gridy = 0;
		getContentPane().add(lblNombreEspec, gbc_lblNombreEspec);
		
		tf_nombreE = new JTextField("");
		GridBagConstraints gbc_tf_nombreE = new GridBagConstraints();
		gbc_tf_nombreE.gridwidth = 4;
		gbc_tf_nombreE.insets = new Insets(0, 0, 5, 15);
		gbc_tf_nombreE.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_nombreE.gridx = 2;
		gbc_tf_nombreE.gridy = 0;
		getContentPane().add(tf_nombreE, gbc_tf_nombreE);
		tf_nombreE.setColumns(10);
		
		JLabel lblDescr = new JLabel("Descripci\u00F3n:");
		lblDescr.setForeground(Color.WHITE);
		lblDescr.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblDescr = new GridBagConstraints();
		gbc_lblDescr.gridwidth = 2;
		gbc_lblDescr.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblDescr.insets = new Insets(0, 20, 5, 5);
		gbc_lblDescr.gridx = 0;
		gbc_lblDescr.gridy = 1;
		getContentPane().add(lblDescr, gbc_lblDescr);
		
		ta_Descr = new JTextArea("");
		GridBagConstraints gbc_ta_Descr = new GridBagConstraints();
		gbc_ta_Descr.gridwidth = 4;
		gbc_ta_Descr.insets = new Insets(0, 0, 20, 15);
		gbc_ta_Descr.fill = GridBagConstraints.BOTH;
		gbc_ta_Descr.gridx = 2;
		gbc_ta_Descr.gridy = 1;
		getContentPane().add(ta_Descr, gbc_ta_Descr);
		
		JLabel lblSprint = new JLabel("Sprint:");
		lblSprint.setForeground(Color.WHITE);
		lblSprint.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblSprint = new GridBagConstraints();
		gbc_lblSprint.anchor = GridBagConstraints.WEST;
		gbc_lblSprint.insets = new Insets(0, 20, 50, 5);
		gbc_lblSprint.gridx = 0;
		gbc_lblSprint.gridy = 2;
		getContentPane().add(lblSprint, gbc_lblSprint);
		
		cb_Sprint = new JComboBox();
		List <String> lista_sprints = new ArrayList<String>();
		lista_sprints.add("Por determinar");
		lista_sprints.add("Sprint 1");
		lista_sprints.add("Sprint 2");
		lista_sprints.add("Sprint 3");
		cb_Sprint.setModel(new DefaultComboBoxModel(lista_sprints.toArray()));
		
		GridBagConstraints gbc_cb_Sprint = new GridBagConstraints();
		gbc_cb_Sprint.gridwidth = 2;
		gbc_cb_Sprint.insets = new Insets(0, 10, 50, 5);
		gbc_cb_Sprint.fill = GridBagConstraints.HORIZONTAL;
		gbc_cb_Sprint.gridx = 1;
		gbc_cb_Sprint.gridy = 2;
		getContentPane().add(cb_Sprint, gbc_cb_Sprint);
		
		JLabel lblNumHr = new JLabel("N\u00FAmero de horas:");
		lblNumHr.setForeground(Color.WHITE);
		lblNumHr.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNumHr = new GridBagConstraints();
		gbc_lblNumHr.anchor = GridBagConstraints.EAST;
		gbc_lblNumHr.insets = new Insets(0, 0, 50, 5);
		gbc_lblNumHr.gridx = 3;
		gbc_lblNumHr.gridy = 2;
		getContentPane().add(lblNumHr, gbc_lblNumHr);
		
		spinnerHrs = new JSpinner();
		spinnerHrs.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		GridBagConstraints gbc_spinnerHrs = new GridBagConstraints();
		gbc_spinnerHrs.anchor = GridBagConstraints.WEST;
		gbc_spinnerHrs.insets = new Insets(0, 0, 50, 25);
		gbc_spinnerHrs.gridx = 4;
		gbc_spinnerHrs.gridy = 2;
		// Para hacer mas largo el spinner
		Component mySpinnerEditor = spinnerHrs.getEditor();
		JFormattedTextField jftf = ((JSpinner.DefaultEditor) mySpinnerEditor).getTextField();
		jftf.setEnabled(false);
		jftf.setDisabledTextColor(Color.black);
		jftf.setColumns(3);
		getContentPane().add(spinnerHrs, gbc_spinnerHrs);
		
		JButton btnAddE = new JButton("A\u00F1adir");
		btnAddE.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int id_sprint;
				if (!tf_nombreE.getText().equals("") || !ta_Descr.getText().equals("")) {
					// Si la base de datos está OFFLINE nos saltamos el paso de comprobar si la base de datos está online, para acceder más rápido
					if (Login.statusDB.equals("OFFLINE")) {
						id_sprint = numSprint();
						Especificacion espec = new Especificacion(tf_nombreE.getText(), ta_Descr.getText(), spinnerHrs.getValue()+"h", id_proyecto, id_sprint, "Por determinar");
						embebidaDAO.insertarEspecificacion(espec);
						
						//Añadir especificación al panel
						EspecPanel newEPanel = new EspecPanel(espec); 
						especIntFrame.paneles.add(newEPanel);
						especIntFrame.panel.add(newEPanel);
						
						JOptionPane.showMessageDialog(AddEspecificacion.this, "Especificación " + tf_nombreE.getText() + " creada correctamente!", "Información", JOptionPane.INFORMATION_MESSAGE);
						tf_nombreE.setText("");
						ta_Descr.setText("");
						int option = JOptionPane.showConfirmDialog(AddEspecificacion.this, "Abandonar pantalla para crear especificación?", "Confirmación", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (option == JOptionPane.OK_OPTION) {
							AddEspecificacion.this.dispose();
						}
					}
					// Comprobamos si está online con la BBDD remota
					else if (remotaDAO.bd_online()) {
						id_sprint = numSprint();
						Especificacion espec = new Especificacion(tf_nombreE.getText(), ta_Descr.getText(), spinnerHrs.getValue()+"h", id_proyecto, id_sprint, "Por determinar");
						// Insertamos la nueva especificación en la remota y en la embebida
						remotaDAO.insertarEspecificacion(espec);
						embebidaDAO.insertarEspecificacion(espec);
						
						//Añadir especificación al panel
						EspecPanel newEPanel = new EspecPanel(espec); 
						especIntFrame.paneles.add(newEPanel);
						especIntFrame.panel.add(newEPanel);
						
						JOptionPane.showMessageDialog(AddEspecificacion.this, "Especificación " + tf_nombreE.getText() + " creada correctamente!", "Información", JOptionPane.INFORMATION_MESSAGE);
						tf_nombreE.setText("");
						ta_Descr.setText("");
						int option = JOptionPane.showConfirmDialog(AddEspecificacion.this, "Abandonar pantalla para crear especificación?", "Confirmación", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (option == JOptionPane.OK_OPTION) {
							AddEspecificacion.this.dispose();
						}	
					}
					/* Si se ha ido la conexión con la base de datos remota mostraremos un OptionPane */
					else {
						JOptionPane.showMessageDialog(AddEspecificacion.this, "Ha habido un error, probablemente se haya ido la conexión.", "Error al crear especificación", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(AddEspecificacion.this, "Faltan campos por rellenar", "Alerta", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnAddE.setForeground(Color.WHITE);
		btnAddE.setBackground(new Color(255, 69, 28));
		GridBagConstraints gbc_btnAddE = new GridBagConstraints();
		gbc_btnAddE.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddE.insets = new Insets(0, 0, 50, 15);
		gbc_btnAddE.gridx = 5;
		gbc_btnAddE.gridy = 2;
		getContentPane().add(btnAddE, gbc_btnAddE);
		
		//Para que al pulsar el icono de cerrar ventana no haga nada
		setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
		
		// JOptionPane de confirmación al cerrar la ventana
		addInternalFrameListener(new InternalFrameAdapter(){
            public void internalFrameClosing(InternalFrameEvent e) {
				int option = JOptionPane.showConfirmDialog(AddEspecificacion.this, "Abandonar pantalla para crear especificación?", "Confirmación", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (option == JOptionPane.OK_OPTION) {
					AddEspecificacion.this.dispose();
				}
            }
        });
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
	
	/**
	 * Sirve para saber el id del sprint seleccionado
	 * @return Devuelve el sprint seleccionado
	 */
	private int numSprint() {
		int id_sprint;
		if (cb_Sprint.getSelectedItem().toString().equals("Por determinar")) {
			id_sprint = 0;
		} else {
			id_sprint = cb_Sprint.getSelectedIndex();
			//System.out.println("sprint: "+id_sprint);
		}
		return id_sprint;
	}
}
