package coriger.chapt3.x1;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by coriger on 2016/2/29.
 */
public class UnSafeTest {

    public static Unsafe unsafe;

    static{
        try {
            unsafe = UnSafeTest.getUnsafeInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){

        Person person;
        // 对象实例化
        try {
            person = (Person) unsafe.allocateInstance(Person.class);
            person.setAge(100);

            long ageOffset = unsafe.objectFieldOffset
                    (Person.class.getDeclaredField("age"));
            System.out.println(ageOffset);
            // 获取字段值
            System.out.println("age is "+unsafe.getInt(person,ageOffset));

            // 修改字段值
            unsafe.putInt(person,ageOffset,50);
            System.out.println("age is "+unsafe.getInt(person,ageOffset));

            // CAS 如果age是50 则修改成80
            System.out.println(unsafe.compareAndSwapInt(person,ageOffset,50,80));
            System.out.println("age is "+unsafe.getInt(person,ageOffset));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Unsafe getUnsafeInstance() throws Exception
    {
        // 通过反射获取rt.jar下的Unsafe类
        Field theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafeInstance.setAccessible(true);
        // return (Unsafe) theUnsafeInstance.get(null);是等价的
        return (Unsafe) theUnsafeInstance.get(Unsafe.class);
    }


}
