package id.co.mandiri.customerapp.dao;

import id.co.mandiri.customerapp.domain.Customer;
import id.co.mandiri.customerapp.domain.CustomerException;
import id.co.mandiri.customerapp.service.CustomerService;
import id.co.mandiri.customerapp.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao implements CustomerService {
    private  Connection connection;

    private final String querydisplayAllCustomer = "SELECT * FROM customers";
    private final String queryfindCustomerById =
            "SELECT * FROM customers WHERE customerid = ?";

    private final String queryeditCustomer = "UPDATE customers " +
            "SET firstname = ?, lastname= ?, dateofbirth= ? " +
            "WHERE customerid = ?";
    private final String querydeleteCustomer =
            "DELETE FROM customers " +
                    "WHERE customerid= ?";
    private final String queryinsertCustomer =
            "INSERT INTO customers(firstname,lastname,dateofbirth) VALUES (?,?,?)";

    public CustomerDao(){
        connection = DbUtil.getConnection();
    }
    public CustomerDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Customer> displayAllCustomer() {
        List<Customer> customers = new ArrayList<>();
        try(
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(querydisplayAllCustomer);
        ){
            while (resultSet.next()){
                customers.add(new Customer(
                        resultSet.getInt("customerid"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getDate("dateofbirth").toLocalDate()
                ));

            }
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return customers;
    }

    @Override
    public void addCustomer(Customer customer) throws CustomerException {
        try(
            PreparedStatement ps = connection.prepareStatement(queryinsertCustomer);
                ){
            ps.setString(1,customer.getFirstName());
            ps.setString(2,customer.getLastName());
            ps.setDate(3,Date.valueOf(customer.getDateofBirth()));
            ps.executeUpdate();

        } catch (SQLException sqle){
            sqle.printStackTrace();
            throw new CustomerException("Add customer failed");
        }
    }

    @Override
    public void editCustomer(Customer customer) throws CustomerException {
        try(
                PreparedStatement ps = connection.prepareStatement(queryeditCustomer);
        ){
            ps.setString(1,customer.getFirstName());
            ps.setString(2,customer.getLastName());
            ps.setDate(3,Date.valueOf(customer.getDateofBirth()));
            ps.setInt(4,customer.getCustomerid());
            ps.executeUpdate();

        } catch (SQLException sqle){
            sqle.printStackTrace();
            throw new CustomerException("Edit customer failed");
        }

    }

    @Override
    public Customer findCustomerId(int id) throws CustomerException {
        Customer customer = null;
        try (
                PreparedStatement ps = connection.prepareStatement(queryfindCustomerById);
        ) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                customer = new Customer(
                        resultSet.getInt("customerid"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getDate("dateofbirth").toLocalDate()
                );
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new CustomerException("Find customer failed");

        }if(customer !=null) {
            return customer;
        }else {
            throw new CustomerException("customer tidak ditemukan");
        }

    }

    @Override
    public void deleteCustomer(int id) throws CustomerException {
        try (
                PreparedStatement ps = connection.prepareStatement(querydeleteCustomer);
        ) {
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new CustomerException("Delete customer failed");
        }
    }
}
