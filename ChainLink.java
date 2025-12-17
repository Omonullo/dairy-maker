public class ChainLink {

    private CowRecord data;
    private ChainLink next;

    public ChainLink(CowRecord data) {
        this.data = data;
        this.next = null;
    }

    public CowRecord getData() {
        return this.data;
    }

    public ChainLink getNext() {
        return this.next;
    }

    public void setNext(ChainLink next) {
        this.next = next;
    }
}
