package com.lili.sds.config;


import com.lili.sds.interceptor.LoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
// Spring内部的一种配置方式
//采用JavaBean的形式来代替传统的xml配置文件形式进行针对框架个性化定制
//@EnableWebMvc   不要接管SpringMVC
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {



//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//       // super.addViewControllers(registry);
//    }

    //所有的WebMvcConfigurerAdapter组件都会一起起作用
    @Bean //将组件注册在容器
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            @Override
            // 添加视图跳转控制器，跳转指定页面
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/admmain.html").setViewName("/adm/adminindex");
                registry.addViewController("/stumain.html").setViewName("/stu/stuindex");
                registry.addViewController("/foreignmain.html").setViewName("/foreign/foreignindex");
                registry.addViewController("/stuadmin.html").setViewName("/adm/stulist");
                registry.addViewController("/addstudent.html").setViewName("/adm/addstu");
            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                super.addInterceptors(registry);
//                静态资源；  *.css , *.js
//                SpringBoot已经做好了静态资源映射
                /* mylabel：1 */
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/index.html","/","/stu/login","/adm/login");
            }
        };
        return adapter;
    }
}
