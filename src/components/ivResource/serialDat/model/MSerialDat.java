package components.ivResource.serialDat.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.event.TableModelEvent;

import com.alee.utils.ArrayUtils;

import components.ivResource.utilities.abstractLocalFile.model.ALocalFileModel;
import components.ivResource.utilities.abstractLocalFile.model.EndOfFileE;
import components.ivResource.utilities.abstractLocalFile.model.IrregularLine;
import components.ivResourceElement.AbstractResourceElement;
import components.ivResourceElement.ResourceRow;
import components.ivResourceElement.interfaces.IivResourceRow;
import configs.AppConfigs;
import utilities.ResourceLoader;

public class MSerialDat extends ALocalFileModel {

	private static ImageIcon icon = ResourceLoader.getImageIcon("icon/file.png", AppConfigs.NAV_ICON_w,
			AppConfigs.NAV_ICON_H);

	public MSerialDat(File file) {
		super(file);
	}

	@Override
	public boolean save() {
		return false;
	}

	@Override
	public boolean addNewRow(IivResourceRow r) {
		return this.addNewRow(r, true);
	}

	public boolean addNewRow(IivResourceRow r, boolean addToModel) {
		this.connect();
		try {
			fileRW.seek(setFilePointerToEOF());
			String row = r.toIvString() + System.getProperty("line.separator");
			fileRW.write(row.getBytes());
			if (addToModel)
				rows.add((ResourceRow) r);
		} catch (IOException e) {
			return false;
		} finally {
			this.disconnect();
			notyfyTableObservers(new TableModelEvent(this));
		}
		return true;
	}

	@Override
	public ResourceRow[] find(ResourceRow r, int returnLimit) {
		ArrayList<ResourceRow> results = new ArrayList<>();
		boolean doKraja = false;
		if (returnLimit == 0)
			doKraja = true;
		while (returnLimit > 0 || doKraja) {
			try {
				ResourceRow tr = this.readRow();
				if (tr.getColCount() == r.getColCount()){
					int i = 0,j = 0,k = 0;
					for (AbstractResourceElement el : r.getAllElements()) {
						if (el != null){
							j++;
							if ( el.compareTo(tr.getElement(k)) != 0){
								break;
							}
							i++;
						}
						k++;
					}
					if (j == i){
						results.add(tr);
						returnLimit--;
					}
					
				}
			} catch (IrregularLine e) {
			} catch (IOException | EndOfFileE e) {
				break;
			}
			
		}
		return (ResourceRow[]) results.toArray(new ResourceRow[results.size()]);
	}

	public ResourceRow find(ResourceRow r) {
		return this.find(r, 0)[0];
	}

	@Override
	public ImageIcon getIcon() {
		return icon;
	}

	@Override
	public IivResourceRow[] getRows(int start, int count) {
		return null;
	}

}
