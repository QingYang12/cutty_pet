package com.cutty_pet.cutty_pet;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        // Scanner input=new Scanner(System.in);
        // String str=input.next();

        int[] arr={2, 3, 2, 4, 3, 5};
        int start=0;
        int end=arr.length-1;
        int kinit=2;
        int k=arr.length- kinit;
        Test.finder(start,end,k,arr);
        int finded=arr[k];
        System.out.println("hello world "+finded);

    }



    public static void  finder(int start,int end,int k,int[] arr){
        //初始值记录
        int startinit=start;
        int endinit=end;
        int key =0;
        while(start!=end){
            if(arr[start]>arr[end]){
                int temp=arr[start];
                arr[start]=arr[end];
                arr[end]=temp;
                key++;
            }else{
                boolean xkey=key%2==0;
                if(xkey){
                    end--;
                    System.out.println("end---   "+end  );
                }else{
                    start++;
                    System.out.println("start+++  "+start);
                }
            }
        }
        System.out.println("记录arr    "+ Arrays.toString(arr) );
        //终止条件
        if(start==k){
            //return arr[start];
        }else{
            if(start-startinit>0 ){
                finder(startinit,start-1,k,arr);
            }
            if(endinit-start>0){
                finder(start+1,endinit,k,arr);
            }

        }

    }
}
