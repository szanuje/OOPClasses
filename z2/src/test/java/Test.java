import interfaces.ModelConstants;

public class Test {

    public static void main(String[] args) {
        ModelConstants model = DefaultModelConstants.builder()
                .withBeta(3.3)
                .withDesiredNumberOfNeighbors(2.2)
                .build();
        System.out.println(model);
    }
}
