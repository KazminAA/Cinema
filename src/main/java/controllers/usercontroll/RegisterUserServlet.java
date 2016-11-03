package controllers.usercontroll;

import dao.impl.RoleDaoImpl;
import dto.UserDTO;
import dto.UserRoleDTO;
import mappers.BeanMapper;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by Alexandr on 03.10.2016.
 */
@WebServlet(name = "RegisterUserServlet", urlPatterns = "/register")
public class RegisterUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        if ((UserServiceImpl.getInstance().getByLogin(request.getParameter("login")) != null) ||
                (UserServiceImpl.getInstance().getByEmail(request.getParameter("email")) != null)) {
            setReqErr(request, response, "Пользователь с таким логином или почтовым адрессом уже существует.");
        } else {
            UserRoleDTO role = BeanMapper.getInstance().singleMapper(RoleDaoImpl.getInstance().getBy("name", "User").get(0), UserRoleDTO.class);
            UserDTO userDTO = null;
            try {
                if (request.getParameter("birthday").equals("")) {
                    setReqErr(request, response, "Незаполнена дата.");
                    return;
                }
                userDTO = new UserDTO(request.getParameter("login"), request.getParameter("pwd"),
                        request.getParameter("email"), request.getParameter("userName"), request.getParameter("userSurname"), role,
                        Boolean.parseBoolean(request.getParameter("sex")), LocalDate.parse(request.getParameter("birthday")));
            } catch (IllegalArgumentException e) {
                setReqErr(request, response, "Неверный формат почтового адресса.");
            } catch (NullPointerException e) {
                setReqErr(request, response, "Не все поля заполнены.");
            }
            if (userDTO != null) {
                UserServiceImpl.getInstance().save(userDTO);
                request.getSession().setAttribute("user", userDTO);
                response.sendRedirect(request.getContextPath() + "/cinema");
            }
        }
    }

    private void setReqErr(HttpServletRequest request, HttpServletResponse response, String message) throws IOException {
        request.getSession().setAttribute("message", message);
        response.sendRedirect(request.getContextPath() + "/pages/common/register.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
