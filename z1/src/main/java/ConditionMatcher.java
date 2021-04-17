import interfaces.Datagram;
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
    List<Group> groups = extractGroups(keyStoreManager.getGroup(condition.getSourceGroupId()).get());
    System.out.println(groups);
    return groups.stream().anyMatch(g -> groupHasAddress(g, datagram.getSourceAddress()));
  }

  private boolean matchDestination() {
    List<Group> groups = extractGroups(keyStoreManager.getGroup(condition.getDestGroupId()).get());
    return groups.stream().anyMatch(g -> groupHasAddress(g, datagram.getDestinationAddress()));
  }

  private List<Group> extractGroups(Group initialGroup) {
    List<Group> list = new ArrayList<>();
    extractGroupsIds(initialGroup, list);
    return list;
  }

  private List<Group> extractGroupsIds(Group group, List<Group> groupsList) {
    groupsList.add(group);
    List<Group> foundGroups = keyStoreManager.getGroup(group.getId()).get().getGroups();
    groupsList.addAll(foundGroups);
    foundGroups.forEach(g -> extractGroupsIds(g, groupsList));
    return groupsList;
  }

  private boolean groupHasAddress(Group group, String address) {
    List<Network> networkNamesList = keyStoreManager.getGroup(group.getId()).get().getNetworks();
    return networkNamesList.stream()
            .anyMatch(netName -> networkHasAddress(netName, address));
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
    return datagram.getFlags().contains(condition.getFlag());
  }
}