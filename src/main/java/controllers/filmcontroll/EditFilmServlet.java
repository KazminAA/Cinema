package controllers.filmcontroll;

import dto.FilmDTO;
import service.api.Service;
import service.impl.FilmServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexandr on 11.10.2016.
 */
@WebServlet(name = "EditFilmServlet", urlPatterns = "/admin/editfilm")
public class EditFilmServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Service<FilmDTO> filmService = FilmServiceImpl.getInstance();
        FilmDTO[] filmDTOs = null;
        request.setCharacterEncoding("utf-8");
        if ((request.getParameter("edit") != null) && (request.getParameter("edit").equals("edit"))) {
            String[] idsStr = request.getParameterValues("id");
            String[] names = request.getParameterValues("name");
            String[] yearsStr = request.getParameterValues("year");
            String[] durationsStr = request.getParameterValues("duration");
            String[] genres = request.getParameterValues("genre");
            String[] countrys = request.getParameterValues("country");
            String[] produsers = request.getParameterValues("produser");
            String[] casts = request.getParameterValues("cast");
            String[] descriptions = request.getParameterValues("description");
            String[] smallPosters = request.getParameterValues("smallPoster");
            String[] bigPosters = request.getParameterValues("bigPoster");
            if (idsStr != null) {
                for (int i = 0; i < idsStr.length; i++) {
                    FilmDTO updatingFilm = new FilmDTO();
                    updatingFilm.setId(Integer.parseInt(idsStr[i]));
                    updatingFilm.setName(names[i]);
                    updatingFilm.setYearOfRelease(Integer.parseInt(yearsStr[i]));
                    updatingFilm.setDurationMin(Integer.parseInt(durationsStr[i]));
                    updatingFilm.setGenre(genres[i]);
                    updatingFilm.setCountry(countrys[i]);
                    updatingFilm.setProduser(produsers[i]);
                    updatingFilm.setCast(casts[i]);
                    updatingFilm.setDescription(descriptions[i]);
                    updatingFilm.setSmallPoster(smallPosters[i]);
                    updatingFilm.setBigPoster(bigPosters[i]);
                    filmService.update(updatingFilm);
                }
            }
            request.getRequestDispatcher("/movie?status=1").forward(request, response);
        } else {
            String[] ids = (String[]) request.getAttribute("selected");
            if (ids != null) {
                int count = ids.length;
                if (count != 0) {
                    filmDTOs = new FilmDTO[count];
                    for (int i = 0; i < count; i++) {
                        filmDTOs[i] = filmService.getById(Integer.parseInt(ids[i]));
                    }
                }
                request.setAttribute("filmsToEdit", filmDTOs);
                request.getRequestDispatcher("/pages/admin/filmtoedit.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
