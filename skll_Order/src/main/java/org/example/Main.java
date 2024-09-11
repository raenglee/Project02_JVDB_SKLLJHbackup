package org.example;


import org.example.Oder.OrdersRepository;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    private OrdersRepository oderRepository = new OrdersRepository();

    Main() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    무엇을 하시겠습니까?
                    1. 대여
                    """);

            int n = scanner.nextInt();

            if (n == 1) {
                oderRepository.rental();
            } else {
                System.out.println("종료합니다.");
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}