import interfaces.ACLi;
import interfaces.Datagram;
import model.ACLLine;
import model.AccessControlList;

import java.util.Collection;

public class ACLVerifier {

    private ACLVerifier() {
        //
    }

    public static ACLi.Result verify(AccessControlList acl, Datagram datagram) {
        Collection<ACLLine> lines = acl.getAclLines();
        if (lines.stream().anyMatch(line -> line.getResult() == line.test(datagram))) {
            return ACLi.Result.ALLOW;
        } else {
            return ACLi.Result.DENY;
        }
    }
}
