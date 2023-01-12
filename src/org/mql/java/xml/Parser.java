package org.mql.java.xml;

import org.mql.java.models.Voiture;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Parser {

	private DocumentBuilder documentBuilder;
	private Document document;
	private Set<Voiture> voitures;

	public Parser(String documentPath) {
		documentBuilder = null;
		document = null;
		voitures = new HashSet<>();
		initialize(documentPath);
		voitures = fetchCars();
	}

	public void initialize(String documentPath) {
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			document = documentBuilder.parse(documentPath);
			document.getDocumentElement().normalize();
		} catch (Exception e) {
			System.out.println("FILE PATH NOT FOUND");
		}
	}

	public void commitModification() {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult("resources/voitures.xml");
			transformer.transform(domSource, streamResult);
		} catch (Exception e) {
			System.out.println("TRANSFORMER FAILED");
		}
	}

	public Set<Voiture> fetchCars() {
		Set<Voiture> voitures = new HashSet<>();
		NodeList nodeList = document.getElementsByTagName("voiture");
		for (int temp = 0; temp < nodeList.getLength(); temp++) {
			Node node = nodeList.item(temp);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				Voiture voiture = new Voiture();
				voiture.setModel(element.getAttribute("model"));
				voiture.setMarque(element.getAttribute("marque"));
				voiture.setMatricule(element.getAttribute("matricule"));
				voiture.setColeur(element.getAttribute("coleur"));
				voiture.setAnnee(Integer.parseInt(element.getAttribute("annee")));
				voiture.setAutomatique(Boolean.parseBoolean(element.getAttribute("automatique")));
				voitures.add(voiture);
			}
		}
		return voitures;
	}

	public void addCar(Voiture voiture) {
		if (!checkIfAlreadyExists(voiture)) {
			Element racineElement = document.getDocumentElement();
			Element voitureElement = document.createElement("voiture");
			voitureElement.setAttribute("model", voiture.getModel());
			voitureElement.setAttribute("matricule", voiture.getMatricule());
			voitureElement.setAttribute("marque", voiture.getMarque());
			voitureElement.setAttribute("coleur", voiture.getColeur());
			voitureElement.setAttribute("annee", Integer.toString(voiture.getAnnee()));
			voitureElement.setAttribute("automatique", Boolean.toString(voiture.isAutomatique()));
			racineElement.appendChild(voitureElement);
			commitModification();
		}
	}
	
	public void modifyCar(Voiture voiture) {
		NodeList nodeList = document.getElementsByTagName("voiture");
		for (int temp = 0; temp < nodeList.getLength(); temp++) {
			Node node = nodeList.item(temp);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				if(element.getAttribute("matricule").equals(voiture.getMatricule())) {
					element.setAttribute("model", voiture.getModel());
					element.setAttribute("marque", voiture.getMarque());
					element.setAttribute("coleur", voiture.getColeur());
					element.setAttribute("annee", Integer.toString(voiture.getAnnee()));
					element.setAttribute("automatique", Boolean.toString(voiture.isAutomatique()));
					commitModification();		
				}
			}
		}
	}
	
	public void removeCar(Voiture voiture) {
		NodeList voitureList = document.getElementsByTagName("voiture");
		for (int i = 0; i < voitureList.getLength(); i++) {
			Node voitureNode = voitureList.item(i);
			if (voitureNode.getAttributes().getNamedItem("matricule").getNodeValue().equals(voiture.getMatricule())) {
				voitureNode.getParentNode().removeChild(voitureNode);
			}
		}
		commitModification();
	}
	
	public boolean checkIfAlreadyExists(Voiture voiture) {
		for (Voiture voitureTmp : voitures) {
			if (voitureTmp.getMatricule().equals(voiture.getMatricule())) {
				return true;
			}
		}
		return false;
	}

}
