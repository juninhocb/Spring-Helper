package com.example.springiotenantapp.tenant;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class HttpTenantResolver implements TenantResolver<HttpServletRequest>{

    @Override
    public String tenantResolver(HttpServletRequest request) {
        return request.getHeader(TENANT_HEADER);
    }
}
