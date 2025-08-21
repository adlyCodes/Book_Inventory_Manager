package  com.example.demo;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.text.*;
import java.sql.*;

public class HelloApplication extends Application {

    int counter ,x ,counterISBN, xISBN, myResultSetAdd = 0;
    Text editBook;
    Text bookName = new Text ("Book Name: ");
    TextField addName = new TextField();
    Text bookYear = new Text ("Book Year: ");
    TextField addYear = new TextField();
    Text bookAuthor = new Text ("Book Author: ");
    TextField addAuthor = new TextField();
    Text bookISBN = new Text ("Book ISBN: ");
    TextField addISBN = new TextField();
    TextField deleteName = new TextField() ;
    TextField deleteYear = new TextField();
    TextField deleteAuthor = new TextField();
    TextField deleteISBN = new TextField();
    Text selectByName = new Text("Select A Book By Name: ");
    Text selectByISBN = new Text("Select A Book By ISBN: ");
    TextField selectByNameField = new TextField();
    TextField selectByISBNField = new TextField();
    Text searchByNameCriteria = new Text ("Enter Name To Search By: ");
    Text searchByYearCriteria = new Text ("Enter Year To Search By: ");
    Text searchByAuthorCriteria = new Text ("Enter Author To Search By: ");
    TextField nameCriteriaField = new TextField();
    TextField yearCriteriaField = new TextField();
    TextField authorCriteriaField = new TextField();
    Text selectEditBookName = new Text ("Type Book's Name To Edit: ");
    Text selectEditBookISBN = new Text ("Type Book's ISBN To Edit: ");
    TextField selectEditBookNameField = new TextField();
    TextField selectEditBookISBNField = new TextField();
    Text updateName = new Text ("Update Name");
    Text updateYear = new Text ("Update Year");
    Text updateAuthor = new Text ("Update Author");
    Text updateISBN = new Text ("Update ISBN");
    TextField updateNameField = new TextField();
    TextField updateYearField = new TextField();
    TextField updateAuthorField = new TextField();
    TextField updateISBNField = new TextField();

    public static void main(String[] args) {launch();}

    GridPane pane = new GridPane();
    Text text = new Text ("Welcome to Book Inventory");

