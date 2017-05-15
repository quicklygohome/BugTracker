package servlets;

import dataModel.DaoCommand;
import dataModel.DaoFactory;
import dataModel.DaoFactoryImpl;
import dataModel.ProjectDaoImpl;
import entities.Project;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "LoadProjectsDataServlet")
public class LoadProjectsDataServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DaoFactory daoFactory = new DaoFactoryImpl();
        List<Project> projects = (List<Project>) daoFactory.executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoFactory factory) {
                List<Project> result = null;
                ProjectDaoImpl projectDao = (ProjectDaoImpl) factory.getDao(ProjectDaoImpl.class.getSimpleName());
                result = projectDao.getAll();
                return result;
            }
        });
        request.getSession().setAttribute("projects",projects);
        response.sendRedirect("projects.jsp");
    }
}
