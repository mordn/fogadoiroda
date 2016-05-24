package hu.unideb.inf.bead.fogadoiroda;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import hu.unideb.inf.bead.fogadoiroda.model.Felhasznalo;

public class Xml {
    public Xml() {
   /*     try {
			init();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    }
	
  public void init() throws ParserConfigurationException, TransformerException {
	  
	  List<File> filesList = new ArrayList<File>();
      filesList.add(new File("felhasznalok.xml"));
      for (File file : filesList) { 
          if (!file.exists()) {
                  DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                  DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                  Document doc = docBuilder.newDocument();

                  Element root = doc.createElement("felhasznalok");
          		doc.appendChild(root);
          		Element employee = doc.createElement("felhasznalo");
          		root.appendChild(employee);
          		
          		Element name = doc.createElement("fnev");
          		name.appendChild(doc.createTextNode("alap"));
          		employee.appendChild(name);
          		
          		Element jelszo = doc.createElement("pass");
          		jelszo.appendChild(doc.createTextNode("alap"));
          		employee.appendChild(jelszo);
          		
          		Element vnev = doc.createElement("vnev");
          		vnev.appendChild(doc.createTextNode("Vezetéknevem"));
          		employee.appendChild(vnev);
          		
          		Element knev = doc.createElement("knev");
          		knev.appendChild(doc.createTextNode("Keresztnevem"));
          		employee.appendChild(knev);
          		
          		Element egyenleg = doc.createElement("egyenleg");
          		egyenleg.appendChild(doc.createTextNode("5000"));
          		employee.appendChild(egyenleg);
                  
                  
                  TransformerFactory transformerFactory = TransformerFactory.newInstance();
                  Transformer transformer = transformerFactory.newTransformer();
                  DOMSource source = new DOMSource(doc);
                  StreamResult result = new StreamResult(new File("felhasznalok.xml"));

                  transformer.transform(source, result);
          }
      
      }    
	  
  }
  public List<Felhasznalo> beolvas(List<Felhasznalo> felhasznalok){
	  List<Felhasznalo> felhasznalolista = new ArrayList<Felhasznalo>();
      SAXParserFactory factory = SAXParserFactory.newInstance();
      SAXParser saxParser = null;
	try {
		saxParser = factory.newSAXParser();
	} catch (ParserConfigurationException e1) {
		e1.printStackTrace();
	} catch (SAXException e1) {
		e1.printStackTrace();
	}
      
      DefaultHandler handler = new DefaultHandler(){
    	  Felhasznalo felhaszn = new Felhasznalo();
    	  boolean bfnev=false;
    	  boolean bjelszo1=false;
    	  boolean bvnev=false;
    	  boolean bknev=false;
    	  boolean begyenleg=false;
    	  
    	  //mindig lefut amikor a parser kap egy '<' nyitó tagot
    	  public void startElement(String uri, String localName, String qName,
    			  Attributes attributes) throws SAXException{
    		  if(qName.equalsIgnoreCase("felhasznalo")) {
    				felhaszn = new Felhasznalo();		  
    			  }
    		  if (qName.equalsIgnoreCase("fnev")){
    			  bfnev=true;
    		  }
    		  if (qName.equalsIgnoreCase("pass")){
    			  bjelszo1=true;
    		  }

    		  if (qName.equalsIgnoreCase("vnev")){
    			  bvnev=true;
    		  }
    		  if (qName.equalsIgnoreCase("knev")){
    			  bknev=true;
    		  }
    		  if (qName.equalsIgnoreCase("egyenleg")){
    			  begyenleg=true;
    		  }
    	  }
    	//mindig lefut amikor a parser kap egy '>' záró tagot
    	  public void endElement(String uri, String localName, String qName) throws SAXException{	
    	  }
    	  public void characters(char ch[], int start, int lenght) throws SAXException{
    		  if(bfnev){
				felhaszn.setFelhnev(new String(ch,start,lenght));
    			  bfnev=false;
    		  }
       		  if(bjelszo1){
    			  felhaszn.setJelszo(new String(ch,start,lenght));
    			  bjelszo1=false;
    		  }

       		  if(bvnev){
       			  felhaszn.setVnev(new String(ch,start,lenght));
       			  bvnev=false;
       		  }
       		  if(bknev){
       			  felhaszn.setKnev(new String(ch,start,lenght));
       			  bknev=false;
       		  }
       		  if(begyenleg){
       			  felhaszn.setEgyenleg( Double.parseDouble(new String(ch,start,lenght)));
       			  begyenleg=false;

       			 felhasznalolista.add(felhaszn);
       		  }
    	  }    
      };
      
      try {
    
		saxParser.parse("felhasznalok.xml", handler);
	} catch (SAXException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} 
       
       
       
       return felhasznalolista;
  } 
  public void filebair(List<Felhasznalo> felhasznalolista){
	  DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = null;
	try {
		docBuilder = docFactory.newDocumentBuilder();
	} catch (ParserConfigurationException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}

      Document doc = docBuilder.newDocument();

      Element root = doc.createElement("felhasznalok");
		doc.appendChild(root);
		
		for (int i = 0; i < felhasznalolista.size(); i++) {		
		
		Element employee = doc.createElement("felhasznalo");
		root.appendChild(employee);
		
		Element name = doc.createElement("fnev");
		name.appendChild(doc.createTextNode(felhasznalolista.get(i).getFelhnev()));
		employee.appendChild(name);
		
		Element jelszo = doc.createElement("pass");
		jelszo.appendChild(doc.createTextNode(felhasznalolista.get(i).getJelszo()));
		employee.appendChild(jelszo);
		
		Element vnev = doc.createElement("vnev");
		vnev.appendChild(doc.createTextNode(felhasznalolista.get(i).getVnev()));
		employee.appendChild(vnev);
		
		Element knev = doc.createElement("knev");
		knev.appendChild(doc.createTextNode(felhasznalolista.get(i).getKnev()));
		employee.appendChild(knev);
		
		Element egyenleg = doc.createElement("egyenleg");
		egyenleg.appendChild(doc.createTextNode(Double.toString(felhasznalolista.get(i).getEgyenleg())));
		employee.appendChild(egyenleg);
      
		}  
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = null;
	try {
		transformer = transformerFactory.newTransformer();
	} catch (TransformerConfigurationException e1) {
		e1.printStackTrace();
	}
      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(new File("felhasznalok.xml"));

      try {
		transformer.transform(source, result);
	} catch (TransformerException e) {
		e.printStackTrace();
	}     
  }
  
  
}
