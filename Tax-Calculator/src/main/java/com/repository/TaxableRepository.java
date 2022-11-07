package com.repository;

import com.domain.TaxableIncome;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaxableRepository {
    private DataSource dataSource;
    public TaxableRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    private static final String ALL = "select id,name,category,basic_salary,house_rent,medical,conveyance,commission,bonus,taxable,gross_tax from taxes";
    private static final String SELECT_ONE = "select name,category,basic_salary,house_rent,medical,conveyance,commission,bonus,taxable,gross_tax from taxes where id = ?";
    private static final String CREATE = "insert into taxes (name,category,basic_salary,house_rent,medical,conveyance,commission,bonus,taxable,gross_tax) values (?,?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE1 = "update taxes set ";
    private static final String UPDATE2 = " = ? where id = ?";
    private static final String DELETE = "delete from taxes where id = ?";
    public List<TaxableIncome> list() throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(ALL);
        return listMapper(resultSet);
    }
    public TaxableIncome get(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ONE);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        TaxableIncome tax = new TaxableIncome();
        while (resultSet.next()) {
            mapper(resultSet, tax);
        }
        return tax;
    }
    public boolean create(TaxableIncome tax) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(CREATE);
        preparedStatement.setString(1, tax.getName());
        preparedStatement.setString(2, tax.getCategory_select());
        preparedStatement.setDouble(3, tax.getBasic_salary());
        preparedStatement.setDouble(4, tax.getHouserent());
        preparedStatement.setDouble(5, tax.getMedical());
        preparedStatement.setDouble(6, tax.getConveyance());
        preparedStatement.setDouble(7, tax.getCommission());
        preparedStatement.setDouble(8, tax.getBonus());
        preparedStatement.setDouble(9, tax.getTotalTaxable());
        preparedStatement.setDouble(9, tax.getTotalTaxable());
        preparedStatement.setDouble(10, tax.getGrossTax());
        return preparedStatement.execute();
    }
    public boolean update(TaxableIncome department, String columnName, Double columnValue) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE1 + columnName.toString() + UPDATE2);
        preparedStatement.setDouble(1, columnValue);
        preparedStatement.setLong(2, department.getId());
        return preparedStatement.execute();
    }
    public boolean delete(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
        preparedStatement.setLong(1, id);
        return preparedStatement.execute();
    }
    private void mapper(ResultSet resultSet, TaxableIncome tax) throws SQLException {
        tax.setId(resultSet.getLong(1));
        tax.setName(resultSet.getString(2));
        tax.setCategory_select(resultSet.getString(3));
        tax.setBasic_salary(resultSet.getDouble(4));
        tax.setHouserent(resultSet.getDouble(5));
        tax.setMedical(resultSet.getDouble(6));
        tax.setConveyance(resultSet.getDouble(7));
        tax.setCommission(resultSet.getDouble(8));
        tax.setBonus(resultSet.getDouble(9));
        tax.setTotalTaxable(resultSet.getDouble(10));
        tax.setGrossTax(resultSet.getDouble(11));
    }
    private List<TaxableIncome> listMapper(ResultSet resultSet) throws SQLException {
        List<TaxableIncome> taxes = new ArrayList<>();
        while (resultSet.next()) {
            TaxableIncome tax = new TaxableIncome();
            mapper(resultSet, tax);
            taxes.add(tax);
        }
        return taxes;
    }
}