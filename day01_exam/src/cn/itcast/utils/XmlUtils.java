package cn.itcast.utils;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class XmlUtils {
	private static final String FILE = "src/xml/student.xml";
	
	public static Document getDocument() throws Exception{
		return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(FILE));
	}
	
	public static void write2Xml(Document document) throws Exception{
		TransformerFactory.newInstance().newTransformer().transform(new DOMSource(document), new StreamResult(new File(FILE)));
	}
}
