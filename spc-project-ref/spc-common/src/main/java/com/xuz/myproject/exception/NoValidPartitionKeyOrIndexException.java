package com.xuz.myproject.exception;

/**
 * 没有有效的分片键或者分片索引异常
 *
 * 没有分片键（索引）或者分片键（索引）的值为空
 *
 * @author daiwl
 */
public class NoValidPartitionKeyOrIndexException extends MyRuntimeException {

    public NoValidPartitionKeyOrIndexException() {
        super();
    }

    public NoValidPartitionKeyOrIndexException(String message) {
        super(message);
    }

    public NoValidPartitionKeyOrIndexException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoValidPartitionKeyOrIndexException(Throwable cause) {
        super(cause);
    }

    public NoValidPartitionKeyOrIndexException(ErrorCodeEnum error, String message) {
        super(error, message);
    }
}
