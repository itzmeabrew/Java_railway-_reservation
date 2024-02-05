package irctc;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginController implements Initializable
{
	@FXML
	private TextField uname;
	@FXML
	private TextField pass;
	@FXML
	private Button login,signup;
	@FXML
	private Label sts;
	
	db_healper db =new db_healper();
	
	public void login_tsr(ActionEvent event) 
	{
		db.db_connect();
		String txtUsername,txtPassword,sql;
		
		txtUsername=uname.getText();
		txtPassword=pass.getText();
		sql="select * from irctc_users where usr_id = '"+txtUsername+"' and pass = '"+txtPassword+"';"; 
		
		try
		{
			db.db_retrive(sql);
			int counter = 0;
			while(db.rs.next())
			{
			  counter++;
			}
			System.out.println(counter);
			if (counter==1) 
			{
				FileWriter fw=new FileWriter("user_name.txt");
				PrintWriter pw=new PrintWriter(fw);
				pw.println(txtUsername);
				pw.close();
				System.out.println("User Found");
				Parent homeView = FXMLLoader.load(getClass().getResource("Home.fxml"));
				Scene home_scene=new Scene(homeView);
				Stage window=(Stage) ((Node) event.getSource()).getScene().getWindow();
				window.setScene(home_scene);
				window.show();
				window.setTitle("IRCTC Main Veiw");
			}
			else
			{
				System.out.println("User Not Found");
				sts.setText("Credentials Error");
			}
		}
		catch (SQLException e) 
		{
			System.out.println("Retrive Error");
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("Window Error");
			e.printStackTrace();
		}
		
		System.out.println(sql);
		//System.out.println(db.rs.getString("usr_id"));
	}
	
	public void sign_up(ActionEvent event)
	{
		Parent homeView;
		try 
		{
			homeView = FXMLLoader.load(getClass().getResource("signup.fxml"));
			Scene sign_scene=new Scene(homeView);
			Stage window=(Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(sign_scene);
			window.show();
			window.setTitle("IRCTC Sign Up");
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
