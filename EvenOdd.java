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