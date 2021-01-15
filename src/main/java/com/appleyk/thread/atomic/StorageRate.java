package com.appleyk.thread.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class StorageRate {

    private AtomicLong writeSize = new AtomicLong(0L);
//    private Long writeSize = 0L;

    public StorageRate() {
    }

    public void write(long length) {
        writeSize.addAndGet(length);
//        writeSize+=length;
    }

    public AtomicLong getWriteSize() {
        return writeSize;
    }

    public void setWriteSize(AtomicLong writeSize) {
        this.writeSize = writeSize;
    }


//    public Long getWriteSize() {
//        return writeSize;
//    }
//
//    public void setWriteSize(Long writeSize) {
//        this.writeSize = writeSize;
//    }

    public static void main(String[] args) throws Exception{
        StorageRate storageRate = new StorageRate();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i <1000 ; i++) {
            threads.add( new Thread(()->storageRate.write(2L)));
        }
        for (Thread t : threads) {
           t.start();
        }
        Thread.sleep(3000);
        System.out.println(storageRate.getWriteSize());
    }

}
