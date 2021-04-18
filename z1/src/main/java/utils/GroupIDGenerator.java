package utils;

public class GroupIDGenerator {

    private static Integer number = 0;

    private GroupIDGenerator() {
    }

    public static Integer getNewGroupID() {
        return number++;
    }
}