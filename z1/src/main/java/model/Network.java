package model;

import java.util.HashSet;
import java.util.Set;

public class Network {

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

    public void addHost(Host host) {
        hosts.add(host);
    }

    public Set<Host> getHosts() {
        return hosts;
    }
}