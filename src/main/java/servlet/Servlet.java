package servlet;

import json.JSON;
import model.Car;
import model.Person;
import service.CarService;
import service.PersonService;
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
import java.util.List;

@WebServlet("")
public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        try (PrintWriter printWriter = resp.getWriter()) {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            PersonService personService = new PersonServiceImpl();
            CarService carService = new CarServiceImpl();
            JSON<Person> json = new JSON<>();
            JSON<Car> json2 = new JSON<>();
            resp.setContentType("text/html");
            printWriter.write(json.toJSON(personService.getAllPerson()));
            printWriter.write("<br/>");
            printWriter.write(json2.toJSON(carService.getAllCar()));
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
