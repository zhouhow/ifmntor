//coding=utf-8
package com.bjtu.common.tool;

import java.util.HashMap;
import java.util.Map;

public class Enums {
    public enum DataGramType {
        C2lact((byte) 0x01),
        L2cact((byte) 0x81),
        C2lapp((byte) 0x11),
        L2capp((byte) 0x91),
        TraNumVerify((byte) 0x05),
        TraDisCom((byte) 0x06),
        TraDisComCon((byte) 0x51),
        TraNumVerifyExpand((byte) 0x07),
        TraData((byte) 0x129),
        TraPosRep((byte) 0x136),
        MesVerify((byte) 0x146),
        TaskEnd((byte) 0x150),
        VerIncomp((byte) 0x154),
        ComDiaBegin((byte) 0x155),
        ComDiaEnd((byte) 0x156),
        SomPosRep((byte) 0x157),
        ComDiaEst((byte) 0x159),
        TraDaVerify((byte) 0x08),
        ComMes((byte) 0x24),
        SysVer((byte) 0x32),
        ComDiaEndVerify((byte) 0x39),
        AccTra((byte) 0x41),
        Unknown((byte) 0);

        private final byte value;
        private static final Map<Byte, DataGramType> map = new HashMap<>();

        static {
            for (DataGramType type : DataGramType.values()) {
                map.put(type.value, type);
            }
        }
        public static DataGramType get(byte b) {
            return map.get(b) == null ? Unknown : map.get(b);
        }
        private DataGramType(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }
    }

    public enum BusiType {
        车次号信息((byte) 0x05),
        调度命令信息((byte) 0x06),
        列车启动和停稳信息((byte) 0x07);

        private final byte value;
        private static final Map<Byte, BusiType> map = new HashMap<>();

        static {
            for (BusiType type : BusiType.values()) {
                map.put(type.value, type);
            }
        }
        public static String get(byte b) {
            return map.get(b) == null ? "Unknown" : map.get(b).toString();
        }
        private BusiType(byte value) {
            this.value = value;
        }

    }

    public enum CommType {
        列车停稳信息((byte) 0x02),
        列车启动信息((byte) 0x03),
        调度命令信息((byte) 0x20),
        车次号信息((byte) 0x21),
        调度命令确认信息((byte) 0x51);

        private final byte value;
        private static final Map<Byte, CommType> map = new HashMap<>();

        static {
            for (CommType type : CommType.values()) {
                map.put(type.value, type);
            }
        }
        public static String get(byte b) {
            return map.get(b) == null ? "Unknown" : map.get(b).toString();
        }
        private CommType(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }
    }

    public enum FuncType {
        调度命令((byte) 0x01),
        列车进路预告信息((byte) 0x07),
        出入库检测((byte) 0x20),
        出入库检测请求((byte) 0x80),
        自动确认((byte) 0x81),
        手动签收((byte) 0x82);

        private final byte value;
        private static final Map<Byte, FuncType> map = new HashMap<>();

        static {
            for (FuncType type : FuncType.values()) {
                map.put(type.value, type);
            }
        }
        public static String get(byte b) {
            return map.get(b) == null ? "Unknown" : map.get(b).toString();
        }
        private FuncType(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }
    }

    public enum U1CheckUnitType {
        Unknown,
        轨道检测,
        弓网检测,
        TMIS,
        DMIS,
        列控通讯,
        语音录音,
        轴温报警,
        鸣笛检查,
        预留给备用单元;
        private static final Map<Byte, U1CheckUnitType> map = new HashMap<>();

        static {
            for (U1CheckUnitType type : U1CheckUnitType.values()) {
                map.put((byte) type.ordinal(), type);
            }
        }
        public static String get(byte b) {
            return map.get(b) == null ? "Unknown" : map.get(b).toString();
        }
    }

    public enum U1Signal {
        无灯,
        绿,
        黄,
        双黄,
        红黄,
        红,
        白,
        绿黄,
        黄2;
        private static final Map<Byte, U1Signal> map = new HashMap<>();

