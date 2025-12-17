public class Main {

    public static void main(String... args) {
        new Main().run();
    }

    public void run() {
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
    }
}
