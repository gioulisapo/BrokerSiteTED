package db;

import entities.HouseEntity;
import entities.House_PictureEintity;
import java.io.IOException;
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
public class HouseDb {

    public static boolean insertHouse(HouseEntity house) {

        boolean inserted = false;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;
        if (conn != null) {
            try {
                String sql = "INSERT INTO houses (uniqueCode, x, y, priceBuy, priceLent, region, squares, heat,"
                        + "expenses, timeAvailable, username,name,address,description,type) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                statement = conn.prepareStatement(sql);
                statement.setString(1, house.getUniqueCode());
                statement.setString(2, house.getLongitude());
                statement.setString(3, house.getLatitude());
                statement.setFloat(4, house.getPriceBuy());
                statement.setFloat(5, house.getPriceLent());
                statement.setString(6, house.getRegion());
                statement.setInt(7, house.getSquares());
                statement.setString(8, house.getHeat());
                statement.setFloat(9, house.getExpenses());
                statement.setString(10, house.getDateAvailable());
                statement.setString(11, house.getUsername());
                statement.setString(12, house.getName());
                statement.setString(13, house.getAddress());
                statement.setString(14, house.getDescription());
                statement.setString(15, house.getType());
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

    public static ArrayList<HouseEntity> SelectHousesOfUser(String username) {
        ArrayList<HouseEntity> uList = null;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;

        if (conn != null) {
            try {
                String sql = "SELECT * FROM tedproject.houses WHERE username = ?";
                statement = conn.prepareStatement(sql);
                statement.setString(1, username);
                set = statement.executeQuery();
                uList = new ArrayList<HouseEntity>();
                while (set.next()) { //username, password, name, lastName, telephone, email, role, country
                    HouseEntity house = new HouseEntity();
                    house.setUsername(set.getString("username"));
                    house.setType(set.getString("type"));
                    house.setUniqueCode(set.getString("uniqueCode"));
                    house.setLongitude(set.getString("x"));
                    house.setLatitude(set.getString("y"));
                    house.setDateAvailable(set.getString("timeAvailable"));
                    house.setHeat(set.getString("heat"));
                    house.setSquares(set.getInt("squares"));
                    house.setRegion(set.getString("region"));
                    house.setPriceBuy(set.getFloat("priceBuy"));
                    house.setPriceLent(set.getFloat("priceLent"));
                    house.setExpenses(set.getFloat("expenses"));
                    house.setName(set.getString("name"));
                    house.setAddress(set.getString("address"));
                    house.setDescription(set.getString("description"));
                    uList.add(house);
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
    
     public static int CountHousesOfUser(String username) {
        Connection conn = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;
        int result = 0;
        if (conn != null) {
            try {
                String sql = "SELECT * FROM tedproject.houses WHERE username = ?";
                statement = conn.prepareStatement(sql);
                statement.setString(1, username);
                set = statement.executeQuery();
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

    public static ArrayList<HouseEntity> SelectAllHouses() {
        ArrayList<HouseEntity> uList = null;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;

        if (conn != null) {
            try {
                String sql = "SELECT * FROM tedproject.houses";
                statement = conn.prepareStatement(sql);
                set = statement.executeQuery();
                uList = new ArrayList<HouseEntity>();
                while (set.next()) { //username, password, name, lastName, telephone, email, role, country
                    HouseEntity house = new HouseEntity();
                    house.setUsername(set.getString("username"));
                    house.setType(set.getString("type"));
                    house.setUniqueCode(set.getString("uniqueCode"));
                    house.setLongitude(set.getString("x"));
                    house.setLatitude(set.getString("y"));
                    house.setDateAvailable(set.getString("timeAvailable"));
                    house.setHeat(set.getString("heat"));
                    house.setSquares(set.getInt("squares"));
                    house.setRegion(set.getString("region"));
                    house.setPriceBuy(set.getFloat("priceBuy"));
                    house.setPriceLent(set.getFloat("priceLent"));
                    house.setExpenses(set.getFloat("expenses"));
                    house.setName(set.getString("name"));
                    house.setAddress(set.getString("address"));
                    house.setDescription(set.getString("description"));
                    uList.add(house);
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

    public static HouseEntity ReturnHouse(String UniqueCode) {
        Connection conn = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;
        HouseEntity house = new HouseEntity();
        if (conn != null) {
            try {
                String sql = "SELECT * FROM tedproject.houses WHERE uniqueCode = ?";
                statement = conn.prepareStatement(sql);
                statement.setString(1, UniqueCode);
                set = statement.executeQuery();
                if (set.next()) { //username, password, name, lastName, telephone, email, role, country
                    house.setUsername(set.getString("username"));
                    house.setUniqueCode(set.getString("uniqueCode"));
                    house.setLongitude(set.getString("x"));
                    house.setLatitude(set.getString("y"));
                    house.setDateAvailable(set.getString("timeAvailable"));
                    house.setHeat(set.getString("heat"));
                    house.setSquares(set.getInt("squares"));
                    house.setRegion(set.getString("region"));
                    house.setPriceBuy(set.getFloat("priceBuy"));
                    house.setPriceLent(set.getFloat("priceLent"));
                    house.setExpenses(set.getFloat("expenses"));
                    house.setName(set.getString("name"));
                    house.setAddress(set.getString("address"));
                    house.setDescription(set.getString("description"));
                }
                else
                    return null;

            } catch (SQLException ex) {
                ex.printStackTrace();
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeResources(set, statement, conn);
            }
        }
        return house;
    }

    public static boolean insertPicture(House_PictureEintity pic) throws IOException {
        boolean inserted = false;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;

        if (conn != null && (pic.getPicture().available() > 0)) {
            try {
                String sql = "INSERT INTO houses_pics (house_code,pic , description, unique_key) values (?, ?, ?, ?)";
                statement = conn.prepareStatement(sql);
                statement.setString(1, pic.getHouseId());
                statement.setBlob(2, pic.getPicture());
                statement.setString(3, pic.getDescription());
                statement.setString(4, pic.getUniqueId());
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

    public static ArrayList<House_PictureEintity> SelectPicturesOfHouse(String HouseId) {
        ArrayList<House_PictureEintity> uList = null;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;

        if (conn != null) {
            try {
                String sql = "SELECT * FROM tedproject.houses_pics WHERE house_code = ?";
                statement = conn.prepareStatement(sql);
                statement.setString(1, HouseId);
                set = statement.executeQuery();
                uList = new ArrayList<House_PictureEintity>();
                while (set.next()) { //username, password, name, lastName, telephone, email, role, country
                    House_PictureEintity house = new House_PictureEintity();
                    house.setUniqueId(set.getString("unique_key"));
                    house.setPictureOut(set.getBytes("pic"));
                    house.setDescription(set.getString("description"));
                    uList.add(house);
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

    public static int CountPicturesOfHouse(String HouseId) {
        int count = 0;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;

        if (conn != null) {
            try {
                String sql = "SELECT * FROM tedproject.houses_pics WHERE house_code = ?";
                statement = conn.prepareStatement(sql);
                statement.setString(1, HouseId);
                set = statement.executeQuery();
                while (set.next()) {
                    count++;
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

    public static int DeletePictures(String[] Pictures) {
        Connection conn = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;
        int result = 0;//Don't calculate Admin
        if (conn != null) {
            try {
                String sql = "DELETE FROM tedproject.houses_pics WHERE unique_key = ?";
                statement = conn.prepareStatement(sql);
                for (int i = 0; i < Pictures.length; i++) {
                    statement.setString(1, Pictures[i]);
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

    public static int Delete(HouseEntity house) {
        Connection conn = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;
        int result = 0;//Don't calculate Admin
        if (conn != null) {
            try {
                String sql = "DELETE FROM tedproject.houses WHERE uniqueCode = ?";
                statement = conn.prepareStatement(sql);
                statement.setString(1, house.getUniqueCode());
                statement.executeUpdate();
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

    public static boolean updateHouse(HouseEntity house) {

        boolean updated = false;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;

        if (conn != null) {
            try {
                String sql = "UPDATE tedproject.houses set priceBuy=?,priceLent=?,squares=?,heat=?,expenses=?,timeAvailable=?,name=?,description=?  WHERE uniqueCode LIKE ?";
                statement = conn.prepareStatement(sql);
                statement.setFloat(1, house.getPriceBuy());
                statement.setFloat(2, house.getPriceLent());
                statement.setInt(3, house.getSquares());
                statement.setString(4, house.getHeat());
                statement.setFloat(5, house.getExpenses());
                statement.setString(6, house.getDateAvailable());
                statement.setString(7, house.getName());
                statement.setString(8, house.getDescription());
                System.out.println("house.getDescription()-->" + house.getDescription());
                statement.setString(9, house.getUniqueCode());
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
}
