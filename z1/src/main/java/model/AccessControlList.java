package model;

import interfaces.ACLi;

import java.util.Objects;

public class AccessControlList {

    private final Integer aclID;
    private Integer lineNumber;
    private ACLi.Condition condition;
    private ACLi.Result result;

    public AccessControlList(Integer aclID) {
        this.aclID = aclID;
    }

    public Integer getAclID() {
        return aclID;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public ACLi.Condition getCondition() {
        return condition;
    }

    public void setCondition(ACLi.Condition condition) {
        this.condition = condition;
    }

    public ACLi.Result getResult() {
        return result;
    }

    public void setResult(ACLi.Result result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessControlList that = (AccessControlList) o;
        return Objects.equals(aclID, that.aclID) && Objects.equals(lineNumber, that.lineNumber) && Objects.equals(condition, that.condition) && result == that.result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(aclID, lineNumber, condition, result);
    }
}
