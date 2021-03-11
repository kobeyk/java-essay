package com.appleyk.designpatterns.任务回调;

/**
 * <p>任务执行线程</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @github https://github.com/kobeyk
 * @date created on 15:53 2021/2/20
 */
public class JobRunner implements Runnable{

    private final JobExecutable job;
    private final IJobListener listener;

    public JobRunner(JobExecutable jobExecutable, IJobListener listenable) {
        this.job = jobExecutable;
        this.listener = listenable;
    }

    public JobExecutable getJobExecutable() {
        return job;
    }

    @Override
    public void run() {
        // 执行任务
        job.execute(listener);
    }
}
