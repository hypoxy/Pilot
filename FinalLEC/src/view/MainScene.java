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
import model.Nilai;

public class MainScene implements EventHandler<ActionEvent>{
	ObservableList<Nilai> nilaiList = FXCollections.observableArrayList();
	BorderPane border1, border2;
	GridPane grid1;
	VBox vbox1, vbox2, vbox3;
	HBox hbox1;
	Scene scene;
	Label greetTitle;
	Label title, nMatkul, nNIM, nNilai;
	TableView<Nilai> table; 
	Button add, updt, dlt, clear;
	TextField nMatkulField, nNIMField, nNilaiField;
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
		
		nMatkul = new Label("Id Matakuliah");
		nNIM = new Label("NIM Mahasiswa");
		nNilai = new Label("Nilai Mahasiswa");
		nMatkulField = new TextField();
		nNIMField = new TextField();
		nNilaiField = new TextField();
		
		table = new TableView<Nilai>();
		
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
		vbox2.getChildren().addAll(nMatkul, nMatkulField, nNIM, nNIMField, nNilai, nNilaiField);
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
	
		nMatkul.setWrapText(true);

		border1.setCenter(border2);
		border2.setCenter(grid1);
		
		nMatkulField.setMinWidth(250);
		nMatkulField.setMaxWidth(250);
		
		
		nNIMField.setMinWidth(250);
		nNIMField.setMaxWidth(250);
		
		nNilaiField.setMinWidth(250);
		nNilaiField.setMaxWidth(250);
		
