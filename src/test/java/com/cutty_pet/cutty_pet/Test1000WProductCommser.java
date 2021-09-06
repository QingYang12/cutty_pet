package com.cutty_pet.cutty_pet;

import java.util.concurrent.atomic.AtomicInteger;


public class Test1000WProductCommser {

    static volatile AtomicInteger s=new AtomicInteger(0);
    public static void main(String[] args) {

        new Thread(()->{
            while(true){
                if(s.get()<=500000){
                    Test1000WProductCommser.product();
                }
            }
        }).start();


    }

    public static  void  product(){
        int i=0;
        while(i<500000){
            i++;
            Test1000WProductCommser.s.getAndAdd(1);
            System.out.println("S++");
            System.out.println(Test1000WProductCommser.s);
            send();
        }
    }
    public static void send(){


    }
}
