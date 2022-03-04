package com.github.hexdude.consant;

/**
 * @author HEXDude
 * @date 3/4/22
 * @description  Start time is A essential needs for the strategy.<br>
 * Considering this strategy may be used in Distributed systems or Micro-service.<br>
 * If start time in separate system is different might cause fuzzy in chasing origin.<br>
 * And also succession ID will be convenient to make index on table.<br>
 * Thus I had determined to make it final in source code.
 * @version beta-0.1
 */
public enum Constant {

    /**
     * Snowflake algorithm start time.<br>
     * {@code 2022/02/22 22:22:22}<br>
     * 系统起始时间<br>
     */
    START_TIME(1645539742L);

    private Long timestamp;

    Constant(Long timestamp) {
        this.timestamp = timestamp;
    }

    @SuppressWarnings("unused")
    public Long getTimestamp() {
        return timestamp;
    }

    @SuppressWarnings("unused")
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
