package su.orm;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import su.reflection.FieldCollector;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class SimpleExtractConvertor implements ExtractConvertor{

    @Autowired
    ApplicationContext context;

    @SneakyThrows
    @Override
    public <T> List<T> convert(ResultSet resultSet, Class<T> clazz) {

        List<T> res = new ArrayList<>();

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

        return res;
    }
}
