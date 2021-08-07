package ru.olegcherednik.gson.mybatis;

import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.olegcherednik.gson.mybatis.app.config.DatabaseConfig;
import ru.olegcherednik.gson.mybatis.app.config.EmbeddedPostgresConfiguration;
import ru.olegcherednik.gson.mybatis.app.mapper.JsonMapper;

/**
 * @author Oleg Cherednik
 * @since 07.08.2021
 */
@MybatisTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = { EmbeddedPostgresConfiguration.class, DatabaseConfig.class })
abstract class AbstractMapperTest {

    @Autowired
    protected JsonMapper jsonMapper;

}
