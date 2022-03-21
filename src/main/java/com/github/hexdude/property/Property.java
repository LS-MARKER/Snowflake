package com.github.hexdude.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author HEXDude
 * @date 3/4/22
 * @description Accept the corresponding value from configuration file.
 * @version beta-0.1
 */
@ConfigurationProperties(prefix = "hex.snowflake")
public class Property {

    /**
     * <p>在生成的ID的64位二进制数值中，{@link #sequence}的二进制数值占{@code 12}位。</p>
     */
    private static final long SEQUENCE_LENGTH = 12L;

    /**
     * <p>{@link #sequence}的最大可接受十进制数值为{@code 4095}。</p>
     * <p>超过该数值，会使得转换的对应的二进制数值产生溢出，超过{@link #SEQUENCE_LENGTH}所定义的长度。</p>
     */
    @SuppressWarnings("PointlessBitwiseExpression")
    private static final long MAXIMUM_SEQUENCE = -1L ^ (-1L << SEQUENCE_LENGTH);

    /**
     * <p>在生成的ID的64位二进制数值中，{@link #sequence}的的左偏移量为{@code 0}位。</p>
     */
    private static final long SEQUENCE_LEFT_SHIFT_LENGTH = 0L;

    /**
     * <p>在生成的ID的64位二进制数值中，{@link #workerId}的二进制数值占{@code 5}位。</p>
     */
    private static final long WORKER_ID_LENGTH = 5L;

    /**
     * <p>{@link #workerId}的最大可接受十进制数值为{@code 31}。</p>
     * <p>超过该数值，会使得转换的对应的二进制数值产生溢出，超过{@link #WORKER_ID_LENGTH}所定义的长度。</p>
     */
    @SuppressWarnings("PointlessBitwiseExpression")
    private static final long MAXIMUM_WORKER_ID = -1L ^ (-1L << WORKER_ID_LENGTH);

    /**
     * <p>在生成的ID的64位二进制数值中，{@link #workerId}的的左偏移量为{@code 12}位。</p>
     */
    private static final long WORKER_ID_LEFT_SHIFT_LENGTH = SEQUENCE_LENGTH;

    /**
     * <p>在生成的ID的64位二进制数值中，{@link #dataCenterId}的二进制数值占{@code 5}位。</p>
     */
    private static final long DATA_CENTER_ID_LENGTH = 5L;

    /**
     * <p>{@link #dataCenterId}的最大可接受十进制数值为{@code 31}。</p>
     * <p>超过该数值，会使得转换的对应的二进制数值产生溢出，超过{@link #DATA_CENTER_ID_LENGTH}所定义的长度。</p>
     */
    @SuppressWarnings("PointlessBitwiseExpression")
    private static final long MAXIMUM_DATA_CENTER_ID = -1L ^ (-1L << DATA_CENTER_ID_LENGTH);

    /**
     * <p>在生成的ID的64位二进制数值中，{@link #dataCenterId}的的左偏移量为{@code 17}位。</p>
     */
    private static final long DATA_CENTER_ID_LEFT_SHIFT_LENGTH = SEQUENCE_LENGTH + WORKER_ID_LENGTH;

    /**
     * <p>在生成的ID的64位二进制数值中，{@link #timeStamp}的的左偏移量为{@code 22}位。</p>
     */
    private static final long TIME_STAMP_LEFT_SHIFT_LENGTH = SEQUENCE_LENGTH + WORKER_ID_LENGTH + DATA_CENTER_ID_LENGTH;

    /**
     * <p>标识在同一时间内生成的ID。</p>
     */
    private long sequence = 0L;

    /**
     * <p>当前服务在整个系统里唯一标识。</p>
     */
    private long workerId = 0L;

    /**
     * <p>当前服务所在的服务器或数据中心标识。</p>
     */
    private long dataCenterId = 0L;

    /**
     * <p>雪花算法时间戳部分基数，生成的ID会使用生成时间减去该时间作为时间部分。</p>
     */
    private long timeStamp = 1645539742000L;

    /**
     * <p>雪花算法上一次成功生成一个ID的时间戳。</p>
     */
    private long lastTimeStamp = -1L;

    public long getSequence() {
        return sequence;
    }

    public void setSequence(long sequence) {
        this.sequence = sequence;
    }

    public long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(long workerId) {
        checkRange(workerId);
        this.workerId = workerId;
    }

    public long getDataCenterId() {
        return dataCenterId;
    }

    public void setDataCenterId(long dataCenterId) {
        checkRange(dataCenterId);
        this.dataCenterId = dataCenterId;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public long getLastTimeStamp() {
        return lastTimeStamp;
    }

    public void setLastTimeStamp(long lastTimeStamp) {
        this.lastTimeStamp = lastTimeStamp;
    }

    public long getMaximumSequence() {
        return MAXIMUM_SEQUENCE;
    }

    public long getSequenceLeftShiftLength() {
        return SEQUENCE_LEFT_SHIFT_LENGTH;
    }

    public long getWorkerIdLeftShiftLength() {
        return WORKER_ID_LEFT_SHIFT_LENGTH;
    }

    public long getDataCenterIdLeftShiftLength() {
        return DATA_CENTER_ID_LEFT_SHIFT_LENGTH;
    }

    public long getTimeStampLeftShiftLength() {
        return TIME_STAMP_LEFT_SHIFT_LENGTH;
    }

    /**
     * <p>检查配置文件中的{@link #workerId}或{@link #dataCenterId}的合法性。</p>
     * <p>Valid the {@link #workerId} or {@link #dataCenterId} value from configuration file while init.</p>
     * @param param Long WorkerID或DataCenterID
     * @throws IllegalArgumentException 参数不合法异常
     */
    protected void checkRange(Long param) {
        if (param < 0 || param >= MAXIMUM_WORKER_ID) {
            throw new IllegalArgumentException("Consider define your Worker ID or Data-Center ID in duration of " +
                    "0L~31L to avoid this error.");
        }
    }
}
