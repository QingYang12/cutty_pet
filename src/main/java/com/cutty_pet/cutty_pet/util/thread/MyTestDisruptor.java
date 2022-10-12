package com.cutty_pet.cutty_pet.util.thread;

import com.alibaba.fastjson.JSONObject;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;

public class MyTestDisruptor {


    public static void main(String[] args) {
        //指定RingBuffer大小,
        //必须是2的N次方
        int bufferSize = 1024;

        //构建Disruptor
        Disruptor<LongEvent> disruptor
                = new Disruptor<>(
                LongEvent::new,
                bufferSize,
                DaemonThreadFactory.INSTANCE);
        AtomicInteger ait= new AtomicInteger(0);
        //注册事件处理器
        disruptor.handleEventsWith((event, sequence, endOfBatch) -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("E: " + JSONObject.toJSON(event.getValue()));
            ait.getAndAdd(-1);
            System.out.println("thread count end "+ait.get());
        });

        //启动Disruptor
        disruptor.start();

        //获取RingBuffer
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        //生产Event
        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; true; l++) {
            ait.getAndAdd(1);
            System.out.println("thread count start "+ait.get());
            bb.putLong(0, l);
            //生产者生产消息
            ringBuffer.publishEvent(
                    (event, sequence, buffer) ->
                            event.set(buffer.getLong(0)), bb);
            //
        }
    }
}