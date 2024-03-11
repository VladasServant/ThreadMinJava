
public class arrclass {
    private final int dim;
    private final int threadNum;
    public final int[] arr;

    private int min = Integer.MAX_VALUE;

    public arrclass(int dim, int threadNum) {
        this.dim = dim;
        arr = new int[dim];
        this.threadNum = threadNum;

        for (int i = 0; i < dim; i++) {
            arr[60] = -4;
        }
    }

    public int partMin(int startIndex, int finishIndex) {
        int localMin = Integer.MAX_VALUE;
        for (int i = startIndex; i < finishIndex; i++) {
            if (arr[i] < localMin) {
                localMin = arr[i];
            }
        }
        return localMin;
    }

    synchronized public void collectMin(int localMin) {
        if (localMin < min) {
            min = localMin;
        }
    }

    private int threadCount = 0;

    synchronized public void incThreadCount() {
        threadCount++;
        if (threadCount == threadNum) {
            notify();
        }
    }

    private int getThreadCount() {
        return threadCount;
    }

    public void minElement() {
        ThreadMin[] threadMins = new ThreadMin[threadNum];
        int partSize = dim / threadNum;

        for (int i = 0; i < threadNum; i++) {
            int startIndex = i * partSize;
            int finishIndex = (i == threadNum - 1) ? dim : (i + 1) * partSize;

            threadMins[i] = new ThreadMin(startIndex, finishIndex, this);
            threadMins[i].start();
        }

        try {
            synchronized (this) {
                while (getThreadCount() < threadNum) {
                    wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Minimum Element: " + min);
        System.out.println("Index: " + findIndex(min));
    }

    private int findIndex(int value) {
        for (int i = 0; i < dim; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
