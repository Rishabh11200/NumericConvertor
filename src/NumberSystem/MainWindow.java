package NumberSystem;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;


public class MainWindow
{
	public String From,To,input1,output1;
	JFrame jf;
	JLabel lblfrom,lblto,lblenter,lblresult;
	JTextArea txtinput,txtresult;
	JButton btnconvert,btnreset,btnswap,btnhistory;
	JComboBox drop_from,drop_to;
	
	public MainWindow()
	{
jf =new JFrame("Number Convertor system");
		
		String str1[]= {"Decimal","Binary","Octal","Hexa-decimal"};
		drop_from= new JComboBox(str1);
		drop_from.setBackground(Color.WHITE);
		String str2[]= {"Decimal","Binary","Octal","Hexa-decimal"};
		drop_to= new JComboBox(str2);
		drop_to.setBackground(Color.WHITE);
		
		lblfrom = new JLabel("From :");
		lblto = new JLabel("To :");
		lblenter =  new JLabel("Enter Number : ");
		lblresult = new JLabel("Result :");
		
		
		txtinput = new JTextArea(10,10);
		txtresult= new JTextArea(10,10);
		
		btnconvert = new JButton();
		btnconvert.setBackground(Color.BLUE);
		btnconvert.setForeground(Color.WHITE);
		btnconvert.setToolTipText("Press For Conversion..");
        btnconvert.setOpaque(true);
      
        
		btnswap = new  JButton();
		btnswap.setToolTipText("Press For Swapping The Values..");
		btnswap.setBackground(Color.gray);
		btnswap.setForeground(Color.WHITE);
		
		
		btnhistory = new  JButton();
		btnhistory.setToolTipText("Press To Watch History..");
		btnhistory.setBackground(Color.gray);
		btnhistory.setForeground(Color.WHITE);
		
		btnreset = new JButton();
		btnreset.setToolTipText("Press To Reset..");
		btnreset.setBackground(Color.gray);
		btnreset.setForeground(Color.WHITE);
		
		jf.add(lblfrom);
		lblfrom.setBounds(50, 30, 100, 20);
		jf.add(lblto);
		lblto.setBounds(300, 30, 100, 20);
		jf.add(drop_from);
		drop_from.setBounds(60, 60, 150, 30);
		
		jf.add(drop_to);
		drop_to.setBounds(310, 60, 150, 30);
		jf.add(lblenter);
		lblenter.setBounds(50, 110, 100, 20);
		jf.add(txtinput);
		txtinput.setBounds(50, 140, 410, 50);
		
		jf.add(btnconvert);
		btnconvert.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				From  = (String) drop_from.getSelectedItem();
				To = (String) drop_to.getSelectedItem();
				
		//Decimal To Binary
				if(From=="Decimal" && To=="Binary") {
					try {
						int decimal = Integer.parseInt( txtinput.getText() );
						String str ="";
						for(;decimal>0;){
							int binary = decimal%2;
							str = Integer.toString(binary) + str;
							decimal /= 2;
						}
						txtresult.setText( str );
					}catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog( null, e1.getMessage() );
						txtinput.setText( "" );
					}
				}
				
