package cn.itcast.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyContentHandler2 extends DefaultHandler {

	private boolean isOK = false;
	private int index = 1;
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if(isOK==true && index==1){
			System.out.println(new String(ch,start,length));
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if(qName.equals("售价")){
			isOK = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if(qName.equals("售价")){
			isOK = false;
			index++;
		}
	}
}
