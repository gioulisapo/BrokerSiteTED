package db;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnectionPool {

    private static  ConnectionPool pool = null;
    private static DataSource ds = null;

    private ConnectionPool()
    {
        try {
            Context context = new InitialContext();
            Context envctx =  (Context) context.lookup("java:comp/env");
            ds =  (DataSource) envctx.lookup("jdbc/tedproject");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public synchronized static ConnectionPool getInstance()
    {
        pool = new ConnectionPool();
        return pool;
    }

    public Connection getConnection()
    {
        try {
            return ds.getConnection();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public void freeConnection (Connection c)
    {
        try {
            c.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
  }

