package com.appleyk.collection.对象克隆;

import java.io.*;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  9:45 下午 2020/10/21
 */
public class TUser implements Cloneable,Serializable{

    private Integer id;
    private String name;
    private TAddress address;

    public TUser() {
    }

    public TUser(Integer id, String name, TAddress address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TAddress getAddress() {
        return address;
    }

    public void setAddress(TAddress address) {
        this.address = address;
    }

    /**
     * 浅拷贝
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected TUser clone() throws CloneNotSupportedException {
        return (TUser) super.clone();
    }

    /**
     * 深拷贝
     * @return
     */
    protected TUser depthClone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        // 写对象
        oos.writeObject(this);

        // 读对象
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois =new ObjectInputStream(bis);
        Object o = ois.readObject();
        ois.close();
        bis.close();
        oos.close();
        bos.close();
        return (TUser)o;
    }

    @Override
    public String toString() {
        return "TUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';
    }

    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        TUser user = new TUser(1,"a",new TAddress("苏州市","山湖湾"));
        TUser clone = user.clone();
        System.out.println(clone.getName());
        user.getAddress().setCity("信阳市");
        System.out.println(clone.getAddress());

        System.out.println("开始深拷贝");
        TUser depthClone = user.depthClone();
        user.getAddress().setLocation("浉河区");
        System.out.println(depthClone.getAddress());
    }
}
