package cn.itcast.dao;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import cn.itcast.domain.Student;
import cn.itcast.utils.XmlUtils;

public class StudentDao {
	public Student read(String idcard){
		try {
			// 获取document
			Document document = XmlUtils.getDocument();
			// 获取所有student节点
			NodeList list = document.getElementsByTagName("student");
			//遍历所有节点
			for(int i=0;i<list.getLength();i++){
				//获取student节点，并强转为Element使用更多方法
				Element stu = (Element) list.item(i);
				//与传入的值进行匹配
				if(idcard.equals(stu.getAttribute("idcard"))){
					//把各节点的值，传入Student对象的引用
					Student s = new Student();
					s.setIdcard(stu.getAttribute("idcard"));
					s.setExamid(stu.getAttribute("examid"));
					s.setName(stu.getElementsByTagName("name").item(0).getTextContent());
					s.setLocation(stu.getElementsByTagName("location").item(0).getTextContent());
					s.setGrade(Double.parseDouble(stu.getElementsByTagName("grade").item(0).getTextContent()));
					//返回查询到的student
					return s;
				}
			}
			//未匹配，则返回null
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void add(Student student){
		try {
			// 获取document
			Document document = XmlUtils.getDocument();
			//创建student节点，并添加节点属性
			Element stu = document.createElement("student");
			stu.setAttribute("idcard", student.getIdcard());
			stu.setAttribute("examid", student.getExamid());
			//创建子节点，赋值，添加到父节点
			Element name = document.createElement("name");
			name.setTextContent(student.getName());
			stu.appendChild(name);
			Element location = document.createElement("location");
			location.setTextContent(student.getLocation());
			stu.appendChild(location);
			Element grade = document.createElement("grade");
			grade.setTextContent(student.getGrade()+"");
			stu.appendChild(grade);
			//获取根节点，把student添加到根节点上
			document.getElementsByTagName("exam").item(0).appendChild(stu);
			//写入xml文档中
			XmlUtils.write2Xml(document);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void delete(String idcard){
		try {
			// 获取document
			Document document = XmlUtils.getDocument();
			// 获取所有student节点
			NodeList list = document.getElementsByTagName("student");
			//遍历所有节点
			for(int i=0;i<list.getLength();i++){
				Element stu = (Element) list.item(i);
				//与传入的值进行匹配
				if(stu.getAttribute("idcard").equals(idcard)){
					//获取父节点，删除子节点
					stu.getParentNode().removeChild(stu);
					//与xml文档同步
					XmlUtils.write2Xml(document);
					//正确删除则return，结束方法，不进行下面的操作
					return;
				}
			}
			//若删除失败，则返回运行时异常
			throw new RuntimeException("删除失败!");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void update(Student student){
		try {
			// 获取document
			Document document = XmlUtils.getDocument();
			// 获取所有student节点
			NodeList list = document.getElementsByTagName("student");
			//遍历所有节点
			for(int i=0;i<list.getLength();i++){
				Element stu = (Element) list.item(i);
				//与传入的值进行匹配
				if(stu.getAttribute("idcard").equals(student.getIdcard())){
					//用传入的值，覆盖原值
					stu.setAttribute("examid", student.getExamid());
					stu.getElementsByTagName("name").item(0).setTextContent(student.getName());
					stu.getElementsByTagName("location").item(0).setTextContent(student.getLocation());
					stu.getElementsByTagName("grade").item(0).setTextContent(student.getGrade()+"");
					//同步到xml文档中
					XmlUtils.write2Xml(document);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}
