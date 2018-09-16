package javaBigWork;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Computer_room_chargeGUI extends JFrame implements ActionListener {
	JLabel jlb1, jlb2, jlb3 ,jlb4,jlb5,jlb6,jlb7;
	JButton jb1, jb2;
	JPanel jp1, jp2, jp3,jp4,jp5,jp6;
	JTextField jtf1, jtf2, jtf3,jtf4,jtf5,jtf6,jtf7;
	
	static int count = 0;
	static BigDecimal balance;
	 
	public Computer_room_chargeGUI(String s) {
		jlb1 = new JLabel("余额");
		jlb2 = new JLabel("状态");
		jlb3 = new JLabel("开始时间");
		jlb4 = new JLabel("上机时间");
		jlb5 = new JLabel("姓名");
		jlb6 = new JLabel("班级");
		jlb7 = new JLabel("学号");
		
		
		 jp1=new JPanel();  
         jp2=new JPanel();  
         jp3=new JPanel();  
         jp4=new JPanel();  
         jp5=new JPanel();  
         jp6=new JPanel();  
         
         jb1 =  new JButton("登记");
         jb2 =  new JButton("退出");
         
         jtf1 = new JTextField(10);
         jtf2 = new JTextField(10);
         jtf3 = new JTextField(12);
         jtf4 = new JTextField(10);
         jtf5 = new JTextField(10);
         jtf6 = new JTextField(10);
         jtf7 = new JTextField(10);
         
		jp1.add(jlb1);
		jp1.add(jtf1);
		jp1.add(jlb2);
		jp1.add(jtf2);
		
		jp2.add(jlb5);
		jp2.add(jtf5);
		
		jp3.add(jlb6);
		jp3.add(jtf6);
		
		jp4.add(jlb7);
		jp4.add(jtf7);
		
		jp5.add(jb1);
		jp5.add(jb2);
		
		jp6.add(jlb3);
		jp6.add(jtf3);
		jp6.add(jlb4);
		jp6.add(jtf4);
		
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		
		this.add(jp1);  
        this.add(jp2);  
        this.add(jp3); 
        this.add(jp4); 
        this.add(jp5); 
        this.add(jp6); 
        
        
      //设置布局管理器  
        this.setLayout(new GridLayout(6,1));  
        //给窗口设置标题  
        this.setTitle("机房上机系统");  
        //设置窗体大小  
        this.setSize(400,400);  
        //设置窗体初始位置  
        this.setLocation(200, 150);  
        //设置当关闭窗口时，保证JVM也退出  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        //显示窗体  
        this.setVisible(true);  
        this.setResizable(true);  
		
        ConnectorTest1.InquireBalance(jtf1,s);
        ConnectorTest1.showState(jtf2, s);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="登记") {
			//DecimalFormat dfl = new DecimalFormat( "0.00"); //设置double类型小数点后位数格式
			String s = "正常";
			double balance = Double.parseDouble(jtf1.getText());
			//展示用户信息
			ConnectorTest1.showMess(jtf5,jtf6,jtf7);
			if(balance>3 && s.equals(jtf2.getText())){
				JOptionPane.showMessageDialog(null,"登记成功！","提示消息",JOptionPane.WARNING_MESSAGE);
				Date time1=new Date();    
				SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss"); 
				SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd ");
				String date = df1.format(time1);
				String time = df.format(time1);
				jtf3.setText(time);		
				//添加上机记录
				ConnectorTest1.addOnmachineRecording(date,time);
				//登陆时间计数器
				Counter.AcountThread(jtf1,jtf4);		
			}
			else {
				JOptionPane.showMessageDialog(null,"无法登记！","提示消息",JOptionPane.WARNING_MESSAGE);
				   System.exit(0);  
			}	
		}
		else if(e.getActionCommand()=="退出")  
        {  
			//修改账号余额
			ConnectorTest1.modifyBalance(jtf1);
			ConnectorTest1.changeRecordingState();
			System.exit(0);   
        }    
		
	}
}
