package cn.itcast.store.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.store.util.Book;
import cn.itcast.store.util.DB;

public class CookieDetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//1.根据用户带过来的id，显示商品的详细信息
		String id = request.getParameter("id");
		Book book = (Book) DB.getAll().get(id);
		out.write("您要查看的书的详细信息如下：<br/><br/>");
		out.write(book.getId()+"<br/>");
		out.write(book.getName()+"<br/>");
		out.write(book.getAuthor()+"<br/>");
		out.write(book.getDescription()+"<br/>");
		//2.给用户回送包含当前商品id的cookie
		String bookHistory = makeHistory(request, id);
		Cookie cookie = new Cookie("bookHistory", bookHistory);
		cookie.setMaxAge(1*30*24*3600);
		response.addCookie(cookie);
	}

	private String makeHistory(HttpServletRequest request, String id) {
		String bookHistory = null;
		Cookie cookies[] = request.getCookies();
		for(int i=0;cookies!=null&&i<cookies.length;i++){
			Cookie cookie = cookies[i];
			if(cookie.getName().equals("bookHistory")){
				bookHistory = cookie.getValue();
			}
		}
		if(bookHistory==null)
			return id;
		List l = Arrays.asList(bookHistory.split("\\_"));
		LinkedList<String> list = new LinkedList();
		list.addAll(l);
		if(list.contains(id)){
			list.remove(id);
			list.addFirst(id);
		}else if(list.size()>=3){
			list.removeLast();
			list.addFirst(id);
		}else{
			list.addFirst(id);
		}
		StringBuffer sb = new StringBuffer();
		for(String lib : list){
			sb.append(lib+"_");
		}
		return sb.deleteCharAt(sb.length()-1).toString();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
