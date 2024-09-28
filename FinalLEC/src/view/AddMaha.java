package view;

import java.sql.SQLException;
import java.util.Optional;

import connector.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Mahasiswa;

public class AddMaha implements EventHandler<ActionEvent>{
	ObservableList<Mahasiswa> mahaList = FXCollections.observableArrayList();
	BorderPane border1, border2;
	GridPane grid1;
	VBox vbox1, vbox2, vbox3;
	HBox hbox1;
	Scene scene;
	Label greetTitle;
	Label title, nNama, nNIM, nUni;
	TableView<Mahasiswa> table; 
	Button add, updt, dlt, clear;
	TextField nNamaField, nNIMField, nUniField;
	MenuBar menuBar;
	Menu menu, menu1;
	MenuItem menuItem1, menuItem2, menuItem3, menuItem4;
	
	private Connect connect;
	private main.Main main;
	
	public void initialize() {
		border1 = new BorderPane();
		border2 = new BorderPane();
		grid1 = new GridPane();
		
		vbox1 = new VBox();
		vbox2 = new VBox();
		vbox3 = new VBox();

		
		hbox1 = new HBox();		
		
		scene = new Scene(border1, 750, 700);
		
		greetTitle = new Label("Hello, " + LoginScene.getName);
		
		title = new Label("Menu");
		
		add = new Button("Add");
		updt = new Button("Update");
		dlt = new Button("Delete");
		clear = new Button("Clear");
		
		nNIM = new Label("NIM Mahasiswa");
		nNama = new Label("Nama Mahasiswa");
		nUni = new Label("Universitas Mahasiswa");
		nNamaField = new TextField();
		nNIMField = new TextField();
		nUniField = new TextField();
		
		table = new TableView<Mahasiswa>();
		
		menuBar = new MenuBar();
		menu = new Menu("Dashboard");
		menu1 = new Menu("Log Out");
		
		menuItem1 = new MenuItem("Main");
		menuItem2 = new MenuItem("Tambah Mahasiswa");
		menuItem3 = new MenuItem("Tambah Mata Kuliah");
		menuItem4 = new MenuItem("Log Out");
		
		connect = Connect.getInstance();
	}
	
	public void addComponent() {
		menuBar.getMenus().addAll(menu, menu1);
		menu.getItems().addAll(menuItem1, menuItem2, menuItem3);
		menu1.getItems().addAll(menuItem4);
		
		vbox1.getChildren().add(title);
		vbox2.getChildren().addAll(nNIM, nNIMField, nNama, nNamaField, nUni, nUniField);
		hbox1.getChildren().addAll(add, updt, dlt, clear);
		
		grid1.add(table, 0, 0);
		grid1.add(vbox1, 0, 1);
		grid1.add(vbox2, 0, 2);
		grid1.add(hbox1, 0, 3);
	}
	
	public void arrange() {
		grid1.setAlignment(Pos.CENTER);
		greetTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 25;");
		title.setStyle("-fx-font-weight: bold; -fx-font-size: 18;");
		BorderPane.setAlignment(greetTitle, Pos.CENTER);
		vbox1.setAlignment(Pos.CENTER);
		vbox2.setAlignment(Pos.CENTER);
		hbox1.setAlignment(Pos.CENTER);
		
		grid1.setVgap(10);
		hbox1.setSpacing(10);
	
		nNama.setWrapText(true);

		border1.setCenter(border2);
		border2.setCenter(grid1);
		
		nNamaField.setMinWidth(250);
		nNamaField.setMaxWidth(250);
		
		
		nNIMField.setMinWidth(250);
		nNIMField.setMaxWidth(250);
		
		nUniField.setMinWidth(250);
		nUniField.setMaxWidth(250);
		
		border1.setTop(menuBar);
		border2.setTop(greetTitle);	
	}
	
	public void setTable() {
		TableColumn<Mahasiswa, String> NIMMaha = new  TableColumn<Mahasiswa, String>("NIM");
		NIMMaha.setCellValueFactory(new PropertyValueFactory<Mahasiswa, String>("NIM"));
		NIMMaha.setMinWidth(border1.getWidth()/ 3);
		
		TableColumn<Mahasiswa, String> namaMaha = new  TableColumn<Mahasiswa, String>("namaMahasiswa");
		namaMaha.setCellValueFactory(new PropertyValueFactory<Mahasiswa, String>("namaMahasiswa"));
		namaMaha.setMinWidth(border1.getWidth()/ 3);
		
		TableColumn<Mahasiswa, String> uniMaha = new  TableColumn<Mahasiswa, String>("asalUniversitas");
		uniMaha.setCellValueFactory(new PropertyValueFactory<Mahasiswa, String>("asalUni"));
		uniMaha.setMinWidth(border1.getWidth()/ 3);
		

		table.getColumns().addAll(NIMMaha, namaMaha, uniMaha);
	}

	private void clearSelectedRow() {
	    Mahasiswa selectedItem = table.getSelectionModel().getSelectedItem();
	    if (selectedItem != null) {
	        refreshTable();

	    } else {
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("No Selection");
	        alert.setHeaderText("Tidak ada baris yang dipilih");
	        alert.setContentText("Pilih salah satu baris");
	        alert.show();
	    }
	}
	
