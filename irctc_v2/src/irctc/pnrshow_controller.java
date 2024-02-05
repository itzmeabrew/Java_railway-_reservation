package irctc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class pnrshow_controller implements Initializable
{
	String SQL;
	String sname,sage,sgen,trainName,seatNo,src,dstn,rdate,asts,txtPNR;
    @FXML
    private Label name;

    @FXML
    private Label gen;

    @FXML
    private Label age;

    @FXML
    private Label pnr;

    @FXML
    private Label tname;

    @FXML
    private Label date;

    @FXML
    private Label sno;

    @FXML
    private Label dep;

    @FXML
    private Label arr;

    @FXML
    private Label sts;
    
    db_healper db= new db_healper();
    
    public void initData() 
	{
		String str = "none";
		try 
		{
			FileReader fr=new FileReader("pnr_sql.txt");
			BufferedReader br=new BufferedReader(fr);
			while ((str=br.readLine())!=null) 
			{
				System.out.println(str);
				 SQL = str;
			}
			br.close();
		}
		catch (FileNotFoundException e) 
		{
			System.out.println("Init Error");
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			System.out.println("Init Error");
		}
		
	}
    
    public void set_data()
    {
    	name.setText(sname);
    	gen.setText(sgen);
    	age.setText(sage);
    	pnr.setText(txtPNR);
    	tname.setText(trainName);
    	date.setText(rdate);
    	sno.setText(seatNo);
    	dep.setText(src);
    	arr.setText(dstn);
    	sts.setText(asts);	
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		initData();
		// TODO Auto-generated method stub
		try 
    	{	    	
    		db.db_connect();
			db.db_retrive(SQL);
			while (db.rs.next()) 
			{
				sname=db.rs.getString("passenger_name");
				sage=db.rs.getString("age");
				sgen=db.rs.getString("gender");
				trainName=db.rs.getString("train_name");
				seatNo=db.rs.getString("seat_number");
				src=db.rs.getString("src_stn");
				dstn=db.rs.getString("trgt_stn");
				rdate=db.rs.getString("resrv_date");
				asts=db.rs.getString("resrv_sts");
				txtPNR=db.rs.getString("PNR");
				System.out.println(name);
			}
		} 
    	catch (SQLException e) 
    	{
    		System.out.println("Retrive Error");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		set_data();
	}
    

}
