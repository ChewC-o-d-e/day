package cn.itcast.sax;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
/**
 * 得到xml文档内容的事件处理器
 * @author Administrator
 *
 */
public class MyContentHandler implements ContentHandler {

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		System.out.println("当前解析到了："+qName+",这个标签是开始标签");
		for(int i=0;i<atts.getLength();i++){
			String name = atts.getQName(i);
			String value = atts.getValue(i);
			System.out.println(name+"="+value);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		System.out.println("当前解析到了："+qName+",这个标签是结束标签");
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		System.out.println("当前解析到了内容："+new String(ch,start,length));
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void processingInstruction(String target, String data)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub

	}

	@Override
	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		// TODO Auto-generated method stub

	}

}
