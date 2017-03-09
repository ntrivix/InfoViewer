package components.ivResource.utilities.abstractLocalFile.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import components.ivResource.sekDat.model.MSekDat;
import components.ivResource.utilities.abstractIvResource.model.AbstractMivResource;
import components.ivResourceElement.AbstractResourceElement;
import components.ivResourceElement.ResourceRow;
import components.ivResourceElement.interfaces.IivResourceRow;
import components.metadata.MMetaData;
import components.metadata.MSingleMetaD;
import components.settings.settingsManager.SettingsManager;
import configs.AppConfigs;
import utilities.MergeArranged;

public abstract class ALocalFileModel extends AbstractMivResource implements TableModel {

	protected File file;
	protected String filepath;
	protected RandomAccessFile fileRW;
	protected long filePointer = 0;

	protected int rowsToFetch = SettingsManager.getInstance().getRows_to_fetch();

	protected ArrayList<ResourceRow> rows = new ArrayList<>();

	public ALocalFileModel(String filepath) {
		this(new File(filepath));
	}

	public ALocalFileModel(File file) {
		super();
		try {
			file.createNewFile();
		} catch (IOException e) {

		}
		this.file = file;
		this.filepath = file.getPath();
	}

	@Override
	public boolean readMetaData() {
		Path path = Paths.get(getLocation().getAbsolutePath());

		try {
			UserDefinedFileAttributeView view = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
			// view.write("publishable",
			// Charset.defaultCharset().encode("true"));
			String name = "infov";
			ByteBuffer buffer = ByteBuffer.allocate(view.size(name));
			view.read(name, buffer);
			buffer.flip();
			String value = Charset.defaultCharset().decode(buffer).toString();
			metaD = new MMetaData(value);
		} catch (Exception e) {
			metaD = new MMetaData();
			return false;
		}
		return true;
	}

