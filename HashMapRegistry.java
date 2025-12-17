import java.util.HashMap;

public class HashMapRegistry implements CowRegistry {

    private HashMap<Integer, CowRecord> cows;

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

    public int getSize() {
        return this.cows.size();
    }
}
