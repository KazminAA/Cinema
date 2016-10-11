package controllers.sessioncontroll;

import dto.SessionDTO;
import service.api.Service;
import service.impl.SessionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexandr on 11.10.2016.
 */
@WebServlet(name = "DeleteSessionServlet", urlPatterns = "/admin/delsession")
public class DeleteSessionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] toDelStr = (String[]) request.getAttribute("selected");
        if (toDelStr != null) {
            Service<SessionDTO> sessionService = SessionServiceImpl.getInstance();
            for (int i = 0; i < toDelStr.length; i++) {
                sessionService.delete(Integer.parseInt(toDelStr[i]));
            }
        }
        response.sendRedirect(request.getContextPath() + "/admin/sessiontodel");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
