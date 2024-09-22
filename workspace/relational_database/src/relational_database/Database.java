package relational_database;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Download connector from the below URL
 * https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-j-9.0.0.tar.gz
 */
public class Database {
	
	Connection connection = null;
	
	Database() {
		this.loadDriver();
	}
	
	public void loadDriver() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Loaded the JDBC driver successfully.");
		}
		catch (ClassNotFoundException exception) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Loading driver failed:", exception);
		}
	}

	public void connection(String username, String password, String database)  {
	  try {
		  this.connection = DriverManager.getConnection("jdbc:mysql://mysql8-host/"+database,
				  										username, password);
		  System.out.println("Connection Successful");
	  }
	  catch (SQLException exception) {
		  Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "SQLException: Database connection failed:", exception);
	  }
	  catch (Exception exception) {
		  Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Exception: Database connection failed:", exception);
	  }
	}
	
	/* This function is used to print the queried resultSet.
	 */
	public void printResultSet(ResultSet resultSet) {
		try {
				String output = "";
				ResultSetMetaData metaData = resultSet.getMetaData();
				while(resultSet.next()) {
					int coloumnCount = metaData.getColumnCount();
					for(int i=1; i <= coloumnCount; i++) {
						String coloumnName = metaData.getColumnName(i);
						output = output+coloumnName+ " " + resultSet.getString(coloumnName)+ ", " ;
					}
					output +="\n";
					
				}
				System.out.println(output);
		} 
		catch (SQLException exception) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "SQLException: Print result set failed:", exception);
		}
		catch (Exception exception) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Exception: Print result set failed:", exception);
		}
	}
	
	public void queryResultSet(String statementString) {
		try {
			ResultSet result = null;
			Statement statement = this.connection.createStatement();
			result = statement.executeQuery(statementString);
			System.out.println("Query completed");
			this.printResultSet(result);
			statement.close();
		}
		catch (SQLException exception) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "SQLException: Quering result set failed:", exception);
		}
		catch (Exception exception) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Exception: Quering result set failed:", exception);
		}
	}
	
	public Statement queryResultSet1(String statementString) {
		Statement statement = null;
		try {
			statement = this.connection.createStatement();
			statement.executeQuery(statementString);
		}
		catch (SQLException exception) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "SQLException: Quering result set failed:", exception);
		}
		catch (Exception exception) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Exception: Quering result set failed:", exception);
		}
		return statement;
	}
	
	public void updateRow(String tableName, String updateColoumns, String conditions) {
		try {
			String updateSQL = "UPDATE " + tableName +" SET "+updateColoumns+" WHERE "+conditions;
			PreparedStatement statement = this.connection.prepareStatement(updateSQL);
			statement.executeUpdate();
			System.out.println("Update completed");
			this.printJDBCConnectionTable();
			statement.close();
		}
		catch (SQLException exception) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "SQLException: Updating row failed:", exception);
		}
		catch (Exception exception) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Exception: Updating row failed:", exception);
		}
	}
	
	public void insertRow(String tableName, String values) {
		try {
			Statement statement = this.connection.createStatement();
			String insertString = "INSERT INTO "+ tableName +" VALUES ("+values+");";
			statement.execute(insertString);
			System.out.println("Insert completed");
			this.printJDBCConnectionTable();
			statement.close();
		}
		catch (SQLException exception) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "SQLException: Inserting row failed:", exception);
		}
		catch (Exception exception) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Exception: Inserting row failed:", exception);
		}
	}
	
	public void insertRowPrepared(String tableName, String slno, String name,
			                      String age, String occupation)
	{
		try {
			String sqlStatement = "INSERT INTO "+ tableName + " VALUES (?,?,?,?)";
			PreparedStatement statement = this.connection.prepareStatement(sqlStatement);
			statement.setString(1, slno);
			statement.setString(2, name);
			statement.setString(3, age);
			statement.setString(4, occupation);
			statement.execute();
			System.out.println("Insert completed");
			this.printJDBCConnectionTable();
			statement.close();
		}
		catch (SQLException exception) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "SQLException: Inserting row failed:", exception);
		}
		catch (Exception exception) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Exception: Inserting row failed:", exception);
		}
	}
	
	
	public void deleteRow(String tableName, String condition) {
		try {
			Statement statement = this.connection.createStatement();
			String deleteString = "DELETE FROM "+ tableName +" WHERE "+ condition;
			statement.execute(deleteString);
			System.out.println("Delete completed");
			this.printJDBCConnectionTable();
			statement.close();
		}
		catch (SQLException exception) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "SQLException: Deleting command failed:", exception);
		}
		catch (Exception exception) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Exception: Deleting row failed:", exception);
		}
	}
	
	public void printJDBCConnectionTable() {
		String selectSQL = "SELECT * FROM javajdbc.jdbcconnectiontable";
		this.queryResultSet(selectSQL);
	}
	
	public void executeBatch(String[] sqlStatements) {
		try {
			Statement statement = this.connection.createStatement();
		
			for(String sql: sqlStatements) {
				statement.addBatch(sql);
			}
			
			int[] results = statement.executeBatch();
			for(int result: results) {
				System.out.println(result);
			}
			
			System.out.println("Insert Batch completed");
			this.printJDBCConnectionTable();
			statement.close();
			
		}
		catch (SQLException exception) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "SQLException: Adding batch failed:", exception);
		}
		catch (Exception exception) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Exception: Adding batch failed:", exception);
		}
	}
	
	public void executeBatchPreparedStatement(String sql, String[][] sqlValues) {
		try {
			PreparedStatement statement = this.connection.prepareStatement(sql);
			for(String[] value: sqlValues) {
				statement.setString(1, value[0]);
				statement.setString(2, value[1]);
				statement.setString(3, value[2]);
				statement.setString(4, value[3]);
				statement.addBatch();
				statement.clearParameters();
			}
			
			int[] results = statement.executeBatch();
			for(int result: results) {
				System.out.println(result);
			}
			
			System.out.println("Insert Batch completed");
			this.printJDBCConnectionTable();
			statement.close();
			
		}
		catch (SQLException exception) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "SQLException: Adding batch failed:", exception);
		}
		catch (Exception exception) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Exception: Adding batch failed:", exception);
		}
	}
	
	public void executeStoredProcedureNoParam() {
		try {
			String sql = "CALL javajdbc.simplenoparam()";
			CallableStatement statement = this.connection.prepareCall(sql);
			boolean hasResult = statement.execute();
			if(hasResult) {
				this.printResultSet(statement.getResultSet());
			}
			System.out.println("Calling stored procedure completed");
			statement.close();
		}
		catch (SQLException exception) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "SQLException: Calling stored procedure failed:", exception);
		}
		catch (Exception exception) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Exception:  Calling stored procedure failed:", exception);
		}
	}
	
	public void executeStoredProcedureInParam() {
		try {
			String sql = "CALL javajdbc.simpleinparam(?)";
			CallableStatement statement = this.connection.prepareCall(sql);
			statement.setInt(1, 40);
			boolean hasResult = statement.execute();
			if(hasResult) {
				this.printResultSet(statement.getResultSet());
			}
			System.out.println("Calling stored procedure completed");
			statement.close();
		}
		catch (SQLException exception) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "SQLException: Calling stored procedure failed:", exception);
		}
		catch (Exception exception) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Exception:  Calling stored procedure failed:", exception);
		}
	}
	
	public void executeStoredProcedureOutParam() {
		try {
			String sql = "CALL javajdbc.simpleoutparam(?,?)";
			CallableStatement statement = this.connection.prepareCall(sql);
			statement.setInt(1, 20);
			statement.registerOutParameter(2, java.sql.Types.INTEGER);
			boolean hasResult = statement.execute();
			if(hasResult) {
				int countResult = statement.getInt(2);
				System.out.println("No of memebers "+countResult);
				this.printResultSet(statement.getResultSet());
			}
			System.out.println("Calling stored procedure completed");
			statement.close();
		}
		catch (SQLException exception) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "SQLException: Calling stored procedure failed:", exception);
		}
		catch (Exception exception) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Exception:  Calling stored procedure failed:", exception);
		}
	}
	
	public void executeStoredProcedureInOutParam() {
		try {
			String sql = "CALL javajdbc.simpleinoutparam(?)";
			CallableStatement statement = this.connection.prepareCall(sql);
			statement.setInt(1, 20);
			statement.registerOutParameter(1, java.sql.Types.INTEGER);
			boolean hasResult = statement.execute();
			if(hasResult) {
				int countResult = statement.getInt(1);
				System.out.println("No of memebers "+countResult);
				this.printResultSet(statement.getResultSet());
			}
			System.out.println("Calling stored procedure completed");
			statement.close();
		}
		catch (SQLException exception) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "SQLException: Calling stored procedure failed:", exception);
		}
		catch (Exception exception) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Exception:  Calling stored procedure failed:", exception);
		}
	}
	
}
