package condition;

import interfaces.Datagram;
import keystore.KeyStoreManager;
import model.Group;
import model.Host;
import model.Network;

import java.util.ArrayList;
import java.util.List;

public class ConditionMatcher {

    private final KeyStoreManager keyStoreManager;
    private final DefaultCondition condition;
    private final Datagram datagram;

    public ConditionMatcher(KeyStoreManager keyStoreManager, DefaultCondition condition, Datagram datagram) {
        this.keyStoreManager = keyStoreManager;
        this.condition = condition;
        this.datagram = datagram;
    }

    public boolean match() {
        return matchSource() &&
                matchDestination() &&
                matchProtocol() &&
                matchFlag();
    }

    private boolean matchSource() {
        List<Group> groups = new ArrayList<>();
        condition.getSourceGroupsIds().forEach(group ->
                groups.addAll(
                        extractGroups(keyStoreManager.getGroup(group)))
        );
        return groups.stream().anyMatch(g -> groupHasAddress(g, datagram.getSourceAddress()));
    }

    private boolean matchDestination() {
        List<Group> groups = new ArrayList<>();
        condition.getDestGroupsIds().forEach(group ->
                groups.addAll(
                        extractGroups(keyStoreManager.getGroup(group)))
        );
        return groups.stream().anyMatch(g -> groupHasAddress(g, datagram.getDestinationAddress()));
    }

    private List<Group> extractGroups(Group initialGroup) {
        List<Group> list = new ArrayList<>();
        extractGroupsIds(initialGroup, list);
        return list;
    }

    private void extractGroupsIds(Group group, List<Group> groupsList) {
        groupsList.add(group);
        List<Group> foundGroups = keyStoreManager.getGroup(group.getId()).getGroups();
        if (foundGroups.isEmpty()) {
            return;
        }
        groupsList.addAll(foundGroups);
        foundGroups.forEach(g -> extractGroupsIds(g, groupsList));
    }

    private boolean groupHasAddress(Group group, String address) {
        List<Network> networksList = group.getNetworks();
        List<Host> hostsList = group.getHosts();
        return networksList.stream().anyMatch(netName -> networkHasAddress(netName, address)) ||
                hostsList.stream().anyMatch(host -> hostHasAddress(host, address));
    }

    private boolean networkHasAddress(Network network, String address) {
        return network.getHosts().stream().anyMatch(host -> hostHasAddress(host, address));
    }

    private boolean hostHasAddress(Host host, String address) {
        return host.getHostAddress().equals(address);
    }

    private boolean matchProtocol() {
        return datagram.getProtocol() == condition.getProtocol();
    }

    private boolean matchFlag() {
        return datagram.getFlags().containsAll(condition.getFlags());
    }
}