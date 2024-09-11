package org.example.Oder;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrdersRepository {

    public void rental() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("손님 이름: ");
        String c_name = scanner.nextLine();
        System.out.print("빌리는 책 이름 : ");
        String b_name = scanner.nextLine();

        ResultSet bidrs = null;
        ResultSet eidrs = null;
        ResultSet cidrs = null;

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/SKLL_Library", "root", "1234");
            PreparedStatement pstmt = conn.prepareStatement("SELECT b_id FROM Book WHERE b_name = ?");
            pstmt.setString(1, b_name);
            bidrs = pstmt.executeQuery();
            bidrs.next();
            int b_id = bidrs.getInt("b_id");

            pstmt = conn.prepareStatement("SELECT e_id FROM entry WHERE b_id = ?");
            pstmt.setInt(1, b_id);
            eidrs = pstmt.executeQuery();
            eidrs.next();

            pstmt = conn.prepareStatement("SELECT c_id FROM Customer WHERE c_name = ?");
            pstmt.setString(1, c_name);
            cidrs = pstmt.executeQuery();
            cidrs.next();


        } catch (Exception e) {
            e.printStackTrace();
        }
        int e_id = eidrs.getInt("e_id");
        int c_id = cidrs.getInt("c_id");

        try {
            Connection conn
                    = DriverManager.getConnection(
                    "jdbc:mysql://192.168.0.85:3306/SKLL_Library", "root", "1234");
            PreparedStatement pstmt = conn.prepareStatement("""
                    INSERT INTO Orders (start_date, c_id, e_id)
                                        VALUES (?, ?, ?)""");

            pstmt.setString(1, Date.valueOf(LocalDate.now());
            pstmt.setInt(2, c_id);
            pstmt.setInt(3, e_id);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
