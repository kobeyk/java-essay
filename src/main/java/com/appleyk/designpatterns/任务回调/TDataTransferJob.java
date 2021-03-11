package com.appleyk.designpatterns.任务回调;

/**
 * <p>数据传输任务业务实现</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @github https://github.com/kobeyk
 * @date created on 15:54 2021/2/20
 */
public class TDataTransferJob extends JobExecutable{

    public TDataTransferJob(TJob job){
        super(job);
    }

    @Override
    protected void execute(TJob job, IJobListener listener) throws Exception {
        Long total= job.getSize();
        // 分块传，1024*1024*10 每10M传一下
        long finished = 1024L*1024L*10;
        while (finished<total){
            listener.onProgress(finished,total);
            finished+=1024L*1024L*10;
            Thread.sleep(2000);
        }
        listener.onProgress(finished,total);
    }
}
