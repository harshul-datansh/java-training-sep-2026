import java.util.function.Supplier;
public class Counter {
    Supplier<Integer> make() {
        int count = 0;
        count++;
        return () -> count;
    }
}
