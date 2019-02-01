package Frames;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.BoxLayout;
import javax.swing.ScrollPaneConstants;

public class Especificaciones extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Especificaciones frame = new Especificaciones();
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
	public Especificaciones() {
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		setBounds(100, 100, 494, 324);
		setBackground(new Color(90,21,50));
		
		JButton btnGuardar = new JButton("Guardar");
		
		btnGuardar.setBackground(new Color(255,69,28));
		btnGuardar.setForeground(Color.white);
		
		JButton btnAnadir = new JButton("A\u00F1adir");
		btnAnadir.setBackground(new Color(255,69,28));
		btnAnadir.setForeground(Color.white);
		
		JButton btnElimin = new JButton("Eliminar");
		btnElimin.setBackground(new Color(255,69,28));
		btnElimin.setForeground(Color.white);
		
		
		EspecPanel ePane ;
		int espec =4;
		JPanel panel = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		panel.setBackground(new Color(33,0,17));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		scrollPane.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		for (int i = 0; i < 10; i++) {
			
			ePane = new EspecPanel();
			
			panel.add(ePane);
			//scrollPane.setViewportView(ePane);
			
			
		}
		
	
		
		
		
		
		
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
