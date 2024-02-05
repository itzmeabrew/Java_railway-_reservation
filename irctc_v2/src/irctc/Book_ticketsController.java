package irctc;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Book_ticketsController implements Initializable
{
	@FXML
    private TextField fname;

    @FXML
    private ChoiceBox<String> cls;

    @FXML
    private DatePicker date;

    @FXML
    private TextField email;

    @FXML
    private TextField mob;

    @FXML
    private JFXRadioButton male;

    @FXML
    private ToggleGroup gen;

    @FXML
    private JFXRadioButton female;

    @FXML
    private TextField age;

    @FXML
    private JFXButton book;

    @FXML
    private TextField dep;

    @FXML
    private TextField arr;
    
    @FXML
    private TextField stno;
    
    db_healper db=new db_healper();
    
    long pnr,tid;
    String name,smob,departure,arrival,sage,sclass,semail,sstno,sgen;

	LocalDate sdate;
    
    public static long getRandomIntegerBetweenRange(double min,long max)
	{
	    long x = (long) ((Math.random()*((max-min)+1))+min);
	    return x;
	}
    
    public void init_data()
    {
    	name=fname.getText();
    	smob=mob.getText();
    	departure=dep.getText();
    	arrival=arr.getText();
    	sage=age.getText();
    	sdate=date.getValue();
    	sclass=cls.getValue();
    	semail=email.getText();
    	sstno=stno.getText();
    	pnr=getRandomIntegerBetweenRange(100000000, 999999999);
    
		if(female.isSelected()) 
    	{
    		sgen="F";
    	}
    	else if(male.isSelected())
    	{
    		sgen="M";
    	}
    		
	}
    
    public void book_now() 
    {
    	init_data();
    	
    	String SQL,tid = null;
		int flag=1;
    	
		try 
    	{
			int c = 0,res;
    		db.db_connect();
    		SQL="SELECT train_id FROM `train` WHERE `src_id` = '"+departure+"' AND `trgt_id` = '"+arrival+"';";
			db.db_retrive(SQL);
			while (db.rs.next()) 
			{
				tid=db.rs.getString("train_id");
				c++;
			}
			System.out.println(tid);
			if (c==0)
			{
				 Window owner = book.getScene().getWindow();
				Alert_helper.showAlert(Alert.AlertType.ERROR, owner, "Search Error!",
	                    "No Train/Booking Failed");
				flag=0;
			}
			
			SQL="INSERT INTO `passenger` (`PNR`, `seat_number`, `passenger_name`, `age`, `gender`, `train_id`) VALUES ('"+pnr+"','"+sstno+"','"+name+"','"+sage+"','"+sgen+"','"+tid+"');";
			res=db.db_insert(SQL);
			
			if (res!=1)
			{
				 Window owner = book.getScene().getWindow();
				Alert_helper.showAlert(Alert.AlertType.ERROR, owner, "Search Error!",
	                    "No Train/Booking Failed");
				flag=0;
			}
			
			SQL="INSERT INTO `reservation` (`train_id`, `avail_date`, `email`, `PNR`, `resrv_date`, `resrv_sts`) VALUES ('"+tid+"','"+sdate+"','"+semail+"','"+pnr+"','"+sdate+"','OK');";
			res=db.db_insert(SQL);
			
			if (res!=1)
			{
				 Window owner = book.getScene().getWindow();
				Alert_helper.showAlert(Alert.AlertType.ERROR, owner, "Search Error!",
	                    "No Train/Booking Failed");
				flag=0;
			}
			
			SQL="INSERT INTO `passenger_ticket` (`PNR`, `src_id`, `trgt_id`, `class_type`, `rsrv_sts`, `train_id`) VALUES ('"+pnr+"','"+departure+"','"+arrival+"','"+sclass+"','OK','"+tid+"');";
			res=db.db_insert(SQL);
			
			if (res!=1)
			{
				 Window owner = book.getScene().getWindow();
				Alert_helper.showAlert(Alert.AlertType.ERROR, owner, "Search Error!",
	                    "No Train/Booking Failed");
				flag=0;
			}
		
		} 
    	catch (SQLException e) 
    	{
    		System.out.println("SQL Error");
			// TODO Auto-generated catch block
    		 Window owner = book.getScene().getWindow();
				Alert_helper.showAlert(Alert.AlertType.ERROR, owner, "Search Error!",
	                    "No Train/Booking Failed");
			e.printStackTrace();
		}
		
		if (flag==1)
		{
			Window owner = book.getScene().getWindow();
			Alert_helper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Booked!",
                    "Your PNR Number is "+pnr);
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
