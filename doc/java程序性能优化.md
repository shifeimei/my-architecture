# 程序性能优化
## 设计优化
###设计模式
    -单例模式
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
## 