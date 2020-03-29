package org.modernjavarecipes.lambda;

@FunctionalInterface
public interface MyFunctionalInterface {

        int myMethod();
        // int myOtherMethod();
        default String sayHello() {
            return "Hello, World!";
        }
        static void myStaticMethod() {
            System.out.println("I'm a static method in an interface");
        }

        //Extending a functional interface—no longer functional
        public interface MyChildFunctionalInterface extends MyFunctionalInterface{
            int anotherMethod();
        }

}
