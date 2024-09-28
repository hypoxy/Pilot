package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	Stage stage;
	Scene scene;
	
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		showLoginScene();
	}
	
	public void showLoginScene() {
		view.LoginScene login = new view.LoginScene(this);
		scene = login.returnScene();
        stage.setTitle("MScore");
        stage.setScene(scene);
        stage.show();
	}
	
	public void showRegistScene() {
		view.RegisterScene regis = new view.RegisterScene(this);
		scene = regis.returnScene();
		stage.setTitle("MScore");
		stage.setScene(scene);
		stage.show();
	}
	
	public void showMainScene() {
		view.MainScene main = new view.MainScene(this);
		scene = main.returnScene();
		stage.setTitle("MScore");
		stage.setScene(scene);
		stage.show();
	}
	
	public void showAddMaha() {
		view.AddMaha main = new view.AddMaha(this);
		scene = main.returnScene();
		stage.setTitle("MScore");
		stage.setScene(scene);
		stage.show();
	}

	public void showAddMatkul() {
		view.AddMatkul main = new view.AddMatkul(this);
		scene = main.returnScene();
		stage.setTitle("MScore");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}


}
