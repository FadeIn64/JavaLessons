package su.orm;


import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import su.reflection.FieldCollector;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class DBExtractor {

    @Autowired
    ExtractConvertor convertor;

    @Autowired
    Connection connection;

    @Autowired
    SQLStringBuilder sqlStringBuilder;


    @SneakyThrows
    public <T> List<T> getList(Class<T> clazz){

        List<T> res = new ArrayList<>();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlStringBuilder.extractQuery(clazz));

        res = convertor.convert(resultSet, clazz);

        return res;

    }

}