	private void saveMetaData() {
		Path p = Paths.get(getLocation().getAbsolutePath());
		// final Path file =
		// Paths.get(Example.class.getResource("/samples/example.txt").toURI()).toAbsolutePath();

		final UserDefinedFileAttributeView view = Files.getFileAttributeView(p, UserDefinedFileAttributeView.class);

		// The file attribute
		final String name = "infov";
		final String value = getMetaData().toJson();
		// Write the properties
		byte[] bytes;
		try {
			bytes = value.getBytes("UTF-8");
			final ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
			writeBuffer.put(bytes);
			writeBuffer.flip();
			view.write(name, writeBuffer);
		} catch (UnsupportedEncodingException e) {
			bytes = null;
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean connect() {
		try {
			fileRW = new RandomAccessFile(file, "rw");

			return true;
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Acced denied or file does not exist", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}

	@Override
	public void disconnect() {
		try {
			fileRW.close();
		} catch (IOException e) {

		}
	}
	
	public void seekToFilePointer(){
		try {
			this.fileRW.seek(filePointer);
		} catch (IOException e) {

		}
	}

	public File getLocation() {
		return file;
	}

	@Override
	public boolean delete() {
		return this.file.delete();
	}
	
	public boolean read() {
		this.connect();
		try {
			fileRW.seek(filePointer);
			ArrayList<ResourceRow> rows = new ArrayList<>();
			for (int i = 0; i < rowsToFetch; i++) {
				try {
					rows.add(readRow());
					this.rows = null;
				} catch (IrregularLine e) {
				} catch (EndOfFileE e) {
					break;
				}
			}
			filePointer = fileRW.getFilePointer();
			if (rows.size() > 0)
				this.rows = rows;
			else return false;
		} catch (IOException e) {
			return false;
		} finally {
			this.disconnect();
			notyfyTableObservers(new TableModelEvent(this));
		}
		return true;
	}

	private final Charset fromCharset = Charset.forName("latin1");
	private final Charset toCharset = Charset.forName("UTF-8");

	public long pomFilePointer = filePointer;
	
	public ResourceRow readRow() throws IOException, IrregularLine, EndOfFileE {
		String tline = fileRW.readLine();
		if (tline == null)
			throw new EndOfFileE();

		tline = new String(tline.getBytes(fromCharset), toCharset);
		//if (tline.endsWith(AppConfigs.FILE_DELIMITER)) //OVO KASNIJE UKLONI
			//tline += "null";							//
		String[] split = tline.split(Pattern.quote(AppConfigs.FILE_DELIMITER));
		if (split.length != getMetaData().getColumsCount())
			throw new IrregularLine(tline);
		ArrayList<AbstractResourceElement> elements = new ArrayList<>();
		int j = 0;
		for (String rec : split) {
			MSingleMetaD tmd = getMetaData().getMetaD(j);
			AbstractResourceElement el;
			try {
				el = tmd.generateModel(rec);
			} catch (Exception e) {
				el =null;
			}
			if (el != null) {
				elements.add(el);
			}
			j++;
		}
		pomFilePointer = fileRW.getFilePointer();
		if (elements.size() == getMetaData().getColumsCount())
			return new ResourceRow(elements);
		else
			throw new IrregularLine(tline);
	}
	
	

	public long getFilePointer() {
		return filePointer;
	}
	
	public void resetFilePointer(){
		filePointer = 0;
	}
	
	public long setFilePointerToEOF(){
		return filePointer = file.length();
	}

	public void setFilePointer(long filePointer) {
		this.filePointer = filePointer;
	}

	@Override
	public String toString() {
		return file.getName();
	}

	@Override
	public boolean rename(String newName) {
		file.renameTo(new File(file.getParent()+newName));
		return true;
	}

	@Override
	public void setMetaData(MMetaData md) {
		super.setMetaData(md);
		this.saveMetaData();
	};

	@Override
	public int getSize() {
		try {
			return (int) Math.round(file.length() / (double) 1024);
		} catch (Exception e) {

		} // velicina u kb

		return 0;
	}

	@Override
	public IivResourceRow[] getRows(int start, int count) {
		return (IivResourceRow[]) rows.subList(start, start + count).toArray();
	}

	@Override
	public int getLoadedRowCount() {
		return rows.size();
	}

	/*
	 * Implementacija table modela
	 */

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
			el.setData(aValue);
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
	public AbstractMivResource sortAll(int[][] crit) {
		MergeArranged mergeArr = new MergeArranged(metaD, crit, this.file);
		disableViewUpdate = true;
		long oldFilePointere = this.filePointer;
		ArrayList<ResourceRow> oldrows = this.rows;
		this.filePointer = 0;
		
		
		rowCrit = crit;
		while (this.read()){
			sortRead();			
			mergeArr.addToMerge((ResourceRow[])this.rows.toArray(new ResourceRow[rows.size()]));
		}
		mergeArr.merge();
		disableViewUpdate  = false;
		this.rows = oldrows;
		this.filePointer = oldFilePointere;
		return this;
	}
	
	private int[][] rowCrit;
	Comparator<ResourceRow> comparator = new Comparator<ResourceRow>() {
		@Override
		public int compare(ResourceRow o1, ResourceRow o2) {
			return o1.compareTo(o2, rowCrit);
		}
	};
	
	public void sortReaded(int [][] crit){
		rowCrit = crit;
		sortRead();
	}
	
	public void sortRead(){
		Collections.sort(this.rows, comparator);
	}

	public MSekDat toSekDat(MMetaData md) {
		
		this.sortAll(getCritByPrimaryKey(md));
		MSekDat msd = new MSekDat(this.file);
		msd.setMetaData(md);
		return msd;
	}
	
	public int[][] getCritByPrimaryKey(MMetaData md){
		/*int[][] crit = new int [1][md.getColumsCount()];
		int i = 0, j = 0;
		for (MSingleMetaD msd : md.getAll()) {
			if (msd.isPrimary()){
				crit[0][i] = j;
				i++;
			}
			j++;
		}
		int[][] newcrit = new int[1][i];
		for (int k = 0; k<i; k++){
			newcrit[0][k] = crit[0][k];
		}
		return newcrit;
		*/
		ArrayList<Integer> pk = new ArrayList<>();
		int i = 0;
		for (MSingleMetaD ms : md.getAll()) {
			if (ms.isPrimary())
				pk.add(i);
			i++;
		}
		int [][] crit = new int[1][pk.size()];
		i =0;
		for (Integer pki : pk) {
			crit[0][i++] = pki;
		}
		return crit;
	}

	@Override
	public String getHashCode() {
		
		return getLocation().getAbsolutePath();
	}
	
	

}
