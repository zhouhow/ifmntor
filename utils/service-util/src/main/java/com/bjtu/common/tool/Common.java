package com.bjtu.common.tool;

import com.bjtu.model.base.PcapEntity;
import com.bjtu.model.pcap1.Alarm;
import com.bjtu.model.pcap1.TrainDispatchCommand;
import com.bjtu.model.pcap1.TrainDispatchCommandConfirm;
import org.apache.commons.lang3.ArrayUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.DatatypeConverter;

import com.bjtu.common.tool.Enums.*;

public class Common {
    public static void confirmCommand(List<PcapEntity> trainDispatchCommand, List<Alarm> alarmCommand, TrainDispatchCommandConfirm trainDispatchCommandConfirm, int interval) {
        PcapEntity entity;
        Iterator<PcapEntity> iterator = trainDispatchCommand.iterator();
        while(iterator.hasNext()){
            entity = iterator.next();
            if (entity instanceof TrainDispatchCommand) {
                TrainDispatchCommand dispatchCommand = (TrainDispatchCommand) entity;
                if (
                        dispatchCommand.getSourceAddress().equals(trainDispatchCommandConfirm.getSourceAddress())
                                && dispatchCommand.getDestinationAddress().equals(trainDispatchCommandConfirm.getDestinationAddress())
                                && dispatchCommand.getCommandInfoNumber().equals(trainDispatchCommandConfirm.getCommandInfoNumber())
                ) {
                    iterator.remove();
                    break;
                }
                if(addAlarm(alarmCommand, interval, entity)) iterator.remove();
            } else break;
        }
    }
    public static boolean addAlarm(List<Alarm> alarmCommand, int interval, PcapEntity entity){
        if(new Date().getTime() - entity.getCreateTime().getTime() >= interval){
            Alarm alarm = new Alarm();
            alarm.setAlarmTime(new Date().toString());
            alarm.setSourceAddress(entity.getSourceAddress());
            alarm.setDestinationAddress(entity.getDestinationAddress());
            alarmCommand.add(alarm);
            return true;
        }else return false;
    }

    public static String toIpAddress(byte[] ipAddress) {
        try {
            InetAddress inetAddress = InetAddress.getByAddress(ipAddress);
            return inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String sixteen(byte b) {
        return Integer.toHexString(b).toUpperCase() + "H";
    }

    /// 计算byte[]的16进制,默认小端
    public static String sixteen(byte[] x, boolean isLittle) {
        if (isLittle) {
            ArrayUtils.reverse(x);
        }
        return DatatypeConverter.printHexBinary(x).toLowerCase() + "H";
    }


    /// 计算byte[]的10进制,默认小端
    public static String ten(byte[] x, boolean isLittle) {
        if (isLittle) {
            ArrayUtils.reverse(x);
        }
        int length = x.length;
        int shift = 0;
        long result = 0;
        for (int i = length - 1; i >= 0; i--) {
            result |= (long) (x[i] & 0xff) << shift;
            shift += 8;
        }
        return Long.toString(result);
    }
    public static Long tenToLong(byte[] x, boolean isLittle) {
        if (isLittle) {
            ArrayUtils.reverse(x);
        }
        int length = x.length;
        int shift = 0;
        long result = 0;
        for (int i = length - 1; i >= 0; i--) {
            result |= (long) (x[i] & 0xff) << shift;
            shift += 8;
        }
        return result;
    }

    public static long Cal(byte[] x) {
        int length = x.length;
        int shift = 0;
        long result = 0;
        for (int i = length - 1; i >= 0; i--) {
            result |= (long) x[i] << shift;
            shift += 8;
        }
        return result;
    }

    public static String calMile(byte[] data) {
        if (data[0] == (byte) 0xFF && data[1] == (byte) 0xFF && data[2] == (byte) 0xFF) {
            return "无效值";
        } else {
            int value = (data[0] & 0xFF) + ((data[1] & 0xFF) << 8) + ((data[2] & 0xFF) << 16);
            if ((value & 0x00800000) != 0) { // 检查符号位
                value |= 0xFF000000; // 扩展符号位
            }
            return Integer.toString(value);
        }
    }

    /// 小端,4字节年月日时分秒
    public static String calTime4(byte[] data) {
        int timeValue = (data[3] << 24) | (data[2] << 16) | (data[1] << 8) | data[0];
        int second = timeValue & 0x3F;
        int minute = (timeValue >> 6) & 0x3F;
        int hour = (timeValue >> 12) & 0x1F;
        int day = (timeValue >> 17) & 0x1F;
        int month = (timeValue >> 22) & 0x0F;
        int year = ((timeValue >> 26) & 0x3F) + 2000;
        return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
    }

    /// 大端,6字节年月日时分秒
    public static String calTime6(byte[] data) {
        // 解析年份，高2位为世纪数（一般为20），低6位为年份（00-99）
        return time(data);
    }

    /// 大端,两个3字节年月日时分秒
    public static String calTime3(byte[] d1, byte[] d2) {
        byte[] data = new byte[6];
        System.arraycopy(d1, 0, data, 0, 3);
        System.arraycopy(d2, 0, data, 3, 3);
        // 解析年份，高2位为世纪数，低6位为年份（00-99）
        return time(data);
    }

    private static String time(byte[] data) {
        int year = ((data[0] & 0xF0) >> 4) * 100 + (data[0] & 0x0F);
        // 解析月份，高位为十位（01-12），低位为个位（01-09）
        int month = ((data[1] & 0xF0) >> 4) * 10 + (data[1] & 0x0F);
        // 解析日期，高位为十位（01-31），低位为个位（01-09）
        int day = ((data[2] & 0xF0) >> 4) * 10 + (data[2] & 0x0F);
        // 解析小时，高位为十位（00-23），低位为个位（00-09）
        int hour = ((data[3] & 0xF0) >> 4) * 10 + (data[3] & 0x0F);
        // 解析分钟，高位为十位（00-59），低位为个位（00-09）
        int minute = ((data[4] & 0xF0) >> 4) * 10 + (data[4] & 0x0F);
        // 解析秒，高位为十位（00-59），低位为个位（00-09）
        int second = ((data[5] & 0xF0) >> 4) * 10 + (data[5] & 0x0F);
        return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
    }

    private static String parseMessage(byte[] data) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < data.length) {
            if (data[i] >= 0xA1 && data[i] <= 0xF7) {
                if (i + 1 < data.length) {
                    byte highByte = (byte) (data[i] - 0xA0);
                    byte lowByte = (byte) (data[i + 1] - 0xA0);
                    sb.append(Charset.forName("GBK").decode(ByteBuffer.wrap(new byte[]{highByte, lowByte})));
                    i += 2;
                }
            } else if (data[i] >= 0x00 && data[i] <= 0x7F) {
                sb.append((char) data[i]);
                i++;
            } else {
                // 如果不是区位码也不是ASCII码，忽略该字节
                i++;
            }
        }
        return sb.toString();
    }

