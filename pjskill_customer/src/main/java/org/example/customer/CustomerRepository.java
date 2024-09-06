package org.example.customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    public void select() {
        List<Customer> list = new ArrayList<>();
        try (Connection conn
                     = DriverManager.getConnection(
                "jdbc:mysql://192.168.0.85:3306/SKLL_Library", "root", "1234")) {
            PreparedStatement pstmt = conn.prepareStatement("select * from Customer order by c_id");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Customer customer = Customer.builder()
                        .c_id(rs.getInt("c_id"))
                        .c_name(rs.getString("c_name"))
                        .c_phone(rs.getString("c_phone"))
                        .join_date(rs.getObject("join_date", LocalDate.class))
                        .withdraw_date(rs.getObject("withdraw_date", LocalDate.class))
                        .build();
                list.add(customer);
            }
            list.stream()
                    .forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert() {
        try (
                Connection conn
                        = DriverManager.getConnection(
                        "jdbc:mysql://192.168.0.85:3306/SKLL_Library", "root", "1234")) {
            PreparedStatement pstmt = conn.prepareStatement("""
                    INSERT INTO Customer
                    (c_name, c_phone, c_state, join_date, withdraw_date)
                    (?, ?, ?, ?, ?)
                    """);
            pstmt.setString(1, "이름");
            pstmt.setString(2, "010-1234-5678");
            pstmt.setString(3, "2");
            pstmt.setObject(4, LocalDate.of(2024, 9, 10));
            pstmt.setObject(5, LocalDate.of(2024, 12, 03));

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}