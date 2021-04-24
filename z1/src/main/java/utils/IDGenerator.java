package utils;

public class IDGenerator {

    private Integer number;

    public IDGenerator() {
        number = 0;
    }

    public Integer getNewGroupID() {
        return number++;
    }
}