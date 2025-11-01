package MultithreadingAndConcurrency.concurrentCollection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentCache {
    private static final Map<String,String> cache = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        for (int i = 0; i <9; i++) {
            int threadNum = i;
            new Thread(()->{
                String key = "Key @ "+threadNum;
                for (int j = 0; j < 3; j++) {
                    String value = getCachedValue(key);
                    System.out.println("Thread : "+Thread.currentThread().getName()+" (key : "+key+" value : "+value+")");
                }
            }).start();
        }
    }

    private static String getCachedValue(String key){
        String value = cache.get(key);

        if (value == null){
            value = computeValue(key);
            cache.put(key,value);
        }
        return value;
    }

    private static String computeValue(String key){
        System.out.println(key+" not present in the Cache, So going to compute!");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        return "Value for "+key;
    }
}