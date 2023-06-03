package org.rpcframework.myRPCVersion1.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Dongyu Lv
 * @create 2023-05-27 00:57
 */
@Data
@Builder
public class RPCResponse implements Serializable {
    // 状态信息
    private int code;
    private String message;
    // 数据
    private Object data;

    public static RPCResponse success(Object data) {
        return RPCResponse.builder().code(200).data(data).build();
    }

    public static RPCResponse fail() {
        return RPCResponse.builder().code(500).message("服务器发生错误！").build();
    }


}
