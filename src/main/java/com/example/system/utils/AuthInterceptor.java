package com.example.system.utils;

import com.auth0.jwt.interfaces.Claim;
import com.example.system.domain.Authority;
import com.sun.istack.internal.NotNull;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author VioletMitsuko
 */
public class AuthInterceptor implements HandlerInterceptor {
    /**
     * 返回的code里面
     * 要重新登录是 255
     * 没有权限是250
     */
    @Override
    public boolean preHandle(@NotNull HttpServletRequest request,
                             @NotNull HttpServletResponse response,
                             @NotNull Object handler) throws Exception {
        response.setCharacterEncoding("UTF-8");
        if(!(handler instanceof HandlerMethod)){
            response.getWriter().write(
                    new ResultInfo<String>(200,"放过假请求",null).toString());
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        System.out.println("Method 》》："+method.getName() );
        //检查方法是否跳过验证
        if (method.isAnnotationPresent(Authority.class)){
            Authority loginVerify = method.getAnnotation(Authority.class);
            System.out.println("=====进入验证=====");
            if (loginVerify.required()){
                String token = request.getHeader("token");
                System.out.println("token >> "+token);
                if (token == null){
                    response.getWriter().write(new ResultInfo<String>(255,"您没有携带token",null).toString());
                    return false;
                }else {
                    Map<String, Claim> map;
                    try {
                        map = TokenService.verifierToken(token);
                    }catch (Exception e){
                        response.getWriter().write(
                                new ResultInfo<String>(255,"凭证已过期，请重新登录",null).toString());
                        return false;
                    }

                    List<String> menuName = map.get("menuName").asList(String.class);
                    System.out.println("正在访问的权限是》》："+ Arrays.toString(loginVerify.value()));
                    for (String menuN : menuName) {
                        for (String aut : loginVerify.value()) {
                            if (menuN.equals(aut)){
                                System.out.println("允许访问");
                                return true;
                            }
                        }
                    }
                    response.getWriter().write(
                            new ResultInfo<String>(250,"您没有权限进行该操作","请更换账号").toString());
                    return false;
                }
            }else {
                return true;
            }
        }else {
            response.getWriter().write(new ResultInfo<String>(404,"没有查找到您的服务",null).toString());
            return false;
        }
    }
}
