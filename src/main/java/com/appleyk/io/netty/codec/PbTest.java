package com.appleyk.io.netty.codec;

import com.appleyk.io.netty.codec.protobuf.model.StudentModel;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  11:22 下午 2021/2/21
 */
public class PbTest {
    public static void main(String[] args) {

        // 编译的CMD： protoc --java_out=. Student.proto

        // 消息体构构建器
        StudentModel.Student.Builder builder = StudentModel.Student.newBuilder();
        builder.setId(100);
        builder.setName("你猜我猜不猜");

        // builder转对象
        StudentModel.Student student = builder.build();
        System.out.println(student.getName());
        System.out.println(student.getId());
        System.out.println(student.toByteArray().length);
    }
}
