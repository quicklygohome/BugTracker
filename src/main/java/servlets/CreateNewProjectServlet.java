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
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Sasha on 15.05.2017.
 */
@WebServlet(name = "CreateNewProjectServlet")
public class CreateNewProjectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pName = request.getParameter("pName");
        String pHead = request.getParameter("pHead");
        String pStartDate = request.getParameter("pStartDate");
        String pFinishDate = request.getParameter("pFinishDate");

        DaoFactory daoFactory = new DaoFactoryImpl();
        try {
            Project project = (Project) daoFactory.transaction(new DaoCommand() {
                @Override
                public Object execute(DaoFactory factory) {
                    Project result = null;

                    Project prj = new Project();
                    prj.setProjectName(pName);
                    prj.setHeadId(Long.valueOf(pHead));
                    prj.setStartDate(Timestamp.valueOf(pStartDate));
                    prj.setFinishDate(Timestamp.valueOf(pFinishDate));

                    ProjectDaoImpl projectDao = (ProjectDaoImpl) factory.getDao(ProjectDaoImpl.class.getSimpleName());
                    if (projectDao.persist(prj).equals("SUCCESS")){
                        List<Project> allProjects = projectDao.getAll();
                        result = allProjects.get(allProjects.size()-1);
                    }
                    return result;
                }
            });
            if (project!=null){
                request.getRequestDispatcher("projects.jsp").forward(request,response);
            } else {
                //todo:handling
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
