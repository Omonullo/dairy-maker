import java.util.HashMap;



/// I don't konw if using HashMap is ok
/// We can switch to using this once it is approved
/// it has better performance with O(1)
public class HashMapRegistry implements CowRegistry {

    private final HashMap<Integer, CowRecord> cows;

    public HashMapRegistry() {
        this.cows = new HashMap<>();
    }

    public void register(CowRecord cow) {
        if (cow == null) {
            throw new IllegalArgumentException("Cannot register null cow");
        }
        this.cows.put(cow.getCowId(), cow);
    }

    public CowRecord findById(int cowId) {
        return this.cows.get(cowId);
    }

    public boolean existsById(int cowId) {
        return this.cows.containsKey(cowId);
    }

    public boolean removeById(int cowId) {
        if (!this.cows.containsKey(cowId)) {
            return false;
        }
        this.cows.remove(cowId);
        return true;
    }
}
