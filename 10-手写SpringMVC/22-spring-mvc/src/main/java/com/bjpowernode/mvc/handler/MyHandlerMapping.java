package com.bjpowernode.mvc.handler;

import com.bjpowernode.mvc.annotation.MyRequestParam;
import com.bjpowernode.mvc.ui.MyModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于controller的映射
 *
 */
public class MyHandlerMapping {

    private Object controller;

    private Method method;

    private String url;

    private Map<String, Integer> paramIndexMapping;

    public MyHandlerMapping(Object controller, Method method, String url) {
        this.controller = controller;
        this.method = method;
        this.url = url;
        paramIndexMapping = new HashMap<>();
        putParamIndexMapping(method);
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Integer> getParamIndexMapping() {
        return paramIndexMapping;
    }

    public void setParamIndexMapping(Map<String, Integer> paramIndexMapping) {
        this.paramIndexMapping = paramIndexMapping;
    }

    private void putParamIndexMapping(Method method) {
        Annotation[][] parameter = method.getParameterAnnotations();
        for (int i=0; i<parameter.length; i++) {
            for (Annotation annotation : parameter[i]) {
                if (annotation instanceof MyRequestParam) {
                    String paramName = ((MyRequestParam) annotation).value();
                    if (!"".equals(paramName)) {
                        paramIndexMapping.put(paramName, i);
                    }
                }
            }
        }

        Class[] paramTypes = method.getParameterTypes();
        for (int i=0; i< paramTypes.length; i++) {
            Class type = paramTypes[i];

            if (type == MyModel.class || type == HttpServletRequest.class || type == HttpServletResponse.class) {
                paramIndexMapping.put(type.getName(), i);
            }
        }
    }
}
