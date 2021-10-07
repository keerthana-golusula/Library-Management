import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Calendar; 
import javax.swing.*;

//
//class studrec
//{
//	static ArrayList<student> al=new ArrayList<student>();
//}
//class bookrec
//{
//	static ArrayList<book> bl=new ArrayList<book>();
//}
//class book
//{
//	String bookno;
//	String bookname;
//	String author;
//	boolean taken;
//	book(String a,String n,String no)
//	{
//		bookno=a;
//		bookname=n;
//		author=no;
//		taken=false;
//	}
//}
//class student
//{
//	String admno;
//	String name;
//	String stbno;
//	int token;
//	student(String a,String n,int no)
//	{
//		admno=a;
//		name=n;
//		token=no;
//		stbno="-";
//	}
//}
class CreBook extends Frame implements ActionListener
{
	TextField t1,t2,t3,t4;
	Button create;
	CreBook()
	{
		setSize(800,800);
		setLayout(new FlowLayout(FlowLayout.CENTER,300,50));
		setVisible(true);
		Font myFont = new Font("Times New Roman", Font.BOLD,20);
	 	setTitle("NEW BOOK ENTRY");
		Label l1=new Label("Enter The Book no.");l1.setFont(myFont);
		add(l1);
		t1=new TextField(10);t1.setFont(myFont);
		add(t1);
		Label l2=new Label("Enter The Name of The Book");l2.setFont(myFont);
		add(l2);
		t2=new TextField(30);t2.setFont(myFont);
		add(t2);
		Label l3=new Label("Enter The Name of The Author");l3.setFont(myFont);
		add(l3);
		t3=new TextField(30);t3.setFont(myFont);
		add(t3);
                Label l4=new Label("Enter The edition of The book");l4.setFont(myFont);
		add(l4);
		t4=new TextField(30);t4.setFont(myFont);
		add(t4);
		create= new Button("Create");create.setFont(myFont);
		add(create);
		create.addActionListener(this);
		addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        });  
   	}
    public void actionPerformed(ActionEvent e) { dispose();
		String no= this.t1.getText();
		String n=this.t2.getText();
		String aut=this.t3.getText();
		String ed=this.t4.getText();
           	try{
	Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con=DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:keerth","scott","Keerthana_9");

Statement stmt=con.createStatement();
ResultSet rs=stmt.executeQuery("select * from books where book_id="+no);
if(rs.next())
new popup("the book id"+no+" already exists");
else{
String q1="INSERT INTO books VALUES (?,?,?,?)";
PreparedStatement pstmt1 = con.prepareStatement(q1);
pstmt1.setString(1, no);
   pstmt1.setString(2, n);
pstmt1.setString(3, aut);
   pstmt1.setString(4, ed);
if(pstmt1.executeUpdate()>0)
{
new popup("NEW BOOK CREATED");
}
}
con.close();

}catch(Exception ex){ System.out.println(e);}
		
         }
}

