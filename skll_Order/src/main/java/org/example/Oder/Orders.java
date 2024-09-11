package org.example.Oder;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Orders {
    private int o_id;
    private int c_id;
    private int e_id;
    private LocalDate start_date;
    private LocalDate end_date;
    private LocalDate extension_date;

    @Override
    public String toString() {
        return "주문기록{" +
                "주문번호: " + o_id +
                ", 고객 번호: " + c_id +
                ", e_id=" + e_id +
                ", 대여일: " + start_date +
                ", 반납일: " + end_date +
                ", 연장: " + extension_date +
                '}';
    }
}





