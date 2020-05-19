package ioc;

import com.appleyk.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

/**
 * <p></p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/18 9:47 PM
 */
public class IocTest {


    /**
     * 通过类路径加载、读取、解析xml bean配置，refresh，构建IOC（高级）容器
     */
    @Test
    public void getBeanByCpx(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = ac.getBean("userService",UserService.class);
        userService.save();
        ((ClassPathXmlApplicationContext)ac).destroy();
    }

    /*
     * 通过文件系统加载、读取、解析xml bean配置，refresh，构建IOC（高级）容器
     */
    @Test
    public void getBeanByFsx(){
        ApplicationContext ac = new FileSystemXmlApplicationContext("//Users//apple//Appleyk//github//java-essay//src//main//resources//spring.xml");
        UserService userService = ac.getBean("userService",UserService.class);
        userService.save();
    }


    /**
     * 通过BeanFactory创建bean，构造基础IOC容器，然后再从容器中再获取bean
     */
    @Test
    public void getBeanByFactoryByClassPath(){
        ClassPathResource resource = new ClassPathResource("spring.xml");
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(resource);
        UserService userService = factory.getBean("userService", UserService.class);
        userService.save();
    }


    @Test
    public void getBeanFactoryByFileSystem(){
        FileSystemResource resource = new FileSystemResource("//Users//apple//Appleyk//github//java-essay//src//main//resources//spring.xml");
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);
        beanFactory.getBean("houseBean");
    }


}
