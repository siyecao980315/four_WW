package javaBigWork;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ManageGUI extends JFrame implements ActionListener {
	
	  JLabel jlb1, jlb2, jlb3,jlb4,jlb5,jlb6,jlb7,jlb8,jlb9= null;
	  JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6,jtf7,jtf8 = null; 
	  JPanel jp1, jp2, jp3, jp4,jp5,jp6,jp7 = null;
	  JButton jb1,jb2, jb3,jb4= null;  
	  DefaultTableModel model= null;  
	  static JTable table;
	  JTable table2 = null;  
	  JScrollPane jsp,jsp2 = null;  
	 
	  public ManageGUI() {
		  jlb1 = new JLabel("������ѧ�ţ�");  
	      jlb2=new JLabel("�������ѯ���ڣ�"); 
	      jlb3=new JLabel("ѧ����Ϣ��");
	      jlb4=new JLabel("�ϻ�������");
	      jlb5=new JLabel("���ϻ�ʱ�䣺");
	      jlb6=new JLabel("��");
	      jlb7=new JLabel("������");
	      jlb8=new JLabel("�༶��");
	      jlb9=new JLabel("ѧ�ţ�");
	      
	      jtf1 = new JTextField(10);  
	      jtf2 = new JTextField(10);
	      jtf3 = new JTextField(10);
	      jtf4 = new JTextField(10);
	      jtf5 = new JTextField(10);
	      jtf6 = new JTextField(10);
	      jtf7 = new JTextField(10);
	      jtf8 = new JTextField(10);
	      
	      jb1 = new JButton("��ѯ");  
	      jb2 = new JButton("��ʧ");
	      jb3 = new JButton("�ָ�");
	      jb4 = new JButton("��ѯ���");
	      // ���ü���  
	      jb1.addActionListener(this);
	      jb2.addActionListener(this);
	      jb3.addActionListener(this);
	      jb4.addActionListener(this);
	      
	      // ���ñ��1  
	      String[] colnames = { "����", "�ϻ�����", "�ϻ�ʱ��", "����ʱ��" };  
	      model = new DefaultTableModel(colnames, 5);  
	      table = new JTable(model);  
	      jsp = new JScrollPane(table);  
	      
	      jp1 = new JPanel();  
	      jp2 = new JPanel();  
	      jp3 = new JPanel();  
	      jp4 = new JPanel();
	      jp5 = new JPanel();
	      jp6 = new JPanel();
	     	      
	      	jp1.add(jlb1);  
	        jp1.add(jtf1); 
	        jp1.add(jlb2);  
	        jp1.add(jtf2);
	        
	        jp3.add(jb1);
	        jp3.add(jb2);
	        jp3.add(jb3);
	        jp3.add(jb4);
	        
	        jp2.add(jlb7);  
	        jp2.add(jtf6); 
	        jp2.add(jlb8);  
	        jp2.add(jtf7);
	        jp2.add(jlb9);  
	        jp2.add(jtf8);
	        
	        
	        jp4.add(jlb3);  
	        
	        jp5.add(jsp);  	         
	        
	        jp6.add(jlb4);  
	        jp6.add(jtf3); 
	        jp6.add(jlb6);  
	        jp6.add(jtf5); 	        
	        jp6.add(jlb5);
	        jp6.add(jtf4);
		    
	        this.add(jp1);  
	        this.add(jp2);  
	        this.add(jp3);  
	        this.add(jp4); 
	        this.add(jp5); 
	        this.add(jp6); 
	        
	        this.setLayout(new GridLayout(6, 1));  
	        this.setTitle("�ϻ��շ�ϵͳ����ʦ");  
	        this.setSize(600, 600);  
	        this.setLocation(150, 150);  
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	        this.setVisible(true);  
	        this.setResizable(false);  
	      
	  
	  }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		ManageGUI mg = new ManageGUI();
		//clearArray();
	}
	// ����������
	public static void clearArray() {
		for(int i = 0; i < 5; i++) {
//			System.out.println(ConnectorTest1.countRecording);
			table.setValueAt("", i, 0);  
	        table.setValueAt("", i, 1);  
	        table.setValueAt("", i, 2);  
	        table.setValueAt("", i, 3); 					
		}
	}
	//����� ��ֵ
	public static void assigningvValues() {
		for(int i = 0; i < ConnectorTest1.countRecording; i++) {
			table.setValueAt(ConnectorTest1.s_name, i, 0);  
	        table.setValueAt(ConnectorTest1.dataOnmachine[i], i, 1);  
	        table.setValueAt(ConnectorTest1.startTime[i], i, 2);  
	        table.setValueAt(ConnectorTest1.usedTime[i], i, 3); 					
		}
	}
	
	public static void assigningvValues1() {
		for(int i = 0; i < 5; i++) {
			table.setValueAt(ConnectorTest1.s_name, i, 0);  
	        table.setValueAt(ConnectorTest1.dataOnmachine[i], i, 1);  
	        table.setValueAt(ConnectorTest1.startTime[i], i, 2);  
	        table.setValueAt(ConnectorTest1.usedTime[i], i, 3); 					
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e1) {
		if (e1.getActionCommand().equals("��ѯ")  &&!jtf1.getText().isEmpty() &&!jtf2.getText().isEmpty()) { 
			clearArray();//��ձ������
			if(! ConnectorTest1.DetermineStunumberExist(jtf1.getText())) {	
				// ��ʾ�û��Ļ�����Ϣ
				ConnectorTest1.InquireStuInformationByTea(jtf1, jtf6, jtf7, jtf8);
				//��ȡ�û������ϻ�ʱ��
				ConnectorTest1.getStuAllUsedTime(jtf4);
				//�����û��ϻ���¼
				ConnectorTest1.getDataFormOnmachine(jtf1.getText(), jtf2.getText());
				jtf1.setText("");  
				jtf2.setText("");  
				if(ConnectorTest1.countRecording <= 5) {
					//��ʾ�û��ϻ���¼
					assigningvValues();
					jtf3.setText(Integer.toString(ConnectorTest1.countRecording));				
					ConnectorTest1.countRecording = 0;
				}
				else {
					assigningvValues1();
					jtf3.setText(Integer.toString(ConnectorTest1.countRecording));				
					ConnectorTest1.countRecording = 0;
				}
								
			}
			else {
	             JOptionPane.showMessageDialog(null,"�û��������ڣ�\n����������","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);  
	             jtf1.setText("");  
				 jtf2.setText(""); 
			}
		}
		else if(e1.getActionCommand().equals("��ѯ")  &&!jtf1.getText().isEmpty() && jtf2.getText().isEmpty()){
			clearArray();
			if(! ConnectorTest1.DetermineStunumberExist(jtf1.getText())) {	
				// ��ʾ�û��Ļ�����Ϣ
				ConnectorTest1.InquireStuInformationByTea(jtf1, jtf6, jtf7, jtf8);
				//��ȡ�û������ϻ�ʱ��
				ConnectorTest1.getAllDataFormOnmachine(jtf1.getText());
				//�����û��ϻ���¼
				ConnectorTest1.getStuAllUsedTime(jtf4);
				jtf1.setText("");  
				jtf2.setText("");  				
				if(ConnectorTest1.countRecording <= 5) {
					assigningvValues();
					jtf3.setText(Integer.toString(ConnectorTest1.countRecording));				
					ConnectorTest1.countRecording = 0;
				}
				else {
					assigningvValues1();
					jtf3.setText(Integer.toString(ConnectorTest1.countRecording));				
					ConnectorTest1.countRecording = 0;
				}
							
			
			}
			else {
				JOptionPane.showMessageDialog(null,"�û��������ڣ�\n����������","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);  
	             jtf1.setText("");  
				 jtf2.setText(""); 
			}
			
		}
		else if(e1.getActionCommand().equals("��ʧ") ){
			if(! ConnectorTest1.DetermineStunumberExist(jtf1.getText())) {
				ConnectorTest1.cardLoss(jtf1.getText());
	            JOptionPane.showMessageDialog(null,"��ʧ�ɹ���\n����������","��ʾ��Ϣ",JOptionPane.INFORMATION_MESSAGE);  
	            jtf1.setText("");  
				jtf2.setText(""); 
			}
			else {
				JOptionPane.showMessageDialog(null,"ѧ��Ϊ�ջ�ѧ�Ŵ���\n��������дѧ��","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);

			}
			
		}
		else if(e1.getActionCommand().equals("�ָ�") ) {
			if(! ConnectorTest1.DetermineStunumberExist(jtf1.getText())) {
				ConnectorTest1.recoveryState(jtf1.getText());
				JOptionPane.showMessageDialog(null,"�ָ��ɹ���","��ʾ��Ϣ",JOptionPane.INFORMATION_MESSAGE);
				jtf1.setText("");  
				jtf2.setText("");
			}
			else{
				JOptionPane.showMessageDialog(null,"ѧ��Ϊ�ջ�ѧ�Ŵ���\n��������дѧ��","��ʾ��Ϣ",JOptionPane.INFORMATION_MESSAGE);
			}
			 
		}
		else if(e1.getActionCommand().equals("��ѯ���")){
			ConnectorTest1.InquireBalance(jtf5, jtf1.getText());
		}
	}
}
