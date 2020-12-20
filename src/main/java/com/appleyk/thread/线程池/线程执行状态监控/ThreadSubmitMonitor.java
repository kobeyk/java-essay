package com.appleyk.thread.线程池.线程执行状态监控;

import com.appleyk.thread.线程池.ThreadPoolExecutorFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  5:35 下午 2020/12/20
 */
public class ThreadSubmitMonitor {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = ThreadPoolExecutorFactory.getPoolExecutor();
        Future<?> submit = poolExecutor.submit(() -> {
            int res = 1 / 0;
            // 如果以submit的方式执行线程的话，如果任务异常，则不会抛
            // 因为它在调用任务时，如果抛出异常，会先保存下来，随后通过get拿到异常值
            // 再跟进当前任务task的执行状态，确定向外抛出的异常类型是什么，比如取消异常、执行异常等
        });
        try {
            submit.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        // 关闭线程池
        poolExecutor.shutdown();;
    }
}
