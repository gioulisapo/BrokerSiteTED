package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * The Servlet is used by the navigation bar in admin_header.jspf to redirect admin between admin oriented Pages
 * In case of--> else if (request.getParameter("Normal_User") != null) admin loses his admin privilages
 * and continues as a normal user.
 */
public class AdminRedirectionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // MyClass myClass = new MyClass();
        String url = "";
        HttpSession session = request.getSession(true);
        if (request.getParameter("home") != null) {
            url = "/WEB-INF/jsp/AdminHomePage.jsp";
        } else if (request.getParameter("pendingusr") != null) {
            url = "/WEB-INF/jsp/AdminPending.jsp";

        } else if (request.getParameter("enl") != null) {
            url = "/WEB-INF/jsp/AdminAlgorithmChoice.jsp";
        } else if (request.getParameter("over") != null) {
            url = "/WEB-INF/jsp/AdminUsrOverview.jsp";
        } else if (request.getParameter("Normal_User") != null) {
            url = "/WEB-INF/jsp/UserHomePage.jsp";
            session.setAttribute("Administrator", false); // Once You continue as a user u have to relogin to access admin page
        }
        request.getRequestDispatcher(url).forward(request, response);

    }
}
