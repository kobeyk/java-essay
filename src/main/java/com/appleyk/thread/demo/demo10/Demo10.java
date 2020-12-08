package com.appleyk.thread.demo.demo10;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p></p>
 *
 * @author yukun24@126.com
 * @version V.1.0.1
 * @company 苏州中科蓝迪
 * @date created on 17:32 2020/6/8
 */
public class Demo10 {

    // 假设总容量80G
    private volatile Integer capacity = 80;
    private ConcurrentHashMap<String, Boolean> flags = new ConcurrentHashMap<String, Boolean>();

    /**
     * 模拟数据上传
     * @param currentCapacity
     */
    public void storage(Integer currentCapacity){
        String name = Thread.currentThread().getName();
        System.out.println(name+",上传数据量："+currentCapacity+"G");
        this.capacity-=currentCapacity;
        if("t3".equals(name)){
            flags.put(name,false);
            System.out.println(name+",上传异常数据量："+currentCapacity+"G");
        }else{
            flags.put(name,true);
        }

    }

    public Integer getCapacity() {
        return capacity;
    }

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();
        Demo10 demo10 = new Demo10();
        System.out.println("数据总容量：80G");
        Map<String, Integer> threadCapacity = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            String thName = "t" + (i + 1);
            Integer rand = new Random().nextInt(10);
            threads.add(new Thread(() -> demo10.storage(rand), thName));
            threadCapacity.put(thName, rand);
        }
        threads.forEach(t -> t.start());
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
            }
        });
        System.out.println("所有数据完成上传！");
        System.out.println("最后容量：" + demo10.getCapacity() + "G");
        System.out.println("检查数据上传任务是否有异常的：");
        if (demo10.flags.size() > 0) {
            for (Map.Entry<String, Boolean> flags : demo10.flags.entrySet()) {
                String key = flags.getKey();
                Boolean value = flags.getValue();
                if (!value) {
                    Integer capacity = threadCapacity.get(key);
                    demo10.capacity += capacity;
                    System.out.println(key + "进行任务回滚，撤销"+key+"上传的" + capacity+"G");
                    System.out.println("开始删除"+key+"上传的文件.....");
                }
            }
        }
        System.out.println("核查数据后，最终的设备剩余容量为：" + demo10.capacity);

    }
}
