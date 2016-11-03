package controllers.usercontroll;

import dto.UserDTO;
import service.api.Service;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexandr on 11.10.2016.
 */
@WebServlet(name = "DeleteUserServlet", urlPatterns = "/admin/deleteuser")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] toDelStr = request.getParameterValues("selected");
        Service<UserDTO> userService = UserServiceImpl.getInstance();
        for (int i = 0; i < toDelStr.length; i++) {
            userService.delete(Integer.parseInt(toDelStr[i]));
        }
        response.sendRedirect(request.getContextPath() + "/admin/usertodel");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
