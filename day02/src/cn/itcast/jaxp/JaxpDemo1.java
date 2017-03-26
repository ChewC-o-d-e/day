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
	 * TODO 获取节点的值
	 * @param str 节点名称
	 * @param index 节点下标
	 */
	public void read(String str, int index) {
		// 1.获取工厂
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			// 2.产生解析器
			DocumentBuilder builder = factory.newDocumentBuilder();
			// 3.解析xml文档，得到代表文档的document
			Document document = builder.parse(new File(FILE));
			// 得到售价节点的值
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
	 * TODO 修改节点的值
	 * @param str 要修改的节点名称
	 * @param index	节点下标
	 * @param value	修改后的值
	 */
	public void update(String str, int index, String value) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(FILE));
			Node node = document.getElementsByTagName(str).item(index);
			node.setTextContent(value);
			// 把内存中的document写到xml文档
			TransformerFactory tf = TransformerFactory.newInstance();
			// 得到转换器
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
	 * TODO 向指定节点增加孩子节点
	 * @param str 要添加的节点名称
	 * @param value 节点内文字
	 * @param index 节点下标
	 */
	public void add(String str,String value,int index){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(FILE));
			//创建需要增加的节点
			Node node = document.createElement(str);
			node.setTextContent(value);
			//得到需要添加的节点的父节点
			Node parent = document.getElementsByTagName("书").item(index);
			//把需要增加的节点挂到父节点上
			parent.appendChild(node);
			//在指定节点位置后添加新节点
			//parent.insertBefore(node, document.getElementsByTagName("书名").item(0));
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
	 * TODO 删除指定节点
	 * @param str 要删除的节点
	 * @param index 要删除节点的位置
	 */
	public void delete(String str, int index) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(FILE));
			Node node = document.getElementsByTagName(str).item(index);
			// 通过父节点删除子节点
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
