package com.appleyk.aop;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class ProjectService {

    /**
     * 项目保存
     * @param id 唯一表示
     * @param name 名称
     * @return 插入的影响行数
     */
    public Integer save(Long id, String name ) throws Exception{
        if(id == null){
            throw new Exception("项目ID不允许空");
        }
        System.out.println("方法执行，插入一条记录：insert into t_project values("+id+",'"+name+"')");
        return 1;
    }
}
