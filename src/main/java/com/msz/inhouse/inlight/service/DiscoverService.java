package com.msz.inhouse.inlight.service;

import com.msz.inhouse.inlight.Controller;
import com.msz.inhouse.inlight.container.ControllerNotFoundException;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

/** Discovery service to find your Wifi Box IP Address.
 * @author Michal Szala
 */
public class DiscoverService {
    private final static String BROADCAST_MESSAGE = "Link_Wi-Fi";
    private final static String IP_MASK = "255.255.255.255";
    private final static int PORT = 48899;

    /** Creates controller from found IP address of WifiBox using local network 192.168.*.*
     *
     * @return Controller with found IP address.
     * @throws IOException When found network errors.
     * @throws ControllerNotFoundException When did not find WifiBox in local network.
     */
    public Controller discoverWifiBox() throws IOException, ControllerNotFoundException {
        return this.discoverWiFiBox(IP_MASK);
    }

    /** Creates controller from provided WifiBox host
     *
     * @param host Provided WifiBox host address - IP or domain.
     * @return Controller with found IP address.
     * @throws IOException When found network errors.
     * @throws ControllerNotFoundException When did not find WifiBox on provided host.
     */
    public Controller discoverWiFiBox(String host) throws ControllerNotFoundException, IOException{
        DatagramSocket socket = new DatagramSocket(null);
        byte[] broadcastData = BROADCAST_MESSAGE.getBytes(StandardCharsets.UTF_8);

        InetAddress address = InetAddress.getByName(host);
        DatagramPacket packet;
        packet = new DatagramPacket(broadcastData, broadcastData.length, address, PORT);
        socket.setSoTimeout(1000);
        socket.send(packet);
        socket.receive(packet);
        socket.close();

        String ipAddress = packet.getAddress().getHostAddress();
        if(ipAddress == null || ipAddress == "") {
            throw new ControllerNotFoundException("Controller not found.");
        }
        return new Controller(ipAddress);
    }
}
