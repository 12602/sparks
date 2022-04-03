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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author LENOVO
 */
@WebServlet(urlPatterns = {"/recieve"})
public class recieve extends HttpServlet {

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
             /* TODO output your page here. You may use following sample code. */
        String name=request.getParameter("sender");
       
        String amt=request.getParameter("amount");
        Integer amount=Integer.parseInt(amt);
        try
        {
            
     
                  Class.forName("oracle.jdbc.OracleDriver");
               Connection  conn=DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-3JDRGG3:1521/xe","oracle","abc");
     PreparedStatement ps1=conn.prepareStatement("select * from bank");
     PreparedStatement ps2=conn.prepareStatement("update bank set amount=? where SENDERNAME=?");
    
     ResultSet rs=ps1.executeQuery();
    
     String n="";
     int total=0;
     
     
    int count=0;
     while(rs.next())
     {
         total=0;
         if(rs.getString(2).equalsIgnoreCase(name))
         {
             count++;
             int a=rs.getInt(4);
             a=a+amount;
             n=rs.getString(2);
             total=a;
        
             ps2.setInt(1, a);
         ps2.setString(2,name);
             ps2.executeUpdate();
            
        }
     }
     
     
     
     ps2=conn.prepareStatement("select * from bank");
            rs=ps2.executeQuery();
out.println("<html>");
out.println("<head>");
out.println("<style>tr,th,td:hover{background-color:white}  td{background-color:yellow;padding:10px } th{background-color:orange;} table{width:80vw;height:70vh; background-color:green ;margin-left:10px;padding:20px}}  </style>");
out.println("</head>");
out.print("<body>");


out.print("<table>");
out.println("<h1 style='text-align:center' > After Transfering  Money </h1>");
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
            

       
        }
        catch(Exception e)
        {
            out.print("Exception");
        
          }
          
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
