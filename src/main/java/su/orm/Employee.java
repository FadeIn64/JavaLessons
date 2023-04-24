package su.orm;


import lombok.ToString;

@ToString
@Table("Employees")
public class Employee {

    @Column
    Integer id;

    @Column
    String name;

    @Column
    Integer department;
}