		border1.setTop(menuBar);
		border2.setTop(greetTitle);	
	}
	
	public void setTable() {
		TableColumn<Nilai, Integer> idNilaiColumn = new  TableColumn<Nilai, Integer>("idNilai");
		idNilaiColumn.setCellValueFactory(new PropertyValueFactory<Nilai, Integer>("idNilai"));
		idNilaiColumn.setMinWidth(border1.getWidth()/ 7);
		
		TableColumn<Nilai, String> NIMColumn = new  TableColumn<Nilai, String>("NIM");
		NIMColumn.setCellValueFactory(new PropertyValueFactory<Nilai, String>("NIM"));
		NIMColumn.setMinWidth(border1.getWidth()/ 7);
		
		TableColumn<Nilai, String> idMatkulColumn = new  TableColumn<Nilai, String>("idMataKuliah");
		idMatkulColumn.setCellValueFactory(new PropertyValueFactory<Nilai, String>("idMataKuliah"));
		idMatkulColumn.setMinWidth(border1.getWidth()/ 7);
		
		TableColumn<Nilai, String> idPenggunaColumn = new  TableColumn<Nilai, String>("idPengguna");
		idPenggunaColumn.setCellValueFactory(new PropertyValueFactory<Nilai, String>("idPengguna"));
		idPenggunaColumn.setMinWidth(border1.getWidth()/ 7);
		
		TableColumn<Nilai, Integer> jumlahNilaiColumn = new  TableColumn<Nilai, Integer>("jumlahNilai");
		jumlahNilaiColumn.setCellValueFactory(new PropertyValueFactory<Nilai, Integer>("jumlahNilai"));
		jumlahNilaiColumn.setMinWidth(border1.getWidth()/ 7);
		
		TableColumn<Nilai, String> gradeColumn = new  TableColumn<Nilai, String>("grade");
		gradeColumn.setCellValueFactory(new PropertyValueFactory<Nilai, String>("grade"));
		gradeColumn.setMinWidth(border1.getWidth()/ 7);
		
		TableColumn<Nilai, String> statusColumn = new  TableColumn<Nilai, String>("status");
		statusColumn.setCellValueFactory(new PropertyValueFactory<Nilai, String>("status"));
		statusColumn.setMinWidth(border1.getWidth()/ 7);
		
		table.getColumns().addAll(idNilaiColumn, NIMColumn, idMatkulColumn, idPenggunaColumn, jumlahNilaiColumn, gradeColumn, statusColumn);
	}

	private void clearSelectedRow() {
	    Nilai selectedItem = table.getSelectionModel().getSelectedItem();
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
					TableSelectionModel<Nilai> tsm = table.getSelectionModel(); 
					tsm.setSelectionMode(SelectionMode.SINGLE);

					Nilai n = tsm.getSelectedItem();
					
					 if (n != null) {
			                nMatkulField.setText(n.getIdMataKuliah());
			                nNIMField.setText(n.getNIM());
			                nNilaiField.setText(String.valueOf(n.getJumlahNilai()));
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
		nilaiList.clear(); 
		
		String query = "SELECT * FROM nilai";
		connect.rs = connect.execQueryGetData(query);
		
		try {
			while(connect.rs.next()) {
				int id = connect.rs.getInt("idNilai");
				String NIM = connect.rs.getString("NIM");
				String idMatkul = connect.rs.getString("idMataKuliah");
				String idPeng = connect.rs.getString("idPengguna");
				int nilai = connect.rs.getInt("jumlahNilai");
				String grade = connect.rs.getString("grade");
				String stat = connect.rs.getString("status");
				
				nilaiList.add(new Nilai(id, NIM, idMatkul, idPeng, nilai, grade, stat));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void refreshField() {
		nMatkulField.setText("");
		nNIMField.setText("");
		nNilaiField.setText("");
	}
	
	public void refreshTable() {
		getData();
		ObservableList<Nilai> newnList = FXCollections.observableArrayList(nilaiList);
		table.setItems(newnList);
		
		refreshField();
	}
	
	private String determineGrade(int nilai) {
	    if (nilai >= 90 && nilai <= 100) {
	        return "A";
	    } else if (nilai >= 85 && nilai <= 89) {
	        return "A-";
	    } else if (nilai >= 80 && nilai <= 84) {
	        return "B+";
	    } else if (nilai >= 75 && nilai <= 79) {
	        return "B";
	    } else if (nilai >= 70 && nilai <= 74) {
	        return "B-";
	    } else if (nilai >= 65 && nilai <= 69) {
	        return "C";
	    } else if (nilai >= 50 && nilai <= 64) {
	        return "D";
	    } else {
	        return "E";
	    }
	}

	private String determineStatus(String grade) {
	    return (grade.equals("A") || grade.equals("A-") || grade.equals("B+") || grade.equals("B") || grade.equals("B-"))
	            ? "PASS" : "FAIL";
	}

	
	public void addData(String NIM, String idMataKuliah, String idPengguna, int jumlahNilai) {
		String grade = determineGrade(jumlahNilai);
	    String status = determineStatus(grade);
	    int id = nilaiList.size() + 1;
	    
	    String query = String.format("INSERT INTO nilai VALUES(%d, '%s', '%s', '%s', '%s', '%s', '%s');",
	    		id, NIM, idMataKuliah, idPengguna, jumlahNilai, grade, status);
	    connect.execQuery(query);
	}

	
	public void updateData(int idNilai, int jumlahNilai) {
	    String grade = determineGrade(jumlahNilai);
	    String status = determineStatus(grade);
		
		String query = String.format("UPDATE nilai set jumlahNilai = %d, grade = '%s', status = '%s'"
				+ " WHERE idNilai = %d",  jumlahNilai, grade, status, idNilai);
		connect.execQuery(query);	
	}
	
	public void deleteData(int delete){
		String query = String.format("DELETE FROM nilai WHERE idNilai = %d", delete);
		
		connect.execQuery(query);
		
	}

	@Override
	public void handle(ActionEvent event) {
		if(event.getSource() == add) {
			String nidMatkul = nMatkulField.getText();
			String nNIM = nNIMField.getText();
			String nidPengguna = LoginScene.getId;
			int nJumNilai = Integer.valueOf(nNilaiField.getText()); 
			
			addData(nNIM, nidMatkul, nidPengguna, nJumNilai);
			refreshTable();
			
			nMatkulField.clear();
			nNIMField.clear();
			nNilaiField.clear();
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Input Sukses");
			alert.setHeaderText("Data Ditambah");
			alert.setContentText("Data Nilai Mahasiswa " + nNIMField.getText() + " berhasil ditambah");
			alert.show();
			
		}
		
		else if(event.getSource() == updt) {	
			//Hanya bisa update nilai
			int nJumNilai = Integer.valueOf(nNilaiField.getText()); 
			int id = table.getSelectionModel().getSelectedItem().getIdNilai();
			
			updateData(id, nJumNilai);;
			refreshTable();
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Update Sukses");
			alert.setHeaderText("Nilai Diganti");
			alert.setContentText("Nilai Mahasiswa " + nNIMField.getText() + " berhasil diganti");
			alert.show();
		}
		
		else if(event.getSource() == dlt) {		
			    Nilai selectedNilai = table.getSelectionModel().getSelectedItem();
			    
			    if (selectedNilai != null) {
			        Alert alert = new Alert(AlertType.CONFIRMATION);
			        alert.setTitle("Delete Data");
			        alert.setHeaderText(null);
			        alert.setContentText("Yakin untuk menghapus data nilai?");
			       
			        Optional<ButtonType> result = alert.showAndWait();

			        if (result.isPresent() && result.get() == ButtonType.OK) {
			            int delete = selectedNilai.getIdNilai();
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

	
	public MainScene(main.Main main) {
		this.main = main;
		initialize();
		addComponent();
		arrange();
		setTable();
		setEventHandler();
		refreshTable();
	}

}
