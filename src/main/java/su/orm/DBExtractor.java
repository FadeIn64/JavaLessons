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
    ApplicationContext context;

    @Autowired
    Connection connection;

    @Autowired
    SQLStringBuilder sqlStringBuilder;


    @SneakyThrows
    public <T> List<T> getList(Class<T> clazz){

        List<T> res = new ArrayList<>();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlStringBuilder.extractQuery(clazz));
        ResultSetMetaData metaData = resultSet.getMetaData();

        Map<String, List<Field>> fieldMap = FieldCollector.getAllFields(clazz).stream()
                .filter(x->x.isAnnotationPresent(Column.class))
                .collect(Collectors.groupingBy(x->x.getAnnotation(Column.class).value().equals("")?
                        x.getName().toLowerCase() : x.getAnnotation(Column.class).value().toLowerCase()));

        while (resultSet.next()){
            T obj = clazz.newInstance();
            for(int i = 1; i <= metaData.getColumnCount(); i++){
                Field field = fieldMap.get(metaData.getColumnName(i).toLowerCase()).get(0);
                Convertor c = context.getBean(field.getType().getSimpleName().toLowerCase()+"Convertor", Convertor.class);
                Object val = c.convert(resultSet.getString(i));
                field.setAccessible(true);
                field.set(obj, val);
            }
            res.add(obj);
        }

        System.out.println(fieldMap);

        return res;

    }

}
