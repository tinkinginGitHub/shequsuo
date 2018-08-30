package cn.anyoufang.annotation;

import java.lang.annotation.*;

/**
 *
 * 缓存注解类
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RedisCache {
	Class type();//被代理类的全类名，在之后会做为redis hash 的key
}
