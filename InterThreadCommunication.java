package JavaInterThreadCommunication;

public class InterThreadCommunication {
    public static void main(String[] args){
        Operation operation = new Operation();
        Producer producer = new Producer(operation);
        Thread prod = new Thread(producer, "Producer");
        prod.start();
        
        Consumer consumer = new Consumer(operation);
        Thread cons = new Thread(consumer, "Consumer");
        cons.start();    
    }
}

class Producer implements Runnable{
    Operation q;
    public Producer(Operation q) {
        this.q = q;
    }
    @Override
    public void run(){
        int i =0;
        while(i <= 3){
            q.put(i++);
            try{Thread.sleep(1000);}catch(InterruptedException e){}
        }  
    }   
}

class Consumer implements Runnable{
    Operation q;
    public Consumer(Operation q) {
        this.q = q;       
    }
    @Override
    public void run(){
        int i =0;
        while(i <= 3){
            q.get();
            try{Thread.sleep(1000);}catch(InterruptedException e){}
        }  
    }  
}

class Operation{   
    int num;
    boolean isValueSet = false;
    public synchronized void put(int num){
        while(isValueSet){
            try{wait();}catch(InterruptedException e){}
        }
        System.out.println(Thread.currentThread().getName() + " is Putting: " +num);
        this.num = num;
        isValueSet = true;
        notify();
    }
    public synchronized void get(){
        while(!isValueSet){
            try{wait();}catch(InterruptedException e){}
        }
        System.out.println(Thread.currentThread().getName() + " is Getting: " +num);
        isValueSet = false;
        notify();
    }
}