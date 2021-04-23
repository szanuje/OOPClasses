import condition.ConditionFactory;
import interfaces.ACLi;
import interfaces.Datagram;
import keystore.KeyStoreManager;
import model.AccessControlList;
import model.Group;
import model.Host;
import model.Network;
import utils.GroupIDGenerator;

public class ACL implements ACLi {

    private final KeyStoreManager keyStoreManager;
    private final ConditionFactory conditionFactory;

    public ACL() {
        this.keyStoreManager = new KeyStoreManager();
        this.conditionFactory = new ConditionFactory();
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
        keyStoreManager.getGroup(groupID).addHost(keyStoreManager.getHost(hostName));
    }

    @Override
    public void addNetToGroup(Integer groupID, String netName) {
        keyStoreManager.getGroup(groupID).addNetwork(keyStoreManager.getNet(netName));
    }

    @Override
    public void addGroupToGroup(Integer groupID, Integer subGroupID) {
        keyStoreManager.getGroup(groupID).addGroup(keyStoreManager.getGroup(groupID));
    }

    @Override
    public Condition newCondition(Integer sourceGroupID, Integer destinationGroupID,
                                  Datagram.Protocol protocol, Datagram.Flag flag) {
        return conditionFactory.createDefault(sourceGroupID, destinationGroupID, protocol, flag, keyStoreManager);
    }

    @Override
    public Condition and(Condition c1, Condition c2) {
        return conditionFactory.createAnd(c1, c2);
    }

    @Override
    public Condition or(Condition c1, Condition c2) {
        return conditionFactory.createOr(c1, c2);
    }

    @Override
    public Condition not(Condition c) {
        return conditionFactory.createNot(c);
    }

    @Override
    public Integer createACL() {
        return null;
    }

    @Override
    public void addConditionToACL(Integer aclID, Integer lineNumber, Condition condition, Result result) {
        keyStoreManager.addAccessControlList(aclID, new AccessControlList(aclID, lineNumber, condition, result));
    }

    @Override
    public Result test(Integer aclID, Datagram datagram) {
        return null;
    }

}