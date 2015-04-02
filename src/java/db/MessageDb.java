package db;

import entities.MessageEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Apostolis
 */
public class MessageDb {

    public static boolean insertMessage(MessageEntity message) {

        boolean inserted = false;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;
        if (conn != null) {
            try {
                String sql = "INSERT INTO messages (recipient, sender_mail, subject, message, is_read, unique_id) VALUES (?,?,?,?,?,?)";
                statement = conn.prepareStatement(sql);
                statement.setString(1, message.getRecipient());
                statement.setString(2, message.getSender_mail());
                statement.setString(3, message.getSubject());
                statement.setString(4, message.getMessage());
                message.GenerateUnique_Id();
                statement.setInt(5, 0);//message is by default unread
                statement.setString(6, message.getUnique_id());
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

    public static ArrayList<MessageEntity> SelectMessagesOfUser(String username) {
        ArrayList<MessageEntity> uList = null;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;

        if (conn != null) {
            try {
                String sql = "SELECT * FROM tedproject.messages WHERE recipient = ?";
                statement = conn.prepareStatement(sql);
                statement.setString(1, username);
                set = statement.executeQuery();
                uList = new ArrayList<MessageEntity>();
                while (set.next()) { //username, password, name, lastName, telephone, email, role, country
                    MessageEntity message = new MessageEntity();
                    message.setMessage(set.getString("message"));
                    message.setRecipient(set.getString("recipient"));
                    message.setSubject(set.getString("subject"));
                    if (set.getInt("is_read") == 1) {
                        message.setRead(true);
                    } else {
                        message.setRead(false);
                    }
                    message.setSender_mail(set.getString("sender_mail"));
                    message.setUnique_id(set.getString("unique_id"));
                    uList.add(message);
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

    public static int CountUnread(String recipient) {
        int count = 0;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;

        if (conn != null) {
            try {
                String sql = "SELECT * FROM tedproject.messages WHERE recipient = ?";
                statement = conn.prepareStatement(sql);
                statement.setString(1, recipient);
//                statement.setInt(2,0);
                set = statement.executeQuery();
                while (set.next()) {
                    if (set.getInt("is_read") == 0) {
                        count++;
                    }
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeResources(set, statement, conn);
            }
        }
        return count;
    }

    public static int Delete(String[] MessageId) {
        Connection conn = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;
        int result = 0;//Don't calculate Admin
        if (conn != null) {
            try {
                String sql = "DELETE FROM tedproject.messages WHERE unique_id = ?";
                statement = conn.prepareStatement(sql);
                for (int i = 0; i < MessageId.length; i++) {
                    statement.setString(1, MessageId[i]);
                    statement.executeUpdate();
                }
                result = 1;
            } catch (SQLException ex) {
                ex.printStackTrace();
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeResources(set, statement, conn);
            }
        }
        return result;
    }

    public static boolean setMessageRead(String message_id) {

        boolean updated = false;
        System.out.println("ID" + message_id);
        Connection conn = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;

        if (conn != null) {
            try {
                String sql = "UPDATE tedproject.messages set is_read=? WHERE unique_id LIKE ?";
                statement = conn.prepareStatement(sql);
                statement.setInt(1, 1);
                statement.setString(2, message_id);
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

    public static MessageEntity ReturnMessage(String UniqueCode) {
        Connection conn = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;
        MessageEntity message = new MessageEntity();
        if (conn != null) {
            try {
                String sql = "SELECT * FROM tedproject.messages WHERE unique_id = ?";
                statement = conn.prepareStatement(sql);
                statement.setString(1, UniqueCode);
                set = statement.executeQuery();
                set.next();
                message.setMessage(set.getString("message"));
                message.setSender_mail(set.getString("sender_mail"));
                message.setSubject(set.getString("subject"));;
            } catch (SQLException ex) {
                ex.printStackTrace();
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeResources(set, statement, conn);
            }
        }
        return message;
    }
}
