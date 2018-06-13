package com.myself.common;


import com.myself.exception.ParamException;
import com.myself.exception.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HandlerExceptionResolver接口中定义了一个resolveException方法，处理全局异常。
 * Exception ex参数即抛出的异常。返回值类型是ModelAndView，可以通过这个返回值来设置异常时显示的页面。
 *
 * 如果这个bean被spring管理，那么当http进行返回的时候，会被捕捉
 * 实现全局的异常处理逻辑
 */
@Slf4j
public class SpringExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //当前返回的url
        String url = request.getRequestURL().toString();
        ModelAndView mv;
        String defaultMsg = "System error";

        // 这里我们要求项目中所有请求json数据，都使用.json结尾
        if (url.endsWith(".json")) {
            if (ex instanceof PermissionException || ex instanceof ParamException) {
                JsonData result = JsonData.fail(ex.getMessage());
                mv = new ModelAndView("jsonView", result.toMap());
            } else {
                log.error("unknown json exception, url:" + url, ex);
                JsonData result = JsonData.fail(defaultMsg);
                mv = new ModelAndView("jsonView", result.toMap());
            }
        } else if (url.endsWith(".page")){ // 这里我们要求项目中所有请求page页面，都使用.page结尾
            log.error("unknown page exception, url:" + url, ex);
            JsonData result = JsonData.fail(defaultMsg);
            mv = new ModelAndView("exception", result.toMap());
        } else {
            log.error("unknow exception, url:" + url, ex);
            JsonData result = JsonData.fail(defaultMsg);
            mv = new ModelAndView("jsonView", result.toMap());
        }

        return mv;
    }
}