class CreStuRec extends Frame implements ActionListener
{
	TextField t1,t2;
	Button create;
	CreStuRec()
	{
		setSize(800,800);
		setLayout(new FlowLayout(FlowLayout.CENTER,500,80));
		setVisible(true);
		Font myFont = new Font("Times New Roman", Font.BOLD,20);
	 	setTitle("NEW STUDENT ENTRY");
		Label l1=new Label("Enter The admission no.");l1.setFont(myFont);
		add(l1);
		t1=new TextField(10);t1.setFont(myFont);
		add(t1);
		Label l2=new Label("Enter The Name of The Student");l2.setFont(myFont);
		add(l2);
		t2=new TextField(30);t2.setFont(myFont);
		add(t2);
		create= new Button("Create");create.setFont(myFont);
		add(create);
		
		create.addActionListener(this);
		addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        });  
   	}
	public void actionPerformed(ActionEvent e) {dispose(); 
		String no= this.t1.getText();
		String n=this.t2.getText();
           try{
		Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con=DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:keerth","scott","Keerthana_9");

Statement stmt=con.createStatement();
ResultSet rs=stmt.executeQuery("select * from student where student_id="+no);
if(rs.next())
{
new popup("student id"+no+"already exists");
}else{
String q1="INSERT INTO student VALUES (?,?,?)";
PreparedStatement pstmt1 = con.prepareStatement(q1);
pstmt1.setString(1, no);
   pstmt1.setString(2, n);
pstmt1.setInt(3, 0);
if(pstmt1.executeUpdate()>0)
{
new popup("NEW STUDENT RECORD CREATED");
}}
con.close();

}catch(Exception ex){ System.out.println(e);}
		
         }

}
class ModStuName extends Frame implements ActionListener
{
	TextField t1,t2;
	Button modify;
	ModStuName()
	{
		setSize(800,800);
		setLayout(new FlowLayout(FlowLayout.CENTER,500,80));
		setVisible(true);
	 	setTitle("MODIFY STUDENT");
		Font myFont = new Font("Times New Roman", Font.BOLD,20);
		Label l1=new Label("Enter The admission no.");l1.setFont(myFont);
		add(l1);
		t1=new TextField(10);t1.setFont(myFont);
		add(t1);
		Label l2=new Label("Enter new Name of The Student");l2.setFont(myFont);
		add(l2);
		t2=new TextField(30);t2.setFont(myFont);
		add(t2);
		modify= new Button("Modify");modify.setFont(myFont);
		add(modify);
		
		modify.addActionListener(this);
		addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        });  
   	}
    public void actionPerformed(ActionEvent e) { 
		dispose();int flag=0;
		String no= t1.getText();
		String n=t2.getText();
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con=DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:keerth","scott","Keerthana_9");

Statement stmt=con.createStatement();
ResultSet rs=stmt.executeQuery("select * from student where student_id="+no);
if(!rs.next())
{
new popup("Enter valid student id");
}
else{
String q1="UPDATE student SET NAME=? WHERE STUDENT_ID=?";
PreparedStatement pstmt1 = con.prepareStatement(q1);
pstmt1.setString(1, n);
   pstmt1.setString(2, no);
if(pstmt1.executeUpdate()>0)
new popup("STUDENT NAME MODIFIED");
}con.close();

}catch(Exception ex){ System.out.println(e);}
			
         }
}
class ModBook extends Frame implements ActionListener
{
	TextField t1,t2,t3,t4;
	Button modify;
	ModBook()
	{
		setSize(1000,1000);
		setLayout(new FlowLayout(FlowLayout.CENTER,500,30));
		setVisible(true);
	 	setTitle("MODIFY BOOK");
		Font myFont = new Font("Times New Roman", Font.BOLD,20);
		Label l1=new Label("Enter The book no.");l1.setFont(myFont);
		add(l1);
		t1=new TextField(10);t1.setFont(myFont);
		add(t1);
		Label l2=new Label("Enter new Name of The book");l2.setFont(myFont);
		add(l2);
		t2=new TextField(30);t2.setFont(myFont);
		add(t2);
		Label l3=new Label("Enter new author name of The book");l3.setFont(myFont);
		add(l3);
		t3=new TextField(30);t3.setFont(myFont);
		add(t3);
        Label l4=new Label("Enter new edition of The book");l4.setFont(myFont);
		add(l4);
		t4=new TextField(30);t4.setFont(myFont);
		add(t4);
		modify= new Button("Modify");modify.setFont(myFont);
		add(modify);
		modify.addActionListener(this);
		addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        });  
   	}
    public void actionPerformed(ActionEvent e) { 
		dispose();int flag=0;
		String no= t1.getText();
		String name=t2.getText();
        String author=t3.getText();
        String edition=t4.getText();
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con=DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:keerth","scott","Keerthana_9");

Statement stmt=con.createStatement();
String q="select * from books where book_id="+no;
ResultSet rs=stmt.executeQuery(q);
if(!rs.next())
{
new popup("enter a valid book id");
}
String q1="UPDATE books SET BOOK_NAME=?,BOOK_AUTHOR=?,EDITION=? WHERE BOOK_ID=?";
PreparedStatement pstmt1 = con.prepareStatement(q1);
pstmt1.setString(1, name);
pstmt1.setString(2,author);
pstmt1.setString(3,edition);
   pstmt1.setString(4, no);
if(pstmt1.executeUpdate()>0){
new popup("book details are modified succesfully :-)");
} 
con.close();

}catch(Exception ex){ System.out.println(e);}
			
         }
	
}
class DelStuRec extends Frame implements ActionListener
{
	TextField t1,t2;
	Button delete;
	DelStuRec()
	{
		setSize(800,800);
		setLayout(new FlowLayout(FlowLayout.CENTER,500,80));
		setVisible(true);
	 	setTitle("DELETE STUDENT RECORD");
		Font myFont = new Font("Times New Roman", Font.BOLD,20);
		Label l1=new Label("Enter The admission no.");l1.setFont(myFont);
		add(l1);
		t1=new TextField(10);t1.setFont(myFont);
		add(t1);
		delete= new Button("Delete");delete.setFont(myFont);
		add(delete);
		delete.addActionListener(this);
		addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        });  
   	}
    
    public void actionPerformed(ActionEvent e) { 
		dispose();int flag=0;
		String no= t1.getText();
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con=DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:keerth","scott","Keerthana_9");

Statement stmt=con.createStatement();
ResultSet rs=stmt.executeQuery("select * from student where student_id="+no);

if(!rs.next())
{
new popup("enter valid student id");
}else{ 
ResultSet rs2=stmt.executeQuery("select * from issue where student_id="+no);
if(rs2.next()){

ResultSet rs3=stmt.executeQuery("select book_id from issue where student_id="+no);
String str=" ";
//String str2="the student has taken ";
while(rs3.next())
{ 
str=str+rs3.getString(1)+",";
}
new popup("the student has taken"+str+"books");

}else{
String q1="DELETE FROM student WHERE STUDENT_ID=?";
PreparedStatement pstmt1 = con.prepareStatement(q1);
pstmt1.setString(1, no);
   //pstmt1.setString(2, no);
pstmt1.executeUpdate(); 
    new popup("STUDENT details deleted");}
con.close();
}
}catch(Exception ex){ System.out.println(e);}
			
            
         }

	}
