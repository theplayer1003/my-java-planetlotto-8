package planetlotto.util;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class RetryHandler {

    public static <T> T retryUntilSuccess(Supplier<T> supplier) {
        return retryUntilSuccess(supplier, System.out::println);
    }

    public static <T> T retryUntilSuccess(Supplier<T> supplier, Consumer<String> errorPrinter) {
        while (true) {
            try {
                return supplier.get();
//            } catch (BusinessException e) {
//                errorPrinter.accept(e.getMessage());
            } catch (IllegalArgumentException e) {
                errorPrinter.accept(e.getMessage());
            }
        }
    }
}
