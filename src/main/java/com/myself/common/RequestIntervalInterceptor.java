package com.myself.common;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;

/**
 * 反爬策略或者多次请求时间间隔限制
 *
 * @author Created by zion
 * @Date 2018/3/28.
 */
public class RequestIntervalInterceptor implements HandlerInterceptor {
//
//    @Autowired
//    private RedisClient customJedisClient;
//
//    @Autowired
//    private AuthorInfoService authorInfoService;

    private final Boolean openCrawler = true;


    /**
     * 在执行controller处理之前执行，返回值为boolean ,
     * 返回值为true时接着执行postHandle和afterCompletion，如果我们返回false则中断执行
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String uri = httpServletRequest.getRequestURI();
        String lastRequestAddress = uri.substring(uri.lastIndexOf("/") + 1, uri.length());
        if (openCrawler) {
            if (checkInterval(lastRequestAddress)) {
                httpServletResponse.setContentType("application/json;charset=utf-8");
                PrintWriter out = httpServletResponse.getWriter();
                String str = "{\"code\":200,\"data\":{\"flag\":false,\"needValidate\":true,\"message\":\"需要验证码验证!\"}}";
                out.print(str);
                return false;
            }
        }
        return true;
    }


    /**
     * 在执行controller的处理后，在ModelAndView处理前执行
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在DispatchServlet执行完ModelAndView之后执行
     * 这个方法的主要作用是用于清理资源的
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    /**
     * @param lastRequestAddress 请求路径
     * @return
     */
    private boolean checkInterval(String lastRequestAddress) {
        return true;
//        Boolean flag = false;
//        String userId = UserContentUtil.getUserContent().getUserInfo().getUserId();
//        String userKey = "crawler_" + userId;
//        Object object = customJedisClient.get(userKey);
//        JSONObject userOperate = new JSONObject();
//        if (object == null) {
//            userOperate.put("operateTime", new Date());
//            userOperate.put("operateURI", lastRequestAddress);
//        } else {
//            userOperate = JSON.parseObject((String) object);
//            if (userOperate.get("operateURI").equals(lastRequestAddress)) {
//                Date operateTime = userOperate.getDate("operateTime");
//                Integer operateCount = userOperate.getInteger("operateCount");
//                long interval = (new Date().getTime() - operateTime.getTime()) / 1000;
//                if (interval >= 1) {
//                    userOperate.put("operateTime", new Date());
//                    userOperate.put("operateURI", lastRequestAddress);
//                } else {
//                    userOperate.put("operateCount", operateCount + 1);
//                    userOperate.put("operateURI", lastRequestAddress);
//                    flag = true;
//                }
//            }
//            customJedisClient.set(userKey, String.valueOf(userOperate));
//        }
//        return flag;
    }
}
