package components.administrator.process;

import java.util.HashMap;

/**
 * Created by Sudarshan on 1/30/2017.
 */
public class ThreadManager {
    HashMap<String, Thread> threadHashMap;

    public ThreadManager() {
        threadHashMap = new HashMap<>();
    }

    public void addThread(String key, Thread value){
        threadHashMap.put(key, value);
    }

    public Thread getThread(String key){
        return threadHashMap.get(key);
    }

}
