package com.appleyk.tomcat.config;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class CServletMappingConfig {
    public static List<CServletMapping> mappings  ;
    static {
        mappings = new ArrayList<>();
        mappings.add(new CServletMapping("user","/user/query",
                "com.appleyk.tomcat.servlet.UserServlet"));
        mappings.add(new CServletMapping("person","/person/query",
                "com.appleyk.tomcat.servlet.PersonServlet"));
    }
}
