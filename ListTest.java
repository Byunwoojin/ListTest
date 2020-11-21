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
		setTitle("����Ʈ �׽�Ʈ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		MyListListener mll= new MyListListener();
		MyActionListener mal = new MyActionListener();
		
		
		JPanel panelNorth = new JPanel();
		JTextField tf = new JTextField("",33); // ���� �Է��� ���� �ؽ�Ʈ �ʵ� ����
		// �͸�Ŭ������ �̺�Ʈ ������ ����
		tf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				v.add(tf.getText()); // �ؽ�Ʈ �ʵ忡 �Էµ� ������ ���Ϳ� �߰�
				tf.setText(""); // �ؽ�Ʈ �ʵ� �ʱ�ȭ
				strList.setListData(v); // SetListData�� ����� ���� ����
			}
		});
		panelNorth.add(tf);
		
		
		JPanel panelCenter = new JPanel(); 
		v = new Vector<String>();
		strList = new JList<String>(v); // ���ͷκ��� �������� ���޹޴� ����Ʈ ����
		strList.setFixedCellWidth(330); // ����Ʈ�� �ʺ� ��������
		strList.addListSelectionListener(mll); // ����Ʈ�� ListSelection������ ���
		panelCenter.add(new JScrollPane(strList)); // ��ũ�� ����
		
		
		JPanel panelBottom = new JPanel(); 
		panelBottom.setLayout(new FlowLayout());
		tfResult = new JTextField("",15); // ����Ʈ���� ���õ� �׸��� ��Ÿ���� ����, �ؽ�Ʈ�ʵ� ����
		btnMod = new JButton("����");
		btnMod.addActionListener(mal); // ��ư�� Action������ ���
		btnDel = new JButton("����");
		btnDel.addActionListener(mal); // ��ư�� Action������ ���
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
			String name = (String)strList.getSelectedValue();  // ��Ͽ��� ���õ� ��
			tfResult.setText(name); // �ؽ�Ʈ �ʵ忡, ���õ� ���� ��Ÿ����.
			index = strList.getSelectedIndex();  // ��Ͽ��� ���õ� �ε���
		}
	}
	class MyActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnMod) { 
				v.set(index, tfResult.getText()); // ��Ͽ��� ���õ� �׸���, ���Ϳ��� �����ϰ�
				strList.setListData(v); // SetListData�� ����� ���� ����
				tfResult.setText(""); // tfResult �ؽ�Ʈ�ʵ� �ʱ�ȭ
			}
			if(e.getSource() == btnDel) { 
				v.remove(index); // ��Ͽ��� ���õ� �׸���, ���Ϳ��� �����ϰ�
				strList.setListData(v); // SetListData�� ����� ���� ����
				tfResult.setText(""); // tfResult �ؽ�Ʈ�ʵ� �ʱ�ȭ
				}
		}
	}
	public static void main(String[] args) {
		new ListTest();
	}
}
