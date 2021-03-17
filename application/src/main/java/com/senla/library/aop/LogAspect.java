package com.senla.library.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import static org.apache.logging.log4j.LogManager.getLogger;


/**
 * @author Dmitry Chueshov 23.12.2020 20:32
 *
 * see code objectmapper
 * www.codeflow.site/ru/article/java__how-to-enable-pretty-print-json-output-jackson
 */

@Component
@Aspect
public class LogAspect {
    
    private static final String SPACE = " ";
    
    private ObjectMapper objectMapper;
    
    @AfterThrowing( pointcut = "execution ( public * com.senla.library.controller.*.*(..))", throwing = "exception" )
    public void loggingExceptionControllerLayer(JoinPoint joinPoint, Exception exception) {
    
        Logger logger;
    
        StringBuilder builder = new StringBuilder();
    
        logger = getLogger(joinPoint.getTarget().getClass());
    
        objectMapper = new ObjectMapper();
    
        builder.append(joinPoint.getSignature()).append(SPACE);
        builder.append(joinPoint.getArgs().length).append(SPACE);
        builder.append(joinPoint.getSignature().getName()).append(SPACE);
        builder.append(exception.getMessage()).append(SPACE);
    
        try{
            builder.append(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(joinPoint.getArgs())).append(SPACE);
            logger.error(builder.toString());
        
        } catch(JsonProcessingException e){
            logger.error(e.getMessage());
        }
    }
}