class DelBook extends Frame implements ActionListener
{
	TextField t1,t2;
	Button delete;
	DelBook()
	{
		setSize(800,800);
		setLayout(new FlowLayout(FlowLayout.CENTER,500,80));
		setVisible(true);
	 	setTitle("DELETE BOOK");
		Font myFont = new Font("Times New Roman", Font.BOLD,20);
		Label l1=new Label("Enter The book no.");l1.setFont(myFont);
		add(l1);
		t1=new TextField(10);t1.setFont(myFont);
		add(t1);
		delete= new Button("Delete");delete.setFont(myFont);
		add(delete);
		delete.addActionListener(this);
		addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        });  
   	}
     public void actionPerformed(ActionEvent e) { 
		dispose();int flag=0;
		String no= t1.getText();
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con=DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:keerth","scott","Keerthana_9");

Statement stmt=con.createStatement();

String q1="DELETE FROM books WHERE BOOK_ID=?";
PreparedStatement pstmt1 = con.prepareStatement(q1);
pstmt1.setString(1, no);
   //pstmt1.setString(2, no);
if(pstmt1.executeUpdate()>0)
new popup("a book is deleted");
con.close();

}catch(Exception ex){ System.out.println(e);}
			
         }
}
class SpecStuRec extends Frame implements ActionListener
{
	TextField tf,tf5,tf6,tf7,tf8;
	Label l1,l2,l3,l4;
	SpecStuRec()
	{
		setVisible(true);
		setLayout(new FlowLayout(FlowLayout.CENTER,500,40));
		setTitle("SPECIFIC STUDENT RECORD");
		Font myFont = new Font("Times New Roman", Font.BOLD,20);
		setSize(800,800);
		Label l=new Label("Enter adm. no.:");l.setFont(myFont);add(l);
		tf=new TextField(10);tf.setFont(myFont);
		add(tf);
		l1=new Label("Adm. no.");l1.setFont(myFont);
		l2=new Label("Name:");l2.setFont(myFont);
		l3=new Label("No. of Books:");l3.setFont(myFont);
		l4=new Label("Book No.:");l4.setFont(myFont);
		tf5=new TextField(10);tf5.setFont(myFont);
		tf6=new TextField(10);tf6.setFont(myFont);
		tf7=new TextField(10);tf7.setFont(myFont);
		tf8=new TextField(10);tf8.setFont(myFont);
		add(l1);add(tf5);
		add(l2);add(tf6);
		add(l3);add(tf7);
		add(l4);add(tf8);
		tf.addActionListener(this);
		addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        });  
	}
	public void actionPerformed(ActionEvent e)
	{
   // showdata();
    dispose();int flag=0;
		String no= tf.getText();
	try{
	Class.forName("oracle.jdbc.driver.OracleDriver");

 Connection con=DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:keerth","scott","Keerthana_9");

Statement stmt=con.createStatement();

PreparedStatement ps=con.prepareStatement("select * from student where student_id=?");
ps.setString(1,no);
ResultSet rs=ps.executeQuery();
//showdata();
while(rs.next()){
tf5.setText(rs.getString(1));
tf6.setText(rs.getString(2));
tf7.setText(rs.getString(3));
 }
con.close();

}catch(Exception ae){ 
    
   System.out.println(ae);

}
		
	}
    
    }

