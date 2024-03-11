
public class ThreadMin extends Thread {
    private final int startIndex;
    private final int finishIndex;
    private final arrclass arrClass;

    public ThreadMin(int startIndex, int finishIndex, arrclass arrClass) {
        this.startIndex = startIndex;
        this.finishIndex = finishIndex;
        this.arrClass = arrClass;
    }

    @Override
    public void run() {
        int min = arrClass.partMin(startIndex, finishIndex);
        arrClass.collectMin(min);
        arrClass.incThreadCount();
    }
}
