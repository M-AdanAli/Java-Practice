package functionalprogramming;

import java.util.function.BiFunction;

public class FunctionInterfaceDemo {
    public static BiFunction<Integer, Integer, Integer> subtract = (a,b) -> a-b;
    public static BiFunction<Integer,Integer,Integer> sumFunction = Integer::sum;

    public static void main(String[] args) {
        System.out.println(subtract.apply(10,4));

    }
}
