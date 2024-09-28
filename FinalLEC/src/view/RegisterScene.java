package view;

import java.sql.SQLException;

import connector.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.Pengguna;



public class RegisterScene implements EventHandler<ActionEvent>{

	ObservableList<Pengguna> penggunaList = FXCollections.observableArrayList();
	BorderPane borderPane;
	GridPane gridPane;
	Scene scene;
	Label regLbl, userLbl, tmptKerjaLbl, emailLbl, passLbl, konPassLbl, tlpLbl;
	TextField emailField, userField, tmptField, tlpField;
	PasswordField passField, konpassField;
	Button regisButton;
	MenuBar menuBar;
	Menu menu;
	MenuItem menuItem1, menuItem2;
	
	private Connect connect;
	private main.Main main;
		
	public void initialize() {
		borderPane = new BorderPane();
		gridPane = new GridPane();
		scene = new Scene(borderPane, 750, 700);
		
		regLbl = new Label("REGISTER");
		userLbl = new Label("Nama");
		tmptKerjaLbl = new Label("Tempat Kerja");
		emailLbl = new Label("Email");
		passLbl = new Label("Password");
		konPassLbl = new Label("Konfirmasi Password");
		tlpLbl = new Label("Nomor Telepon");
		
		
		userField = new TextField();
		tmptField = new TextField();
		emailField = new TextField();
		passField = new PasswordField();
		konpassField = new PasswordField();
		tlpField = new TextField();
		
		regisButton = new Button("Sign Up");
		
		menuBar = new MenuBar();
		menu = new Menu("Menu");
		
    	menuItem1 = new MenuItem("Login");
    	menuItem2 = new MenuItem("Register");
		
    	connect = Connect.getInstance();
	}
	
	public void addComponent() {
    	menuBar.getMenus().add(menu);
    	menu.getItems().addAll(menuItem1, menuItem2);
		
		gridPane.addRow(0, regLbl);
		gridPane.addRow(1, userLbl);
		gridPane.addRow(2, userField);
		gridPane.addRow(3, tmptKerjaLbl);
		gridPane.addRow(4, tmptField);
		gridPane.addRow(5, emailLbl);
		gridPane.addRow(6, emailField);
		gridPane.addRow(7, passLbl);
		gridPane.addRow(8, passField);
		gridPane.addRow(9, konPassLbl);
		gridPane.addRow(10, konpassField);
		gridPane.addRow(11, tlpLbl);
		gridPane.addRow(12, tlpField);
		gridPane.addRow(13, regisButton);
	}
	
	public void arrange() {
        gridPane.setPadding(new Insets(20));
        gridPane.setAlignment(Pos.CENTER);
        GridPane.setHalignment(regLbl, HPos.CENTER);
        GridPane.setHalignment(regisButton, HPos.CENTER);
        regLbl.setStyle("-fx-font-weight: bold; -fx-font-size: 54;");
        gridPane.setVgap(5);

        userField.setPrefWidth(200); 
        emailField.setPrefWidth(200); 
        passField.setPrefWidth(200); 
        konpassField.setPrefWidth(200); 
        tlpField.setPrefWidth(200); 
        
        borderPane.setCenter(gridPane);
        borderPane.setTop(menuBar);
	}
	
	public Scene returnScene() {
		return scene;
	}
	
	public void setEventHandler() {
		regisButton.setOnAction(this);
		menuItem1.setOnAction(this);
		menuItem2.setOnAction(this);
	}
	
	public RegisterScene(main.Main main) {
		this.main = main;
		initialize();
		addComponent();
		arrange();
		setEventHandler();
		getData();
	}

