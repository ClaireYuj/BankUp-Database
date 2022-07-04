// STUBBED FILE
import com.sun.deploy.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.*;

// this is the class through which all Database calls go
public class DbUser extends DbBasic {

	private ResultSet rs = null;
	static private final int STR_SIZE = 25;
	File outputFile;
	Byte[] sourceByte;
	FileOutputStream outputStream;
	FileWriter fileWriter;
	List<String> primaryKey;
	List<String> primaryType;
	List<String> primaryIndexCol;
	List<String> column;
	List<ResultSet>  dataSet =  new ArrayList<>();
	String indexList = "";
	//Connection conn;

	/*
	 * Creates a connection to the named database
	 */
	DbUser ( String dbName ) {
		super( dbName );
		outputFile= new File("output.sql");
		try{
			fileWriter=new FileWriter(outputFile);
		}catch (Exception e){
			e.printStackTrace();
		}
		if (!outputFile.exists()) {
			try {
				outputFile.createNewFile();
				fileWriter=new FileWriter(outputFile);
			} catch (IOException e) {
				System.out.println("fail to create file");
				e.printStackTrace();
			}
		}

	}

	/*
	 * get the table name
	 */
	public List<String> getTableName( ){
		// get the table name in a database
		int index=0;
		List<String> tables=  new ArrayList<String>();
		try {
		// get the metadata from database
			DatabaseMetaData dbmd = con.getMetaData();
		// the type is table
			String[] types = {"TABLE"};
			ResultSet rs = dbmd.getTables(null, null, "%", types);
			ResultSetMetaData rmd = rs.getMetaData();
			while(rs.next()){
				// get the table name and tye--table or view
				tables.add(rs.getString("TABLE_TYPE")+"-"+rs.getString("TABLE_NAME"));
			}
		}
		catch (SQLException e){
		e.printStackTrace();
		}
		return tables;
	}

