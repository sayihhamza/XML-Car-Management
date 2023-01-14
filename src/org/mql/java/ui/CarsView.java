package org.mql.java.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.mql.java.models.Voiture;
import org.mql.java.xml.Parser;

public class CarsView extends JFrame {

	private static final long serialVersionUID = 1L;

	private Parser parser;
	private JTable table;
	private DefaultTableModel model;

	public CarsView() {
		super("Cars View");
		setSize(800, 600);
		setLocationRelativeTo(null);  
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		parser = new Parser("resources/voitures.xml");
		String[] columnNames = { "Matricule", "Marque", "Model", "Couleur", "Année", "Automatique" };
		model = new DefaultTableModel(columnNames, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return column != 0;
			}
		};

		table = new JTable(model);
		
		JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.addItem("false");
        comboBox.addItem("true");
        table.getColumn("Automatique").setCellEditor(new DefaultCellEditor(comboBox));
		
		Font headerFont = new Font("Arial", Font.BOLD, 14);
		table.getTableHeader().setFont(headerFont);

		table.setCellSelectionEnabled(true);
		ListSelectionModel select = table.getSelectionModel();
		select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		select.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int[] row = table.getSelectedRows();
				int[] columns = table.getSelectedColumns();
				for (int i = 0; i < row.length; i++) {
					for (int j = 0; j < columns.length; j++) {
						Voiture tmpVoiture = parser.getCarByMatricule((String) table.getValueAt(row[i], 0));
						tmpVoiture.setMarque((String) table.getValueAt(row[i], 1));
						tmpVoiture.setModel((String) table.getValueAt(row[i], 2));
						tmpVoiture.setColeur((String) table.getValueAt(row[i], 3));
						tmpVoiture.setAnnee((String) table.getValueAt(row[i], 4));
						tmpVoiture.setAutomatique((String) table.getValueAt(row[i], 5));
						parser.modifyCar(tmpVoiture);
					}
				}
			}
		});

		add(new JScrollPane(table), BorderLayout.CENTER);
		for (Voiture car : parser.getVoitures()) {
			model.addRow(new Object[] { car.getMatricule(), car.getMarque(), car.getModel(), car.getColeur(),
					car.getAnnee(), car.isAutomatique() });
		}
		JButton addButton = new JButton("ADD NEW CAR");
		addButton.addActionListener(e -> {
			AddCarView form = new AddCarView(parser, model,this);
			form.setModal(true);
			form.setVisible(true);
		});

		JButton removeButton = new JButton("REMOVE SELECTED CAR");
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (table.getSelectedRow() != -1) {
					parser.removeCar(parser.getCarByMatricule((String) table.getValueAt(table.getSelectedRow(), 0)));
					model.removeRow(table.getSelectedRow());
				}
			}
		});

	    addButton.setPreferredSize(new Dimension(40, 40));
	    removeButton.setPreferredSize(new Dimension(40, 40));
		
		add(addButton, BorderLayout.NORTH);
		add(removeButton, BorderLayout.SOUTH);
		setVisible(true);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			new CarsView();
		} catch (Exception e) {
			System.out.println("LOOK AND FELL NOT FOUND");
		}
	}
}