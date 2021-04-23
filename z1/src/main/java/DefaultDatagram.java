import interfaces.Datagram;

import java.util.Set;

public class DefaultDatagram implements Datagram {

    private final String sourceAddress;
    private final String destinationAddress;
    private final Protocol protocol;
    private final Set<Flag> flags;

    public DefaultDatagram(String sourceAddress, String destinationAddress, Protocol protocol, Set<Flag> flags) {
        this.sourceAddress = sourceAddress;
        this.destinationAddress = destinationAddress;
        this.protocol = protocol;
        this.flags = flags;
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