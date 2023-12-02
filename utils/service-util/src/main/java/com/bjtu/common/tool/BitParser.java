package com.bjtu.common.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BitParser {
    private byte[] data;
    private int startBit;

    public BitParser(byte[] data) {
        this.data = data;
        this.startBit = 0;
    }

    public String parseBitsToDecimal(int numBits) {
        long value = 0;
        value = getValue(numBits, value, startBit, data);

        startBit += numBits;

        return String.valueOf(value);
    }

    static long getValue(int numBits, long value, int startBit, byte[] data) {
        int bitCount = 0;

        for (int i = startBit; bitCount < numBits && i < data.length * 8; i++) {
            int byteIndex = i / 8;
            int bitIndex = i % 8;

            int bit = (data[byteIndex] >> (7 - bitIndex)) & 1;
            value = (value << 1) | bit;

            bitCount++;
        }
        return value;
    }

    public String parseTime(int numBits) throws ParseException {
        return String.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(parseBitsToDecimal(numBits)));
    }
    public String parseBCD(int numBits) {
        StringBuilder sb = new StringBuilder();
        int numDigits = numBits / 4; // 每个 BCD 编码占用 4 位，总共有 numDigits 个数字

        for (int i = 0; i < numDigits; i++) {
            String digit = parseBitsToDecimal(4);
            sb.append(digit);
        }

        return sb.toString();
    }
    public String parseIPAddress() {
        StringBuilder sb = new StringBuilder();

        // 解析 ABCD 四个网段
        for (int i = 0; i < 4; i++) {
            String segment = parseBitsToDecimal(8);
            sb.append(segment);
            if (i < 3) {
                sb.append(".");
            }
        }
        startBit += 32;
        return sb.toString();
    }

}

