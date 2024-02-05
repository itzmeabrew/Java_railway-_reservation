package irctc;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class pnr_askController implements Initializable
{

    @FXML
    private TextField pnr;

    @FXML
    private JFXButton srch;

    @FXML
    private JFXButton home;
    
    db_healper db= new db_healper();
    
    public void home_ret(ActionEvent event) 
	   {
		   Parent homeView;
		   try 
		   {
			   homeView = FXMLLoader.load(getClass().getResource("Home.fxml"));
			   Scene home_scene=new Scene(homeView);
			   Stage window=(Stage) ((Node) event.getSource()).getScene().getWindow();
			   window.setScene(home_scene);
			   window.show();
			   window.setTitle("IRCTC Main Veiw");
			}
		   catch (IOException e) 
		   {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Window Error");
		   }
	   }
    
    public void get_pnr(ActionEvent event)
    {
    	int c = 0;
    	String txtPNR,SQL;
		String name,age,gen,trainName,seatNo,src,dstn,rdate,sts;
    	txtPNR=pnr.getText();
    	SQL="SELECT p.passenger_name,p.age,p.gender,t.train_id,t.train_name,p.PNR,p.seat_number,t.src_stn,t.trgt_stn,r.resrv_date,r.resrv_sts FROM passenger p JOIN passenger_ticket pt ON p.PNR=pt.PNR JOIN reservation r ON r.PNR JOIN train t ON t.train_id WHERE p.PNR='"+txtPNR+"';";
    	try 
    	{
    		db.db_connect();
			db.db_retrive(SQL);
			while (db.rs.next()) 
			{
				name=db.rs.getString("passenger_name");
				age=db.rs.getString("age");
				gen=db.rs.getString("gender");
				trainName=db.rs.getString("train_name");
				seatNo=db.rs.getString("seat_number");
				src=db.rs.getString("src_stn");
				dstn=db.rs.getString("trgt_stn");
				rdate=db.rs.getString("resrv_date");
				sts=db.rs.getString("resrv_sts");
				c++;
				System.out.println(name);
				System.out.println(age);
				System.out.println(gen);
				System.out.println(trainName);
				System.out.println(seatNo);
				System.out.println(src);
				System.out.println(dstn);
				System.out.println(rdate);
				System.out.println(sts);
			}
			if (c>0)
			{
				FileWriter fw=new FileWriter("pnr_sql.txt");
				PrintWriter pw=new PrintWriter(fw);
				pw.println(SQL);
				pw.close();
				
				Parent pnrShow;
				pnrShow = FXMLLoader.load(getClass().getResource("pnr_sts_show.fxml"));
				Scene s_scene=new Scene(pnrShow);
				Stage s_window=(Stage) ((Node) event.getSource()).getScene().getWindow();
				s_window.setScene(s_scene);
				s_window.show();
				s_window.setTitle("PNR Status");
			}
		} 
    	catch (SQLException e) 
    	{
    		System.out.println("Retrive Error");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO Auto-generated method stub
		
	}

}
