import datagram.DefaultDatagram;
import interfaces.ACLi;
import interfaces.Datagram;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
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

    @Test
    void test2() {
        ACL acl = new ACL();

        String HOST1_NAME = "ALBERT";
        String HOST1_ADDRESS = "192.168.1.1"; // in net1, group1
        String HOST2_NAME = "HAKER";
        String HOST2_ADDRESS = "15.15.15.15"; // in net1, group1
        String HOST3_NAME = "Krzysiek";
        String HOST3_ADDRESS = "10.10.10.5"; // in net2, group2


        acl.addHost(HOST1_ADDRESS, HOST1_NAME);
        acl.addHost(HOST2_ADDRESS, HOST2_NAME);
        acl.addHost(HOST3_ADDRESS, HOST3_NAME);

        String NET1_NAME = "IT";
        String NET1_ID = "192.168.5.0";
        Integer NET1_MASK = 24;
        String NET2_NAME = "HR";
        String NET2_ID = "192.168.4.0";
        Integer NET2_MASK = 24;
        String NET3_NAME = "MGMT";
        String NET3_ID = "15.14.0.0";
        Integer NET3_MASK = 16;

        acl.addNet(NET1_ID, NET1_MASK, NET1_NAME);
        acl.addNet(NET2_ID, NET2_MASK, NET2_NAME);
        acl.addNet(NET3_ID, NET3_MASK, NET3_NAME);

        Integer GROUP1_ID = acl.createNewGroup();
        Integer GROUP2_ID = acl.createNewGroup();
        Integer GROUP3_ID = acl.createNewGroup();

        acl.addNetToGroup(GROUP1_ID, NET1_NAME);
        acl.addHostToGroup(GROUP1_ID, HOST1_NAME);
        acl.addHostToGroup(GROUP1_ID, HOST3_NAME);

        acl.addNetToGroup(GROUP2_ID, NET2_NAME);
        acl.addHostToGroup(GROUP2_ID, HOST2_NAME);

        acl.addNetToGroup(GROUP3_ID, NET3_NAME);
        acl.addGroupToGroup(GROUP3_ID, GROUP2_ID);

        Datagram.Protocol CONDITION_PROTOCOL = Datagram.Protocol.TCP;
        Datagram.Flag CONDITION_FLAG = Datagram.Flag.RST;

        Integer ACL1_ID = acl.createACL();
        ACLi.Condition condition1 = acl.newCondition(GROUP1_ID, GROUP2_ID, CONDITION_PROTOCOL, CONDITION_FLAG);
        acl.addConditionToACL(ACL1_ID, 1, condition1, ACLi.Result.DENY);
        ACLi.Condition condition2 = acl.newCondition(GROUP1_ID, GROUP3_ID, CONDITION_PROTOCOL, CONDITION_FLAG);
        acl.addConditionToACL(ACL1_ID, 2, condition2, ACLi.Result.DENY);
        ACLi.Condition condition3 = acl.newCondition(GROUP1_ID, GROUP1_ID, CONDITION_PROTOCOL, CONDITION_FLAG);
        acl.addConditionToACL(ACL1_ID, 3, condition3, ACLi.Result.ALLOW);
        ACLi.Condition condition4 = acl.newCondition(GROUP2_ID, GROUP3_ID, CONDITION_PROTOCOL, CONDITION_FLAG);
        acl.addConditionToACL(ACL1_ID, 4, condition4, ACLi.Result.ALLOW);
        ACLi.Condition condition5 = acl.newCondition(GROUP3_ID, GROUP3_ID, CONDITION_PROTOCOL, CONDITION_FLAG);
        acl.addConditionToACL(ACL1_ID, 5, condition5, ACLi.Result.ALLOW);
        ACLi.Condition condition6 = acl.newCondition(GROUP3_ID, GROUP2_ID, CONDITION_PROTOCOL, CONDITION_FLAG);
        acl.addConditionToACL(ACL1_ID, 6, condition6, ACLi.Result.ALLOW);
        ACLi.Condition condition7 = acl.newCondition(GROUP3_ID, GROUP1_ID, CONDITION_PROTOCOL, CONDITION_FLAG);
        acl.addConditionToACL(ACL1_ID, 7, condition7, ACLi.Result.ALLOW);


        Set<Datagram.Flag> flags = new HashSet<>();
        flags.add(Datagram.Flag.RST);
        Datagram.Protocol protocol = Datagram.Protocol.TCP;

        Datagram datagram1 = new DefaultDatagram("12.12.12.1", "192.168.5.2", protocol, flags);
        ACLi.Result result = acl.test(ACL1_ID,  datagram1);
        Assertions.assertEquals(ACLi.Result.DENY, result);

        Datagram datagram2 = new DefaultDatagram("192.168.5.1", "192.168.5.2", protocol, flags);
        ACLi.Result result2 = acl.test(ACL1_ID, datagram2);
        Assertions.assertEquals(ACLi.Result.ALLOW, result2);

        Datagram datagram3 = new DefaultDatagram("192.168.5.1", "192.168.5.2", protocol, flags);
        ACLi.Result result3 = acl.test(ACL1_ID, datagram3);
        Assertions.assertEquals(ACLi.Result.ALLOW, result3);

        Datagram datagram4 = new DefaultDatagram("192.168.5.1", "192.168.4.1", protocol, flags);
        ACLi.Result result4 = acl.test(ACL1_ID, datagram4);
        Assertions.assertEquals(ACLi.Result.DENY, result4);

        Datagram datagram5 = new DefaultDatagram("192.168.5.1", "15.14.1.1", protocol, flags);
        ACLi.Result result5 = acl.test(ACL1_ID, datagram5);
        Assertions.assertEquals(ACLi.Result.DENY, result5);

        Datagram datagram6 = new DefaultDatagram("192.168.4.1", "192.168.5.2", protocol, flags);
        ACLi.Result result6 = acl.test(ACL1_ID, datagram6);
        Assertions.assertEquals(ACLi.Result.ALLOW, result6);

        Datagram datagram7 = new DefaultDatagram("192.168.4.1", "15.14.1.1", protocol, flags);
        ACLi.Result result7 = acl.test(ACL1_ID, datagram7);
        Assertions.assertEquals(ACLi.Result.ALLOW, result7);

        Datagram datagram8 = new DefaultDatagram("15.14.1.1", "192.168.4.1", protocol, flags);
        ACLi.Result result8 = acl.test(ACL1_ID, datagram8);
        Assertions.assertEquals(ACLi.Result.ALLOW, result8);

        Datagram datagram9 = new DefaultDatagram("15.14.1.5", "15.14.1.1", protocol, flags);
        ACLi.Result result9 = acl.test(ACL1_ID, datagram9);
        Assertions.assertEquals(ACLi.Result.ALLOW, result9);

    }
}