    @Override
    public void start(Stage stage) {
        Button viewbooks = new Button ("View Books");viewbooks.setCursor(Cursor.HAND);
        Button addbooks = new Button ("Add Books");addbooks.setCursor(Cursor.HAND);
        Button applyAddition = new Button ("Add");applyAddition.setCursor(Cursor.HAND);
        Button editbooks = new Button ("Edit Books");editbooks.setCursor(Cursor.HAND);
        Button editBooksSelectName = new Button ("Find By Name");editBooksSelectName.setCursor(Cursor.HAND);
        Button editBooksSelectISBN = new Button ("Find By ISBN");editBooksSelectISBN.setCursor(Cursor.HAND);
        Button updateNameByName = new Button ("Update Name");updateNameByName.setCursor(Cursor.HAND);
        Button updateYearByName = new Button ("Update Year");updateYearByName.setCursor(Cursor.HAND);
        Button updateAuthorByName = new Button ("Update Author");updateAuthorByName.setCursor(Cursor.HAND);
        Button updateISBNByName = new Button ("Update ISBN");updateISBNByName.setCursor(Cursor.HAND);
        Button updateNameByISBN =new Button("Update Name");updateNameByISBN.setCursor(Cursor.HAND);
        Button updateYearByISBN=new Button("Update Year");updateYearByISBN.setCursor(Cursor.HAND);
        Button updateAuthorByISBN=new Button("Update Author");updateAuthorByISBN.setCursor(Cursor.HAND);
        Button updateISBNByISBN=new Button("Update ISBN");updateISBNByISBN.setCursor(Cursor.HAND);
        Button searchbooks = new Button ("Search Books");searchbooks.setCursor(Cursor.HAND);
        Button searchNameCriteriaButton = new Button ("Search By Name");searchNameCriteriaButton.setCursor(Cursor.HAND);
        Button searchYearCriteriaButton = new Button ("Search By Year");searchYearCriteriaButton.setCursor(Cursor.HAND);
        Button searchAuthorCriteriaButton = new Button ("Search By Author");searchAuthorCriteriaButton.setCursor(Cursor.HAND);
        Button viewbookdetails = new Button ("View Book Details");viewbookdetails.setCursor(Cursor.HAND);
        Button viewDetailsByName = new Button ("View Details By Name");viewDetailsByName.setCursor(Cursor.HAND);
        Button viewDetailsByISBN = new Button("View Details By ISBN");viewDetailsByISBN.setCursor(Cursor.HAND);
        Button deletebooks = new Button ("Delete Books");deletebooks.setCursor(Cursor.HAND);
        Button deletebooksbyname = new Button ("Delete");deletebooksbyname.setCursor(Cursor.HAND);
        Button deletebooksbyyear = new Button ("Delete");deletebooksbyyear.setCursor(Cursor.HAND);
        Button deletebooksbyauthor = new Button ("Delete");deletebooksbyauthor.setCursor(Cursor.HAND);
        Button deletebooksbyisbn = new Button ("Delete");deletebooksbyisbn.setCursor(Cursor.HAND);

        Button closeButton = new Button("Close");
        closeButton.setCursor(Cursor.HAND);
        closeButton.setOnAction(event -> {
            Stage currentStage = (Stage) closeButton.getScene().getWindow();
            currentStage.close();
        });

        viewbooks.setOnAction(actionEvent -> {
                    try {
                        Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost/bookinventory", "root", "12345");
                        // Establish connection, hostname: local host, database name: bookinventory
                        Statement myStatementView = myConnection.createStatement();
                        ResultSet myResultSetView = myStatementView.executeQuery("SELECT * FROM bookinventory.tablebook");

                        GridPane viewAllGridPane=new GridPane();
                        Text text1=new Text();
                        String stringViewAll="";

                        int i=0;
                        while (myResultSetView.next()){
                            i++;
                            stringViewAll +="     ID "+i+" : "+myResultSetView.getInt("ID") + "    Name "+i+" : "
                                    + myResultSetView.getString("Name") +"     year "+i+" : "
                                    + myResultSetView.getInt("Year") + "     Author "+i+" : "
                                    + myResultSetView.getString("Author") + "     ISBN "+i+" : "
                                    + myResultSetView.getString("ISBN" ) + "\n" +
                                    "-------------------------------------------------------------------------------------------------------------------" + "\n";

                            System.out.println(myResultSetView.getInt("ID") + " " + myResultSetView.getString("Name") + " "
                                    + myResultSetView.getInt("Year") + " "
                                    + myResultSetView.getString("Author") + " " + myResultSetView.getString("ISBN"));
                        }
                        closeButton.setCursor(Cursor.HAND);
                        closeButton.setOnAction(event -> {
                            Stage currentStage = (Stage) closeButton.getScene().getWindow();
                            currentStage.close();
                        });

                        text1.setText(stringViewAll);
                        viewAllGridPane.add(text1,0,1);
                        viewAllGridPane.add(closeButton,0,2);
                        viewAllGridPane.setPadding(new Insets(25,25,25,25));
                        Stage viewAll =new Stage();
                        Scene viewAllScene=new Scene(viewAllGridPane);
                        viewAll.setScene(viewAllScene);
                        viewAll.setTitle("View All Inventory");
                        viewAll.setMinWidth(100);
                        viewAll.setMinHeight(100);
                        viewAll.show();
                    }
                    catch (Exception e) {System.out.println(e.toString());}
                }
        );
        addbooks.setOnAction(actionEvent -> {
            try {
                GridPane paneAdd = new GridPane();
                Stage stageAddBook = new Stage();
                stageAddBook.setTitle("Add New Book");
                Scene addScene = new Scene(paneAdd, 500,350);
                stageAddBook.setScene(addScene);
                stageAddBook.show();

                paneAdd.setPadding(new Insets(25,25,25,25));
                paneAdd.setHgap(25);
                paneAdd.setVgap(10);

                paneAdd.add(bookName, 0,0);
                paneAdd.add(bookYear, 0,1);
                paneAdd.add(bookAuthor, 0,2);
                paneAdd.add(bookISBN, 0,3);
                paneAdd.add(addName, 1,0);
                paneAdd.add(addYear, 1,1);
                paneAdd.add(addAuthor, 1,2);
                paneAdd.add(addISBN, 1,3);
                paneAdd.add(applyAddition,1,4);
                paneAdd.add(closeButton,0,4);
            }
            catch (Exception e) {System.out.println(e.toString());}
        });
        applyAddition.setOnAction(actionEvent -> {

            Text test = new Text();
            test.setFont(Font.font("Times New Roman", FontWeight.BOLD,FontPosture.ITALIC,20));
            test.setStyle("-fx-font-size: 20px;");
            GridPane applyAdditionPane = new GridPane();
            Scene applyAdditionScene = new Scene(applyAdditionPane, 750,50);
            Stage stageApplyAddition = new Stage();
            stageApplyAddition.setScene(applyAdditionScene);

            try {
                Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost/bookinventory", "root", "12345");
                // Establish connection, hostname: local host, database name: bookinventory
                Statement myStatementAdd = myConnection.createStatement();

                String nameNew = addName.getText();
                String yearNew =addYear.getText();
                String authorNew = addAuthor.getText();
                String isbnNew = addISBN.getText();

                String space = "";
                ResultSet myResultSetNoTwoISBN = myStatementAdd.executeQuery("SELECT * FROM bookinventory.tablebook WHERE ISBN ='"+isbnNew+"'");

                while (myResultSetNoTwoISBN.next())
                {
                    counterISBN++;
                    xISBN = counterISBN;
                }
                counterISBN = 0;

                if (xISBN >= 1) {test.setText("ISBN is already used before, Please try again");}
                else if(nameNew.isBlank()){test.setText("Book's Name should inputted, Please try again");}
                else if (yearNew.equals(space)) {test.setText("Book's Year should be inputted, Please try again");}
                else if(authorNew.isBlank()){test.setText("Book's Author should be inputted, Please try again");}
                else if(isbnNew.isBlank()){test.setText("Book's ISBN should be inputted, Please try again");}
                else {
                    myResultSetAdd = 0;
                    String addq1 =
                            "INSERT INTO tablebook (Name, Year, Author, ISBN) VALUES ('"+nameNew+"','"+yearNew+"','"+authorNew+"','" +isbnNew+"')";
                    myResultSetAdd = myStatementAdd.executeUpdate(addq1);
                    if (myResultSetAdd > 0) {test.setText("Successfully inserted");}
                }
                xISBN = 0;
                myResultSetNoTwoISBN.close();

                stageApplyAddition.setTitle("Book Insertion");
                applyAdditionPane.add(test,0,0);
                applyAdditionPane.setAlignment(Pos.CENTER);
                applyAdditionPane.setPadding(new Insets(25,25,25,25));
                applyAdditionPane.setVgap(50);
                applyAdditionPane.setHgap(50);
                stageApplyAddition.show();
            }
            catch (Exception e) {test.setText(e.toString());}
        });
        deletebooks.setOnAction(actionEvent -> {
            try {
                GridPane paneDelete = new GridPane();
                Stage stageDeleteBook = new Stage();
                stageDeleteBook.setTitle("Delete Book");
                Scene deleteScene = new Scene(paneDelete,500,350);
                stageDeleteBook.setScene(deleteScene);
                stageDeleteBook.show();

                paneDelete.setPadding(new Insets(25,25,25,25));
                paneDelete.setHgap(25);
                paneDelete.setVgap(10);

                paneDelete.add(bookName, 0,0);
                paneDelete.add(bookYear, 0,1);
                paneDelete.add(bookAuthor, 0,2);
                paneDelete.add(bookISBN, 0,3);
                paneDelete.add(deleteName, 1,0);
                paneDelete.add(deleteYear, 1,1);
                paneDelete.add(deleteAuthor, 1,2);
                paneDelete.add(deleteISBN, 1,3);
                paneDelete.add(deletebooksbyname,2,0);
                paneDelete.add(deletebooksbyyear,2,1);
                paneDelete.add(deletebooksbyauthor,2,2);
                paneDelete.add(deletebooksbyisbn,2,3);
                paneDelete.add(closeButton, 0,4);
            }
            catch (Exception e) {System.out.println(e.toString());}
        });
        deletebooksbyname.setOnAction(actionEvent -> {
            try{
                Text test1 = new Text();
                test1.setFont(Font.font("Times New Roman", FontWeight.BOLD,FontPosture.ITALIC,20));
                test1.setStyle("-fx-font-size: 20px;");
                GridPane deleteBooksByNamePane = new GridPane();
                Scene deleteBookByNameScene = new Scene(deleteBooksByNamePane, 750,50);
                Stage stageDeleteBookByName = new Stage();
                stageDeleteBookByName.setScene(deleteBookByNameScene);

                Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost/bookinventory", "root", "12345");
                // Establish connection, hostname: local host, database name: bookinventory
                Statement myStatementAdd = myConnection.createStatement();

                String nameDelete = deleteName.getText();

                String deleteq1 = "DELETE from tablebook WHERE Name = '"+nameDelete+"'";
                myResultSetAdd = myStatementAdd.executeUpdate(deleteq1);

                if (myResultSetAdd > 0) {test1.setText("Successfully deleted");}
                else if(nameDelete.isBlank()){test1.setText("Book's Name should be inputted, Please try again");}
                else {test1.setText("Book not found, deletion failed");}

                stageDeleteBookByName.setTitle("Book Deletion");
                deleteBooksByNamePane.add(test1,0,0);
                deleteBooksByNamePane.setAlignment(Pos.CENTER);
                deleteBooksByNamePane.setPadding(new Insets(25,25,25,25));
                deleteBooksByNamePane.setVgap(50);
                deleteBooksByNamePane.setHgap(50);
                stageDeleteBookByName.show();
            }
            catch (Exception e){System.out.println(e.toString());}
        });
        deletebooksbyyear.setOnAction(actionEvent -> {
            try{
                Text test2 = new Text();
                test2.setFont(Font.font("Times New Roman", FontWeight.BOLD,FontPosture.ITALIC,20));
                test2.setStyle("-fx-font-size: 20px;");
                GridPane deleteBooksByYearPane = new GridPane();
                Scene deleteBookByYearScene = new Scene(deleteBooksByYearPane, 750,50);
                Stage stageDeleteBookByYear = new Stage();
                stageDeleteBookByYear.setScene(deleteBookByYearScene);

                Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost/bookinventory", "root", "12345");
                // Establish connection, hostname: local host, database name: bookinventory
                Statement myStatementAdd = myConnection.createStatement();
                String yearDelete = deleteYear.getText();
                String deleteq2 = "DELETE from tablebook WHERE Year = '"+yearDelete+"'";
                myResultSetAdd = myStatementAdd.executeUpdate(deleteq2);

                if (myResultSetAdd > 0) {test2.setText("Successfully deleted");}
                else if(yearDelete.isBlank()){test2.setText("Book's Year should be inputted, Please try again");}
                else {test2.setText("Book not found, deletion failed");}

                stageDeleteBookByYear.setTitle("Book Deletion");
                deleteBooksByYearPane.add(test2,0,0);
                deleteBooksByYearPane.setAlignment(Pos.CENTER);
                deleteBooksByYearPane.setPadding(new Insets(25,25,25,25));
                deleteBooksByYearPane.setVgap(50);
                deleteBooksByYearPane.setHgap(50);
                stageDeleteBookByYear.show();
            }
            catch (Exception e){System.out.println(e.toString());}
        });
        deletebooksbyauthor.setOnAction(actionEvent -> {
            try{
                Text test3 = new Text();
                test3.setFont(Font.font("Times New Roman", FontWeight.BOLD,FontPosture.ITALIC,20));
                test3.setStyle("-fx-font-size: 20px;");
                GridPane deleteBooksByAuthorPane = new GridPane();
                Scene deleteBookByAuthorScene = new Scene(deleteBooksByAuthorPane, 750,50);
                Stage stageDeleteBookByAuthor = new Stage();
                stageDeleteBookByAuthor.setScene(deleteBookByAuthorScene);

                Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost/bookinventory", "root", "12345");
                // Establish connection, hostname: local host, database name: bookinventory
                Statement myStatementAdd = myConnection.createStatement();
                String authorDelete = deleteAuthor.getText();
                String deleteq3 = "DELETE from tablebook WHERE Author = '"+authorDelete+"'";
                myResultSetAdd = myStatementAdd.executeUpdate(deleteq3);

                if (myResultSetAdd > 0) {test3.setText("Successfully deleted");}
                else if(authorDelete.isBlank()){test3.setText("Book's Author should be inputted, Please try again");}
                else {test3.setText("Book not found, deletion failed");}

                stageDeleteBookByAuthor.setTitle("Book Deletion");
                deleteBooksByAuthorPane.add(test3,0,0);
                deleteBooksByAuthorPane.setAlignment(Pos.CENTER);
                deleteBooksByAuthorPane.setPadding(new Insets(25,25,25,25));
                deleteBooksByAuthorPane.setVgap(50);
                deleteBooksByAuthorPane.setHgap(50);
                stageDeleteBookByAuthor.show();
            }
            catch (Exception e){System.out.println(e.toString());}
        });
        deletebooksbyisbn.setOnAction(actionEvent -> {
            try{

                Text test4 = new Text();
                test4.setFont(Font.font("Times New Roman", FontWeight.BOLD,FontPosture.ITALIC,20));
                test4.setStyle("-fx-font-size: 20px;");
                GridPane deleteBooksByISBNPane = new GridPane();
                Scene deleteBookByISBNScene = new Scene(deleteBooksByISBNPane, 750,50);
                Stage stageDeleteBookByISBN = new Stage();
                stageDeleteBookByISBN.setScene(deleteBookByISBNScene);

                Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost/bookinventory", "root", "12345");
                // Establish connection, hostname: local host, database name: bookinventory
                Statement myStatementAdd = myConnection.createStatement();
                String isbnDelete = deleteISBN.getText();
                String deleteq4 = "DELETE from tablebook WHERE ISBN = '"+isbnDelete+"'";

                myResultSetAdd = myStatementAdd.executeUpdate(deleteq4);

                if (myResultSetAdd > 0) {test4.setText("Successfully deleted");}
                else if(isbnDelete.isBlank()){test4.setText("Book's ISBN should be inputted, Please try again");}
                else {test4.setText("Book not found, deletion failed");}

                stageDeleteBookByISBN.setTitle("Book Deletion");
                deleteBooksByISBNPane.add(test4,0,0);
                deleteBooksByISBNPane.setAlignment(Pos.CENTER);
                deleteBooksByISBNPane.setPadding(new Insets(25,25,25,25));
                deleteBooksByISBNPane.setVgap(50);
                deleteBooksByISBNPane.setHgap(50);
                stageDeleteBookByISBN.show();
            }
            catch (Exception e){System.out.println(e.toString());}
        });
        viewbookdetails.setOnAction(actionEvent -> {
            try {
                GridPane paneViewBookDetails = new GridPane();
                Stage stageViewBookDetails = new Stage();
                stageViewBookDetails.setTitle("View Book Details");
                Scene viewBookDetailsScene = new Scene(paneViewBookDetails,750,350);
                stageViewBookDetails.setScene(viewBookDetailsScene);
                stageViewBookDetails.show();

                paneViewBookDetails.setPadding(new Insets(25,25,25,25));
                paneViewBookDetails.setHgap(20);
                paneViewBookDetails.setVgap(10);

                paneViewBookDetails.add(selectByName, 0,0);
                paneViewBookDetails.add(selectByISBN, 0,1);
                paneViewBookDetails.add(selectByNameField, 1,0);
                paneViewBookDetails.add(selectByISBNField, 1,1);
                paneViewBookDetails.add(viewDetailsByName, 2,0);
                paneViewBookDetails.add(viewDetailsByISBN,2,1);
                paneViewBookDetails.add(closeButton,0,2);
            }
            catch (Exception e) {System.out.println(e.toString());}
        });
        viewDetailsByName.setOnAction(actionEvent -> {
            try{

                Text test5 = new Text();
                test5.setFont(Font.font("Times New Roman", FontWeight.BOLD,FontPosture.ITALIC,20));
                test5.setStyle("-fx-font-size: 20px;");
                GridPane viewDetailsByNamePane = new GridPane();
                Scene viewDetailsByNameScene = new Scene(viewDetailsByNamePane, 750,350);
                Stage stageViewDetailsByName = new Stage();
                stageViewDetailsByName.setScene(viewDetailsByNameScene);

                Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost/bookinventory", "root", "12345");
                // Establish connection, hostname: local host, database name: bookinventory
                Statement myStatementViewBookDetails = myConnection.createStatement();

                String nameViewBookDetails = selectByNameField.getText();

                String viewq1 = "SELECT * from tablebook WHERE Name = '"+nameViewBookDetails+"'";

                ResultSet myResultSetViewBookDetails = myStatementViewBookDetails.executeQuery(viewq1);
                while (myResultSetViewBookDetails.next())
                {
                    counter++;
                    x = counter;
                }
                counter = 0;

                if (selectByNameField.getText().isBlank()){test5.setText("Book's Name is not inputted");}
                else if (x >= 2 ){test5.setText("Inputted name is assigned to "+x+" other books, please specify by ISBN");}
                else if (x == 0) {test5.setText("Book's Name is not found, Please try again");}
                else{
                    myResultSetViewBookDetails.close();
                    Connection myConnect= DriverManager.getConnection("jdbc:mysql://localhost/bookinventory", "root", "12345");
                    Statement myStatement = myConnect.createStatement();
                    ResultSet myResult=myStatementViewBookDetails.executeQuery("SELECT * from tablebook");

                    int i=0;
                    while(myResult.next()) {

                        if (myResult.getString("Name").equals(nameViewBookDetails)) {

                            i++;

                            test5.setText("     ID "+i+" : "+myResult.getInt("ID") + "    Name "+i+" : "
                                    + myResult.getString("Name") +"     year "+i+" : "
                                    + myResult.getInt("Year") + "     Author "+i+" : "
                                    + myResult.getString("Author") + "     ISBN "+i+" : "
                                    + myResult.getString("ISBN" ) + "\n" +
                                    "-------------------------------------------------------------------------------------------------------------------" + "\n");
                        }
                    }
                }
                x=0;

                stageViewDetailsByName.setTitle("Book Details");
                viewDetailsByNamePane.add(test5,0,0);
                viewDetailsByNamePane.setAlignment(Pos.CENTER);
                viewDetailsByNamePane.setPadding(new Insets(25,25,25,25));
                viewDetailsByNamePane.setVgap(50);
                viewDetailsByNamePane.setHgap(50);
                stageViewDetailsByName.show();

            }
            catch (Exception e) {System.out.println(e.toString());}
        });
        viewDetailsByISBN.setOnAction(actionEvent -> {
            try {

                Text test6 = new Text();
                test6.setFont(Font.font("Times New Roman", FontWeight.BOLD,FontPosture.ITALIC,20));
                test6.setStyle("-fx-font-size: 20px;");
                GridPane viewDetailsByISBNPane = new GridPane();
                Scene viewDetailsByISBNScene = new Scene(viewDetailsByISBNPane, 750,350);
                Stage stageViewDetailsByISBN = new Stage();
                stageViewDetailsByISBN.setScene(viewDetailsByISBNScene);

                Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost/bookinventory", "root", "12345");
                // Establish connection, hostname: local host, database name: bookinventory
                Statement myStatementViewBookDetails = myConnection.createStatement();

                String isbnViewBookDetails = selectByISBNField.getText();

                String viewq3 = "SELECT * from tablebook WHERE ISBN ='"+isbnViewBookDetails+"'";

                ResultSet myResultSetViewBookDetails = myStatementViewBookDetails.executeQuery(viewq3);

                while (myResultSetViewBookDetails.next())
                {
                    counter++;
                    x = counter;
                }
                counter = 0;
                myResultSetViewBookDetails.close();

                if (selectByISBNField.getText().isBlank()){test6.setText("Book's ISBN is not inputted");}
                else if (x >= 2) {test6.setText("Inputted ISBN is assigned to another book, please try again");}
                else if (x == 0) {test6.setText("Book ISBN not found, please try again");}
                else {
                    Connection myConnect = DriverManager.getConnection("jdbc:mysql://localhost/bookinventory", "root", "12345");
                    Statement myStatement = myConnect.createStatement();
                    ResultSet myResult = myStatementViewBookDetails.executeQuery("SELECT * from tablebook");

                    int i=0;
                    while(myResult.next()) {

                        if (myResult.getString("ISBN").equals(isbnViewBookDetails)) {
                            i++;
                            test6.setText("     ID "+i+" : "+myResult.getInt("ID") + "    Name "+i+" : "
                                    + myResult.getString("Name") +"     year "+i+" : "
                                    + myResult.getInt("Year") + "     Author "+i+" : "
                                    + myResult.getString("Author") + "     ISBN "+i+" : "
                                    + myResult.getString("ISBN" ) + "\n" +
                                    "-------------------------------------------------------------------------------------------------------------------" + "\n");
                        }
                    }
                    x=0;
                }

                stageViewDetailsByISBN.setTitle("Book Details");
                viewDetailsByISBNPane.add(test6,0,0);
                viewDetailsByISBNPane.setAlignment(Pos.CENTER);
                viewDetailsByISBNPane.setPadding(new Insets(25,25,25,25));
                viewDetailsByISBNPane.setVgap(50);
                viewDetailsByISBNPane.setHgap(50);
                stageViewDetailsByISBN.show();

            }
            catch (Exception e) {System.out.println(e.toString());}
        });
        searchbooks.setOnAction(actionEvent -> {
            try{
                GridPane paneSearch = new GridPane();
                Stage stageSearchBook = new Stage();
                stageSearchBook.setTitle("Search Books");
                Scene searchScene = new Scene(paneSearch,500,350);
                stageSearchBook.setScene(searchScene);
                stageSearchBook.show();

                paneSearch.setPadding(new Insets(25,25,25,25));
                paneSearch.setHgap(20);
                paneSearch.setVgap(10);

                paneSearch.add(searchByNameCriteria, 0,0);
                paneSearch.add(searchByYearCriteria, 0,1);
                paneSearch.add(searchByAuthorCriteria, 0,2);
                paneSearch.add(nameCriteriaField, 1,0);
                paneSearch.add(yearCriteriaField, 1,1);
                paneSearch.add(authorCriteriaField, 1,2);
                paneSearch.add(searchNameCriteriaButton,2,0);
                paneSearch.add(searchYearCriteriaButton,2,1);
                paneSearch.add(searchAuthorCriteriaButton,2,2);
                paneSearch.add(closeButton,0,3);
            }
            catch (Exception e) {System.out.println(e.toString());}
        });
        searchNameCriteriaButton.setOnAction(actionEvent -> {

            try{
                Text test7 = new Text();
                test7.setFont(Font.font("Times New Roman", FontWeight.BOLD,FontPosture.ITALIC,20));
                test7.setStyle("-fx-font-size: 20px;");
                GridPane searchBookByNamePane = new GridPane();
                Scene searchBookByNameScene = new Scene(searchBookByNamePane, 750,350);
                Stage stageSearchBookByName = new Stage();
                stageSearchBookByName.setScene(searchBookByNameScene);

                Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost/bookinventory", "root", "12345");
                // Establish connection, hostname: local host, database name: bookinventory
                Statement myStatementSearchBookNames = myConnection.createStatement();

                String nameSearchCriteria = nameCriteriaField.getText();

                ResultSet myResultSetSearchBookNames =
                        myStatementSearchBookNames.executeQuery("SELECT * FROM bookinventory.tablebook WHERE Name = '"+nameSearchCriteria+"'");

                x = 0;
                int i = 0;
                String NameCriteria = "";
                while (myResultSetSearchBookNames.next()){

                    counter++;
                    x = counter;
                    i++;
                    NameCriteria += "     ID "+i+" : "+myResultSetSearchBookNames.getInt("ID") + "    Name "+i+" : "
                            + myResultSetSearchBookNames.getString("Name") +"     year "+i+" : "
                            + myResultSetSearchBookNames.getInt("Year") + "     Author "+i+" : "
                            + myResultSetSearchBookNames.getString("Author") + "     ISBN "+i+" : "
                            + myResultSetSearchBookNames.getString("ISBN" ) + "\n" +
                            "-------------------------------------------------------------------------------------------------------------------" + "\n";
                }
                if (NameCriteria!=""){test7.setText(NameCriteria);}
                else if (nameCriteriaField.getText().isBlank()){test7.setText("Book's Name should be inputted, Please try again");}
                else if(x==0){test7.setText("Book name is invalid");}
                counter = 0;
                myResultSetSearchBookNames.close();

                stageSearchBookByName.setTitle("Book Details");
                searchBookByNamePane.add(test7,0,0);
                searchBookByNamePane.setAlignment(Pos.CENTER);
                searchBookByNamePane.setPadding(new Insets(25,25,25,25));
                searchBookByNamePane.setVgap(50);
                searchBookByNamePane.setHgap(50);
                stageSearchBookByName.show();
            }
            catch (Exception e) {System.out.println(e.toString());}
        });
        searchAuthorCriteriaButton.setOnAction(actionEvent -> {
            try{

                Text test8 = new Text();
                test8.setFont(Font.font("Times New Roman", FontWeight.BOLD,FontPosture.ITALIC,20));
                test8.setStyle("-fx-font-size: 20px;");
                GridPane searchBookByAuthorPane = new GridPane();
                Scene searchBookByAuthorScene = new Scene(searchBookByAuthorPane, 750,350);
                Stage stageSearchBookByAuthor = new Stage();
                stageSearchBookByAuthor.setScene(searchBookByAuthorScene);

                Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost/bookinventory", "root", "12345");
                // Establish connection, hostname: local host, database name: bookinventory
                Statement myStatementSearchBookAuthor = myConnection.createStatement();

                String AuthorCriteria = authorCriteriaField.getText();
                ResultSet myResultSetSearchBookAuthor =
                        myStatementSearchBookAuthor.executeQuery("SELECT * FROM bookinventory.tablebook WHERE Author = '"+AuthorCriteria+"'");

                x = 0;
                int i = 0;
                String AuthorCriteriaString = "";

                while(myResultSetSearchBookAuthor.next()){

                    counter++;
                    x = counter;
                    i++;

                    AuthorCriteriaString+="     ID "+i+" : "+myResultSetSearchBookAuthor.getInt("ID") + "    Name "+i+" : "
                            + myResultSetSearchBookAuthor.getString("Name") +"     year "+i+" : "
                            + myResultSetSearchBookAuthor.getInt("Year") + "     Author "+i+" : "
                            + myResultSetSearchBookAuthor.getString("Author") + "     ISBN "+i+" : "
                            + myResultSetSearchBookAuthor.getString("ISBN" ) + "\n" +
                            "-------------------------------------------------------------------------------------------------------------------" + "\n";
                }
                if (AuthorCriteriaString!=""){test8.setText(AuthorCriteriaString);}
                else if (authorCriteriaField.getText().isBlank()){test8.setText("Book's author should be inputted, Please try again");}
                else if (x==0){test8.setText("Author name is invalid");}
                counter = 0;
                myResultSetSearchBookAuthor.close();

                stageSearchBookByAuthor.setTitle("Book Details");
                searchBookByAuthorPane.add(test8,0,0);
                searchBookByAuthorPane.setAlignment(Pos.CENTER);
                searchBookByAuthorPane.setPadding(new Insets(25,25,25,25));
                searchBookByAuthorPane.setVgap(50);
                searchBookByAuthorPane.setHgap(50);
                stageSearchBookByAuthor.show();

            }
            catch (Exception e) {System.out.println(e.toString());}
        });
        searchYearCriteriaButton.setOnAction(actionEvent -> {
            try{

                Text test9 = new Text();
                test9.setFont(Font.font("Times New Roman", FontWeight.BOLD,FontPosture.ITALIC,20));
                test9.setStyle("-fx-font-size: 20px;");
                GridPane searchBookByYearPane = new GridPane();
                Scene searchBookByYearScene = new Scene(searchBookByYearPane, 750,350);
                Stage stageSearchBookByYear = new Stage();
                stageSearchBookByYear.setScene(searchBookByYearScene);

                Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost/bookinventory", "root", "12345");
                // Establish connection, hostname: local host, database name: bookinventory
                Statement myStatementSearchBookYears = myConnection.createStatement();

                int yearSearchCriteria = Integer.parseInt(yearCriteriaField.getText());

                ResultSet myResultSetSearchBookYears =
                        myStatementSearchBookYears.executeQuery("SELECT * FROM bookinventory.tablebook WHERE Year = '"+yearSearchCriteria+"'");

                x = 0;
                int i = 0;
                String YearCriteria = "";
                while(myResultSetSearchBookYears.next()){

                    counter++;
                    x = counter;
                    i++;

                    YearCriteria +="     ID "+i+" : "+myResultSetSearchBookYears.getInt("ID") + "    Name "+i+" : "
                            + myResultSetSearchBookYears.getString("Name") +"     year "+i+" : "
                            + myResultSetSearchBookYears.getInt("Year") + "     Author "+i+" : "
                            + myResultSetSearchBookYears.getString("Author") + "     ISBN "+i+" : "
                            + myResultSetSearchBookYears.getString("ISBN" ) + "\n" +
                            "-------------------------------------------------------------------------------------------------------------------" + "\n";
                }
                if (YearCriteria!=null) {test9.setText(YearCriteria);}
                else if (yearCriteriaField.getText().isBlank()){test9.setText("Book's year should be inputted, Please");}
                else if(x==0){
                    test9.setText("Book year is invalid");
                }
                counter = 0;
                myResultSetSearchBookYears.close();

                stageSearchBookByYear.setTitle("Book Details");
                searchBookByYearPane.add(test9,0,0);
                searchBookByYearPane.setAlignment(Pos.CENTER);
                searchBookByYearPane.setPadding(new Insets(25,25,25,25));
                searchBookByYearPane.setVgap(50);
                searchBookByYearPane.setHgap(50);
                stageSearchBookByYear.show();
            }
            catch (Exception e) {System.out.println(e.toString());}

        });
        editbooks.setOnAction(actionEvent -> {
            try {
                GridPane paneEditBook = new GridPane();
                Stage stageEditBook = new Stage();
                stageEditBook.setTitle("Edit Book");
                Scene editBook = new Scene(paneEditBook,750,350);
                stageEditBook.setScene(editBook);


                paneEditBook.setPadding(new Insets(25,25,25,25));
                paneEditBook.setHgap(20);
                paneEditBook.setVgap(10);

                paneEditBook.add(selectEditBookName, 0,0);
                paneEditBook.add(selectEditBookISBN, 0,1);
                paneEditBook.add(selectEditBookNameField, 1,0);
                paneEditBook.add(selectEditBookISBNField, 1,1);
                paneEditBook.add(editBooksSelectName, 2,0);
                paneEditBook.add(editBooksSelectISBN,2,1);
                paneEditBook.add(closeButton,0,2);
                stageEditBook.show();
            }
            catch (Exception e) {System.out.println(e.toString());}
        });
        editBooksSelectName.setOnAction(actionEvent -> {

            try{

                Text test10 = new Text();
                test10.setFont(Font.font("Times New Roman", FontWeight.BOLD,FontPosture.ITALIC,20));
                test10.setStyle("-fx-font-size: 20px;");
                GridPane editBookByNamePane = new GridPane();
                Scene editBookByNameScene = new Scene(editBookByNamePane, 750,350);
                Stage stageEditBookByName = new Stage();
                stageEditBookByName.setScene(editBookByNameScene);

                Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost/bookinventory", "root", "12345");
                // Establish connection, hostname: local host, database name: bookinventory
                Statement myStatementEditBook = myConnection.createStatement();

                String nameEditBook = selectEditBookNameField.getText();

                String editq1 = "SELECT * from tablebook WHERE Name = '"+nameEditBook+"'";

                ResultSet myResultSetEditBook = myStatementEditBook.executeQuery(editq1);

                x=0;
                while (myResultSetEditBook.next())
                {
                    counter++;
                    x = counter;
                }
                counter = 0;
                myResultSetEditBook.close();

                if (selectEditBookNameField.getText().isBlank()){test10.setText("Book's name is not inputted");}
                else if (x >= 2 ){test10.setText("Inputted name is assigned to "+x+" other books, please specify by ISBN");}
                else if (x == 0) {test10.setText("Book name not found, please try again");}
                else{
                    Connection myConnect= DriverManager.getConnection("jdbc:mysql://localhost/bookinventory", "root", "12345");
                    Statement myStatement = myConnect.createStatement();

                    ResultSet myResult=myStatementEditBook.executeQuery("SELECT * from tablebook");

                    GridPane paneEditBookByName = new GridPane();
                    stageEditBookByName.setTitle("Edit Book By Name");
                    Scene editBookByName = new Scene(paneEditBookByName, 750,350);
                    stageEditBookByName.setScene(editBookByName);
                    stageEditBookByName.show();

                    x =0;

                    while(myResult.next()) {

                        if (myResult.getString("Name").equals(nameEditBook)) {
                            editBook = new Text(myResult.getInt("ID") + " " + myResult.getString("Name") + " " +
                                    myResult.getInt("Year") + " " + myResult.getString("Author") + " " + myResult.getString("ISBN"));
                        }
                    }
                    myResult.close();

                    editBook.setFont(Font.font("Times New Roman", FontWeight.BOLD,FontPosture.ITALIC,20));
                    editBook.setStyle("-fx-font-size: 20px;");
                    paneEditBookByName.add(editBook,1,0);
                    paneEditBookByName.add(updateName,0,1);
                    paneEditBookByName.add(updateYear,0,2);
                    paneEditBookByName.add(updateAuthor,0,3);
                    paneEditBookByName.add(updateISBN,0,4);
                    paneEditBookByName.add(updateNameField,1,1);
                    paneEditBookByName.add(updateYearField,1,2);
                    paneEditBookByName.add(updateAuthorField,1,3);
                    paneEditBookByName.add(updateISBNField,1,4);
                    paneEditBookByName.add(updateNameByName,2,1);
                    paneEditBookByName.add(updateYearByName,2,2);
                    paneEditBookByName.add(updateAuthorByName,2,3);
                    paneEditBookByName.add(updateISBNByName,2,4);
                    paneEditBookByName.add(closeButton,0,5);

                    paneEditBookByName.setPadding(new Insets(25,25,25,25));
                    paneEditBookByName.setHgap(25);
                    paneEditBookByName.setVgap(25);

                }

                stageEditBookByName.setTitle("Book Updating");
                editBookByNamePane.add(test10,0,0);
                editBookByNamePane.setAlignment(Pos.CENTER);
                editBookByNamePane.setPadding(new Insets(25,25,25,25));
                editBookByNamePane.setVgap(50);
                editBookByNamePane.setHgap(50);
                stageEditBookByName.show();

            }
            catch (Exception e) {System.out.println(e.toString());}
        });

        updateNameByName.setOnAction(actionEvent -> {

            try{

                Text test11 = new Text();
                test11.setFont(Font.font("Times New Roman", FontWeight.BOLD,FontPosture.ITALIC,20));
                test11.setStyle("-fx-font-size: 20px;");
                GridPane updateNameByNamePane = new GridPane();
                Scene updateNameByNameScene = new Scene(updateNameByNamePane, 750,350);
                Stage stageNameByByName = new Stage();
                stageNameByByName.setScene(updateNameByNameScene);

                Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost/bookinventory", "root", "12345");
                // Establish connection, hostname: local host, database name: bookinventory
                Statement myStatementUpdateNameName = myConnection.createStatement();

                String updateBookNameByName = updateNameField.getText();

                if (updateNameField.getText().isBlank()){test11.setText("Book's name to be updated should be inputted");}

                else {
                    String updateq1 = "UPDATE tablebook set Name = '" + updateBookNameByName + "' WHERE Name = '" + selectEditBookNameField.getText() + "'";

                    myStatementUpdateNameName.executeUpdate(updateq1);

                    test11.setText("Name is updated successfully");

                }


                stageNameByByName.setTitle("Book Updating");
                updateNameByNamePane.add(test11,0,0);
                updateNameByNamePane.setAlignment(Pos.CENTER);
                updateNameByNamePane.setPadding(new Insets(25,25,25,25));
                updateNameByNamePane.setVgap(50);
                updateNameByNamePane.setHgap(50);
                stageNameByByName.show();

            }
            catch (Exception e) {System.out.println(e.toString());}

        });
        updateYearByName.setOnAction(actionEvent -> {
            try{

                Text test12 = new Text();
                test12.setFont(Font.font("Times New Roman", FontWeight.BOLD,FontPosture.ITALIC,20));
                test12.setStyle("-fx-font-size: 20px;");
                GridPane updateYearByNamePane = new GridPane();
                Scene updateNameByNameScene = new Scene(updateYearByNamePane, 750,350);
                Stage stageYearByName = new Stage();
                stageYearByName.setScene(updateNameByNameScene);

                Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost/bookinventory", "root", "12345");
                // Establish connection, hostname: local host, database name: bookinventory
                Statement myStatementUpdateYearName = myConnection.createStatement();

                int updateBookYearByName = Integer.parseInt(updateYearField.getText());

                if (updateYearField.getText().isBlank()){test12.setText("Book's year to be updated should be inputted");}

                else {
                    String updateq1 = "UPDATE tablebook set Year = '" + updateBookYearByName + "' WHERE Name = '" + selectEditBookNameField.getText() + "'";

                    myStatementUpdateYearName.executeUpdate(updateq1);

                    test12.setText("Year is updated successfully");

                }

                stageYearByName.setTitle("Book Updating");
                updateYearByNamePane.add(test12,0,0);
                updateYearByNamePane.setAlignment(Pos.CENTER);
                updateYearByNamePane.setPadding(new Insets(25,25,25,25));
                updateYearByNamePane.setVgap(50);
                updateYearByNamePane.setHgap(50);
                stageYearByName.show();

            }
            catch (Exception e) {System.out.println(e.toString());}

        });
        updateAuthorByName.setOnAction(actionEvent -> {
            try{
                Text test13 = new Text();
                test13.setFont(Font.font("Times New Roman", FontWeight.BOLD,FontPosture.ITALIC,20));
                test13.setStyle("-fx-font-size: 20px;");
                GridPane updateAuthorByNamePane = new GridPane();
                Scene updateAuthorByNameScene = new Scene(updateAuthorByNamePane, 750,350);
                Stage stageAuthorByName = new Stage();
                stageAuthorByName.setScene(updateAuthorByNameScene);

                Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost/bookinventory", "root", "12345");
                // Establish connection, hostname: local host, database name: bookinventory
                Statement myStatementUpdateAuthorName = myConnection.createStatement();

                String updateBookAuthorByName = updateAuthorField.getText();

                if (updateAuthorField.getText().isBlank()){test13.setText("Book's author to be updated should be inputted");}

                else {

                    String updateq1 = "UPDATE tablebook set Author = '" + updateBookAuthorByName + "' WHERE Name = '" + selectEditBookNameField.getText() + "'";

                    myStatementUpdateAuthorName.executeUpdate(updateq1);

                    test13.setText("Author is updated successfully");

                }

                stageAuthorByName.setTitle("Book Updating");
                updateAuthorByNamePane.add(test13,0,0);
                updateAuthorByNamePane.setAlignment(Pos.CENTER);
                updateAuthorByNamePane.setPadding(new Insets(25,25,25,25));
                updateAuthorByNamePane.setVgap(50);
                updateAuthorByNamePane.setHgap(50);
                stageAuthorByName.show();

            }
            catch (Exception e) {System.out.println(e.toString());}
        });

        editBooksSelectISBN.setOnAction(actionEvent -> {

            Text test14 = new Text();

            GridPane editBookByISBNPane = new GridPane();
            Scene editBookByISBNScene = new Scene(editBookByISBNPane, 750,350);
            Stage stageEditBookByISBN = new Stage();
            stageEditBookByISBN.setScene(editBookByISBNScene);

            try {
                Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost/bookinventory", "root", "12345");
                // Establish connection, hostname: local host, database name: bookinventory
                Statement myStatementEditBook = myConnection.createStatement();

                String ISBNEditBook = selectEditBookISBNField.getText();

                ResultSet myresult = myStatementEditBook.executeQuery("Select * from tablebook");

                x = 0;
                counter = 0;

                while (myresult.next()) {
                    if (myresult.getString("ISBN").equals(selectEditBookISBNField.getText())) {counter++;}
                }
                System.out.println(counter);
                myresult.close();
            }
            catch (Exception e) {System.out.println(e.toString());}
            GridPane paneEditBookByISBN = new GridPane();
            Scene editBookByISBN = new Scene(paneEditBookByISBN,750,350);
            stageEditBookByISBN.setScene(editBookByISBN);
            if (counter != 0) {
                stageEditBookByISBN.setTitle("Book Updating");
                editBookByISBNPane.setAlignment(Pos.CENTER);
                editBookByISBNPane.setPadding(new Insets(25,25,25,25));
                editBookByISBNPane.setVgap(50);
                editBookByISBNPane.setHgap(50);
                try {

                    Connection myConnect = DriverManager.getConnection("jdbc:mysql://localhost/bookinventory", "root", "12345");
                    Statement myStatement = myConnect.createStatement();
                    ResultSet myResult = myStatement.executeQuery("SELECT * from tablebook");
                    String ISBNEditBook = selectEditBookISBNField.getText();

                    stageEditBookByISBN.setTitle("Edit Book By ISBN");

                    while (myResult.next()) {

                        if (myResult.getString("ISBN").equals(ISBNEditBook)) {
                            //System.out.println(myResult.getString("Name"));
                            test14.setText(myResult.getInt("ID") + " " + myResult.getString("Name") + " " +
                                    myResult.getInt("Year") + " " + myResult.getString("Author") + " " + myResult.getString("ISBN"));
                        }
                    }
                    myResult.close();
                    test14.setFont(Font.font("Times New Roman", FontWeight.BOLD,FontPosture.ITALIC,20));
                    test14.setStyle("-fx-font-size: 20px;");
                    paneEditBookByISBN.add(test14,1,0);
                    paneEditBookByISBN.add(updateName, 0, 1);
                    paneEditBookByISBN.add(updateYear, 0, 2);
                    paneEditBookByISBN.add(updateAuthor, 0, 3);
                    paneEditBookByISBN.add(updateISBN, 0, 4);
                    paneEditBookByISBN.add(updateNameField, 1, 1);
                    paneEditBookByISBN.add(updateYearField, 1, 2);
                    paneEditBookByISBN.add(updateAuthorField, 1, 3);
                    paneEditBookByISBN.add(updateISBNField, 1, 4);
                    paneEditBookByISBN.add(updateNameByISBN, 2, 1);
                    paneEditBookByISBN.add(updateYearByISBN, 2, 2);
                    paneEditBookByISBN.add(updateAuthorByISBN, 2, 3);
                    paneEditBookByISBN.add(updateISBNByISBN, 2, 4);
                    paneEditBookByISBN.add(closeButton,0,5);

                    stageEditBookByISBN.setTitle("Book Updating");
                    paneEditBookByISBN.setAlignment(Pos.CENTER);
                    paneEditBookByISBN.setPadding(new Insets(25,25,25,25));
                    paneEditBookByISBN.setVgap(25);
                    paneEditBookByISBN.setHgap(25);

                    stageEditBookByISBN.setTitle("Book Updating");
                    editBookByISBNPane.setAlignment(Pos.CENTER);
                    editBookByISBNPane.setPadding(new Insets(25,25,25,25));
                    editBookByISBNPane.setVgap(50);
                    editBookByISBNPane.setHgap(50);

                }
                catch (Exception e) {System.out.println(e.toString());}
            }
            else if(selectEditBookISBNField.getText().isBlank()){
                paneEditBookByISBN.setAlignment(Pos.CENTER);
                test14.setFont(Font.font("Times New Roman", FontWeight.BOLD,FontPosture.ITALIC,20));
                test14.setStyle("-fx-font-size: 20px;");
                stageEditBookByISBN.setTitle("Error");
                paneEditBookByISBN.add(test14,0,0);
                test14.setText("Book's ISBN is not inputted");}
            else{
                paneEditBookByISBN.setAlignment(Pos.CENTER);
                test14.setFont(Font.font("Times New Roman", FontWeight.BOLD,FontPosture.ITALIC,20));
                test14.setStyle("-fx-font-size: 20px;");
                stageEditBookByISBN.setTitle("Error");
                paneEditBookByISBN.add(test14,0,0);
                test14.setText("ISBN is invalid");}
            //editBookByISBNPane.add(test14,0,0);
            stageEditBookByISBN.show();
        });
        updateNameByISBN.setOnAction(actionEvent -> {
            try{

                Text test15 = new Text();
                test15.setFont(Font.font("Times New Roman", FontWeight.BOLD,FontPosture.ITALIC,20));
                test15.setStyle("-fx-font-size: 20px;");
                GridPane updateNameByISBNPane = new GridPane();
                Scene updateNameByISBNScene = new Scene(updateNameByISBNPane, 750,350);
                Stage stageNameByISBN = new Stage();
                stageNameByISBN.setScene(updateNameByISBNScene);

                Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost/bookinventory", "root", "12345");
                // Establish connection, hostname: local host, database name: bookinventory
                Statement myStatementUpdateNameISBN = myConnection.createStatement();

                String updateBookNameByISBN = updateNameField.getText();

                if (updateNameField.getText().isBlank()){test15.setText("Book's name to be updated should be updated");}

                else {
                    String updateq1 = "UPDATE tablebook set Name = '" + updateBookNameByISBN + "' WHERE ISBN = '" + selectEditBookISBNField.getText() + "'";

                    myStatementUpdateNameISBN.executeUpdate(updateq1);
                    test15.setText("Successful");
                }

                stageNameByISBN.setTitle("Book Updating");
                updateNameByISBNPane.add(test15,0,0);
                updateNameByISBNPane.setAlignment(Pos.CENTER);
                updateNameByISBNPane.setPadding(new Insets(25,25,25,25));
                updateNameByISBNPane.setVgap(50);
                updateNameByISBNPane.setHgap(50);
                stageNameByISBN.show();

            }
            catch (Exception e) {System.out.println(e.toString());}
        });
        updateAuthorByISBN.setOnAction(actionEvent -> {
            try{
                Text test16 = new Text();
                test16.setFont(Font.font("Times New Roman", FontWeight.BOLD,FontPosture.ITALIC,20));
                test16.setStyle("-fx-font-size: 20px;");
                GridPane updateAuthorByISBNPane = new GridPane();
                Scene updateAuthorByISBNScene = new Scene(updateAuthorByISBNPane, 750,350);
                Stage stageAuthorByISBN = new Stage();
                stageAuthorByISBN.setScene(updateAuthorByISBNScene);

                Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost/bookinventory", "root", "12345");
                // Establish connection, hostname: local host, database name: bookinventory
                Statement myStatementUpdateAuthorISBN = myConnection.createStatement();

                String updateBookAuthorByISBN = updateAuthorField.getText();

                if (updateAuthorField.getText().isBlank()){test16.setText("Book's author to be updated should be inputted");}

                else {
                    String updateq1 = "UPDATE tablebook set Author = '" + updateBookAuthorByISBN + "' WHERE ISBN = '" + selectEditBookISBNField.getText() + "'";

                    myStatementUpdateAuthorISBN.executeUpdate(updateq1);
                    test16.setText("Successful");
                }

                stageAuthorByISBN.setTitle("Book Updating");
                updateAuthorByISBNPane.add(test16,0,0);
                updateAuthorByISBNPane.setAlignment(Pos.CENTER);
                updateAuthorByISBNPane.setPadding(new Insets(25,25,25,25));
                updateAuthorByISBNPane.setVgap(50);
                updateAuthorByISBNPane.setHgap(50);
                stageAuthorByISBN.show();

            }
            catch (Exception e) {System.out.println(e.toString());}

        });
        updateYearByISBN.setOnAction(actionEvent -> {
            try{
                Text test17 = new Text();
                test17.setFont(Font.font("Times New Roman", FontWeight.BOLD,FontPosture.ITALIC,20));
                test17.setStyle("-fx-font-size: 20px;");
                GridPane updateYearByISBNPane = new GridPane();
                Scene updateYearByISBNScene = new Scene(updateYearByISBNPane, 750,350);
                Stage stageYearByISBN = new Stage();
                stageYearByISBN.setScene(updateYearByISBNScene);

                Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost/bookinventory", "root", "12345");
                // Establish connection, hostname: local host, database name: bookinventory
                Statement myStatementUpdateYearISBN = myConnection.createStatement();

                String updateBookYearByISBN = updateYearField.getText();

                if(updateYearField.getText().isBlank()){test17.setText("Book's year to be updated should be inputted");}

                else {
                    String updateq1 = "UPDATE tablebook set Year = '" + updateBookYearByISBN + "' WHERE ISBN = '" + selectEditBookISBNField.getText() + "'";

                    myStatementUpdateYearISBN.executeUpdate(updateq1);
                    test17.setText("Successful");
                }
                stageYearByISBN.setTitle("Book Updating");
                updateYearByISBNPane.add(test17,0,0);
                updateYearByISBNPane.setAlignment(Pos.CENTER);
                updateYearByISBNPane.setPadding(new Insets(25,25,25,25));
                updateYearByISBNPane.setVgap(50);
                updateYearByISBNPane.setHgap(50);
                stageYearByISBN.show();

            }
            catch (Exception e) {System.out.println(e.toString());}
        });
        updateISBNByName.setOnAction(actionEvent -> {

            Text test18 = new Text();
            test18.setFont(Font.font("Times New Roman", FontWeight.BOLD,FontPosture.ITALIC,20));
            test18.setStyle("-fx-font-size: 20px;");
            GridPane updateISBNByNamePane = new GridPane();
            Scene updateISBNByNameScene = new Scene(updateISBNByNamePane, 750,350);
            Stage stageISBNByName = new Stage();
            stageISBNByName.setScene(updateISBNByNameScene);

            try{
                Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost/bookinventory", "root", "12345");
                // Establish connection, hostname: local host, database name: bookinventory
                Statement myStatementUpdateISBNName = myConnection.createStatement();
                ResultSet myresult=myStatementUpdateISBNName.executeQuery("Select * from tablebook");
                counter=0;
                while (myresult.next()){
                    if(myresult.getString("ISBN").equals(updateISBNField.getText())){
                        counter++;
                    }
                }
                myresult.close();
            }
            catch(Exception e){System.out.println(e.toString());}

            if(counter==0){
                try {
                    Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost/bookinventory", "root", "12345");
                    // Establish connection, hostname: local host, database name: bookinventory
                    Statement myStatementUpdateISBNName = myConnection.createStatement();

                    String updateBookISBNByISBN = updateISBNField.getText();

                    String updateq1 = "UPDATE tablebook set ISBN = '"+updateBookISBNByISBN+"' WHERE Name = '"+selectEditBookNameField.getText()+"'";

                    myStatementUpdateISBNName.executeUpdate(updateq1);
                    test18.setText("ISBN is updated successfully");

                    stageISBNByName.setTitle("Book Updating");
                    updateISBNByNamePane.add(test18,0,0);
                    updateISBNByNamePane.setAlignment(Pos.CENTER);
                    updateISBNByNamePane.setPadding(new Insets(25,25,25,25));
                    updateISBNByNamePane.setVgap(50);
                    updateISBNByNamePane.setHgap(50);
                    stageISBNByName.show();
                }
                catch (Exception e){System.out.println(e.toString());
                }
            }
            else if (updateISBNField.getText().isBlank()){System.out.println("Book's ISBN to be updated should inputted");}
            else {System.out.println("ISBN is assigned to another book");}
        });
        updateISBNByISBN.setOnAction(actionEvent -> {

            Text test19 = new Text();
            test19.setFont(Font.font("Times New Roman", FontWeight.BOLD,FontPosture.ITALIC,20));
            test19.setStyle("-fx-font-size: 20px;");
            GridPane updateISBNByISBNPane = new GridPane();
            Scene updateISBNByISBNScene = new Scene(updateISBNByISBNPane, 750,350);
            Stage stageISBNByISBN = new Stage();
            stageISBNByISBN.setScene(updateISBNByISBNScene);

            try{
                Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost/bookinventory", "root", "12345");
                // Establish connection, hostname: local host, database name: bookinventory
                Statement myStatementUpdateISBNISBN = myConnection.createStatement();
                ResultSet myresult=myStatementUpdateISBNISBN.executeQuery("Select * from tablebook");
                counter=0;
                while (myresult.next()){
                    if(myresult.getString("ISBN").equals(updateISBNField.getText())){
                        counter++;
                    }
                }
                myresult.close();
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
            if(counter==0){
                try {
                    Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost/bookinventory", "root", "12345");
                    // Establish connection, hostname: local host, database name: bookinventory
                    Statement myStatementUpdateISBNISBN = myConnection.createStatement();

                    String updateBookISBNByISBN = updateISBNField.getText();

                    String updateq1 = "UPDATE tablebook set ISBN = '"+updateBookISBNByISBN+"' WHERE ISBN = '"+selectEditBookISBNField.getText()+"'";

                    myStatementUpdateISBNISBN.executeUpdate(updateq1);
                    test19.setText("Successful");

                    stageISBNByISBN.setTitle("Book Updating");
                    updateISBNByISBNPane.add(test19,0,0);
                    updateISBNByISBNPane.setAlignment(Pos.CENTER);
                    updateISBNByISBNPane.setPadding(new Insets(25,25,25,25));
                    updateISBNByISBNPane.setVgap(50);
                    updateISBNByISBNPane.setHgap(50);
                    stageISBNByISBN.show();
                }
                catch (Exception e){System.out.println(e.toString());
                }
            }
            else if (updateISBNField.getText().isBlank()){test19.setText("Book's ISBN to be updated should be inputted");}
            else {test19.setText("ISBN is taken");}
        });
        closeButton.setCursor(Cursor.HAND);
        closeButton.setOnAction(event -> {
            Stage currentStage = (Stage) closeButton.getScene().getWindow();
            currentStage.close();
        });
        pane.setPadding(new Insets(10,75,75,50));
        pane.setHgap(5);
        pane.setVgap(5);
        text.setX(20);
        pane.add(text,0,0);
        pane.add(viewbooks,0,1);
        pane.add(addbooks,0,2);
        pane.add(deletebooks,0,3);
        pane.add(viewbookdetails,0,4);
        pane.add(searchbooks,0,5);
        pane.add(editbooks,0,6);
        pane.add(closeButton,0,7);
        Scene scene = new Scene(pane,350, 250);
        stage.setTitle("Book Inventory");
        stage.setScene(scene);
        stage.setMinHeight(150);
        stage.setMinWidth(150);
        stage.show();
    }
}