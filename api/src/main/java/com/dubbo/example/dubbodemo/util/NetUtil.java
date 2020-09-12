package com.dubbo.example.dubbodemo.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author: hongwei.quhw
 * @date: 2020-09-12 23:08
 */
public class NetUtil {
    /**
     * The constant LOCALHOST.
     */
    public static final String          LOCALHOST                        = "127.0.0.1";

    /**
     * The constant ANYHOST.
     */
    public static final String          ANYHOST                          = "0.0.0.0";

    /** symbol : */
    public static final char            COLON                            = ':';

    private static volatile InetAddress LOCAL_ADDRESS                    = null;

    private static final Pattern IP_PATTERN                       = Pattern
            .compile("\\d{1,3}(\\.\\d{1,3}){3,5}$");

    public static final String          NETWORK_INTERFACE_BINDING        = "network_interface_binding";
    public static final String          NETWORK_INTERFACE_BINDING_VALUE  = System
            .getProperty(NETWORK_INTERFACE_BINDING);
    public static final String          NETWORK_INTERFACE_DENYLIST       = "network_interface_denylist";
    public static final List<String>    NETWORK_INTERFACE_DENYLIST_VALUE = System
            .getProperty(NETWORK_INTERFACE_DENYLIST) == null ? Collections
            .emptyList()
            : Arrays
            .asList(System
                    .getProperty(
                            NETWORK_INTERFACE_DENYLIST)
                    .split(","));


    /**
     * Gets local address.
     *
     * @return loccal IP all network card
     */
    public static InetAddress getLocalAddress() {
        if (LOCAL_ADDRESS != null) {
            return LOCAL_ADDRESS;
        }
        InetAddress localAddress = getLocalAddress0();
        LOCAL_ADDRESS = localAddress;
        return localAddress;
    }



    private static boolean isValidAddress(InetAddress address) {
        if (address == null || address.isLoopbackAddress()) {
            return false;
        }
        String name = address.getHostAddress();
        return (name != null && !ANYHOST.equals(name) && !LOCALHOST.equals(name) && IP_PATTERN
                .matcher(name).matches());
    }

    private static InetAddress getLocalAddress0() {
        InetAddress localAddress = null;
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            if (interfaces != null) {
                while (interfaces.hasMoreElements()) {
                    try {
                        NetworkInterface network = interfaces.nextElement();
                        boolean useNi;
                        if (NETWORK_INTERFACE_BINDING_VALUE != null
                                && !NETWORK_INTERFACE_BINDING_VALUE.isEmpty()) {
                            if (NETWORK_INTERFACE_BINDING_VALUE.equals(network.getDisplayName())
                                    || NETWORK_INTERFACE_BINDING_VALUE.equals(network.getName())) {
                                useNi = true;
                            } else {
                                continue;
                            }
                        } else {
                            if (NETWORK_INTERFACE_DENYLIST_VALUE.contains(network.getDisplayName())
                                    || NETWORK_INTERFACE_DENYLIST_VALUE.contains(network.getName())) {
                                continue;
                            }
                            useNi = true;
                        }

                        Enumeration<InetAddress> addresses = network.getInetAddresses();
                        if (addresses != null) {
                            while (addresses.hasMoreElements()) {
                                try {
                                    InetAddress address = addresses.nextElement();
                                    if (useNi && isValidAddress(address)) {
                                        return address;
                                    }
                                } catch (Throwable e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    } catch (Throwable e) {
                       e.printStackTrace();
                    }
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return localAddress;
    }
}
