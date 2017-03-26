package cn.itcast.store.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.store.util.Book;
import cn.itcast.store.util.DB;

public class CookieIndexServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//1.显示网站所有商品
		out.write("本网站有如下书籍：<br/><br/>");
		Set<Map.Entry<String, Book>> set =  DB.getAll().entrySet();
		for(Map.Entry<String, Book> map : set){
			Book book = map.getValue();
			out.write("<a href='/day07/servlet/CookieDetailServlet?id="+book.getId()+"' target='_blank'>"+book.getName()+"</a><br/>");
		}
		//2.显示用户曾经浏览过的商品
		out.write("<br/><br/>您曾经浏览过的商品：<br/>");
		Cookie cookies[] = request.getCookies();
		for(int i=0;cookies!=null&&i<cookies.length;i++){
			Cookie cookie = cookies[i];
			if(cookie.getName().equals("bookHistory")){
				String bookHistory = cookie.getValue();
				String ids[] = bookHistory.split("\\_");
				for(String id : ids){
					Book book = (Book) DB.getAll().get(id);
					out.write(book.getName()+"<br/>");
				}
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
