package id.co.mandiri.customerapp.util;

import junit.framework.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AssertionsKt;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class DbUtilTest {

    @org.junit.jupiter.api.Test
    void getProperty() {
        String util = DbUtil.getProperty("url");
        Assertions.assertEquals("jdbc:mysql://localhost:3306/belajarjava", util);
    }

    @org.junit.jupiter.api.Test
    void getConnection() {
        Connection connection = DbUtil.getConnection();
        Assertions.assertNotNull(connection,"connection should be successfull");
    }
}