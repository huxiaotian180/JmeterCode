package jmeter;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

//服务为：将输入的两个参数通过IO存入文件；
public class PerformenceTest implements JavaSamplerClient {
    private SampleResult results;
    private String a;
    private String b;
    private String filename;

    // 设置传入的参数，可以设置多个，已设置的参数会显示到Jmeter的参数列表中
    public Arguments getDefaultParameters() {
        Arguments params = new Arguments();
        params.addArgument("filename", "0");// 设置参数，并赋予默认值0
        params.addArgument("a", "0");// 设置参数，并赋予默认值0
        params.addArgument("b", "0");// 设置参数，并赋予默认值0
        return params;
    }

    // 初始化方法，实际运行时每个线程仅执行一次，在测试方法运行前执行
    public void setupTest(JavaSamplerContext arg0) {
        results = new SampleResult();
    }

    // 测试执行的循环体，根据线程数和循环次数的不同可执行多次
    public SampleResult runTest(JavaSamplerContext arg0) {
        b = arg0.getParameter("b"); // 获取在Jmeter中设置的参数值
        a = arg0.getParameter("a"); // 获取在Jmeter中设置的参数值
        filename = arg0.getParameter("filename"); // 获取在Jmeter中设置的参数值
        results.sampleStart();// jmeter 开始统计响应时间标记
        try {
            OutputService test = new OutputService();
            //test.output(filename, Integer.parseInt(a), Integer.parseInt(b));
            test.output(filename,a,b);
            results.setSuccessful(true);
            // 被测对象调用
        } catch (Throwable e) {
            results.setSuccessful(false);
            e.printStackTrace();
        } finally {
            results.sampleEnd();// jmeter 结束统计响应时间标记
        }
        return results;
    }

    // 结束方法，实际运行时每个线程仅执行一次，在测试方法运行结束后执行
    public void teardownTest(JavaSamplerContext arg0) {
    }

    /*
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Arguments params = new Arguments();
        params.addArgument("a", "0");// 设置参数，并赋予默认值0
        params.addArgument("b", "0");// 设置参数，并赋予默认值0
        params.addArgument("filename","abc.txt");
        JavaSamplerContext arg0 = new JavaSamplerContext(params);
        PerformenceTest test = new PerformenceTest();
        test.setupTest(arg0);
        test.runTest(arg0);
        test.teardownTest(arg0);
    }
    */
}