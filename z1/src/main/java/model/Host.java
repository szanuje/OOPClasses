package model;

import java.util.Objects;

public class Host {

    private final String hostAddress;
    private final String hostName;

    public Host(String hostAddress, String hostName) {
        this.hostAddress = hostAddress;
        this.hostName = hostName;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public String getHostName() {
        return hostName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Host host = (Host) o;
        return Objects.equals(hostAddress, host.hostAddress) && Objects.equals(hostName, host.hostName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hostAddress, hostName);
    }
}
