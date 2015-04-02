/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * used to redirect a user within the pages it is called by user_header.jspf. Not all
 * pages are available to every user since they must have the 
 * necessary rights
 * 
 */
public class UserRedirectionServlet extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String url = "";
        if(session == null)
                url = "index.jsp";
        else if (session.getAttribute("username") == null ||session.getAttribute("username").equals("")) {
            url = "index.jsp";
        } else {

            // User IS logged in.  
            if (request.getParameter("home") != null) {
                url = "/WEB-INF/jsp/UserHomePage.jsp";
            } else if (request.getParameter("profile_update") != null) {
                url = "/WEB-INF/jsp/UserUpdateProfile.jsp";

            } else if (request.getParameter("search_house") != null) {
                url = "/WEB-INF/jsp/UserBuyPage.jsp";
            } else if (request.getParameter("sell_house") != null) {
                url = "/WEB-INF/jsp/UserEnlistHouse.jsp";
            } else if (request.getParameter("manage_houses") != null) {
                url = "/WEB-INF/jsp/UserManageHousesPage.jsp";
            } else if (request.getParameter("user_inbox") != null) {
                url = "/WEB-INF/jsp/UserInboxPage.jsp";
            }
        }
        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher(url).forward(request, response);

    }
   
}