	/**
	 * get the column name andthe type
	 * @param tableName
	 * @return
	 */
	public List<String> getColumnName(String tableName) {
		//Map<String,String> columns = null;

		List<String> columns=new ArrayList<String>();
		try {
			//columns = new HashMap<>();
			DatabaseMetaData dbmd = con.getMetaData();
			ResultSet rs = dbmd.getColumns(null, null, tableName, "%");
			while (rs.next()) {
				String columnName = rs.getString("COLUMN_NAME");// column name
				String dataTypeName = rs.getString("TYPE_NAME");// data type
				int nullAble = rs.getInt("NULLABLE");
				String field = columnName+" "+dataTypeName+"//-"+nullAble;
				columns.add(field);
				//System.out.println(field);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return columns;
	}

	/**
	 * get primary key from meta data
	 * @param tableName
	 * @return
	 */
	public List<String> getPrimaryKey (String tableName) {

		List<String> primaryKey = new ArrayList<String>();

		try {
			DatabaseMetaData dbmd = con.getMetaData();
			ResultSet rs = dbmd.getPrimaryKeys(null, null, tableName);
			while (rs.next()) {
				String columnName = rs.getString("COLUMN_NAME");// column name
				short keySeq = rs.getShort("KEY_SEQ");// sequence
				String pkName = rs.getString("PK_NAME"); // primary name
			//	System.out.println(columnName + "-" + keySeq + "-" + pkName);
				primaryKey.add(columnName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return primaryKey;
	}

	/**
	 * get the type of primary key
	 * @param tableName
	 * @return
	 */
	public List<String> getPrimaryType (String tableName){

		List<String> primaryType = new ArrayList<String>();
		this.primaryKey=getPrimaryKey(tableName);
		try {
			DatabaseMetaData dbmd = con.getMetaData();


			ResultSet rs= dbmd.getColumns(null, null, tableName, "%");
			while (rs.next()) {
				for(int i=0;i<this.primaryKey.size();i++){
					if(rs.getString("COLUMN_NAME").equals(this.primaryKey.get(i))){
						{
							String type = rs.getString("TYPE_NAME");
							primaryType.add(type);
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		primaryKey.clear();
		return primaryType;
	}

	/**
	 * get the set of foreign key and its primary key
	 * @param tableName
	 * @return
	 */
	public List<String> getForeignKey(String tableName ) {


		List<String> foreignkey = new ArrayList<String>();
		try {
			DatabaseMetaData dbmd = con.getMetaData();
			ResultSet rs = dbmd.getImportedKeys(null, null, tableName);
			while (rs.next()) {
				String pkTableName = rs.getString("PKTABLE_NAME");// get the primary table name
				String pkColumnName = rs.getString("PKCOLUMN_NAME");// get the primary column name

				String fkColumnName = rs.getString("FKCOLUMN_NAME"); // get the foreign table name
				foreignkey.add(fkColumnName +"//-"+ pkTableName + "//:" + pkColumnName);
				//System.out.println(fkColumnName +"-"+ pkTableName + ":" + pkColumnName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return foreignkey;
	}

	public String addForeignKey(String tablename) {

		List<String> foreignKey = getForeignKey(tablename);
		String updateForeignKey="";
		if(foreignKey.size()>0){
			updateForeignKey+=",";
			for (int i = 0; i < foreignKey.size(); i++) {
				updateForeignKey += "foreign key(" + foreignKey.get(i).split("//-")[0] + ") references `"
						+ foreignKey.get(i).split("-")[1].split("//:")[0] + "`("
						+ foreignKey.get(i).split("-")[1].split("//:")[1] + ")";
				if(i<foreignKey.size()-1) updateForeignKey+=", ";

			}
		}
		return updateForeignKey;
	}

	public String createTable(String tableName,String type){

		List<String> col= getColumnName(tableName);
		List<String> primarykey = getPrimaryKey(tableName);
		//List<String> foreignKey = getForeignKey(tableName);
		String createSql="";
		boolean isPRi=false;
		if(type.equals("TABLE")) {
			createSql = "Drop table if exists `" + tableName + "`;\n";
			createSql += "create TABLE `" + tableName + "` (";
		}
		else if(type.equals("VIEW")){
			createSql = "Drop view if exists `" + tableName + "`;\n";
			createSql += "create VIEW " + tableName + " (";
		}
		/**
		 * create the sql for create primary key and some other attibutes
		 */
		for(int i=0;i<col.size();i++){
			// add not null
				createSql+=col.get(i).split("//-")[0];
				if(col.get(i).split("//-")[1].equals("0")){
					createSql+=" "+"not null";
				}// end of determine is null?
			isPRi=false;
			if(i+1<col.size()) createSql+=",";
		}// end of judge col
		createSql+=", primary key(";
		for(int i=0;i<primarykey.size();i++){
			createSql+=primarykey.get(i);
			if(i+1<primarykey.size()) createSql+=",";
		}
		createSql+=")";

		/**
		 * get sql create the foregin key
		 */
		createSql+=addForeignKey(tableName);
		createSql+=");\n";
		// create table
		try{
			Statement stmt = newcon.createStatement();
			stmt.executeUpdate(createSql);


		}catch (SQLException e){
			e.printStackTrace();
		}
		/*
		output the createsql String
		 */
	try{
		fileWriter.append(createSql);
		fileWriter.flush();
	}catch (Exception e){
		e.printStackTrace();
	}

	return createSql;
	}



	/**
	 * get the primary value of a table
	 * @param tablename
	 * @return
	 */
	public List<String> getPrimaryColumnAsIndex(String tablename){

		List<String> indexCol = new ArrayList<String>();
		//List<String> primaryElement =  new ArrayList<>();
		String primaryElement="";
		this.primaryKey=getPrimaryKey(tablename);//change
		try{
			String primaryKey="";
			//for(int i=0;i<getPrimaryKey(tablename).size();i++){
				for(int i=0;i<this.primaryKey.size();i++){
				primaryKey+= getPrimaryKey(tablename).get(i);
				if(i<getPrimaryKey(tablename).size()-1)
					primaryKey+=",";
			}
			String sql=" select "+primaryKey+" from `"+tablename+"`";
			Statement stmt = con.createStatement();
			rs=stmt.executeQuery(sql);
			while ((rs.next())){
				primaryElement =  "";
				for(int i=0;i<getPrimaryKey(tablename).size();i++){
					primaryElement+=rs.getString(primaryKey.split(",")[i]);
					if(i<getPrimaryKey(tablename).size()-1)
						primaryElement+="//,";
				}
				indexCol.add(primaryElement);

			}
		}catch (SQLException e){
			e.printStackTrace();
		}

		return indexCol;
	}

	/**
	 *  insert data into table
	 * @param tablename
	 * @return
	 */
	public void insertData(String tablename){

		List<String> insertSqlSets = new ArrayList<>();
		int primaryIndexNum=getPrimaryColumnAsIndex(tablename).size();
		int primaryKeyNum=getPrimaryKey(tablename).size();
		int colNum = getColumnName(tablename).size();
		this.primaryKey=getPrimaryKey(tablename);
		this.primaryType=getPrimaryType(tablename);
		this.primaryIndexCol=getPrimaryColumnAsIndex(tablename);
		this.column=getColumnName(tablename);

		try{
			// try to get the raw data from origin table
			Statement stmt = con.createStatement();
			// get the size of table
			//for(int i=0;i<getPrimaryColumnAsIndex(tablename).size();i++){
			for(int i=0;i<primaryIndexNum;i++){
				// initial the insert statement
				String insertSql="";
				insertSql+="Insert into `"+tablename+"` values(";
				String sql = "select * from `"+tablename+"` where ";

				// get the primary key as index
				for(int k=0;k<primaryKeyNum;k++){
					sql+=this.primaryKey.get(k)+ " = ";
				// if the datatype is char
					//System.out.println(getPrimaryKey(tablename)+":"+getPrimaryType(tablename));
					if(this.primaryType.get(k).contains("CHAR")){
						//sql=sql+"'"+getPrimaryColumnAsIndex(tablename).get(i).split("//,")[k]+"'";
						sql=sql+"'"+this.primaryIndexCol.get(i).split("//,")[k]+"'";
					}
					else{
						//sql+=getPrimaryColumnAsIndex(tablename).get(i).split("//,")[k];
						sql+=this.primaryIndexCol.get(i).split("//,")[k];
					}
				// add and
				//if(k<getPrimaryKey(tablename).size()-1){
					if(k<primaryKeyNum-1){
					sql+=" and ";
					}
				}
				sql+=";";
				//System.out.println(sql);
				// get the resultset
				this.dataSet.add(stmt.executeQuery(sql));
				for (int j = 1; j < colNum+1; j++) {
					//deal with different type of insert sql
				/*for (int j = 1; j < getColumnName(tablename).size()+1; j++) {
					insertSql += dealInsertType(dataSet.get(i).getString(j),getColumnName(tablename).get(j-1));
					if(j<getColumnName(tablename).size())*/
					insertSql += dealInsertType(this.dataSet.get(i).getString(j),column.get(j-1));
					if(j<colNum)
						insertSql+=",";
				}
				insertSql += ");\n";
				insertSqlSets.add(insertSql);
				Statement newstmt = newcon.createStatement();
				newstmt.executeUpdate(insertSqlSets.get(i));
				fileWriter.append(insertSql);
				fileWriter.flush();
			//	System.out.println(insertSqlSets.get(i));
			}// end of first for
		}
		catch (Exception e){
			e.printStackTrace();
		}
		// release
		primaryKey.clear();
		primaryType.clear();
		primaryIndexCol.clear();
		dataSet.clear();
		column.clear();
		dataSet.clear();
		insertSqlSets.clear();

	}


	// deal with varchar and date type
	public String dealInsertType(String sql, String colname){
		String dataType=(colname.split("//-")[0]).split(" ")[1];
		String[] temp;
		if(sql!=null) {
			if (dataType.contains("CHAR") || dataType.contains("TEXT") || dataType.contains("BLOB")||dataType.contains("TIME")) {
				if (sql.contains("'")) {
					//sql.replaceAll("'","''");
					temp = sql.split("'");
					sql = "";
					for (int i = 0; i < temp.length; i++) {
						sql += temp[i];
						if (i < temp.length - 1) {
							sql += "''";
						}
					}
				}
				sql = "'" + sql + "'";
			}
			// deal with the datatype "Date" and "Timeslamp"
			else if (dataType.contains("DATE")) {
				sql = "DATE('" + sql + "')";
			}
			if (sql.contains("\\N")) {
				sql = "'\\N'";
			}
		}
		return sql;
	}


}

