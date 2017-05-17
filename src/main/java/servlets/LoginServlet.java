package servlets;


import dataModel.*;
import entities.*;


import java.io.IOException;
import java.util.*;

@javax.servlet.annotation.WebServlet(name = "LoginServlet")
public class LoginServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String userEmail = request.getParameter("userEmail");
        String userPassword = request.getParameter("userPassword");

        DaoFactory daoFactory = new DaoFactoryImpl();

        Employee employee = (Employee) daoFactory.executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoFactory factory) {
                Employee result = null;
                EmployeeDaoImpl employeeDao = (EmployeeDaoImpl) factory.getDao(EmployeeDaoImpl.class.getSimpleName());
                result = employeeDao.logIn(userEmail, userPassword);
                return result;
            }
        });

        Map<String, Object> map = (Map<String, Object>) daoFactory.executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoFactory factory) {
                EmployeeDaoImpl employeeDao = (EmployeeDaoImpl) factory.getDao("EmployeeDaoImpl");
                return employeeDao.getTasksByUser(employee.getEntityId());
            }
        });

        if (employee!=null){
//            objectMap.put("user", employee);
//            String json = new Gson().toJson(objectMap);
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//            response.getWriter().write(json);
//            request.setAttribute("pageObjects", objectMap);

            request.getSession().setAttribute("currentUser", employee);
            request.getSession().setAttribute("tasks", map);
            request.getRequestDispatcher("startPage.jsp").forward(request,response);
        } else{
            //todo: send error message
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

}
