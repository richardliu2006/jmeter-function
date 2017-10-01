package com.zuobanshan.jmeter.jmeter_gui;

import java.util.ArrayList;
import java.util.List;

import org.apache.jmeter.config.Argument;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.samplers.AbstractSampler;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.Interruptible;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.testelement.TestIterationListener;
import org.apache.jmeter.testelement.TestStateListener;
import org.apache.jmeter.testelement.ThreadListener;
import org.apache.jmeter.testelement.property.PropertyIterator;
import org.apache.jmeter.testelement.property.TestElementProperty;

public class OspSamplerBase extends AbstractSampler
		implements TestStateListener, TestIterationListener, ThreadListener, Interruptible {
	////////////////////// Code ///////////////////////////

	public OspSamplerBase() {
		setArguments(new Arguments());
	}

	public void setInterfaceName(String newInterfaceName) {
		this.setProperty(InterfaceName, newInterfaceName);
	}

	public String getInterfaceAddress() {
		return getPropertyAsString(InterfaceAddress);
	}

	public String getInterfaceName() {
		return getPropertyAsString(InterfaceName);
	}

	public void setInterfaceGroup(String newInterfaceGroup) {
		this.setProperty(InterfaceGroup, newInterfaceGroup, "");
	}

	public String getInterfaceGroup() {
		return getPropertyAsString(InterfaceGroup, "");
	}

	public String getInterfaceVersion() {
		return getPropertyAsString(InterfaceVersion);
	}

	public String getInterfaceMethod() {
		return getPropertyAsString(InterfaceMethod);
	} // 这些都是从界面获取数据

	/**
	 * @Descrition:设置参数
	 */
	public void setArguments(Arguments value) { // 设置和获取参数
		setProperty(new TestElementProperty(ARGUMENTS, value));
	}

	/**
	 * @Descrition:获取参数
	 */
	public Arguments getArguments() {
		return (Arguments) getProperty(ARGUMENTS).getObjectValue();
	}

	/**
	 * @Descrition:执行sampler这个非常重要了，是执行sampler的地方
	 */
	@Override
	public SampleResult sample(Entry e) {
		return sample();
	}

	private transient MySamplerAbstractImpl impl; // sampler执行类的抽象类，

	/**
	 * @Descrition:执行sampler
	 */
	public SampleResult sample() {
		SampleResult res = null;
		try {
			if (impl == null) { // 这里面可以做工厂模式，处理不同的协议sampler
				impl = new MySamplerTestSampler(this);
				List argumentList = getArgumentList(); // 获取参数数据，默认的HTTP请求就是在这里组装带参数的URL，传入不同的client的
				res = impl.sample(argumentList);
				res.setSampleLabel(getName());
			}
			return res;
		} catch (Exception e) {
			return errorResult(e, new SampleResult());
		}
	}

	/**
	 * @Descrition: 这里面组装表格传过来的数据，传LIST过去 ，这里可以任意自己处理数据格式
	 */
	public List getArgumentList() {
		List<MySamplerArgument> list = new ArrayList();
		PropertyIterator iter = getArguments().iterator();
		while (iter.hasNext()) {
			MySamplerArgument item = null;
			Object objectValue = iter.next().getObjectValue();
			try {
				item = (MySamplerArgument) objectValue;
				list.add(item);
			} catch (ClassCastException e) {
				log.warn("Unexpected argument type: " + objectValue.getClass().getName());
				item = new MySamplerArgument((Argument) objectValue);
			}
		}
		return list;
	}
}