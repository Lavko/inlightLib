package com.msz.inhouse.inlight;

import lombok.extern.java.Log;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

@Log
public class CommandSender implements Runnable{
    private final static int PORT = 8899;

    private final InetAddress host;
    private final byte[] command;

    public CommandSender(InetAddress host, byte[] command) {
        this.host = host;
        this.command = command;
    }

    @Override
    public void run() {
        try {
            DatagramSocket socket = new DatagramSocket(null);
            DatagramPacket packet;
            packet = new DatagramPacket(command, command.length, host, PORT);
            socket.send(packet);
            socket.close();
        } catch (Exception e) {
            log.warning(e.getMessage());
        }
    }
}
