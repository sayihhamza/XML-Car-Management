package org.mql.java.ui;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.mql.java.models.Voiture;
import org.mql.java.xml.Parser;

public class AddCarView extends JDialog {

	private static final long serialVersionUID = 1L;
	private Form form;

	public AddCarView(Parser parser, DefaultTableModel model,JFrame frame) {
		
		
		this.setLocation(750,400);
		
		form = new Form("Add car");

		setSize(400, 300);
		setLayout(new FlowLayout());
		add(form);

		JTextField matriculeField = new JTextField(20);
		form.add("Matricule", 30, matriculeField);

		JTextField marqueField = new JTextField(20);
		form.add("Marque", 30, marqueField);

		JTextField modelField = new JTextField(20);
		form.add("Model", 30, modelField);

		JTextField couleurField = new JTextField(20);
		form.add("Couleur", 30, couleurField);

		JTextField anneeField = new JTextField(20);
		form.add("Année", 30, anneeField);

		JTextField automatiqueField = new JTextField(20);
		form.add("Automatique:", 30, automatiqueField);

		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(e -> {
			String matricule = matriculeField.getText();
			String marque = marqueField.getText();
			String modele = modelField.getText();
			String couleur = couleurField.getText();
			String annee = anneeField.getText();
			String automatique = automatiqueField.getText();

			if (matricule.length() >= 1 && marque.length() >= 1 && modele.length() >= 1 && couleur.length() >= 1
					&& annee.length() >= 1 && automatique.length() >= 1) {
//			if (matricule.length() >= 1) {
				// creation d nouvelle VoitureModel
				Voiture car = new Voiture();
				car.setMatricule(matricule);
				car.setModel(modele);
				car.setMarque(marque);
				car.setColeur(couleur);
				car.setAnnee(annee);
				car.setAutomatique(automatique);
				parser.addCar(car);
				parser.fetchCars();
				model.addRow(new Object[] { matricule, marque, modele, couleur, annee, automatique });
				// fermer le dialogue
			}
			setVisible(false);
		});
		add(saveButton);
		setVisible(true);

	}
}
