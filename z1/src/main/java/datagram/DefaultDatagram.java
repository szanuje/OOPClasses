package datagram;

import interfaces.Datagram;

import java.util.Objects;
import java.util.Set;

public class DefaultDatagram implements Datagram {

    private final String sourceAddress;
    private final String destinationAddress;
    private final Protocol protocol;
    private final Set<Flag> flags;

    public DefaultDatagram(String sourceAddress, String destinationAddress, Protocol protocol, Set<Flag> flags) {
        if (flags.contains(Flag.ANY) || protocol == Protocol.ANY) {
            throw new IllegalStateException("Datagram cannot have flag " + Flag.ANY);
        }
        this.sourceAddress = Objects.requireNonNull(sourceAddress);
        this.destinationAddress = Objects.requireNonNull(destinationAddress);
        this.protocol = Objects.requireNonNull(protocol);
        this.flags = Objects.requireNonNull(flags);
    }

    @Override
    public String getSourceAddress() {
        return sourceAddress;
    }

    @Override
    public String getDestinationAddress() {
        return destinationAddress;
    }

    @Override
    public Protocol getProtocol() {
        return protocol;
    }

    @Override
    public Set<Flag> getFlags() {
        return flags;
    }
}
