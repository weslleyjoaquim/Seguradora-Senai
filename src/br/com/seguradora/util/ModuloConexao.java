package br.com.seguradora.util;


import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;

public class ModuloConexao {
    
    public static Connection conector(){
        java.sql.Connection conexao = null;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/seguradora";
        String user = "root";
        String password = "P@ss123";
        
        try{
            Class.forName(driver);
            conexao = DriverManager.getConnection(url,user,password);
            return conexao;
            
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
}