		//Decimal To Octal
				if(From=="Decimal" && To=="Octal") {
					try {
						int decimal  = Integer.parseInt( txtinput.getText() );
						String octal = Integer.toOctalString(decimal);
						txtresult.setText( octal );
					}catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog( null, e1.getMessage() );
						txtinput.setText(null);
					}
				}
				
		//Decimal To HexDecimal
				if(From=="Decimal" && To=="Hexa-decimal") {
					try {
						int decimal  = Integer.parseInt( txtinput.getText() );
						String hexa = Integer.toHexString(decimal);
						txtresult.setText( hexa );
					}catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog( null, e1.getMessage() );
						txtinput.setText(null);
					}
				}		
				
		//Decimal To Decimal
				if(From=="Decimal" && To=="Decimal") {
					try {
						int decimal = Integer.parseInt(txtinput.getText());
						txtresult.setText(Integer.toString(decimal));
					}catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog( null, e1.getMessage() );
						txtinput.setText(null);
					}
				}
				
		//Binary To Decimal
				if(From=="Binary" && To=="Decimal") {
					try {
						String binary = txtinput.getText().toString();
						int decimal = Integer.parseInt(binary,2);
						txtresult.setText( Integer.toString(decimal) );
					}catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog( null, e1.getMessage() );
						txtinput.setText(null);
					}
				}
				
		//Binary To Binary
				if(From=="Binary" && To=="Binary") {
					try {
						String binary = txtinput.getText().toString();
						txtresult.setText(binary);
					}catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog( null, e1.getMessage() );
						txtinput.setText(null);
					}
				}
				
		//Binary To Octal
				if(From=="Binary" && To=="Octal") {
					try {
						String binary = txtinput.getText().toString();
						int btoo = Integer.parseInt(binary,2);
						txtresult.setText(Integer.toOctalString(btoo));
					}catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog( null, e1.getMessage() );
						txtinput.setText(null);
					}
				}
				
		//Binary To Hexa-Decimal
				if(From=="Binary" && To=="Hexa-decimal") {
					try {
						String binary = txtinput.getText().toString();
						int btoh = Integer.parseInt(binary,2);
						txtresult.setText(Integer.toHexString(btoh));
					}catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog( null, e1.getMessage() );
						txtinput.setText(null);
					}
				}
				
		//Octal To Decimal
				if(From=="Octal" && To=="Decimal") {
					try {
						String octal = txtinput.getText();
						int decimal = Integer.parseInt(octal,8);
						txtresult.setText( Integer.toString(decimal) );
					}catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog( null, e1.getMessage() );
						txtinput.setText(null);
					}
				}
				
		//Octal To Octal
				if(From=="Octal" && To=="Octal") {
					try {
						String octal = txtinput.getText();
						txtresult.setText(octal);
					}catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog( null, e1.getMessage() );
						txtinput.setText(null);
					}
				}
				
		//Octal To Binary
				if(From=="Octal" && To=="Binary") {
					try {
						String octal = txtinput.getText();
						int decimal = Integer.parseInt(octal,8);
						String str ="";
						for(;decimal>0;){
							int binary = decimal%2;
							str = Integer.toString(binary) + str;
							decimal /= 2;
						}
						txtresult.setText( str );
					}catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog( null, e1.getMessage() );
						txtinput.setText(null);
					}
				}
				
		//Octal To Hexa-Decimal
				if(From=="Octal" && To=="Hexa-decimal") {
					try {
						String octal = txtinput.getText();
						int decimal = Integer.parseInt(octal,8);
						txtresult.setText(Integer.toHexString(decimal));
					}catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog( null, e1.getMessage() );
						txtinput.setText(null);
					}
				}
				
		//HexaDecimal To Decimal
				if(From=="Hexa-decimal" && To=="Decimal") {
					try {
						String hexa = txtinput.getText();
						int decimal = Integer.parseInt(hexa,16);
						txtresult.setText( Integer.toString(decimal) );
					}catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog( null, e1.getMessage() );
						txtinput.setText(null);
					}
				}
				
				
		//Hexa-Decimal To Hexa-Decimal
				if(From=="Hexa-decimal" && To=="Hexa-decimal") {
					try {
						String hexa = txtinput.getText();
						txtresult.setText(hexa);
					}catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog( null, e1.getMessage() );
						txtinput.setText(null);
					}
				}
				
				
		//Hexa-Decimal To Binary
				if(From=="Hexa-decimal" && To=="Binary") {
					try {
						String hexa1 = txtinput.getText();
						int decimal1 =  Integer.parseInt(hexa1,16);
						String str ="";
						for(;decimal1>0;){
							int binary1 = decimal1%2;
							str = Integer.toString(binary1) + str;
							decimal1 /= 2;
						}
						txtresult.setText( str);
					}catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog( null, e1.getMessage() );
						txtinput.setText(null);
					}
				}
				
				
		//Hexa-Decimal To Octal
				if(From=="Hexa-decimal" && To=="Octal") {
					try {
						String hexa1 = txtinput.getText();
						int decimal1 =  Integer.parseInt(hexa1,16);
						txtresult.setText( Integer.toOctalString(decimal1) );
					}catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog( null, e1.getMessage() );
						txtinput.setText(null);
					}
				}
				
				input1 = txtinput.getText();
				output1 = txtresult.getText();
				
				Connection c=null;
				int result=0;
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					c = DriverManager.getConnection("jdbc:mysql://localhost:3306/number?useTimezone=true&serverTimezone=UTC", "root", "");
					String sql="insert into converter (input_type,input,output_type,output) values (?,?,?,?)";
					PreparedStatement pr=null;
					pr=c.prepareStatement(sql);
					pr.setString(1, From);
					pr.setString(2, input1);
					pr.setString(3, To);
					pr.setString(4, output1);
					result=pr.executeUpdate();
					
					} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e2) {
						e2.printStackTrace();
					}
			}
		});
		btnconvert.setBounds(50, 210, 85, 40);
		jf.add(btnreset);
		btnreset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				txtinput.setText(null);
				txtresult.setText(null);
				
			}
		});
		btnreset.setBounds(160, 210, 85, 40);
		jf.add(btnswap);
		btnswap.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int s,s1,t;
				s = drop_from.getSelectedIndex();
				s1 = drop_to.getSelectedIndex();
				t = s;
				s = s1;
				s1 = t;
				drop_from.setSelectedIndex(s);
				drop_to.setSelectedIndex(s1);
				txtinput.setText(null);
				txtresult.setText(null);
								
			}
		});
		btnswap.setBounds(270, 210, 85, 40);
		jf.add(btnhistory);
		btnhistory.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jf.setVisible(false);
				new SecondWindow();
				
			}
		});
		btnhistory.setBounds(379, 210, 85, 40);
		jf.add(lblresult);
		lblresult.setBounds(50, 265, 100, 20);
		jf.add(txtresult);
		txtresult.setBounds(50, 295, 410,50);
		txtresult.setBackground(Color.decode("#DCDCDC"));
		
		try {
			Image bg = ImageIO.read(new File("C:\\Users\\syash\\Desktop\\convert.png"));
			btnconvert.setIcon(new ImageIcon(bg));
			Image bg1 = ImageIO.read(new File("C:\\Users\\syash\\Desktop\\reset.png"));
			btnreset.setIcon(new ImageIcon(bg1));
			Image bg2 = ImageIO.read(new File("C:\\Users\\syash\\Desktop\\swap.png"));
			btnswap.setIcon(new ImageIcon(bg2));
			Image bg3 = ImageIO.read(new File("C:\\Users\\syash\\Desktop\\history.png"));
			btnhistory.setIcon(new ImageIcon(bg3));
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
		jf.setSize(500,400);
		jf.getContentPane().setBackground(Color.decode("#ffdd99"));//EEE8AA//ffd480//ffe6b3
		jf.setLayout(null);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(1);
	}
	public JFrame getFrameInstance() {
        return jf;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainWindow();

	}
	 


}
