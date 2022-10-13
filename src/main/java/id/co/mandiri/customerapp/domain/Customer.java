package id.co.mandiri.customerapp.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Customer {
    private int customerid;
    private String firstName;
    private String lastName;
    private LocalDate dateofBirth;

    public Customer(int customerid, String firstName, String lastName, LocalDate dateofBirth) {
        this.customerid = customerid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateofBirth = dateofBirth;
    }

    public Customer(String firstName, String lastName, LocalDate dateofBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateofBirth = dateofBirth;
    }

    public int getCustomerid() {
        return customerid;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public LocalDate getDateofBirth() {
        return dateofBirth;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateofBirth(LocalDate dateofBirth) {
        this.dateofBirth = dateofBirth;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerid=" + customerid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateofBirth=" + dateofBirth +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerid == customer.customerid && Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName) && Objects.equals(dateofBirth, customer.dateofBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerid, firstName, lastName, dateofBirth);
    }




}
