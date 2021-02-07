package com.github.mrag.helloim.common;

public final class SequenceUtil {

    public static long nextLong() {
        return SnowflakeIdWorker.DEFAULT_ID_WORKER.nextId();
    }


    /**
     * 基于官方，按照自己的需求进行定制的雪花算法.<br>
     * 64位主键，其中从左至右：<br>
     * <b>首位置0 - 41位时间戳 - 5位业务号 - 5位机器标识 - 12位计数序列号</b>
     *
     * @author Gmw
     */
    private static class SnowflakeIdWorker {
        public static final SnowflakeIdWorker DEFAULT_ID_WORKER = new SnowflakeIdWorker(0, 0);

        /**
         * 起始时间戳，设置为项目正式上线时间的时间即可
         */
        public static final long twepoch = 1596014494595L;
        /**
         * 业务号长度
         */
        public static final long businessIdBits = 5L;
        /**
         * 机器标识长度
         */
        public static final long workerIdBits = 5L;
        /**
         * 计数序列号长度
         */
        public static final long sequenceBits = 12L;
        /**
         * 最大机器标识
         */
        public static final long maxWorkerId = ~(-1 << workerIdBits);
        /**
         * 最大业务号
         */
        public static final long maxBusinessId = ~(-1 << businessIdBits);
        /**
         * 生成序列掩码
         */
        public static final long sequenceMask = ~(-1 << sequenceBits);
        /**
         * 机器标识向左移位数
         */
        public static final long workerIdShift = sequenceBits;
        /**
         * 业务号向左移位数
         */
        public static final long businessIdShift = sequenceBits + workerIdBits;
        /**
         * 时间戳向左移位数
         */
        public static final long timestampShift = sequenceBits + workerIdBits + businessIdBits;

        /**
         * 当前机器ID
         */
        private final long businessId;
        /**
         * 当前机器ID
         */
        private final long workerId;
        /**
         * 毫秒内序列
         */
        private long sequence;
        /**
         * 上一次生成序列时的时间戳
         */
        private long lastTimestamp;

        /**
         * 提供一个机器标识ID以初始化该生成器
         *
         * @param businessId
         * @param workerId   机器标识
         */
        public SnowflakeIdWorker(long businessId, long workerId) {
            this.businessId = businessId;
            if (workerId < 0 || workerId > maxWorkerId) {
                throw new IllegalArgumentException(
                        String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
            }
            this.workerId = workerId;
            this.sequence = 0L;
            this.lastTimestamp = -1L;
        }

        /**
         * 获取毫秒数
         *
         * @return 系统毫秒数
         */
        private long timeGen() {
            return System.currentTimeMillis();
        }

        /**
         * 系统时钟若回退过则抛异常
         */
        private long checkTimestampMoved(long timestamp) {
            if (timestamp < this.lastTimestamp) {
                throw new RuntimeException(String.format(
                        "Clock moved backwards.  Refusing to generate id for %d milliseconds",
                        lastTimestamp - timestamp));
            }
            return timestamp;
        }

        /**
         * 获取下一毫秒
         *
         * @param lastTimestamp 上一毫秒
         * @return 下一毫秒
         */
        private long tillNextMillis(long lastTimestamp) {
            long timestamp = checkTimestampMoved(timeGen());
            while (timestamp == lastTimestamp) {
                timestamp = timeGen();
            }
            return timestamp;
        }

        public synchronized long nextId() {
            long timestamp = checkTimestampMoved(timeGen());

            checkTimestampMoved(timestamp);

            // 同一毫秒内则递增序列号
            if (timestamp == this.lastTimestamp) {
                sequence = (sequence + 1) & sequenceMask;

                // 在这一毫秒内已经生成了满额的ID，阻塞到下一毫秒
                if (sequence == 0L) {
                    timestamp = tillNextMillis(timestamp);
                }
            }
            // 若是下一毫秒则seq置0
            else {
                sequence = 0L;
            }

            // 更新数据
            this.lastTimestamp = timestamp;

            return ((timestamp - twepoch) << timestampShift)
                    | (businessId << businessIdShift)
                    | (workerId << workerIdShift)
                    | sequence;
        }

        // test
        public static void main(String[] args) {
            SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
            for (int i = 0; i < 1000; i++) {
                long id = idWorker.nextId();
                System.out.println(Long.toBinaryString(id));
                System.out.println(id);
            }
        }
    }

}
