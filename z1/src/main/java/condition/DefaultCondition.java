package condition;

import interfaces.ACLi;
import interfaces.Datagram;
import keystore.KeyStoreManager;

import java.util.Set;

public class DefaultCondition implements ACLi.Condition {


    private final Set<Integer> sourceGroupsIds;
    private final Set<Integer> destGroupsIds;
    private final Datagram.Protocol protocol;
    private final Set<Datagram.Flag> flags;
    private final KeyStoreManager keyStoreManager;

    protected DefaultCondition(Set<Integer> sourceGroupsIds,
                                Set<Integer> destGroupsIds,
                                Datagram.Protocol protocol,
                                Set<Datagram.Flag> flags,
                                KeyStoreManager keyStoreManager) {
        this.sourceGroupsIds = sourceGroupsIds;
        this.destGroupsIds = destGroupsIds;
        this.protocol = protocol;
        this.flags = flags;
        this.keyStoreManager = keyStoreManager;
    }

    public Set<Integer> getSourceGroupsIds() {
        return sourceGroupsIds;
    }

    public Set<Integer> getDestGroupsIds() {
        return destGroupsIds;
    }

    public Datagram.Protocol getProtocol() {
        return protocol;
    }

    public Set<Datagram.Flag> getFlags() {
        return flags;
    }

    public KeyStoreManager getKeyStoreManager() {
        return keyStoreManager;
    }

    @Override
    public boolean match(Datagram datagram) {
        return new ConditionMatcher(keyStoreManager, this, datagram).match();
    }
}