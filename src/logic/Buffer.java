package logic;

public interface Buffer {

    void putElement(Integer element) throws InterruptedException;

    Integer getFirstElement() throws InterruptedException;

    boolean isFull();

    boolean isEmpty();
}