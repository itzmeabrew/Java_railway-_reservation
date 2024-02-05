package irctc;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class SignupController implements Initializable
{
	String txtuname,txtpass;
	
	@FXML
	private TextField fname;
	@FXML
	private TextField uname;
	@FXML
	private TextField pass;
	@FXML
	private TextField age;
	@FXML
	private TextField mob;
	@FXML
	private TextField state;
	@FXML
	private TextField city;
	@FXML
	private TextField pass2;
	@FXML
	private TextField email;
	@FXML
	private Button register;
	@FXML
	private Button ret;
	@FXML
	private Label sts;
	@FXML
	private RadioButton male;
	@FXML
	private RadioButton female;
	
	db_healper db =new db_healper();
	
	public void signup(ActionEvent event) 
	{
		String txtfname,txtemail,txtmob,txtstate,txtcity,txtpass2,txtgender,txtage;
		
		txtfname=fname.getText();
		txtemail=email.getText();
		txtuname=uname.getText();
		txtpass=pass.getText();
		txtpass2=pass2.getText();
		txtage=age.getText();
		txtmob=mob.getText();
		txtstate=state.getText();
		txtcity=city.getText();
		
		System.out.println(txtpass);
		System.out.println(txtpass2);
		
		if(male.isSelected())
		{
			txtgender="M";
		}
		else 
		{
			txtgender="F";
		}
		
		if(!(txtpass.equals(txtpass2)))
		{
			sts.setText("Password Mismatch");
		}
		
		else if(txtfname.isEmpty() || txtemail.isEmpty() || txtuname.isEmpty() || txtage.isEmpty() || txtmob.isEmpty() || txtstate.isEmpty() || txtcity.isEmpty() || txtpass.isEmpty() )
		{
			sts.setText("Check all fields");
		}
		else 
		{
			try
			{
				db.db_connect();
				String SQL;
				SQL="INSERT INTO `irctc_users` (`email`, `usr_id`, `pass`, `gender`, `age`, `mobile`, `city`, `state`, `fullname`) VALUES ( '"+txtemail+"','"+txtuname+"','"+txtpass+"','"+txtgender+"','"+txtage+"','"+txtmob+"','"+txtstate+"','"+txtcity+"','"+txtfname+"');";
				System.out.println(SQL);
				int sta=db.db_insert(SQL);
				System.out.println(sta);
				if (sta==1)
				{
					sts.setText("Sign Up Sucess");
				}
				
			} 
			catch (SQLException e) 
			{
			// TODO Auto-generated catch block
			System.out.println("Insert Error");
			}
		}
	}
	
	public void cancel(ActionEvent event) 
	{
		try 
		{
			Parent loginView;
			loginView = FXMLLoader.load(getClass().getResource("login.fxml"));
			Scene login_scene=new Scene(loginView);
			Stage window=(Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(login_scene);
			window.show();
			window.setTitle("IRCTC Main Veiw");
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("Window Error");
		}
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		// TODO Auto-generated method stub
		
	}
}
