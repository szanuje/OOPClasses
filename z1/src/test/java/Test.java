import interfaces.ACLi;
import interfaces.Datagram;

import java.util.Set;

public class Test {

    public static void main(String[] args) {

        ACL acl = new ACL();
        acl.addHost("192.168.1.1", "localhost1");
        acl.addHost("192.168.1.2", "localhost2");
        acl.addHost("192.168.1.3", "localhost3");
        acl.addNet("100.200.1.1", 24, "NET1");
        acl.addNet("100.200.2.1", 16, "NET2");

        Integer groupID1 = acl.createNewGroup();
        acl.addHostToGroup(groupID1, "localhost1");
        acl.addNetToGroup(groupID1, "NET1");

        Integer groupID2 = acl.createNewGroup();
        acl.addHostToGroup(groupID2, "localhost2");
        acl.addNetToGroup(groupID2, "NET2");

        Datagram datagram = new DefaultDatagram("192.168.1.1", "192.168.1.2",
                Datagram.Protocol.ANY, Set.of(Datagram.Flag.ANY));

        ACLi.Condition condition = acl.newCondition(groupID1, groupID2, Datagram.Protocol.ANY, Datagram.Flag.ANY);

        System.out.println(condition.match(datagram));
    }
}