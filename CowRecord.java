/// Represents a single cow with ID, nickname, parent reference, and children
/// Forms a tree structure where each cow knows its parent and children
/// Validates that ID is non-negative and nickname is not null
public class CowRecord {

    private final int cowId;
    private final String nickName;
    private CowRecord parent;
    private CowChain children;

    public CowRecord(int cowId, String nickName, CowRecord parent) {
        if (cowId < 0) {
            throw new IllegalArgumentException("Cow ID cannot be negative");
        }
        if (nickName == null) {
            throw new IllegalArgumentException("Nickname cannot be null");
        }
        this.cowId = cowId;
        this.nickName = nickName;
        this.parent = parent;
        this.children = new CowChain();
    }

    public int getCowId() {
        return this.cowId;
    }

    public String getNickName() {
        return this.nickName;
    }

    public CowRecord getParent() {
        return this.parent;
    }

    public void setParent(CowRecord parent) {
        this.parent = parent;
    }

    public CowChain getChildren() {
        return this.children;
    }

    public void addCalf(CowRecord calf) {
        this.children.append(calf);
    }

    public boolean removeCalfById(int cowId) {
        return this.children.removeById(cowId);
    }

    public void clearChildren() {
        this.children = new CowChain();
    }
}
