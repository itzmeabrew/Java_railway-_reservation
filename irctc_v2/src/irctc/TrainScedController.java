package irctc;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TrainScedController
{
	String txtID;

    @FXML
    private TextField stnID;

    @FXML
    private JFXButton srch;
    
    @FXML
    private JFXButton home;
    
    db_healper db=new db_healper();
    
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
    
    public void Station_sced(ActionEvent event)
    {
    	String SQL,res;
    	int c=0;
    	txtID=stnID.getText();
    	SQL="SELECT train.train_id,train.train_name,train.train_type,train.src_stn,train.trgt_stn,route.stop_nmbr,route.arr_time,route.dep_time,route.src_dstn FROM `train` JOIN route ON train.train_id=route.train_id WHERE train.train_id='"+txtID+"';";
    	//System.out.println(SQL);
    	//System.out.println(txtID);
    	try 
    	{
    		db.db_connect();
			db.db_retrive(SQL);
			while (db.rs.next()) 
			{
				res=db.rs.getString("train_id")+":"+db.rs.getString("train_name")+":"+db.rs.getString("train_type")+":"+db.rs.getString("src_stn")+":"+db.rs.getString("trgt_stn")+":"+db.rs.getString("stop_nmbr")+":"+db.rs.getString("arr_time")+":"+db.rs.getString("dep_time")+":"+db.rs.getString("src_dstn");
				c++;
				System.out.println(res);
			}
			if (c>0)
			{
				FileWriter fw=new FileWriter("sql2.txt");
				PrintWriter pw=new PrintWriter(fw);
				pw.println(SQL);
				pw.close();
				
				Parent SearchView;
				FXMLLoader loader = new FXMLLoader(getClass().getResource("ShedView.fxml"));
	            SearchView = loader.load();            
	            Stage srch_window = new Stage();
	            srch_window.setScene(new Scene(SearchView));
	            srch_window.setTitle("Schedule Result");
	            srch_window.show();
			}
		} 
    	catch (SQLException e) 
    	{
    		System.out.println("Retrive Error");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Window Error");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
