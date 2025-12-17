public class CattleFarm {

    private CowRecord rootCow;
    private CowRegistry registry;

    public CattleFarm(int rootId, String rootName) {
        this(rootId, rootName, new ChainRegistry());
    }

    public CattleFarm(int rootId, String rootName, CowRegistry registry) {
        this.rootCow = new CowRecord(rootId, rootName, null);
        this.registry = registry;
        this.registry.register(this.rootCow);
    }

    public CowRecord findCow(int cowId) {
        return this.registry.findById(cowId);
    }

    public boolean giveBirth(int parentCowId, int childCowId, String childNickName) {
        if (this.registry.existsById(childCowId)) {
            return false;
        }

        CowRecord parent = this.findCow(parentCowId);

        if (parent == null) {
            return false;
        }

        CowRecord calf = new CowRecord(childCowId, childNickName, parent);
        parent.addCalf(calf);
        this.registry.register(calf);

        return true;
    }

    public boolean endLifeSpan(int cowId) {
        if (cowId == this.rootCow.getCowId()) {
            return false;
        }

        CowRecord cow = this.findCow(cowId);

        if (cow == null) {
            return false;
        }

        CowRecord parent = cow.getParent();

        if (parent == null) {
            return false;
        }

        if (!parent.getChildren().existsById(cowId)) {
            return false;
        }

        if (!this.registry.existsById(cowId)) {
            return false;
        }

        boolean removedFromParent = parent.removeCalfById(cowId);
        if (!removedFromParent) {
            return false;
        }

        boolean removedFromRegistry = this.registry.removeById(cowId);
        if (!removedFromRegistry) {
            parent.addCalf(cow);
            return false;
        }

        CowChain orphans = cow.getChildren();
        ChainLink currentOrphan = orphans.getHead();
        while (currentOrphan != null) {
            CowRecord orphan = currentOrphan.getData();
            orphan.setParent(parent);
            parent.addCalf(orphan);
            currentOrphan = currentOrphan.getNext();
        }

        cow.clearChildren();

        return true;
    }

    public void printFarm() {
        this.printCowRecursive(this.rootCow, 0);
    }

    private void printCowRecursive(CowRecord cow, int depth) {
        String indent = "";
        int i = 0;
        while (i < depth) {
            indent = indent + "  ";
            i = i + 1;
        }

        System.out.println(indent + "Cow(" + cow.getCowId() + ") " + cow.getNickName());

        ChainLink currentChild = cow.getChildren().getHead();
        while (currentChild != null) {
            this.printCowRecursive(currentChild.getData(), depth + 1);
            currentChild = currentChild.getNext();
        }
    }
}
