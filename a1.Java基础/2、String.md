## String 字符串

### 面试题

#### 1、String、StringBuffer、StringBuilder的区别是什么？

- String是不可变的，每次String创建一个对象都会创建一个新的String对象，因此频繁修改字符串的情况下，会造成巨大的内存开销。
- StringBuffer是可变的（Unicode序列），可以在原有的基础上进行修改，避免创建新的对象。是线程安全的
- StringBuilder可变的（Unicode序列），可以在原有的基础上进行修改，避免创建新的对象。在并发情况下是不安全的

#### 2、什么是线程安全？
每一个方法都是同步的，在执行方法的时候会对对象进行加锁操作，保证同一时间只有一个线程可以访问该对象，避免多个线程同时
修改一个对象的情况，从而保证线程的安全性

#### 3、String类能被继承吗？

不能，
- 1、String是一个被final修饰的类，final修饰的类是不可以被继承的。
- 2、效率，禁止被继承，就相当于方法禁止重写可以提高效率
- 3、安全性，String调用了很多底层方法，调用了操作系统的api，如果方法可以被重写，可能会被植入恶意代码，影响程序。

#### 4、String s = "asdf"和new String("asdf")有什么区别呢？
- 1、String s = "asdf"在堆中生成1个”asdf”对象，并将”asdf”对象的引用值0x01存储在字符串常量池的StringTable中，最后将引用值0x01赋值给变量str1。
- 2、String s = new String("asdf"); 会在堆中创建2个"asdf"对象，一个将"asdf"对象的引用存储在字符串常量池的StringTable中，一个将"asdf"对象的引用赋值给s。

#### 5、
