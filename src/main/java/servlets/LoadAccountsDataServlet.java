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
import java.util.List;

/**
 * Created by Sasha on 14.05.2017.
 */
@WebServlet(name = "LoadAccountsDataServlet")
public class LoadAccountsDataServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DaoFactory daoFactory = new DaoFactoryImpl();
        List<Employee> employees = (List<Employee>) daoFactory.executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoFactory factory) {
                List<Employee> result = null;
                EmployeeDaoImpl employeeDao = (EmployeeDaoImpl) factory.getDao(EmployeeDaoImpl.class.getSimpleName());
                result = employeeDao.getAll();
                return result;
            }
        });
        request.getSession().setAttribute("employees", employees);
        response.sendRedirect("accounts.jsp");
    }
}
