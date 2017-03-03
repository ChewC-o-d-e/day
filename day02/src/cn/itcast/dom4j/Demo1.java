package cn.itcast.dom4j;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

public class Demo1 {
	private static final String FILE = "src/xml/book.xml";
	@Test
	public void read() throws Exception{
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(FILE));
		Element root = document.getRootElement();
		Element bookname = root.element("��").element("����");
		System.out.println(bookname.getText());
	}
	
	@Test
	public void readattr() throws Exception{
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(FILE));
		Element root = document.getRootElement();
		String value = root.element("��").attributeValue("name");
		System.out.println(value);
	}
	
	@Test
	public void add() throws Exception{
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(FILE));
		Element price = DocumentHelper.createElement("�ۼ�");
		price.setText("19Ԫ");
		document.getRootElement().element("��").add(price);
		/*
		 * �ַ��������ʽ����:FileWriter(FILE);Ĭ��ʹ��GB2312�ַ����
		 * ���������ʹ���ֽ������
		 */
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream(FILE), format);
		writer.write(document);//utf-8
		writer.close();
	}
	
	@Test
	public void update()throws Exception{
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(FILE));
		Element price = (Element) document.getRootElement().element("��").elements("�ۼ�").get(1);
		price.setText("209Ԫ");
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream(FILE), format);
		writer.write(document);
		writer.close();
	}
	
	@Test
	public void delete() throws Exception{
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(FILE));
		Element price = (Element) document.getRootElement().element("��").elements("�ۼ�").get(1);
		price.getParent().remove(price);
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream(FILE),format);
		writer.write(document);
		writer.close();
	}
	
	@Test
	public void add2() throws Exception{
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(FILE));
		Element price = DocumentHelper.createElement("�ۼ�");
		price.setText("11Ԫ");
		List list = document.getRootElement().element("��").elements();
		list.add(1, price);
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream(FILE),format);
		writer.write(document);
		writer.close();
	}
}
