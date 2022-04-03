/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author LENOVO
 */
public class bank extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
           Class.forName("oracle.jdbc.OracleDriver");
             Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-3JDRGG3:1521/xe","oracle","abc");
        PreparedStatement ps=conn.prepareStatement("select * from bank");
      ResultSet rs=ps.executeQuery();
out.println("<html>");
out.println("<head>");
out.println("<style>tr,th,td:hover{background-color:white}  td{background-color:yellow;padding:10px } th{background-color:orange;} table{width:80vw;height:70vh; background-color:green ;margin-left:40px;padding:20px}}  </style>");
out.println("</head>");
out.print("<body>");
out.println("<h1 style='text-align:center' > Transaction History</h1>");
out.print("<table>");
out.println("<tr><th>Account No </th><th>Account Holder Name</th><th>Final Amount</th></tr>");
      while(rs.next())
         {
             out.println("<tr>");
             out.println("<td>");
             out.println(rs.getInt(1));
             out.println("</td><td>");
            out.println(rs.getString(2));
           out.println("</td><td>");
           
            out.println(rs.getInt(4));
            out.println("</td></tr>");
            
           
         }
//          
//        
//        }

        }
       catch(Exception e)
        {
          PrintWriter out;
          out=response.getWriter();
          out.print(("Exception ocuured"));
        }

    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
