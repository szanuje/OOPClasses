//import model.Host;
//import model.Network;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//class NetworkTest {
//
//    @Test
//    void whenAddHostGivenNetworkThenObserverAddHost() {
//
//        // given
//        String NET_ID = "192.168.1.0";
//        String NET_NAME = "NET1";
//
//        ACL acl = new ACL();
//        Host host1 = new Host("192.168.1.1", "host1");
//        Host host2 = new Host("192.168.1.2", "host2");
//        Host host3 = new Host("192.168.1.3", "host3");
//        Host host4 = new Host("192.168.1.4", "host4");
//        List<Host> hosts = List.of(host1, host2, host3, host4);
//
//        // when
//        acl.addHost(host1.getHostAddress(), host1.getHostName());
//        acl.addHost(host2.getHostAddress(), host2.getHostName());
//        acl.addHost(host3.getHostAddress(), host3.getHostName());
//        acl.addHost(host4.getHostAddress(), host4.getHostName());
//
//        acl.addNet(NET_ID, 24, NET_NAME);
//        Network net = acl.getKeyStoreManager().getNet(NET_NAME);
//
//        // then
//        Assertions.assertTrue(net.getHosts().containsAll(hosts));
//    }
//
//    @Test
//    void whenAddHostGivenNetworkThenObserverAddHost2() {
//
//        // given
//        String NET_ID = "192.168.1.0";
//        String NET_NAME = "NET1";
//        String NET_ID_2 = "192.162.22.0";
//        String NET_NAME_2 = "NET2";
//
//        ACL acl = new ACL();
//        Host host1 = new Host("192.168.1.1", "host1");
//        Host host2 = new Host("192.168.1.2", "host2");
//        Host host3 = new Host("192.168.1.3", "host3");
//        Host host4 = new Host("192.168.1.4", "host4");
//        List<Host> hosts = List.of(host1, host2, host3, host4);
//
//        // when
//        acl.addHost(host1.getHostAddress(), host1.getHostName());
//        acl.addHost(host2.getHostAddress(), host2.getHostName());
//        acl.addHost(host3.getHostAddress(), host3.getHostName());
//        acl.addHost(host4.getHostAddress(), host4.getHostName());
//
//        acl.addNet(NET_ID, 24, NET_NAME);
//        Network net = acl.getKeyStoreManager().getNet(NET_NAME);
//        acl.addNet(NET_ID_2, 8, NET_NAME_2);
//        Network net2 = acl.getKeyStoreManager().getNet(NET_NAME_2);
//
//        // then
//        Assertions.assertTrue(net.getHosts().containsAll(hosts));
//        Assertions.assertTrue(net2.getHosts().containsAll(hosts));
//    }
//
//    @Test
//    void whenAddHostGivenNetworkThenObserverAddHost3() {
//
//        // given
//        String NET_ID = "192.168.1.0";
//        String NET_NAME = "NET1";
//        String NET_ID_2 = "192.162.22.0";
//        String NET_NAME_2 = "NET2";
//
//        ACL acl = new ACL();
//        acl.addNet(NET_ID, 24, NET_NAME);
//        Network net = acl.getKeyStoreManager().getNet(NET_NAME);
//        acl.addNet(NET_ID_2, 8, NET_NAME_2);
//        Network net2 = acl.getKeyStoreManager().getNet(NET_NAME_2);
//
//        // when
//        Host host1 = new Host("192.168.1.1", "host1");
//        Host host2 = new Host("192.168.1.2", "host2");
//        Host host3 = new Host("192.168.1.3", "host3");
//        Host host4 = new Host("192.168.1.4", "host4");
//        List<Host> hosts = List.of(host1, host2, host3, host4);
//
//        acl.addHost(host1.getHostAddress(), host1.getHostName());
//        acl.addHost(host2.getHostAddress(), host2.getHostName());
//        acl.addHost(host3.getHostAddress(), host3.getHostName());
//        acl.addHost(host4.getHostAddress(), host4.getHostName());
//
//        // then
//        Assertions.assertTrue(net.getHosts().containsAll(hosts));
//        Assertions.assertTrue(net2.getHosts().containsAll(hosts));
//    }
//}
