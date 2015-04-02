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

public class HouseUpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url, UpdateMessage = "Error while updating house info. Please try again later";
        HttpSession session = request.getSession(true);
        /*Reason for session attributes check is that if you have logged in as an Admin and you log out
         if back is pressed the cashed coppy of the page will appear allowing you to perform actions
         in that page*/
        if (session.getAttribute("username") == null) {
            url = "index.jsp";
        } else {
            String name = request.getParameter("nameInput");
            Float PriceLent = Float.parseFloat(request.getParameter("priceLentInput"));
            Float PriceBuy = Float.parseFloat(request.getParameter("priceBuyInput"));
            int squares = Integer.parseInt(request.getParameter("squaresInput"));
            String heat = request.getParameter("heat");
            Float expenses = Float.parseFloat(request.getParameter("expensesInput"));
            String timeAvailableInput = request.getParameter("timeAvailableInput");
            String houseId = session.getAttribute("houseId").toString();
            String description = request.getParameter("descriptionInput");
            HouseEntity house = new HouseEntity();
            house.setDateAvailable(timeAvailableInput);
            house.setExpenses(expenses);
            house.setPriceBuy(PriceBuy);
            house.setPriceLent(PriceLent);
            house.setSquares(squares);
            house.setHeat(heat);
            house.setUniqueCode(houseId);
            house.setName(name);
            house.setDescription(description);

            if (HouseDb.updateHouse(house)) {
                session.setAttribute("timeAvailable", house.getDateAvailable());
                session.setAttribute("heat", house.getHeat());
                session.setAttribute("squares", house.getSquares());
                session.setAttribute("priceBuy", house.getPriceBuy());
                session.setAttribute("priceLent", house.getPriceLent());
                session.setAttribute("expenses", house.getExpenses());
                session.setAttribute("name", house.getName());
                session.setAttribute("description", house.getDescription());
                UpdateMessage = "Updated Succesfully";
            }
        }
        request.setAttribute("UpdateMessage", UpdateMessage);
        url = "/WEB-INF/jsp/HouseInfo.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