class SpecBook extends Frame implements ActionListener
{
	TextField tf,tf5,tf6,tf7,tf8;
	Label l1,l2,l3,l4;
	SpecBook()
	{
		setVisible(true);
		setLayout(new FlowLayout(FlowLayout.CENTER,500,40));
		setTitle("SPECIFIC BOOK DETAILS");
		setSize(800,800);
		Font myFont = new Font("Times New Roman", Font.BOLD,20);
		Label l=new Label("Enter book no.:");l.setFont(myFont);add(l);
		tf=new TextField(10);tf.setFont(myFont);
		add(tf);
       Button show= new Button("show");show.setFont(myFont);
		add(show);
//		l1=new Label("Book no.");l1.setFont(myFont);
//		l2=new Label("Book Name:");l2.setFont(myFont);
//		l3=new Label("author:");l3.setFont(myFont);
//		l4=new Label("taken:");l4.setFont(myFont);
//		tf5=new TextField(10);tf5.setFont(myFont);
//		tf6=new TextField(10);tf6.setFont(myFont);
//		tf7=new TextField(10);tf7.setFont(myFont);
//		tf8=new TextField(10);tf8.setFont(myFont);
//		add(l1);add(tf5);
//		add(l2);add(tf6);
//		add(l3);add(tf7);
//		add(l4);add(tf8);
		show.addActionListener(this);
		addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        });  
	}
    public void actionPerformed(ActionEvent e)
	{
   // showdata();
    dispose();int flag=0;
		String no= tf.getText();
	try{
	Class.forName("oracle.jdbc.driver.OracleDriver");

 Connection con=DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:keerth","scott","Keerthana_9");

Statement stmt=con.createStatement();
String q=("select * from books where book_id="+no);
//ps.setString(1,no);
ResultSet rs=stmt.executeQuery(q);
//showdata();
if(rs == null){
new popup("enter valid book id");
}else{
ResultSet rs2=stmt.executeQuery("select * from issue where book_id="+no);
      String s1=" ",s2=" ",s3=" ",str=" ";
      if(rs2.next()){
         str="yes";
        }else{
       str="no";
        }
        while(rs.next())
        {
        s1=rs.getString(1);
s2=rs.getString(2);
s3=rs.getString(3);
}
show f=new show(s1,s2,s3,str);
}
con.close();
}catch(Exception ae){ 
    
   System.out.println(ae);
}
}
    }
    class show extends Frame{
    Label l1,l2,l3,l4,tf5,tf6,tf7,tf8;
    show(String s1,String s2,String s3,String s4){
     setVisible(true);
		setLayout(new FlowLayout(FlowLayout.CENTER,500,40));
		setTitle("SPECIFIC BOOK DETAILS");
		setSize(800,800);
		Font myFont = new Font("Times New Roman", Font.BOLD,20);
        l1=new Label("Book no.");l1.setFont(myFont);
		l2=new Label("Book Name:");l2.setFont(myFont);
		l3=new Label("author:");l3.setFont(myFont);
		l4=new Label("taken:");l4.setFont(myFont);
		tf5=new Label(s1);tf5.setFont(myFont);
		tf6=new Label(s2);tf6.setFont(myFont);
		tf7=new Label(s3);tf7.setFont(myFont);
        
		tf8=new Label(s4);tf8.setFont(myFont);
        
        //tf8=new Label("No");tf8.setFont(myFont);
		add(l1);add(tf5);
		add(l2);add(tf6);
		add(l3);add(tf7);
		add(l4);add(tf8);
        addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        });  
        
        }

    }
