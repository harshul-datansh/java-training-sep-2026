import java.util.*;
public class Loop {
    void run() {
        List<Runnable> tasks = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            tasks.add(() -> System.out.println(i));
        }
    }
}
