package irctc;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SearchTrainController implements Initializable 
{

	@FXML
	private TextField dep;

	@FXML
	private TextField arr;

	@FXML
	private ChoiceBox<String> cls;

	@FXML
	private JFXButton find_train;

	@FXML
	private DatePicker date;

	db_healper db = new db_healper();

	public void Search_Trains(ActionEvent event) 
	{
		String departure, arrival, tclass;
		LocalDate sdate;

		departure = dep.getText();
		arrival = arr.getText();
		sdate = date.getValue();
		tclass = cls.getValue();

		try 
		{
			int c = 0;
			String sql, res;
			db.db_connect();
			sql = "SELECT * FROM `train` JOIN train_status ON train.train_id=train_status.train_id WHERE `src_id` = '"
					+ departure + "' AND `trgt_id` = '" + arrival + "' AND `train_type` = '" + tclass
					+ "' AND train_status.avail_date='" + sdate + "';";
			System.out.println(departure);
			System.out.println(arrival);
			System.out.println(sdate);
			System.out.println(tclass);
			System.out.println(sql);
			db.db_retrive(sql);
			while (db.rs.next()) 
			{
				res = db.rs.getString("train_name") + ":" + db.rs.getString("train_type") + ":"
						+ db.rs.getString("src_stn") + ":" + db.rs.getString("trgt_stn") + ":"
						+ db.rs.getString("avail_date");
				System.out.println(res);
				c++;
			}
			System.out.println(c);
			if (c > 0) 
			{
				FileWriter fw=new FileWriter("sql.txt");
				PrintWriter pw=new PrintWriter(fw);
				pw.println(sql);
				pw.close();

				Parent SearchView;
				FXMLLoader loader = new FXMLLoader(getClass().getResource("train_data.fxml"));
	            SearchView = loader.load();            
	            Stage srch_window = new Stage();
	            srch_window.setScene(new Scene(SearchView));
	            srch_window.setTitle("Search Result");
	            srch_window.show();
			}
		} catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) 
		{
			// TODO: handle exception
			System.out.println("Window Error");
			e.printStackTrace();
		}

	}
	
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

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO Auto-generated method stub
		cls.getItems().addAll("Anhubati Class", "AC First Class", "AC Economy", "Sleeper", "Second Sitting");
	}

}
