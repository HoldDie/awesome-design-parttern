### 工厂模式

工厂设计模式的思考：

#### 简单工厂和工厂方法差异？

- 工厂方法只会返回一种类型的实例，最好具有父类或者共同实现的接口。
- 简单工厂：用来生产同一等级结构的任意产品（对于任意新增的产品，无能为力）
- 工厂方法：用来生产同一等级结构中的固定产品（支持增加任意产品）
- 抽象工厂：用来生产不同产品族的全部产品。（对于新增的产品，无能为力，只是多种实现定义好的产品生成，实例的组合，支持添加产品族，就是产品的下线由谁生产，但是生产什么是事先固定好的）

#### 工厂方法和抽象工厂差异？

- 抽象工厂关键在于产品之间的抽象关系，所以至少要两个产品，工厂方法在于生成产品，不关注产品键的关系，所以可以只生成一个产品。
- 抽象工厂中客户端把产品的抽象关系理清楚，在最终使用的时候，一般使用客户端和（其接口），产品之间的关系是被封装固定的；而工厂方法是在最终使用的时候，使用产品本身。
- 抽象工厂更像一个复杂版本的策略模式，策略模式通过更换策略来改变处理方式或者结果，而抽象工厂的客户端，通过更改工厂来改变结果，所以在使用的时候，就使用客户端和更换工厂，而看不到产品本身。
- 工厂方法的目的是生产商品，所以能看到商品，而且还是要使用商品，当然如果产品在创建者内部使用，那么工厂方法就是为了完善创建者，从而可以使用创建者，另外创建者本身是不能更换所生成的产品的。
- 抽象工厂的工厂是类，工厂方法的工厂是方法。

#### 客户和工厂之间还是有耦合，如何进一步优化？

- 依赖反转原理（dependency inversion principle）
- 控制反转（inversion of control）
- 依赖注入（dependency injection）

### 单例模式

- 单例模式限制类的实例化，保证JVM中的类的实例只有一个
- 单例类提供一个可以全局访问单例的入口
- 常用于
  - 日志logging
  - 配置设置类
  - 驱动程序对象
  - 缓存对象
  - 线程池对象
- 其中会涉及JAVA面试题
  - 提前和延迟（lazy）
  - 静态初始化块
  - 多线程安全
  - 双重检查锁定（double checked locking）
  - JVM、JIT指令优化重排
  - 反射（reflection）
  - 序列化（serialization）
  - 克隆（clone）

单例模式：

- 普通的懒汉模式
- 饿汉模式
- 同步饿汉模式
- 比尔普夫模式
- 枚举模式

破坏单例模式：

- 反射破坏
- 防止，就是构造方法抛异常

单例模式的应用：

- Core Java
  - java.lang.Runtime
  - java.awt.Desktop
- Spring 容器
  - Singleton Scope（per container）

破坏单例模式的方法：

### 经典构建者模式

- 将复杂对象的构造和表示分离，相同的构造流程可以创建不同的表示。

四种角色：

- Director（导演）：规范和指导创建流程（规定这个片子怎么拍）
- Builder（导演要求的演员标准）：创建实际产品的接口（拍电影中，需要演员的基本条件）
  - 会空翻
  - 会打架
- ConcreateBuilder（演员）：负责创建复杂产品的具体类，知道每个部件的构造细节（具体某个演员）
  - 成龙，会空翻，会打架
  - 李连杰也会空翻，会打架
- Product（电影）：组装出来的最后产品
  - 最后呈现到荧幕上的最终效果。

#### 构建者模式和抽象工厂区别

- 抽象工厂相比较于普通的工厂就是，
  - 可以生产一个产品族，一个电器行业，都有开关，电灯泡和电风扇
  - 每一个方法可以生成一个产品
- 构建者模式是规定一个产品的生产过程中的具体细节
  - 最终只会构建出来一个产品
  - 建造者模式是创建负载对象的一种分解方式

### 流畅构建者模式

问题域：

- 传统的构建者模式在实际的场景中应用不多
- 流畅接口Fluent Interface实际场景中常用
  - 方法级联（Method Cascading /Chaining）
  - 事实上的Builder模式
- 不可变（immutability）对象需求
  - 不容易出Bug
  - 多线程安全

#### 构造对象实例的传统方式

- 构造函数（Telesoping Constructor Pattern）
- Setter 方法（JavaBeans Pattern）
- 工厂

#### 显微镜构造函数模式

- 罗列各种参数组合复杂繁琐
- 扩展字段，增加构造函数方法
- 构造函数，参数位置，重载哟

#### JavaBean 模式

- 使用一堆的Setter和Getter方法
- 状态可变，一致性保证
  - 应为使用setter，不能保证最终的值是否一致
  - 潜在一致性问题
- 多线程安全

#### 流畅接口模式

- 所有的字段都是final，事先定义好
- 构造函数是私有的，
- 全部都是getter方法，没有setter方法
- 使用一个BeanBuilder实体，并且实体字段也是final可自定义选择
- 每一个方法设置参数完成以后返回的都是自己BeanBuilder
- 最后定义一个Builder方法，代表产品要出炉了，把自己的BeanBuilder实体传入其中，返回Bean实体
- 之后可以进行一些验证操作，返回Bean

