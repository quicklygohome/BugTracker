package servlets;

import com.google.gson.Gson;
import dataModel.*;
import entities.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

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
                EmployeeDaoImpl employeeDao = (EmployeeDaoImpl) factory.getDao("EmployeeDaoImpl");
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
//        InitialContext ctx = null;
//        DataSource ds = null;
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        Map<String, Object> map =null;
//
//        String sql = "SELECT BUGTRACKER.TASK.*,PROJECTNAME FROM BUGTRACKER.TASK,BUGTRACKER.PROJECT WHERE BUGTRACKER.TASK.PROJECT_ID=PROJECT.ID AND BUGTRACKER.TASK.ASSIGNEE_ID=?";
//        try {
//            response.setContentType("text/html");
//            PrintWriter writer = response.getWriter();
//            ctx = new InitialContext();
//            ds = (DataSource) ctx.lookup("jdbc/bugtrackerMySql");
//            conn = ds.getConnection();
//            ps = conn.prepareStatement(sql);
//
//            ps.setLong(1, 1);
//            rs = ps.executeQuery();
//            if (!rs.next()){
//                System.out.println("resultset is null");
//            }
//            else {
//                rs.beforeFirst();
//                while(rs.next()) {
//                    writer.println("id "+rs.getLong("ID"));
//                    writer.println("name "+rs.getString("TASKNAME"));
//                    writer.println("type "+rs.getString("TYPE"));
//                    writer.println("state "+rs.getString("STATE"));
//                    writer.println( "descr "+rs.getString("DESCRIPTION"));
//                    writer.println("priority"+rs.getString("PRIORITY"));
//                    writer.println("pId "+rs.getLong("PROJECT_ID"));
//                    writer.println("pName "+rs.getString("PROJECTNAME"));
//                }
//            }
//            writer.close();
//        }
//        catch (SQLException se) {
//            System.out.println("SQLException: "+se.getMessage());
//        }
//        catch (NamingException ne) {
//            System.out.println("NamingException: "+ne.getMessage());
//        }

        if (map!=null){
//            objectMap.put("user", employee);
//            String json = new Gson().toJson(objectMap);
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//            response.getWriter().write(json);
//            request.setAttribute("pageObjects", objectMap);

//            request.setAttribute("user", employee);
            request.setAttribute("tasks", map);
            request.getRequestDispatcher("startPage.jsp").forward(request,response);
        } else{
            //todo: send error message
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

}
