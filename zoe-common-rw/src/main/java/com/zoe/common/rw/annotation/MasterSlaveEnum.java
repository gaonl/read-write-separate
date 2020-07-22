package com.zoe.common.rw.annotation;

/**
 * @author gaonl
 * @date 2020/07/17
 */
public enum MasterSlaveEnum {

    /**
     * 主库（读）
     */
    MASTER(MasterSlaveEnum.MASTER_NAME),

    /**
     * 从库（写）
     */
    SLAVE(MasterSlaveEnum.SLAVE_NAME);

    public static final String MASTER_NAME = "master";
    public static final String SLAVE_NAME = "slave";

    private String name;

    MasterSlaveEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