#### 优点

- 参数组合更加灵活
- 参数设置直观表意
- 一个构造函数实例可以构造多个对象表示
- 构造出不可变对象（immutable Object）

#### 应用：

- Core Java
  - java.lang.StringBuilder#append()
  - java.lang.StringBuffer#append()
  - java.nio.ByteBuffer#put()
- Spring Framework
  - EmbeddedDataBuilder
  - AuthenticationManagerBuilder
  - UriComponentsBuilder
  - MockMvcWebClientBuilder

Fluent 接口模式有啥不足？适用于什么样的场景?

- Bean 构造方法是私有的，意味着该类不能在客户端代码里直接实例化
- 改立现在有事不可变的了，所有属性嗾使`final`类型的，在构造方法里面被赋值，另外我们只为他们提供了getter方法
- builder类使用了构造方法只接受必须属性，为了确保这些属性在构造方法里赋值，只有这些属性被定义成final类型。

### 适配器模式

#### 定义

- 将类的接口转换成客户期望的接口
- 让具有不兼容接口的类能够相互合作
- 常用名称
  - Wrapper
  - Translator

#### 实例：

- 常见的显示器的接口和笔记本接口 
- 对应到工程中，就是新系统的接口和老系统的接口不能很好的匹配，这就需要中间有一个`Adapter` 将两者连接到一起
- 我自己的理解就是，Service实现和Dao实现，对于底层的实现可以有多种，只是我们平常就只使用一种，致使自己也没有什么感觉。

#### 要素：

- Target：定义客户要使用的接口
- Adapter：中间层的适配
  - 实现Target接口
  - 引入被转换的对象
  - 然后在对应的方法中，调用对应的方法适配呗
- Adaptee：需要被转换的对象
- Client：通过适配器接口Target去使用了Adaptee的功能

#### 注意：

- 适配器模式只是针对两个接口之间的适配，主要是适配功能
- 对于刚开始的Service和Dao的认识有偏差，但是整体的调用流程相似
- 针对接口的适配，接口的适配，接口的适配

利用适配器模式，使用数组实现队列接口

### 桥接模式

问题域：

- 抽象和实现独立变化
- 类层次爆炸（exploding class inheritance）

设计原理：

- 组合优于继承（Prefer composition over inheritance）

- HAS-A 比 IS-A 要好
  - 低耦合
  - 运行期改变行为

主要的思想：

- 由原先的继承关系，一层层的，往下继承的过程中，越往下会导致类内的成员更加复杂，此时我们可以使用桥接模式，对原先的类进行组合配置，使用一个抽象引用，可以更加灵活的实现子类，在桥的两边都可以很灵活的继承父类进行扩展，而且原先的代码结构也不需要改变，使用两层结构，解决类爆炸问题。

桥接器模式：

- 将抽象和实现解耦，让两边都能独立变化
- 适用
  - 跨平台支持
  - 插件plugin
  - 驱动器程序Driver

关系图

![1527469537001](source/images/1527469537001.png)

关键要素：

将抽象和实现进行解耦，使两边的变化相互不影响

**Abstraction**：定义高层操作，适用Implementor提供的原子操作，提供给客户端适用的抽象，引用Implementor，客户适用该抽象，不需要知道实现者细节。

**RefinedAbstraction**：该类实现Abstraction定义的功能，将实现细节代理到ConcreteImplementor

**Implementor**：实现者接口，一般提供原子primitive操作

**ConCreteImplementor**：具体的实现者

代码抽象发生变化不影响原先的实现。达到abstract 抽象层 和 Implementor 实现层任何一方改变，不会影响到其他结构。

#### 好处：

- 抽象和实现解耦
  - 编译或运行时绑定
- 减少子类数量
- 代码简洁
- 接口和实现可以独立变化
- 易于扩展
- 客户端代码低耦合
  - 依赖于抽象，而非具体实现

#### 应用：

Log日志

#### 桥接模式、适配器模式、策略模式的结构类似，他们之间的差异？

共同点：

- 桥接和适配器都是让两个东西配合工作

不同点：

##### 出发点不同：

- 适配器：改变已有的两个接口，让他们相容
- 桥接模式：分离抽象化和实现，是两者的接口可以不同，目的是分离

如果你拿到两个已有的模块，想让他们同时工作，那么你使用的是适配器模式

如果你什么都还没有，但是想分开实现，那么桥接是一个选择。



桥接是先有桥，才有两端的东西

适配是先有两边的东西，才有适配器



桥接是在桥后之后，两边的东西还是可以变化的。

最容易理解的就是：Java中的JRE环境，它本身就是一个很好的桥，先写好在linux上执行的JRE，再写好可以WIndows下执行的JRE，这样无论什么样的程序，主要配合响应的JRE就可以在Linux和Windows上运行。

两个JRE并没有限定你写什么样的程序，但是有个要求是你必须使用Java来写。