package web;

import db.MessageDb;
import entities.MessageEntity;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserContactServlet extends HttpServlet {

    /**
     * Called by HousePresentationPage.jsp when contact Seller button is pressed with value requestContact
     * in that case user is redirected to UserContact.jsp Also called by UserContact.jsp with the value of
     * requestContact in this case a message is submited to the owner of the house.
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
        String ServletResponse = "";
        /*Reason for session attributes check is that if you have logged in as an Admin and you log out
         if back is pressed the cashed coppy of the page will appear allowing you to perform actions
         in that page*/
        if (session.getAttribute("username") == null) {
            url = "index.jsp";
        } else {
            if (request.getParameter("requestContact") != null) {
                url = "/WEB-INF/jsp/UserContact.jsp";
            } else if (request.getParameter("submitContact") != null) {
                MessageEntity message = new MessageEntity();
                message.setMessage(request.getParameter("InputMessage"));
                message.setRecipient(session.getAttribute("owner").toString());
                message.setSender_mail(request.getParameter("InputEmail"));
                message.setSubject(request.getParameter("InputSubject"));
                if (MessageDb.insertMessage(message)) {
                    request.setAttribute("close", "true");
                    ServletResponse = "Your message was sent succesfully";
                    url = "/WEB-INF/jsp/Response.jsp";
                } else {
                    request.setAttribute("close", "true");
                    ServletResponse = "Error while submiting your message. PLease try again later";
                    request.setAttribute("title", "Message Error");
                    url = "/WEB-INF/jsp/Response.jsp";
                }

            }
            request.setAttribute("message", ServletResponse);
        }
        request.getRequestDispatcher(url).forward(request, response);

    }
}
