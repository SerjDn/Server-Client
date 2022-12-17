package org.example.model;

import lombok.*;
import org.example.enums.Status;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Client {
    private String name;
    private String datetime;
    private String socketInfo;
    private Status status;
}
