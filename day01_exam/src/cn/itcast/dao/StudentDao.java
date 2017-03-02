package cn.itcast.dao;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import cn.itcast.domain.Student;
import cn.itcast.utils.XmlUtils;

public class StudentDao {
	public Student read(String idcard){
		try {
			// ��ȡdocument
			Document document = XmlUtils.getDocument();
			// ��ȡ����student�ڵ�
			NodeList list = document.getElementsByTagName("student");
			//�������нڵ�
			for(int i=0;i<list.getLength();i++){
				//��ȡstudent�ڵ㣬��ǿתΪElementʹ�ø��෽��
				Element stu = (Element) list.item(i);
				//�봫���ֵ����ƥ��
				if(idcard.equals(stu.getAttribute("idcard"))){
					//�Ѹ��ڵ��ֵ������Student���������
					Student s = new Student();
					s.setIdcard(stu.getAttribute("idcard"));
					s.setExamid(stu.getAttribute("examid"));
					s.setName(stu.getElementsByTagName("name").item(0).getTextContent());
					s.setLocation(stu.getElementsByTagName("location").item(0).getTextContent());
					s.setGrade(Double.parseDouble(stu.getElementsByTagName("grade").item(0).getTextContent()));
					//���ز�ѯ����student
					return s;
				}
			}
			//δƥ�䣬�򷵻�null
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void add(Student student){
		try {
			// ��ȡdocument
			Document document = XmlUtils.getDocument();
			//����student�ڵ㣬����ӽڵ�����
			Element stu = document.createElement("student");
			stu.setAttribute("idcard", student.getIdcard());
			stu.setAttribute("examid", student.getExamid());
			//�����ӽڵ㣬��ֵ����ӵ����ڵ�
			Element name = document.createElement("name");
			name.setTextContent(student.getName());
			stu.appendChild(name);
			Element location = document.createElement("location");
			location.setTextContent(student.getLocation());
			stu.appendChild(location);
			Element grade = document.createElement("grade");
			grade.setTextContent(student.getGrade()+"");
			stu.appendChild(grade);
			//��ȡ���ڵ㣬��student��ӵ����ڵ���
			document.getElementsByTagName("exam").item(0).appendChild(stu);
			//д��xml�ĵ���
			XmlUtils.write2Xml(document);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void delete(String idcard){
		try {
			// ��ȡdocument
			Document document = XmlUtils.getDocument();
			// ��ȡ����student�ڵ�
			NodeList list = document.getElementsByTagName("student");
			//�������нڵ�
			for(int i=0;i<list.getLength();i++){
				Element stu = (Element) list.item(i);
				//�봫���ֵ����ƥ��
				if(stu.getAttribute("idcard").equals(idcard)){
					//��ȡ���ڵ㣬ɾ���ӽڵ�
					stu.getParentNode().removeChild(stu);
					//��xml�ĵ�ͬ��
					XmlUtils.write2Xml(document);
					//��ȷɾ����return����������������������Ĳ���
					return;
				}
			}
			//��ɾ��ʧ�ܣ��򷵻�����ʱ�쳣
			throw new RuntimeException("ɾ��ʧ��!");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void update(Student student){
		try {
			// ��ȡdocument
			Document document = XmlUtils.getDocument();
			// ��ȡ����student�ڵ�
			NodeList list = document.getElementsByTagName("student");
			//�������нڵ�
			for(int i=0;i<list.getLength();i++){
				Element stu = (Element) list.item(i);
				//�봫���ֵ����ƥ��
				if(stu.getAttribute("idcard").equals(student.getIdcard())){
					//�ô����ֵ������ԭֵ
					stu.setAttribute("examid", student.getExamid());
					stu.getElementsByTagName("name").item(0).setTextContent(student.getName());
					stu.getElementsByTagName("location").item(0).setTextContent(student.getLocation());
					stu.getElementsByTagName("grade").item(0).setTextContent(student.getGrade()+"");
					//ͬ����xml�ĵ���
					XmlUtils.write2Xml(document);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}
