package com.example.configclient.aspects;

import com.example.configclient.config.DatabaseContextHolder;
import com.example.configclient.interfaces.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class DataSourceAspect {

    @Pointcut("execution(public * com.example.configclient.service.*.*(..))")
    public void pointCut(){}

    @Before("pointCut()")
    public void doBefore(JoinPoint point){
        Class<?>[] interfaces = point.getTarget().getClass().getInterfaces();
        for(Class inter : interfaces){
            if(inter.isAnnotationPresent(DataSource.class)){
                DataSource dataSource = (DataSource) inter.getAnnotation(DataSource.class);
                log.info("选择数据源---" + dataSource.value().getValue());
                DatabaseContextHolder.setDBKey(dataSource.value().getValue());
                break;
            }
        }
    }

    @After("pointCut()")
    public void doAfter(){
        DatabaseContextHolder.clearDBKey();
    }
}
