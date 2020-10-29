package NumberSystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.List;
import java.sql.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane; 
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SecondWindow  implements ActionListener{

	JFrame f;
	JTable j; 
	JButton btnBack,btnClear;
	
	DefaultTableModel model = new DefaultTableModel();
	

	
	public SecondWindow() {
		f = new JFrame("Number Convertor system"); 
		btnClear = new JButton();
		btnClear.setToolTipText("Press To Clear History..");
		btnClear.setBackground(Color.gray);
		btnClear.setForeground(Color.WHITE);
		btnClear.addActionListener(this);
		
		btnBack = new JButton();
		btnBack.setToolTipText("Press To Go Back..");
		btnBack.setBackground(Color.gray);
		btnBack.setForeground(Color.WHITE);
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				f.setVisible(false);
				new MainWindow();
			}
		});
		
		// Column Names 
		String[] columnNames = { "Id", "Input Type", "Input Number", "Output Type","Output Result" }; 

		 model.setColumnIdentifiers(columnNames);
		// Initializing the JTable 
		j = new JTable();
		j.setBackground(Color.decode("#ffffcc"));//EEE8AA//ffd480

		j.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		j.setFillsViewportHeight(true);
		
		j.setBounds(30, 40, 200, 300);
		
		JPanel jp = new JPanel();
		JScrollPane sp = new JScrollPane(j);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		Connection c =null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/number?useTimezone=true&serverTimezone=UTC", "root", "");
			String sql = "select * from converter";
			PreparedStatement pr = c.prepareStatement(sql);
			ResultSet rs = pr.executeQuery();
			int i=0;
			while(rs.next()) {
				int id = rs.getInt("id");
				String input_type = rs.getString("input_type");
				String input = rs.getString("input");
				String output_type = rs.getString("output_type");
				String output = rs.getString("output");
				model.addRow(new Object[] {id,input_type,input,output_type,output});
				i++;
			}

			j.setModel(model);
			
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
		model.fireTableDataChanged();
		jp.add(sp,BorderLayout.CENTER);	
		JPanel jp1 =new JPanel();
		jp1.add(btnBack,BorderLayout.SOUTH);
		jp1.add(btnClear,BorderLayout.SOUTH);
		
		f.add(jp,BorderLayout.CENTER);
		f.add(jp1,BorderLayout.SOUTH);
		
		try {
			Image bg = ImageIO.read(new File("C:\\Users\\syash\\Desktop\\back.png"));
			btnBack.setIcon(new ImageIcon(bg));
			Image bg1 = ImageIO.read(new File("C:\\Users\\syash\\Desktop\\clear.png"));
			btnClear.setIcon(new ImageIcon(bg1));
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
		// Frame Size 
		f.getContentPane().setBackground(Color.decode("#e6e6e6"));//EEE8AA//ffd480//#ffe6b3
		f.setSize(500, 500);
		f.setResizable(false);
		// Frame Visible = true 
		f.setVisible(true); 
	}

	 public JFrame getFrameInstance() {
	        return f;
	    }
	// Driver method 
//	public static void main(String[] args) {
//		new SecondWindow(); 	
//
//	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() != null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/number?useTimezone=true&serverTimezone=UTC", "root", "");
				PreparedStatement s = c.prepareStatement("delete  from converter");
				int rs = s.executeUpdate();
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			f.setVisible(false);
			new SecondWindow();
		}
		
	}

}
