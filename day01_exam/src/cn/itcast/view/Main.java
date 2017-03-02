package cn.itcast.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import cn.itcast.dao.StudentDao;
import cn.itcast.domain.Student;

public class Main {

	public static void main(String[] args) {
		System.out.println("查找学生 (a)  添加学生(b)  删除学生(c)  修改学生(d)");
		System.out.print("请输入操作类型：");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String type = br.readLine();
			if ("a".equals(type)) {
				System.out.print("请输入要查询的学生身份证号：");
				String idcard = br.readLine();
				StudentDao sdao = new StudentDao();
				Student s = sdao.read(idcard);
				if (s == null) {
					System.out.println("未找到该学生！");
				} else {
					System.out.println("该学生信息如下:\r姓名：" + s.getName()
							+ "\t准考证号：" + s.getExamid() + "\t身份证号："
							+ s.getIdcard() + "\t所在地：" + s.getLocation()
							+ "\t成绩：" + s.getGrade());
				}
			} else if ("b".equals(type)) {
				try{
					//获取要添加的学生信息
					System.out.print("请输入学生姓名：");
					String name = br.readLine();
					System.out.print("请输入学生准考证号：");
					String examid = br.readLine();
					System.out.print("请输入学生身份证号：");
					String idcard = br.readLine();
					System.out.print("请输入学生所在地：");
					String location = br.readLine();
					System.out.print("请输入学生成绩：");
					String grade = br.readLine();
					Student s = new Student();
					s.setExamid(examid);
					s.setIdcard(idcard);
					s.setName(name);
					s.setLocation(location);
					s.setGrade(Double.parseDouble(grade));
					StudentDao sdao = new StudentDao();
					sdao.add(s);
					System.out.println("添加成功");
				}catch(Exception e){
					System.out.println("添加失败");
				}
			} else if ("c".equals(type)) {
				try{
					System.out.print("请输入要删除的学生身份证号：");
					String idcard = br.readLine();
					StudentDao sdao = new StudentDao();
					sdao.delete(idcard);
					System.out.println("删除成功!!");
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
			} else if ("d".equals(type)) {
				System.out.print("请输入要修改的学生身份证号：");
				String idcard = br.readLine();
				StudentDao sdao = new StudentDao();
				if(sdao.read(idcard) == null){
					System.out.println("未找到该学生！");
				}else{
					System.out.print("请输入学生姓名：");
					String name = br.readLine();
					System.out.print("请输入学生学生证号：");
					String examid = br.readLine();
					System.out.print("请输入学生所在地：");
					String location = br.readLine();
					System.out.print("请输入学生成绩：");
					String grade = br.readLine();
					
					Student s = new Student();
					s.setIdcard(idcard);
					s.setExamid(examid);
					s.setName(name);
					s.setLocation(location);
					s.setGrade(Double.parseDouble(grade));
					sdao.update(s);
					System.out.println("修改成功!!!");
				}
			} else {
				System.out.println("没有该操作类型，请重试！");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
