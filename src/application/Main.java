package application;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
public class Main extends Application {
	BorderPane root;
    public static class FileScanResult {
        private String fileName;
        private String status;
        public FileScanResult(String fileName, String status) {
            this.fileName = fileName;
            this.status = status;
        }
        public String getFileName() {
            return fileName;
        }
        public void setFileName(String fileName) {
            this.fileName = fileName;
        }
        public String getStatus() {
            return status;
        }
        public void setStatus(String status) {
            this.status = status;
        }
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
    	primaryStage.setTitle("Jack");
	    DirectoryChooser directoryChooser = new DirectoryChooser();
	    directoryChooser.setInitialDirectory(new File("C:\\"));
	    FileInputStream input1=new FileInputStream("C:\\Users\\raja\\Documents\\images\\antivirus.png");
		Image icon=new Image(input1);
		primaryStage.getIcons().add(icon);
		FileInputStream input2=new FileInputStream("C:\\Users\\raja\\Documents\\images\\fullScan.png");
		Image fullScanIcon=new Image(input2);
		ImageView fullScan=new ImageView(fullScanIcon);
		FileInputStream input3=new FileInputStream("C:\\Users\\raja\\Documents\\images\\folderScan.png");
		Image folderScanIcon=new Image(input3);
		ImageView folderScan=new ImageView(folderScanIcon);
		FileInputStream input4=new FileInputStream("C:\\Users\\raja\\Documents\\images\\settings.png");
		Image settings=new Image(input4);
		ImageView mySettings=new ImageView(settings);
		Tooltip tooltip1=new Tooltip("Complete scan");
		Tooltip tooltip2=new Tooltip("Scan folder");
		Tooltip tooltip3=new Tooltip("Settings");
		ToolBar toolBar=new ToolBar();
		Region spacer=new Region();
		spacer.setPrefHeight(5);
		Region spacer2=new Region();
		spacer2.setPrefHeight(5);
		Button scanButton=new Button();
		scanButton.getStyleClass().add("no-border-button");
		scanButton.setTooltip(tooltip1);
		scanButton.setGraphic(fullScan);
		Button scanFolderButton=new Button();
		scanFolderButton.getStyleClass().add("no-border-button");
		Button settingsButton2=new Button();
		settingsButton2.getStyleClass().add("no-border-button");
		settingsButton2.setTooltip(tooltip3);
		settingsButton2.setGraphic(mySettings);
		scanFolderButton.setGraphic(folderScan);
		scanFolderButton.setTooltip(tooltip2);
		scanFolderButton.setOnAction(event->{
			DirectoryChooser directoryChooser1 = new DirectoryChooser();
		    File selectedDirectory = directoryChooser1.showDialog(primaryStage);
		    ProgressBar progressBar1=new ProgressBar(0);
		    progressBar1.setPrefWidth(600);
		    progressBar1.setPrefHeight(35);
		    Label statusLabel = new Label("Scanning...");
		    progressBar1.setProgress(0.3);
		    Stage secondStage=new Stage();
			if(selectedDirectory!=null&&selectedDirectory.exists()&&selectedDirectory.isDirectory()) {
			    VBox box=new VBox(progressBar1,statusLabel);
				Scene scene3=new Scene(box,600,100);
				secondStage.setTitle("Jack");
				 FileInputStream input = null;
					try {
						input = new FileInputStream("C:\\Users\\raja\\Documents\\images\\antivirus.png");
						Image icon1=new Image(input);
				   		secondStage.getIcons().add(icon1);
					} 
					catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
				secondStage.setScene(scene3);
				secondStage.setResizable(false);
				secondStage.show();
			}

	        if (selectedDirectory == null) {
	            return;
	        }

	        File[] filesInDirectory = selectedDirectory.listFiles();

	        if (filesInDirectory == null || filesInDirectory.length == 0) {
	            return;
	        }
	        ObservableList<FileScanResult> scanResults = FXCollections.observableArrayList();
	        List<String> maliciousSignatures = new ArrayList<>();
	        Task<Void> scanTask = new Task<>() {
	            @Override
	            protected Void call() {
	            	int scannedFiles = 0;
	                int totalFiles = filesInDirectory.length;
	                for (int i = 0; i < totalFiles; i++) {
	                    File file = filesInDirectory[i];
	                    String fileName;
	                    if(file.isFile()) {
	                    	fileName=file.getName();
	                    try { 
		                        MessageDigest digest = null;
								try {
									digest = MessageDigest.getInstance("MD5");
								} catch (NoSuchAlgorithmException e1) {
									e1.printStackTrace();
								}
		                        Path filePath = Path.of(selectedDirectory.getAbsolutePath()+"\\"+file.getName());
		                        
		                        try (FileInputStream fis = new FileInputStream(filePath.toFile())) {
		                            byte[] buffer = new byte[1024]; // 1 KB buffer
		                            int bytesRead;
		                            while ((bytesRead = fis.read(buffer)) != -1) {
		                                digest.update(buffer, 0, bytesRead);
		                            }
		                        } catch (FileNotFoundException e1) {
									
									e1.printStackTrace();
								} catch (IOException e2) {
									e2.printStackTrace();
								}


		                        byte[] hashBytes = digest.digest();
		                        StringBuilder hexString = new StringBuilder();
		                        for (byte b : hashBytes) {
		                            hexString.append(String.format("%02x", b));
		                        }
		                        String dbURL = "jdbc:sqlite:signatures.db";
		                		HashSet<String> signatures = new HashSet<>();
		                        String query = "SELECT signature FROM malware_signatures";
		                        try (Connection conn = DriverManager.getConnection(dbURL);
		                             PreparedStatement pstmt = conn.prepareStatement(query);
		                             ResultSet rs = pstmt.executeQuery()) {
		                            while (rs.next()) {
		                                signatures.add(rs.getString("signature"));
		                            }
		                            
		                        } catch (Exception e) {
		                            e.printStackTrace();
		                        }
		                        if(signatures.contains(hexString.toString())){
		                        	maliciousSignatures.add("Malicious");
		                        }
		                        else {
		                        	maliciousSignatures.add("Clean");
		                        }
		                    	scannedFiles++;
		                    	updateMessage("Scanning "+ file);
			                    updateProgress(scannedFiles, totalFiles); 
	                    
	                    }
	                    		
	                    catch(Exception e) {
	                    		
	                    	}
	                    String status=maliciousSignatures.contains("Malicious")? "Malicious" : "Clean";
	                    Platform.runLater(() -> {
	                        scanResults.add(new FileScanResult(fileName, status));
	                    });
	                    }
	                    updateProgress(i + 1, totalFiles);
	                }
	                return null;
	            }
	        };
	        statusLabel.textProperty().bind(scanTask.messageProperty());
            progressBar1.progressProperty().bind(scanTask.progressProperty());
	        scanTask.setOnSucceeded(e ->{
        	    secondStage.close();
        	    showResultsStage(scanResults);  
        });
	        secondStage.setOnCloseRequest(event7 -> {
	            if (scanTask != null && scanTask.isRunning()) {
	                scanTask.cancel(); // Cancel the scanning task
	            }
	        });
          scanTask.setOnCancelled(e->{
        	  statusLabel.textProperty().unbind();
        	  statusLabel.setText("Scanning cancelled!");
        	  
          });
          scanTask.setOnFailed(e ->{
        	  statusLabel.textProperty().unbind();
        	  statusLabel.setText("Scanning failed!");
          });
          Thread scanThread = new Thread(scanTask);
          scanThread.setDaemon(true);
          scanThread.start();
		});
		Button quickScan=new Button("");
		quickScan.getStyleClass().add("no-border-button");
		Tooltip tooltip4=new Tooltip("Quick scan");
		quickScan.setTooltip(tooltip4);
		quickScan.setOnAction(event->quickScanLogic());
		FileInputStream input5=new FileInputStream("C:\\Users\\raja\\Documents\\images\\quickScan.png");
		Image quickScanIcon=new Image(input5);
		ImageView quickScanImg=new ImageView(quickScanIcon);
		quickScan.setGraphic(quickScanImg);
		Region spacer3=new Region();
		spacer3.setPrefHeight(5);
		toolBar.getItems().addAll(scanButton,spacer,scanFolderButton,spacer2,settingsButton2,spacer3,quickScan);
		toolBar.setOrientation(Orientation.VERTICAL);
		Button homeButton=new Button("Home");
		homeButton.setId("button-1");
		homeButton.setOnAction(event->homeContent());
        Button scanButton2 = new Button("Scan");
        scanButton2.setId("button-2");
        scanButton2.setOnAction(e->scanContent());
        Button quarantineButton = new Button("Quarantine");
        quarantineButton.setId("button-3");
        quarantineButton.setOnAction(e->quarantineContent());
        Button updateButton = new Button("Update");
        updateButton.setId("button-4");
        updateButton.setOnAction(e->updateContent());
        Button settingsButton = new Button("Settings");
        settingsButton.setId("button-5");
        settingsButton.setOnAction(e->settingsContent());
        Button aboutButton=new Button("About");
        aboutButton.setId("button-6");
        aboutButton.setOnAction(e->{
           Text text=new Text("This is not just an antivirus software it has many functionalities like disk cleaning and disk defragmentation");
           VBox box=new VBox(text);
           Scene scene=new Scene(box,600,400);
           Stage newStage=new Stage();
           newStage.setScene(scene);
           newStage.setResizable(false);
           newStage.setTitle("Jack");
           FileInputStream input = null;
		try {
			input = new FileInputStream("C:\\Users\\raja\\Documents\\images\\antivirus.png");
			Image icon1=new Image(input);
	   		newStage.getIcons().add(icon1);
		} 
		catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
   		   
           newStage.show();
        });
        ToolBar navigationBar = new ToolBar(homeButton,scanButton2, quarantineButton, updateButton, settingsButton,aboutButton);
        root=new BorderPane();
        root.setTop(navigationBar);
        root.setLeft(toolBar);
        Text text=new Text("Home Screen");
    	StackPane homeContent = new StackPane(text);
        root.setCenter(homeContent);
		Scene scene = new Scene(root,1000,600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    private void quickScanLogic() {
		
	}
	private void updateContent() {
    	Text text=new Text("Update Screen");
    	StackPane updateContent = new StackPane(text);
        root.setCenter(updateContent);
	}
	private void quarantineContent() {
		Text text=new Text("Quarantine Screen");
    	StackPane quarantineContent = new StackPane(text);
        root.setCenter(quarantineContent);
	}
	private void homeContent() {
    	Text text=new Text("Home Screen");
    	StackPane homeContent = new StackPane(text);
        root.setCenter(homeContent);
	}
	private void settingsContent() {
		Text text=new Text("Settings Screen");
		StackPane settingsContent=new StackPane(text);
		root.setCenter(settingsContent);
	}
	private void scanContent() {
    	Text text=new Text("Scan Screen");
    	StackPane scanContent = new StackPane(text);
    	root.setCenter(scanContent);	
	}
	@SuppressWarnings({ "unchecked" })
	private void showResultsStage(ObservableList<FileScanResult> scanResults) {
        TableView<FileScanResult> tableView = new TableView<>();
        TableColumn<FileScanResult, String> fileNameColumn = new TableColumn<>("File Name");
        fileNameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getFileName()));
        TableColumn<FileScanResult, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus()));
        statusColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String status, boolean empty) {
                super.updateItem(status, empty);
                if (status == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(status);
                    if (status.equals("Malicious")) {
                        setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                    } else {
                        setStyle("-fx-text-fill: green;");
                    }
                }
            }
        });
        tableView.getColumns().addAll(fileNameColumn, statusColumn);
        tableView.setItems(scanResults);
        VBox root2 = new VBox(tableView);
        root2.setStyle("-fx-padding: 10;");
        Scene scene = new Scene(root2, 600, 400);
        Stage resultsStage = new Stage();
        resultsStage.setTitle("Scan Results");
        resultsStage.setScene(scene);
        resultsStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
