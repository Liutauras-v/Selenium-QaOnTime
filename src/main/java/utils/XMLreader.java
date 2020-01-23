package utils;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLreader {

	private String filePath;
	
	public XMLreader(String filePath) {
		this.filePath = filePath;
	}
	
	public String configData(String commonTag) {
		try {
			File File = new File(this.filePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(File);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("config");
			Node nNode = nList.item(0);
			System.out.println("\nCurrent Element :" + nNode.getNodeName());
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				return eElement.getElementsByTagName(commonTag).item(0).getTextContent();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";

	}
	
	public String getUserName() {
		return configData("userNameXml");
	}

	public String getPassword() {
		return configData("paswordXml");
	}

	public String getUrl() {
		return configData("urlXml");
	}
	
}
