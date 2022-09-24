package com.bjpowernode.mvc.servlet;

import com.bjpowernode.mvc.context.MyAnnotationWebConfigApplicationContext;
import com.bjpowernode.mvc.handler.MyHandlerMapping;
import com.bjpowernode.mvc.ui.MyModel;
import com.bjpowernode.mvc.ui.MyModelAndView;
import com.bjpowernode.mvc.view.MyInternalResourceViewResolver;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 中央调度器
 *
 */
public class MyDispatcherServlet extends HttpServlet {

    public static final String DEFAULT_SERVLET_NAME = "dispatcherServlet";
    //spring mvc的容器
    private MyAnnotationWebConfigApplicationContext myAnnotationWebConfigApplicationContext;

    //是我们的配置类 WebServletConfig.class
    public Class<?>[] annotatedClasses;

    public MyDispatcherServlet(Class<?>[] annotatedClasses) {
        this.annotatedClasses = annotatedClasses;
    }

    /**
     * tomcat启动，项目加载的入口
     *
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        //classpath:applicationContext.xml
        //String configLocation = config.getInitParameter(CONTEXTCONFIGLOCATION).replace("classpath:", "");

        //初始化spring 容器
        myAnnotationWebConfigApplicationContext = new MyAnnotationWebConfigApplicationContext(annotatedClasses);

        System.out.println("spring web init finished.........................");
    }

    /**
     * 用户访问的入口
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String method = request.getMethod();

        if (method.equals("GET")) {
            doGet(request, response);

        } else if (method.equals("POST")) {
            doPost(request, response);
        }

        //.....doPut， doDelete
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doDispatch(request, response);

    }

    private void doDispatch(HttpServletRequest request, HttpServletResponse response) {

        //1、获取controller
        MyHandlerMapping myHandlerMapping = getMyRequestMapping(request);

        //2、调用controller
        Object object = hand(request, response, myHandlerMapping);

        //3、跳转页面渲染结果
        render(object, request, response);
    }

    /**
     * 获取映射关系
     *
     * @param request
     * @return
     */
    private MyHandlerMapping getMyRequestMapping(HttpServletRequest request) {
        // URI :  /22-spring-mvc/mvc/userInfo
        String uri = request.getRequestURI();
        //项目名称
        String contextPath = request.getContextPath();

        //controller方法的映射url
        String url = uri.replace(contextPath, "");

        //从spring容器中获取已经初始化好的那个映射关系的List集合
        for (MyHandlerMapping myHandlerMapping : myAnnotationWebConfigApplicationContext.getMyBeanFactory().getMyHandlerMappings()) {

            if (!url.equals(myHandlerMapping.getUrl())) {
                continue;
            }
            return myHandlerMapping;
        }

        return null;
     }

    /**
     * 实现对controller的调用
     *
     * @param request
     * @param response
     * @param myHandlerMapping
     * @return
     */
    private Object hand(HttpServletRequest request, HttpServletResponse response, MyHandlerMapping myHandlerMapping) {

        //controller放数据的model
        MyModel myModel = new MyModel();

        //参数类型
        Class[] paramTypes = myHandlerMapping.getMethod().getParameterTypes();

        //参数值
        Object[] paramValue = new Object[paramTypes.length];

        //先把页面请求传过来的参数解析出来
        Map<String, String[]> requestMap = request.getParameterMap();
        for (Map.Entry<String, String[]> requestParam : requestMap.entrySet()) {
            String paramName = requestParam.getKey();
            String paramVal = requestParam.getValue()[0];

            //根据参数名拿到该参数在整个参数列表中是第几个
            int index = myHandlerMapping.getParamIndexMapping().get(paramName);
            paramValue[index] = paramVal;
        }

        //还有Model、request、response
        if (myHandlerMapping.getParamIndexMapping().containsKey(MyModel.class.getName())) {
            int myIndex = myHandlerMapping.getParamIndexMapping().get(MyModel.class.getName());
            paramValue[myIndex] = myModel;
        }

        if (myHandlerMapping.getParamIndexMapping().containsKey(HttpServletRequest.class.getName())) {
            int myIndex = myHandlerMapping.getParamIndexMapping().get(HttpServletRequest.class.getName());
            paramValue[myIndex] = request;
        }

        if (myHandlerMapping.getParamIndexMapping().containsKey(HttpServletResponse.class.getName())) {
            int myIndex = myHandlerMapping.getParamIndexMapping().get(HttpServletResponse.class.getName());
            paramValue[myIndex] = response;
        }

        try {
            //调用真正的controller方法
            Object result = myHandlerMapping.getMethod().invoke(myHandlerMapping.getController(), paramValue);

            myModel.forEach((String key, Object value) -> {
                //把Model中的数据放置到request中，以便于页面能获取到
                request.setAttribute(key, value);
            });

            //contrller方法的返回类型
            Class returnType = myHandlerMapping.getMethod().getReturnType();

            if (result == null) {
                return null;
            }

            //返回MyModelAndView
            if (returnType == MyModelAndView.class) {
                return (MyModelAndView)result;
            }

            //返回jsp TODO
            MyInternalResourceViewResolver viewResolver = new MyInternalResourceViewResolver();
                    //myAnnotationWebConfigApplicationContext.getBean("viewResolver", MyInternalResourceViewResolver.class);

            viewResolver.setPrefix("/");
            viewResolver.setSuffix(".jsp");
            // /userInfo.jsp  或者  /WEB-INF/jsp/userInfo.jsp
            return viewResolver.getPrefix() + result + viewResolver.getSuffix();

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 跳转页面
     *
     * @param viewPath
     * @param request
     * @param response
     */
    private void render(Object viewPath, HttpServletRequest request, HttpServletResponse response) {

        if (viewPath instanceof String) {
            //servlet的分发对象
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(String.valueOf(viewPath));

            try {
                //分发跳转了
                requestDispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}