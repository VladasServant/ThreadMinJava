
public class Main {
    public static void main(String[] args) {
        int dim = 10000000;
        int threadNum = 4;
        arrclass arrClass = new arrclass(dim, threadNum);
        arrClass.minElement();
    }
}