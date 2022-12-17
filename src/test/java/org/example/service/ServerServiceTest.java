package org.example.service;

import org.example.enums.Status;
import org.example.model.Client;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ServerServiceTest {

    @Test
    public void serializeListTest() {
        ServerService serverService = new ServerService(8086);
        Client client = new Client("user1", new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()), "Socket[addr=/127.0.0.1,port=57210,localport=8085]", Status.CONNECT);
        serverService.serializeList(client);
        Assert.assertEquals("user1", client.getName());
    }

    @Test
    public void clientProcessorTest() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8087);
        Boolean socketInfo = serverSocket.isClosed();
        Assert.assertEquals(false, socketInfo);
    }
}
