public class Dinglemouse {
    public static final Dinglemouse INST = new Dinglemouse();
    private static final int ONE_HUNDRED = 100;
    private int value;

    private Dinglemouse() {
        value = ONE_HUNDRED;
    }

    public int plus100(int n) {
        return n + ONE_HUNDRED;
    }
}

