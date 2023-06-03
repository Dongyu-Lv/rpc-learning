package org.rpcframework.myRPCVersion2.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Dongyu Lv
 * @create 2023-05-27 00:51
 *
 * 服务端会有多个方法，上个例子中值传递一个参数id，无法确定调用哪个方法
 * 因此，RPC请求中，client应该发送需要调用的Service接口名，方法名，参数，参数类型
 * 服务端就能够根据这些信息反射调用相应的方法
 * 本例中仍使用Java自带的序列化方式
 */
@Data
@Builder
public class RPCRequest implements Serializable {
    // 接口名
    private String interfaceName;
    // 方法名
    private String methodName;
    // 参数列表
    private Object[] params;
    // 参数类型
    private Class<?>[] paramsTypes;

}
