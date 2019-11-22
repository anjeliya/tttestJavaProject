package test;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;

public class GListEdit extends JFrame {

	private JPanel contentPane;
	private JTextField tfNo;
	private JTextField tfLocal;
	private JTextField tfName;
	private JTextField tfCompany;
	private JTextField tfRedate;
	private JTextField tfPrice;
	private JTextField tfQuant;
	
	//변수추가
	private GList parent;
	//화면 갱신용
	private GListDAO dao;
	private GListDTO dto;
	private String no;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GListEdit frame = new GListEdit();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public GListEdit(GList parent, String no) {
		//GList 프레임의 주소값을 저장
		this.parent = parent;
		this.no = no;
		System.out.println(parent);
		System.out.println(dto);
		
		setTitle("제품 편집");
		//현재 창만 닫는 옵션으로 변경
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setBounds(100, 100, 249, 300);
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
		
		
		
		
		//dto값을 텍스트필드에 출력
		dao=new GListDAO();
		dto=dao.viewGList(no);
		
		tfNo.setText(dto.getNo());
		tfLocal.setText(dto.getLocal());
		tfName.setText(dto.getName());
		tfCompany.setText(dto.getCompany());
		tfRedate.setText(dto.getRedate()+"");
		tfPrice.setText(dto.getPrice()+"");//int 점수를 ""로 String화함
		tfQuant.setText(dto.getQuant()+"");

		
		tfNo.setText(dto.getNo());
		
		JButton btnUpdate = new JButton("수정");
		////
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String no = tfNo.getText();
				String local = tfLocal.getText();
				String name = tfName.getText();
				String company = tfCompany.getText();
				//Date => String
				String redate = tfRedate.getText();
				int price = Integer.parseInt(tfPrice.getText());
				int quant = Integer.parseInt(tfQuant.getText());
				//Date.valueOf(redate)
				GListDTO dto= new GListDTO(no, local, name, company, Date.valueOf(redate), price, quant);
				int result=dao.updateGList(dto);
				if(result==1) {
					JOptionPane.showMessageDialog(
							GListEdit.this, "수정되었습니다.");
					parent.refreshTable();//GList 갱신
					dispose();//현재 프레임을 닫는 Window 메소드
				}
				
				
			}
		});
		
		////
		
		btnUpdate.setBounds(12, 207, 97, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("삭제");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String no=tfNo.getText();
				int result=0;
				int response=JOptionPane.showConfirmDialog(
						GListEdit.this, "삭제하시겠습니까?");
				if(response==JOptionPane.YES_OPTION) {
					result=dao.deleteGList(no);
				}
				if(result==1) {
					JOptionPane.showMessageDialog(GListEdit.this, "삭제되었습니다.");
					parent.refreshTable();
					dispose();
					
				}
				
				
				
				
			}
		});
		btnDelete.setBounds(121, 207, 97, 23);
		contentPane.add(btnDelete);
	}
}
