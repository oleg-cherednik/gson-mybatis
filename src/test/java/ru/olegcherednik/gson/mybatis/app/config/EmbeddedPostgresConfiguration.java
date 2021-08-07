package ru.olegcherednik.gson.mybatis.app.config;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Collections;

/**
 * @author Oleg Cherednik
 * @since 07.08.2021
 */
@Configuration
public class EmbeddedPostgresConfiguration {

    private DataSource ds;

    @Bean
    public DataSource dataSource() throws IOException {
        EmbeddedPostgres pg = EmbeddedPostgres.builder().start();
        ds = pg.getPostgresDatabase(Collections.singletonMap("currentSchema", "postgres"));
        return ds;
    }

}
