package com.senla.library;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
