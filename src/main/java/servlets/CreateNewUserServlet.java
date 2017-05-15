package servlets;

import dataModel.DaoCommand;
import dataModel.DaoFactory;
import dataModel.DaoFactoryImpl;
import dataModel.EmployeeDaoImpl;
import entities.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CreateNewUserServlet")
public class CreateNewUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeName = request.getParameter("employeeName");
        String employeeEmail = request.getParameter("employeeEmail");
        String employeePass = request.getParameter("employeePass");
        boolean isAdmin = Boolean.getBoolean(request.getParameter("isAdmin"));

        DaoFactory daoFactory = new DaoFactoryImpl();

        try {
            Employee emp = (Employee) daoFactory.transaction(new DaoCommand() {
                @Override
                public Object execute(DaoFactory factory) {
                    Employee result = null;

                    Employee employee = new Employee();
                    employee.setEmployeeName(employeeName);
                    employee.setEmail(employeeEmail);
                    employee.setPassword(employeePass);
                    employee.setAdmin(isAdmin);

                    EmployeeDaoImpl employeeDao = (EmployeeDaoImpl) factory.getDao(EmployeeDaoImpl.class.getSimpleName());
                    if (employeeDao.persist(employee).equals("SUCCESS")) {
                        result = employeeDao.readByEmail(employeeEmail);
                    }
                    return result;
                }
            });

            if (emp!=null){
                request.getRequestDispatcher("accounts.jsp").forward(request,response);
            } else {
                //todo:add handling
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
