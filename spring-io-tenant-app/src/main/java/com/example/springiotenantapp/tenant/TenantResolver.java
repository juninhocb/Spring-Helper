package com.example.springiotenantapp.tenant;

import org.springframework.lang.NonNull;

public interface TenantResolver<T> {

    String TENANT_HEADER = "X-TenantId";

    String tenantResolver(@NonNull T object);
}