        static {
            for (U1Signal type : U1Signal.values()) {
                map.put((byte) type.ordinal(), type);
            }
        }
        public static String get(byte b) {
            return map.get(b) == null ? "Unknown" : map.get(b).toString();
        }
    }

    public enum U1Condi {
        _((byte) 0x00),
        零位((byte) 0x01),
        向后((byte) 0x02),
        向前((byte) 0x04),
        制动((byte) 0x08),
        牵引((byte) 0x16);

        private final byte value;
        private static final Map<Byte, U1Condi> map = new HashMap<>();

        static {
            for (U1Condi type : U1Condi.values()) {
                map.put(type.value, type);
            }
        }
        public static String get(byte b) {
            return map.get(b) == null ? "Unknown" : map.get(b).toString();
        }
        private U1Condi(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }
    }
    public enum U1SignalType {
        出站((byte)0x02),
        进站((byte)0x03),
        通过((byte)0x04),
        预告((byte)0x05),
        容许((byte)0x06);

        private final byte value;
        private static final Map<Byte, U1SignalType> map = new HashMap<>();

        static {
            for (U1SignalType type : U1SignalType.values()) {
                map.put(type.value, type);
            }
        }
        public static String get(byte b) {
            return map.get(b) == null ? "Unknown" : map.get(b).toString();
        }
        private U1SignalType(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }
    }

    public enum U1BenBu {
        本货,
        本客,
        补货,
        补客;
        private static final Map<Byte, U1BenBu> map = new HashMap<>();

        static {
            for (U1BenBu type : U1BenBu.values()) {
                map.put((byte) type.ordinal(), type);
            }
        }
        public static String get(byte b) {
            return map.get(b) == null ? "Unknown" : map.get(b).toString();
        }
    }

    public enum U1DevSta {
        监控非调车,
        降级非调车,
        监控调车,
        降级调车;
        private static final Map<Byte, U1DevSta> map = new HashMap<>();

        static {
            for (U1DevSta type : U1DevSta.values()) {
                map.put((byte) type.ordinal(), type);
            }
        }
        public static String get(byte b) {
            return map.get(b) == null ? "Unknown" : map.get(b).toString();
        }
    }
    public enum QDLrbg {
        反向,
        正向,
        未知,
        备用;
        private static final Map<Byte, QDLrbg> map = new HashMap<>();

