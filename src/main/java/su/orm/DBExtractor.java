package su.orm;


import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

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

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlStringBuilder.extractQuery(clazz));

        return convertor.convert(resultSet, clazz);

    }

}
