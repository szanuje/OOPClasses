package model;

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
}