package su.orm;


import lombok.ToString;

@ToString
@Table("Employees")
public class Employee {

    @Entity
    Integer id;

    @Entity
    String name;

    @Entity
    Integer department;
}
