/*
 * Contains all required classes for Algorithm choice 
 */
package db;

import entities.HouseEntity;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Apostolis
 */
public class AlgorithmDb
{

    public static boolean setAlgorithm(String algorithm_choice) //Set algorithm choice to SAW or TOPSIS in db
    {                                                           //Only one entry must exist in database. If entry Does not
                                                                //exist in database it we ll insert it
        boolean updated = false;
        Connection conn = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet set = null;
        if (conn != null) {
            try {
                String sql = "SELECT * FROM algorithm";
                statement = conn.prepareStatement(sql);
                set = statement.executeQuery();
                if (!set.next()) // Table is emppty
                {
                    sql = "INSERT INTO algorithm (algorithm_choice) VALUES (?)";
                    statement = conn.prepareStatement(sql);
                    statement.setString(1, algorithm_choice);
                    if (statement.executeUpdate() > 0) {
                        updated = true;
                    }
                }
                else {
                    sql = "UPDATE algorithm set algorithm_choice =?";
                    statement = conn.prepareStatement(sql);
                    statement.setString(1, algorithm_choice);
                    if (statement.executeUpdate() > 0) {
                        updated = true;
                    }
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return updated;
    }

    public static String getAlgorithm() // No need for prepared statement. Get the chosen (by admin) enlistment algorithm
    {
        Connection conn = Connector.getConnection();
        Statement statement = null;
        ResultSet set = null;
        String result = "";//Don't calculate Admin

        if (conn != null) {
            try {
                statement = conn.createStatement();
                set = statement.executeQuery("SELECT * FROM algorithm");
                while (set.next()) {
                    result = set.getString("algorithm_choice");
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
                Logger.getLogger(UserDb.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                closeResources(set, statement, conn);
            }
        }
        return result;
    }
/**/

    /**
     *
     * @return An array of 10 or less Houses. Using the parameters passed
     * it will determine first of all which algorithm is being used by the system
     * and there is an implementation for each one of them. 
     */
    
    public static ArrayList<HouseEntity> SellectHouses(String RegionInput, int RegionWeight, float LentFromInput, float LentToInput, int LentWeight,
            float BuyFromInput, float BuyToInput, int BuyWeight, int SquaresFromInput, int SquaresToInput, int SquaresWeight, String heat, int HeatWeight,
            float ExpenseFromInput, float ExpenseToInput, int ExpenseWeight, String DateFromInput, String DateToInput, int DateWeight, String TypeInput, int TypeWeight) throws ParseException
    {
        ArrayList<HouseEntity> uList = new ArrayList<HouseEntity>();
        uList = HouseDb.SelectAllHouses();
        String[] regions = RegionInput.split(",");
        if (uList.size() <= 10) { //If there are 10 or less results there is no need to categorise them
            return uList;       //since a maximum of 10 houses must be returned
        }
        else {
            if (AlgorithmDb.getAlgorithm().trim().equals("saw")) {
                int i = 0;
                int[][] HouseScore = new int[10][2];//2d array Containing score and house place in ulist
                for (int j = 0; j < 10; j++) {
                    HouseScore[j][0] = -1; //initalize array
                }
                for (HouseEntity u : uList) {
                    int Score = 0; //Start Score Calculation
                    for (int j = 0; j < regions.length; j++) //Check if it is within the requested regions and add to Score
                    {
                        if (u.getRegion().equals(regions[j])) {
                            Score += RegionWeight;
                            break;
                        }
                    }
                    if (u.getPriceBuy() >= BuyFromInput && u.getPriceBuy() <= BuyToInput) {
                        Score += BuyWeight;
                    }
                    if (u.getPriceLent() >= LentFromInput && u.getPriceLent() <= LentToInput) {
                        Score += LentWeight;
                    }
                    if (u.getSquares() >= SquaresFromInput && u.getSquares() <= SquaresToInput) {
                        Score += SquaresWeight;
                    }
                    if (u.getHeat().equals(heat)) {
                        Score += HeatWeight;
                    }
                    if (u.getExpenses() >= ExpenseFromInput && u.getExpenses() <= ExpenseToInput) {
                        Score += ExpenseWeight;
                    }
                    if (TypeInput.equals("both")) {
                        Score += TypeWeight;
                    }
                    else {
                        if (u.getType() != null && u.getType().equals(TypeInput)) {
                            Score += TypeWeight;
                        }
                    }
                    DateFormat formatter;
                    Date dateFrom, dateTo, date;
                    formatter = new SimpleDateFormat("dd-mm-yyyy");
                    dateFrom = formatter.parse(DateFromInput);
                    dateTo = formatter.parse(DateToInput);
                    date = formatter.parse(u.getDateAvailable());
                    if (date.compareTo(dateFrom) >= 0 && date.compareTo(dateTo) <= 0) {
                        Score += DateWeight;
                    }
                    /*Done calcularing Score of house*/
                    if (i < 10) //The first 10 ellemts will have to enter the array shorted descendingly
                    {
                        int j = 0;
                        while (j < 10) {
                            if (HouseScore[j][0] >= Score) {
                                j++;
                            }
                            else {
                                if (HouseScore[j][0] < Score && HouseScore[j][0] != -1) //If current score is larger than one of the previous ones swap them (make sure that it is not
                                {                                                           //swaped with an empty place
                                    int tempScore = HouseScore[j][0];
                                    int tempHouseNo = HouseScore[j][1];
                                    HouseScore[j][0] = Score;
                                    HouseScore[j][1] = i;
                                    HouseScore[i][0] = tempScore;
                                    HouseScore[i][1] = tempHouseNo;
                                    break;
                                }
                                else // if found empty place place it in
                                {
                                    HouseScore[j][0] = Score;
                                    HouseScore[j][1] = i;
                                    break;
                                }
                            }
                        }
                    }
                    else // Initial matrix is full so we will start checking it's element one by one. If we find a score that
                        //is smaller than the current one we will insert it in its stead.
                    {
                        int j = 0;
                        while (j < 10) {
                            if (HouseScore[j][0] >= Score) {
                                j++;
                            }
                            else {
                                HouseScore[j][0] = Score;
                                HouseScore[j][1] = i;
                                break;
                            }
                        }
                    }
                    i++;
                }
                ArrayList<HouseEntity> returnList = new ArrayList<HouseEntity>(); // Array list consisting of 10 elements with the highest scores
                for (int j = 0; j < 10; j++) {
                    returnList.add(uList.get(HouseScore[j][1]));
                }
                return returnList;
            }
            else //TOPSIS implementation
            {
                double[][] topsisMatrix = new double[(uList.size() + 1)][8]; //First row weigths + one row for each house, 8 rows for each attribute
                topsisMatrix[0][0] = RegionWeight;
                topsisMatrix[0][1] = LentWeight;
                topsisMatrix[0][2] = BuyWeight;
                topsisMatrix[0][3] = SquaresWeight; //init first row with weights
                topsisMatrix[0][4] = HeatWeight;
                topsisMatrix[0][5] = ExpenseWeight;
                topsisMatrix[0][6] = DateWeight;
                topsisMatrix[0][7] = TypeWeight;
                int i = 1;
                for (HouseEntity u : uList) //fill matrix
                {
                    for (int j = 0; j < regions.length; j++) //Check if it is within the requested regions and add to Score
                    {
                        if (u.getRegion().equals(regions[j])) {
                            topsisMatrix[i][0] = 20;
                            break;
                        }
                    }
                    if (u.getPriceLent() >= LentFromInput && u.getPriceLent() <= LentToInput) {
                        topsisMatrix[i][1] = 20;
                    }
                    if (u.getPriceBuy() >= BuyFromInput && u.getPriceBuy() <= BuyToInput) {
                        topsisMatrix[i][2] = 20;
                    }
                    if (u.getSquares() >= SquaresFromInput && u.getSquares() <= SquaresToInput) {
                        topsisMatrix[i][3] = 20;
                    }
                    if (u.getHeat().equals(heat)) {
                        topsisMatrix[i][4] = 20;
                    }
                    if (u.getExpenses() >= ExpenseFromInput && u.getExpenses() <= ExpenseToInput) {
                        topsisMatrix[i][5] = 20;
                    }
                    DateFormat formatter;
                    Date dateFrom, dateTo, date;
                    formatter = new SimpleDateFormat("dd-mm-yyyy");
                    dateFrom = formatter.parse(DateFromInput);
                    dateTo = formatter.parse(DateToInput);
                    date = formatter.parse(u.getDateAvailable());
                    if (date.compareTo(dateFrom) >= 0 && date.compareTo(dateTo) <= 0) {
                        topsisMatrix[i][6] = 20;
                    }
                    if (TypeInput.equals("both")) {
                        topsisMatrix[i][7] = 20;
                    }
                    else {
                        if (u.getType() != null && u.getType().equals(TypeInput)) {
                            topsisMatrix[i][7] = 20;
                        }
                    }
                    i++;
                } //now matrix has all required values to start calculating
                double[] topsisParameter = new double[]{1, 1, 1, 1, 1, 1, 1, 1}; //sqrt[Σ(χij^2)] for each column must be at least one for healthy next steps
                for (int j = 0; j < 8; j++) //step 1(a)of algorithm
                {
                    for (i = 1; i < uList.size(); i++) {
                        topsisParameter[j] = topsisParameter[j] + pow(topsisMatrix[i][j], 2);
                    }
                    topsisParameter[j] = sqrt(topsisParameter[j]);
                }
                for (int j = 0; j < 8; j++) //step 1(b)and 2 of algorithm Calculate for each entry cell value/sqrt[Σ(χ^2ij)] and then multiply it with column weight
                {
                    for (i = 1; i < uList.size(); i++) {
                        topsisMatrix[i][j] = topsisMatrix[i][j] / topsisParameter[j] * topsisMatrix[0][j];
                    }
                }
                double[] idealSolution = new double[8];// calculate ideal solution step 3(a)
                for (int j = 0; j < 8; j++) {
                    idealSolution[j] = CalculateSolution(topsisMatrix, j, false, uList.size());
                }
                double[] negativeIdealSolution = new double[8];// calculate ideal solution step 3(a)
                for (int j = 0; j < 8; j++) {
                    negativeIdealSolution[j] = CalculateSolution(topsisMatrix, j, true, uList.size());
                }
                double[] seperatrionPositive = new double[uList.size()]; //step 4(a)of algorithm calculating S*i
                for (i = 1; i < uList.size() + 1; i++) {
                    for (int j = 0; j < 8; j++) {
                        seperatrionPositive[i - 1] += pow((idealSolution[j] - topsisMatrix[i][j]), 2);
                    }
                    seperatrionPositive[i - 1] = sqrt(seperatrionPositive[i - 1]);
                }
                double[] seperationNegative = new double[uList.size()]; //step 4(b)of algorithm Calculating S'i
                for (i = 1; i < uList.size() + 1; i++) {
                    for (int j = 0; j < 8; j++) {
                        seperationNegative[i - 1] += pow((negativeIdealSolution[j] - topsisMatrix[i][j]), 2);
                    }
                    seperationNegative[i - 1] = sqrt(seperationNegative[i - 1]);
                }
                double[] RelativeCloseness = new double[uList.size()]; //step 5of algorithm Ci = S'i/(S*i+S'i))
                for (i = 0; i < uList.size(); i++) {
                    RelativeCloseness[i] = seperationNegative[i] / (seperatrionPositive[i] + seperationNegative[i]);
                }//At last we habe the Topsis Matrix with the calculated scores of each house. The scores in the matrix are in the same order they are in the uList
                double[][] HouseScore = new double[10][2];//2d array Containing score and house place in ulist
                for (int j = 0; j < 10; j++) {
                    HouseScore[j][0] = -1; //initalize array
                }
                for (int j = 0; j < 10; j++) {
                    int counter = 0;
                    while (counter < 10) {
                        if (HouseScore[counter][0] >= RelativeCloseness[j]) {
                            counter++;
                        }
                        else {
                            if (HouseScore[counter][0] < RelativeCloseness[j] && HouseScore[counter][0] != -1) //If current score is larger than one of the previous ones swap them (make sure that it is not
                            {                                                           //swaped with an empty place
                                double tempScore = HouseScore[counter][0];
                                double tempHouseNo = HouseScore[counter][1];
                                HouseScore[counter][0] = RelativeCloseness[j];
                                HouseScore[counter][1] = j;
                                HouseScore[j][0] = tempScore;
                                HouseScore[j][1] = tempHouseNo;
                                break;
                            }
                            else // if found empty place place it in
                            {
                                HouseScore[counter][0] = RelativeCloseness[j];
                                HouseScore[counter][1] = j;

                                break;
                            }
                        }
                    }
                }
                for (int k = 10; k < uList.size(); k++) {
                    int j = 0;
                    while (j < 10) {
                        if (HouseScore[j][0] >= RelativeCloseness[k]) {
                            j++;
                        }
                        else {
                            HouseScore[j][0] = RelativeCloseness[k];
                            HouseScore[j][1] = k;
                            break;
                        }
                    }
                }
                ArrayList<HouseEntity> returnList = new ArrayList<HouseEntity>(); // Array list consisting of 10 elements with the highest scores
                for (int j = 0; j < 10; j++) {
                    returnList.add(uList.get((int)HouseScore[j][1]));
                }
                return returnList;
            }
        }
    }

    private static double CalculateSolution(double[][] matrix, int column, boolean worst, int rows) //used by topsis algorithm
    {
        double solution;
        if (worst) {
            solution = 40;//imposibly high number
            for (int i = 1; i < rows; i++) {
                if (matrix[i][column] < solution) {
                    solution = matrix[i][column];
                }
            }
        }
        else {
            solution = 0;//imposibly low number
            for (int i = 1; i < rows; i++) {
                if (matrix[i][column] > solution) {
                    solution = matrix[i][column];
                }
            }
        }
        return solution;
    }

    private static void closeResources(ResultSet set, Statement statement, Connection conn)
    {
        if (set != null) {
            try {
                set.close();

            }
            catch (SQLException ex) {
                Logger.getLogger(UserDb.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (statement != null) {
            try {
                statement.close();

            }
            catch (SQLException ex) {
                Logger.getLogger(UserDb.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (conn != null) {
            try {
                conn.close();

            }
            catch (SQLException ex) {
                Logger.getLogger(UserDb.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
