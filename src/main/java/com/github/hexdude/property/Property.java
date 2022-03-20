package com.github.hexdude.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author HEXDude
 * @date 3/4/22
 * @description Accept the corresponding value from configuration file.
 * @version beta-0.1
 */
@ConfigurationProperties(prefix = "hex.snowflake", ignoreInvalidFields = true)
public class Property {

    /**
     * Defined the binary length of {@link #workerId} occupied in generated 64bit unique ID.<br>
     * 指定{@link #workerId}的二进制数值在生成的64位雪花算法ID中所占的位数。
     */
    private static final long WORKER_ID_LENGTH = 5L;

    /**
     * Defined the binary length of {@link #dataCenterId} occupied in generated 64bit unique ID.<br>
     * 指定{@link #dataCenterId} ID的二进制数值在生成的64位雪花算法ID中所占的位数。
     */
    private static final long DATA_CENTER_ID_LENGTH = 5L;

    /**
     * Defined the maximum acceptable {@link #workerId} AKA {@code 0L~63L}.<br>
     * 指定可接受的最大的{@link #workerId}的值为{@code 0L~63L}。
     */
    @SuppressWarnings({"unused", "PointlessBitwiseExpression"})
    private static final long MAXIMUM_WORKER_ID = -1L ^ (-1L << WORKER_ID_LENGTH);

    /**
     * Defined the maximum acceptable {@link #dataCenterId} AKA {@code 0L~63L}.<br>
     * 指定可接受的最大的{@link #dataCenterId}的值为{@code 0L~63L}。
     */

    @SuppressWarnings({"unused", "PointlessBitwiseExpression"})
    private static final long MAXIMUM_DATA_CENTER_ID = -1L ^ (-1L << DATA_CENTER_ID_LENGTH);

    /**
     * Default value is {@code 1645539742L} AKA {@code 2022/02/22 22:22:22}.<br>
     * Defined the start time of SnowFlake system's generating strategy.<br>
     * Not recommended modifying this attribute.<br>
     * 定义雪花算法生成策略的起始时间。
     */
    private long startTime = 1645539742L;

    /**
     * Default value is {@code 0L}.<br>
     * Defined the current service instance's identifier of the entire microservice system.<br>
     * 定义当前服务在整个微服务系统中的唯一标识。
     */
    private long workerId = 0L;

    /**
     * Default value is {@code 0L}.<br>
     * Defined the current service instance's location of which server's[dataCenter] identifier.<br>
     * 定义当前服务所部署的数据中心的唯一标识。
     */
    private long dataCenterId = 0L;

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(long workerId) {
        this.workerId = workerId;
    }

    public long getDataCenterId() {
        return dataCenterId;
    }

    public void setDataCenterId(long dataCenterId) {
        this.dataCenterId = dataCenterId;
    }
}
