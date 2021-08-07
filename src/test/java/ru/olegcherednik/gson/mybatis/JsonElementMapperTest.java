package ru.olegcherednik.gson.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import ru.olegcherednik.gson.mybatis.app.mapper.JsonMapper;

/**
 * @author Oleg Cherednik
 * @since 07.08.2021
 */
@Test
public class JsonElementMapperTest extends AbstractMapperTest {

    @Autowired
    private JsonMapper jsonMapper;

    public void foo() {
        System.out.println("aaa");
    }
}
