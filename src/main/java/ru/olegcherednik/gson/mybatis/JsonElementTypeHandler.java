package ru.olegcherednik.gson.mybatis;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonWriter;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Map PostgreSQL JSON type to Gson JsonElement does not return null (empty node instead).
 * Just use JSON string representation as intermediate data format.
 *
 * @see JsonElement
 */
@MappedTypes({JsonElement.class, JsonArray.class, JsonObject.class})
public class JsonElementTypeHandler extends NotNullResultTypeHandler<JsonElement> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, JsonElement parameter, JdbcType jdbcType) throws SQLException {
        try {
            StringWriter sw = new StringWriter();
            JsonWriter jsonWriter = new JsonWriter(sw);
            jsonWriter.setLenient(false);
            Streams.write(parameter, jsonWriter);
            ps.setString(i, sw.toString());
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    @Override
    public JsonElement getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String jsonSource = rs.getString(columnName);
        if (jsonSource != null) {
            return fromString(jsonSource);
        }
        return JsonNull.INSTANCE;
    }

    @Override
    public JsonElement getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String jsonSource = rs.getString(columnIndex);
        if (jsonSource != null) {
            return fromString(jsonSource);
        }
        return JsonNull.INSTANCE;
    }

    @Override
    public JsonElement getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String jsonSource = cs.getString(columnIndex);
        if (jsonSource != null) {
            return fromString(jsonSource);
        }
        return JsonNull.INSTANCE;
    }

    private JsonElement fromString(String source) {
        return source == null ? null : new JsonElementLazyWrapper(source);
    }
}
