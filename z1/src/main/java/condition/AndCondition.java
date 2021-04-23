package condition;

import interfaces.ACLi;
import interfaces.Datagram;

public class AndCondition implements ACLi.Condition {

    ACLi.Condition condition1;
    ACLi.Condition condition2;

    public AndCondition(ACLi.Condition condition1, ACLi.Condition condition2) {
        this.condition1 = condition1;
        this.condition2 = condition2;
    }

    @Override
    public boolean match(Datagram datagram) {
        return condition1.match(datagram) && condition2.match(datagram);
    }
}
