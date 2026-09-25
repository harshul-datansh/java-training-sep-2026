@FunctionalInterface
public interface OrderRule {
    boolean test(String orderId);
    String describe();
}
