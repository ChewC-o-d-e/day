package cn.itcast.jaxp;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class JaxpDemo1 {
	private static final String FILE = "src/xml/book.xml";
	
	/**
	 * TODO ��ȡ�ڵ��ֵ
	 * @param str �ڵ�����
	 * @param index �ڵ��±�
	 */
	public void read(String str, int index) {
		// 1.��ȡ����
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			// 2.����������
			DocumentBuilder builder = factory.newDocumentBuilder();
			// 3.����xml�ĵ����õ������ĵ���document
			Document document = builder.parse(new File(FILE));
			// �õ��ۼ۽ڵ��ֵ
			NodeList nlist = document.getElementsByTagName(str);
			Node node = nlist.item(index);
			String value = node.getTextContent();
			System.out.println(value);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * TODO �޸Ľڵ��ֵ
	 * @param str Ҫ�޸ĵĽڵ�����
	 * @param index	�ڵ��±�
	 * @param value	�޸ĺ��ֵ
	 */
	public void update(String str, int index, String value) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(FILE));
			Node node = document.getElementsByTagName(str).item(index);
			node.setTextContent(value);
			// ���ڴ��е�documentд��xml�ĵ�
			TransformerFactory tf = TransformerFactory.newInstance();
			// �õ�ת����
			Transformer t = tf.newTransformer();
			t.transform(new DOMSource(document), new StreamResult(new File(FILE)));
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * TODO ��ָ���ڵ����Ӻ��ӽڵ�
	 * @param str Ҫ��ӵĽڵ�����
	 * @param value �ڵ�������
	 * @param index �ڵ��±�
	 */
	public void add(String str,String value,int index){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(FILE));
			//������Ҫ���ӵĽڵ�
			Node node = document.createElement(str);
			node.setTextContent(value);
			//�õ���Ҫ��ӵĽڵ�ĸ��ڵ�
			Node parent = document.getElementsByTagName("��").item(index);
			//����Ҫ���ӵĽڵ�ҵ����ڵ���
			parent.appendChild(node);
			//��ָ���ڵ�λ�ú�����½ڵ�
			//parent.insertBefore(node, document.getElementsByTagName("����").item(0));
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			t.transform(new DOMSource(document), new StreamResult(new File(FILE)));
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * TODO ɾ��ָ���ڵ�
	 * @param str Ҫɾ���Ľڵ�
	 * @param index Ҫɾ���ڵ��λ��
	 */
	public void delete(String str, int index) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(FILE));
			Node node = document.getElementsByTagName(str).item(index);
			// ͨ�����ڵ�ɾ���ӽڵ�
			node.getParentNode().removeChild(node);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			t.transform(new DOMSource(document), new StreamResult(new File(FILE)));
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
}
