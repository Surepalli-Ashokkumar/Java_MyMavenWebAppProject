package com.cisco.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/fetch")
public class FetchServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=resp.getWriter();
		Connection conn=DBConfig.getConnections();
		
		resp.setContentType("text/html");
		
		if(conn!=null) {
			out.print("Connection Established");
			
			try {
				Statement stmt= conn.createStatement();
				ResultSet rs= stmt.executeQuery("select * from employee");
				
				while(rs.next()) {
					out.println();
					out.println(rs.getInt(1)+", "+rs.getString(2)+", "+rs.getString(3)+", "+rs.getString(4)+"<br>");
				}
			} catch (SQLException e) {
				// TODO: handle exception
			}
			 
		}else {
			out.print("Error While Connecting");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	

}
