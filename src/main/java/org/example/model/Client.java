package org.example.model;

import lombok.*;

import java.net.Socket;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Client {
    private String name;
    private String datetime;
    private String socket;
}
