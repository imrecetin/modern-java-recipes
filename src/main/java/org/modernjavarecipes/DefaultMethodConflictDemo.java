package org.modernjavarecipes;

public class DefaultMethodConflictDemo {
    public static void main(String[] args) {

    }

    public interface Company {
        default String getName() {
            return "Initech";
        }
        // other methods
    }

    public interface Employee {
        String getFirst();
        String getLast();
        void convertCaffeineToCodeForMoney();
        default String getName() {
            return String.format("%s %s", getFirst(), getLast());
        }
    }

    //Fixed version of CompanyEmployee
    public class CompanyEmployee implements Company, Employee {
        @Override
        public String getName() {
            return String.format("%s working for %s",
                    Employee.super.getName(), Company.super.getName());
        }

        private String first;
        private String last;

        @Override
        public void convertCaffeineToCodeForMoney() {
            System.out.println("Coding...");
        }

        @Override
        public String getFirst() {
            return first;
        }

        @Override
        public String getLast() {
            return last;
        }
    }

    public class CompanyEmployee implements Company, Employee {
        private String first;
        private String last;

        @Override
        public void convertCaffeineToCodeForMoney() {
            System.out.println("Coding...");
        }

        @Override
        public String getFirst() {
            return first;
        }

        @Override
        public String getLast() {
            return last;
        }
    }
}
