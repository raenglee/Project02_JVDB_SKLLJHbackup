package org.example.customer;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerRepository {

    public void select() {
        List<Customer> list = new ArrayList<>();
        try {
            Connection conn
                    = DriverManager.getConnection(
                    "jdbc:mysql://192.168.0.85:3306/SKLL_Library", "root", "1234");
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
        Scanner scanner = new Scanner(System.in);

        System.out.print("회원 이름을 입력하세요: ");
        String c_name = scanner.next();
        System.out.print("회원 전화번호를 입력하세요: ");
        String c_phone = scanner.next();


        try {
            Connection conn
                    = DriverManager.getConnection(
                    "jdbc:mysql://192.168.0.85:3306/SKLL_Library", "root", "1234");
            PreparedStatement pstmt = conn.prepareStatement("""
                    INSERT INTO Customer (c_name, c_phone, join_date)
                    VALUES (?, ?, ?)""");

            pstmt.setString(1, c_name);
            pstmt.setString(2, c_phone);
            pstmt.setObject(3, Date.valueOf(LocalDate.now()));

            pstmt.executeUpdate();

            System.out.println("회원가입이 완료되었습니다.");
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update_withdraw() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("탈퇴한 회원의 번호를 입력 해주세요.");
        int c_id = scanner.nextInt();

        try {
            Connection conn
                    = DriverManager.getConnection(
                    "jdbc:mysql://192.168.0.85:3306/SKLL_Library", "root", "1234");
            PreparedStatement pstmt = conn.prepareStatement("UPDATE Customer SET withdraw_date = ? where c_id = ?");
            pstmt.setObject(1, Date.valueOf(LocalDate.now()));
            pstmt.setInt(2, c_id);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void update() {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("수정할 회원 번호를 입력하세요: ");
//        String c_id = scanner.next();
//        System.out.print("회원의 수정된 이름을 입력하세요: ");
//        String c_name = scanner.next();
//        System.out.print("회원의 수정된 전화번호를 입력하세요: ");
//        String c_phone = scanner.next();
//        System.out.print("회원의 수정된 대여상태를 입력하세요: ");
//        LocalDate join_Date = LocalDate.parse(scanner.next());
//        System.out.print("회원의 수정된 탈퇴일을 입력하세요(yyyy-mm-dd): ");
//        LocalDate withdraw_Date = LocalDate.parse(scanner.next());
//
//        try {
//            Connection conn
//                    = DriverManager.getConnection(
//                    "jdbc:mysql://192.168.0.85:3306/SKLL_Library", "root", "1234");
//
//            PreparedStatement pstmt = conn.prepareStatement("UPDATE Customer SET c_name= ?, c_phone = ?, join_date = ?, withdraw_date = ? where c_id = ?");
//            pstmt.setString(1, c_name);
//            pstmt.setString(2, c_phone);
//            pstmt.setObject(3, join_Date);
//            pstmt.setObject(4, withdraw_Date);
//            pstmt.setString(5, c_id);
//
//            pstmt.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    public void update() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    어떤 정보를 수정하시겠습니까?
                    1. 회원 이름
                    2. 회원 휴대폰
                    3. 회원 가입일
                    4. 회원 탈퇴일
                    5. 종료
                    """);

            int cho = scanner.nextInt();

            try {
                Connection conn
                        = DriverManager.getConnection(
                        "jdbc:mysql://192.168.0.85:3306/SKLL_Library", "root", "1234");

                if (cho == 1) {

                    System.out.print("수정할 회원 번호를 입력하세요: ");
                    String c_id = scanner.next();
                    System.out.print("회원 이름을 수정하세요: ");
                    String c_name = scanner.next();

                    PreparedStatement pstmt = conn.prepareStatement("UPDATE Customer SET c_name= ? where c_id = ?");

                    pstmt.setString(1, c_name);
                    pstmt.setString(2, c_id);

                    pstmt.executeUpdate();

                } else if (cho == 2) {


                    System.out.print("수정할 회원 번호를 입력하세요: ");
                    String c_id = scanner.next();
                    System.out.print("회원 전화번호를 수정하세요: ");
                    String c_phone = scanner.next();

                    PreparedStatement pstmt = conn.prepareStatement("UPDATE Customer SET c_phone = ? where c_id = ?");

                    pstmt.setString(1, c_phone);
                    pstmt.setString(2, c_id);

                    pstmt.executeUpdate();

                } else if (cho == 3) {

                    System.out.print("수정할 회원 번호를 입력하세요: ");
                    String c_id = scanner.next();
                    System.out.print("회원의 가입일을 수정하세요(yyyy-mm-dd): ");
                    String join_date = scanner.next();


                    PreparedStatement pstmt = conn.prepareStatement("UPDATE Customer SET join_date = ? where c_id = ?");
                    pstmt.setString(1, join_date);
                    pstmt.setString(2, c_id);

                    pstmt.executeUpdate();

                } else if (cho == 4) {


                    System.out.print("수정할 회원 번호를 입력하세요: ");
                    String c_id = scanner.next();
                    System.out.print("회원의 탈퇴일을 수정하세요(yyyy-mm-dd): ");
                    String withdraw_date = scanner.next();

                    PreparedStatement pstmt = conn.prepareStatement("UPDATE Customer SET withdraw_date = ? where c_id = ?");

                    pstmt.setString(1, withdraw_date);
                    pstmt.setString(2, c_id);

                    pstmt.executeUpdate();

                } else {
                    System.out.println("종료합니다.");
                    break;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("삭제할 회원의 번호를 입력 해주세요.");
        int c_id = scanner.nextInt();

        try {
            Connection conn
                    = DriverManager.getConnection(
                    "jdbc:mysql://192.168.0.85:3306/SKLL_Library", "root", "1234");
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Customer WHERE c_id = ?");
            pstmt.setInt(1, c_id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}