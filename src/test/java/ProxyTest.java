import org.junit.Test;
import org.rpcframework.myRPCVersion1.proxytest.JDKProxyFactory;
import org.rpcframework.myRPCVersion1.proxytest.SmsService;
import org.rpcframework.myRPCVersion1.proxytest.SmsServiceImpl;

/**
 * @author Dongyu Lv
 * @create 2023-05-27 00:27
 */
public class ProxyTest {
    @Test
    public void test() {
        // 获取代理对象
        SmsService smsService = (SmsService) JDKProxyFactory.getProxy(new SmsServiceImpl());
        // 通过代理对象调用增强后的方法
        String send = smsService.send("hello, Java");

        System.out.println(send);
    }
}
