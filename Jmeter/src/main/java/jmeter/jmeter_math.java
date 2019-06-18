package jmeter;

/**
 * Created by DELL on 2018/11/20.
 */

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class jmeter_math extends AbstractJavaSamplerClient
        /**
         * 初始化jmeter请求和返回参数
         */
        {
            private Math test = null;
            private String a;
            private String b;
            private String resultData;
            private static long start = 0L;
            private static long end = 0L;

    /**
     * 设置传入的参数，可以设置多个，已设置的参数会显示到Jmeter的参数列表中
     *
     * @return
     */
    public Arguments getDefaultParameters() {
        Arguments params = new Arguments();
        params.addArgument("num1", "");
        params.addArgument("num2", "");
        return params;
    }

            /**
             * 初始化方法，实际运行时每个线程仅执行一次，在测试方法运行前执行
             * @param arg0
             */
    public void setupTest(JavaSamplerContext arg0) {
        this.test = new Math();
        this.a = arg0.getParameter("num1");
        this.b = arg0.getParameter("num2");
        start = System.currentTimeMillis();
    }

            /**
             * 线程运行是业务代码
             * @param arg0
             * @return
             */
    public SampleResult runTest(JavaSamplerContext arg0) {
        SampleResult sr = new SampleResult();
        sr.setSamplerData("请求参数num1:" + this.a + "\n" + "请求参数num2:" + this.b);
        try {
            sr.sampleStart();
            this.resultData = String.valueOf(this.test.sum(Integer.parseInt(this.a), Integer.parseInt(this.b)));
            //this.resultData = String.valueOf(this.test.sum(a,b));
            //this.resultData = test.sum(a,b);
            if ((this.resultData != null) && (this.resultData.length() > 0)) {
                sr.setResponseData("结果是:" + this.resultData, null);
                sr.setDataType("text");
                sr.setResponseCode("200");
                sr.setResponseMessage("成功");

            }
            Thread.sleep(10L);

            sr.setSuccessful(true);
        } catch (Throwable e) {
            sr.setSuccessful(false);
            sr.setResponseData("请求非法");
            e.printStackTrace();
        } finally {
            sr.sampleEnd();
        }
        return sr;
    }

            /**
             * 线程结束运行方法
             * @param arg0
             */
    public void teardownTest(JavaSamplerContext arg0) {
        end = System.currentTimeMillis();
        System.err.println("cost time:" + (end - start) + "毫秒");
    }
}