    public static String getText(byte[] data) {
        int N = data.length;
        // 找到凭证名称的结束位置
        int endPos = ArrayUtils.indexOf(data, (byte) 0x0D);
        if (endPos == -1)
            return "格式错误";
        // 提取凭证名称
        String voucherName = new String(data, 0, endPos, Charset.forName("GBK"));
        // 找到发令处所名称的结束位置
        int startPos = endPos + 2; // 跳过0DH和0AH
        endPos = ArrayUtils.indexOf(data, (byte) 0x0D, startPos);
        if (endPos == -1)
            return "格式错误";
        // 提取发令处所名称
        String issuerName = new String(data, startPos, endPos - startPos, Charset.forName("GBK"));
        // 提取正文并拼接
        byte[] textBytes = ArrayUtils.subarray(data, endPos + 2, N);
        return voucherName + issuerName + parseMessage(textBytes);
    }

    public static DataGramType check(byte[] data, String protocol) {
        if(sixteen(new byte[]{data[0], data[1]},false).equals("1002H")){
            if (protocol.equals("UDP")) {
                DataGramType temp = data.length >= 18 ? DataGramType.get(data[16]) : DataGramType.Unknown;
                return temp == DataGramType.TraDisCom && DataGramType.get(data[17]) == DataGramType.TraDisComCon ?
                        DataGramType.TraDisComCon : temp;

            } else if (protocol.equals("TCP")) {
                return data.length >= 5 ? DataGramType.get(data[4]) : DataGramType.Unknown;
            }
        }else {
            byte[] bytes = new byte[8];
            System.arraycopy(data, 0, bytes, 0,8);
            return DataGramType.get(new Byte(String.valueOf(tenToLong(bytes, true))));
        }
        return DataGramType.Unknown;
    }

    public static String calPosition(byte[] data, boolean flag) {
        if (flag) {
            double longitude;
            if ((data[0] << 8 | data[1] & 0xFF) == 0xFFFF) { // 判断是否无卫星定位系统
                longitude = Double.NaN; // 如果无卫星定位系统，则经度为 NaN
            } else {
                int degree = bcdToDecimal(data[0]) * 100 + bcdToDecimal(data[1]); // 解析度码
                double minute = bcdToDecimal(data[2]) * 10.0 + bcdToDecimal(data[3]) + bcdToDecimal(data[4]) / 60.0; // 解析分码
                longitude = degree + minute / 60.0;
            }
            return Double.toString(longitude);
        } else {
            double latitude;
            if ((data[0] << 8 | data[1] & 0xFF) == 0xFFFF) { // 判断是否无卫星定位系统
                latitude = Double.NaN; // 如果无卫星定位系统，则经度为 NaN
            } else {
                int degree = bcdToDecimal(data[0]) * 100 + bcdToDecimal(data[1]); // 解析度码
                double minute = bcdToDecimal(data[1]) * 10.0 + bcdToDecimal(data[2]) + bcdToDecimal(data[3]) / 60.0; // 解析分码
                latitude = degree + minute / 60.0;
            }
            return Double.toString(latitude);
        }
    }

    // 将 BCD 码转换为十进制数的函数
    static int bcdToDecimal(byte bcd) {
        return (bcd >> 4) * 10 + (bcd & 0x0F);
    }

    public static String parseQScale(byte data){
        if(data == 0){
            return "10cm";
        }else if(data == 1){
            return "1m";
        }else if(data == 2){
            return "10m";
        }else {
            return "-";
        }
    }
    public static String parseCTCSLevel(byte data){
        if(data == 1){
            return "CTCS 2级";
        }else if(data == 3){
            return "CTCS 3级";
        }else {
            return "-";
        }
    }
    public static String parseAxeLoad(byte data){
        if(data <= 80){
            return String.valueOf(data /  2);
        }else if(data == 126){
            return ">40";
        }else {
            return "-";
        }
    }
    public static String parseTrainType(byte data){
        if(data == 1){
            return "8节编组列车";
        }else if(data == 2){
            return "16节编组列车";
        }else if(data == 3){
            return "17节编组列车";
        }else return "-";
    }

    public static String parseMesLevel(byte data){
        if(data == 1){
            return "C2";
        }else if(data == 3){
            return "C3";
        }else if(data == 5){
            return "不受等级限制";
        }else return "-";
    }
}
