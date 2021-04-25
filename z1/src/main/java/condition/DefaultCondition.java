package condition;

import interfaces.ACLi;
import interfaces.Datagram;
import keystore.KeyStoreManager;

import java.util.Objects;

public class DefaultCondition implements ACLi.Condition {

    private final Integer sourceGroupId;
    private final Integer destGroupId;
    private final Datagram.Protocol protocol;
    private final Datagram.Flag flag;
    private final KeyStoreManager keyStoreManager;

    DefaultCondition(Integer sourceGroupId, Integer destGroupId, Datagram.Protocol protocol,
                     Datagram.Flag flag, KeyStoreManager keyStoreManager) {
        this.sourceGroupId = Objects.requireNonNull(sourceGroupId);
        this.destGroupId = Objects.requireNonNull(destGroupId);
        this.protocol = Objects.requireNonNull(protocol);
        this.flag = Objects.requireNonNull(flag);
        this.keyStoreManager = Objects.requireNonNull(keyStoreManager);
    }

    public Integer getSourceGroupId() {
        return sourceGroupId;
    }

    public Integer getDestGroupId() {
        return destGroupId;
    }

    public Datagram.Protocol getProtocol() {
        return protocol;
    }

    public Datagram.Flag getFlag() {
        return flag;
    }

    @Override
    public boolean match(Datagram datagram) {
        return new ConditionMatcher(keyStoreManager, this, datagram).match();
    }
}
