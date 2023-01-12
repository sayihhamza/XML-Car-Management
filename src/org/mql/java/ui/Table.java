package org.mql.java.ui;

import java.util.Set;

//Packages to import
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.mql.java.models.Voiture;
import org.mql.java.xml.*;

public class Table {
	// frame
	JFrame f;
	// Table
	JTable j;

	// Constructor
	Table() {
		// Frame initialization
		f = new JFrame();

		// Frame Title
		f.setTitle("JTable Example");
		
		Parser parser = new Parser("resources/voitures.xml");
		Set<Voiture> voitures = parser.fetchCars();

		
		Object[][] data = new Object[voitures.size()][6];
		int i = 0;
		for (Voiture voiture : voitures) {
			Object[] voitureData = {voiture.getMatricule(),voiture.getModel(),voiture.getMarque(),voiture.getColeur(),voiture.getAnnee(),voiture.isAutomatique()};
			data[i] = voitureData;
			i++;
		}

		// Column Names
		String[] columnNames = { "Matricule", "Model", "Marque", "Annee", "Coleur", "Automatique" };

		// Initializing the JTable
		j = new JTable(data, columnNames);
		JScrollPane sp = new JScrollPane(j);
		f.add(sp);
		f.setSize(700, 400);
		f.setVisible(true);
	}


	// Driver method
	public static void main(String[] args) {
		new Table();
	}
}
