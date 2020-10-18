package servlet;

import json.JSONCar;
import json.JSONPerson;
import service.CarService;
import service.impl.CarServiceImpl;
import service.impl.PersonServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("")
public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PersonServiceImpl personService = new PersonServiceImpl();
        JSONPerson jsonPerson = new JSONPerson();
        CarService carService = new CarServiceImpl();
        JSONCar jsonCar = new JSONCar();
        resp.setContentType("text/html");
        try (PrintWriter printWriter = resp.getWriter()) {
            printWriter.write(jsonPerson.toJSON(personService.getAllPerson()));
            printWriter.write(jsonCar.toJSON(carService.getAllCar()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}