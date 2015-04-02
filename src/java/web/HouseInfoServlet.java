/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import db.HouseDb;
import entities.HouseEntity;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Apostolis
 */
public class HouseInfoServlet extends HttpServlet {

    /**
     *Called by UserManageHousesPage.jsp it used to set the requested houses info
     * as session attributes and call HouseInfo.jsp to present them and alter them
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String houseId = request.getParameter("house");
        String url,message="";
        HttpSession session = request.getSession(true);
        HouseEntity house = new HouseEntity();
        house = HouseDb.ReturnHouse(houseId);
        if (house != null) {
            url = "/WEB-INF/jsp/HouseInfo.jsp";
            session.setAttribute("error", "false");
            session.setAttribute("username", house.getUsername());
            session.setAttribute("houseId", house.getUniqueCode());
            session.setAttribute("x", house.getLongitude());
            session.setAttribute("y", house.getLatitude());
            session.setAttribute("timeAvailable", house.getDateAvailable());
            session.setAttribute("heat", house.getHeat());
            session.setAttribute("squares", house.getSquares());
            session.setAttribute("region", house.getRegion());
            session.setAttribute("priceBuy", house.getPriceBuy());
            session.setAttribute("priceLent", house.getPriceLent());
            session.setAttribute("expenses", house.getExpenses());
            session.setAttribute("name", house.getName());
            session.setAttribute("address", house.getAddress());
            session.setAttribute("description", house.getDescription());
        } else {//in case the house hase been already deleted
            url = "/WEB-INF/jsp/Response.jsp";
            request.setAttribute("close", "true");
            request.setAttribute("message", "Please refresh management page");
        }
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
