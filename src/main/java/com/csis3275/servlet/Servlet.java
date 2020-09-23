package com.csis3275.servlet;

//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Servlet implementation class Servlet
 */
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	       response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        try {
	           
	            String modelName = request.getParameter("modelName");
	            String pros = request.getParameter("pros");
	            String cons = request.getParameter("cons");
	            String[] prosArr = pros.split("\\r?\\n");
	            String[] consArr = cons.split("\\r?\\n");
	            
	            String html;
	            html = "<html>"
	            		+ "<head>"
	            			+ "<link href='style.css' rel='stylesheet'>"
	            		+ "</head>"
	            		+ "<body>"
	            			+ "<h3>" + modelName + "</h3>";
	            html += "<div class='textAreaColumn'>";
	            html += "<div>"
	            			+ "<table>"
	            				+ "<tr>"
	            					+ "<th>"
	            						+ "<h3>Pros:</h3>"
	            					+ "</th>"
	            				+ "</tr>";
	            
	            for (String pro : prosArr) {
	            	html += "<tr><td>* " + pro + "</td></tr>";
	            }
	            
	            html += "</table></div>";
	            
	            html += "<div>"
            				+ "<table>"
            					+ "<tr>"
            						+ "<th>"
            							+ "<h3>Cons:</h3>"
            						+ "</th>"
            					+ "</tr>";
            
            for (String con : consArr) {
            	html += "<tr><td>* " + con + "</td></tr>";
            }
            
            html += "</table></div></div>";
            
            html += "<br></br><br></br><br></br><br></br>"
            		+ "<div>"
            			+ "<form method='GET' action='Servlet/DownloadCSV'>"
            				+ "<button type='submit'>Download CSV</button>"
            			+ "</form>";
            
            html += "<form method='GET' action='Servlet/DownloadJSON'>"
            				+ "<button type='submit'>Download JSON</button>"
            			+ "</form>";
            html += "</div></body></html>";
            
            out.println(html);
 	            
            HandleFile newModel = new HandleFile(modelName, prosArr, consArr);
 
	        } finally 
	        {            
	            out.close();
	        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
