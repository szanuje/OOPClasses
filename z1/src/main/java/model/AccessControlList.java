package model;

import interfaces.ACLi;

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
}
