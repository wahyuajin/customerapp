package id.co.mandiri.customerapp.dao;

import id.co.mandiri.customerapp.domain.Customer;
import id.co.mandiri.customerapp.domain.CustomerException;
import id.co.mandiri.customerapp.service.CustomerService;
import id.co.mandiri.customerapp.util.DbUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDaoTest {

    private static CustomerDao dao;


    @BeforeEach
    void setUp() {
        Connection connection = DbUtil.getConnection();
        dao = new CustomerDao(connection);
    }

    @Test
    void displayAllCustomer() {
    }

    @Test
    void addCustomer() {
        Customer customer = new Customer("Jorgi","Doe",
                LocalDate.of(1996,1,9));
        try {
            dao.addCustomer(customer);
            Customer result = dao.findCustomerId(6);
            Assertions.assertEquals("Jorgi", result.getFirstName());
        } catch (CustomerException e){
            e.printStackTrace();
        }
    }

    @Test
    void editCustomer() {
        try {
            Customer customer = dao.findCustomerId(3);
            customer.setFirstName("Tom");
            customer.setLastName("Hanks");
            customer.setDateofBirth(LocalDate.now());
            dao.editCustomer(customer);
            Customer result = dao.findCustomerId(3);
            Assertions.assertEquals("Tom", result.getFirstName());
            Assertions.assertEquals("Hanks", result.getLastName());
            Assertions.assertEquals(LocalDate.now(), result.getDateofBirth());
        } catch (CustomerException e){
            e.printStackTrace();
        }

    }

    @Test
    void findCustomerId() {
            Exception e = Assertions.assertThrows(
                    CustomerException.class,
            () -> dao.findCustomerId(100)
            );
            Assertions.assertEquals("customer tidak ditemukan", e.getMessage());
        }

    @Test
    void deleteCustomer() {
        try{
            dao.deleteCustomer(1);
            Exception e = Assertions.assertThrows(
                    CustomerException.class,
                    () -> dao.findCustomerId(1)
            );
            Assertions.assertEquals("customer tidak ditemukan", e.getMessage());
        } catch (CustomerException e){
            e.printStackTrace();
        }

    }
}