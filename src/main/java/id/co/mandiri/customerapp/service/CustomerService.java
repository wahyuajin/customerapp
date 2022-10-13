package id.co.mandiri.customerapp.service;

import id.co.mandiri.customerapp.domain.Customer;
import id.co.mandiri.customerapp.domain.CustomerException;

import java.util.List;

public interface CustomerService {
    List<Customer> displayAllCustomer();
    void addCustomer(Customer customer) throws CustomerException;
    void editCustomer(Customer customer) throws CustomerException;
    Customer findCustomerId(int id) throws CustomerException;
    void deleteCustomer(int id) throws CustomerException;
}
