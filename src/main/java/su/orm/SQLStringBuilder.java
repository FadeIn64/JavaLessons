package su.orm;

public interface SQLStringBuilder {

    String extractQuery(Class<?> clazz);

    default String updateQuery(Class<?> clazz){
        throw new UnsupportedOperationException();
    }

}
