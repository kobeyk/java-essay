package com.appleyk.designpatterns.任务回调;

/**
 * <p>任务执行监听器</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @github https://github.com/kobeyk
 * @date created on 15:24 2021/2/20
 */
public interface IJobListener {
    // 任务执行成功回调
    void onSuccess(TJob job);
    // 任务执行异常回调
    void onFail(TJob job, Throwable throwable);
    // 任务执行进度
    void onProgress(long curSize,long totalSze);
}
