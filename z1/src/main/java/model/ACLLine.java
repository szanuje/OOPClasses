package model;

import interfaces.ACLi;
import interfaces.Datagram;

public class ACLLine {

    private final Integer lineNumber;
    private final ACLi.Condition condition;
    private final ACLi.Result result;

    public ACLLine(Integer lineNumber, ACLi.Condition condition, ACLi.Result result) {
        this.lineNumber = lineNumber;
        this.condition = condition;
        this.result = result;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public ACLi.Result getResult() {
        return result;
    }

    public ACLi.Result test(Datagram datagram) {
        if (condition.match(datagram)) {
            return result;
        } else {
            return null;
        }
    }
}
