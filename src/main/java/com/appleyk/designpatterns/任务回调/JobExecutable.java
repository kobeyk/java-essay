package com.appleyk.designpatterns.任务回调;


/**
 * <p>任务抽象执行类（将任务通用执行的地方封装一下）</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @github https://github.com/kobeyk
 * @date created on 15:30 2021/2/20
 */
public abstract class JobExecutable {

    private final TJob job;
    public JobExecutable(TJob job) {
       this.job = job;
    }

    public void execute(IJobListener listener){
        try{
            execute(job,listener);
            // 成功了就执行success回调方法
            listener.onSuccess(job);
        }catch (Exception e){
            // 异常了就执行fail回调方法
            listener.onFail(job,e.getCause());
        }
    }

    protected abstract void execute(TJob job, IJobListener listener) throws Exception;
}
