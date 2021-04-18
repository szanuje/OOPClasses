package model;

import java.util.ArrayList;
import java.util.List;

public class Group {

    private final Integer id;
    private final List<Network> networks;
    private final List<Host> hosts;
    private final List<Group> groups;

    public Group(Integer id) {
        this.id = id;
        this.networks = new ArrayList<>();
        this.hosts = new ArrayList<>();
        this.groups = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void addNetwork(Network network) {
        networks.add(network);
    }

    public List<Network> getNetworks() {
        return networks;
    }

    public void addHost(Host host) {
        hosts.add(host);
    }

    public List<Host> getHosts() {
        return hosts;
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public List<Group> getGroups() {
        return groups;
    }
}