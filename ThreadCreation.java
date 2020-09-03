package JavaMultiThreading;

public class ThreadCreation {
    public static void main(String[] args)throws Exception{
        System.out.println("Task 1- Main Thread"); //main thread
        
        Thread t2 = new Task2(); //thread creation by extending thread
        t2.start();
        
        Task3 r = new Task3(); //thread creation by implenting runnable interface
        Thread t3 = new Thread(r);
        t3.start();
        
        Thread t4 = new Thread(() -> System.out.println("Task 4")); //thread creation by implementing runnable interface but inline declaration of run method
        t4.start();

        System.out.println("Task 5 - Main Thread"); //main thread
    }
}
class Task2 extends Thread{
    @Override
    public void run(){
        System.out.println("Task 2");
    }
}

class Task3 implements Runnable{
    @Override
    public void run(){
        System.out.println("Task 3");
    }
}
