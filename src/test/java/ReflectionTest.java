import org.junit.Test;

/**
 * @author Dongyu Lv
 * @create 2023-05-27 19:01
 */
public class ReflectionTest {
    @Test
    public void testGetInterfaces() {
        TestClass testClass = new TestClass();
        Class<?>[] interfaces = testClass.getClass().getInterfaces();
        for (Class<?> anInterface : interfaces) {
            System.out.println(anInterface);
        }
    }
}
interface A {}
interface B{}
class TestClass implements A, B {}