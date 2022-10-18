package com;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@Controller
@RequestMapping("/home")
public class HomeController {

    private DataSource dataSource;

    public HomeController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @RequestMapping("/hello")
    public void hello(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter printWriter = response.getWriter();
        printWriter.write("<h1>Hello Java<h1>");
    }

    @RequestMapping("/greet")
    public String greet() {
        return "home";
    }

    @RequestMapping("/welcome")
    public String welcome(Model model) {
        model.addAttribute("name", "SHAZID - BIN - ZAMAN");
        return "welcome";
    }

    @RequestMapping("/data")
    public void getData() throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from users");
        while (resultSet.next()) {
            System.out.println(resultSet.getLong(1));
            System.out.println(resultSet.getString(2));
            System.out.println(resultSet.getString(3));
        }
    }

    @RequestMapping("/data/create")
    public void createData() throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("insert into users (email, password) values (?, ?)");
        statement.setString(1, "test1@aiub.edu");
        statement.setString(2, "123");
        statement.execute();
    }

    @RequestMapping("/data/update")
    public void createUpdate() throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("update users set email = ?, password = ? where id = ?");
        statement.setString(1, "test4@aiub.edu");
        statement.setString(2, "1146755");
        statement.setLong(3, 2);
        Integer res = statement.executeUpdate();
        System.out.println("update" + res);
    }

    @RequestMapping("/data/delete")
    public void userDelete() throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("delete from users where id = ?");
        statement.setLong(1, 2);
        Integer res = statement.executeUpdate();
        System.out.println("delete" + res);
    }

    @RequestMapping("/data/cal")
    public String showCal(Model model) {
        model.addAttribute("result", 0);
        return "calculator";
    }

    @RequestMapping("/data/cal/add")
    public String calAdd(@ModelAttribute("num1") Integer num1, @ModelAttribute("num2") Integer num2, Model model) throws SQLException {
        Integer result = num1 + num2;

        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("insert into cal (expression, result) values (?, ?)");
        statement.setString(1, (num1 + " + " + num2));
        statement.setInt(2, result);
        model.addAttribute("result", result);
        Integer res = statement.executeUpdate();
        System.out.println("Cal" + res);
        return "calculator";
    }
}
