package cn.itcast.store.session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itcast.store.util.Book;
import cn.itcast.store.util.DB;

public class SessionBuy extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Book book = (Book) DB.getAll().get(id);//得到用户想买的书
		HttpSession session = request.getSession();//获取session
		List list = (List) session.getAttribute("list");//得到用户用于保存所有书的容器
		//如果session中不存在名为list的属性，就初始化list，并添加到session中
		if(list==null){
			list = new ArrayList();
			session.setAttribute("list", list);
		}
		list.add(book);
		//解决IE禁用Cookie后的session处理
		//用于对sendRedirect方法后的url地址进行重写
		String url = response.encodeRedirectURL("/day07/servlet/SessionCart");
		response.sendRedirect(url);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
