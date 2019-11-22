package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;

public class GList extends JFrame {

	private JPanel contentPane;
	private JTextField tfSearch;
	private JTable table;
	
	//변수 추가
		private GListDAO dao;
		private Vector cols;
		private DefaultTableModel model;
//DefaultTableModel클래스 사용이유 
//JTable에서는 추가, 삭제기능을 사용하지 못하지만 DefaultTableModel사용시 addRow(), removeRow() 사용가능
		private GListDTO dto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GList frame = new GList();
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
	public GList() {
		setTitle("재고 관리");
		
		dao=new GListDAO(); //dao 인스턴스 생성
		//테이블에 제목열 구성
		cols=new Vector();
		cols.add("제품번호");
		cols.add("지점");
		cols.add("제품명");
		cols.add("제조회사");
		cols.add("납품일자");
		cols.add("단가");
		cols.add("수량");
		cols.add("총금액");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 643, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 68, 605, 271);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("제품번호를입력하세요");
		lblNewLabel.setBounds(12, 10, 132, 15);
		contentPane.add(lblNewLabel);
		
		tfSearch = new JTextField();
		tfSearch.setBounds(156, 7, 116, 21);
		contentPane.add(tfSearch);
		tfSearch.setColumns(10);
		
		JButton btnSearch = new JButton("검색");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		btnSearch.setBounds(284, 6, 97, 23);
		contentPane.add(btnSearch);
		
		JButton btnList = new JButton("전체품목보기");
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list();
				table.setModel(model);
				clear();
			}
		});
		btnList.setBounds(499, 6, 116, 23);
		contentPane.add(btnList);
		
		
		
		JButton btnAdd = new JButton("추가");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//GListSave에 참조변수를 넘겨주면서 생성
				GListSave frm = new GListSave(GList.this);
				frm.setVisible(true);//추가 프레임을 화면에 표시
				//추가 프레임을 표시할 좌표지정
				frm.setLocation(200,200);
			}
		});
		
		
		
		btnAdd.setBounds(12, 35, 97, 23);
		contentPane.add(btnAdd);
		
		JButton btnEdit = new JButton("편집");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JTable에서 선택한 행의 인덱스 값
				int idx=table.getSelectedRow();//0번째 행~
				System.out.println(idx);
				if(idx == -1) {//선택한 행이 없으면
					JOptionPane.showMessageDialog(
							GList.this, "편집할 자료를 선택하세요.");
					return; //메소드 종료
				} else { //선택한 행이 있으면
					String no=String.valueOf(table.getValueAt(idx, 0));
					GListEdit frm=
							new GListEdit(GList.this,no);
					frm.setVisible(true);
					frm.setLocation(550, 100);
				}
			}
		});
		
		btnEdit.setBounds(121, 35, 97, 23);
		contentPane.add(btnEdit);
		
		//테이블 갱신
		refreshTable();
	}
	
	//JTable에 출력할 데이터 모델 생성
		public void refreshTable() {
			DefaultTableModel model=
					new DefaultTableModel(dao.listGList(), cols);
			//테이블에 데이터 채워짐
			table.setModel(model);
		}
		
		
		//메소드 추가
		public void search() {
			String no = tfSearch.getText();
			//Date redate = Date.valueOf(tfSearch.getText());
			//Date 검색 소스
			model = new DefaultTableModel(
					dao.searchGList(no), cols) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			table.setModel(model);
		}	
		
		public void list() {
			model = new DefaultTableModel(dao.listGList(), cols) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
		}
		
		public void clear() {	
			
		}	
		
		
	
}
