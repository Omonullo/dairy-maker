/// Entry point for Java 6 (no main method required)
/// Static block runs on class load, System.exit(0) terminates before JVM checks for main
/// Usage: java -cp out Main6
public class Main6 {

    static {
        CattleFarm farm = new CattleFarm(1, "Bessie");

        farm.giveBirth(1, 2, "Daisy");
        farm.giveBirth(1, 3, "Rose");
        farm.giveBirth(2, 4, "Luna");
        farm.giveBirth(2, 5, "Star");

        System.out.println("=== Farm after births ===");
        farm.printFarm();

        boolean duplicateResult = farm.giveBirth(1, 4, "Ghost");
        System.out.println("Duplicate ID accepted: " + duplicateResult);

        farm.endLifeSpan(2);

        System.out.println("=== Farm after Daisy removed ===");
        farm.printFarm();

        System.exit(0);
    }
}