        static {
            for (QDLrbg type : QDLrbg.values()) {
                map.put((byte) type.ordinal(), type);
            }
        }
        public static String get(byte b) {
            return map.get(b) == null ? "Unknown" : map.get(b).toString();
        }
    }
    public enum MMode2 {
        待机模式((byte)0x01),
        完全监控模式((byte)0x02),
        部分监控模式((byte)0x03),
        引导模式((byte)0x05),
        目视行车模式((byte)0x07),
        调车模式((byte)0x08),
        隔离模式((byte)0x09),
        机车信号模式((byte)0x10),
        休眠模式((byte)0x11);
        private static final Map<Byte, MMode2> map = new HashMap<>();
        private final byte value;
        static {
            for (MMode2 type : MMode2.values()) {
                map.put((byte) type.ordinal(), type);
            }
        }
        public static String get(byte b) {
            return map.get(b) == null ? "Unknown" : map.get(b).toString();
        }
        private MMode2(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }
    }
    public enum MMode3 {
        完全监控模式((byte)0x00),
        引导模式((byte)0x01),
        目视行车模式((byte)0x02),
        调车模式((byte)0x03),
        休眠模式((byte)0x05),
        待机模式((byte)0x06),
        冒进模式((byte)0x07),
        冒进后模式((byte)0x08),
        隔离模式((byte)0x10);
        private static final Map<Byte, MMode3> map = new HashMap<>();
        private final byte value;
        static {
            for (MMode3 type : MMode3.values()) {
                map.put((byte) type.ordinal(), type);
            }
        }
        public static String get(byte b) {
            return map.get(b) == null ? "Unknown" : map.get(b).toString();
        }
        private MMode3(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }
    }
    public enum MError {
        应答器一致性_链接位置或编号错误((byte)0x00),
        应答器一致性_链接应答器组报文错误((byte)0x01),
        应答器一致性_未链接应答器组报文错误((byte)0x02),
        无线一致性_无线消息错误((byte)0x03),
        无线一致性_消息时序错误((byte)0x04),
        无线一致性_无线超时((byte)0x05),
        非致命错误((byte)0x06),
        致命错误_设备处于SL或NL模式((byte)0x07);
        private static final Map<Byte, MError> map = new HashMap<>();
        private final byte value;
        static {
            for (MError type : MError.values()) {
                map.put((byte) type.ordinal(), type);
            }
        }
        public static String get(byte b) {
            return map.get(b) == null ? "Unknown" : map.get(b).toString();
        }
        private MError(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }
    }
    public enum MTask {
        办客((byte)0x01),
        不办客((byte)0x02);
        private static final Map<Byte, MTask> map = new HashMap<>();
        private final byte value;
        static {
            for (MTask type : MTask.values()) {
                map.put((byte) type.ordinal(), type);
            }
        }
        public static String get(byte b) {
            return map.get(b) == null ? "无效" : map.get(b).toString();
        }
        private MTask(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }
    }
    public enum MSkip {
        通过((byte)0x01),
        到发((byte)0x02);
        private static final Map<Byte, MSkip> map = new HashMap<>();
        private final byte value;
        static {
            for (MSkip type : MSkip.values()) {
                map.put((byte) type.ordinal(), type);
            }
        }
        public static String get(byte b) {
            return map.get(b) == null ? "无效" : map.get(b).toString();
        }
        private MSkip(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }
    }
    public enum QDir {
        反向((byte)0x01),
        正向((byte)0x02);
        private static final Map<Byte, QDir> map = new HashMap<>();
        private final byte value;
        static {
            for (QDir type : QDir.values()) {
                map.put((byte) type.ordinal(), type);
            }
        }
        public static String get(byte b) {
            return map.get(b) == null ? "无效" : map.get(b).toString();
        }
        private QDir(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }
    }
    public enum QTb {
        无折返((byte)0x01),
        原地立折((byte)0x02);
        private static final Map<Byte, QTb> map = new HashMap<>();
        private final byte value;
        static {
            for (QTb type : QTb.values()) {
                map.put((byte) type.ordinal(), type);
            }
        }
        public static String get(byte b) {
            return map.get(b) == null ? "无效" : map.get(b).toString();
        }
        private QTb(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }
    }
    public enum MC {
        无命令((byte)0x00),
        开门命令((byte)0x01),
        关门命令((byte)0x02),
        备用((byte)0x03);
        private static final Map<Byte, MC> map = new HashMap<>();
        private final byte value;
        static {
            for (MC type : MC.values()) {
                map.put((byte) type.ordinal(), type);
            }
        }
        public static String get(byte b) {
            return map.get(b) == null ? "无效" : map.get(b).toString();
        }
        private MC(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }
    }
    public enum QSS {
        未停稳((byte)0x00),
        停稳未停准((byte)0x01),
        停稳停准((byte)0x02);
        private static final Map<Byte, QSS> map = new HashMap<>();
        private final byte value;
        static {
            for (QSS type : QSS.values()) {
                map.put((byte) type.ordinal(), type);
            }
        }
        public static String get(byte b) {
            return map.get(b) == null ? "备用" : map.get(b).toString();
        }
        private QSS(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }
    }
    public enum MAM {
        非AM模式((byte)0x00),
        AM模式((byte)0x01);
        private static final Map<Byte, MAM> map = new HashMap<>();
        private final byte value;
        static {
            for (MAM type : MAM.values()) {
                map.put((byte) type.ordinal(), type);
            }
        }
        public static String get(byte b) {
            return map.get(b) == null ? "备用" : map.get(b).toString();
        }
        private MAM(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }
    }
    public enum MAS {
        预选策略1_低于ATP曲线5km((byte)0x01),
        预选策略1_低于ATP曲线2km((byte)0x02),
        预选策略1_低于ATP曲线8km((byte)0x03),
        按照运行计划控车((byte)0x04);
        private static final Map<Byte, MAS> map = new HashMap<>();
        private final byte value;
        static {
            for (MAS type : MAS.values()) {
                map.put((byte) type.ordinal(), type);
            }
        }
        public static String get(byte b) {
            return map.get(b) == null ? "备用" : map.get(b).toString();
        }
        private MAS(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }
    }
    public enum MDM {
        无效((byte)0x00),
        MM((byte)0x01),
        AM((byte)0x02),
        备用((byte)0x03);
        private static final Map<Byte, MDM> map = new HashMap<>();
        private final byte value;
        static {
            for (MDM type : MDM.values()) {
                map.put((byte) type.ordinal(), type);
            }
        }
        public static String get(byte b) {
            return map.get(b) == null ? "备用" : map.get(b).toString();
        }
        private MDM(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }
    }
    public enum MDS {
        异常((byte)0x00),
        车门开((byte)0x01),
        车门关((byte)0x02),
        备用((byte)0x03);
        private static final Map<Byte, MDS> map = new HashMap<>();
        private final byte value;
        static {
            for (MDS type : MDS.values()) {
                map.put((byte) type.ordinal(), type);
            }
        }
        public static String get(byte b) {
            return map.get(b) == null ? "备用" : map.get(b).toString();
        }
        private MDS(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }
    }
    public enum QS {
        位置无效((byte)0x00),
        位置有效((byte)0x01),
        位置未知((byte)0x02),
        备用((byte)0x03);
        private static final Map<Byte, QS> map = new HashMap<>();
        private final byte value;
        static {
            for (QS type : QS.values()) {
                map.put((byte) type.ordinal(), type);
            }
        }
        public static String get(byte b) {
            return map.get(b) == null ? "备用" : map.get(b).toString();
        }
        private QS(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }
    }
    public enum QDir3 {
        反向有效((byte)0x00),
        正向有效((byte)0x01),
        双向有效((byte)0x02);
        private static final Map<Byte, QDir3> map = new HashMap<>();
        private final byte value;
        static {
            for (QDir3 type : QDir3.values()) {
                map.put((byte) type.ordinal(), type);
            }
        }
        public static String get(byte b) {
            return map.get(b) == null ? "无效" : map.get(b).toString();
        }
        private QDir3(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }
    }
    public enum CcQDir {
        反向有效((byte)0x00),
        正向有效((byte)0x01);
        private static final Map<Byte, CcQDir> map = new HashMap<>();
        private final byte value;
        static {
            for (CcQDir type : CcQDir.values()) {
                map.put((byte) type.ordinal(), type);
            }
        }
        public static String get(byte b) {
            return map.get(b) == null ? "无效" : map.get(b).toString();
        }
        private CcQDir(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }
    }
    public enum CcQGDir {
        下坡或平坡((byte)0x00),
        上坡((byte)0x01);
        private static final Map<Byte, CcQGDir> map = new HashMap<>();
        private final byte value;
        static {
            for (CcQGDir type : CcQGDir.values()) {
                map.put((byte) type.ordinal(), type);
            }
        }
        public static String get(byte b) {
            return map.get(b) == null ? "无效" : map.get(b).toString();
        }
        private CcQGDir(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }
    }
    public enum QFront {
        车尾保持((byte)0x00),
        无车尾保持((byte)0x01);
        private static final Map<Byte, QFront> map = new HashMap<>();
        private final byte value;
        static {
            for (QFront type : QFront.values()) {
                map.put((byte) type.ordinal(), type);
            }
        }
        public static String get(byte b) {
            return map.get(b) == null ? "无效" : map.get(b).toString();
        }
        private QFront(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }
    }
    public enum QTC {
        辅助信息((byte)0x00),
        重要信息((byte)0x01);
        private static final Map<Byte, QTC> map = new HashMap<>();
        private final byte value;
        static {
            for (QTC type : QTC.values()) {
                map.put((byte) type.ordinal(), type);
            }
        }
        public static String get(byte b) {
            return map.get(b) == null ? "无效" : map.get(b).toString();
        }
        private QTC(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }
    }
    public enum QTD {
        无需组合((byte)0x00),
        组合((byte)0x01);
        private static final Map<Byte, QTD> map = new HashMap<>();
        private final byte value;
        static {
            for (QTD type : QTD.values()) {
                map.put((byte) type.ordinal(), type);
            }
        }
        public static String get(byte b) {
            return map.get(b) == null ? "无效" : map.get(b).toString();
        }
        private QTD(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }
    }

