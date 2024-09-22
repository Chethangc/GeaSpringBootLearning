package relational_database;

/*
 * Install windowBuilder (Current) 
 * Help -> Eclipse Marketplace -> WindowBuilder -> Install 'WindowBuilder Current'.
 */
public class Main {

	
	public static void main(String args[]) {
		Database database;
		database = new Database();
		database.connection("chethangc", "17071998", "test_database");
		
		
		// QUERY
		database.printJDBCConnectionTable();
		// with WHERE condition
		database.queryResultSet("SELECT * FROM javajdbc.jdbcconnectiontable WHERE slno=1");
		
		// INSERT
		System.out.println("\nBefore Inserting row \n");
		database.printJDBCConnectionTable();

		database.insertRow("javajdbc.jdbcconnectiontable", "3, 'Alonegc', '26', 'single'");
		
		System.out.println("\nAfter Inserting row \n");
		database.printJDBCConnectionTable();
		
		// DELETE
		System.out.println("\nBefore Deleting row \n");
		database.printJDBCConnectionTable();
		
		database.deleteRow("javajdbc.jdbcconnectiontable", "slno='3';");
		
		System.out.println("\nAfter Deleting row \n");
		database.printJDBCConnectionTable();
		
		executeBatch();
		database.executeStoredProcedureNoParam();
		database.executeStoredProcedureInParam();
		database.executeStoredProcedureOutParam();
		database.executeStoredProcedureInOutParam();
	}
	
	public static void executeBatch() {
		Database database;
		database = new Database();
		database.connection("chethangc", "17071998", "test_database");
		String [] sqlStatements = {"INSERT INTO javajdbc.jdbcconnectiontable VALUES ('111','chethan', '25', 'newbie')",
						           "INSERT INTO javajdbc.jdbcconnectiontable VALUES ('112','khethan', '75', 'oldbie')",
						           "INSERT INTO javajdbc.jdbcconnectiontable VALUES ('113','hethan', '45', 'engg')",
						           "INSERT INTO javajdbc.jdbcconnectiontable VALUES ('116','mhethan', '29', 'parole')"};
		database.executeBatch(sqlStatements);
		
		
		String statement = "INSERT INTO javajdbc.jdbcconnectiontable VALUES (?,?,?,?);";
		String [][] tableValues = {{"118","gc","78","department"},
				                   {"119","cg","79","hope"}};
		database.executeBatchPreparedStatement(statement, tableValues);
	}
}
