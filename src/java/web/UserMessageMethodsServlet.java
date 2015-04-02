/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import db.MessageDb;
import entities.MessageEntity;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserMessageMethodsServlet extends HttpServlet {

    /**
     *It is called by UserInboxPage.jsp. Depending on the button pressed it either
     * delete the selected messages or if read/reply is pressed it marks a message as read and redirects
     * the user to the according page to read the message
     * 
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "";
        HttpSession session = request.getSession(false);
        String ServletResponse = "";
        /*Reason for session attributes check is that if you have logged in as an Admin and you log out
         if back is pressed the cashed coppy of the page will appear allowing you to perform actions
         in that page*/
        if (session.getAttribute("username") == null) {
            url = "index.jsp";
        } else {
            if (request.getParameter("delete") != null) {
                String[] SelectedValues = request.getParameterValues("message_id");
                if (SelectedValues != null) {
                    MessageDb.Delete(SelectedValues);
                }
                url = "/WEB-INF/jsp/UserInboxPage.jsp";
            }
            if (request.getParameter("read") != null) {
                url = "/WEB-INF/jsp/UserMessageReadReply.jsp";
                String message_id = request.getParameter("read");
                MessageDb.setMessageRead(message_id);
                MessageEntity message = new MessageEntity();
                message = MessageDb.ReturnMessage(message_id);
                session.setAttribute("subject", message.getSubject());
                session.setAttribute("message_body", message.getMessage());
                session.setAttribute("sender", message.getSender_mail());
            }
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