class AllStuRec extends Frame
{
	AllStuRec()
	{
		//if(studrec.al.size()==0)
		
		
			try{
	Class.forName("oracle.jdbc.driver.OracleDriver");

 Connection con=DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:keerth","scott","Keerthana_9");

Statement stmt=con.createStatement();

ResultSet rs=stmt.executeQuery("select * from student");
//System.out.println(rs.next());
if(!rs.next()){
new popup("NO STUDENT DETAILS RECORDED");
}
else{
setVisible(true);
		setSize(800,800);
		setTitle("ALL STUDENTS RECORD");
		Font myFont = new Font("Times New Roman", Font.BOLD,20);
		setLayout(new GridLayout((studrec.al.size()+1),3));
		Label l1=new Label("Admission no.");l1.setFont(myFont);add(l1);
		Label l2=new Label("Name");l2.setFont(myFont);add(l2);
		Label l3=new Label("No. of books");l3.setFont(myFont);add(l3);
	

		do{
        //System.out.println("hello");
        String student_id=rs.getString(1);
        String student_name=rs.getString(2);
        String booksissued=rs.getString(3);
			Label l6=new Label(student_id);l6.setFont(myFont);add(l6);
			Label l7=new Label(student_name);l7.setFont(myFont);add(l7);
			Label l9=new Label(booksissued);l9.setFont(myFont);add(l9);
    //System.out.println("hello2");
        }while(rs.next());
    }
 con.close();

}catch(Exception ae){ 
    
   System.out.println(ae);

}

		
		addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        }); 
    
	}
}
class popup extends Frame
{
	String str; 
	popup(String s)
	{
		setVisible(true);
		setLayout(new FlowLayout(FlowLayout.CENTER,500,80));
		Font myFont = new Font("Times New Roman", Font.BOLD,20);
		setTitle("NOTICE");
		setSize(600,200);
		str=s;
		Label l=new Label(str);l.setFont(myFont);
		add(l);	
		addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        }); 
	} 
}	
class BookIssue extends Frame implements ActionListener
{
	TextField tf1,tf2;
	BookIssue()
	{
		setVisible(true);
		setLayout(new FlowLayout(FlowLayout.CENTER,500,80));
		setTitle("BOOK ISSUE");
		Font myFont = new Font("Times New Roman", Font.BOLD,20);
		setSize(800,800);
		Label l1=new Label("Enter Book no.:");l1.setFont(myFont);
		add(l1);
		tf1=new TextField(10);tf1.setFont(myFont);
		add(tf1);
		Label l2=new Label("Enter Student Admission no.:");l2.setFont(myFont);
		add(l2);
		tf2=new TextField(10);tf2.setFont(myFont);
		add(tf2);
		Button b=new Button("Issue");b.setFont(myFont);
		add(b);
		b.addActionListener(this);
		addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        }); 
	} 
	public void actionPerformed(ActionEvent e)
	{
    int flag=0,found=0;
		String s="";
		String bo=tf1.getText();
		String st=tf2.getText();

	try{
	Class.forName("oracle.jdbc.driver.OracleDriver");


 Connection con=DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:keerth","scott","Keerthana_9");

Statement stmt=con.createStatement();
String q1="select * from books where book_id="+bo;
ResultSet rs=stmt.executeQuery(q1);
if(!rs.next())
{
new popup("No such book exists");
}else{
String q2="select * from issue where book_id="+bo;
ResultSet rs2=stmt.executeQuery(q2);
if(rs2.next())
{
new popup("this book is already taken");
}else{
String q3="select * from student where student_id="+st;
ResultSet rs3=stmt.executeQuery(q3);
if(!rs3.next())
{
new popup("no such student record exists");
}else{
String q4="select count(*) from issue where student_id="+st;
ResultSet rs4= stmt.executeQuery(q4);
//java.sql.ResultSetMetaData metaData = rs4.getMetaData();
int n=0;
while(rs4.next())
{
// metaData = rs.getMetaData();
 n = rs4.getInt(1);

}
//System.out.println(n);
if(n==2)
{
new popup("your cards are completed");
}else{
String q5="INSERT INTO issue(student_id,book_id,issuedate) VALUES (?,?,?)";
PreparedStatement pstmt1 = con.prepareStatement(q5);
pstmt1.setString(1, st);
   pstmt1.setString(2, bo);
   pstmt1.setDate(3, new java.sql.Date(System.currentTimeMillis()));
   //pstmt1.setDate(3,d);
 //  pstmt1.setDate(3, getCurrentDate());
if(pstmt1.executeUpdate()>0)
new popup("Book Issued Properly");
}
  }
  }
 }
con.close();

}catch(Exception ae){ 
    
   System.out.println(ae);

}
	}
}
class BookDeposit extends Frame implements ActionListener
{
	TextField tf1,tf2,tf3;
	BookDeposit()
	{
		setVisible(true);
		setLayout(new FlowLayout(FlowLayout.CENTER,500,60));
		setTitle("BOOK DEPOSIT");
		Font myFont = new Font("Times New Roman", Font.BOLD,20);
		setSize(800,800);
		Label l1=new Label("Enter Book no.:");l1.setFont(myFont);
		add(l1);
		tf1=new TextField(10);tf1.setFont(myFont);
		add(tf1);
		Label l2=new Label("Enter Student Admission no.:");l2.setFont(myFont);
		add(l2);
		tf2=new TextField(10);tf2.setFont(myFont);
		add(tf2);
//		Label l3=new Label("Book Deposited in number of days:");l3.setFont(myFont);
//		add(l3);
//		tf3=new TextField(10);tf3.setFont(myFont);
//		add(tf3);
		Button b=new Button("Deposit");b.setFont(myFont);
		add(b);
		b.addActionListener(this);
		addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        }); 
	} 
	public void actionPerformed(ActionEvent e)
	{

    	try{
	Class.forName("oracle.jdbc.driver.OracleDriver");

 Connection con=DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:keerth","scott","Keerthana_9");

String s="";
		String bo=tf1.getText();
		String st=tf2.getText();
        //String days=tf3.getText();
Statement stmt=con.createStatement();
PreparedStatement pstmt = null;
//System.out.println("haii");
String query = "select * from issue where student_id="+st+"and book_id="+bo;
//      pstmt = con.prepareStatement(query); // create a statement
//      pstmt.setString(1, st); // set input parameter 1
//      pstmt.setString(2, bo); // set input parameter 2
//      //pstmt.executeUpdate(); // execute insert statement
ResultSet rs=stmt.executeQuery(query);
new popup("done");
if(!rs.next()){
//System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));{
 new popup(" enter valid entries as the book with id "+bo+ " is not issued for student with id "+st);
}else{
ResultSet rs2=stmt.executeQuery("select trunc(SYSDATE - (select issuedate from issue where book_id="+bo+")) from dual");
int diff=0;
while(rs2.next())
{
diff=rs2.getInt(1);

//System.out.println(diff);

}
//int n=Integer.parseInt(days);
if(diff>10)
{
int fine=diff-10;
new popup(" book is returning after due date please collect "+fine+"rupees and deposit book");
}else{
PreparedStatement pstmt2 = null;
String q2 ="delete from issue where book_id="+bo;
////System.out.println("n");
//
//System.out.println("hel");
//
//pstmt2.setString(1, bo);
//System.out.println("hello");
stmt.executeQuery(q2);
new popup("Book Deposited Succesfully");
}

}
con.close();

}catch(Exception ae){ 
    
   System.out.println(ae);

}
	}
}
class AllBooks 
{
	AllBooks()
	{
    try{
	Class.forName("oracle.jdbc.driver.OracleDriver");

 Connection con=DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:keerth","scott","Keerthana_9");

Statement stmt=con.createStatement();

ResultSet rs=stmt.executeQuery("select * from books");
if(!rs.next()){
new popup("NO books DETAILS RECORDED");
}
else{
    JFrame f=new JFrame("allbooks");
    final JTextArea t=new JTextArea(100,100);
JScrollPane scroll = new JScrollPane(t);  
       scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
       scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
    Container contentPane = f.getContentPane();
    contentPane.add(scroll, BorderLayout.CENTER);
    f.setSize(800,800);
    f.setLayout(null);
    f.setVisible(true);
    JPanel p =new JPanel();
     
    
		p.setVisible(true);
		p.setSize(800,800);
		//setTitle("ALL BOOKS");
		Font myFont = new Font("Times New Roman", Font.BOLD,20);

    
    
    //    f.add(scroll);
    p.setLayout(new GridLayout(0,4));
//        Scrollbar scroll=new Scrollbar(Scrollbar.VERTICAL,100,100,,255);
//        add(scroll);
    
    
JLabel l1=new JLabel("Book no.");l1.setFont(myFont);f.add(l1);
		JLabel l2=new JLabel("Book Name");l2.setFont(myFont);f.add(l2);
		JLabel l3=new JLabel("Author Name");l3.setFont(myFont);f.add(l3);
		JLabel l4=new JLabel("Edition");l4.setFont(myFont);f.add(l4);
		//Iterator<book> itr=bookrec.bl.iterator();
        
		do
		{
        String book_id=rs.getString(1);
        String bookname=rs.getString(2);
        String author=rs.getString(3);
        String edition=rs.getString(4);
			JLabel l5=new JLabel(book_id);l5.setFont(myFont);f.add(l5);
			JLabel l6=new JLabel(bookname);l6.setFont(myFont);f.add(l6);
			JLabel l7=new JLabel(author);l7.setFont(myFont);f.add(l7);
			JLabel l8=new JLabel(edition);l8.setFont(myFont);f.add(l8);
			
		}while(rs.next());
    t.setText("What is Lorem Ipsum?Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Why do we use it?It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using , making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).Where does it come from?Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of de Finibus Bonorum et Malorum (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, Lorem ipsum dolor sit amet.., comes from a line in section 1.10.32.The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from de Finibus Bonorum et Malorum by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.Where can I get some?There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which dont look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isnt anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.");
    
   f.setContentPane(contentPane);
     
  
}
con.close();

}catch(Exception ae){ 
    
   System.out.println(ae);

}
     
//		addWindowListener(new WindowAdapter(){  
//            public void windowClosing(WindowEvent e) {  
//                dispose();  
//            }  
//        }); 
 }
}	

