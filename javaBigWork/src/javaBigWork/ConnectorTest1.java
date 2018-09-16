package javaBigWork;

import java.sql.*;

import javax.swing.*;

public class ConnectorTest1 {
	
	static String s_id;
	static String s_stuNumber;
	static String s_password;
	static int s_balance;
	static String s_name;
	static String s_class;
		
	static String dataOnmachine[];
	static String startTime[];
	static String usedTime[];
	static int countRecording = 0;
	
	static Connection con;
	static Statement sql;
	static ResultSet rs; 
    static PreparedStatement ps;  
    
    //��ȡĳѧ�������ϻ�ʱ��
    public static void getStuAllUsedTime(JTextField jtf){
    	ConnectSQL();
    	try {	
    		int allTime = 0;
    		ps = con.prepareStatement("select time_digit from recoding where stu_number = ?  and onmachineState = '0'" );  
            ps.setString(1, s_stuNumber);    
            rs = ps.executeQuery(); 
            while(rs.next()) {
            	int time = rs.getInt(1);
            	allTime += time;
            }
            jtf.setText(TimeChange.secToTime(allTime));
		} catch (Exception e) {}
    }
    //��¼ĳѧ���ܵ��ϻ�ʱ��
    public static int statisticsAllTime(int number) {
    	ConnectSQL();
    	try {							
    		ps = con.prepareStatement("update recoding set time_digit =? where stu_number = ?  and onmachineState = '1'" );  
            ps.setInt(1, number);
            ps.setString(2, s_stuNumber);    
            ps.executeUpdate(); 
            con.close();
		} catch (Exception e) {}
    	
    	return 0 ;
    }
    
