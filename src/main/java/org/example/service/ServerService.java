package org.example.service;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.example.model.Client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;

public class ServerService {

    public static void serializeList(Client client) {
//        File file = new File("src/main/resources/client.json");
        try {
            JsonMapper mapper = new JsonMapper();
            List<Client> clients = new ArrayList<>();
            clients.add(client);
            mapper.writeValue(new File("src/main/resources/client.json"), clients);
        } catch (IOException e) {
            System.out.println("Can not create file: " + ".................");
        }
    }

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8085);

        while (true) {

            Socket socket = serverSocket.accept();

            Client client = new Client("user", new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()), socket.toString());
            System.out.println(client);
            System.out.println("user has been connected!");

            System.out.println(client.getSocket());
            serializeList(client);

            new Thread(() -> {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String word;

                    while ( (word = bufferedReader.readLine()) != null) {
                        System.out.println(word);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }
}
