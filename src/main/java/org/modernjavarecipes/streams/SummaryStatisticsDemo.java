package org.modernjavarecipes.streams;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class SummaryStatisticsDemo {

    public static void main(String[] args) {
        //SummaryStatistics
        DoubleSummaryStatistics stats = DoubleStream.generate(Math::random)
                .limit(1_000_000)
                .summaryStatistics();
        System.out.println(stats);
        System.out.println("count: " + stats.getCount());
        System.out.println("min : " + stats.getMin());
        System.out.println("max : " + stats.getMax());
        System.out.println("sum : " + stats.getSum());
        System.out.println("ave : " + stats.getAverage());

        List<Team> teams= Arrays.asList(new Team(),new Team(),new Team());
        DoubleSummaryStatistics teamStats = teams.stream()
                .mapToDouble(Team::getSalary)
                .collect(DoubleSummaryStatistics::new,
                        DoubleSummaryStatistics::accept,
                        DoubleSummaryStatistics::combine);

        //Collect using summarizingDouble
        teamStats = teams.stream().collect(Collectors.summarizingDouble(Team::getSalary));
    }

    //Team class contains id, name, and salary
    public static class Team {
        private static final NumberFormat nf = NumberFormat.getCurrencyInstance();
        private int id;
        private String name;
        private double salary;

        public static NumberFormat getNf() {
            return nf;
        }

        @Override
        public String toString() {
            return "Team{" +
                    "id=" + getId() +
                    ", name='" + getName() + '\'' +
                    ", salary=" + getNf().format(getSalary()) +
                    '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }
    }
}
