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
        for (ACLLine line : lines) {
            if (line.getResult() == line.test(datagram)) {
                return line.getResult();
            }
        }
        return ACLi.Result.DENY;
    }
}
