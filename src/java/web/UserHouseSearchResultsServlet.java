/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import db.AlgorithmDb;
import entities.HouseEntity;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * It is called by UserBuyPage.jsp. First of all it preforms various data checks
 * if any of those fails th user is redirected to UserBuyPage.jsp and is presented 
 * with the according message. When input data has passed the checks, it passed to
 * AlgorithmDb.SellectHouses and the 10 best results are returned as an arraylist
 * the arraylist is then passed as a session Attribute and UserSearchHousesResults.jsp
 * is called
 * 
 */
public class UserHouseSearchResultsServlet extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message;
        HttpSession session = request.getSession(false);
        String url;
        if (session == null ||session.getAttribute("username") == null) {
            url = "index.jsp";
        } else {
            float LentFromInput = 0, LentToInput = 0, BuyFromInput = 0, BuyToInput = 0;
            String[] transaction = request.getParameterValues("Buyer_Tenant");
            int buy = 0, rent = 0;
            if (transaction == null || transaction.length <= 0) {
                message = "Choose wether you wish to Buy or Rent or both";
                request.setAttribute("message", message);
                request.getRequestDispatcher("/WEB-INF/jsp/UserBuyPage.jsp").forward(request, response);
            } else {//Other data checks
                for (int i = 0; i < transaction.length; i++) {
                    if (transaction[i].equals("Buy")) {
                        buy = 1;
                    }
                    if (transaction[i].equals("Lent")) {
                        rent = 1;
                    }
                }
                if (buy == 1) { //I buyer wants to buy check if fields are empty and if price range makes sence
                    if (request.getParameter("BuyFromInput") == null || request.getParameter("BuyToInput") == null
                            || request.getParameter("BuyToInput").isEmpty() || request.getParameter("BuyFromInput").isEmpty()) {
                        message = "Please fill Min and Max Buy Price";
                        request.setAttribute("message", message);
                        request.getRequestDispatcher("/WEB-INF/jsp/UserBuyPage.jsp").forward(request, response);
                    } else {
                        BuyFromInput = Float.parseFloat(request.getParameter("BuyFromInput"));
                        BuyToInput = Float.parseFloat(request.getParameter("BuyToInput"));
                    }
                }
                if (rent == 1) {//I buyer wants to rent check if fields are empty and if price range makes sence
                    if (request.getParameter("LentFromInput") == null || request.getParameter("LentToInput") == null
                            || request.getParameter("LentFromInput").isEmpty() || request.getParameter("LentToInput").isEmpty()) {
                        message = "Please fill Min and Max Rent Price";
                        request.setAttribute("message", message);
                        request.getRequestDispatcher("/WEB-INF/jsp/UserBuyPage.jsp").forward(request, response);
                    } else {
                        LentFromInput = Float.parseFloat(request.getParameter("LentFromInput"));
                        LentToInput = Float.parseFloat(request.getParameter("LentToInput"));
                    }

                }
            }
            String RegionInput = request.getParameter("RegionInput");
            int RegionWeight = Integer.parseInt(request.getParameter("RegionWeight"));
            int LentWeight = Integer.parseInt(request.getParameter("LentWeight"));
            if (rent == 0) {
                LentWeight = 0;//if client does not want to rent then rent weight is 0
            }
            int BuyWeight = Integer.parseInt(request.getParameter("BuyWeight"));
            if (buy == 0) {
                BuyWeight = 0;//if client does not want to buy then buy weight is 0
            }
            int SquaresFromInput = Integer.parseInt(request.getParameter("SquaresFromInput"));
            int SquaresToInput = Integer.parseInt(request.getParameter("SquaresToInput"));
            int SquaresWeight = Integer.parseInt(request.getParameter("SquaresWeight"));
            String heat = request.getParameter("heat");
            int HeatWeight = Integer.parseInt(request.getParameter("HeatWeight"));
            float ExpenseFromInput = Float.parseFloat(request.getParameter("ExpenseFromInput"));
            float ExpenseToInput = Float.parseFloat(request.getParameter("ExpenseToInput"));
            int ExpenseWeight = Integer.parseInt(request.getParameter("ExpenseWeight"));
            String DateFromInput = request.getParameter("DateFromInput");
            String DateToInput = request.getParameter("DateToInput");
            int DateWeight = Integer.parseInt(request.getParameter("DateWeight"));
            String TypeInput = request.getParameter("TypeInput");
            int TypeWeight = Integer.parseInt(request.getParameter("TypeWeight"));
            /*Perform various Range checks*/
            if (BuyFromInput > BuyToInput) {
                message = "Min Buy Price must be smaller or equal to Max Buy Price";
                request.setAttribute("message", message);
                request.getRequestDispatcher("/WEB-INF/jsp/UserBuyPage.jsp").forward(request, response);
            }
            if (LentFromInput > LentToInput) {
                message = "Min Rent Price must be smaller or equal to Max Rent Price";
                request.setAttribute("message", message);
                request.getRequestDispatcher("/WEB-INF/jsp/UserBuyPage.jsp").forward(request, response);
            }
            if (ExpenseFromInput > ExpenseToInput) {
                message = "Min Expenses must be smaller or equal to Max Expenses";
                request.setAttribute("message", message);
                request.getRequestDispatcher("/WEB-INF/jsp/UserBuyPage.jsp").forward(request, response);
            }
            if (SquaresFromInput > SquaresToInput) {
                message = "Min Square Meters must be smaller or equal to Max Square Meters";
                request.setAttribute("message", message);
                request.getRequestDispatcher("/WEB-INF/jsp/UserBuyPage.jsp").forward(request, response);
            }
            DateFormat formatter;
            Date dateFrom = null, dateTo = null;
            formatter = new SimpleDateFormat("dd-mm-yyyy");
            try {
                dateFrom = formatter.parse(DateFromInput);
                dateTo = formatter.parse(DateToInput);
            } catch (ParseException ex) {
                Logger.getLogger(UserHouseSearchResultsServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (dateTo.compareTo(dateFrom) < 0) {
                message = "Please enter correct Dates";
                request.setAttribute("message", message);
                request.getRequestDispatcher("/WEB-INF/jsp/UserBuyPage.jsp").forward(request, response);
            }
            String algorithm = "";
            ArrayList<HouseEntity> ResultsArray = new ArrayList<HouseEntity>();
            try {
                ResultsArray = (ArrayList) AlgorithmDb.SellectHouses(RegionInput, RegionWeight, LentFromInput, LentToInput, LentWeight,
                        BuyFromInput, BuyToInput, BuyWeight, SquaresFromInput, SquaresToInput,
                        SquaresWeight, heat, HeatWeight, ExpenseFromInput, ExpenseToInput, ExpenseWeight, DateFromInput,
                        DateToInput, DateWeight, TypeInput, TypeWeight);
            } catch (ParseException ex) {
                Logger.getLogger(UserHouseSearchResultsServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            session.setAttribute("resultsArray", ResultsArray);
            url = "/WEB-INF/jsp/UserSearchHousesResults.jsp";
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
