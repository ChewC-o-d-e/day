package cn.itcast.store.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.store.util.Book;
import cn.itcast.store.util.DB;

public class SessionIndex extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//首页：列出所有书
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		request.getSession();
		out.write("本网站有如下书：<br/>");
		Set<Map.Entry<String, Book>> set = DB.getAll().entrySet();
		for(Map.Entry<String, Book> map : set){
			Book book = map.getValue();
			String url = "/day07/servlet/SessionBuy?id="+book.getId();
			//解决IE禁用Cookie后的session处理
			//用于对表单action和超链接的url地址进行重写
			url = response.encodeURL(url);
			out.println(book.getName()+" <a href='"+url+"'>购买</a><br/>");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
