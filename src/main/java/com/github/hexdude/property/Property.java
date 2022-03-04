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
     * Length of Worker ID in generated 64 bit ID.<br>
     * Worker ID在64位ID中所占位数
     */
    private static final long WORKER_ID_LENGTH = 5L;

    /**
     * The maximum decimal value of Worker ID.<br>
     * Worker ID最大值
     */
    @SuppressWarnings("PointlessBitwiseExpression")
    private static final long WORKER_ID_MAXIMUM = -1L ^ (-1L << WORKER_ID_LENGTH);

    /**
     * Length of Data-Center ID in generated 64 bit ID.<br>
     * Data-Center ID在64位ID中所占位数
     */
    private static final long DATA_CENTER_ID_LENGTH = 5L;

    /**
     * The maximum decimal value of Data-Center ID.<br>
     * Data-Center ID最大值
     */
    @SuppressWarnings({"PointlessBitwiseExpression", "unused"})
    private static final long DATA_CENTER_ID_MAXIMUM = -1L ^ (-1L << DATA_CENTER_ID_LENGTH);

    /**
     * Worker ID is the only two of the customize attributes in Snowflake strategy.<br>
     * It is deeply influences the final generated value.<br>
     * Worker ID是唯二可以配置的属性
     */
    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    private Long workerID = 1L;

    /**
     * Data-Center ID is the only two of the customize attributes in Snowflake strategy.<br>
     * It is deeply influences the final generated value.<br>
     * Data-Center ID是唯二可以配置的属性
     */
    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    private Long dataCenterID = 1L;

    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    public Long getWorkerID() {
        return workerID;
    }

    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    public void setWorkerID(Long workerID) {
        checkRange(workerID);
        this.workerID = workerID;
    }

    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    public Long getDataCenterID() {
        return dataCenterID;
    }

    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    public void setDataCenterID(Long dataCenterID) {
        checkRange(dataCenterID);
        this.dataCenterID = dataCenterID;
    }

    /**
     * Check if the param over {@link #WORKER_ID_LENGTH} or {@link #DATA_CENTER_ID_LENGTH}.<br>
     * @param ID Long
     * @throws IllegalArgumentException If param is not fit the range.
     */
    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    protected void checkRange(Long ID) throws IllegalArgumentException  {
        if (ID < 0 || ID > WORKER_ID_MAXIMUM) {
            throw new IllegalArgumentException("Snowflake init failure,Worker ID or Data-Center ID may over range!");
        }
    }
}
