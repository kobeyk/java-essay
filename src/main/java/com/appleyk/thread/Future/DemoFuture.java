package com.appleyk.thread.Future;

import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <p></p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @github https://github.com/kobeyk/dubbo-spring-boot-sample
 * @date created on 9:19 2020/11/6
 */
public class DemoFuture {

//  static class LineDemo implements Runnable{
//       @Override
//       public void run() {
//           System.out.println("nihao");
//       }
//   }
//
//    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,
//                2,
//                10, TimeUnit.SECONDS,
//                new ArrayBlockingQueue<>(2));
//        Future future = executor.submit(new LineDemo());
//        Object o = future.get();
//        System.out.println(o);
//        executor.shutdown();
//
//    }

public static boolean flag = true;
public static void main(String[] args) {

    Set<Integer> a = Sets.newHashSet(1, 3,5,9);
    Set<Integer> b = Sets.newHashSet(3,11,13);
    Set<Integer> c = Sets.newHashSet(2, 4, 6);
    List<Set<Integer>> list = new ArrayList<>();
    list.add(a);
    list.add(b);
    list.add(c);
    System.out.println("是否存在独立的流程："+check(list));
}

    public static boolean check(List<Set<Integer>> list){
        for (int i = 0; i < list.size(); i++) {
            Set<Integer> tmp = list.get(i);
            int step = 0;
            for (int j = 0; j <list.size() ; j++) {
                if(tmp!=list.get(j) && Sets.intersection(list.get(i),list.get(j)).isEmpty()){
                    step++;
                }
            }
            if(step == list.size()-1){
                flag = false;
                break;
            }

        }
         return flag;
    }

}
