package servlets;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConectaDB {
    //Variables de conexion
    Connection cnex = null;
    Statement state;
    ResultSet res;
    
    public ConectaDB(){
        
    }
    public void conecta(){//Metodo para conectar
        try{
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        cnex = DriverManager.getConnection("jdbc:mysql://localhost/Lab3","root","n0m3l0");
        System.out.println("Si conecta");
        
        }catch(Exception eeee){
            System.out.print("No conecta");
        }
    }
    public void cierra() throws SQLException{//Metodo para cerrar conexión
        cnex.close();
    }
    //Métodos para ejecutar sentencias SQL en la BD
    public int update(String update) throws SQLException{//Para hacer insert, update, o delete
        state = cnex.createStatement();
        return state.executeUpdate(update);
    }
    public ResultSet query(String query) throws SQLException{//Para hacer select
        state = cnex.createStatement();
        return state.executeQuery(query);
    }
}
