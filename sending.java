/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author LENOVO
 */
public class sending extends HttpServlet {

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
         Class.forName("oracle.jdbc.OracleDriver");
                Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-3JDRGG3:1521/xe","oracle","abc");
             PreparedStatement ps=conn.prepareStatement("select * from bank");
      ResultSet rs=ps.executeQuery();
out.println("<html>");
out.println("<head>");
out.println("<style>tr,th,td:hover{background-color:white}  td{background-color:yellow;padding:10px } th{background-color:orange;} table{width:80vw;height:70vh; background-color:green ;margin-left:10px;padding:20px}}  </style>");
out.println("</head>");
out.print("<body>");
  String  sender=request.getParameter("sender");
            String reciever=request.getParameter("reciever");
          String amount=request.getParameter("amount");
      Integer amt=Integer.parseInt(amount);
            
            ps=conn.prepareStatement("Update bank set amount=? where  SENDERNAME =? AND RECIEVERNAME=?");
            int count=0;
            PreparedStatement ps2=conn.prepareStatement("select * from bank");
            rs=ps2.executeQuery();
            String senderName="";
           
            int amountleft=0;
          while(rs.next())
            {
                
                if(sender.equals(rs.getString(2))&&reciever.equals(rs.getString(3)))
                {
                    count++;
                   
                    senderName=rs.getString(2);
                   reciever=rs.getString(3);
                   int totalamount=rs.getInt(4);
                   
                    ps.setInt(1, totalamount-amt);
                 
                ps.setString(2,sender);
                ps.setString(3, reciever);
                ps.executeUpdate();
                
                }
            }
         
         
ps2=conn.prepareStatement("select * from bank");
            rs=ps2.executeQuery();


out.println("<h1 style='text-align:center' > After Sending Money </h1>");
out.print("<table>");
out.println("<tr><th>Account No </th><th>Sender Name</th><th>Reciever Name</th><th>Final Amount</th></tr>");
      while(rs.next())
         {
             out.println("<tr>");
             out.println("<td>");
             out.println(rs.getInt(1));
             out.println("</td><td>");
            out.println(rs.getString(2));
           out.println("</td><td>");
            out.println(rs.getString(3));
              out.println("</td><td>");
            out.println(rs.getString(4));
            out.println("</td></tr>");
            
           
         }
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            /* TODO output your page here. You may use following sample code. */
           

//            ps.execute();

          
       
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
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
