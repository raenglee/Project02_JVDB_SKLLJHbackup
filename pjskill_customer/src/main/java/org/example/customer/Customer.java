package org.example.customer;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    private int c_id;
    private String c_name;
    private String c_phone;
    private String c_state;
    private LocalDate join_date;
    private LocalDate withdraw_date;

    @Override
    public String toString() {
        return "Customer{" +
                "c_id=" + c_id +
                ", c_name='" + c_name + '\'' +
                ", c_phone='" + c_phone + '\'' +
                ", c_state='" + c_state + '\'' +
                ", join_date=" + join_date +
                ", withdraw_date=" + withdraw_date +
                '}';
    }
}
