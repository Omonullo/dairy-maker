public class CowChain {

    private ChainLink head;
    private ChainLink tail;
    private int size;

    public CowChain() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public int getSize() {
        return this.size;
    }

    public ChainLink getHead() {
        return this.head;
    }

    public void append(CowRecord cow) {
        if (cow == null) {
            throw new IllegalArgumentException("Cannot append null cow");
        }

        ChainLink newLink = new ChainLink(cow);

        if (this.head == null) {
            this.head = newLink;
            this.tail = newLink;
        } else {
            this.tail.setNext(newLink);
            this.tail = newLink;
        }

        this.size = this.size + 1;
    }

    public CowRecord findById(int cowId) {
        ChainLink current = this.head;

        while (current != null) {
            if (current.getData().getCowId() == cowId) {
                return current.getData();
            }
            current = current.getNext();
        }

        return null;
    }

    public boolean existsById(int cowId) {
        return this.findById(cowId) != null;
    }

    public boolean removeById(int cowId) {
        if (this.head == null) {
            return false;
        }

        if (this.head.getData().getCowId() == cowId) {
            this.head = this.head.getNext();
            if (this.head == null) {
                this.tail = null;
            }
            this.size = this.size - 1;
            return true;
        }

        ChainLink previous = this.head;
        ChainLink current = this.head.getNext();

        while (current != null) {
            if (current.getData().getCowId() == cowId) {
                previous.setNext(current.getNext());
                if (current == this.tail) {
                    this.tail = previous;
                }
                this.size = this.size - 1;
                return true;
            }
            previous = current;
            current = current.getNext();
        }

        return false;
    }
}
