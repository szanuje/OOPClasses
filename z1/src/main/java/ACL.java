import interfaces.ACLi;
import interfaces.Datagram;
import model.Group;
import model.Host;
import model.Network;
import utils.GroupIDGenerator;

public class ACL implements ACLi {

  private final KeyStoreManager keyStoreManager;

  public ACL() {
    this.keyStoreManager = new KeyStoreManager();
  }

  @Override
  public void addHost(String hostAddress, String hostName) {
    keyStoreManager.addHost(hostName, new Host(hostAddress, hostName));
  }

  @Override
  public void addNet(String netID, Integer networkPrefixLength, String netName) {
    keyStoreManager.addNet(netName, new Network(netID, networkPrefixLength, netName));
  }

  @Override
  public Integer createNewGroup() {
    Integer groupID = GroupIDGenerator.getNewGroupID();
    keyStoreManager.addGroup(groupID, new Group(groupID));
    return groupID;
  }

  @Override
  public void addHostToGroup(Integer groupID, String hostName) {
    keyStoreManager.getGroup(groupID).ifPresent(group -> group.addHost(keyStoreManager.getHost(hostName).get()));
  }

  @Override
  public void addNetToGroup(Integer groupID, String netName) {
    keyStoreManager.getGroup(groupID).ifPresent(group -> group.addNetwork(keyStoreManager.getNet(netName).get()));
  }

  @Override
  public void addGroupToGroup(Integer groupID, Integer subGroupID) {
    keyStoreManager.getGroup(groupID).ifPresent(group -> group.addGroup(keyStoreManager.getGroup(groupID).get()));
  }

  @Override
  public Condition newCondition(Integer sourceGroupID, Integer destinationGroupID,
                                Datagram.Protocol protocol, Datagram.Flag flag) {
    return new DefaultCondition(sourceGroupID, destinationGroupID, protocol, flag, keyStoreManager);
  }

  @Override
  public Condition and(Condition c1, Condition c2) {
    return null;
  }

  @Override
  public Condition or(Condition c1, Condition c2) {
    return null;
  }

  @Override
  public Condition not(Condition c) {
    return null;
  }

  @Override
  public Integer createACL() {
    return null;
  }

  @Override
  public void addConditionToACL(Integer aclID, Integer lineNumber, Condition condition, Result result) {

  }

  @Override
  public Result test(Integer aclID, Datagram datagram) {
    return null;
  }

}