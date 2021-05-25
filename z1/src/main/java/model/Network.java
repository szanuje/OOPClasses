package model;

import java.util.Objects;

public class Network {

    private final String netID;
    private final Integer networkPrefixLength;
    private final String netName;

    public Network(String netID, Integer networkPrefixLength, String netName) {
        this.netID = netID;
        this.networkPrefixLength = networkPrefixLength;
        this.netName = netName;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Network network = (Network) o;
        return Objects.equals(netID, network.netID) && Objects.equals(networkPrefixLength, network.networkPrefixLength) &&
                Objects.equals(netName, network.netName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(netID, networkPrefixLength, netName);
    }
}