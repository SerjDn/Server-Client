package org.example.service;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.example.enums.Status;
import org.example.model.Client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;

public class ServerService {

    private ServerSocket serverSocket;
    private final List<Client> clients = new ArrayList<>();
    File file = new File("src/main/resources/client.json");
    private int userCounter = 1;

    public ServerService(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Can not create new server...");
        }
    }

    public void serializeList(Client client) {
        try {
            JsonMapper mapper = new JsonMapper();
            clients.add(client);
            mapper.writeValue(file, clients);
        } catch (IOException e) {
            System.out.println("Can not create/read file: " + file);
        }
    }

    public void clientProcessor() throws IOException {

        while (true) {

            Socket socket = serverSocket.accept();

            Client client = new Client("user" + userCounter, new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()), socket.toString(), Status.CONNECT);
            serializeList(client);
            System.out.println(client.getName() + " has been connected!");
            userCounter += 1;

            new Thread(() -> {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String word;

                    while ( (word = bufferedReader.readLine()) != null) {
                        System.out.println(client.getName() + ": " + word);
                        if (word.equals("exit")) {
                            Client clientLeft = new Client(client.getName(), new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()), client.getSocketInfo(), Status.DISCONNECT);
                            serializeList(clientLeft);
                            System.out.println(client.getName() + " left from server!");
                            break;
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }
}
