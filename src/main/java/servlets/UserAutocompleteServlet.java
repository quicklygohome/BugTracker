package servlets;

import com.google.gson.Gson;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sasha on 15.05.2017.
 */
@WebServlet(name = "UserAutocompleteServlet")
public class UserAutocompleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String term = request.getParameter("term");
        DaoFactory daoFactory = new DaoFactoryImpl();
        List<Employee> employeesAutoComplete = (List<Employee>) daoFactory.executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoFactory factory) {
                List<Employee> result = null;
                EmployeeDaoImpl employeeDao = (EmployeeDaoImpl) factory.getDao(EmployeeDaoImpl.class.getSimpleName());
                result = employeeDao.autocomplete("%"+term+"%");
                return result;
            }
        });
        Map<String,String> map = new HashMap<>();
        for (Employee employee:employeesAutoComplete){
            String key = employee.getEmail();
            String val = employee.getEmployeeName();
            map.put(key, val+" <"+key+">");
        }
        String searchMap = new Gson().toJson(map);
        response.setContentType("application/json");
        response.getWriter().write(searchMap);
    }
}
