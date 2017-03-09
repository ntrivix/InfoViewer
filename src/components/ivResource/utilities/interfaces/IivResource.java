package components.ivResource.utilities.interfaces;

import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import components.ivResource.sekDat.model.MSekDat;
import components.ivResource.serialDat.model.MSerialDat;
import components.ivResourceElement.ResourceRow;
import components.ivResourceElement.interfaces.IivResourceElement;
import components.ivResourceElement.interfaces.IivResourceRow;
import components.metadata.MMetaData;

public interface IivResource {
	public boolean connect();
	public void disconnect();
	public boolean save();
	public boolean rename(String newName);
	public boolean readMetaData();
	public MMetaData getMetaData();
	public void setMetaData(MMetaData md);
	public boolean read();
	public boolean addNewRow(IivResourceRow r);
	public IivResourceRow[] getRows(int start, int count);
	public int getLoadedRowCount();
	
	/*
	 * Ako je neki element u resource row null on se zanemaruje u trazenju
	 */
	public ResourceRow[] find(ResourceRow r, int returnLimit);

	public String getHashCode();
	public ImageIcon getIcon();
	public boolean delete();
	public int getSize();
	
	
}
