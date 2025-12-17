public interface CowRegistry {

    void register(CowRecord cow);

    CowRecord findById(int cowId);

    boolean existsById(int cowId);

    boolean removeById(int cowId);

    int getSize();
}
