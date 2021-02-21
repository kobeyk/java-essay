package com.appleyk.designpatterns.任务回调;

/**
 * <p></p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @github https://github.com/kobeyk
 * @date created on 15:25 2021/2/20
 */
public class TJob {

    private Long id;
    private String name;
    /**数据量*/
    private Long size;
    /**0:待执行 1：执行成功 -1:执行失败*/
    private Integer status = 0;

    public TJob(Long id, String name, Long size) {
        this.id = id;
        this.name = name;
        this.size = size;
    }

    public TJob(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
