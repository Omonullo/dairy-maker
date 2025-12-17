/// Interface for cow lookup strategies
/// Allows switching between different storage implementations
/// See ChainRegistry for linked list, HashMapRegistry for O(1) lookups
public interface CowRegistry {

    void register(CowRecord cow);

    CowRecord findById(int cowId);

    boolean existsById(int cowId);

    boolean removeById(int cowId);

}
