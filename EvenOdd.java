package JavaInterThreadCommunication;

public class EvenOdd {
    public static void main(String[] args){
        PrintNumber pn = new PrintNumber();
        int MAX = 10;
        Thread t1 = new Thread(new FindEvenOdd(pn, false, MAX),"Odd");
        Thread t2 = new Thread(new FindEvenOdd(pn, true, MAX),"Even");
        t1.start();
        t2.start();
    }
}

class PrintNumber {
    boolean isOdd;
    public synchronized void printEven(int num){
      while(!isOdd){
         try{wait();}catch(InterruptedException e){}
      }
      System.out.println("Even Number: "+num); 
      isOdd = false;
      notify();
    }
    
    public synchronized void printOdd(int num){
        while(isOdd){
            try{wait();}catch(InterruptedException e){}
            }
            System.out.println("Odd Number: " +num);  
            isOdd = true;
            notify();
    }
}

class FindEvenOdd implements Runnable {
    private final PrintNumber p;
    private final boolean isEvenNumber;
    private int MAX;
    
    FindEvenOdd(PrintNumber p, boolean b, int m){
        this.isEvenNumber = b;
        this.p = p;
        this.MAX = m;
    }
    
    @Override
    public void run(){
         int i = isEvenNumber ? 2 : 1;
         while(i <= MAX){
            if(isEvenNumber){
                p.printEven(i);
                try{Thread.sleep(1000);}catch(InterruptedException e){}
            }
            else{
                p.printOdd(i);
                try{Thread.sleep(1000);}catch(InterruptedException e){}
            }
            i=i+2;
        }
    }  
}



package EvenOdd;

public class Test {
   public static void main(String[] args){
       Operation operation = new Operation();
       
       Even e = new Even(operation);
       Thread thread1 = new Thread(e, "Even");
       thread1.start();
       
       Odd o = new Odd(operation);
       Thread thread2 = new Thread(o, "Odd");
       thread2.start();
   }
}

class Even implements Runnable{
    Operation operation;
    
    Even(Operation o){
       this.operation = o; 
    }
    
    @Override
    public void run(){
        for(int i =2; i<=10; i = i+2){
           operation.printEven(i); 
           try{Thread.sleep(1000);}catch(InterruptedException e){}
        }
    }
}

class Odd implements Runnable{
    Operation operation;
    
    Odd(Operation o){
       this.operation = o; 
    }
    
    @Override
    public void run(){
       for(int i =1; i<=9; i = i+2){
           operation.printOdd(i);
           try{Thread.sleep(1000);}catch(InterruptedException e){}
        } 
    }
}

class Operation{
   
    boolean isValueSet = false;
    
    public synchronized void printEven(int num){
        while(!isValueSet){
            try{wait();}catch(InterruptedException e){}
        }
        System.out.println(Thread.currentThread().getName() + " is putting " +num);
        isValueSet = false;
        notify();
    }
    
    public synchronized void printOdd(int num){
        while(isValueSet){
            try{wait();}catch(InterruptedException e){}
        }
        System.out.println(Thread.currentThread().getName() + " is putting " +num);
        isValueSet = true;
        notify();
    }
}
