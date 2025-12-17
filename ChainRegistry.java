/// Linked list implementation of CowRegistry
/// Uses CowChain internally, no collections required
/// Lookups are O(N), appends are O(1) with tail pointer
public class ChainRegistry implements CowRegistry {

    private final CowChain chain;

    public ChainRegistry() {
        this.chain = new CowChain();
    }

    public void register(CowRecord cow) {
        this.chain.append(cow);
    }

    public CowRecord findById(int cowId) {
        return this.chain.findById(cowId);
    }

    public boolean existsById(int cowId) {
        return this.chain.existsById(cowId);
    }

    public boolean removeById(int cowId) {
        return this.chain.removeById(cowId);
    }

}
