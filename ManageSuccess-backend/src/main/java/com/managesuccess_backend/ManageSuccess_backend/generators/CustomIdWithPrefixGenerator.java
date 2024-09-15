package com.managesuccess_backend.ManageSuccess_backend.generators;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.IdentityGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.util.Properties;
import java.util.UUID;

public class CustomIdWithPrefixGenerator implements IdentifierGenerator {

    public static final String CUSTOM_ID_PREFIX_PARAM = "prefixName";
    public static final String CUSTOM_ID_PREFIX_OBJECT = "OBJECT";
    public static final String CUSTOM_ID_PREFIX_ATTACHMENT = "ATTACHMENT";
    public static final String CUSTOM_ID_PREFIX_COMPANY = "COMPANY";
    public static final String CUSTOM_ID_PREFIX_USER = "USER";
    public static final String CUSTOM_ID_PREFIX_TEAM = "TEAM";
    public static final String CUSTOM_ID_PREFIX_USER_EXPERIENCES_OPTION = "USER-EXPERIENCE-OPTION";
    public static final String CUSTOM_ID_PREFIX_PROJECT = "PROJECT";
    public static final String CUSTOM_ID_PREFIX_TASK = "TASK";
    public static final String CUSTOM_ID_PREFIX_COMMENT = "COMMENT";
    private String paramPrefixValue;

    @Override
    public void configure(Type type, Properties parameters, ServiceRegistry serviceRegistry) {
        IdentifierGenerator.super.configure(type, parameters, serviceRegistry);
        paramPrefixValue = parameters.getProperty(CUSTOM_ID_PREFIX_PARAM, CUSTOM_ID_PREFIX_OBJECT);
    }

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {
        return paramPrefixValue + "-" + UUID.randomUUID();
    }
}
