package org.modernjavarecipes.java9;

public class Employee implements Comparable<Employee> {
    public static Employee DEFAULT_EMPLOYEE=new Employee();
    private String name;
    private Integer id;

    public Employee(Integer i, String name) {
    }

    public Employee() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int compareTo(Employee o) {
        return 0;
    }
}
