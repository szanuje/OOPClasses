package model;

import utils.NetworkUtils;

import java.util.HashSet;
import java.util.Objects;
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
        if (NetworkUtils.isHostInNetwork(this, host)) {
            hosts.add(host);
        }
    }

    public Set<Host> getHosts() {
        return hosts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Network network = (Network) o;
        return Objects.equals(netID, network.netID) && Objects.equals(networkPrefixLength, network.networkPrefixLength) &&
                Objects.equals(netName, network.netName) && Objects.equals(hosts, network.hosts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(netID, networkPrefixLength, netName, hosts);
    }
}