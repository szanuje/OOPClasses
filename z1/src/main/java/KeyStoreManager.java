import keystore.GroupKeyStore;
import keystore.HostKeyStore;
import keystore.KeyStore;
import keystore.NetworkKeyStore;
import model.Group;
import model.Host;
import model.Network;

import java.util.Optional;

public class KeyStoreManager {

  private final KeyStore<String, Network> networkKeyStore;
  private final KeyStore<String, Host> hostKeyStore;
  private final KeyStore<Integer, Group> groupKeyStore;

  public KeyStoreManager() {
    networkKeyStore = new NetworkKeyStore();
    hostKeyStore = new HostKeyStore();
    groupKeyStore = new GroupKeyStore();
  }

  public void addHost(String hostName, Host host) {
    hostKeyStore.insert(hostName, host);
  }

  public Optional<Host> getHost(String hostName) {
    return hostKeyStore.get(hostName);
  }

  public void addNet(String netName, Network net) {
    networkKeyStore.insert(netName, net);
  }

  public Optional<Network> getNet(String netName) {
    return networkKeyStore.get(netName);
  }

  public void addGroup(Integer groupID, Group group) {
    groupKeyStore.insert(groupID, group);
  }

  public Optional<Group> getGroup(Integer groupID) {
    return groupKeyStore.get(groupID);
  }

}