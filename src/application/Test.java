package application;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Test {

    public static void main(String[] args) {
        String folderPath = "C:/path/to/folder"; // Specify the folder to scan
        try {
            scanFolder(Paths.get(folderPath));
        } catch (IOException e) {
            System.err.println("Error scanning folder: " + e.getMessage());
        }
    }

    public static void scanFolder(Path folderPath) throws IOException {
        Files.walkFileTree(folderPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                // Scan each file
                System.out.println("Scanning file: " + file);
                // Call your file scanning logic here
                scanFile(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                System.err.println("Failed to access file: " + file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("Entering directory: " + dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    public static void scanFile(Path file) {
        // Implement your file scanning logic here
        // Example: Check if the file matches a known virus signature
        System.out.println("Checking file: " + file.getFileName());
    }
}

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.HashSet;
//public class Test{
//	public static void main(String[] args) {
//		String dbURL = "jdbc:sqlite:C:\\Users\\raja\\Documents\\signatures.db";
//		HashSet<String> signatures = new HashSet<>();
//        String query = "SELECT signature FROM malware_signatures";
//        try (Connection conn = DriverManager.getConnection(dbURL);
//             PreparedStatement pstmt = conn.prepareStatement(query);
//             ResultSet rs = pstmt.executeQuery()) {
//            while (rs.next()) {
//                signatures.add(rs.getString("signature"));
//            }
//            System.out.println("Signatures loaded successfully. Total: " + signatures.size());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//		
//	}
//}
//System.out.println("Signatures loaded successfully. Total: " + signatures.size());
//String filePath2 = "C:\\Users\\raja\\Documents\\malware signature files\\md5.txt";
//try (BufferedReader br = new BufferedReader(new FileReader(filePath2))) {
//    String line;
//    while ((line = br.readLine()) != null) {
//    	if(hexString.toString()==line) {
//    		maliciousSignatures.add("Malicious");
//    	}
//    	else {
//    	   maliciousSignatures.add("Clean");
//    	}
//    
//    }
//} catch (IOException e3) {
//    e3.printStackTrace();
//}

//import org.apache.tika.Tika;
//import java.io.File;
//
//public class FileTypeDetector {
//    public static void main(String[] args) {
//        try {
//            File file = new File("path/to/your/file");
//            Tika tika = new Tika();
//
//            // Detect the file type
//            String fileType = tika.detect(file);
//
//            System.out.println("Detected file type: " + fileType);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.io.FileReader;
//import com.opencsv.CSVReader;
//
//public class Test {
//    public static void main(String[] args) {
//        // SQLite database URL
//        String dbURL = "jdbc:sqlite:C:\\Users\\raja\\Documents\\signatures.db";
//        // Path to the CSV file
//        String csvFile = "C:\\Users\\raja\\Documents\\patterns.csv";
//
//        // SQL query to insert data
//        String insertSQL = """
//            INSERT INTO malware_signatures (signature, category)
//            VALUES (?, ?);
//            """;
//
//        try (Connection conn = DriverManager.getConnection(dbURL);
//             CSVReader reader = new CSVReader(new FileReader(csvFile));
//             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
//
//            conn.setAutoCommit(false); // Disable auto-commit to start a transaction
//
//            String[] nextLine;
//            reader.readNext(); // Skip the header row if it exists
//            int batchSize = 0;
//
//            while ((nextLine = reader.readNext()) != null) {
//                // Validate row data
//                if (nextLine.length >= 2) {
//                    pstmt.setString(1, nextLine[0]); // signature
//                    pstmt.setString(2, nextLine[1]); // category
//                    pstmt.addBatch(); // Add to batch
//                    batchSize++;
//                }
//
//                // Execute batch after every 1000 rows
//                if (batchSize % 1000 == 0) {
//                    pstmt.executeBatch();
//                    conn.commit(); // Commit transaction
//                    batchSize = 0;
//                }
//            }
//
//            // Final batch execution
//            pstmt.executeBatch();
//            conn.commit(); // Commit remaining transaction
//
//            System.out.println("Data inserted successfully and efficiently from CSV.");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}

//import com.opencsv.CSVReader;
//import java.io.FileReader;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//public class Test {
//    public static void main(String[] args) {
//        String dbURL = "jdbc:sqlite:C:\\Users\\raja\\Documents\\signatures.db";
//        String csvFile = "C:\\Users\\raja\\Documents\\patterns.csv";
//
//        String insertSQL = """
//            INSERT INTO malware_signatures (signature, category)
//            VALUES (?, ?);
//        """;
//
//        try (Connection conn = DriverManager.getConnection(dbURL);
//             CSVReader reader = new CSVReader(new FileReader(csvFile));
//             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
//
//            String[] nextLine;
//            reader.readNext(); // Skip the header row
//
//            while ((nextLine = reader.readNext()) != null) {
//            	    if(nextLine.length>=2) {
//            	    	pstmt.setString(1, nextLine[0]);                
//                        pstmt.setString(2, nextLine[1]);
//                        pstmt.executeUpdate();
//            	    }
//            	    else {
//            	    	System.out.println("Skipping invalid row: " + String.join(", ", nextLine));
//            	    }
//            		
//               
//            }
//
//            System.out.println("Data inserted successfully from CSV.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Statement;
//public class Test {
//    public static void main(String[] args) {
//        String dbURL = "jdbc:sqlite:C:\\Users\\raja\\Documents\\signatures.db";
//
//        try (Connection conn = DriverManager.getConnection(dbURL)) {
//            if (conn != null) {
//            	System.out.println("Connection established");
//                // Create a table
//                String createTableSQL = """
//                    CREATE TABLE IF NOT EXISTS malware_signatures (
//                        signature TEXT NOT NULL,
//                        category TEXT
//                    );
//                """;
//
//                try (Statement stmt = conn.createStatement()) {
//                    stmt.execute(createTableSQL);
//                    System.out.println("Database and table created successfully.");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}

//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ToolBar;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.StackPane;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//
//public class Test extends Application {
//
//    @Override
//    public void start(Stage primaryStage) {
//        // Create the toolbar
//        ToolBar toolbar = new ToolBar(
//                new Button("Home"),
//                new Button("Scan"),
//                new Button("Settings")
//        );
//
//        // Create the main layout
//        BorderPane borderPane = new BorderPane();
//        borderPane.setTop(toolbar);
//
//        // Create initial content for the center
//        StackPane homeContent = new StackPane(new Text("Home Screen"));
//        borderPane.setCenter(homeContent);
//
//        // Create other scenes' content
//        StackPane scanContent = new StackPane(new Text("Scan Screen"));
//        StackPane settingsContent = new StackPane(new Text("Settings Screen"));
//
//        // Add button actions to change center content
//        ((Button) toolbar.getItems().get(0)).setOnAction(e -> borderPane.setCenter(homeContent));
//        ((Button) toolbar.getItems().get(1)).setOnAction(e -> borderPane.setCenter(scanContent));
//        ((Button) toolbar.getItems().get(2)).setOnAction(e -> borderPane.setCenter(settingsContent));
//
//        // Set up the stage
//        Scene scene = new Scene(borderPane, 800, 600);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Antivirus Software");
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}

//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.StackPane;
//import javafx.stage.Stage;
//public class Test extends Application {
//    @Override
//    public void start(Stage primaryStage) {
//        // Create the BorderPane layout
//        BorderPane borderPane = new BorderPane();
//
//        // Create the toolbar
//        StackPane toolbar = new StackPane();
//        Button homeButton = new Button("Home");
//        Button scanButton = new Button("Scan");
//        Button quarantineButton = new Button("Quarantine");
//        
//        // Add buttons to the toolbar (StackPane for simplicity)
//        toolbar.getChildren().addAll(homeButton, scanButton, quarantineButton);
//        
//        // Set toolbar at the top
//        borderPane.setTop(toolbar);
//
//        // Create content area
//        StackPane contentArea = new StackPane();
//        contentArea.getChildren().add(new Button("Welcome to the Antivirus App!"));
//        
//        // Set the initial scene content
//        borderPane.setCenter(contentArea);
//
//        // Button actions to switch content in the center
//        homeButton.setOnAction(e -> contentArea.getChildren().setAll(new Button("Home Content")));
//        scanButton.setOnAction(e -> contentArea.getChildren().setAll(new Button("Scan Content")));
//        quarantineButton.setOnAction(e -> contentArea.getChildren().setAll(new Button("Quarantine Content")));
//
//        // Create the scene and show it
//        Scene scene = new Scene(borderPane, 600, 400);
//        primaryStage.setTitle("Antivirus Software");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}

//import javafx.application.Application;
//import javafx.application.Platform;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.concurrent.Task;
//import javafx.geometry.Orientation;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.ProgressBar;
//import javafx.scene.control.TableCell;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.ToolBar;
//import javafx.scene.control.Tooltip;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.Priority;
//import javafx.scene.layout.Region;
//import javafx.scene.layout.VBox;
//import javafx.stage.DirectoryChooser;
//import javafx.stage.Stage;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.nio.file.Path;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.ArrayList;
//import java.util.List;
//public class Test extends Application {
//
//    public static class FileScanResult {
//        private String fileName;
//        private String status;
//
//        public FileScanResult(String fileName, String status) {
//            this.fileName = fileName;
//            this.status = status;
//        }
//
//        public String getFileName() {
//            return fileName;
//        }
//
//        public void setFileName(String fileName) {
//            this.fileName = fileName;
//        }
//
//        public String getStatus() {
//            return status;
//        }
//
//        public void setStatus(String status) {
//            this.status = status;
//        }
//    }
//
//    @Override
//    public void start(Stage primaryStage) throws FileNotFoundException {
//    	primaryStage.setTitle("Jack");
//	    DirectoryChooser directoryChooser = new DirectoryChooser();
//	    directoryChooser.setInitialDirectory(new File("C:\\"));
//	    FileInputStream input1=new FileInputStream("C:\\Users\\raja\\Documents\\images\\antivirus.png");
//		Image icon=new Image(input1);
//		primaryStage.getIcons().add(icon);
//		FileInputStream input2=new FileInputStream("C:\\Users\\raja\\Documents\\images\\fullScan.png");
//		Image fullScanIcon=new Image(input2);
//		ImageView fullScan=new ImageView(fullScanIcon);
//		FileInputStream input3=new FileInputStream("C:\\Users\\raja\\Documents\\images\\folderScan.png");
//		Image folderScanIcon=new Image(input3);
//		ImageView folderScan=new ImageView(folderScanIcon);
//		FileInputStream input4=new FileInputStream("C:\\Users\\raja\\Documents\\images\\settings.png");
//		Image settings=new Image(input4);
//		ImageView mySettings=new ImageView(settings);
//		Tooltip tooltip1=new Tooltip("Complete Scan");
//		Tooltip tooltip2=new Tooltip("Scan folder");
//		Tooltip tooltip3=new Tooltip("Settings");
//		ToolBar toolBar=new ToolBar();
//		Region spacer=new Region();
//		spacer.setPrefHeight(5);
//		Region spacer2=new Region();
//		spacer2.setPrefHeight(5);
//		Button scanButton=new Button();
//		scanButton.setTooltip(tooltip1);
//		scanButton.setGraphic(fullScan);
//		Button scanFolderButton=new Button();
//		Button settingsButton2=new Button();
//		settingsButton2.setTooltip(tooltip3);
//		settingsButton2.setGraphic(mySettings);
//		scanFolderButton.setGraphic(folderScan);
//		scanFolderButton.setTooltip(tooltip2);
//		scanFolderButton.setOnAction(event->{
//			DirectoryChooser directoryChooser1 = new DirectoryChooser();
//		    File selectedDirectory = directoryChooser1.showDialog(primaryStage);
//			ProgressBar progressBar1=new ProgressBar(0);
//		    progressBar1.setPrefWidth(600);
//		    progressBar1.setPrefHeight(35);
//		    Label statusLabel = new Label("Scanning...");
//			progressBar1.setProgress(0.3);
//			VBox box=new VBox(progressBar1,statusLabel);
//			Scene scene3=new Scene(box,600,100);
//			Stage secondStage=new Stage();
//			secondStage.setTitle("");
//			secondStage.setScene(scene3);
//			secondStage.show();
//
//	        if (selectedDirectory == null) {
//	            System.out.println("No directory selected!");
//	            return;
//	        }
//
//	        File[] filesInDirectory = selectedDirectory.listFiles();
//
//	        if (filesInDirectory == null || filesInDirectory.length == 0) {
//	            System.out.println("The selected directory is empty!");
//	            return;
//	        }
//
//	        // Signature-based detection
//	        ObservableList<FileScanResult> scanResults = FXCollections.observableArrayList();
//	        List<String> maliciousSignatures = new ArrayList<>();
////	        maliciousSignatures.add("malicious");
////	        maliciousSignatures.add("virus");
//
//	        // Task to simulate file scanning
//	        Task<Void> scanTask = new Task<>() {
//	            @Override
//	            protected Void call() {
//	            	int scannedFiles = 0;
//	                int totalFiles = filesInDirectory.length;
//	                
//	                for (int i = 0; i < totalFiles; i++) {
//	                    File file = filesInDirectory[i];
//	                    String fileName = file.getName();
//
//	                    // Simulate scanning delay
//	                    
//	                    try {
//	                    	if (file.isFile()) {
//		                    	
//		                        //System.out.println("File: " + file.getName());
//		                        MessageDigest digest = null;
//								try {
//									digest = MessageDigest.getInstance("MD5");
//								} catch (NoSuchAlgorithmException e1) {
//									e1.printStackTrace();
//								}
//		                        Path filePath = Path.of(selectedDirectory.getAbsolutePath()+"\\"+file.getName());
//		                        
//		                        try (FileInputStream fis = new FileInputStream(filePath.toFile())) {
//		                            byte[] buffer = new byte[1024]; // 1 KB buffer
//		                            int bytesRead;
//		                            while ((bytesRead = fis.read(buffer)) != -1) {
//		                                digest.update(buffer, 0, bytesRead);
//		                            }
//		                        } catch (FileNotFoundException e1) {
//									
//									e1.printStackTrace();
//								} catch (IOException e2) {
//									e2.printStackTrace();
//								}
//
//
//		                        byte[] hashBytes = digest.digest();
//		                        StringBuilder hexString = new StringBuilder();
//		                        for (byte b : hashBytes) {
//		                            hexString.append(String.format("%02x", b));
//		                        }
//		                        String filePath2 = "C:\\Users\\raja\\Documents\\malware signature files\\md5.txt";
//		                        
//		                        //String status="";
//		                        try (BufferedReader br = new BufferedReader(new FileReader(filePath2))) {
//		                            String line;
//		                            
//		                            while ((line = br.readLine()) != null) {
//		                            	if(hexString.toString()==line) {
//		                            		maliciousSignatures.add("Malicious");
//		                            		 
//		                            		
//
//		                            	}
//		                            	else {
//		                            	   maliciousSignatures.add("Clean");
//		                            		
//		                            		
//		                            	}
//		                            
//		                            }
//		                            
//		                            
//		                           
//		                        } catch (IOException e3) {
//		                            e3.printStackTrace();
//		                        }
//		                       
//		                    	scannedFiles++;
//		                    	updateMessage("Scanning "+ file);
//		                    	
//			                        updateProgress(scannedFiles, totalFiles);
//		                        
//			                        
//		                    } 
//	                    }catch(Exception e) {
//	                    		
//	                    	}
//	                    String status=maliciousSignatures.contains("Malicious")? "Malicious" : "Clean";
//	                    Platform.runLater(() -> {
//	                        scanResults.add(new FileScanResult(fileName, status));
//	                    });
//
//	                    updateProgress(i + 1, totalFiles); // Update progress
//	                }
//	                return null;
//	            }
//	        };
//	        statusLabel.textProperty().bind(scanTask.messageProperty());
//            progressBar1.progressProperty().bind(scanTask.progressProperty());
//	        //progressBar.progressProperty().bind(scanTask.progressProperty());
//	        scanTask.setOnSucceeded(e ->{
//        	    secondStage.close();
//        	    showResultsStage(scanResults);
//        	    
//        	    
//        });
//          scanTask.setOnCancelled(e -> statusLabel.setText("Scanning cancelled!"));
//          scanTask.setOnFailed(e -> statusLabel.setText("Scanning failed!"));
//          Thread scanThread = new Thread(scanTask);
//          scanThread.setDaemon(true);
//          scanThread.start();
//		});
//		
//		toolBar.getItems().addAll(scanButton,spacer,scanFolderButton,spacer2,settingsButton2);
//		toolBar.setOrientation(Orientation.VERTICAL);
//		Button homeButton=new Button("Home");
//		homeButton.setId("button-1");
//        Button scanButton2 = new Button("Scan");
//        scanButton2.setId("button-2");
//        Button quarantineButton = new Button("Quarantine");
//        quarantineButton.setId("button-3");
//        Button updateButton = new Button("Update");
//        updateButton.setId("button-4");
//        Button settingsButton = new Button("Settings");
//        settingsButton.setId("button-5");
//        Button aboutButton=new Button("About");
//        aboutButton.setId("button-6");
//        ToolBar navigationBar = new ToolBar(homeButton,scanButton2, quarantineButton, updateButton, settingsButton,aboutButton);
//		VBox root=new VBox(navigationBar,toolBar);
//		VBox.setVgrow(toolBar, Priority.ALWAYS);
//		Scene scene = new Scene(root,1000,600);
//		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//		primaryStage.setScene(scene);
//		primaryStage.show();
//    }
//    @SuppressWarnings({ "unchecked", "unused" })
//	private void showResultsStage(ObservableList<FileScanResult> scanResults) {
//        TableView<FileScanResult> tableView = new TableView<>();
//        
//        // Define columns
//        TableColumn<FileScanResult, String> fileNameColumn = new TableColumn<>("File Name");
//        fileNameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getFileName()));
//
//        TableColumn<FileScanResult, String> statusColumn = new TableColumn<>("Status");
//        statusColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus()));
//
//        // Highlight malicious files
//        statusColumn.setCellFactory(column -> new TableCell<>() {
//            @Override
//            protected void updateItem(String status, boolean empty) {
//                super.updateItem(status, empty);
//                if (status == null || empty) {
//                    setText(null);
//                    setStyle("");
//                } else {
//                    setText(status);
//                    if (status.equals("Malicious")) {
//                        setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
//                    } else {
//                        setStyle("-fx-text-fill: green;");
//                    }
//                }
//            }
//        });
//
//        tableView.getColumns().addAll(fileNameColumn, statusColumn);
//        tableView.setItems(scanResults);
//
//        VBox root = new VBox(tableView);
//        root.setStyle("-fx-padding: 10;");
//        Scene scene = new Scene(root, 600, 400);
//
//        Stage resultsStage = new Stage();
//        resultsStage.setTitle("Scan Results");
//        resultsStage.setScene(scene);
//        resultsStage.show();
//    }
//
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
//import javafx.application.Application;
//import javafx.application.Platform;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.concurrent.Task;
//import javafx.scene.Scene;
//import javafx.scene.control.ProgressBar;
//import javafx.scene.control.TableCell;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//public class Test extends Application {
//
//    public static class FileScanResult {
//        private String fileName;
//        private String status;
//
//        public FileScanResult(String fileName, String status) {
//            this.fileName = fileName;
//            this.status = status;
//        }
//
//        public String getFileName() {
//            return fileName;
//        }
//
//        public void setFileName(String fileName) {
//            this.fileName = fileName;
//        }
//
//        public String getStatus() {
//            return status;
//        }
//
//        public void setStatus(String status) {
//            this.status = status;
//        }
//    }
//
//    @Override
//    public void start(Stage primaryStage) {
//        TableView<FileScanResult> tableView = new TableView<>();
//
//        // Define columns
//        TableColumn<FileScanResult, String> fileNameColumn = new TableColumn<>("File Name");
//        fileNameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getFileName()));
//
//        TableColumn<FileScanResult, String> statusColumn = new TableColumn<>("Status");
//        statusColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus()));
//
//        // Highlight malicious files
//        statusColumn.setCellFactory(column -> new TableCell<>() {
//            @Override
//            protected void updateItem(String status, boolean empty) {
//                super.updateItem(status, empty);
//                if (status == null || empty) {
//                    setText(null);
//                    setStyle("");
//                } else {
//                    setText(status);
//                    if (status.equals("Malicious")) {
//                        setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
//                    } else {
//                        setStyle("-fx-text-fill: green;");
//                    }
//                }
//            }
//        });
//
//        tableView.getColumns().addAll(fileNameColumn, statusColumn);
//
//        // Observable list to hold scan results
//        ObservableList<FileScanResult> scanResults = FXCollections.observableArrayList(
//                new FileScanResult("example.txt", "Pending..."),
//                new FileScanResult("malicious.exe", "Pending..."),
//                new FileScanResult("readme.md", "Pending..."),
//                new FileScanResult("virus.bat", "Pending..."),
//                new FileScanResult("document.pdf", "Pending...")
//        );
//
//        tableView.setItems(scanResults);
//
//        // Progress bar
//        ProgressBar progressBar = new ProgressBar(0);
//
//        // Task to simulate file scanning
//        Task<Void> scanTask = new Task<>() {
//            @Override
//            protected Void call() {
//                int totalFiles = scanResults.size();
//                for (int i = 0; i < totalFiles; i++) {
//                    try {
//                        Thread.sleep(1000); // Simulate scanning time
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                    FileScanResult file = scanResults.get(i);
//                    String status = file.getFileName().toLowerCase().contains("malicious") || file.getFileName().toLowerCase().contains("virus")
//                            ? "Malicious"
//                            : "Clean";
//
//                    final int index = i;
//                    Platform.runLater(() -> {
//                        file.setStatus(status);
//                        tableView.refresh(); // Refresh table to show updated status
//                    });
//
//                    updateProgress(index + 1, totalFiles);
//                }
//                return null;
//            }
//        };
//
//        // Bind progress bar to task progress
//        progressBar.progressProperty().bind(scanTask.progressProperty());
//
//        // When task finishes, hide the progress bar
//        scanTask.setOnSucceeded(event -> progressBar.setVisible(false));
//
//        // Start the task in a new thread
//        new Thread(scanTask).start();
//
//        VBox root = new VBox(10, progressBar, tableView);
//        root.setStyle("-fx-padding: 10;");
//
//        Scene scene = new Scene(root, 600, 400);
//        primaryStage.setTitle("Antivirus Scanner Results");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}

//import javafx.application.Application;
//import javafx.application.Platform;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.scene.Scene;
//import javafx.scene.control.ProgressBar;
//import javafx.scene.control.TableCell;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//public class Test extends Application {
//
//    public static class FileScanResult {
//        private String fileName;
//        private String status;
//
//        public FileScanResult(String fileName, String status) {
//            this.fileName = fileName;
//            this.status = status;
//        }
//
//        public String getFileName() {
//            return fileName;
//        }
//
//        public void setFileName(String fileName) {
//            this.fileName = fileName;
//        }
//
//        public String getStatus() {
//            return status;
//        }
//
//        public void setStatus(String status) {
//            this.status = status;
//        }
//    }
//
//    @Override
//    public void start(Stage primaryStage) {
//        TableView<FileScanResult> tableView = new TableView<>();
//
//        // Define columns
//        TableColumn<FileScanResult, String> fileNameColumn = new TableColumn<>("File Name");
//        fileNameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getFileName()));
//
//        TableColumn<FileScanResult, String> statusColumn = new TableColumn<>("Status");
//        statusColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus()));
//
//        // Highlight malicious files
//        statusColumn.setCellFactory(column -> new TableCell<>() {
//            @Override
//            protected void updateItem(String status, boolean empty) {
//                super.updateItem(status, empty);
//                if (status == null || empty) {
//                    setText(null);
//                    setStyle("");
//                } else {
//                    setText(status);
//                    if (status.equals("Malicious")) {
//                        setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
//                    } else {
//                        setStyle("-fx-text-fill: green;");
//                    }
//                }
//            }
//        });
//
//        tableView.getColumns().addAll(fileNameColumn, statusColumn);
//
//        // Observable list to hold scan results
//        ObservableList<FileScanResult> scanResults = FXCollections.observableArrayList(
//                new FileScanResult("example.txt", "Scanning..."),
//                new FileScanResult("malicious.exe", "Scanning..."),
//                new FileScanResult("readme.md", "Scanning..."),
//                new FileScanResult("virus.bat", "Scanning..."),
//                new FileScanResult("document.pdf", "Scanning...")
//        );
//
//        tableView.setItems(scanResults);
//
//        // Progress bar
//        ProgressBar progressBar = new ProgressBar(0);
//
//        // Simulate file scanning in a background thread
//        new Thread(() -> {
//            int totalFiles = scanResults.size();
//            for (int i = 0; i < totalFiles; i++) {
//                FileScanResult file = scanResults.get(i);
//                try {
//                    Thread.sleep(1000); // Simulate scanning time
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                String status = file.getFileName().toLowerCase().contains("malicious") || file.getFileName().toLowerCase().contains("virus")
//                        ? "Malicious"
//                        : "Clean";
//
//                final int index = i;
//                Platform.runLater(() -> {
//                    file.setStatus(status);
//                    tableView.refresh(); // Refresh table to show updated status
//                    progressBar.setProgress((index + 1) / (double) totalFiles);
//
//                    // If scanning is complete
//                    if (index + 1 == totalFiles) {
//                        progressBar.setVisible(false); // Hide progress bar
//                    }
//                });
//            }
//        }).start();
//
//        VBox root = new VBox(10, progressBar, tableView);
//        root.setStyle("-fx-padding: 10;");
//
//        Scene scene = new Scene(root, 600, 400);
//        primaryStage.setTitle("Antivirus Scanner Results");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}

//import javafx.application.Application;
//import javafx.application.Platform;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.scene.Scene;
//import javafx.scene.control.ProgressBar;
//import javafx.scene.control.TableCell;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import javafx.util.Callback;
//
//public class Test extends Application {
//
//    // Data model for scan results
//    public static class FileScanResult {
//        private String fileName;
//        private String status;
//
//        public FileScanResult(String fileName, String status) {
//            this.fileName = fileName;
//            this.status = status;
//        }
//
//        public String getFileName() {
//            return fileName;
//        }
//
//        public void setFileName(String fileName) {
//            this.fileName = fileName;
//        }
//
//        public String getStatus() {
//            return status;
//        }
//
//        public void setStatus(String status) {
//            this.status = status;
//        }
//    }
//
//    @Override
//    public void start(Stage primaryStage) {
//        TableView<FileScanResult> tableView = new TableView<>();
//
//        // Define columns
//        TableColumn<FileScanResult, String> fileNameColumn = new TableColumn<>("File Name");
//        fileNameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getFileName()));
//
//        TableColumn<FileScanResult, String> statusColumn = new TableColumn<>("Status");
//        statusColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus()));
//
//        // Highlight malicious files
//        statusColumn.setCellFactory(column -> new TableCell<>() {
//            @Override
//            protected void updateItem(String status, boolean empty) {
//                super.updateItem(status, empty);
//                if (status == null || empty) {
//                    setText(null);
//                    setStyle("");
//                } else {
//                    setText(status);
//                    if ("Malicious".equals(status)) {
//                        setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
//                    } else {
//                        setStyle("-fx-text-fill: green;");
//                    }
//                }
//            }
//        });
//
//        tableView.getColumns().addAll(fileNameColumn, statusColumn);
//
//        // Observable list to hold scan results
//        ObservableList<FileScanResult> scanResults = FXCollections.observableArrayList(
//            new FileScanResult("example.txt", "Scanning..."),
//            new FileScanResult("malicious.exe", "Scanning..."),
//            new FileScanResult("readme.md", "Scanning..."),
//            new FileScanResult("virus.bat", "Scanning..."),
//            new FileScanResult("document.pdf", "Scanning...")
//        );
//
//        tableView.setItems(scanResults);
//
//        // Progress bar
//        ProgressBar progressBar = new ProgressBar(0);
//
//        // Simulate file scanning in a background thread
//        new Thread(() -> {
//            int totalFiles = scanResults.size();
//            for (int i = 0; i < totalFiles; i++) {
//                FileScanResult file = scanResults.get(i);
//                try {
//                    Thread.sleep(1000); // Simulate scanning time
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                String status = file.getFileName().toLowerCase().contains("malicious") || file.getFileName().toLowerCase().contains("virus")
//                        ? "Malicious"
//                        : "Clean";
//
//                final int index = i;
//                Platform.runLater(() -> {
//                    file.setStatus(status);
//                    tableView.refresh(); // Refresh table to show updated status
//                    progressBar.setProgress((index + 1) / (double) totalFiles);
//                });
//            }
//        }).start();
//
//        VBox root = new VBox(10, tableView, progressBar);
//        root.setStyle("-fx-padding: 10;");
//
//        Scene scene = new Scene(root, 600, 400);
//        primaryStage.setTitle("Antivirus File Scanner");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}

//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.*;
//import javafx.stage.Stage;
//
//import java.util.*;
//
//public class Test extends Application {
//
//    private ProgressBar progressBar;
//    private ListView<String> fileListView;
//
//    @Override
//    public void start(Stage primaryStage) {
//        // Layout
//        VBox root = new VBox(10);
//        root.setStyle("-fx-padding: 20;");
//
//        // ProgressBar
//        progressBar = new ProgressBar(0);
//        root.getChildren().add(progressBar);
//
//        // File ListView
//        fileListView = new ListView<>();
//        root.getChildren().add(fileListView);
//
//        // Start Scanning Button
//        Button startScanButton = new Button("Start Scan");
//        startScanButton.setOnAction(e -> startScan());
//        root.getChildren().add(startScanButton);
//
//        // Setup Scene
//        Scene scene = new Scene(root, 400, 300);
//        primaryStage.setTitle("Antivirus Software");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    private void startScan() {
//        // Simulated files and infection status
//        List<String> files = Arrays.asList("file1.txt", "file2.exe", "file3.docx", "file4.jpg");
//        Map<String, Boolean> infectionStatus = new HashMap<>();
//        infectionStatus.put("file1.txt", false);
//        infectionStatus.put("file2.exe", true);
//        infectionStatus.put("file3.docx", false);
//        infectionStatus.put("file4.jpg", true);
//
//        // Update UI
//        new Thread(() -> {
//            for (int i = 0; i < files.size(); i++) {
//                String file = files.get(i);
//
//                // Simulate scanning time
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//                }
//
//                // Update ProgressBar and ListView
//                final double progress = (i + 1) / (double) files.size();
//                final boolean isInfected = infectionStatus.get(file);
//                final String displayText = file + (isInfected ? " - Infected" : " - Clean");
//
//                // Update UI on FX Thread
//                int finalI = i;
//                javafx.application.Platform.runLater(() -> {
//                    progressBar.setProgress(progress);
//                    fileListView.getItems().add(displayText);
//
//                    if (isInfected) {
//                        fileListView.getItems().set(finalI, displayText + " ⚠️");
//                    }
//
//                    if (progress == 1.0) {
//                        showPopup(infectionStatus);
//                    }
//                });
//            }
//        }).start();
//    }
//
//    private void showPopup(Map<String, Boolean> infectionStatus) {
//        // Popup Stage
//        Stage popupStage = new Stage();
//        VBox popupRoot = new VBox(10);
//        popupRoot.setStyle("-fx-padding: 20;");
//
//        Label resultLabel = new Label("Scan Complete! Infected Files:");
//        popupRoot.getChildren().add(resultLabel);
//
//        ListView<String> infectedFilesList = new ListView<>();
//        infectionStatus.forEach((file, isInfected) -> {
//            if (isInfected) {
//                infectedFilesList.getItems().add(file);
//            }
//        });
//
//        popupRoot.getChildren().add(infectedFilesList);
//
//        Button closeButton = new Button("Close");
//        closeButton.setOnAction(e -> popupStage.close());
//        popupRoot.getChildren().add(closeButton);
//
//        // Setup Scene
//        Scene popupScene = new Scene(popupRoot, 300, 200);
//        popupStage.setTitle("Scan Results");
//        popupStage.setScene(popupScene);
//        popupStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.nio.file.Path;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.List;
//
//import javafx.application.Application;
//import javafx.concurrent.Task;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.ProgressBar;
//import javafx.scene.layout.VBox;
//import javafx.stage.DirectoryChooser;
//import javafx.stage.Modality;
//import javafx.stage.Stage;
//
//public class Test extends Application {
//	DirectoryChooser directoryChooser;
//    @Override
//    public void start(Stage primaryStage) {
//        Button scanButton = new Button("Start Scan");
//        directoryChooser = new DirectoryChooser();
//	    directoryChooser.setInitialDirectory(new File("C:\\"));
//        // Simulate scan completion and show the pop-up
//        scanButton.setOnAction(event -> {
//            boolean threatsDetected = performScan(primaryStage); // Simulate scan result
//            String message = threatsDetected ? "Threats detected!" : "No threats found!";
//            showPopup(message);
//        });
//
//        VBox root = new VBox(10, scanButton);
//        root.setStyle("-fx-padding: 20;");
//        Scene scene = new Scene(root, 300, 200);
//
//        primaryStage.setTitle("Antivirus Software");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    private boolean performScan(Stage stage) {
//    	
//    	 File selectedDirectory = directoryChooser.showDialog(stage);
//		 
//         //System.out.println(selectedDirectory.getAbsolutePath());
//		    ProgressBar progressBar1=new ProgressBar(0);
//		    progressBar1.setPrefWidth(600);
//		    progressBar1.setPrefHeight(35);
//		    Label statusLabel = new Label("Scanning...");
//			progressBar1.setProgress(0.3);
//			VBox box=new VBox(progressBar1,statusLabel);
//			Scene scene3=new Scene(box,600,100);
//			Stage secondStage=new Stage();
//			secondStage.setTitle("");
//			secondStage.setScene(scene3);
//			secondStage.show();
//         File folder = new File(selectedDirectory.getAbsolutePath());
//         //File[] files = folder.listFiles();
//         List<File> files = List.of(folder.listFiles());
//         int totalFiles = files.size();
//         Task<Void> scanTask = new Task<>() {
//        	 boolean flag=true;
//         
//             @Override
//             protected Void call() throws Exception {
//             	
//                 int scannedFiles = 0;
//                 //boolean flag=true;
//                     if (files != null) {
// 		                for (File file : files) {
// 		                    if (file.isFile()) {
// 		                    	
// 		                        //System.out.println("File: " + file.getName());
// 		                        MessageDigest digest = null;
// 								try {
// 									digest = MessageDigest.getInstance("MD5");
// 								} catch (NoSuchAlgorithmException e1) {
// 									e1.printStackTrace();
// 								}
// 		                        Path filePath = Path.of(selectedDirectory.getAbsolutePath()+"\\"+file.getName());
// 		                        
// 		                        try (FileInputStream fis = new FileInputStream(filePath.toFile())) {
// 		                            byte[] buffer = new byte[1024]; // 1 KB buffer
// 		                            int bytesRead;
// 		                            while ((bytesRead = fis.read(buffer)) != -1) {
// 		                                digest.update(buffer, 0, bytesRead);
// 		                            }
// 		                        } catch (FileNotFoundException e1) {
// 									
// 									e1.printStackTrace();
// 								} catch (IOException e2) {
// 									e2.printStackTrace();
// 								}
//
//
// 		                        byte[] hashBytes = digest.digest();
// 		                        StringBuilder hexString = new StringBuilder();
// 		                        for (byte b : hashBytes) {
// 		                            hexString.append(String.format("%02x", b));
// 		                        }
// 		                        String filePath2 = "C:\\Users\\raja\\Documents\\malware signature files\\md5.txt"; // Replace with your file path
// 		                        try (BufferedReader br = new BufferedReader(new FileReader(filePath2))) {
// 		                            String line;
// 		                            while ((line = br.readLine()) != null) {
// 		                            	if(hexString.toString()==line) {
// 		                            		flag=false;
// 		                            		System.out.println(file.getName()+" "+ "is a malicious file");
//// 		                            		Label label7=new Label(file.getName()+ " is a malicious file");
//// 		                            		VBox box7=new VBox(label7);
//// 		                            		Scene scene4=new Scene(box7,600,100);
//// 		                					Stage thirdStage=new Stage();
//// 		                					thirdStage.setTitle("");
//// 		                					thirdStage.setScene(scene4);
//// 		                					thirdStage.show();
// 		                            	}
// 		                            	else {
// 		                            		flag=true;
// 		                            	
//// 		                            		Label label45=new Label("There is no malicious file in this folder");
//// 		                            		VBox box45=new VBox(label45);
//// 		                            		Scene scene5=new Scene(box45,600,100);
//// 		                					Stage fourthStage=new Stage();
//// 		                					fourthStage.setTitle("");
//// 		                					fourthStage.setScene(scene5);
//// 		                					fourthStage.show();
// 		                            		
// 		                            	}
// 		                            
// 		                            }
// 		                            
// 		                           
// 		                        } catch (IOException e3) {
// 		                            e3.printStackTrace();
// 		                        }
// 		                       
// 		                    	scannedFiles++;
// 		                    	updateMessage("Scanning "+ file);
//	 		                        updateProgress(scannedFiles, totalFiles);
// 		                        //System.out.println(hexString.toString());
//	 		                        
// 		                    } 
// 		                   
// 		                    else if (file.isDirectory()) {
// 		                        //System.out.println("Directory: " + file.getName());
// 		                    }
// 		                } 
//                 }
//                 updateMessage("Scan completed");
//                 return null;
//                
//             }
//           
//             
//        
//         };
//         
//         statusLabel.textProperty().bind(scanTask.messageProperty());
//         progressBar1.progressProperty().bind(scanTask.progressProperty());
//
//        
//         scanTask.setOnSucceeded(e -> statusLabel.setText("Scanning complete!"));
//         scanTask.setOnCancelled(e -> statusLabel.setText("Scanning cancelled!"));
//         scanTask.setOnFailed(e -> statusLabel.setText("Scanning failed!"));
//
//       
//         Thread scanThread = new Thread(scanTask);
//         scanThread.setDaemon(true);
//         scanThread.start(); 
//        
//		return flag; // Randomly simulate threats detected or not
//    }
//
//    private void showPopup(String message) {
//        Stage popupStage = new Stage();
//        popupStage.initModality(Modality.APPLICATION_MODAL); // Block interaction with other windows
//        popupStage.setTitle("Scan Result");
//
//        Label messageLabel = new Label(message);
//        Button closeButton = new Button("Close");
//        closeButton.setOnAction(event -> popupStage.close());
//
//        VBox layout = new VBox(10, messageLabel, closeButton);
//        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
//
//        Scene scene = new Scene(layout, 250, 150);
//        popupStage.setScene(scene);
//        popupStage.showAndWait(); // Wait for the user to close
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}

//import javafx.animation.KeyFrame;
//import javafx.animation.Timeline;
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.layout.StackPane;
//import javafx.stage.Stage;
//import javafx.util.Duration;
//
//public class Test extends Application {
//
//    @Override
//    public void start(Stage primaryStage) {
//        Label label = new Label("Starting...");
//        StackPane root = new StackPane(label);
//        Scene scene = new Scene(root, 300, 200);
//
//        // Timeline for updating the label text
//        Timeline timeline = new Timeline();
//        for (int i = 1; i <= 10; i++) {
//            final int count = i; // Need a final variable for use in the lambda
//            timeline.getKeyFrames().add(
//                new KeyFrame(Duration.seconds(i), e -> label.setText("Count: " + count))
//            );
//        }
//        timeline.setCycleCount(1); // Run once
//        timeline.play();
//
//        primaryStage.setTitle("Label Update Example");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}

//import javafx.application.Application;
//import javafx.concurrent.Task;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.control.ProgressBar;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//import java.io.File;
//import java.util.List;
//
//public class Test extends Application {
//
//    @Override
//    public void start(Stage stage) {
//        // UI Components
//        ProgressBar progressBar = new ProgressBar(0);
//        Label statusLabel = new Label("Scanning files...");
//        VBox root = new VBox(10, progressBar, statusLabel);
//        root.setStyle("-fx-padding: 20; -fx-alignment: center;");
//        
//        // Example file list
//        File directory = new File("C:\\Users\\raja\\Downloads"); // Change this to your directory
//        List<File> files = List.of(directory.listFiles());
//        int totalFiles = files.size();
//
//        // Task for file scanning
//        Task<Void> scanTask = new Task<>() {
//            @Override
//            protected Void call() throws Exception {
//                int scannedFiles = 0;
//                for (File file : files) {
//                    if (isCancelled()) break;
//
//                    // Simulate scanning a file (replace with actual scanning logic)
//                    Thread.sleep(200); // Simulate time for scanning
//                    scannedFiles++;
//                    
//                    // Update progress
//                    updateProgress(scannedFiles, totalFiles);
//                }
//                return null;
//            }
//        };
//
//        // Bind progress bar to task progress
//        progressBar.progressProperty().bind(scanTask.progressProperty());
//
//        // Update status label based on task state
//        scanTask.setOnSucceeded(e -> statusLabel.setText("Scanning complete!"));
//        scanTask.setOnCancelled(e -> statusLabel.setText("Scanning cancelled!"));
//        scanTask.setOnFailed(e -> statusLabel.setText("Scanning failed!"));
//
//        // Start the task
//        Thread scanThread = new Thread(scanTask);
//        scanThread.setDaemon(true);
//        scanThread.start();
//
//        // Set up the scene
//        Scene scene = new Scene(root, 300, 150);
//        stage.setScene(scene);
//        stage.setTitle("File Scanner");
//        stage.show();
//    }

//    public static void main(String[] args) {
//        launch(args);
//    }
//}
//import javafx.application.Application;
//import javafx.concurrent.Task;
//import javafx.scene.Scene;
//import javafx.scene.control.ProgressBar;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//public class Test extends Application {
//    @Override
//    public void start(Stage primaryStage) {
//        ProgressBar progressBar = new ProgressBar(0); // Initial value 0
//        
//        // Define a long-running task
//        Task<Void> task = new Task<>() {
//            @Override
//            protected Void call() throws Exception {
//                for (int i = 0; i <= 100; i++) {
//                    // Simulate work
//                    Thread.sleep(100);
//                    updateProgress(i, 100); // Update progress
//                }
//                return null;
//            }
//        };
//
//        // Bind the progress property of the task to the progress bar
//        progressBar.progressProperty().bind(task.progressProperty());
//        
//        // Start the task in a new thread
//        new Thread(task).start();
//
//        VBox vbox = new VBox(10, progressBar);
//        vbox.setStyle("-fx-padding: 20;");
//
//        Scene scene = new Scene(vbox, 300, 100);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Progress Bar Example");
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
//import javafx.application.Application;
//import javafx.geometry.Insets;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ButtonBar.ButtonData;
//import javafx.scene.control.ButtonType;
//import javafx.scene.control.Dialog;
//import javafx.scene.layout.HBox;
//import javafx.stage.Stage;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontPosture;
//import javafx.scene.text.FontWeight;
//import javafx.scene.text.Text;
//public class Test extends Application {
//   @Override
//   public void start(Stage stage) {
//      //Creating a dialog
//      Dialog<String> dialog = new Dialog<String>();
//      //Setting the title
//      dialog.setTitle("Dialog");
//      ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
//      //Setting the content of the dialog
//      dialog.setContentText("This is a sample dialog");
//      //Adding buttons to the dialog pane
//      dialog.getDialogPane().getButtonTypes().add(type);
//      //Setting the label
//      Text txt = new Text("Click the button to show the dialog");
//      Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
//      txt.setFont(font);
//      //Creating a button
//      Button button = new Button("Show Dialog");
//      //Showing the dialog on clicking the button
//      button.setOnAction(e -> {
//         dialog.showAndWait();
//      });
//      //Creating a vbox to hold the button and the label
//      HBox pane = new HBox(15);
//      //Setting the space between the nodes of a HBox pane
//      pane.setPadding(new Insets(50, 150, 50, 60));
//      pane.getChildren().addAll(txt, button);
//      //Creating a scene object
//      Scene scene = new Scene(new Group(pane), 595, 250, Color.BEIGE);
//      stage.setTitle("Dialog");
//      stage.setScene(scene);
//      stage.show();
//   }
//   public static void main(String args[]){
//      launch(args);
//   }
//}
//import javafx.application.Application;
//import javafx.concurrent.Task;
//import javafx.scene.Scene;
//import javafx.scene.control.ProgressBar;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//public class Test extends Application {
//    @Override
//    public void start(Stage primaryStage) {
//        ProgressBar progressBar = new ProgressBar(0);
//        progressBar.setPrefWidth(600);
//        progressBar.setPrefHeight(30);
//        //progressBar.setStyle("-fx-accent: green");
//        int totalIterations = 100; // Example loop count
//        Task<Void> task = createTask(totalIterations);
//
//        // Bind progress bar to task progress
//        progressBar.progressProperty().bind(task.progressProperty());
//
//        // Add task completion handler
//        task.setOnSucceeded(event -> System.out.println("Task completed successfully!"));
//
//        // Start the task
//        Thread thread = new Thread(task);
//        thread.setDaemon(true);
//        thread.start();
//
//        // Layout
//        VBox layout = new VBox(10, progressBar);
//        Scene scene = new Scene(layout, 400, 200);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Progress Bar Example");
//        primaryStage.show();
//    }
//
//    private Task<Void> createTask(int totalIterations) {
//        return new Task<>() {
//            @Override
//            protected Void call() throws Exception {
//                for (int i = 0; i < totalIterations; i++) {
//                    Thread.sleep(100); // Simulate work
//                    updateProgress(i + 1, totalIterations);
//                }
//                return null;
//            }
//        };
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ProgressBar;
//import javafx.scene.layout.VBox;
//import javafx.stage.DirectoryChooser;
//import javafx.stage.Stage;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.nio.file.Path;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//public class Test extends Application {
//    public static void main(String[] args) {
//        launch(args);
//    }
//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.setTitle("JavaFX App");
//        DirectoryChooser directoryChooser = new DirectoryChooser();
//        directoryChooser.setInitialDirectory(new File("C:\\"));
//        Button button = new Button("Select Directory");
//        ProgressBar progressBar = new ProgressBar(0);
//        progressBar.setPrefWidth(300);
//
//        button.setOnAction(e -> {
//            File selectedDirectory = directoryChooser.showDialog(primaryStage);
//            //
//            //System.out.println(selectedDirectory.getAbsolutePath());
//            File folder = new File(selectedDirectory.getAbsolutePath());
//            File[] files = folder.listFiles();
//            if (files != null) {
//                for (File file : files) {
//                    if (file.isFile()) {
//                        //System.out.println("File: " + file.getName());
//                        MessageDigest digest = null;
//						try {
//							digest = MessageDigest.getInstance("MD5");
//						} catch (NoSuchAlgorithmException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
//                        Path filePath = Path.of(selectedDirectory.getAbsolutePath()+"\\"+file.getName());
//                        // Read the file in chunks
//                        try (FileInputStream fis = new FileInputStream(filePath.toFile())) {
//                            byte[] buffer = new byte[1024]; // 1 KB buffer
//                            int bytesRead;
//                            while ((bytesRead = fis.read(buffer)) != -1) {
//                                digest.update(buffer, 0, bytesRead);
//                            }
//                        } catch (FileNotFoundException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						} catch (IOException e2) {
//							// TODO Auto-generated catch block
//							e2.printStackTrace();
//						}
//
//                        // Get the hash bytes
//                        byte[] hashBytes = digest.digest();
//
//                        // Convert the hash bytes to a hexadecimal string
//                        StringBuilder hexString = new StringBuilder();
//                        for (byte b : hashBytes) {
//                            hexString.append(String.format("%02x", b));
//                        }
//                        String filePath2 = "C:\\Users\\raja\\Documents\\malware signature files\\md5.txt"; // Replace with your file path
//                        try (BufferedReader br = new BufferedReader(new FileReader(filePath2))) {
//                            String line;
//                            while ((line = br.readLine()) != null) {
//                            	if(hexString.toString()==line) {
//                            		System.out.println(file.getName()+" "+ "is a malicious file");
//                            	}
//                            
//                            		
//                            	
//                                //System.out.println(line);
//                            }
//                        } catch (IOException e3) {
//                            e3.printStackTrace();
//                        }
//                        //System.out.println(hexString.toString());
//                    } else if (file.isDirectory()) {
//                        //System.out.println("Directory: " + file.getName());
//                    }
//                }
//                System.out.println("There is no malicious in" +" "+selectedDirectory.getAbsolutePath());
//            } else {
//                System.out.println("Folder does not exist or is not a directory.");
//            }
//        });
//        VBox vBox = new VBox(button,progressBar);
//        //HBox hBox = new HBox(button1, button2);
//        Scene scene = new Scene(vBox, 960, 600);
//
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//}
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.ProgressBar;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//public class Test extends Application {
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.setTitle("JavaFX App");
//
//        ProgressBar progressBar = new ProgressBar(0);
//
//        progressBar.setProgress(0.5);
//
//        VBox vBox = new VBox(progressBar);
//        Scene scene = new Scene(vBox, 960, 600);
//
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//}
//import javafx.application.Application;
//import javafx.geometry.Orientation;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ToolBar;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//public class Test extends Application {
//
//    @Override
//    public void start(Stage primaryStage) {
//        // Create a horizontal toolbar
//        ToolBar horizontalToolBar = new ToolBar(
//            new Button("File"),
//            new Button("Edit"),
//            new Button("View")
//        );
//
//        // Create a vertical toolbar
//        ToolBar verticalToolBar = new ToolBar(
//            new Button("Cut"),
//            new Button("Copy"),
//            new Button("Paste")
//        );
//        verticalToolBar.setOrientation(Orientation.VERTICAL);
//
//        // Arrange the toolbars in a VBox
//        VBox root = new VBox(horizontalToolBar, verticalToolBar);
//
//        // Create and set the scene
//        Scene scene = new Scene(root, 400, 300);
//        primaryStage.setTitle("Toolbar Example");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//public class Test extends Application {
//
//    @Override
//    public void start(Stage primaryStage) {
//        // Horizontal Toolbar
//        HBox horizontalToolbar = new HBox(10); // 10px spacing
//        horizontalToolbar.getStyleClass().add("toolbar");
//        horizontalToolbar.getChildren().addAll(
//            new Button("Scan"),
//            new Button("Update"),
//            new Button("Settings")
//        );
//
//        // Vertical Toolbar
//        VBox verticalToolbar = new VBox(10); // 10px spacing
//        verticalToolbar.getStyleClass().add("toolbar");
//        verticalToolbar.getChildren().addAll(
//            new Button("Dashboard"),
//            new Button("Quarantine"),
//            new Button("Reports")
//        );
//
//        // Content Area
//        Label contentArea = new Label("Main Content Area");
//        contentArea.setStyle("-fx-border-color: black; -fx-padding: 20px;");
//
//        // Layout with BorderPane
//        BorderPane root = new BorderPane();
//        root.setTop(horizontalToolbar); // Top toolbar
//        root.setLeft(verticalToolbar);  // Left toolbar
//        root.setCenter(contentArea);    // Main content in the center
//
//        // Scene and Stage
//        Scene scene = new Scene(root, 800, 600);
//
//        // Add custom CSS (optional)
//
//        primaryStage.setTitle("Antivirus Software");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
//import javafx.application.Application;
//import javafx.geometry.Insets;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ToolBar;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.StackPane;
//import javafx.stage.Stage;
//
//public class Test extends Application {
//
//    @Override
//    public void start(Stage primaryStage) {
//    	Button homeButton=new Button("Home");
//        Button scanButton = new Button("Scan");
//        Button quarantineButton = new Button("Quarantine");
//        Button updateButton = new Button("Update");
//        Button settingsButton = new Button("Settings");
//        Button aboutButton=new Button("About");
//        scanButton.setOnAction(e -> System.out.println("Scan selected!"));
//        quarantineButton.setOnAction(e -> System.out.println("Quarantine selected!"));
//        updateButton.setOnAction(e -> System.out.println("Update selected!"));
//        settingsButton.setOnAction(e -> System.out.println("Settings selected!"));
//        ToolBar navigationBar = new ToolBar(homeButton,scanButton, quarantineButton, updateButton, settingsButton,aboutButton);
//        StackPane contentArea = new StackPane();
//        contentArea.setPadding(new Insets(20));
//        BorderPane root = new BorderPane();
//        root.setTop(navigationBar);
//        root.setCenter(contentArea);
//
//        // Scene and stage setup
//        Scene scene = new Scene(root, 800, 600);
//        primaryStage.setTitle("Antivirus Software");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
//import javafx.application.Application;
//import javafx.geometry.Insets;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ToolBar;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.StackPane;
//import javafx.stage.Stage;
//
//public class Test extends Application {
//
//    @Override
//    public void start(Stage primaryStage) {
//        // Create buttons for navigation
//        Button homeButton = new Button("Home");
//        Button aboutButton = new Button("About");
//        Button contactButton = new Button("Contact");
//        
//
//        // Set actions for buttons
//        homeButton.setOnAction(e -> System.out.println("Home clicked!"));
//        aboutButton.setOnAction(e -> System.out.println("About clicked!"));
//        contactButton.setOnAction(e -> System.out.println("Contact clicked!"));
//
//        // Create a ToolBar for navigation
//        ToolBar navigationBar = new ToolBar(homeButton, aboutButton, contactButton);
//
//        // Main content area
//        StackPane contentArea = new StackPane();
//        contentArea.setPadding(new Insets(20));
//
//        // Layout
//        BorderPane root = new BorderPane();
//        root.setTop(navigationBar); // Place the navigation bar at the top
//        root.setCenter(contentArea); // Place content in the center
//
//        // Set up the scene and stage
//        Scene scene = new Scene(root, 600, 400);
//        primaryStage.setTitle("JavaFX Navigation Bar Example");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}

//		TextArea textArea1 = new TextArea();
//		TextArea textArea2 = new TextArea();
//		textArea2.setVisible(false); // Initially hide the second TextArea
//
//		Button switchButton = new Button("Switch");
//        VBox vBox=new VBox(textArea1,textArea2,switchButton);
//        Scene scene=new Scene(vBox);
//		switchButton.setOnAction(event -> {
//		    if (textArea1.isVisible()) {
//		        textArea1.setVisible(false);
//		        textArea2.setVisible(true);
//		    } else {
//		        textArea1.setVisible(true);
//		        textArea2.setVisible(false);
//		    }
//		});
//import javafx.application.Application;
//import javafx.stage.Stage;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.layout.VBox;
//import java.awt.Desktop;
//import java.io.File;
//import java.io.IOException;
//
//public class Test extends Application {
//
//    @Override
//    public void start(Stage primaryStage) {
//        Button openInBrowserButton = new Button("Run HTML in Browser");
//        
//        // Button event handler
//        openInBrowserButton.setOnAction(e -> openHtmlFileInBrowser("C:\\Users\\raja\\Documents\\Text editor files\\index.html"));
//
//        VBox root = new VBox(openInBrowserButton);
//        Scene scene = new Scene(root, 400, 200);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    private void openHtmlFileInBrowser(String filePath) {
//        try {
//            File htmlFile = new File(filePath);
//            if (htmlFile.exists() && Desktop.isDesktopSupported()) {
//                Desktop.getDesktop().browse(htmlFile.toURI());
//            } else {
//                System.out.println("File not found or desktop feature not supported.");
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
//import javafx.stage.FileChooser;
//import javafx.scene.input.KeyEvent;
//import javafx.scene.input.KeyCode;
//import javafx.stage.Stage;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;

// Add this in your main scene setup



//import fi.iki.elonen.NanoHTTPD;
//import javafx.application.Application;
//import javafx.stage.Stage;
//import java.awt.*;
//import java.io.IOException;
//import java.net.URI;
//import java.nio.file.*;
//
//public class Test extends Application {
//
//    private static final String HTML_FILE_PATH = "C:\\Users\\raja\\Documents\\file1.html";  // Change this path
//    private static final String SERVER_URL = "http://localhost:5500";
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) {
//        // Start the embedded web server
//        try {
//            MyHttpServer server = new MyHttpServer(5500);
//            server.start();
//            System.out.println("Server started on " + SERVER_URL);
//
//            // Open the page in the default web browser (Chrome, etc.)
//            openInBrowser(SERVER_URL);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // Monitor the HTML file for changes
//        try {
//            WatchService watchService = FileSystems.getDefault().newWatchService();
//            Path path = Paths.get("C:\\Users\\raja\\Documents");  // Directory where the HTML file is located
//            path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
//
//            // Create a new thread to watch for file changes
//            Thread fileWatcherThread = new Thread(() -> {
//                try {
//                    WatchKey key;
//                    while ((key = watchService.take()) != null) {
//                        for (WatchEvent<?> event : key.pollEvents()) {
//                            if (event.context().toString().equals("file1.html")) {
//                                System.out.println("File changed, reloading browser...");
//                                // Reload the browser page (you may need a Chrome extension or JS code for real-time reload)
//                                openInBrowser(SERVER_URL);  // Reopen or trigger reload
//                            }
//                        }
//                        key.reset();
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            });
//            fileWatcherThread.setDaemon(true);
//            fileWatcherThread.start();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Method to open the browser with the given URL
//    private void openInBrowser(String url) {
//        if (Desktop.isDesktopSupported()) {
//            try {
//                Desktop desktop = Desktop.getDesktop();
//                desktop.browse(new URI(url));  // Opens the URL in the default browser
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            System.out.println("Desktop is not supported. Cannot open browser.");
//        }
//    }
//
//    // Embedded HTTP Server class
//    public static class MyHttpServer extends NanoHTTPD {
//        public MyHttpServer(int port) throws IOException {
//            super(port);
//        }
//
//        @Override
//        public Response serve(IHTTPSession session) {
//            try {
//                String htmlContent = new String(Files.readAllBytes(Paths.get(HTML_FILE_PATH)));
//                return newFixedLengthResponse(Response.Status.OK, "text/html", htmlContent);
//            } catch (IOException e) {
//                e.printStackTrace();
//                return newFixedLengthResponse(Response.Status.INTERNAL_ERROR, "text/plain", "Error loading file");
//            }
//        }
//    }
//}
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.layout.BorderPane;
//import javafx.stage.Stage;
//import org.fxmisc.richtext.InlineCssTextArea;
//
//public class Test extends Application {
//
//    @Override
//    public void start(Stage primaryStage) {
//        // Create an InlineCssTextArea
//        InlineCssTextArea textArea = new InlineCssTextArea();
//
//        // Sample text
//        textArea.appendText("Hello, RichTextFX!\n");
//        textArea.appendText("This is a simple example.\n");
//        textArea.appendText("You can use different styles:\n");
//        
//        // Apply some styling
//        textArea.setStyle(0, 5, "-fx-font-weight: bold;");
//        textArea.setStyle(27, 36, "-fx-font-style: italic;");
//        textArea.setStyle("-fx-font-color: red;");
//        // Create a layout and add the text area
//        BorderPane borderPane = new BorderPane();
//        borderPane.setCenter(textArea);
//
//        // Set up the scene and stage
//        Scene scene = new Scene(borderPane, 600, 400);
//        primaryStage.setTitle("RichTextFX Example");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.control.SplitPane;
//import javafx.scene.control.TextArea;
//import javafx.scene.layout.Priority;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//public class Test extends Application {
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    public void start(Stage primaryStage) {
//    	TextArea textArea=new TextArea();
//        SplitPane splitPane = new SplitPane();
//
//        VBox leftControl  = new VBox(new Label("Left Control"));
//        VBox rightControl = new VBox(textArea);
//        rightControl.setSpacing(0);
//		VBox.setVgrow(textArea,Priority.ALWAYS);
//        splitPane.getItems().addAll(leftControl, rightControl);
//
//        Scene scene = new Scene(splitPane);
//
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("JavaFX App");
//
//        primaryStage.show();
//    }
//}
//import javafx.application.Application;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.control.SplitPane;
//import javafx.scene.control.TextArea;
//import javafx.scene.control.TreeView;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.Priority;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//public class Test extends Application {
//
//    @Override
//    public void start(Stage primaryStage) {
//        // Create the file explorer (TreeView)
//        TreeView<String> fileExplorer = new TreeView<>();
//        // Populate the TreeView with dummy data
//        // You can add actual file system browsing here
//
//        // Create the text area
//        TextArea textArea = new TextArea();
//        
//        // Create the SplitPane
//        SplitPane splitPane = new SplitPane();
//        splitPane.getItems().addAll(fileExplorer, textArea);
//        
//        // Set divider position if needed
//        splitPane.setDividerPositions(0.3); // Adjust this value as needed
//        
//        // Create a Scene
//        BorderPane root = new BorderPane(splitPane);
//        
//        //root.setCenter(splitPane);
//        Scene scene = new Scene(root, 800, 600);
//        
//        // Set up the Stage
//        primaryStage.setTitle("Split Pane Example");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}

//import javafx.application.Application;
//import javafx.event.EventHandler;
//import javafx.scene.Scene;
//import javafx.scene.input.*;
//import javafx.scene.layout.Pane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Circle;
//import javafx.stage.Stage;
//
//public class Test extends Application {
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    public void start(Stage primaryStage) {
//
//        Circle circle = createCircle("#ff00ff", "#ff88ff",100);
//
//        circle.setOnDragDetected((MouseEvent event) -> {
//            System.out.println("Circle 1 drag detected");
//
//            Dragboard db = circle.startDragAndDrop(TransferMode.ANY);
//
//            ClipboardContent content = new ClipboardContent();
//            content.putString("Circle source text");
//            db.setContent(content);
//        });
//        circle.setOnMouseDragged((MouseEvent event) -> {
//            event.setDragDetect(true);
//        });
//
//        Circle circle2 = createCircle("#00ffff", "#88ffff",300);
//
//        circle2.setOnDragOver(new EventHandler<DragEvent>() {
//            public void handle(DragEvent event) {
//                if (event.getGestureSource() != circle2 && event.getDragboard().hasString()) {
//                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
//                }
//
//                event.consume();
//            }
//        });
//
//        circle2.setOnDragDropped((DragEvent event) -> {
//            Dragboard db = event.getDragboard();
//            if (db.hasString()) {
//                System.out.println("Dropped: " + db.getString());
//                event.setDropCompleted(true);
//            } else {
//                event.setDropCompleted(false);
//            }
//            event.consume();
//        });
//
//        Pane pane = new Pane();
//        pane.getChildren().add(circle);
//        pane.getChildren().add(circle2);
//
//        Scene scene = new Scene(pane, 1024, 800, true);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("2D Example");
//
//        primaryStage.show();
//    }
//
//    private Circle createCircle(String strokeColor, String fillColor, double x) {
//        Circle circle = new Circle();
//        circle.setCenterX(x);
//        circle.setCenterY(200);
//        circle.setRadius(50);
//        circle.setStroke(Color.valueOf(strokeColor));
//        circle.setStrokeWidth(5);
//        circle.setFill(Color.valueOf(fillColor));
//        return circle;
//    }
//}
//import javafx.application.Application;
//import javafx.application.ConditionalFeature;
//import javafx.application.Platform;
//import javafx.scene.Group;
//import javafx.scene.PerspectiveCamera;
//import javafx.scene.Scene;
//import javafx.scene.shape.Box;
//import javafx.scene.shape.CullFace;
//import javafx.scene.transform.Rotate;
//import javafx.stage.Stage;
//
//public class Test extends Application {
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    public void start(Stage primaryStage) {
//
//        boolean is3DSupported = Platform.isSupported(ConditionalFeature.SCENE3D);
//        if(!is3DSupported) {
//           System.out.println("Sorry, 3D is not supported in JavaFX on this platform.");
//           return;
//        }
//
//        Box box = new Box(100,100,100);
//        box.setCullFace(CullFace.NONE);
//        box.setTranslateX(250);
//        box.setTranslateY(100);
//        box.setTranslateZ(400);
//
//        boolean fixedEyeAtCameraZero = false;
//        PerspectiveCamera camera = new PerspectiveCamera(fixedEyeAtCameraZero);
//        camera.setTranslateX(150);
//        camera.setTranslateY(-100);
//        camera.setTranslateZ(250);
//
//        Group root = new Group(box);
//        root.setRotationAxis(Rotate.X_AXIS);
//        root.setRotate(30);
//
//        Scene scene = new Scene(root, 500, 300, true);
//        scene.setCamera(camera);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("3D Example");
//
//        primaryStage.show();
//    }
//}
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.control.Pagination;
//import javafx.scene.layout.VBox;
//import javafx.scene.text.Font;
//import javafx.stage.Stage;
//
//public class Test extends Application {
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.setTitle("JavaFX App");
//
//        Pagination pagination = new Pagination();
//        pagination.setPageCount(21);
//        pagination.setCurrentPageIndex(3);
//        pagination.setMaxPageIndicatorCount(3);
//
//        pagination.setPageFactory((pageIndex) -> {
//
//            Label label1 = new Label("Content for page with index: " + pageIndex);
//            label1.setFont(new Font("Arial", 24));
//
//            Label label2 = new Label("Main content of the page ...");
//
//            return new VBox(label1, label2);
//        });
//
//        VBox vBox = new VBox(pagination);
//        Scene scene = new Scene(vBox, 960, 600);
//
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//}
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.layout.VBox;
//import javafx.scene.web.HTMLEditor;
//import javafx.stage.Stage;
//
//public class Test extends Application {
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    public void start(Stage primaryStage) {
//
//        HTMLEditor htmlEditor = new HTMLEditor();
//
//        VBox vBox = new VBox(htmlEditor);
//        Scene scene = new Scene(vBox);
//
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("JavaFX App");
//
//        primaryStage.show();
//    }
//}
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.TextArea;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import org.controlsfx.control.textfield.AutoCompletionBinding;
//import org.controlsfx.control.textfield.TextFields;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class Test extends Application {
//
//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.setTitle("TextArea Suggestions");
//
//        TextArea textArea = new TextArea();
//        textArea.setPromptText("Start typing...");
//
//        // List of suggestions
//        List<String> suggestions = Arrays.asList("Java", "JavaFX", "JavaScript", "Python", "C++", "C#", "Ruby", "PHP");
//
//        // Bind auto-completion to the text area
//        AutoCompletionBinding<String> autoCompletionBinding = TextFields.bindAutoCompletion(textArea, suggestions);
//
//        VBox vbox = new VBox(textArea);
//        Scene scene = new Scene(vbox, 400, 300);
//
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }

//    public static void main(String[] args) {
//        launch(args);
//    }
//}

//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.layout.StackPane;
//import javafx.stage.Stage;
//import javafx.stage.StageStyle;
//
//public class Test extends Application {
//
//    @Override
//    public void start(Stage primaryStage) {
//        Button button = new Button("Open Popup");
//        button.setOnAction(e -> openPopup());
//
//        StackPane root = new StackPane(button);
//        Scene scene = new Scene(root, 300, 250);
//
//        primaryStage.setTitle("Main Window");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    private void openPopup() {
//        Stage popupStage = new Stage();
//        popupStage.initStyle(StageStyle.TRANSPARENT);
//
//        StackPane popupLayout = new StackPane();
//        Scene popupScene = new Scene(popupLayout, 200, 100);
//        
//        // Add your content here (e.g., another button or a label)
////        Button closeButton = new Button("Close");
////        closeButton.setOnAction(e -> popupStage.close());
////        popupLayout.getChildren().add(closeButton);
//
//        // Set CSS
//        popupScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//
//        popupStage.setScene(popupScene);
//        popupStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}

//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.layout.VBox;
//import javafx.stage.Popup;
//import javafx.stage.Stage;
//
//public class Test extends Application{
//
//	@Override
//	public void start(Stage stage) throws Exception {
//		Button bt=new Button("show");
//		Label label=new Label("Welcome in popup window");
//		Label label1=new Label("Welcome in popup window");
//		Popup pp=new Popup();
//		pp.getContent().add(label);
//		pp.getContent().add(label1);
//		label.setMinWidth(97);
//		label.setMinHeight(63);
//		bt.setOnAction(e->{
//			if (!pp.isShowing())
//				pp.show(stage);
//				else
//				pp.hide();
//				System.out.println("The popup is hidden");
//		});
//		VBox box=new VBox(bt);
//		box.setStyle("fx-border-color: black;");
//		Scene scene=new Scene(box,303,302);
//		stage.setScene(scene);
//		stage.show();
//		
//	}
//	public static void main(String[]args) {
//		launch();
//	}
//}
//import javafx.scene.Scene;
//import javafx.scene.control.ListView;
//import javafx.scene.control.TextArea;
//import javafx.scene.layout.VBox;
//import javafx.stage.Popup;
//import javafx.stage.Stage;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class Test extends Application {
//
//    private List<String> suggestions = Arrays.asList("apple", "banana", "grape", "orange", "watermelon");
//    TextArea textArea;
//
//    @Override
//    public void start(Stage primaryStage) {
//        textArea = new TextArea();
//        Popup suggestionPopup = new Popup();
//        ListView<String> suggestionList = new ListView<>();
//
//        // Add the ListView to the Popup
//        suggestionPopup.getContent().add(suggestionList);
//
//        // Listen for text changes in the TextArea
//        textArea.textProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue.isEmpty()) {
//                suggestionPopup.hide();
//            } else {
//                updateSuggestions(newValue, suggestionList, suggestionPopup);
//            }
//        });
//
//        // Handle suggestion selection
//        suggestionList.setOnMouseClicked(event -> {
//            String selected = suggestionList.getSelectionModel().getSelectedItem();
//            if (selected != null) {
//                textArea.setText(selected);
//                suggestionPopup.hide(); // Hide popup after selection
//                textArea.requestFocus(); // Refocus on the TextArea
//            }
//        });
//
//        VBox vbox = new VBox(textArea);
//        Scene scene = new Scene(vbox, 400, 300);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("TextArea Popup Suggestion Example");
//        primaryStage.show();
//    }
//
//    private void updateSuggestions(String input, ListView<String> suggestionList, Popup suggestionPopup) {
//        List<String> filteredSuggestions = suggestions.stream()
//                .filter(s -> s.toLowerCase().startsWith(input.toLowerCase()))
//                .collect(Collectors.toList());
//
//        suggestionList.getItems().setAll(filteredSuggestions);
//        if (!filteredSuggestions.isEmpty()) {
//            suggestionPopup.setAutoHide(true);
//            suggestionPopup.show(suggestionList.getScene().getWindow());
//            suggestionList.setPrefHeight(100); // Set preferred height for the list
//            suggestionList.setPrefWidth(200); // Set preferred width for the list
//
//            // Position the popup below the TextArea
//            suggestionPopup.setAnchorX(textArea.getLayoutX());
//            suggestionPopup.setAnchorY(textArea.getLayoutY() + textArea.getHeight());
//        } else {
//            suggestionPopup.hide(); // Hide if no suggestions
//        }
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}

//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.TextArea;
//import javafx.scene.control.TreeItem;
//import javafx.scene.control.TreeView;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class Test extends Application {
//
//    private List<String> suggestions = Arrays.asList("apple", "banana", "grape", "orange", "watermelon");
//
//    @Override
//    public void start(Stage primaryStage) {
//        TextArea textArea = new TextArea();
//        TreeView<String> suggestionTree = new TreeView<>();
//
//        // Listen for text changes in the TextArea
//        textArea.textProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue.isEmpty()) {
//                suggestionTree.setRoot(null);
//            } else {
//                updateSuggestions(newValue, suggestionTree);
//            }
//        });
//
//        // Handle suggestion selection
//        suggestionTree.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue != null) {
//                textArea.setText(newValue.getValue());
//                suggestionTree.setRoot(null); // Clear suggestions after selection
//                textArea.requestFocus(); // Refocus on the TextArea
//            }
//        });
//
//        VBox vbox = new VBox(textArea, suggestionTree);
//        Scene scene = new Scene(vbox, 400, 300);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("TextArea TreeView Suggestion Example");
//        primaryStage.show();
//    }
//
//    private void updateSuggestions(String input, TreeView<String> suggestionTree) {
//        List<String> filteredSuggestions = suggestions.stream()
//                .filter(s -> s.toLowerCase().startsWith(input.toLowerCase()))
//                .collect(Collectors.toList());
//
//        TreeItem<String> rootItem = new TreeItem<>("Suggestions");
//        for (String suggestion : filteredSuggestions) {
//            rootItem.getChildren().add(new TreeItem<>(suggestion));
//        }
//
//        if (!filteredSuggestions.isEmpty()) {
//            suggestionTree.setRoot(rootItem);
//        } else {
//            suggestionTree.setRoot(null); // Clear if no suggestions
//        }
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}

//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.ListView;
//import javafx.scene.control.TextArea;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class Test extends Application {
//
//    private List<String> suggestions = Arrays.asList("apple", "banana", "grape", "orange", "watermelon");
//
//    @Override
//    public void start(Stage primaryStage) {
//        TextArea textArea = new TextArea();
//        ListView<String> suggestionList = new ListView<>();
//
//        // Handle text changes
//        textArea.textProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue.isEmpty()) {
//                suggestionList.getItems().clear();
//            } else {
//                updateSuggestions(newValue, suggestionList);
//            }
//        });
//
//        // Handle suggestion selection
//        suggestionList.setOnMouseClicked(event -> {
//            String selected = suggestionList.getSelectionModel().getSelectedItem();
//            if (selected != null) {
//                textArea.setText(selected);
//                suggestionList.getItems().clear(); // Clear suggestions after selection
//            }
//        });
//
//        VBox vbox = new VBox(textArea, suggestionList);
//        Scene scene = new Scene(vbox, 400, 300);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("TextArea Suggestion Example");
//        primaryStage.show();
//    }
//
//    private void updateSuggestions(String input, ListView<String> suggestionList) {
//        List<String> filteredSuggestions = suggestions.stream()
//                .filter(s -> s.toLowerCase().startsWith(input.toLowerCase()))
//                .collect(Collectors.toList());
//
//        suggestionList.getItems().setAll(filteredSuggestions);
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}

//import javafx.stage.Stage;
//
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//
//import javafx.application.Application;
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
//import javafx.scene.Scene;
//import javafx.scene.control.TextArea;
//import javafx.scene.control.TreeItem;
//import javafx.scene.control.TreeView;
//import javafx.scene.input.KeyCode;
//import javafx.scene.layout.Priority;
//import javafx.scene.layout.VBox;
//public class Test extends Application{
//	String fileData="";
//	@Override
//	public void start(Stage stage) throws Exception {
//		stage.setTitle("Test");
//		TreeItem<String> rootItem = new TreeItem<String>("Tutorials");
//		TreeItem<String> webItem = new TreeItem<String>("Web Tutorials");
//		webItem.getChildren().add(new TreeItem<String>("HTML  Tutorial"));
//		webItem.getChildren().add(new TreeItem<String>("HTML5 Tutorial"));
//		webItem.getChildren().add(new TreeItem<String>("CSS Tutorial"));
//		webItem.getChildren().add(new TreeItem<String>("SVG Tutorial"));
//		rootItem.getChildren().add(webItem);
//
//		TreeItem<String> javaItem = new TreeItem<String>("Java Tutorials");
//		javaItem.getChildren().add(new TreeItem<String>("Java Language"));
//		javaItem.getChildren().add(new TreeItem<String>("Java Collections"));
//		javaItem.getChildren().add(new TreeItem<String>("Java Concurrency"));
//		rootItem.getChildren().add(javaItem);
//
//		TreeView<String> treeView = new TreeView<String>();
//		treeView.setRoot(rootItem);
//		TextArea textArea=new TextArea();
//		VBox root=new VBox(textArea);
//		VBox.setVgrow(textArea,Priority.ALWAYS);
//		textArea.textProperty().addListener(new ChangeListener<String>() {
//		    //File myFile=new File("C:\\Users\\raja\\Documents\\Text editor files\\predefined.html");
//			@Override
//			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//				TreeItem<String> rootItem = new TreeItem<String>("Tutorials");
//				TreeItem<String> webItem = new TreeItem<String>("Web Tutorials");
//				webItem.getChildren().add(new TreeItem<String>("HTML  Tutorial"));
//				webItem.getChildren().add(new TreeItem<String>("HTML5 Tutorial"));
//				webItem.getChildren().add(new TreeItem<String>("CSS Tutorial"));
//				webItem.getChildren().add(new TreeItem<String>("SVG Tutorial"));
//				rootItem.getChildren().add(webItem);
//				TreeView<String> treeView = new TreeView<String>();
//				treeView.setRoot(rootItem);
//				
//				
////				if(newValue.contains("!")) {
////					textArea.setOnKeyPressed(event->{
////						if(event.getCode()==KeyCode.ENTER) {
////							try (FileReader reader = new FileReader(myFile)) {
////			                	int data=reader.read();
////			                	while(data!=-1) {
////			                		fileData=fileData+(char)data;
////			                		textArea.setText(fileData);
////			                		data=reader.read();
////			                	}
////							
////			               
////							   reader.close();
////							} 
////							catch (IOException ioException) {
////			                    ioException.printStackTrace();
////			                }
////						}	
////					});
//				//}
//			}
//			});
//		Scene scene=new Scene(root);
//		stage.setScene(scene);
//		stage.show();
//		
//	}
//	
//}
