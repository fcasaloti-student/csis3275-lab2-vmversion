package com.csis3275.servlet;
import javax.servlet.annotation.WebServlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Servlet/DownloadCSV")
public class DownloadCSV extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadCSV() {
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

	        try {
	           
	        	// reads input file from an absolute path
	            //String filePath = "C:\\Users\\fcasa\\eclipse-workspace\\main\\data.csv";
//				Path path = Paths.get(System.getProperty("user.home"), "data.csv");
//				String filePath = path.toString();
	        	
				String path1 = this.getClass().getClassLoader().getResource("").getPath();
				String fullPath = "";
				try {
					fullPath = URLDecoder.decode(path1, "UTF-8");
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String pathArr[] = fullPath.split("/WEB-INF/classes/");
				String filePath = pathArr[0];
	        	
	            File downloadFile = new File(filePath, "data.csv");
	            FileInputStream inStream = new FileInputStream(downloadFile);

	            // obtains ServletContext
	            ServletContext context = getServletContext();
	             
	            // gets MIME type of the file
	            String mimeType = context.getMimeType(filePath);
	            if (mimeType == null) {        
	                // set to binary type if MIME mapping not found
	                mimeType = "application/octet-stream";
	            }
	            System.out.println("MIME type: " + mimeType);
	             
	            // modifies response
	            response.setContentType(mimeType);
	            response.setContentLength((int) downloadFile.length());
	             
	            // forces download
	            String headerKey = "Content-Disposition";
	            String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
	            response.setHeader(headerKey, headerValue);
	             
	            // obtains response's output stream
	            OutputStream outStream = response.getOutputStream();
	             
	            byte[] buffer = new byte[4096];
	            int bytesRead = -1;
	             
	            while ((bytesRead = inStream.read(buffer)) != -1) {
	                outStream.write(buffer, 0, bytesRead);
	            }
	            inStream.close();
	            outStream.close();     
	        }
	        finally
	        {            

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