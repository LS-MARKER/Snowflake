package com.github.hexdude.generator;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.github.hexdude.core.Core;
import org.springframework.stereotype.Component;

/**
 * @author HEXDude
 * @date 2022/3/21
 * @description Customized Primary Key generating strategy for JPA(Hibernate).
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
@Component
public class MybatisPlusGenerator implements IdentifierGenerator {

    @Override
    public Number nextId(Object entity) {
        return Core.INSTANCE.generate();
    }
}
