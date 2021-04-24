import datagram.DefaultDatagram;
import interfaces.ACLi;
import interfaces.Datagram;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConditionMatcherTest {

    @Test
    void whenTestConditionGivenHostsThenMatch() {

        // given
        ACL acl = new ACL();

        // when
        acl.addHost("192.168.1.1", "host1");
        acl.addHost("192.168.1.2", "host2");

        Integer groupID1 = acl.createNewGroup();
        acl.addHostToGroup(groupID1, "host1");

        Integer groupID2 = acl.createNewGroup();
        acl.addHostToGroup(groupID2, "host2");

        Datagram datagram = new DefaultDatagram(
                "192.168.1.1",
                "192.168.1.2",
                Datagram.Protocol.ANY,
                Set.of(Datagram.Flag.ANY)
        );

        ACLi.Condition condition = acl.newCondition(groupID1, groupID2, Datagram.Protocol.ANY, Datagram.Flag.ANY);

        // then
        assertTrue(condition.match(datagram));
    }

    @Test
    void whenTestConditionGivenHostsThenMatch2() {

        // given
        ACL acl = new ACL();

        // when
        acl.addHost("192.168.1.1", "host1");
        acl.addHost("192.168.1.2", "host2");

        Integer groupID1 = acl.createNewGroup();
        acl.addHostToGroup(groupID1, "host1");

        Integer groupID2 = acl.createNewGroup();
        acl.addHostToGroup(groupID2, "host2");

        Datagram datagram = new DefaultDatagram(
                "192.168.1.1",
                "192.168.1.2",
                Datagram.Protocol.ANY,
                Set.of(Datagram.Flag.ACL)
        );

        ACLi.Condition condition = acl.newCondition(groupID1, groupID2, Datagram.Protocol.ANY, Datagram.Flag.RST);

        // then
        assertFalse(condition.match(datagram));
    }

    @Test
    void whenTestConditionGivenHostsThenMatch3() {

        // given
        ACL acl = new ACL();

        // when
        acl.addHost("192.168.1.1", "host1");
        acl.addHost("192.168.1.2", "host2");

        Integer groupID1 = acl.createNewGroup();
        acl.addHostToGroup(groupID1, "host1");

        Integer groupID2 = acl.createNewGroup();
        acl.addHostToGroup(groupID2, "host2");

        Datagram datagram = new DefaultDatagram(
                "192.168.1.1",
                "192.168.1.2",
                Datagram.Protocol.ANY,
                Set.of(Datagram.Flag.ACL, Datagram.Flag.RST)
        );

        ACLi.Condition condition = acl.newCondition(groupID1, groupID2, Datagram.Protocol.ANY, Datagram.Flag.RST);

        // then
        assertTrue(condition.match(datagram));
    }

    @Test
    void whenTestConditionGivenHostsThenMatch4() {

        // given
        ACL acl = new ACL();

        // when
        acl.addHost("192.168.1.1", "host1");
        acl.addHost("195.165.1.1", "host2");

        Integer groupID1 = acl.createNewGroup();
        acl.addNet("192.168.1.0", 24, "NET1");
        acl.addNetToGroup(groupID1, "NET1");

        Integer groupID2 = acl.createNewGroup();
        acl.addNet("195.168.1.0", 8, "NET2");
        acl.addNetToGroup(groupID2, "NET2");

        Datagram datagram = new DefaultDatagram(
                "192.168.1.1",
                "195.165.1.1",
                Datagram.Protocol.ANY,
                Set.of(Datagram.Flag.ACL, Datagram.Flag.RST)
        );

        ACLi.Condition condition = acl.newCondition(groupID1, groupID2, Datagram.Protocol.ANY, Datagram.Flag.ANY);

        // then
        assertTrue(condition.match(datagram));
    }
}