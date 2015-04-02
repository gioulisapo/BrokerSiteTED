/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import db.HouseDb;
import entities.HouseEntity;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HouseInsertServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url;
        HttpSession session = request.getSession(true);
        /*Reason for session attributes check is that if you have logged in as an Admin and you log out
         if back is pressed the cashed coppy of the page will appear allowing you to perform actions
         in that page*/
        if (session.getAttribute("username") == null) {
            url = "index.jsp";
        } else {
            String username = (String) session.getAttribute("username");
            HouseEntity house = new HouseEntity();
            String longitude = request.getParameter("xInput");
            String latitude = request.getParameter("yInput");
            String priceLent = request.getParameter("priceLentInput");
            String priceBuy = request.getParameter("priceBuyInput");
            String region = request.getParameter("RegionInput");
            String squares = request.getParameter("squaresInput");
            String heatType = request.getParameter("heat");
            String expenses = request.getParameter("expensesInput");
            String dateAvailable = request.getParameter("timeAvailableInput");
            String name = request.getParameter("nameInput");
            String address = request.getParameter("addressInput");
            String description = request.getParameter("descriptionInput");
            String type = request.getParameter("type");
            house.setType(type);
            house.setLongitude((longitude));
            house.setLatitude((latitude));
            house.setPriceBuy(Float.parseFloat(priceBuy));
            house.setPriceLent(Float.parseFloat(priceLent));
            house.setRegion(region);
            house.setSquares(Integer.parseInt(squares));
            house.setHeat(heatType);
            house.setExpenses(Float.parseFloat(expenses));
            house.setDateAvailable(dateAvailable);
            house.setUsername(username);
            house.setName(name);
            house.setAddress(address);
            house.setDescription(description);
            house.GenerateUniqueCode();//This random number is used as a primary key to link pictures database
            String message;
            url = "/WEB-INF/jsp/UserHomePage.jsp";
            if (HouseDb.insertHouse(house)) {
                message = "House Succesfully enlisted";
            } else {
                message = "Something went wrong! Please try again later";
            }
            request.setAttribute("Insertmessage", message);
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
