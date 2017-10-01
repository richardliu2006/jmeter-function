package com.zuobanshan.jmeter.jmeter_gui;

import java.util.List;

import org.apache.jmeter.samplers.SampleResult;

public class OspSampler extends MySamplerAbstractImpl {

@Override
    protected SampleResult sample(List list) {  //因为我定时的是list类型 所以这里面是list
        SampleResult res = new SampleResult();
      SampleResult res = new SampleResult();
        System.out.println("list:"+list.toString());
        try {
            String interfaceAddress = getInterfaceAddress();
            System.out.println("interfaceAddress:"+interfaceAddress);
            String interfaceName = getInterfaceName();
            res.setSamplerData("interfaceAddress:"+interfaceAddress+"interfaceName:"+interfaceName);
            res.sampleStart();
            res.setResponseData("list参数:"+list.toString(),"UTF-8");
        } catch (RuntimeException e) {
            res.setResponseCode("500");
            res.setSuccessful(false);
            res.setResponseMessage(e.toString());
            e.printStackTrace();
            return res;
        }  catch (Exception e) {
            res.setResponseCode("500");
            res.setSuccessful(false);
            res.setResponseMessage(e.toString());
            e.printStackTrace();
            return res;
        } finally {
            res.sampleEnd();
        }
        return res;
  }
}