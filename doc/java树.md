# 程序性能优化
## 设计优化
###设计模式
    -单例模式
    -享元模式
    -代理模式
        如数据库等资源的延迟加载。加载交给代理类。实际调用某方法时才去加载
    -装饰器模式
    定义：装饰器模式（Decorator Pattern）允许向一个现有的对象添加新的功能，同时又不改变其结构。这种类型的设计模式属于结构型模式，它是作为现有的类的一个包装。这种模式【创建了一个装饰类，用来包装原有的类】，并在保持类方法签名完整性的前提下，提供了额外的功能。
    例如：new DataOutputStream(new BufferedOutputStream(new FileOutputStream("")))=
    new DataOutputStream(new FileOutputStream("")); 但前者具有了缓存写的额外功能。缓存这部分算是装饰的功能。
    其中：DataOutputStream是装饰类【一般是通过构造方法传入具体的功能实现类】，BufferedOutputStream是额外功能。简单理解：定义一个抽象类B，实现接口A.B内置一个对象A.定义具体对象C继承B，构造方法为C(A)，实际上传入的可以是B的各种实现D。我们只要在C的实现中调用super的方法就可以获得传入的D的所有功能。D是额外功能，A是装饰类
    -观察者模式
    定义：当对象间存在一对多关系时，则使用观察者模式（Observer Pattern）。比如，当一个对象被修改时，则会自动通知它的依赖对象。观察者模式属于行为型模式。
    本质上就是将客户端的低效轮循改成主动通知。例如：前端提交了一个任务，需要知道结果，前端订阅，后端维护好订阅列表，通过websocket主动告知结果。
    在jdk中，observer,observable就是观察者和被观察者接口。
    实际上在进程之间通信时消息队列也是这种模式，发布者主动通知订阅者。前端数据变化时，主动通知订阅了的组件。是一种很好的设计模式。
### 常用优化组件和方法
    -缓冲
    例如消息的缓冲，文件写的缓冲buffer
    -缓存
    -对象复用-池
    例如数据库的连接池。常用的有c3p0,dbcp,druid（这些都是pool）.(不用池的话是unpool) .淘宝，支付宝用druid.本地用了 dbcp（tomcat数据源）.
    （其中，druid带监控功能，自动识别数据库类型所以不需要指定驱动类；具体可参考https://blog.csdn.net/wawa3338/article/details/81380662）
     【学习】 https://github.com/alibaba/druid.git
     【学习】GenericObjectPool（如果想实现一套连接池，不要重复造轮子，apache给我们提供好了，参考和改造即可）
    -串行转并行
    -负载均衡
    -时间换空间&空间换时间
## 程序优化
### 字符串
    -subString(begin,end)方法实际引用了原对象，可能会内存泄漏（该对象无法被及时释放）。时间换空间[jdk1.6存在，向上的版本已修复，直接拷贝了字符串]
    string.intern
    {
            String a4 = new String("AABB"); //在堆上AABB
            a4.intern(); // 添加到常量池 "AABB"
            String a5 = "AABB";
            System.out.println(a4 == a5); //false
                
            String a4 = new String("AA") +"BB";  //在堆上AABB
            a4.intern(); // 将a4的引用添加到了常量池（注意不是加号方式的字符串不会是引用的方式）
            String a5 = "AABB";
            System.out.println(a4 == a5); //true 【实际都指向了堆上的a4】
    }


# spring框架
## 概要
* spring ioc(控制反转)，aop(面向切片)
* spring核心模块：
    spring-core:Spring的核心组件
    spring-beans:SpringIoC(依赖注入)的基础实现
    spring-context:Spring提供在基础IoC功能上的扩展服务，此外还提供许多企业级服务的支持，如邮件服务、任务调度、JNDI定位、EJB集成、远程访问、缓存以及各种视图层框架的封装等
    spring-jdbc:
    spring-tx:
    spring-web:
    spring-webmvc:
    spring-aop:Spring的面向切面编程,提供AOP(面向切面编程)实现
    spring-aspects:Spring提供对AspectJ框架的整合
    spring-security:如csrfToken等
    spring-test:

* springboot
    部署方便：内嵌tomcat（减少依赖）,开箱即用（易上手），抛弃xml配置（配置简单），部署方便（jar方式，main方法），对云,pass方便
    rest高效支持：

* java8特性
    optional:减少npe问题概率，增强代码可读性
        Integer a = null;
        Integer b = Optional.ofNullable(a).orElse(2);
        System.out.println(b);
        System.out.println(b.equals(a));
* 设计模式
    控制反转。控制反转是从主动创建变成被动接受（制定了标准）。DI是实现控制反转的手段。控制反转是目标或者叫思想。
    工厂模式
    单例
    创建者模式
    装饰模式
    策略模式
    观察者模式
    代理模式
* mybatis
    --不和spring结合时如何使用
    String resource = "mybatis-config.xml";
    Reader reader = Resources.getResourceAsReader(resource);
    SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reader);
    SqlSession session = ssf.openSession(ExecutorType.BATCH);
    try {
        Data data = (Data)session.selectOne("XxDao", "1");
    } finally {
        session.close();
    }
    -mybatis executor类型：SIMPLE, REUSE, BATCH三种。CachingExecutor(这种只是作为一个executer的包装)
    batch的用法=batch insert values(xxx)

