package controllers.usercontroll;

import dto.UserDTO;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Alexandr on 23.09.2016.
 */
@WebServlet(name = "PrepareUserServlet", urlPatterns = {"/admin/usertodel"})
public class PrepareUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        String sortBy = request.getParameter("sort");
        List<UserDTO> userDTOs;
        userDTOs = UserServiceImpl.getInstance().getAll();
        userDTOs.sort((o2, o1) -> o2.getLogin().compareTo(o1.getLogin()));
        if (sortBy != null) {
            switch (sortBy) {
                case "userSurname": {
                    userDTOs.sort(((o1, o2) -> o2.getUserSurname().compareTo(o1.getUserSurname())));
                    break;
                }
                case "sex": {
                    userDTOs.sort(((o1, o2) -> o2.getSex().compareTo(o1.getSex())));
                    break;
                }
                case "birthday": {
                    userDTOs.sort(((o1, o2) -> o2.getBirthday().compareTo(o1.getBirthday())));
                    break;
                }
            }
        }
        request.setAttribute("usersToDel", userDTOs);
        request.getRequestDispatcher("../pages/admin/usertodel.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        doPost(request, response);
    }
}
