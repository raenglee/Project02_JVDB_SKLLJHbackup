package org.example.customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

    public class chat_CustomerRepository {
        private static final String URL = "jdbc:mysql://192.168.0.85:3306/SKLL_Library";
        private static final String USER = "root";
        private static final String PASSWORD = "1234";

        public void select() {
            List<Customer> list = new ArrayList<>();
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Customer ORDER BY c_id");
                 ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    Customer customer = Customer.builder()
                            .c_id(rs.getInt("c_id"))
                            .c_name(rs.getString("c_name"))
                            .c_phone(rs.getString("c_phone"))
                            .c_state(rs.getString("c_state"))
                            .join_date(rs.getObject("join_date", LocalDate.class))
                            .withdraw_date(rs.getObject("withdraw_date", LocalDate.class))
                            .build();
                    list.add(customer);
                }
                list.forEach(System.out::println);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void selectState() {
            Scanner scanner = new Scanner(System.in);
            System.out.print("조회할 회원 상태를 입력하세요: ");
            String state = scanner.next();

            List<Customer> list = new ArrayList<>();
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Customer WHERE c_state = ? ORDER BY c_id")) {

                pstmt.setString(1, state);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        Customer customer = Customer.builder()
                                .c_id(rs.getInt("c_id"))
                                .c_name(rs.getString("c_name"))
                                .c_phone(rs.getString("c_phone"))
                                .c_state(rs.getString("c_state"))
                                .join_date(rs.getObject("join_date", LocalDate.class))
                                .withdraw_date(rs.getObject("withdraw_date", LocalDate.class))
                                .build();
                        list.add(customer);
                    }
                    list.forEach(System.out::println);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void insert() {
            Scanner scanner = new Scanner(System.in);
            System.out.print("회원 이름을 입력하세요: ");
            String name = scanner.next();
            System.out.print("회원 전화번호를 입력하세요: ");
            String phone = scanner.next();
            System.out.print("회원 상태를 입력하세요: ");
            String state = scanner.next();
            System.out.print("가입일을 입력하세요 (yyyy-MM-dd): ");
            LocalDate joinDate = LocalDate.parse(scanner.next());
            System.out.print("탈퇴일을 입력하세요 (yyyy-MM-dd): ");
            LocalDate withdrawDate = LocalDate.parse(scanner.next());

            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement("""
                     INSERT INTO Customer (c_name, c_phone, c_state, join_date, withdraw_date)
                     VALUES (?, ?, ?, ?, ?)""")) {

                pstmt.setString(1, name);
                pstmt.setString(2, phone);
                pstmt.setString(3, state);
                pstmt.setObject(4, joinDate);
                pstmt.setObject(5, withdrawDate);

                pstmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void update() {
            Scanner scanner = new Scanner(System.in);
            System.out.print("수정할 회원의 ID를 입력하세요: ");
            int id = scanner.nextInt();

            System.out.println("""
        어떤 정보를 수정하시겠습니까?
        1. 회원 이름
        2. 회원 휴대폰
        3. 회원 대여 상태
        4. 회원 가입일
        5. 회원 탈퇴일
        """);

            int choice = scanner.nextInt();
            String sql = null;

            if (choice == 1) {
                sql = "UPDATE Customer SET c_name = ? WHERE c_id = ?";
            } else if (choice == 2) {
                sql = "UPDATE Customer SET c_phone = ? WHERE c_id = ?";
            } else if (choice == 3) {
                sql = "UPDATE Customer SET c_state = ? WHERE c_id = ?";
            } else if (choice == 4) {
                sql = "UPDATE Customer SET join_date = ? WHERE c_id = ?";
            } else if (choice == 5) {
                sql = "UPDATE Customer SET withdraw_date = ? WHERE c_id = ?";
            } else {
                System.out.println("올바른 선택이 아닙니다.");
                return;
            }

            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                System.out.print("수정할 값을 입력하세요: ");
                if (choice == 4 || choice == 5) {
                    LocalDate date = LocalDate.parse(scanner.next());
                    pstmt.setObject(1, date);
                } else {
                    pstmt.setString(1, scanner.next());
                }
                pstmt.setInt(2, id);

                pstmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        public void delete() {
            Scanner scanner = new Scanner(System.in);
            System.out.print("삭제할 회원의 ID를 입력하세요: ");
            int id = scanner.nextInt();

            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Customer WHERE c_id = ?")) {

                pstmt.setInt(1, id);
                pstmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}
