package db;

import entities.UserEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDb {

    public static UserEntity ReturnUser(String username) {
        UserEntity user = new UserEntity();
        Connection conn = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;
        if (conn != null) {
            try {
                String sql = "SELECT * FROM users WHERE USERNAME LIKE ?";
                statement = conn.prepareStatement(sql);
                statement.setString(1, username);
                set = statement.executeQuery();
                while (set.next()) {
                    user.setUsername(set.getString("username"));
                    user.setFirstName(set.getString("name"));
                    user.setLastName(set.getString("lastName"));
                    user.setCountry(set.getString("country"));
                    user.setRole(set.getString("role"));
                    System.out.println("role-->"+user.getRole());
                    user.setMail(set.getString("email"));
                    user.setPhone(set.getString("telephone"));
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeResources(set, statement, conn);
            }
        }
        return user;

    }

    public static ArrayList<UserEntity> SelectUsers(Boolean active) { // No need for prepared statement no user input
        ArrayList<UserEntity> uList = null;
        Connection conn = Connector.getConnection();
        Statement statement = null;
        ResultSet set = null;

        if (conn != null) {
            try {
                statement = conn.createStatement();
                if (active) {
                    set = statement.executeQuery("SELECT * FROM users WHERE accepted=1 AND username NOT LIKE '%admin%'");
                } else {
                    set = statement.executeQuery("SELECT * FROM users WHERE accepted=0");
                }
                uList = new ArrayList<UserEntity>();
                while (set.next()) { //username, password, name, lastName, telephone, email, role, country
                    UserEntity user = new UserEntity();
                    user.setUsername(set.getString("username"));
                    user.setFirstName(set.getString("name"));
                    user.setLastName(set.getString("lastName"));
                    user.setCountry(set.getString("country"));
                    user.setRole(set.getString("role"));
                    user.setMail(set.getString("email"));
                    user.setPhone(set.getString("telephone"));
                    uList.add(user);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeResources(set, statement, conn);
            }
        }
        return uList;
    }

    public static int LoginAttemt(String username, String password) {
        Connection conn = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;
        int result = -1;
        if (conn != null) {
            try {
                String sql = "SELECT * FROM users WHERE username LIKE ?";
                statement = conn.prepareStatement(sql);
                statement.setString(1, username);
                set = statement.executeQuery();
                if (!set.next()) {
                    result = 0;
                } else {
                    result = -2;
                    if (set.getString("password").equals(password.trim())) {
                        if (!set.getBoolean("accepted")) {
                            result = 1;
                        } else {
                            result = 2;
                            for (String retval : set.getString("role").split(",")) {
                                if (retval.equals("Administrator")) {
                                    result = 3;
                                }
                            }
                        }
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeResources(set, statement, conn);
            }
        }
        return result;
    }

    public static int UsernameIsAvailable(String username) {
        Connection conn = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;
        int result = -1;

        if (conn != null) {
            try {
                String sql = "SELECT * FROM users WHERE username LIKE ?";
                statement = conn.prepareStatement(sql);
                statement.setString(1, username);
                set = statement.executeQuery();
                if (set.next()) {
                    result = 0;
                } else {
                    result = 1;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeResources(set, statement, conn);
            }
        }
        return result;
    }

    public static boolean insertUser(UserEntity user) {

        boolean inserted = false;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;

        if (conn != null) {
            try {
                String sql = "INSERT INTO users (username, password, name, lastName, telephone, email, role, country) VALUES (?,?,?,?,?,?,?,?)";
                statement = conn.prepareStatement(sql);
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getFirstName());
                statement.setString(4, user.getLastName());
                statement.setString(5, user.getPhone());
                statement.setString(6, user.getMail());
                statement.setString(7, user.getRole());
                statement.setString(8, user.getCountry());
                if (statement.executeUpdate() > 0) {
                    inserted = true;
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeResources(set, statement, conn);
            }
        }
        return inserted;

    }

    public static boolean updateUser(UserEntity user) {

        boolean updated = false;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;

        if (conn != null) {
            try {
                String sql = "UPDATE users set password=?,name=?,lastName=?,telephone=?,email=?,role=?,country=?,accepted=0  WHERE username LIKE ?";
                statement = conn.prepareStatement(sql);
                statement.setString(1, user.getPassword());
                statement.setString(2, user.getFirstName());
                statement.setString(3, user.getLastName());
                statement.setString(4, user.getPhone());
                statement.setString(5, user.getMail());
                statement.setString(6, user.getRole());
                statement.setString(7, user.getCountry());
                statement.setString(8, user.getUsername());
                if (statement.executeUpdate() > 0) {
                    updated = true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeResources(set, statement, conn);
            }
        }
        return updated;

    }

    private static void closeResources(ResultSet set, Statement statement, Connection conn) {
        if (set != null) {
            try {
                set.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //Statics

    public static int CountPending() {
        Connection conn = Connector.getConnection();
        Statement statement = null;
        ResultSet set = null;
        int result = 0;

        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery("SELECT * FROM users WHERE accepted=0");
                while (set.next()) {
                    result++;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeResources(set, statement, conn);
            }
        }
        return result;
    }

    public static int CountTotal() // No need for prepared statement
    {
        Connection conn = Connector.getConnection();
        Statement statement = null;
        ResultSet set = null;
        int result = 0;//Don't calculate Admin

        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery("SELECT * FROM users WHERE username NOT LIKE '%admin%'");
                while (set.next()) {
                    result++;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeResources(set, statement, conn);
            }
        }
        return result;
    }

    public static int CountActive() // No need for prepared statement
    {
        Connection conn = Connector.getConnection();
        Statement statement = null;
        ResultSet set = null;
        int result = 0;//Don't calculate Admin

        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery("SELECT * FROM users WHERE username NOT LIKE '%admin%' AND accepted=1");
                while (set.next()) {
                    result++;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeResources(set, statement, conn);
            }
        }
        return result;
    }

    public static int[] CountActiveRoles() // No need for prepared statement
    {
        Connection conn = Connector.getConnection();
        Statement statement = null;
        ResultSet set = null;
        int[] result = {0, 0, 0, 0, 0, 0};//Don't calculate Admin
        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery("SELECT * FROM users WHERE accepted=1");
                while (set.next()) {
                    for (String retval : set.getString("role").split(",")) {
                        switch (retval) {
                            case "Administrator":
                                result[0]++;
                                break;
                            case "Lessor":
                                result[1]++;
                                break;
                            case "Seller":
                                result[2]++;
                                break;
                            case "Tenant":
                                result[3]++;
                                break;
                            case "Buyer":
                                result[4]++;
                                break;
                            case "Visitor":
                                result[5]++;
                                break;
                        }
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeResources(set, statement, conn);
            }
        }
        return result;
    }

    public static int Approve(String[] Users) {
        Connection conn = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;
        int result = 0;//Don't calculate Admin
        if (conn != null) {
            try {
                String sql = "UPDATE users SET accepted=1 WHERE username LIKE ?";
                statement = conn.prepareStatement(sql);

                for (int i = 0; i < Users.length; i++) {
                    statement.setString(1, Users[i]);
                    statement.executeUpdate();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeResources(set, statement, conn);
            }
        }
        return result;
    }

    public static int Delete(String[] Users) {
        Connection conn = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;
        int result = 0;//Don't calculate Admin
        if (conn != null) {
            try {
                String sql = "DELETE FROM users WHERE username LIKE ?";
                statement = conn.prepareStatement(sql);

                for (int i = 0; i < Users.length; i++) {
                    statement.setString(1, Users[i]);
                    statement.executeUpdate();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeResources(set, statement, conn);
            }
        }
        return result;
    }
}
