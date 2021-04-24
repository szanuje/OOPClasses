package model;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class AccessControlList {

    private final Integer aclID;
    private final Set<ACLLine> aclLines;

    public AccessControlList(Integer aclID) {
        this.aclID = aclID;
        this.aclLines = new TreeSet<>(Comparator.comparing(ACLLine::getLineNumber));
    }

    public Integer getAclID() {
        return aclID;
    }

    public Set<ACLLine> getAclLines() {
        return new HashSet<>(aclLines);
    }

    public void addLine(ACLLine line) {
        aclLines.add(line);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessControlList that = (AccessControlList) o;
        return Objects.equals(aclID, that.aclID) && Objects.equals(aclLines, that.aclLines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aclID, aclLines);
    }
}
