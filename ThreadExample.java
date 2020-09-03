package JavaMultiThreading;
public class ThreadExample { 
    public static void main(String[] args) throws Exception{
       // thread creation by implenting runnable interface
       Thread t1 = new Thread(() ->
           {
               for(int i =0; i<3; i++){
                   System.out.println("Hi - Thread 1");
                   try{Thread.sleep(1000);}catch(InterruptedException e){} //just putting sleep to demo if thread 1 takes time thread 2 is joining its job independently
                }
            });
       // thread creation by implenting runnable interface
       Thread t2 = new Thread(() ->
           {
               for(int i =0;i<3;i++){
                   System.out.println("Hello - Thread 1");
                   try{Thread.sleep(1000);}catch(InterruptedException e){}
                }
            });
       t1.start();
       t2.start();
       
       // joinning the thread to make sure main thread waits for these two threads to complete
       // elase bye can be printed before as main thread can complete its task beofre other thread
       t1.join();
       t2.join();
       System.out.println("Bye - Main Thread");
    }  
}
