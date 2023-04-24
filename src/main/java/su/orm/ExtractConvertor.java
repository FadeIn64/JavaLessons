package su.orm;

import java.sql.ResultSet;
import java.util.List;

public interface ExtractConvertor {
    <T> List<T> convert(ResultSet resultSet, Class<T> clazz);
}
