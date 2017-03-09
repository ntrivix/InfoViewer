package components.ivResource.dbResource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import components.ivResource.utilities.abstractIvResource.model.AbstractMivResource;
import components.ivResourceElement.AbstractResourceElement;
import components.ivResourceElement.ResourceRow;
import components.ivResourceElement.interfaces.IivResourceRow;
import components.metadata.MMetaData;
import components.metadata.MSingleMetaD;

public class DbTableModel extends AbstractMivResource implements TableModel {

	private String dbName;
	private Connection conn;
	protected ArrayList<ResourceRow> rows = new ArrayList<>();

	public DbTableModel(MMetaData metad, String dbName, Connection conn) {
		super();
		this.metaD = metad;
		this.dbName = dbName;
		this.conn = conn;
	}

	public String getDbName() {
		return dbName;
	}

	public String getTableName() {
		return metaD.getName();
	}

	@Override
	public boolean connect() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean save() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean rename(String newName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean readMetaData() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean read() {
		Statement stmt;
		try {
			ArrayList<ResourceRow> rows = new ArrayList<>();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + this.getTableName());
			while (rs.next()) {
				ArrayList<AbstractResourceElement> res = new ArrayList<>();
				for (int i = 0; i < metaD.getColumsCount(); i++) {
					MSingleMetaD tmd = getMetaData().getMetaD(i);
					try {
						res.add(tmd.generateModel(rs.getString(tmd.getKey())));
					} catch (Exception e) {
						res.add(null);
					}
				}
				rows.add(new ResourceRow(res));
			}
			if (rows.size() > 0)
				this.rows = rows;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			notyfyTableObservers(new TableModelEvent(this));
		}
		return true;
	}

