package org.example;

import org.example.customer.CustomerRepository;

import java.util.Scanner;

public class Main {

    private CustomerRepository customerRepository = new CustomerRepository();

    Main() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    무엇을 하시겠습니까?
                    1. 전체 회원 정보 확인
                    2. 대여한 회원 정보 확인
                    3. 회원 가입
                    4. 회원 정보 수정            
                    5. 회원 탈퇴
                    """);

            int n = scanner.nextInt();

            if (n == 1) {
                customerRepository.select();
            } else if (n == 2) {
                customerRepository.selectState();
            } else if (n == 3) {
                customerRepository.insert();
            } else if (n == 4) {
                customerRepository.update();
            } else if (n == 5) {
                customerRepository.delete();
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