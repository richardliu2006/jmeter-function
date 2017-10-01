package com.zuobanshan.jmeter.jmeter_gui;

import java.awt.BorderLayout;
import java.awt.event.ItemListener;

import org.apache.jmeter.samplers.gui.AbstractSamplerGui;
import org.apache.jmeter.testelement.TestElement;

public class OspSamplerGui extends AbstractSamplerGui implements ItemListener {
    private OspConfigGui demoParamConfigGui;

    public OspSamplerGui() {    //构造方法初始化
          init();
      }
  /**
     * @Descrition初始化
    * */
    private void init() {
        setLayout(new BorderLayout(0, 5));
        setBorder(makeBorder());
        add(makeTitlePanel(), BorderLayout.NORTH);            //设置布局

        demoParamConfigGui = new OspConfigGui();   //这里是另外一个面板类，抽出去了，一会单独说
        add(demoParamConfigGui, BorderLayout.CENTER);
    }
    /**
     * @Descrition清除页面数据信息
    * */
     @Override                           
      public void clearGui() {
        super.clearGui();
        demoParamConfigGui.clear();
    }
 /**
     * @Descrition:配置组装元素信息
    * */
     @Override                           
    public void configure(TestElement element) {
        super.configure(element);
        final MySamplerSamplerBase samplerBase = (MySamplerSamplerBase) element;
        safParamConfigGui.configure(element);
    }

   /**
     * @Descrition:创建新的元素
    * */
    @Override
    public TestElement createTestElement() {
        MySamplerSamplerBase sampler = new MySamplerSamplerBase();
        modifyTestElement(sampler);
        return sampler;
    }
    /**
     * @Descrition:清除旧的组件，从新创建
    * */
    @Override
    public void modifyTestElement(TestElement sampler) {
        sampler.clear();
        safParamConfigGui.modifyTestElement(sampler);
        this.configureTestElement(sampler);
    }
   /**
     * @Descrition:label信息，也就是创建HTTP请求、JAVA请求
    * */
    @Override
    public String getLabelResource() {
        return "mySampler_title"; 
    }
}