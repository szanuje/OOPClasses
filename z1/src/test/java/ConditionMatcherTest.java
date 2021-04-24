import datagram.DefaultDatagram;
import interfaces.ACLi;
import interfaces.Datagram;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConditionMatcherTest {

    @org.junit.jupiter.api.Test
    void whenTestConditionGivenHostsThenMatch() {

        // given
        ACL acl = new ACL();
        acl.addHost("192.168.1.1", "host1");
        acl.addHost("192.168.1.2", "host2");
//        acl.addHost("192.168.1.3", "localhost3");
//        acl.addNet("100.200.1.1", 24, "NET1");
//        acl.addNet("100.200.2.1", 16, "NET2");

        Integer groupID1 = acl.createNewGroup();
        acl.addHostToGroup(groupID1, "host1");
//        acl.addNetToGroup(groupID1, "NET1");

        Integer groupID2 = acl.createNewGroup();
        acl.addHostToGroup(groupID2, "host2");
//        acl.addNetToGroup(groupID2, "NET2");

        Datagram datagram = new DefaultDatagram("192.168.1.1", "192.168.1.2",
                Datagram.Protocol.ANY, Set.of(Datagram.Flag.ANY));

        ACLi.Condition condition = acl.newCondition(groupID1, groupID2, Datagram.Protocol.ANY, Datagram.Flag.ANY);

        assertTrue(condition.match(datagram));
    }

    @org.junit.jupiter.api.Test
    void whenTestConditionGivenHostsThenMatch2() {

        // given
        ACL acl = new ACL();
        acl.addHost("192.168.1.1", "host1");
        acl.addHost("192.168.1.2", "host2");
//        acl.addHost("192.168.1.3", "localhost3");
//        acl.addNet("100.200.1.1", 24, "NET1");
//        acl.addNet("100.200.2.1", 16, "NET2");

        Integer groupID1 = acl.createNewGroup();
        acl.addHostToGroup(groupID1, "host1");
//        acl.addNetToGroup(groupID1, "NET1");

        Integer groupID2 = acl.createNewGroup();
        acl.addHostToGroup(groupID2, "host2");
//        acl.addNetToGroup(groupID2, "NET2");

        Datagram datagram = new DefaultDatagram("192.168.1.1", "192.168.1.2",
                Datagram.Protocol.ANY, Set.of(Datagram.Flag.ACL));

        ACLi.Condition condition = acl.newCondition(groupID1, groupID2, Datagram.Protocol.ANY, Datagram.Flag.RST);

        assertFalse(condition.match(datagram));
    }

    @org.junit.jupiter.api.Test
    void whenTestConditionGivenHostsThenMatch3() {

        // given
        ACL acl = new ACL();
        acl.addHost("192.168.1.1", "host1");
        acl.addHost("192.168.1.2", "host2");
//        acl.addHost("192.168.1.3", "localhost3");
//        acl.addNet("100.200.1.1", 24, "NET1");
//        acl.addNet("100.200.2.1", 16, "NET2");

        Integer groupID1 = acl.createNewGroup();
        acl.addHostToGroup(groupID1, "host1");
//        acl.addNetToGroup(groupID1, "NET1");

        Integer groupID2 = acl.createNewGroup();
        acl.addHostToGroup(groupID2, "host2");
//        acl.addNetToGroup(groupID2, "NET2");

        Datagram datagram = new DefaultDatagram("192.168.1.1", "192.168.1.2",
                Datagram.Protocol.ANY, Set.of(Datagram.Flag.ACL, Datagram.Flag.RST));

        ACLi.Condition condition = acl.newCondition(groupID1, groupID2, Datagram.Protocol.ANY, Datagram.Flag.RST);

        assertTrue(condition.match(datagram));
    }
}