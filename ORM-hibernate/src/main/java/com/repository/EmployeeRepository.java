package com.repository;

import com.domain.Employee;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class EmployeeRepository {

    private SessionFactory sessionFactory;

    public EmployeeRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Employee> list() throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Query<Employee> employeeQuery = session.createQuery("from Employee", Employee.class);
        return employeeQuery.getResultList();
    }

    public boolean create(Employee employee) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        System.out.println("Trying To Insert");
        System.out.println(employee.getId());
        System.out.println(employee.getFirstName());
        System.out.println(employee.getGender());
        session.save(employee);
        return true;
    }

    public Employee get(Long id) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Employee.class, id);
    }

    /*public boolean update(Student student) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
        preparedStatement.setString(1, student.getFirstname());
        preparedStatement.setString(2, student.getLastname());
        preparedStatement.setLong(3, student.getId());
        return preparedStatement.execute();
    }

    public boolean delete(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
        preparedStatement.setLong(1, id);
        return preparedStatement.execute();
    }

    private List<Student> mapper(ResultSet resultSet) throws SQLException {
        List<Student> students = new ArrayList<>();
        while(resultSet.next()) {
            Student student = new Student();
            student.setId(resultSet.getLong(1));
            student.setFirstname(resultSet.getString(2));
            student.setLastname(resultSet.getString(3));
            students.add(student);
        }
        return students;
    }*/
}