	public void setEventHandler() {
		add.setOnAction(this);
		updt.setOnAction(this);
		dlt.setOnAction(this);
		menuItem1.setOnAction(this);
		menuItem2.setOnAction(this);
		menuItem3.setOnAction(this);
		menuItem4.setOnAction(this);
		
		table.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.getSource() == table) {
					TableSelectionModel<Mahasiswa> tsm = table.getSelectionModel(); 
					tsm.setSelectionMode(SelectionMode.SINGLE);

					Mahasiswa m = tsm.getSelectedItem();
					
					 if (m != null) {
						 	nNIMField.setText(m.getNIM());
			                nNamaField.setText(m.getNamaMahasiswa());
			                nUniField.setText(m.getAsalUni());
			            }
				}
			}
			
		});
		
		clear.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		        clearSelectedRow();
		    }
		});

	}
	
	public Scene returnScene() {
		return scene;
	}
	private void getData() {
		mahaList.clear(); 
		
		String query = "SELECT * FROM mahasiswa";
		connect.rs = connect.execQueryGetData(query);
		
		try {
			while(connect.rs.next()) {
				String NIM = connect.rs.getString("NIM");
				String nama = connect.rs.getString("namaMahasiswa");
				String uni = connect.rs.getString("asalUniversitas");
				
				mahaList.add(new Mahasiswa(NIM, nama, uni));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void refreshField() {
		nNIMField.setText("");
		nNamaField.setText("");
		nUniField.setText("");
	}
	
	public void refreshTable() {
		getData();
		ObservableList<Mahasiswa> newmList = FXCollections.observableArrayList(mahaList);
		table.setItems(newmList);
		
		refreshField();
	}

	public void addData(String NIM, String namaMahasiswa, String asalUni) {
		String query = String.format("INSERT INTO mahasiswa VALUES ('%s', '%s', '%s')", 
				NIM, namaMahasiswa, asalUni);
	    connect.execQuery(query);
	}

	
	public void updateData(String NIM, String namaMahasiswa, String asalUni) {
		String query = String.format("UPDATE mahasiswa "
				+ "SET namaMahasiswa = '%s', asalUniversitas = '%s' "
				+ "Where NIM = '%s'", namaMahasiswa, asalUni, NIM);
		connect.execQuery(query);	
	}
	
	public void deleteData(String delete){
		String query = String.format("DELETE FROM mahasiswa WHERE NIM = '%s'", delete);
		connect.execQuery(query);
	}
	
	@Override
	public void handle(ActionEvent event) {
		boolean bool = false;
		if(event.getSource() == add) {
			for (Mahasiswa m : mahaList) {
				bool = false;
				if(m.getNIM().equals(nNIMField.getText())) {
					bool = true;
					break;
				}
			}
			
			if(bool == true) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Invalid Request");
				alert.setHeaderText("NIM Invalid");
				alert.setContentText("NIM sudah terdaftar dalam sistem");
				alert.show();
			}
			else if(bool == false) {
				String NIM = nNIMField.getText();
				String nama = nNamaField.getText();
				String uni = nUniField.getText();
				
				addData(NIM, nama, uni);
				refreshTable();
				
				nNIMField.clear();
				nNamaField.clear();
				nUniField.clear();
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Input Sukses");
				alert.setHeaderText("Data Ditambah");
				alert.setContentText("Data Nilai Mahasiswa " + nNIMField.getText() + " berhasil ditambah");
				alert.show();
			}
		}
		
		else if(event.getSource() == updt) {	
			//Hanya bisa update nilai
			String NIM = nNIMField.getText();
			String nama = nNamaField.getText();
			String uni = nUniField.getText();
			
			updateData(NIM, nama, uni);
			refreshTable();
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Update Sukses");
			alert.setHeaderText("Data Diganti");
			alert.setContentText("Data Mahasiswa " + nNIMField.getText() + " berhasil diganti");
			alert.show();
		}
		
		else if(event.getSource() == dlt) {		
			    Mahasiswa selectedNilai = table.getSelectionModel().getSelectedItem();
			    
			    if (selectedNilai != null) {
			        Alert alert = new Alert(AlertType.CONFIRMATION);
			        alert.setTitle("Delete Data");
			        alert.setHeaderText(null);
			        alert.setContentText("Yakin untuk menghapus data mahasiswa?");
			        
			        alert.showAndWait();

			        Optional<ButtonType> result = alert.showAndWait();

			        if (result.isPresent() && result.get() == ButtonType.OK) {
			            String delete = selectedNilai.getNIM();
			            deleteData(delete);
			            refreshTable();
			            
			        }
			    } 
			    else {
			        Alert alert = new Alert(AlertType.INFORMATION);
			        alert.setTitle("Information");
			        alert.setHeaderText(null);
			        alert.setContentText("Pilih data yang ingin dihapus dari tabel.");
			    }
			

		}
		
			if(event.getSource() == menuItem1) {
				main.showMainScene();
			}
			
			else if(event.getSource() == menuItem2) {
				main.showAddMaha();
			}
			
			else if(event.getSource() == menuItem3) {
				main.showAddMatkul();
			}
			
			else if(event.getSource() == menuItem4) {
				main.showLoginScene();
			}
	}

	
	public AddMaha(main.Main main) {
		this.main = main;
		initialize();
		addComponent();
		arrange();
		setTable();
		setEventHandler();
		refreshTable();
	}

}

