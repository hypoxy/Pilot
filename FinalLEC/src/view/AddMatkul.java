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
import model.MataKuliah;
import model.Nilai;

public class AddMatkul implements EventHandler<ActionEvent>{
	ObservableList<MataKuliah> matkulList = FXCollections.observableArrayList();
	BorderPane border1, border2;
	GridPane grid1;
	VBox vbox1, vbox2, vbox3;
	HBox hbox1;
	Scene scene;
	Label greetTitle;
	Label title, nidMatkul, nNamaMatkul;
	TableView<MataKuliah> table; 
	Button add, updt, dlt, clear;
	TextField nidMatkulField, nNamaMatkulField;
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
		
		nidMatkul = new Label("Id Mata Kuliah");
		nNamaMatkul = new Label("Nama Mata Kuliah");
		nidMatkulField = new TextField();
		nNamaMatkulField = new TextField();
		
		table = new TableView<MataKuliah>();
		
		menuBar = new MenuBar();
		menu = new Menu("Dashboard");
		menu1 = new Menu("Log Out");
		
		menuItem1 = new MenuItem("Main");
		menuItem2 = new MenuItem("Tambah Mahasiswa");
		menuItem3 = new MenuItem("Tambah Universitas");
		menuItem4 = new MenuItem("Log Out");
		
		connect = Connect.getInstance();
	}
	
	public void addComponent() {
		menuBar.getMenus().addAll(menu, menu1);
		menu.getItems().addAll(menuItem1, menuItem2, menuItem3);
		menu1.getItems().addAll(menuItem4);
		
		vbox1.getChildren().add(title);
		vbox2.getChildren().addAll(nidMatkul, nidMatkulField, nNamaMatkul, nNamaMatkulField);
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
	
		nidMatkul.setWrapText(true);

		border1.setCenter(border2);
		border2.setCenter(grid1);
		
		nidMatkulField.setMinWidth(250);
		nidMatkulField.setMaxWidth(250);
		
		nNamaMatkul.setMinWidth(250);
		nNamaMatkulField.setMaxWidth(250);
		
		border1.setTop(menuBar);
		border2.setTop(greetTitle);	
	}
	
	public void setTable() {		
		TableColumn<MataKuliah, String> idColumn = new  TableColumn<MataKuliah, String>("idMataKuliah");
		idColumn.setCellValueFactory(new PropertyValueFactory<MataKuliah, String>("idMatKul"));
		idColumn.setMinWidth(border1.getWidth()/ 2);
		
		TableColumn<MataKuliah, String> namaColumn = new  TableColumn<MataKuliah, String>("namaMataKuliah");
		namaColumn.setCellValueFactory(new PropertyValueFactory<MataKuliah, String>("namaMatKul"));
		namaColumn.setMinWidth(border1.getWidth()/ 2);
		
		table.getColumns().addAll(idColumn, namaColumn);
	}

	private void clearSelectedRow() {
	    MataKuliah selectedItem = table.getSelectionModel().getSelectedItem();
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
					TableSelectionModel<MataKuliah> tsm = table.getSelectionModel(); 
					tsm.setSelectionMode(SelectionMode.SINGLE);

					MataKuliah m = tsm.getSelectedItem();
					
					 if (m != null) {
			                nidMatkulField.setText(m.getIdMatKul());
			                nNamaMatkulField.setText(m.getNamaMatKul());
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
		matkulList.clear(); 
		
		String query = "SELECT * FROM matakuliah";
		connect.rs = connect.execQueryGetData(query);
		
		try {
			while(connect.rs.next()) {
				String id = connect.rs.getString("idMataKuliah");
				String nama = connect.rs.getString("namaMataKuliah");
				
				matkulList.add(new MataKuliah(id, nama));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void refreshField() {
		nidMatkulField.setText("");
		nNamaMatkulField.setText("");
	}
	
	public void refreshTable() {
		getData();
		ObservableList<MataKuliah> newmList = FXCollections.observableArrayList(matkulList);
		table.setItems(newmList);
		
		refreshField();
	}
	

	public void addData(String idMatKul, String namaMatKul) {
	    String query = String.format("INSERT INTO matakuliah VALUES ('%s', '%s');",
	            idMatKul, namaMatKul);
	    connect.execQuery(query);
	}
	
	public void updateData(String idMatKul, String namaMatKul) {
		String query = String.format("UPDATE matakuliah SET namaMataKuliah = '%s' "
				+ "WHERE idMataKuliah = '%s'", namaMatKul, idMatKul);
		connect.execQuery(query);	
	}
	
	public void deleteData(String delete){
		String query = String.format("DELETE FROM matakuliah WHERE idMataKuliah = '%s'", delete);
		connect.execQuery(query);
	}
	
	@Override
	public void handle(ActionEvent event) {
		boolean bool = false;
		for (MataKuliah m : matkulList) {
			bool = false;
			if(m.getIdMatKul().equals(nidMatkulField.getText())) {
				bool = true;
				break;
			}
		}
		
		if(event.getSource() == add) {
			String id = nidMatkulField.getText();
			String nama = nNamaMatkulField.getText();
			
			addData(id, nama);
			refreshTable();
			
			nidMatkulField.clear();
			nNamaMatkulField.clear();
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Input Sukses");
			alert.setHeaderText("Data Ditambah");
			alert.setContentText("Data Nilai Mahasiswa " + nidMatkulField.getText() + " berhasil ditambah");
			alert.show();
			
		}
		
		else if(event.getSource() == updt) {	
			String id = nidMatkulField.getText();
			String nama = nNamaMatkulField.getText();
			
			updateData(id, nama);
			refreshTable();
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Update Sukses");
			alert.setHeaderText("Nilai Diganti");
			alert.setContentText("Nilai Mahasiswa " + nidMatkulField.getText() + " berhasil diganti");
			alert.show();
		}
		
		else if(event.getSource() == dlt) {		
			    MataKuliah selectedNilai = table.getSelectionModel().getSelectedItem();
			    
			    if (selectedNilai != null) {
			        Alert alert = new Alert(AlertType.CONFIRMATION);
			        alert.setTitle("Delete Data");
			        alert.setHeaderText(null);
			        alert.setContentText("Yakin untuk menghapus data nilai?");

			        alert.showAndWait();
			        
			        Optional<ButtonType> result = alert.showAndWait();

			        if (result.isPresent() && result.get() == ButtonType.OK) {
			            String delete = selectedNilai.getIdMatKul();
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

	
	public AddMatkul(main.Main main) {
		this.main = main;
		initialize();
		addComponent();
		arrange();
		setTable();
		setEventHandler();
		refreshTable();
	}

}

