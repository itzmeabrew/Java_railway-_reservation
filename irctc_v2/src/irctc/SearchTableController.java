package irctc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SearchTableController implements Initializable
{
	String SQL;
	db_healper db=new db_healper();
	
	@FXML
	private TableView<ModelTable> table;
	@FXML
	private TableColumn<ModelTable, String> col_tname;
	@FXML
	private TableColumn<ModelTable, String> col_tclass;
	@FXML
	private TableColumn<ModelTable, String> col_tsrc;
	@FXML
	private TableColumn<ModelTable, String> col_tdst;
	@FXML
	private TableColumn<ModelTable, String> col_tdate;
	
	ObservableList<ModelTable> oblist=FXCollections.observableArrayList();
	
	public void initData() 
	{
		String str = "none";
		try 
		{
			FileReader fr=new FileReader("sql.txt");
			BufferedReader br=new BufferedReader(fr);
			while ((str=br.readLine())!=null) 
			{
				System.out.println(str);
				SQL=str;
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		initData();
		System.out.println(SQL);
		try
		{
			db.db_connect();
			db.db_retrive(SQL);
			while (db.rs.next())
			{
				oblist.add(new ModelTable(db.rs.getString("train_name"), db.rs.getString("train_type"), db.rs.getString("src_stn"),db.rs.getString("trgt_stn"), db.rs.getString("avail_date")));
				//oblist.add(new ModelTable("1","2", "3","4","5"));

			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("ERROR");
			e.printStackTrace();
		}
		//oblist.add(new ModelTable("1","2", "3","4","5"));
		
		// TODO Auto-generated method stub
		col_tname.setCellValueFactory(new PropertyValueFactory<>("tname"));
		col_tclass.setCellValueFactory(new PropertyValueFactory<>("tclass"));
		col_tsrc.setCellValueFactory(new PropertyValueFactory<>("source"));
		col_tdst.setCellValueFactory(new PropertyValueFactory<>("destination"));
		col_tdate.setCellValueFactory(new PropertyValueFactory<>("date"));
		
		table.setItems(oblist);
	}

}
