package com.senla.library;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.validation.beanvalidation.BeanValidationPostProcessor;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Stack;
import java.util.Vector;

/**
 * @author Dmitry Chueshov 01.03.2021 20:09
 * @project library
 */

@EnableSwagger2
@SpringBootApplication
public class Application{
    
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
        
       
    }
    
}
