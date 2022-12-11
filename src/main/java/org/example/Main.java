package org.example;

import org.example.service.ServerService;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("""
                ****************************************************************************
                USER CHAT V1.0
                ****************************************************************************
                This App will add to log-file info about user connections/disconnections.
                Enter the command "exit" if you want to leave from the server.
                Waiting for user connection...
                ****************************************************************************
                """);

        ServerService serverService = new ServerService(8085);
        serverService.clientProcessor();
    }
}
