/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ValidaSesionesServlet extends HttpServlet {

    

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String key = "";
        String user = "";

        ConectaDB miDB = null;
        
        response.setContentType("text/html");
        HttpSession sesion = request.getSession();
        String titulo = null;

        //Pedimos los atributos, y verificamos si existen
        key = (String) sesion.getAttribute("claveSesion");
        user = (String) sesion.getAttribute("usSesion");

        try {
            //Conexion a la base de datos, utiliza la clase ConectaDB
            miDB = new ConectaDB();
            miDB.conecta();
            ResultSet res = miDB.query("select * from Usuario where user='" + user + "' and password='" + key + "';");

            if (res.next()) {
                titulo = "llave correcta continua la sesion";
            }else{
                titulo = "llave incorrecta inicie nuevamente sesion";
            }

            miDB.cierra();
            
        } catch (SQLException ex) {
            
            Logger.getLogger(ValidaSesionesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Mostramos los  valores en el cliente
        PrintWriter out = response.getWriter();
        out.println("¿Continua la Sesion y es valida?: " + titulo);
        out.println("<br>");
        out.println("ID de la sesi&oacute;n JSESSIONID: " + sesion.getId());

    }

}
