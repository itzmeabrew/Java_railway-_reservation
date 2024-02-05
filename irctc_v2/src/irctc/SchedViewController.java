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

public class SchedViewController implements Initializable
{
	String SQL;
	db_healper db=new db_healper();

    @FXML
    private TableView<ModelTable2> table;

    @FXML
    private TableColumn<ModelTable2, String> tid;

    @FXML
    private TableColumn<ModelTable2, String> tname;

    @FXML
    private TableColumn<ModelTable2, String> ttype;

    @FXML
    private TableColumn<ModelTable2, String> dep;

    @FXML
    private TableColumn<ModelTable2, String> arr;

    @FXML
    private TableColumn<ModelTable2, String> stp;

    @FXML
    private TableColumn<ModelTable2, String> dtime;

    @FXML
    private TableColumn<ModelTable2, String> atime;

    @FXML
    private TableColumn<ModelTable2, String> dst;
    
    ObservableList<ModelTable2> oblist=FXCollections.observableArrayList();
    
    public void initData() 
	{
		String str = "none";
		try 
		{
			FileReader fr=new FileReader("sql2.txt");
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
		// TODO Auto-generated method stub
		initData();
		System.out.println(SQL);
		try
		{
			db.db_connect();
			db.db_retrive(SQL);
			while (db.rs.next())
			{
				oblist.add(new ModelTable2(db.rs.getString("train_id"), db.rs.getString("train_name"), db.rs.getString("train_type"), db.rs.getString("src_stn"), db.rs.getString("trgt_stn"), db.rs.getString("stop_nmbr"), db.rs.getString("dep_time"), db.rs.getString("arr_time"),db.rs.getString("src_dstn")));
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("ERROR");
			e.printStackTrace();
		}
		//oblist.add(new ModelTable2("1","2", "3","4","5","6","7","8","9"));
		//String tid,tname,tclass,source,destination,stp_nmbr,arr_time,dep_time,src_dstn;
		
		// TODO Auto-generated method stub
		tid.setCellValueFactory(new PropertyValueFactory<>("tid"));
		tname.setCellValueFactory(new PropertyValueFactory<>("tname"));
		ttype.setCellValueFactory(new PropertyValueFactory<>("tclass"));
		dep.setCellValueFactory(new PropertyValueFactory<>("source"));
		arr.setCellValueFactory(new PropertyValueFactory<>("destination"));
		stp.setCellValueFactory(new PropertyValueFactory<>("stp_nmbr"));
		atime.setCellValueFactory(new PropertyValueFactory<>("arr_time"));
		dtime.setCellValueFactory(new PropertyValueFactory<>("dep_time"));
		dst.setCellValueFactory(new PropertyValueFactory<>("src_dstn"));
		
		table.setItems(oblist);
	}
	
}