	private void getData() {
		String query = "SELECT * FROM pengguna";
		connect.rs = connect.execQueryGetData(query);

		try {
			while (connect.rs.next()) {
				String id = connect.rs.getString("idPengguna");
				String name = connect.rs.getString("namaPengguna");
				String password = connect.rs.getString("tempatKerjaPengguna");
				String email = connect.rs.getString("emailPengguna");
				String phoneNum = connect.rs.getString("nomorTelpPengguna");
				String pass = connect.rs.getString("passwordPengguna");
				
				Pengguna pgn = new Pengguna(id, name, password, email, phoneNum, pass);
				penggunaList.add(pgn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void insertData(String idPengguna, String namaPengguna, String tempatKerjaPengguna, String emailPengguna,
	        String nomorTelpPengguna, String passPengguna) {
	    String query = "INSERT INTO pengguna (idPengguna, namaPengguna, tempatKerjaPengguna, emailPengguna, nomorTelpPengguna, passwordPengguna) "
	            + "VALUES('" + idPengguna + "','" + namaPengguna + "','" + tempatKerjaPengguna + "', '" + emailPengguna + "', '"
	            + nomorTelpPengguna + "', '" + passPengguna + "')";
	    connect.execQuery(query);
	}

	private String setId() {
	    int count = penggunaList.size() + 1;
	    String id;
	    
	    // Ambil ID game terbaru dari database
	    String query = "SELECT idPengguna FROM pengguna ORDER BY idPengguna DESC LIMIT 1";
	    connect.rs = connect.execQueryGetData(query);
	    
	    try {
	        if (connect.rs.next()) {
	            String latestId = connect.rs.getString("idPengguna");
	            int latestCount = Integer.parseInt(latestId.substring(2));
	            count = Math.max(count, latestCount + 1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    id = "PA" + String.format("%03d", count);
	    return id;
	}

	
	
	@Override
	public void handle(ActionEvent event) {
		boolean bool = false;
		if(event.getSource() == regisButton) {
		     for (Pengguna p : penggunaList) {
		    	 bool = false;
				if(p.getEmailPengguna().equals(emailField.getText())) {
					bool = true;
					break;
				}
			}
		     
		     if(userField.getText().length() < 4 || userField.getText().length() > 20 ) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Input Invalid");
					alert.setHeaderText("Username invalid");
					alert.setContentText("Username harus berisi 4-20 karakter");
					alert.show();
		     }
		     
		     else if(!emailField.getText().contains("@")) {
		    		Alert alert = new Alert(AlertType.WARNING);
		    		alert.setTitle("Input Invalid");
					alert.setHeaderText("Email invalid");
					alert.setContentText("Email harus mengandung karakter \"@\"");
					alert.show();
		     }
		     
		     else if(bool == true) {
		    	 	Alert alert = new Alert(AlertType.WARNING);
		    	 	alert.setTitle("Input Invalid");
					alert.setHeaderText("Email invalid");
					alert.setContentText("Email harus unik");
					alert.show();
		     }
		     
		     else if(passField.getText().length() < 6 || passField.getText().length() > 20) {
		    	 	Alert alert = new Alert(AlertType.WARNING);
		    	 	alert.setTitle("Input Invalid");
					alert.setHeaderText("Password invalid");
					alert.setContentText("Password harus berisi 6 – 20 karakter");
					alert.show();
		     }
		     
		     else if(!passField.getText().matches("^[a-zA-Z0-9]+$")) {
			    	 Alert alert = new Alert(AlertType.WARNING);
			    	 alert.setTitle("Input Invalid");
			    	 alert.setHeaderText("Password invalid");
			    	 alert.setContentText("Password harus alphanumeric");
			    	 alert.show();
		     }
		     
		     else if(!konpassField.getText().equals(passField.getText())) {
			    	 Alert alert = new Alert(AlertType.WARNING);
			    	 alert.setTitle("Input Invalid");
			    	 alert.setHeaderText("Konfirmasi Password invalid");
			    	 alert.setContentText("Konfirmasi Password harus sama dengan Password");
			    	 alert.show();		    	 
		     }
		     
		     else if(!tlpField.getText().matches("\\d+")) {
			    	 Alert alert = new Alert(AlertType.WARNING);
			    	 alert.setTitle("Input Invalid");
			    	 alert.setHeaderText("Nomor telepon invalid");
			    	 alert.setContentText("Nomor telepon harus berupa angka");
			    	 alert.show();		    	 		    	 
		     }
		     
		     else if(tlpField.getText().length() < 9 || tlpField.getText().length() > 20) {
			    	 Alert alert = new Alert(AlertType.WARNING);
			    	 alert.setTitle("Input Invalid");
			    	 alert.setHeaderText("Nomor Telepon invalid");
			    	 alert.setContentText("Nomor Telepoon harus berisi 9 – 20 angka");
			    	 alert.show();
		     }
		     
		     else {
		    	 insertData(setId(),userField.getText().toString(), tmptField.getText().toString(), emailField.getText().toString(), 
		    			 	tlpField.getText().toString(), passField.getText().toString());
		    	 getData();
		    	 
		    	 Alert alert = new Alert(AlertType.INFORMATION);
		    	 alert.setHeaderText("Akun Dibuat");
		    	 alert.setContentText("Selamat! Akun kamu telah teregistrasi");
		    	 alert.show();
		     
		    	 main.showLoginScene();
		     }
		     
		  
		}
		
		else if (event.getSource() == menuItem1) {
			main.showLoginScene();
		} 
		else if (event.getSource() == menuItem2) {
			main.showRegistScene();
		}
		
	}

}

