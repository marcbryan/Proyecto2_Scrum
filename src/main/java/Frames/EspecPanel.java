package Frames;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.Especificacion;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;

public class EspecPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public EspecPanel(Especificacion e) {
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JCheckBox chckbxMarcar = new JCheckBox("Marcar");
		chckbxMarcar.setBackground(new Color(90,21,50));
		chckbxMarcar.setForeground(Color.white);
		
		JButton btnGuardarCambios = new JButton("Guardar Cambios");
		btnGuardarCambios.setBackground(new Color(255,69,28));
		btnGuardarCambios.setForeground(Color.white);
		
		JTextArea textArea = new JTextArea();
		
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		scrollPane.setViewportView(textArea);
		
		textArea.setText(e.getDescricion_especificacion());
		
		setBackground(new Color(90,21,50));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(28, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnGuardarCambios)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(chckbxMarcar))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 403, GroupLayout.PREFERRED_SIZE))
					.addGap(21))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxMarcar)
						.addComponent(btnGuardarCambios))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(40, Short.MAX_VALUE))
		);
		
		
		setLayout(groupLayout);

	}
}
