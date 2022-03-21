package com.github.hexdude.generator;

import com.github.hexdude.core.Core;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author HEXDude
 * @date 2022/3/21
 * @description
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
@Component
public class JPAMybatisPlusGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return Core.INSTANCE.generate();
    }
}