	@Override
	public boolean addNewRow(IivResourceRow r) {
		StringBuilder insertQ = new StringBuilder("INSERT INTO " + getTableName() + " (");
		StringBuilder values = new StringBuilder();
		for (int i = 0; i < metaD.getColumsCount(); i++) {
			MSingleMetaD tmd = getMetaData().getMetaD(i);
			String t;
			if (i == metaD.getColumsCount() - 1) {
				t = tmd.getKey();
				values.append("?");
			} else {
				t = tmd.getKey() + ", ";
				values.append("?, ");
			}
			insertQ.append(t);
		}
		insertQ.append(") VALUES (" + values + ")");
		PreparedStatement pstmt;
		System.out.println(insertQ.toString());
		try {
			pstmt = conn.prepareStatement(insertQ.toString());
			for (int i = 0; i < metaD.getColumsCount(); i++) {
				pstmt.setObject(i + 1, r.getElement(i).getData(), metaD.getMetaD(i).getSqlType());
			}
			pstmt.execute();
			this.read();
		} catch (SQLException e) {
			JDialog greska = new JDialog();
			greska.setTitle("Greska");
			greska.add(new JLabel(e.getMessage()));
			greska.pack();
			greska.setVisible(true);
			// e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public IivResourceRow[] getRows(int start, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLoadedRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ResourceRow[] find(ResourceRow r, int returnLimit) {
		Statement stmt;
		try {
			stmt = conn.createStatement();
			StringBuilder retString = new StringBuilder();
			int i = 0;
			boolean firstadded = false;
			for (AbstractResourceElement element : r.getAllElements()) {
				if (element != null) {
					if (firstadded)
						retString.append(" AND ");
					retString.append(metaD.getMetaD(i).getKey() + " = ?");
					firstadded = true;
				}
				i++;
			}
			String selectSQL = retString.toString();
			PreparedStatement preparedStatement = conn
					.prepareStatement("SELECT * FROM " + this.getTableName() + " WHERE " + selectSQL);
			int k = 0;
			i = 1;
			for (AbstractResourceElement element : r.getAllElements()) {
				if (element != null) {
					preparedStatement.setObject(i, element.getData());
					System.out.println(i + "=" + element.getData());
					i++;
				}
			}
			System.out.println("SELECT * FROM " + this.getTableName() + " WHERE " + selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			this.rows = new ArrayList<>();
			while (rs.next()) {
				ArrayList<AbstractResourceElement> elems = new ArrayList<>();
				for (i = 0; i < metaD.getColumsCount(); i++) {
					try {
						elems.add(metaD.getMetaD(i).generateModel(rs.getString(metaD.getMetaD(i).getKey())));
					} catch (Exception e) {
						elems.add(null);
					}
				}
				ResourceRow rr = new ResourceRow(elems);
				this.rows.add(rr);
				System.out.println(rr);
			}
			notyfyTableObservers(new TableModelEvent(this));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ImageIcon getIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AbstractMivResource sortAll(int[][] crit) {
		if (crit.length == 0)
			return null;
		Statement stmt;
		try {
			ArrayList<ResourceRow> rows = new ArrayList<>();
			stmt = conn.createStatement();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < crit.length; i++) {

				sb.append(getMetaData().getMetaD(crit[i][0]).getKey());
				if (i != crit.length - 1)
					sb.append(",");
			}
			// sb.deleteCharAt(crit.length-1);
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + this.getTableName() + " ORDER BY " + sb.toString());
			while (rs.next()) {
				ArrayList<AbstractResourceElement> res = new ArrayList<>();
				for (int i = 0; i < metaD.getColumsCount(); i++) {
					MSingleMetaD tmd = getMetaData().getMetaD(i);
					try {
						res.add(tmd.generateModel(rs.getString(tmd.getKey())));
					} catch (Exception e) {
						res.add(null);
					}
				}
				rows.add(new ResourceRow(res));
			}
			if (rows.size() > 0)
				this.rows = rows;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			notyfyTableObservers(new TableModelEvent(this));
		}
		return null;
	}

	@Override
	public String getHashCode() {
		return getDbName() + getTableName();
	}

	private ArrayList<TableModelListener> tObservers = new ArrayList<>();

	@Override
	public void addTableModelListener(TableModelListener l) {
		tObservers.add(l);
	}

	@Override
	public Class getColumnClass(int columnIndex) {
		return this.getMetaData().getColRealType(columnIndex);
	}

	@Override
	public int getColumnCount() {
		return this.getMetaData().getColumsCount();
	}

	@Override
	public String getColumnName(int columnIndex) {
		try {
			return this.getMetaData().getMetaD().get(columnIndex).getName();
		} catch (Exception noCol) {
			return "";
		}
	}

	@Override
	public int getRowCount() {
		return this.rows.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (rows.size() > rowIndex && getColumnCount() > columnIndex) {
			return ((AbstractResourceElement) rows.get(rowIndex).getElement(columnIndex)).getData();
		}
		return null;
	}
	
	public Object getValueAt(int rowIndex, MSingleMetaD col){
		int pos = metaD.getMsingleMdPosition(col);
		if (pos >= 0 && rowIndex >= 0){
			return getValueAt(rowIndex, pos);
		}
		return null;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		tObservers.remove(l);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rows.size() > rowIndex && getColumnCount() > columnIndex) {
			AbstractResourceElement el = (AbstractResourceElement) rows.get(rowIndex).getElement(columnIndex);

			StringBuilder insertQ = new StringBuilder("UPDATE " + getTableName() + " SET ");
			ArrayList<String> cond = new ArrayList<>();
			ArrayList<AbstractResourceElement> condVal = new ArrayList<>();
			for (int i = 0; i < metaD.getColumsCount(); i++) {
				MSingleMetaD tmd = getMetaData().getMetaD(i);

				if (tmd.isPrimary()) {
					cond.add(tmd.getKey() + "=?");
					condVal.add(rows.get(rowIndex).getElement(i));
				}

				if (i == metaD.getColumsCount() - 1) {
					insertQ.append(tmd.getKey() + " = ? ");
				} else {
					insertQ.append(tmd.getKey() + " = ?, ");
				}

			}
			insertQ.append("WHERE ");
			for (int i = 0; i < cond.size(); i++) {
				if (i == cond.size() - 1) {
					insertQ.append(cond.get(i));
				} else {
					insertQ.append(cond.get(i) + " AND ");
				}
			}

			PreparedStatement pstmt;
			System.out.println(insertQ.toString());
			try {
				pstmt = conn.prepareStatement(insertQ.toString());
				int i = metaD.getColumsCount();
				for (int j = 0; j < condVal.size(); j++) {
					pstmt.setObject(i + j + 1, condVal.get(j).getData(), condVal.get(j).getSType().sqlType());
					System.out.println(i + j + 1 + "=>" + condVal.get(j).getData());
				}
				el.setData(aValue);

				for (i = 0; i < metaD.getColumsCount(); i++) {
					try {
						pstmt.setObject(i + 1, rows.get(rowIndex).getElement(i).getData(),
								rows.get(rowIndex).getElement(i).getSType().sqlType());
					} catch (Exception e) {
						pstmt.setObject(i + 1, "null");
					}
					System.out.println(i + 1 + "=>" + rows.get(rowIndex).getElement(i).getData());
				}

				pstmt.execute();
			} catch (SQLException e) {
				JDialog greska = new JDialog();
				greska.setTitle("Greska");
				greska.add(new JLabel(e.getMessage()));
				greska.pack();
				greska.setVisible(true);
				// e.printStackTrace();

			} finally {
				this.read();
			}

		}
	}

	private boolean disableViewUpdate = false;

	protected void notyfyTableObservers(TableModelEvent e) {
		/*
		 * TableModelEvent(source); // The data, ie. all rows changed
		 * TableModelEvent(source, HEADER_ROW); // Structure change, reallocate
		 * TableColumns TableModelEvent(source, 1); // Row 1 changed
		 * TableModelEvent(source, 3, 6); // Rows 3 to 6 inclusive changed
		 * TableModelEvent(source, 2, 2, 6); // Cell at (2, 6) changed
		 * TableModelEvent(source, 3, 6, ALL_COLUMNS, INSERT); // Rows (3, 6)
		 * were inserted TableModelEvent(source, 3, 6, ALL_COLUMNS, DELETE); //
		 * Rows (3, 6) were deleted
		 */
		if (disableViewUpdate)
			return;
		for (TableModelListener t : tObservers) {
			t.tableChanged(e);
		}
	}

	@Override
	public String toString() {
		if (metaD.getReadableName() == null || metaD.getReadableName() == "")
			return metaD.getName();
		return metaD.getReadableName();
	}

}