class AdminMenu extends Frame implements ActionListener
{
	AdminMenu()
	{
		setSize(800,800);
		setLayout(new FlowLayout(FlowLayout.CENTER,500,20));
		setVisible(true);
		Font myFont = new Font("Times New Roman", Font.BOLD,20);
		setTitle("ADMINISTRATOR MENU");
		Button a[]=new Button[11];
		a[0]=new Button("CREATE STUDENT RECORD");
		a[1]=new Button("DISPLAY ALL STUDENTS RECORD");
		a[2]=new Button("DISPLAY SPECIFIC STUDENT RECORD");
		a[3]=new Button("MODIFY STUDENT RECORD");
		a[4]=new Button("DELETE STUDENT RECORD");
		a[5]=new Button("CREATE BOOK");
		a[6]=new Button("DISPLAY ALL BOOKS");
		a[7]=new Button("DISPLAY SPECIFIC BOOK");
		a[8]=new Button("MODIFY BOOK ");
		a[9]=new Button("DELETE BOOK ");
		a[10]=new Button("BACK TO MAIN MENU");
		for(int j=0;j<11;j++)
		{
			a[j].setFont(myFont);
			add(a[j]);
			a[j].addActionListener(this);	
		}
		addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        });  
	}
	public void actionPerformed(ActionEvent e) { 
	String str=e.getActionCommand();
	if(str.equals("CREATE STUDENT RECORD")){new CreStuRec();}
	else if(str.equals("DISPLAY ALL STUDENTS RECORD")){new AllStuRec();}
	else if(str.equals("DISPLAY SPECIFIC STUDENT RECORD")){new SpecStuRec();}
	else if(str.equals("MODIFY STUDENT RECORD")){new ModStuName();}
	else if(str.equals("DELETE STUDENT RECORD")){new DelStuRec();}
	else if(str.equals("CREATE BOOK")){new CreBook();}
	else if(str.equals("DISPLAY ALL BOOKS")){new AllBooks();}
	else if(str.equals("DISPLAY SPECIFIC BOOK")){new SpecBook();}
	else if(str.equals("MODIFY BOOK ")){new ModBook();}
	else if(str.equals("DELETE BOOK ")){new DelBook();}
	else if(str.equals("BACK TO MAIN MENU")){dispose();//setVisible(false);
	}
}
    }  	
