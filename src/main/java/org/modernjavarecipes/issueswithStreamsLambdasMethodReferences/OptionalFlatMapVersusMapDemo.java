package org.modernjavarecipes.issueswithStreamsLambdasMethodReferences;

import java.util.Optional;

public class OptionalFlatMapVersusMapDemo {
    public static void main(String[] args) {

        //Returning an Optional
        Manager mrSlate = new Manager("Mr. Slate");
        Department d = new Department();
        d.setBoss(mrSlate);
        System.out.println("Boss: " + d.getBoss());
        Department d1 = new Department();
        System.out.println("Boss: " + d1.getBoss());

        //Extract a name from an Optional manager
        System.out.println("Name: " +
                d.getBoss().orElse(new Manager("Unknown")).getName());

        System.out.println("Name: " +
                d1.getBoss().orElse(new Manager("Unknown")).getName());
        System.out.println("Name: " + d.getBoss().map(Manager::getName));
        System.out.println("Name: " + d1.getBoss().map(Manager::getName));

        //An Optional wrapped inside an Optional
        Company co = new Company();
        co.setDepartment(d);
        System.out.println("Company Dept: " + co.getDepartment());
        System.out.println("Company Dept Manager: " + co.getDepartment().map(Department::getBoss));

        //Using flatMap on a company
        System.out.println(co.getDepartment().flatMap(Department::getBoss).map(Manager::getName));

        //Using flatMap on an optional company
        Optional<Company> company = Optional.of(co);
        System.out.println(company
                        .flatMap(Company::getDepartment)
                        .flatMap(Department::getBoss)
                        .map(Manager::getName)
        );

    }

    //A company may have a department (only one, for simplicity)
    public static class Company {
        private Department department;
        public Optional<Department> getDepartment() {
            return Optional.ofNullable(department);
        }
        public void setDepartment(Department department) {
            this.department = department;
        }
    }

    //Part of a DAO layer with Optionals
    public static class Manager {
        private String name;
        public Manager(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }

    public static class Department {
        private Manager boss;
        public Optional<Manager> getBoss() {
            return Optional.ofNullable(boss);
        }
        public void setBoss(Manager boss) {
            this.boss = boss;
        }
    }
}
