package org.example.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Client {
    private String name;
    private String datetime;
    private String socketInfo;
    private String status;
}
