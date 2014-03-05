package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.entity.Address;
import dao.service.AddressDao;

@WebServlet("/test")
public class Hello extends HttpServlet {
private static final long serialVersionUID = 1L;

@Override
public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException{
	PrintWriter out = response.getWriter();
	AddressDao ad = new AddressDao();
	Address a = new Address("Veldweg","103","2260","Westerlo");
	Address test = null;
	String testString = "";
	try {
		ad.insertAddress(a);
		int key = a.getId();
		if(key > 0){
			test = ad.findAddressByKey(key);
		}
		testString = "found:"+test;
			
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	out.println("<html>");
	out.println("<body>");
	out.println("<h1>Hello, just a quick test. Stored address: "+testString+"</h1>");
	out.println("</body>");
	out.println("</html>");	
}
}
