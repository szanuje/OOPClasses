package condition;

import interfaces.ACLi;
import interfaces.Datagram;
import keystore.KeyStoreManager;

public class ConditionFactory {

    public ACLi.Condition createAnd(ACLi.Condition condition1, ACLi.Condition condition2) {
        return new AndCondition(condition1, condition2);
    }

    public ACLi.Condition createOr(ACLi.Condition condition1, ACLi.Condition condition2) {
        return new OrCondition(condition1, condition2);
    }

    public ACLi.Condition createNot(ACLi.Condition condition) {
        return new NotCondition(condition);
    }

    public ACLi.Condition createDefault(Integer sourceGroupID, Integer destinationGroupID,
                                        Datagram.Protocol protocol, Datagram.Flag flag, KeyStoreManager keyStoreManager) {
        return new DefaultCondition(sourceGroupID, destinationGroupID, protocol, flag, keyStoreManager);
    }
}
