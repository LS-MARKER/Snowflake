package com.github.hexdude.core;

import com.github.hexdude.property.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.EnumSet;

/**
 * @author HEXDude
 * @date 2022/3/20
 * @description SnowFlake generating strategy implementation.
 */
public enum Core {

    /**
     * Core Algorithm Implementation Instance.
     */
    INSTANCE;

    private Property property;

    public void setProperty(Property property) {
        this.property = property;
    }

    @Component
    public static class CoreInjector {

        @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
        @Autowired
        protected Property property;

        @PostConstruct
        public void postConstruct() {
            for (Core core : EnumSet.allOf(Core.class)) {
                core.setProperty(property);
            }

        }
    }

    public synchronized Long generate() throws InterruptedException {
        long currentTime = System.currentTimeMillis();

        /*
         * 当当前线程的获取的系统时间在上一次生成时间之前，则说明产生了并发错误。
         * If current thread get the system time which is before last time of
         * successfully generated,May means it occurs interrupt exception.
         */
        if (currentTime < property.getLastTimeStamp()) {
            throw new InterruptedException("SnowFlake occurs interrupt execption. Thread -> " + Thread.currentThread().getName());
        }

        /*当当前线程获得的系统时间与上一次成功生成时间一致，则说明在单位时间内，需要对生成的ID进行标识。*/
        if (currentTime == property.getLastTimeStamp()) {
            property.setSequence((property.getSequence() + 1) & property.getMaximumSequence());
            long sequence = property.getSequence();
            /*
            如果在单位时间内序列用完（产生溢出），则阻塞当前线程至获取下一个单位时间。
            If sequence was overflow, Then block current thread till get next valid timestamp.
             */
            if (sequence == 0) {
                currentTime = blockTillNextTime();
            }
        } else {
            /*序列从0开始
            * Sequence starts from 0.
            * */
            property.setSequence(0L);
        }


        /*
        Generates the ID.
         */
        Long id = ((currentTime - property.getTimeStamp()) << property.getTimeStampLeftShiftLength())
                | (property.getDataCenterId() << property.getDataCenterIdLeftShiftLength())
                | (property.getWorkerId() << property.getWorkerIdLeftShiftLength())
                | property.getSequence();

        property.setLastTimeStamp(currentTime);

        return id;
    }

    /**
     * <p>使当前线程获得{@link Property#lastTimeStamp}之后的时间。</p>
     * @return Long
     */
    @SuppressWarnings("JavadocReference")
    private Long blockTillNextTime() {
        long currentTime = System.currentTimeMillis();
        while (currentTime <= property.getLastTimeStamp()) {
            currentTime = System.currentTimeMillis();
        }
        return currentTime;
    }

}
