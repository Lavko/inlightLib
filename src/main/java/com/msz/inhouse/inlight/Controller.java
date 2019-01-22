package com.msz.inhouse.inlight;

import com.msz.inhouse.inlight.container.Group;
import javafx.scene.paint.Color;
import lombok.Data;
import lombok.extern.java.Log;

import java.io.IOException;

/** Represents wifiBox as Controller.
 * @author Michal Szala
 */
@Data
@Log
public class Controller {
    /** Represents the wifiBox IP Adress.
     */
    private String ipAddress;
    private Group activeGroup;

    /** Creates a Controller with the specified IP address.
     * @param ipAddress The controller's IP address.
     */
    public Controller(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /** Switches on selected group.
     * @param group Selected group
     * @throws IOException when Connection cannot be established.
     */
    public void on(Group group) throws IOException {
        byte[] command = new byte[] {Commands.on(group), Commands.ZERO, Commands.END};
        CommunicationService.sendCommand(ipAddress, command);
        this.activeGroup = group;
    }

    /** Switches off selected group.
     * @param group Selected group
     * @throws IOException when Connection cannot be established.
     */
    public void off(Group group) throws IOException {
        byte[] command = new byte[] {Commands.off(group), Commands.ZERO, Commands.END};
        CommunicationService.sendCommand(ipAddress, command);
        this.activeGroup = group;
    }

    /** Set selected group to white light.
     * @param group Selected group
     * @throws IOException when Connection cannot be established.
     */
    public void setWhite(Group group) throws IOException {
        byte[] command = new byte[] {Commands.setWhite(group), Commands.ZERO, Commands.END};
        CommunicationService.sendCommand(ipAddress, command);
        this.activeGroup = group;
    }

    /** Sets brightness of selected group.
     * @param group Selected group.
     * @param percent Brightness percentages from 0 - 100 in int.
     * @throws IOException when Connection cannot be established.
     */
    public void setBrightness(Group group, int percent) throws IOException {
        this.activateGroup(group);
        byte[] command = new byte[] {Commands.setBrightness(), Mapper.mapBrightness(percent), Commands.END};
        CommunicationService.sendCommand(ipAddress, command);
    }

    /** Sets brightness of active group.
     * @param percent Brightness percentages from 0 - 100 in int.
     * @throws IOException when Connection cannot be established.
     */
    public void setBrightness(int percent) throws IOException {
        this.activateGroup(this.activeGroup);
        byte[] command = new byte[] {Commands.setBrightness(), Mapper.mapBrightness(percent), Commands.END};
        CommunicationService.sendCommand(ipAddress, command);
    }

    /** Sets brightness of selected group.
     * @param group Selected group.
     * @param value Brightness value from 2 - 27 in byte.
     * @throws IOException when Connection cannot be established.
     */
    public void setBrightness(Group group, byte value) throws IOException {
        this.activateGroup(group);
        byte[] command = new byte[] {Commands.setBrightness(), value, Commands.END};
        CommunicationService.sendCommand(ipAddress, command);
    }

    /** Sets brightness of active group.
     * @param value Brightness value from 2 - 27 in byte.
     * @throws IOException when Connection cannot be established.
     */
    public void setBrightness(byte value) throws IOException {
        this.activateGroup(this.activeGroup);
        byte[] command = new byte[] {Commands.setBrightness(), value, Commands.END};
        CommunicationService.sendCommand(ipAddress, command);
    }

    /** Sets hue of selected group.
     * @param group Selected group.
     * @param hueValue Hue value from 0 - 360.
     * @throws IOException when Connection cannot be established.
     */
    public void setHue(Group group, double hueValue) throws IOException {
        this.activateGroup(group);
        byte[] command = new byte[] {Commands.setHue(), Mapper.mapHue(hueValue), Commands.END};
        CommunicationService.sendCommand(ipAddress, command);
    }

    /** Sets hue of active group.
     * @param hueValue Hue value from 0 - 360.
     * @throws IOException when Connection cannot be established.
     */
    public void setHue(double hueValue) throws IOException {
        this.activateGroup(this.activeGroup);
        byte[] command = new byte[] {Commands.setHue(), Mapper.mapHue(hueValue), Commands.END};
        CommunicationService.sendCommand(ipAddress, command);
    }

    /** Sets color of selected group.
     * @param group Selected group.
     * @param color Selected color.
     * @throws IOException when Connection cannot be established.
     */
    public void setColor(Group group, Color color) throws IOException {
        this.activateGroup(group);
        byte[] command = new byte[] {Commands.setHue(), Mapper.mapHue(color.getHue()), Commands.END};
        CommunicationService.sendCommand(ipAddress, command);
    }

    /** Sets color of active group.
     * @param color Selected color.
     * @throws IOException when Connection cannot be established.
     */
    public void setColor(Color color) throws IOException {
        this.activateGroup(this.activeGroup);
        byte[] command = new byte[] {Commands.setHue(), Mapper.mapHue(color.getHue()), Commands.END};
        CommunicationService.sendCommand(ipAddress, command);
    }

    /** Toggle mode of selected group.
     * @param group Selected group.
     * @throws IOException when Connection cannot be established.
     */
    public void setMode(Group group) throws IOException {
        this.activateGroup(group);
        byte[] command = new byte[] {Commands.setMode(), Commands.ZERO, Commands.END};
        CommunicationService.sendCommand(ipAddress, command);
    }

    /** Toggle mode of active group.
     * @throws IOException when Connection cannot be established.
     */
    public void setMode() throws IOException {
        this.activateGroup(this.activeGroup);
        byte[] command = new byte[] {Commands.setMode(), Commands.ZERO, Commands.END};
        CommunicationService.sendCommand(ipAddress, command);
    }

    /** Speeds up mode of selected group.
     * @param group Selected group.
     * @throws IOException when Connection cannot be established.
     */
    public void speedUp(Group group) throws IOException {
        this.activateGroup(group);
        byte[] command = new byte[] {Commands.speedUp(), Commands.ZERO, Commands.END};
        CommunicationService.sendCommand(ipAddress, command);
    }

    /** Speeds up mode of active group.
     * @throws IOException when Connection cannot be established.
     */
    public void speedUp() throws IOException {
        this.activateGroup(this.activeGroup);
        byte[] command = new byte[] {Commands.speedUp(), Commands.ZERO, Commands.END};
        CommunicationService.sendCommand(ipAddress, command);
    }

    /** Speeds down mode of selected group.
     * @param group Selected group.
     * @throws IOException when Connection cannot be established.
     */
    public void speedDown(Group group) throws IOException {
        this.activateGroup(group);
        byte[] command = new byte[] {Commands.speedDown(), Commands.ZERO, Commands.END};
        CommunicationService.sendCommand(ipAddress, command);
    }

    /** Speeds down mode of active group.
     * @throws IOException when Connection cannot be established.
     */
    public void speedDown() throws IOException {
        this.activateGroup(this.activeGroup);
        byte[] command = new byte[] {Commands.speedDown(), Commands.ZERO, Commands.END};
        CommunicationService.sendCommand(ipAddress, command);
    }

    private void activateGroup(Group group) throws IOException {
        if(group != activeGroup) {
            this.on(group);
            this.activeGroup = group;
        }
    }
}
