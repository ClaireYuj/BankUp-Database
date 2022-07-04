import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Scanner;


public class Main {

	DbUser myDbUser = null;

	private void createDB(String database) {

		myDbUser = new DbUser(database);
		List<String> tables =new ArrayList<String>();
		tables=myDbUser.getTableName();
		System.out.println();
		System.out.print("The database " +database+" is backuping");
		try{
			// create tables and insert data
			for(int i=0; i<tables.size();i++) {
				if(tables.get(i).split("-")[0].equals("TABLE")){
					myDbUser.createTable(tables.get(i).split("-")[1],"TABLE");
					//System.out.println("Tables"+tables.get(i).split("-")[1] +"established sucessfuly");
					myDbUser.insertData(tables.get(i).split("-")[1]);

					}
				System.out.print("...");

				}
			System.out.println();
			System.out.println();
			System.out.println("The databases is backuped sucessfully, the backuped database "+database+" and 'output.sql' files will shown after you choose '0' to exit");
			myDbUser.fileWriter.close();
			}
		catch (Exception e){
			e.printStackTrace();
		}

} // end of method createDB



	private void closeDB(){
		myDbUser.close();
	}
	public static void main(String [ ] args) {

		Main myMain = new Main();
		//String database=args[0];
		String database="LSH.db";
		myMain.createDB(database);

		myMain.closeDB();
	} // end of method "main"

	} // end of class "Main"
