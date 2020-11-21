import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.*;
import java.util.Vector;

public class ListTest extends JFrame{
	JList<String> strList;
	JTextField tfResult;
	JButton btnMod, btnDel;
	Vector<String> v;
	int index;
	public ListTest() {
		setTitle("리스트 테스트");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		MyListListener mll= new MyListListener();
		MyActionListener mal = new MyActionListener();
		
		
		JPanel panelNorth = new JPanel();
		JTextField tf = new JTextField("",33); // 내용 입력을 위한 텍스트 필드 생성
		// 익명클래스로 이벤트 리스너 구현
		tf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				v.add(tf.getText()); // 텍스트 필드에 입력된 내용을 벡터에 추가
				tf.setText(""); // 텍스트 필드 초기화
				strList.setListData(v); // SetListData로 변경된 벡터 설정
			}
		});
		panelNorth.add(tf);
		
		
		JPanel panelCenter = new JPanel(); 
		v = new Vector<String>();
		strList = new JList<String>(v); // 벡터로부터 아이템을 공급받는 리스트 생성
		strList.setFixedCellWidth(330); // 리스트의 너비 고정설정
		strList.addListSelectionListener(mll); // 리스트에 ListSelection리스너 등록
		panelCenter.add(new JScrollPane(strList)); // 스크롤 지원
		
		
		JPanel panelBottom = new JPanel(); 
		panelBottom.setLayout(new FlowLayout());
		tfResult = new JTextField("",15); // 리스트에서 선택된 항목을 나타내기 위한, 텍스트필드 생성
		btnMod = new JButton("수정");
		btnMod.addActionListener(mal); // 버튼에 Action리스너 등록
		btnDel = new JButton("삭제");
		btnDel.addActionListener(mal); // 버튼에 Action리스너 등록
		panelBottom.add(tfResult);
		panelBottom.add(btnMod);
		panelBottom.add(btnDel);
		
		
		c.add(panelNorth,BorderLayout.NORTH);
		c.add(panelCenter, BorderLayout.CENTER);
		c.add(panelBottom, BorderLayout.SOUTH);
		setSize(350,250);
		setVisible(true);
	}
	class MyListListener implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent e) {
			String name = (String)strList.getSelectedValue();  // 목록에서 선택된 값
			tfResult.setText(name); // 텍스트 필드에, 선택된 값을 나타낸다.
			index = strList.getSelectedIndex();  // 목록에서 선택된 인덱스
		}
	}
	class MyActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnMod) { 
				v.set(index, tfResult.getText()); // 목록에서 선택된 항목을, 벡터에서 수정하고
				strList.setListData(v); // SetListData로 변경된 벡터 설정
				tfResult.setText(""); // tfResult 텍스트필드 초기화
			}
			if(e.getSource() == btnDel) { 
				v.remove(index); // 목록에서 선택된 항목을, 벡터에서 삭제하고
				strList.setListData(v); // SetListData로 변경된 벡터 설정
				tfResult.setText(""); // tfResult 텍스트필드 초기화
				}
		}
	}
	public static void main(String[] args) {
		new ListTest();
	}
}
