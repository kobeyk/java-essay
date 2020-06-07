package com.appleyk.tomcat.config;

/**
 * <p>越努力，越幸运 -- Servlet映射对象</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class CServletMapping {

    /**首先，请求过来，你要告诉我属于哪个App，即要请求哪个servlet*/
    private String servletName;

    /**其次，请求过来，我要知道请求的url是什么*/
    private String url;

    /**最后，根据servletName和url我就可以确定具体由哪一个类中的哪一个方法来执行*/
    private String className;

    public CServletMapping() {
    }

    public CServletMapping(String servletName, String url, String className) {
        this.servletName = servletName;
        this.url = url;
        this.className = className;
    }

    public String getServletName() {
        return servletName;
    }

    public void setServletName(String servletName) {
        this.servletName = servletName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
