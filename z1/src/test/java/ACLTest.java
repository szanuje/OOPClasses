import datagram.DefaultDatagram;
import interfaces.ACLi;
import interfaces.Datagram;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

class ACLTest {

    @Test
    void test() {
        ACL acl = new ACL();

        String HOST1_NAME = "host1";
        String HOST1_ADDRESS = "100.100.100.100"; // in net1, group1
        String HOST2_NAME = "host2";
        String HOST2_ADDRESS = "100.100.100.200"; // in net1, group1
        String HOST3_NAME = "host3";
        String HOST3_ADDRESS = "100.100.200.200"; // in net2, group2
        String HOST4_NAME = "host4";
        String HOST4_ADDRESS = "200.200.200.200"; // in group3, inside group2

        acl.addHost(HOST1_ADDRESS, HOST1_NAME);
        acl.addHost(HOST2_ADDRESS, HOST2_NAME);
        acl.addHost(HOST3_ADDRESS, HOST3_NAME);
        acl.addHost(HOST4_ADDRESS, HOST4_NAME);

        String NET1_NAME = "net1";
        String NET1_ID = "100.100.100.1";
        Integer NET1_MASK = 24;
        String NET2_NAME = "net2";
        String NET2_ID = "100.100.200.1";
        Integer NET2_MASK = 24;

        acl.addNet(NET1_ID, NET1_MASK, NET1_NAME);
        acl.addNet(NET2_ID, NET2_MASK, NET2_NAME);

        Integer GROUP1_ID = acl.createNewGroup();
        Integer GROUP2_ID = acl.createNewGroup();
        Integer GROUP3_ID = acl.createNewGroup();

        acl.addNetToGroup(GROUP1_ID, NET1_NAME);
        acl.addNetToGroup(GROUP2_ID, NET2_NAME);
        acl.addHostToGroup(GROUP3_ID, HOST4_NAME);
        acl.addGroupToGroup(GROUP2_ID, GROUP3_ID);

        Datagram.Protocol CONDITION1_PROTOCOL = Datagram.Protocol.TCP;
        Datagram.Flag CONDITION1_FLAG = Datagram.Flag.FIN;
        ACLi.Condition condition1 = acl.newCondition(GROUP1_ID, GROUP3_ID, CONDITION1_PROTOCOL, CONDITION1_FLAG);

        Datagram.Protocol CONDITION2_PROTOCOL = Datagram.Protocol.UDP;
        Datagram.Flag CONDITION2_FLAG = Datagram.Flag.RST;
        ACLi.Condition condition2 = acl.newCondition(GROUP1_ID, GROUP3_ID, CONDITION2_PROTOCOL, CONDITION2_FLAG);

        Integer ACL1_ID = acl.createACL();
        acl.addConditionToACL(ACL1_ID, 5, condition1, ACLi.Result.ALLOW);
        acl.addConditionToACL(ACL1_ID, 3, condition2, ACLi.Result.DENY);

        Datagram datagram1 = new DefaultDatagram(HOST1_ADDRESS, HOST4_ADDRESS, Datagram.Protocol.TCP, Set.of(Datagram.Flag.FIN, Datagram.Flag.RST));
        ACLi.Result result = acl.test(ACL1_ID,  datagram1);
        Assertions.assertEquals(ACLi.Result.ALLOW, result);
    }
}
