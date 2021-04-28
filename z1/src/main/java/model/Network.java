package model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
        List<String> hostAddress = Arrays.stream(host.getHostAddress().split("\\.")).collect(Collectors.toList());
        List<String> netAddress = Arrays.stream(netID.split("\\.")).collect(Collectors.toList());
        int prefix = 0;
        if (networkPrefixLength == 8) prefix = 1;
        if (networkPrefixLength == 16) prefix = 2;
        if (networkPrefixLength == 24) prefix = 3;
        for (int i = 0; i < 4; i++) {
            if (!hostAddress.get(i).equals(netAddress.get(i))) {
                return;
            }
            if (i + 1 >= prefix) {
                hosts.add(host);
                return;
            }
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