package model;

import java.util.HashSet;
import java.util.Set;

public class Network implements NetworkObserver {

    private final String netID;
    private final Integer networkPrefixLength;
    private final String netName;
    private final Set<Host> hosts;

    public Network(String netID, Integer networkPrefixLength, String netName) {
        this.netID = netID;
        this.networkPrefixLength = networkPrefixLength;
        this.netName = netName;
        this.hosts = new HashSet<>();
    }

    public String getNetID() {
        return netID;
    }

    public Integer getNetworkPrefixLength() {
        return networkPrefixLength;
    }

    public String getNetName() {
        return netName;
    }

    @Override
    public void update(Host host) {
        String hostAddress = host.getHostAddress();
        int octet = 0;
        for (int i = 0; i < hostAddress.length(); i++) {
            if(i % 4 == 0) {
                octet++;
                if (octet >= networkPrefixLength) {
                    hosts.add(host);
                    return;
                }
            }
            if (hostAddress.charAt(i) != netID.charAt(i)) {
                return;
            }
        }
    }

    public Set<Host> getHosts() {
        return hosts;
    }
}