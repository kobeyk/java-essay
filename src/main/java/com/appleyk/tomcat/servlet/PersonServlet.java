package com.appleyk.tomcat.servlet;

import com.appleyk.tomcat.request.CRequest;
import com.appleyk.tomcat.request.CResponse;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class PersonServlet extends CServlet{

    @Override
    void doGet(CRequest request, CResponse response) {
       response.write("查询人员信息，请稍等...");
    }

    @Override
    void doPost(CRequest request, CResponse response) {
        response.write("创建人员信息，请稍等...");
    }
}
