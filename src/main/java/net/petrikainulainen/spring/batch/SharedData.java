package net.petrikainulainen.spring.batch;

public class SharedData {

    private int offset;

    private int limit;

    int currentchunkSize;

    public int getCurrentchunkSize() {
        return currentchunkSize;
    }

    public void setCurrentchunkSize(int currentchunkSize) {
        this.currentchunkSize = currentchunkSize;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

}
