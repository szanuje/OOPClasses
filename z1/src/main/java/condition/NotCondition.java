package condition;

import interfaces.ACLi;
import interfaces.Datagram;

import java.util.Objects;

public class NotCondition implements ACLi.Condition {

    ACLi.Condition condition;

    NotCondition(ACLi.Condition condition) {
        this.condition = Objects.requireNonNull(condition);
    }

    @Override
    public boolean match(Datagram datagram) {
        return !condition.match(datagram);
    }
}
