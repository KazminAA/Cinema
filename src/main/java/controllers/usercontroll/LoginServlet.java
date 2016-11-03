package controllers.usercontroll;

import dto.UserDTO;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexandr on 29.09.2016.
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        UserDTO user = UserServiceImpl.getInstance().getByLogin(login);
        if ((user != null) && user.getPwd().equals(pass)) {
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getSession().getAttribute("url").toString());
        } else {
            request.getSession().setAttribute("message", "Wrong username or password");
            response.sendRedirect(request.getContextPath() + "/pages/common/login.jsp");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
