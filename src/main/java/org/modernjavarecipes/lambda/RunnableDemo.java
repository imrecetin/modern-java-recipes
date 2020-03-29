package org.modernjavarecipes.lambda;

/**
 * Hello world!
 *
 */
public class RunnableDemo
{
    public static void main( String[] args )
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(
                        "inside runnable using an anonymous inner class");
            }
        }).start();

        new Thread(()->System.out.println("inside runnable using an anonymous inner class")).start();

        //Assigning a lambda expression to a variable
        Runnable r=()->System.out.println("inside runnable using an anonymous inner class");
        new Thread(r).start();

    }
}
