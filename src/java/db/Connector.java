/**
 * User: ihamod
 * Date: Apr 28, 2004
 * Time: 11:44:46 PM
 */
package db;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Connector
{
    private static final String user = "root";
    private static final String password = "oKanypre29";
    private static final String dbUrl = "jdbc:mysql://localhost:3306/tedproject";

    public static Connection getConnection()
    { 
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection con = DriverManager.getConnection(dbUrl, user, password);
            return con;
        }
   
       catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }


    }

    public static Connection getConnection(boolean pool)
    {
        if (pool)
        {
            Connection conn = null;
            try
            {
                Context context = new InitialContext();
                Context envctx =  (Context) context.lookup("java:comp/env");
                DataSource ds =  (DataSource) envctx.lookup("jdbc/tedproject");
                conn = ds.getConnection();
            }
            catch (NamingException e)
            {
                e.printStackTrace();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            finally
            {
                return conn;
            }
        }
        else return getConnection();
    }
}