    public enum QTextConfirm {
        无确认需要((byte)0x00),
        继续显示直到确认((byte)0x01),
        当结束条件满足时还未确认则实施常用制动((byte)0x02);
        private static final Map<Byte, QTextConfirm> map = new HashMap<>();
        private final byte value;
        static {
            for (QTextConfirm type : QTextConfirm.values()) {
                map.put((byte) type.ordinal(), type);
            }
        }
        public static String get(byte b) {
            return map.get(b) == null ? "无效" : map.get(b).toString();
        }
        private QTextConfirm(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }
    }
    public enum QDir4 {
        反向有效((byte)0x00),
        正向有效((byte)0x01),
        双向有效((byte)0x02),
        备用((byte)0x03);
        private static final Map<Byte, QDir4> map = new HashMap<>();
        private final byte value;
        static {
            for (QDir4 type : QDir4.values()) {
                map.put((byte) type.ordinal(), type);
            }
        }
        public static String get(byte b) {
            return map.get(b) == null ? "无效" : map.get(b).toString();
        }
        private QDir4(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }
    }
    public enum QF {
        由车载设备确定头尾有效性((byte)0x00),
        头有效进入降速区段((byte)0x01);
        private static final Map<Byte, QF> map = new HashMap<>();
        private final byte value;
        static {
            for (QF type : QF.values()) {
                map.put((byte) type.ordinal(), type);
            }
        }
        public static String get(byte b) {
            return map.get(b) == null ? "无效" : map.get(b).toString();
        }
        private QF(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }
    }
    public enum QTSRS {
        终止通信会话((byte)0x00),
        建立通信会话((byte)0x01);
        private static final Map<Byte, QTSRS> map = new HashMap<>();
        private final byte value;
        static {
            for (QTSRS type : QTSRS.values()) {
                map.put((byte) type.ordinal(), type);
            }
        }
        public static String get(byte b) {
            return map.get(b) == null ? "无效" : map.get(b).toString();
        }
        private QTSRS(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }
    }
    public enum QSleep {
        忽略((byte)0x00),
        考虑((byte)0x01);
        private static final Map<Byte, QSleep> map = new HashMap<>();
        private final byte value;
        static {
            for (QSleep type : QSleep.values()) {
                map.put((byte) type.ordinal(), type);
            }
        }
        public static String get(byte b) {
            return map.get(b) == null ? "无效" : map.get(b).toString();
        }
        private QSleep(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }
    }
    public enum MLP {
        无效((byte)0x00),
        打开((byte)0x01),
        关闭((byte)0x02),
        备用((byte)0x03);
        private static final Map<Byte, MLP> map = new HashMap<>();
        private final byte value;
        static {
            for (MLP type : MLP.values()) {
                map.put((byte) type.ordinal(), type);
            }
        }
        public static String get(byte b) {
            return map.get(b) == null ? "备用" : map.get(b).toString();
        }
        private MLP(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }
    }
}