class MainFr extends Frame implements ActionListener
{
    
	MainFr()
	{
		setLayout(new FlowLayout(FlowLayout.CENTER,500,80));
		setSize(800,800);
		setVisible(true);
		Font myFont1 = new Font("Times New Roman", Font.BOLD,42);
		setTitle("MAIN MENU");
		Label l=new Label("LIBRARY ORGANISATION");
		l.setFont(myFont1);
		add(l);
		Button b1=new Button("BOOK ISSUE");b1.setFont(myFont1);
		Button b2=new Button("BOOK DEPOSIT");b2.setFont(myFont1);
	  	Button b3=new Button("ADMINISTRATOR MENU");b3.setFont(myFont1);
	  	Button b4=new Button("EXIT");b4.setFont(myFont1);
	  	add(b1);
		add(b2);
		add(b3);
		add(b4);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
	addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        });  
		}
public void actionPerformed(ActionEvent e) {
      		if(e.getActionCommand().equals("BOOK ISSUE")){new BookIssue();}
		else if(e.getActionCommand().equals("BOOK DEPOSIT")){new BookDeposit();}
		else if(e.getActionCommand().equals("ADMINISTRATOR MENU")){new AdminMenu();}
		else if(e.getActionCommand().equals("EXIT")){dispose();setVisible(false);}
    }
		
}
class LibManage
{
public static void main(String ar[])
{
	//intro();
	try{
	Class.forName("oracle.jdbc.driver.OracleDriver");

 Connection con=DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:keerth","scott","Keerthana_9");

Statement stmt=con.createStatement();

ResultSet rs=stmt.executeQuery("select * from emp");
while(rs.next())
System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
 
con.close();

}catch(Exception ae){ 
    
   System.out.println(ae);

}
new MainFr();
}
}             