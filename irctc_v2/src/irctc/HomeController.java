package irctc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HomeController implements Initializable
{	
	@FXML
    private JFXButton book;

    @FXML
    private JFXButton sced;

    @FXML
    private JFXButton pnr;

    @FXML
    private Label name_lbl;
    
    @FXML
    private JFXButton srch;
    
	String user_name;
	
	public void initData() 
	{
		String str = "none";
		try 
		{
			FileReader fr=new FileReader("user_name.txt");
			BufferedReader br=new BufferedReader(fr);
			while ((str=br.readLine())!=null) 
			{
				System.out.println(str);
				user_name=str;
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
	
	public void train_search(ActionEvent event)
	{
		try 
		{
			Parent SearchView;
			SearchView = FXMLLoader.load(getClass().getResource("search.fxml"));
			Scene login_scene=new Scene(SearchView);
			Stage window=(Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(login_scene);
			window.show();
			window.setTitle("Search Trains");
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("Window Error");
			e.printStackTrace();
		}
		
	}
	
	public void pnr_status(ActionEvent event)
	{
		try 
		{
			Parent pnrView;
			pnrView = FXMLLoader.load(getClass().getResource("pnr_ask.fxml"));
			Scene pnr_scene=new Scene(pnrView);
			Stage pnr_window=(Stage) ((Node) event.getSource()).getScene().getWindow();
			pnr_window.setScene(pnr_scene);
			pnr_window.show();
			pnr_window.setTitle("Check PNR");
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("Window Error");
		}
	}
	
	public void schedule(ActionEvent event)
	{
		try 
		{
			Parent schedView;
			schedView = FXMLLoader.load(getClass().getResource("train_sced.fxml"));
			Scene sced_scene=new Scene(schedView);
			Stage sch_window=(Stage) ((Node) event.getSource()).getScene().getWindow();
			sch_window.setScene(sced_scene);
			sch_window.show();
			sch_window.setTitle("Schedule");
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("Window Error");
		}
	}
	
	public void bookNow(ActionEvent event)
	{
		try 
		{
			Parent bookView;
			bookView = FXMLLoader.load(getClass().getResource("Book_tickets.fxml"));
			Scene book_scene=new Scene(bookView);
			Stage bk_window=(Stage) ((Node) event.getSource()).getScene().getWindow();
			bk_window.setScene(book_scene);
			bk_window.show();
			bk_window.setTitle("Book Here");
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("Window Error");
		}
	}
	
	public void log_out(ActionEvent event)
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

	public void initialize(URL location, ResourceBundle resources) 
	{
		initData();
		name_lbl.setText(user_name+", Welcome to your dashboard");
		
	}
}
