package org.modernjavarecipes.theOptionalType;

import java.util.Optional;

public class OptionalInGettersAndSettersDemo {
    public static void main(String[] args) {

    }

    //Using Optional in a DAO layer
    public class Department {
        private Manager boss;
        public Optional<Manager> getBoss() {
            return Optional.ofNullable(boss);
        }
        public void setBoss(Manager boss) {this.boss = boss; }
    }

    public class Manager{}
}
