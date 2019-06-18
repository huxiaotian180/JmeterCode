package jmeter;

/**
 * Created by DELL on 2018/11/20.
 */
public class Math
{
    public String sayHello()
    {
        return "Hello";
    }

    public String sayHelloToPerson(String s)
    {
        if ((s == null) || (s.equals(""))) {
            s = "nobody";
        }
        return "Hello " + s;
    }

    public int sum(int a, int b)
    {

        return a+b;
    }

}

