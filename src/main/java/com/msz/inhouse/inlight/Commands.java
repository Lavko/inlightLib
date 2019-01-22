package com.msz.inhouse.inlight;

import com.msz.inhouse.inlight.container.Group;

class Commands {
    protected final static byte ZERO = 0x00;
    protected final static byte END = 0x55;

    protected static byte on(Group group) {
        switch(group) {
            case ALL: return (byte)0x42;
            case FIRST: return (byte)0x45;
            case SECOND: return (byte)0x47;
            case THIRD: return (byte)0x49;
            case FOURTH: return (byte)0x4B;
            default: return (byte)0x00;
        }
    }

    protected static byte off(Group group) {
        switch(group) {
            case ALL: return (byte)0x41;
            case FIRST: return (byte)0x46;
            case SECOND: return (byte)0x48;
            case THIRD: return (byte)0x4A;
            case FOURTH: return (byte)0x4C;
            default: return (byte)0x00;
        }
    }

    protected static byte setWhite(Group group) {
        switch(group) {
            case ALL: return (byte)0xC2;
            case FIRST: return (byte)0xC5;
            case SECOND: return (byte)0xC7;
            case THIRD: return (byte)0xC9;
            case FOURTH: return (byte)0xCB;
            default: return (byte)0x00;
        }
    }

    protected static byte setBrightness() {
        return (byte)0x4E;
    }

    protected static byte setHue() {
        return (byte)0x40;
    }

    protected static byte setMode() {
        return (byte)0x4D;
    }

    protected static byte speedUp() {
        return (byte)0x44;
    }

    protected static byte speedDown() {
        return (byte)0x43;
    }
}
