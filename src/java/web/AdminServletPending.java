/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import db.UserDb;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * It is called by AdminPending.jsp. It approves the selected users calling
 * UserDb.Approve(SelectedValues) thus making approved field in the database = 1
 * Or if Reject button is pressed it will delete the selected users from the
 * database
 */
public class AdminServletPending extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String url = "/WEB-INF/jsp/AdminPending.jsp";
        String[] SelectedValues = request.getParameterValues("username");
        /*Reason for session attributes check is that if you have logged in as an Admin and you log out
         if back is pressed the cashed coppy of the page will appear allowing you to perform actions
         in that page*/
        if (session.getAttribute("Administrator") == null || (boolean) session.getAttribute("Administrator") == false) {
            url = "index.jsp";
        }
            else
            {
        if (request.getParameter("approve") != null) {
            if (SelectedValues != null) {
                UserDb.Approve(SelectedValues);
            }
        } else if (SelectedValues != null) {
            UserDb.Delete(SelectedValues);
        }
                    }
            request.getRequestDispatcher(url).forward(request, response);
        }

    

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
