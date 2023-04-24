package su.orm;

import org.springframework.stereotype.Component;
import su.reflection.FieldCollector;


@Component
public class SimpleSQLStringBuilder implements SQLStringBuilder{

    private static final String SELECT = "SELECT";
    private static final String FROM = "FROM";

    @Override
    public String extractQuery(Class<?> clazz) {
        if (!clazz.isAnnotationPresent(Table.class)) throw new RuntimeException("Class is not table");

        String fields = FieldCollector.getAllFields(clazz).stream()
                .filter(x->x.isAnnotationPresent(Column.class))
                .map(x->x.getAnnotation(Column.class).value().equals("")?
                        x.getName() : x.getAnnotation(Column.class).value())
                .reduce((x, y) -> x.concat(", " + y))
                .get();


        String tableName = clazz.getAnnotation(Table.class).value().equals("")?
                clazz.getSimpleName() : clazz.getAnnotation(Table.class).value();

        return SELECT + " " + fields + " " + FROM + " " + tableName;
    }
}
