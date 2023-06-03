package org.rpcframework.myRPCVersion2.server;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dongyu Lv
 * @create 2023-05-27 16:41
 * 存放服务接口名与服务端对应的实现类
 * 服务启动时要暴露其相关的实现类
 * 根据request中的interface调用服务端中相关实现类
 */
public class ServiceProvider {
    private Map<String, Object> interfaceProvider;

    public ServiceProvider() {
        interfaceProvider = new HashMap<>();
    }

    public void provideServiceInterface(Object service) {
        Class<?>[] interfaces = service.getClass().getInterfaces();

        for (Class clazz : interfaces) {
            interfaceProvider.put(clazz.getName(), service);
        }
    }

    public Object getService(String interfaceName) {
        return interfaceProvider.get(interfaceName);
    }
}
