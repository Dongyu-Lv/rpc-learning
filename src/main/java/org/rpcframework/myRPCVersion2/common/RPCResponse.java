package org.rpcframework.myRPCVersion2.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Dongyu Lv
 * @create 2023-05-27 16:10
 */
@Builder
@Data
public class RPCResponse implements Serializable {
    // 状态信息
    private Integer code;
    private String message;
    // 数据
    private Object data;


    public static RPCResponse success(Object data) {
        return RPCResponse.builder().code(200).data(data).build();
    }

    public static RPCResponse fail() {
        return RPCResponse.builder().code(500).message("服务器发生错误").build();
    }

}
