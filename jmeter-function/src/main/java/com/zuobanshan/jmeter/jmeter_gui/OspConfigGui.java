package com.zuobanshan.jmeter.jmeter_gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.jmeter.gui.util.VerticalPanel;

public class OspConfigGui extends JPanel{
    public JTextField interfaceName;
    public JTextField interfaceAddress;         
    public JTextField interfaceGroup;
    public JTextField interfaceVersion;
    public JTextField interfaceMethod;      //定义几个页面输入框，这个根据自身需要开发哈，我这个是简单的例子
 /**
     * @Descrition:初始化面板
    * */
 private void init() {
        setLayout(new BorderLayout(0, 5));
        if (displayName) {
            setBorder(makeBorder());
            add(makeTitlePanel(), BorderLayout.NORTH);
        }
        // MAIN PANEL
        VerticalPanel mainPanel = new VerticalPanel();
        JPanel serverPanel = new HorizontalPanel();
        serverPanel.add(createInterfaceAddressPanel(), BorderLayout.CENTER);
        mainPanel.add(serverPanel);
        mainPanel.add(createInterfaceNamePanel());
        mainPanel.add(getInterfaceGroupPanel());
        mainPanel.add(createInterfaceVersionPanel());
        mainPanel.add(createInterfaceMethodPanel());
        mainPanel.add(createJarFilePathPanel());
        add(mainPanel, BorderLayout.CENTER);
    }

 /**
     * @Descrition:创建文本框信息
    * */
private JPanel createInterfaceMethodPanel() {                              //初始化上面文本对象,这里我列举一个例子，剩下都一下就是信息和布局的区别
        interfaceMethod = new JTextField(15);
        JLabel label = new JLabel("调用方法:"); // $NON-NLS-1$
        label.setLabelFor(interfaceMethod);
        JPanel panel = new JPanel(new BorderLayout(5, 0));
        panel.add(label, BorderLayout.WEST);
        panel.add(interfaceMethod, BorderLayout.CENTER);
        return panel;
