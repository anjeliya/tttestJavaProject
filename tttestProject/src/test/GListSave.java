package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;

public class GListSave extends JFrame {

	private JPanel contentPane;
	private JTextField tfNo;
	private JTextField tfLocal;
	private JTextField tfName;
	private JTextField tfCompany;
	private JTextField tfRedate;
	private JTextField tfPrice;
	private JTextField tfQuant;
	
	//변수 추가
	private GList frm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GListSave frame = new GListSave();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public GListSave(GList frm) {
		this();//GListSave의 화면을 보이기 위해 처리
		//GListSave() 기본생성자 호출
		this.frm = frm;
	}
	
	public GListSave() {
		setTitle("제품 입력");
		//창닫기(현재창만 닫기)
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 236, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("제품 번호");
		lblNewLabel.setBounds(12, 10, 57, 15);
		contentPane.add(lblNewLabel);
		
		tfNo = new JTextField();
		tfNo.setBounds(81, 7, 116, 21);
		contentPane.add(tfNo);
		tfNo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("지점");
		lblNewLabel_1.setBounds(12, 35, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		tfLocal = new JTextField();
		tfLocal.setBounds(81, 32, 116, 21);
		contentPane.add(tfLocal);
		tfLocal.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("제품명");
		lblNewLabel_2.setBounds(12, 60, 57, 15);
		contentPane.add(lblNewLabel_2);
		
		tfName = new JTextField();
		tfName.setBounds(81, 57, 116, 21);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("제조회사");
		lblNewLabel_3.setBounds(12, 85, 57, 15);
		contentPane.add(lblNewLabel_3);
		
		tfCompany = new JTextField();
		tfCompany.setBounds(81, 82, 116, 21);
		contentPane.add(tfCompany);
		tfCompany.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("납품 일자");
		lblNewLabel_4.setBounds(12, 110, 57, 15);
		contentPane.add(lblNewLabel_4);
		
		tfRedate = new JTextField();
		tfRedate.setBounds(81, 107, 116, 21);
		contentPane.add(tfRedate);
		tfRedate.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("단가");
		lblNewLabel_5.setBounds(12, 135, 57, 15);
		contentPane.add(lblNewLabel_5);
		
		tfPrice = new JTextField();
		tfPrice.setBounds(81, 132, 116, 21);
		contentPane.add(tfPrice);
		tfPrice.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("수량");
		lblNewLabel_6.setBounds(12, 160, 57, 15);
		contentPane.add(lblNewLabel_6);
		
		tfQuant = new JTextField();
		tfQuant.setBounds(81, 157, 116, 21);
		contentPane.add(tfQuant);
		tfQuant.setColumns(10);
		
		JButton btnSave = new JButton("저장");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//사용자가 입력한 값
				String no=tfNo.getText();
				String local=tfNo.getText();
				String name=tfNo.getText();
				String company=tfNo.getText();
				//Date => String
				String redate = tfRedate.getText();
				int price = Integer.parseInt(tfPrice.getText());
				int quant = Integer.parseInt(tfQuant.getText());
				//Date.valueOf(redate)
				GListDTO dto = new GListDTO(no,local,name,company,
						Date.valueOf(redate),price,quant);
				GListDAO dao = new GListDAO();
				int result=dao.insertGList(dto);
				if(result==1) {//insert가 성공하면
					JOptionPane.showMessageDialog(
							GListSave.this, "저장되었습니다.");
					//현재 프레임을 닫기 전에 GList의 refreshTable()호출
					frm.refreshTable();
					//현재 프레임을 닫음
					dispose();
				}
				
			}
		});
		btnSave.setBounds(52, 206, 97, 23);
		contentPane.add(btnSave);
	}
}
