package com.example.framework.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;


import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * 网络信息
 */
public class Network {
    /**
     * 无网络
     */
    public static final int NETWORK_INVALID = -1;
    /**
     * 2G网络
     */
    public static final int NETWORK_2G = 0;
    /**
     * wap网络
     */
    public static final int NETWORK_WAP = 1;
    /**
     * wifi网络
     */
    public static final int NETWORK_WIFI = 2;
    /**
     * 3G网络
     */
    public static final int NETWORK_3G = 3;
    /**
     * 4G网络
     */
    public static final int NETWORK_4G = 4;

    private static final int[] NETWORK_MATCH_TABLE = {NETWORK_2G // NETWORK_TYPE_UNKNOWN
            , NETWORK_2G // NETWORK_TYPE_GPRS
            , NETWORK_2G // NETWORK_TYPE_EDGE
            , NETWORK_3G // NETWORK_TYPE_UMTS
            , NETWORK_2G // NETWORK_TYPE_CDMA
            , NETWORK_3G // NETWORK_TYPE_EVDO_O
            , NETWORK_3G // NETWORK_TYPE_EVDO_A
            , NETWORK_2G // NETWORK_TYPE_1xRTT
            , NETWORK_3G // NETWORK_TYPE_HSDPA
            , NETWORK_3G // NETWORK_TYPE_HSUPA
            , NETWORK_3G // NETWORK_TYPE_HSPA
            , NETWORK_2G // NETWORK_TYPE_IDEN
            , NETWORK_3G // NETWORK_TYPE_EVDO_B
            , NETWORK_4G // NETWORK_TYPE_LTE
            , NETWORK_3G // NETWORK_TYPE_EHRPD
            , NETWORK_3G // NETWORK_TYPE_HSPAP
    };

    private static String mIMEI = "";
    private static String mIMSI = "";
    private static String mWifiMac = "";

    private static int mDefaultNetworkType;
    private static ConnectivityManager mConnectManager;

    /**
     * 初始化默认网络参数
     *
     * @param context 上下文环境
     */
    public static void init(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        mIMEI = telephonyManager.getDeviceId();
        if (mIMEI == null) {
            mIMEI = "";
        }

        mIMSI = telephonyManager.getSubscriberId();
        if (mIMSI == null) {
            mIMSI = "";
        }

        try {
            mWifiMac = ((WifiManager) context.getSystemService(Context.WIFI_SERVICE)).getConnectionInfo().getMacAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mWifiMac == null) {
            mWifiMac = "";
        }

        mDefaultNetworkType = NETWORK_MATCH_TABLE[telephonyNetworkType(context)];
        mConnectManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }


    /**
     * 获取IMEI串号
     *
     * @return IMEI串号。<b>有可能为空值</b>
     */
    public static String imei() {
        return mIMEI;
    }

    /**
     * 获取IMSI移动用户识别码
     *
     * @return IMSI移动用户识别码。<b>有可能为空值</b>
     */
    public static String imsi() {
        return mIMSI;
    }

    /**
     * 获取Wifi Mac地址
     *
     * @return Wifi Mac地址
     */
    public static String wifiMac() {
        return mWifiMac;
    }

    /**
     * 获取网络类型
     *
     * @return 网络类型
     */
    public static int type() {
        if (mConnectManager == null) {
            //当还未来得及初始化时，另一线程请求网络时通用参数中取此值时先运行到这儿，那么如不做处理将崩溃
            return NETWORK_WAP;
        }

        int networkType = mDefaultNetworkType;
        NetworkInfo networkInfo = mConnectManager.getActiveNetworkInfo();
        if (!networkConnected(networkInfo)) {
            networkType = NETWORK_INVALID;
        } else if (isWifiNetwork(networkInfo)) {
            networkType = NETWORK_WIFI;
        } else if (isWapNetwork(networkInfo)) {
            networkType = NETWORK_WAP;
        }

        return networkType;
    }

    /**
     * 是否存在有效的网络连接.
     *
     * @return 存在有效的网络连接返回true，否则返回false
     */
    public static boolean isNetWorkAvailable() {
        return mConnectManager != null && networkConnected(mConnectManager.getActiveNetworkInfo());
    }

    /**
     * 获取本机IPv4地址
     *
     * @return 本机IPv4地址
     */
    public static String ipv4() {
        return ipAddress(true);
    }

    /**
     * 获取本机IPv6地址
     *
     * @return 本机IPv6地址
     */
    public static String ipv6() {
        return ipAddress(false);
    }

    private static String ipAddress(boolean useIPv4) {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface netInterface = en.nextElement();
                for (Enumeration<InetAddress> iNetEnum = netInterface.getInetAddresses(); iNetEnum.hasMoreElements(); ) {
                    InetAddress inetAddress = iNetEnum.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        if (useIPv4 && inetAddress instanceof Inet4Address) {
                            return inetAddress.getHostAddress();
                        } else if (!useIPv4 && inetAddress instanceof Inet6Address) {
                            return inetAddress.getHostAddress();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 直接从系统函数里得到的network type
     *
     * @param context context
     * @return net type
     */
    private static int telephonyNetworkType(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        int networkType = telephonyManager.getNetworkType();
        if (networkType < 0 || networkType >= NETWORK_MATCH_TABLE.length) {
            networkType = TelephonyManager.NETWORK_TYPE_UNKNOWN;
        }
        return networkType;
    }

    private static boolean networkConnected(NetworkInfo networkInfo) {
        return networkInfo != null && networkInfo.isConnected();
    }

    private static boolean isMobileNetwork(NetworkInfo networkInfo) {
        return networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
    }

    @SuppressWarnings("deprecation")
    private static boolean isWapNetwork(NetworkInfo networkInfo) {
        return isMobileNetwork(networkInfo) && !TextUtils.isEmpty(android.net.Proxy.getDefaultHost());
    }

    private static boolean isWifiNetwork(NetworkInfo networkInfo) {
        return networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }
    /**
     * 通过WIFI获取手机ip地址
     *
     * @param context
     *            上下文对象
     * @return
     */
    public static String getLocalIpAddressByWIFI(Context context) {
        // 获取wifi服务
        WifiManager wifiManager = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        // 判断wifi是否开启
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        String ip = intToIp(ipAddress);
        return ip;
    }

    private static String intToIp(int i) {
        return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF)
                + "." + (i >> 24 & 0xFF);
    }

}