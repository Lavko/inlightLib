package com.msz.inhouse.inlight;

import java.io.IOException;
import java.net.*;

class CommunicationService {

    protected static void sendCommand(String ipAddress, byte[] command) throws IOException {
        InetAddress host = InetAddress.getByName(ipAddress);
        CommunicationService.sendCommand(host, command);
    }

    protected static void sendCommand(InetAddress host, byte[] command) throws IOException {
        CommandSender commandSender = new CommandSender(host, command);
        Thread senderThread = new Thread(commandSender);
        senderThread.run();
    }
}
