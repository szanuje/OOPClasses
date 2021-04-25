package condition;

import interfaces.ACLi;
import interfaces.Datagram;

import java.util.Objects;

public class AndCondition implements ACLi.Condition {

    ACLi.Condition condition1;
    ACLi.Condition condition2;

    AndCondition(ACLi.Condition condition1, ACLi.Condition condition2) {
        this.condition1 = Objects.requireNonNull(condition1);
        this.condition2 = Objects.requireNonNull(condition2);
    }

    @Override
    public boolean match(Datagram datagram) {
        return condition1.match(datagram) && condition2.match(datagram);
    }
}
