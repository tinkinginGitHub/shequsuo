package cn.anyoufang.annotation;

import java.lang.annotation.*;

/**
 *
 * 清除过期缓存注解，放置于update delete insert 类型逻辑之上
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RedisEvict {
	Class type();
}
