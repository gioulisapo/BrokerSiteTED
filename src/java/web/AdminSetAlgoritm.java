/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import db.AlgorithmDb;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * Servlet is used by AdminAlgorithmChoice to set the algorithm in database SAW
 * or TOPSIS
 */
public class AdminSetAlgoritm extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String algorithm_choice;
        String url = "/WEB-INF/jsp/AdminAlgorithmChoice.jsp";
        HttpSession session = request.getSession(true);
        /*Reason for session attributes check is that if you have logged in as an Admin and you log out
         if back is pressed the cashed coppy of the page will appear allowing you to perform actions
         in that page*/
        if (session.getAttribute("Administrator") == null || (boolean) session.getAttribute("Administrator") == false) {
            url = "index.jsp";
        } else {
            if (request.getParameter("topsis") != null) {
                algorithm_choice = "topsis";
            } else {
                algorithm_choice = "saw";
            }
            AlgorithmDb.setAlgorithm(algorithm_choice);
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
