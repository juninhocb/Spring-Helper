package com.example.springiotenantapp.tenant;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Component
@Slf4j
public class TenantInterceptor implements HandlerInterceptor {
    private final HttpTenantResolver httpTenantResolver;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        var tenantId = httpTenantResolver.tenantResolver(request);
        TenantContext.setTenantId(tenantId);
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("Post handle triggered, clearing tenant ");
        this.clear();
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("After completion triggered, clearing tenant ");
        this.clear();
    }
    private void clear(){
        TenantContext.clear();
    }
}
