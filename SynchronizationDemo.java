package JavaMultiThreading;

public class SynchronizationDemo {
    public static void main(String[] args) throws Exception{
        Count c = new Count();
        Thread t1 = new T1(c,"Thread 1");
        t1.start();
        
        Thread t2 = new T2(c, "Thread 2");
        t2.start();  
        
        t1.join();
        t2.join();
        
        System.out.println("Count: " +c.c);
    }
}
class T1 extends Thread{
    Count c;
    String name;
    T1(Count c, String name){
        this.c = c;
        this.name = name;
    }
    public void run(){
        for(int i =0; i<3; i++){
            c.increment();
            System.out.println(name);
            try{Thread.sleep(1000);}catch(Exception e){}
        }
    }
}
class T2 extends Thread{
    Count c;
    String name;
    T2(Count c, String name){
        this.c = c;
        this.name = name;
    }
    
    public void run(){
        for(int i =0; i<3; i++){
                c.increment();
                System.out.println(name);
                try{Thread.sleep(1000);}catch(Exception e){}
            }
        }
}
class Count {
    int c;
    public synchronized void increment(){
        c++;
    }
}
