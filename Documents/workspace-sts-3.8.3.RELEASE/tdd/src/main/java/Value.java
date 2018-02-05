public class Value {

    private Integer value;

    public Value(Integer value) {
        this.value = value;
    }

    public boolean isOdd() {

        return value %2 != 0;
    }
}
