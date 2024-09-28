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

public class LoginScene implements EventHandler<ActionEvent> {

	ObservableList<Pengguna> penggunaList = FXCollections.observableArrayList();
	BorderPane borderPane;
	Scene scene;
	Label emailLbl, passLbl, titleLbl, loginLbl;
	GridPane gridPane;
	TextField textField;
	PasswordField passField;
	Button signInButton;
	MenuBar menuBar;
	Menu menu;
	MenuItem menuItem1, menuItem2;
	static String getId;
	static String getName;

	private Connect connect;
	private main.Main main;

	public void initialize() {
		borderPane = new BorderPane();
		gridPane = new GridPane();
		scene = new Scene(borderPane, 750, 700);

		loginLbl = new Label("LOGIN");
		emailLbl = new Label("Email");
		passLbl = new Label("Password");

		textField = new TextField();
		passField = new PasswordField();

		signInButton = new Button();

		menuBar = new MenuBar();
		menu = new Menu("Menu");

		menuItem1 = new MenuItem("Login");
		menuItem2 = new MenuItem("Register");

		connect = Connect.getInstance();
	}

	public void addComponent() {
		signInButton.setText("Sign In");

		menuBar.getMenus().add(menu);
		menu.getItems().addAll(menuItem1, menuItem2);

		gridPane.addRow(0, loginLbl);
		gridPane.addRow(1, emailLbl);
		gridPane.addRow(2, textField);
		gridPane.addRow(3, passLbl);
		gridPane.addRow(4, passField);
		gridPane.addRow(5, signInButton);
	}

	public void arrange() {
		gridPane.setPadding(new Insets(20));
		gridPane.setAlignment(Pos.CENTER);
		GridPane.setHalignment(signInButton, HPos.CENTER);
		GridPane.setHalignment(loginLbl, HPos.CENTER);
		loginLbl.setStyle("-fx-font-weight: bold; -fx-font-size: 40;");
		gridPane.setVgap(5);

		// bingung
		textField.setPrefWidth(200);
		passField.setPrefWidth(200);

		borderPane.setCenter(gridPane);
		borderPane.setTop(menuBar);
	}

	public Scene returnScene() {
		return scene;
	}

	public void setEventHandler() {
		signInButton.setOnAction(this);
		menuItem1.setOnAction(this);
		menuItem2.setOnAction(this);
	}

	public LoginScene(main.Main main) {
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

	@Override
	public void handle(ActionEvent event) {    
		boolean bool = false;
		if (event.getSource() == signInButton) {
			for (Pengguna p : penggunaList) {
				if (p.getEmailPengguna().equals(textField.getText()) && p.getPassPengguna().equals(passField.getText())) {
					bool = true;
					getId = p.getIdPengguna();
					getName = p.getNamaPengguna();
					break;
				}
			}
			
			if(bool == false) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Invalid Request");
				alert.setHeaderText("Kesalahan Penulisan");
				alert.setContentText("Email atau Password yang dimasukkan invalid");
				alert.show();
			}
			
			else if(bool == true) {
				main.showMainScene();
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
