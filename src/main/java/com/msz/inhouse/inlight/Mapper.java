package com.msz.inhouse.inlight;

class Mapper {

    protected static byte mapBrightness(int percent) {
        if(percent <= 0) {
            return (byte)0x00;
        }
        if(percent >= 100) {
            return (byte)27;
        }

        return (byte)(percent / 4 + 2);
    }

    protected static byte mapHue(double hueValue) {
        return (byte)((256 + 176 - (int)(hueValue / 360.0 * 255.0)) % 256);
    }
}
