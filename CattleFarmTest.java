public class CattleFarmTest {

    private int passed;
    private int failed;

    public static void main(String... args) {
        new CattleFarmTest().run();
    }

    public void run() {
        this.passed = 0;
        this.failed = 0;

        this.testMissingParent();
        this.testDuplicateId();
        this.testRemoveParentWithChildren();
        this.testRemoveRoot();
        this.testRemoveNonExistent();
        this.testNegativeIdValidation();
        this.testNullNicknameValidation();
        this.testNullCowAppend();

        System.out.println();
        System.out.println("=== Results: " + this.passed + " passed, " + this.failed + " failed ===");
    }

    private void testMissingParent() {
        CattleFarm farm = new CattleFarm(1, "Bessie");
        boolean result = farm.giveBirth(999, 2, "Orphan");
        this.assertFalse(result, "giveBirth with missing parent should return false");
    }

    private void testDuplicateId() {
        CattleFarm farm = new CattleFarm(1, "Bessie");
        farm.giveBirth(1, 2, "Daisy");
        boolean result = farm.giveBirth(1, 2, "Ghost");
        this.assertFalse(result, "giveBirth with duplicate ID should return false");
    }

    private void testRemoveParentWithChildren() {
        CattleFarm farm = new CattleFarm(1, "Bessie");
        farm.giveBirth(1, 2, "Daisy");
        farm.giveBirth(2, 3, "Luna");
        farm.giveBirth(2, 4, "Star");

        boolean result = farm.endLifeSpan(2);
        this.assertTrue(result, "endLifeSpan should succeed for parent with children");

        CowRecord luna = farm.findCow(3);
        CowRecord star = farm.findCow(4);
        CowRecord bessie = farm.findCow(1);

        this.assertTrue(luna != null, "Luna should still exist");
        this.assertTrue(star != null, "Star should still exist");
        this.assertTrue(luna.getParent() == bessie, "Luna's parent should be Bessie");
        this.assertTrue(star.getParent() == bessie, "Star's parent should be Bessie");
        this.assertTrue(farm.findCow(2) == null, "Daisy should be removed");
    }

    private void testRemoveRoot() {
        CattleFarm farm = new CattleFarm(1, "Bessie");
        boolean result = farm.endLifeSpan(1);
        this.assertFalse(result, "endLifeSpan should fail for root cow");
    }

    private void testRemoveNonExistent() {
        CattleFarm farm = new CattleFarm(1, "Bessie");
        boolean result = farm.endLifeSpan(999);
        this.assertFalse(result, "endLifeSpan should fail for non-existent cow");
    }

    private void testNegativeIdValidation() {
        boolean threw = false;
        try {
            new CowRecord(-1, "Bad", null);
        } catch (IllegalArgumentException e) {
            threw = true;
        }
        this.assertTrue(threw, "CowRecord should reject negative ID");
    }

    private void testNullNicknameValidation() {
        boolean threw = false;
        try {
            new CowRecord(1, null, null);
        } catch (IllegalArgumentException e) {
            threw = true;
        }
        this.assertTrue(threw, "CowRecord should reject null nickname");
    }

    private void testNullCowAppend() {
        boolean threw = false;
        try {
            CowChain chain = new CowChain();
            chain.append(null);
        } catch (IllegalArgumentException e) {
            threw = true;
        }
        this.assertTrue(threw, "CowChain.append should reject null cow");
    }

    private void assertTrue(boolean condition, String message) {
        if (condition) {
            System.out.println("[PASS] " + message);
            this.passed = this.passed + 1;
        } else {
            System.out.println("[FAIL] " + message);
            this.failed = this.failed + 1;
        }
    }

    private void assertFalse(boolean condition, String message) {
        this.assertTrue(!condition, message);
    }
}
