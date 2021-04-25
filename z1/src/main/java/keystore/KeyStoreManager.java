package keystore;

import model.AccessControlList;
import model.Group;
import model.Host;
import model.Network;

public class KeyStoreManager {

    private final KeyStore<String, Network> networkKeyStore;
    private final KeyStore<String, Host> hostKeyStore;
    private final KeyStore<Integer, Group> groupKeyStore;
    private final KeyStore<Integer, AccessControlList> accessControlListKeyStore;

    public KeyStoreManager() {
        networkKeyStore = new NetworkKeyStore();
        hostKeyStore = new HostKeyStore();
        groupKeyStore = new GroupKeyStore();
        accessControlListKeyStore = new AccessControlListKeyStore();
    }

    public void addHost(String hostName, Host host) {
        hostKeyStore.insert(hostName, host);
        notifyNetworkObservers(host);
    }

    public Host getHost(String hostName) {
        return hostKeyStore.get(hostName);
    }

    public void addNet(String netName, Network net) {
        networkKeyStore.insert(netName, net);
        hostKeyStore.getAll().values().forEach(net::update);
    }

    public Network getNet(String netName) {
        return networkKeyStore.get(netName);
    }

    public void addGroup(Integer groupID, Group group) {
        groupKeyStore.insert(groupID, group);
    }

    public Group getGroup(Integer groupID) {
        return groupKeyStore.get(groupID);
    }

    public void addAccessControlList(Integer aclID, AccessControlList accessControlList) {
        accessControlListKeyStore.insert(aclID, accessControlList);
    }

    public AccessControlList getAccessControlList(Integer aclID) {
        return accessControlListKeyStore.get(aclID);
    }

    public void notifyNetworkObservers(Host host) {
        networkKeyStore.getAll().values().forEach(net -> net.update(host));
    }
}
