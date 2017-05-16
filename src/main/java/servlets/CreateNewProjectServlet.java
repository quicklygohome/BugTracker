package servlets;

import dataModel.*;
import entities.Employee;
import entities.Project;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        String email = pHead.substring(pHead.indexOf('<')+1,pHead.lastIndexOf('>'));

        DaoFactory daoFactory = new DaoFactoryImpl();
        try {
            long headId = (long) daoFactory.executeAndClose(new DaoCommand() {
                @Override
                public Object execute(DaoFactory factory) {
                    long id=0;
                    EmployeeDaoImpl employeeDao = (EmployeeDaoImpl) factory.getDao(EmployeeDaoImpl.class.getSimpleName());
                    Employee employee = employeeDao.readByEmail(email);
                    return employee.getEntityId();
                }
            });

            Project project = (Project) daoFactory.transaction(new DaoCommand() {
                @Override
                public Object execute(DaoFactory factory) {
                    Project result = null;

                    Project prj = new Project();
                    prj.setProjectName(pName);
                    prj.setHeadId(headId);
                    prj.setStartDate(Date.valueOf(pStartDate));
                    prj.setFinishDate(Date.valueOf(pFinishDate));

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
