package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateJDBCConnection {

    public static void main(String[] args) throws SQLException {

        String dbUrl="jdbc:sqlserver://HP-Laptop/SQLEXPRESS;Database=LearnDB;IntegratedSecurity=true";
//        String username = "mifta";
//        String password="12345";
//        Connection conncetion = DriverManager.getConnection(dbUrl, username, password);
        Connection conncetion = DriverManager.getConnection(dbUrl);
        if(conncetion.isClosed()){
            System.out.println("Not connected to database");
        }else{
            System.out.println("DataBase connection");
        }
    }
}
