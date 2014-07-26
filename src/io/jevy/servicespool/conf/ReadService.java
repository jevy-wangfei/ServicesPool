package io.jevy.servicespool.conf;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadService {

	private List<Service> serviceList = new ArrayList<Service>();
	private String configFile = "./Services.xml";
	private static Logger log = Logger.getLogger(ReadService.class.getName());

	public ReadService() {
		// Let the setServices method to read xml file and set the services in
		// the list of services.
		this.parseXMLtoServiceList();
	}

	public ReadService(String configFile) {
		// Let the setServices method to read xml file and set the services in
		// the list of services.
		this.configFile = configFile;
		this.parseXMLtoServiceList();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ReadService().print();
	}

	private Document getDocument(String configFile) {

		DocumentBuilder dombuilder = null;
		InputStream is = null;
		Document doc = null;
		try {
			dombuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			log.error(e);
		}

		try {
			if (this.configFile.equals(null)) {
				log.info("configFile doesn't initalized. \n"
						+ "You should initalize this class by constractor ConfigReader(String configFile).\n"
						+ "Or, you can set the configureFile by the methord setConfigFile(String configFile).");
			}
			is = new FileInputStream(configFile);
		} catch (FileNotFoundException e) {
			log.error(e);
		}
		try {
			doc = dombuilder.parse(is);
		} catch (SAXException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		}
		return doc;
	}

	private void parseXMLtoServiceList() {
		// Read services from XML file and Set them in the list of services
		Document doc = getDocument(this.configFile);
		doc.normalizeDocument();
		Element root = doc.getDocumentElement();

		NodeList xmlNameScope = root.getElementsByTagName("ServicesScope");
		log.info("The Services configure file contains "
				+ xmlNameScope.getLength() + " ServicesScope(s).");
		for (int i = 0; i < xmlNameScope.getLength(); i++) {
			Element nameScopeElement = (Element) xmlNameScope.item(i);
			String serviceScope = nameScopeElement.getAttribute("name").trim();
			NodeList xmlService = nameScopeElement.getElementsByTagName("Service");
		
			if (nameScopeElement.getNodeType() == Node.ELEMENT_NODE) {

				log.info("Reading Service from  " + serviceScope + " ......");
				for (int k = 0; k < xmlService.getLength(); k++) {
					Service serviceInst = new Service();
					Element service = (Element) xmlService.item(k);
					serviceInst.setSerivceScope(serviceScope);
					serviceInst.setServiceName(service.getAttribute("name").trim());
					
					NodeList serviceAttr = service.getChildNodes();
					
					for(int q=0; q<serviceAttr.getLength(); q++){
						Node attr = serviceAttr.item(q);
						if(attr.getNodeType()==Node.ELEMENT_NODE){
							if("state".equals(attr.getNodeName())){
								serviceInst.setState("1".equals(attr.getTextContent().trim())?true:false);
							}else if("version".equals(attr.getNodeName())){
								serviceInst.setVersion(attr.getTextContent().trim());
							}else if("SQL".equals(attr.getNodeName())){
								serviceInst.setSQL(attr.getTextContent().trim());
							}
							
						}
					}
					
					if (serviceInst.getServiceName()!=null && serviceInst.getSQL()!=null) {
						serviceList.add(serviceInst);
						log.info(" Service: #" + k + serviceInst.getServiceName());
					}
				}
			}
		}
	}

	public List<Service> getServiceList() {
		return serviceList;
	}
	
	private void print(){
		for(int i=0;i<serviceList.size();i++){
			Service s = serviceList.get(i);
			System.out.println(s.getSerivceScope()+":"+s.getServiceName()+":"+
			s.getVersion()+":"+s.getState()+":"+s.getSQL());
		}
	}

}
