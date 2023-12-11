package util;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class RetryListener implements IAnnotationTransformer {

    public void transform(ITestAnnotation testAnnotation, Class testClass,
                          Constructor testConstructor, Method testMethod){
        Class<? extends IRetryAnalyzer> retry = testAnnotation.getRetryAnalyzerClass();
        if(retry == null){
            testAnnotation.setRetryAnalyzer(FailRetry.class);
        }
    }
}