    //�˳�ʱ�޸��û���¼���ϻ�״̬
    public static void changeRecordingState() {
    	ConnectSQL();
    	try {  
            ps = con.prepareStatement("update recoding set onmachineState = '0' where stu_number = ?  and onmachineState = '1'" );  
            ps.setString(1, s_stuNumber);   
            ps.executeUpdate();   
            con.close();
        } catch (Exception e1) {          
            e1.printStackTrace();  
        }  
    }
    //�û���Ϣչʾ
    public static void showMess(JTextField jtf5,JTextField jtf6, JTextField jtf7) {
    	jtf5.setText(s_name);
    	jtf6.setText(s_class);
    	jtf7.setText(s_stuNumber);
    }
    //����ϻ���¼
    public static void addOnmachineRecording(String date, String time) {
    	ConnectSQL();
    	try {
    		ps = con.prepareStatement("insert into recoding(stu_number,stu_name,date_onMachine,time_start,used_time) values(?,?,?,?,?)");  
            // ��?��ֵ
            ps.setString(1, s_stuNumber);  
            ps.setString(2, s_name); 
            ps.setString(3, date); 
            ps.setString(4, time);
            ps.setString(5, "0");
            ps.executeUpdate(); 
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
    }
    //��̬ˢ�¼�¼
    public static void refreshRecording() {
    	ConnectSQL();
    	try {
    		ps = con.prepareStatement("update recoding set used_time= ? order by id desc limit 1");    		
			ps.setString(1, TimeChange.secToTime(Computer_room_chargeGUI.count));  
			ps.executeUpdate(); 
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
    }
    
    //�޸ĵ�ǰ���
    public static void modifyBalance(JTextField jtf) {
    	ConnectSQL();
    	try {
			sql = con.createStatement();
			String s = "update stu_register set balance= " + Double.parseDouble(jtf.getText()) +" where stuNumber= " +s_stuNumber ;			
//			System.out.println( Double.parseDouble(jtf.getText()));
			sql.executeUpdate(s);
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
    }
    // �ָ��ϼ���״̬
    public static void recoveryState(String number) {
    	ConnectSQL();
    	try {							
			sql =  con.createStatement();
			String s = "update stu_register set State= 1 where stuNumber= " + number;
			//��sql����ϴ������ݿ�ִ��
			sql.executeUpdate(s);
			con.close();
		} catch (Exception e) {}
    }
    
    //�ϼ�����ʧ
    public static void cardLoss(String number) {
    	ConnectSQL();
    	try {  		
			sql =  con.createStatement();
			String s = "update stu_register set State= 0 where stuNumber= " + number;
			//��sql����ϴ������ݿ�ִ��
			sql.executeUpdate(s);
			con.close();
		} catch (Exception e) {}
    }
    //�ж�ѧ���Ƿ����
    public static boolean DetermineStunumberExist(String number) {
    	ConnectSQL();
    	try {							
			sql = con.createStatement();
			rs = sql.executeQuery("select * from stu_register where stuNumber = "+ number);
			if(! rs.next()) {
				con.close();
				return true;
			}
			
		} catch (Exception e) {}
    	return false;
    }
    //����ѧ�Ų�ѯ��ѧ�������5����¼
    public static void getAllDataFormOnmachine(String number) {
    	ConnectSQL();
    	dataOnmachine = new String[100];
    	startTime = new String[100];
    	usedTime = new String[100];
    	try {  
            ps = con.prepareStatement("select stu_number,stu_name,date_onMachine,time_start,used_time from recoding where stu_number=? ");  
            // ��?��ֵ
            ps.setString(1, number);   
            rs = ps.executeQuery();   
            while(rs.next()) { 
            	s_stuNumber = rs.getString(1);  
            	s_name = rs.getString(2); 
            	dataOnmachine[countRecording] = rs.getString(3); 
            	startTime[countRecording] = rs.getString(4); 
            	usedTime[countRecording] = rs.getString(5); 
               countRecording++;
            }
            con.close();
        } catch (Exception e1) {          
            e1.printStackTrace();  
        }  
    	
    }
    
	//��ѯ��Ϣ����ѧ��������
    public static void getDataFormOnmachine(String number,String name ) {  
    	ConnectSQL();
    	dataOnmachine = new String[5];
    	startTime = new String[5];
    	usedTime = new String[5];
        try {  
            ps = con.prepareStatement("select stu_number,stu_name,date_onMachine,time_start,used_time from recoding where stu_number=? and date_onMachine=? ");  
            // ��?��ֵ
            ps.setString(1, number);  
            ps.setString(2, name);    
            rs = ps.executeQuery();   
            while(rs.next()) { 
            	s_stuNumber = rs.getString(1);  
            	s_name = rs.getString(2); 
            	dataOnmachine[countRecording] = rs.getString(3); 
            	startTime[countRecording] = rs.getString(4); 
            	usedTime[countRecording] = rs.getString(5);         
                countRecording++;
            } 
            con.close();
        } catch (Exception e1) {          
            e1.printStackTrace();  
        }  
    }  
    
  //��֤��Ϣstu_register��
  	static void InquireStuInformationByTea(JTextField jtf,JTextField jtf1,JTextField jtf2,JTextField jtf3) {   
  		ConnectSQL();
  		try {
  			sql = con.createStatement();
  			rs = sql.executeQuery("select* from stu_register where stuNumber = " + jtf.getText());
  			if(rs.next()) {
  				jtf3.setText(rs.getString(2));
  				jtf1.setText(rs.getString(4));
  				jtf2.setText(rs.getString(5));		
  			}
  		} catch (Exception e) {
  			System.out.println(e);
  		}
  	}
		
	//��֤��Ϣstu_register��
	static boolean InquireStuInformation(String sn, String pwd ) {   
		ConnectSQL();
		try {
			sql = con.createStatement();
			rs = sql.executeQuery("select* from stu_register");
			while(rs.next()) {
				s_stuNumber = rs.getString(2);
				s_password= rs.getString(3);
				s_name = rs.getString(4);
				s_class = rs.getString(5);
				if(sn.equals(s_stuNumber) && pwd.equals(s_password)) {
					con.close();
					return true;
				}
				
			}
		} catch (Exception e) {
			System.out.println(e);
		}
			return false;
	}
	
	//��֤��Ϣtea_register��
		static boolean InquireTeaInformation(String sn, String pwd ) {   
			ConnectSQL();
			try {
				sql = con.createStatement();
				rs = sql.executeQuery("select* from tea_register");
				while(rs.next()) {
					s_stuNumber = rs.getString(2);
					s_password= rs.getString(3);
					if(sn.equals(s_stuNumber) && pwd.equals(s_password)) {
						con.close();
						return true;
					}
					
				}
			} catch (Exception e) {
				System.out.println(e);
			}
				return false;
		}
	
	
	//�������ݿ�
	 public static void ConnectSQL() {
		
		try {
			//����������
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("����ʧ��");
		}
			
		String url = "jdbc:mysql://localhost:3306/new_schema2?characterEncoding=utf8&useSSL=true";
		String user = "root";
		String password = "wyq-980315";
		try {
			//�������ݿ�
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println("����ʧ��");
		}
	}
	 
	 
	 //��ʾ�ϻ���״̬
	 public static void showState(JTextField jtf,String s) {
		//����stu_register��
			ConnectSQL();
			try {							
				sql = con.createStatement();
				//��ѯstudent1��
				rs = sql.executeQuery("select * from stu_register where stuNumber = "+ s);
				while(rs.next()) {
					if(rs.getInt(7) == 1) {
						jtf.setText("����");
					}
					else {
						jtf.setText("��ʧ");
					}
				}
				con.close();
			} catch (Exception e) {}
	 }
	//��ѯ����
	 public static void InquireBalance(JTextField jtf, String s){
					//����stu_register��
					ConnectSQL();
					try {							
						sql = con.createStatement();
						//��ѯstudent1��
						rs = sql.executeQuery("select * from stu_register where stuNumber = "+ s);
						while(rs.next()) {
								jtf.setText(Double.toString(rs.getDouble(6)));
							}
						con.close();
					} catch (Exception e) {}
					
				}

}
