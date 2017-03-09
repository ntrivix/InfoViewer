package components.dbManager;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.HashMap;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.json.JSONObject;

import components.ivResourceElement.SupportedType;
import components.metadata.MMetaData;
import components.metadata.MSingleMetaD;

public class MDBManager extends DefaultTreeModel {
	private String dbName;
	private JSONObject metaData;
	private Connection con;
	private static DefaultMutableTreeNode root;
	private HashMap< String, MMetaData> hmap = new HashMap<>();

	private MDBManager(String dbName, Connection con, JSONObject metaData) {
		super(root = new DefaultMutableTreeNode(dbName));
		this.dbName = dbName;
		this.con = con;
		this.metaData = metaData;
		hmap = new HashMap();
		initialize();
	}

	public String getDbName() {
		return dbName;
	}

	public Connection getCon() {
		return con;
	}

	private static MDBManager instance;

	public static MDBManager getInstance(String dbName, Connection con, JSONObject metaData) {
		if (instance == null)
			return instance = new MDBManager(dbName, con, metaData);
		else {
			root = new DefaultMutableTreeNode(dbName);
			// instance.con.close();
			instance.con = con;
			instance.metaData = metaData;
			instance.initialize();
			return instance;
		}
	}

	public static MDBManager getLastInstance() {
		return instance;
	}

	private void initialize() {
		this.setRoot(root = new DefaultMutableTreeNode(dbName));
		JSONObject obj = metaData;
		try {
			DatabaseMetaData dbmd = con.getMetaData();
			ResultSet tables = dbmd.getTables(null, null, null, new String[] { "TABLE" });
			while (tables.next()) {

				String tableName = tables.getString("TABLE_NAME");
				String readableName = "";

				ArrayList<MSingleMetaD> mSMetaData = new ArrayList<>();
				HashMap<String, MSingleMetaD> hm = new HashMap<>();

				JSONObject jsonTableCols = null;
				JSONObject jsonTable = null;
				try {
					jsonTable = obj.getJSONObject(tableName);
					jsonTableCols = jsonTable.getJSONObject("cols");
					readableName = jsonTable.getString("table_name");
				} catch (Exception e) {
					System.err.println("Za ovu tabelu nisu uneseni json podaci");
					jsonTable = new JSONObject();
					jsonTableCols = new JSONObject();
					jsonTable.put("cols", jsonTableCols);
					jsonTable.put("table_name", "");
					obj.put(tableName, jsonTable);
				}

				ResultSet rsColumns = dbmd.getColumns(null, null, tableName, null);

				while (rsColumns.next()) {

					String columnName = rsColumns.getString("COLUMN_NAME");
					String colReadableName = "";
					String dataType = rsColumns.getString("TYPE_NAME");
					String isNullable = rsColumns.getString("IS_NULLABLE");
					int colSize = rsColumns.getInt("COLUMN_SIZE");
					try {
						colReadableName = jsonTableCols.getString(columnName);
					} catch (Exception e) {
						jsonTableCols.put(columnName, "");
					}

					MSingleMetaD msmd;
					// izvuci podatke iz JSon fajla
					switch (dataType) {
					case "bigint":
					case "numeric":
					case "smallint":
					case "decimal":
					case "int":
					case "tinyint":
						msmd = new MSingleMetaD<>(Integer.class, columnName, colReadableName);
						break;
					case "float":
					case "real":
						msmd = new MSingleMetaD<>(Float.class, columnName, colReadableName);
						break;
					case "date":
					case "datetime":
					case "time":
						msmd = new MSingleMetaD<>(Date.class, columnName, colReadableName);
						break;
					case "char":
						msmd = new MSingleMetaD<>(String.class, columnName, colReadableName);
						msmd.setType(SupportedType.CHAR);
						break;
					default:
						msmd = new MSingleMetaD<>(String.class, columnName, colReadableName);
						break;

					}
					msmd.setSize(colSize);
					if (isNullable.equals("NO"))
						msmd.setMandatory(true);
					hm.put(columnName, msmd);
					mSMetaData.add(msmd);
				}
				ResultSet primaryKeys = dbmd.getPrimaryKeys(null, null, tableName);
				String pkName = null;
				while (primaryKeys.next()) {
					pkName = primaryKeys.getString("PK_NAME");
					String colName = primaryKeys.getString("COLUMN_NAME");
					MSingleMetaD t = hm.get(colName);
					t.setPrimary(true);
				} /*
					 * ResultSet indexes = dbmd.getIndexInfo(null, null,
					 * tableName, false, false); while (indexes.next()){ String
					 * colName = indexes.getString("COLUMN_NAME"); MSingleMetaD
					 * t = hm.get(colName); if
					 * (indexes.getBoolean("NON_UNIQUE")) t.setUnique(true);
					 * else t.setIndex(true); }
					 */
				MMetaData md = new MMetaData(mSMetaData);
				md.setName(tableName);
				if (pkName != null)
					md.setPrimaryKeyName(pkName);
				md.setReadableName(readableName); // ovo treba izvuci iz JSON-a
				root.add(md);
				hmap.put(md.getName(), md);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(obj);
	}
	
	public MMetaData getTableMetaData(String tableName){
		return hmap.get(tableName);
	}

}
