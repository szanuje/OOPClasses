package model;

import interfaces.ACLi;

public class AccessControlList {

    private final Integer aclID;
    private final Integer lineNumber;
    private final ACLi.Condition condition;
    private final ACLi.Result result;

    public AccessControlList(Integer aclID, Integer lineNumber, ACLi.Condition condition, ACLi.Result result) {
        this.aclID = aclID;
        this.lineNumber = lineNumber;
        this.condition = condition;
        this.result = result;
    }

    public Integer getAclID() {
        return aclID;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public ACLi.Condition getCondition() {
        return condition;
    }

    public ACLi.Result getResult() {
        return result;
    }
}
