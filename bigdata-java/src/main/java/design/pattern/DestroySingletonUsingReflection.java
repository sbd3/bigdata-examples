package design.pattern;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class DestroySingletonUsingReflection {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Singleton first = Singleton.getInstance();
        Singleton second = null;
        Constructor<?>[] constructors = Singleton.class.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            constructor.setAccessible(true);
            second = (Singleton) constructor.newInstance();
            break;
        }
        System.out.println(first.hashCode());
        System.out.println(second.hashCode());
    }

}
