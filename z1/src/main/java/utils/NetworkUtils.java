package utils;

import model.Network;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NetworkUtils {

    private NetworkUtils() {
        //
    }

    public static boolean isAddressInNetwork(Network network, String address) {
        int networkPrefixLength = network.getNetworkPrefixLength();
        int prefix = 0;

        List<String> hostAddress = Arrays.stream(address.split("\\.")).collect(Collectors.toList());
        List<String> netAddress = Arrays.stream(network.getNetID().split("\\.")).collect(Collectors.toList());
        if (networkPrefixLength == 8) prefix = 1;
        if (networkPrefixLength == 16) prefix = 2;
        if (networkPrefixLength == 24) prefix = 3;
        for (int i = 0; i < 4; i++) {
            if (!hostAddress.get(i).equals(netAddress.get(i)) && i < prefix) {
                return false;
            }
        }
        return true;
    }
}
