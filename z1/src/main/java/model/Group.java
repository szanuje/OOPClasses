package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        return new ArrayList<>(networks);
    }

    public void addHost(Host host) {
        hosts.add(host);
    }

    public List<Host> getHosts() {
        return new ArrayList<>(hosts);
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public List<Group> getGroups() {
        return new ArrayList<>(groups);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(id, group.id) && Objects.equals(networks, group.networks) && Objects.equals(hosts, group.hosts) && Objects.equals(groups, group.groups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, networks, hosts, groups);
    }
}