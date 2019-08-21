package io.nuls.dapp.communitygovernance.intercepter;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description:
 * Author: zsj
 * Date:  2017/12/18 0018
 */
@Controller
public class HandlerLoginInterceptor implements HandlerInterceptor{
    /**
     * 登陆过滤
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*boolean flag =true;
        InchainUser user=(InchainUser)request.getSession().getAttribute("user");
        if(null==user){
            response.sendRedirect("toLogin");
            flag = false;
        }else{
            flag = true;
        }
        return flag;*/
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        /*if(response.getStatus()==404){
            response.sendRedirect("/");
        }*/
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
