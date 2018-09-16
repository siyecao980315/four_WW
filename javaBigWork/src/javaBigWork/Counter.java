package javaBigWork;

import java.math.BigDecimal;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Counter {
// static int acount; 
	 public static void AcountThread(JTextField jtf1,JTextField jtf4) {
			 new Thread(new Runnable() {
//	                	 int count = 1;
//	                	 DecimalFormat dfl = new DecimalFormat( "0.00"); //设置double类型小数点后位数格式
	                	 Double balance = Double.parseDouble(jtf1.getText());
	                	 Double number = 0.01;	
	                	 BigDecimal balance1 = new BigDecimal(balance.toString());
	                	 BigDecimal number1 = new BigDecimal(number.toString());
	                     @Override
	                     public void run() {
	                         while(true) {	   	                        	 
	                        	 if(Computer_room_chargeGUI.count % 36 == 0) {
	                        		balance1 = balance1.subtract(number1);
//	         						String s1 = dfl.format(balance);
	         						jtf1.setText(balance1.toString());
	                        		Computer_room_chargeGUI.balance = balance1;	                        		
	                        		//刷新数据	
	                        		ConnectorTest1.refreshRecording();
	                        		//记录某学生的上机时间
	                        		ConnectorTest1.statisticsAllTime(Computer_room_chargeGUI.count);	                 
	                        	 }
	                        	 if(Double.parseDouble(jtf1.getText()) <= 3) {
	             					JOptionPane.showMessageDialog(null,"余额不足！","提示消息",JOptionPane.WARNING_MESSAGE);
	             					System.exit(0);  
	             				}
	                        	 Computer_room_chargeGUI.count++;
	                        	 String time = TimeChange.secToTime(Computer_room_chargeGUI.count);
	                        	 jtf4.setText(time);
	                             try{
	                                 Thread.sleep(1000);
	                             }catch (Exception ex) {
	                                 ex.printStackTrace();
	                             }

	                         }	               
	                     }
	            }).start();
	        // 休眠1秒，等待前面线程累加操作完成
	      
	     }
}
