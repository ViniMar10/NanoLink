package com.vini.url_shortner_backend.common.config;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;

/**
 * Extends default type resolution to include Records, which are implicitly final
 * and would otherwise be skipped by NON_FINAL typing, causing deserialization
 * failures when retrieving them from Redis.
 */
public class RecordAwareTypeResolver extends ObjectMapper.DefaultTypeResolverBuilder {
    public RecordAwareTypeResolver(ObjectMapper.DefaultTyping typing, PolymorphicTypeValidator pvt) {
        super(typing, pvt);
    }

    @Override
    public boolean useForType(JavaType t){
        if (t.getRawClass().isRecord()){
            return true;
        }
        return super.useForType(t);
    }
}
