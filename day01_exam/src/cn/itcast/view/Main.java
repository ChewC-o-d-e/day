package cn.itcast.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import cn.itcast.dao.StudentDao;
import cn.itcast.domain.Student;

public class Main {

	public static void main(String[] args) {
		System.out.println("����ѧ�� (a)  ���ѧ��(b)  ɾ��ѧ��(c)  �޸�ѧ��(d)");
		System.out.print("������������ͣ�");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String type = br.readLine();
			if ("a".equals(type)) {
				System.out.print("������Ҫ��ѯ��ѧ�����֤�ţ�");
				String idcard = br.readLine();
				StudentDao sdao = new StudentDao();
				Student s = sdao.read(idcard);
				if (s == null) {
					System.out.println("δ�ҵ���ѧ����");
				} else {
					System.out.println("��ѧ����Ϣ����:\r������" + s.getName()
							+ "\t׼��֤�ţ�" + s.getExamid() + "\t���֤�ţ�"
							+ s.getIdcard() + "\t���ڵأ�" + s.getLocation()
							+ "\t�ɼ���" + s.getGrade());
				}
			} else if ("b".equals(type)) {
				try{
					//��ȡҪ��ӵ�ѧ����Ϣ
					System.out.print("������ѧ��������");
					String name = br.readLine();
					System.out.print("������ѧ��׼��֤�ţ�");
					String examid = br.readLine();
					System.out.print("������ѧ�����֤�ţ�");
					String idcard = br.readLine();
					System.out.print("������ѧ�����ڵأ�");
					String location = br.readLine();
					System.out.print("������ѧ���ɼ���");
					String grade = br.readLine();
					Student s = new Student();
					s.setExamid(examid);
					s.setIdcard(idcard);
					s.setName(name);
					s.setLocation(location);
					s.setGrade(Double.parseDouble(grade));
					StudentDao sdao = new StudentDao();
					sdao.add(s);
					System.out.println("��ӳɹ�");
				}catch(Exception e){
					System.out.println("���ʧ��");
				}
			} else if ("c".equals(type)) {
				try{
					System.out.print("������Ҫɾ����ѧ�����֤�ţ�");
					String idcard = br.readLine();
					StudentDao sdao = new StudentDao();
					sdao.delete(idcard);
					System.out.println("ɾ���ɹ�!!");
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
			} else if ("d".equals(type)) {
				System.out.print("������Ҫ�޸ĵ�ѧ�����֤�ţ�");
				String idcard = br.readLine();
				StudentDao sdao = new StudentDao();
				if(sdao.read(idcard) == null){
					System.out.println("δ�ҵ���ѧ����");
				}else{
					System.out.print("������ѧ��������");
					String name = br.readLine();
					System.out.print("������ѧ��ѧ��֤�ţ�");
					String examid = br.readLine();
					System.out.print("������ѧ�����ڵأ�");
					String location = br.readLine();
					System.out.print("������ѧ���ɼ���");
					String grade = br.readLine();
					
					Student s = new Student();
					s.setIdcard(idcard);
					s.setExamid(examid);
					s.setName(name);
					s.setLocation(location);
					s.setGrade(Double.parseDouble(grade));
					sdao.update(s);
					System.out.println("�޸ĳɹ�!!!");
				}
			} else {
				System.out.println("û�иò������ͣ������ԣ�");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
