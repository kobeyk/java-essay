package com.appleyk.designpatterns.任务回调.测试;

import com.appleyk.designpatterns.任务回调.IJobListener;
import com.appleyk.designpatterns.任务回调.JobRunner;
import com.appleyk.designpatterns.任务回调.TDataTransferJob;
import com.appleyk.designpatterns.任务回调.TJob;

import java.text.NumberFormat;

/**
 * <p>任务执行回调测试</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @github https://github.com/kobeyk
 * @date created on 16:08 2021/2/20
 */
public class CallBackTest {

    public static void main(String[] args) throws Exception {

        TJob job = new TJob(1L,"数据传输(50M)",1024L*1024L*50);
        System.out.println("任务名称："+job.getName()+"开始传输，任务状态："+job.getStatus());
        Thread t1 = new Thread(new JobRunner(new TDataTransferJob(job), new IJobListener() {
            @Override
            public void onSuccess(TJob job) {
                job.setStatus(1);
                System.out.println("======= 数据传输完毕 =======");
            }

            @Override
            public void onFail(TJob job, Throwable throwable) {
                job.setStatus(-1);
                System.out.println("数据传输异常：" + throwable.getMessage());
            }

            @Override
            public void onProgress(long curSize, long totalSze) {
                // 创建一个数值格式化对象
                NumberFormat numberFormat = NumberFormat.getInstance();
                // 设置精确到小数点后2位
                numberFormat.setMaximumFractionDigits(2);
                String result = numberFormat.format((float) curSize / (float) totalSze * 100);
                System.out.println("已传输：" + result + "%");
            }
        }));
        t1.start();
        t1.join();
        System.out.println("任务名称："+job.getName()+"传输结束，任务状态："+job.getStatus());
    }
}
