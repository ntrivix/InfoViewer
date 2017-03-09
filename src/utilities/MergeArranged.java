package utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;

import components.ivResource.sekDat.model.MSekDat;
import components.ivResource.serialDat.model.MSerialDat;
import components.ivResource.utilities.abstractLocalFile.model.EndOfFileE;
import components.ivResource.utilities.abstractLocalFile.model.IrregularLine;
import components.ivResourceElement.ResourceRow;
import components.metadata.MMetaData;

public class MergeArranged {

	private ArrayList<MSerialDat> toMerge = new ArrayList<>();
	private ArrayList<MSerialDat> merged = new ArrayList<>();

	private MMetaData metaD;
	Comparator<ResourceRow> comparator;
	private int[][] crit;
	private File out;

	private int inserted = 0;
	
	private boolean ignoreEqual = false;
	private boolean uniqueColCheck = false;

	public MergeArranged(MMetaData metaD, int[][] crit, File out) {
		super();
		this.metaD = metaD;
		this.crit = crit;
		this.out = out;
	}

	public MergeArranged(MMetaData metaD, int[][] crit, Path out) {
		this(metaD, crit, out.toFile());
	}

	public MergeArranged(MMetaData metaD, int[][] crit, String outLocation) {
		this(metaD, crit, new File(outLocation));
	}
	
	

	public boolean isIgnoreEqual() {
		return ignoreEqual;
	}

	public void setIgnoreEqual(boolean ignoreEqual) {
		this.ignoreEqual = ignoreEqual;
	}

	public void addToMerge(MSerialDat dat) {
		toMerge.add(dat);
		inserted++;
	}

	public void addToMerge(ResourceRow[] r) {
		MSerialDat dat = new MSerialDat(new File(out.getAbsolutePath() + inserted + ".tmp"));
		dat.setMetaData(metaD);
		for (ResourceRow resourceRow : r) {
			dat.addNewRow(resourceRow, false);
		}
		addToMerge(dat);
	}

	public File merge() {

		while (toMerge.size() > 1) {
			for (int i = 0; i < toMerge.size(); i+=2){
				if (i+1 < toMerge.size())
					merged.add(this.merge(toMerge.get(i), toMerge.get(i+1)));
				else 
					merged.add(toMerge.get(i));
			}
			toMerge = (ArrayList<MSerialDat>) merged.clone();
			merged = new ArrayList<>();
		}
		out.delete();
		toMerge.get(0).rename(out.getName());
		return out;
	}
	
	private ResourceRow lastInserted;

	private MSerialDat merge(MSerialDat d1, MSerialDat d2) {
		d1.connect();
		d2.connect();
		MSerialDat dat = new MSerialDat(new File(out.getAbsolutePath() + inserted + ".tmp"));
		dat.setMetaData(metaD);
		ResourceRow r1 = null, r2 = null;
		do {
			while (r1 == null) {
				try {
					r1 = d1.readRow();
				} catch (IOException | IrregularLine e) {
				} catch (EndOfFileE e) {
					finishFile(dat, d2);
					d1.disconnect(); d2.disconnect();
					return dat;
				}
				d1.setFilePointer(d1.pomFilePointer);
			}
			while (r2 == null) {
				try {
					r2 = d2.readRow();
				} catch (IOException | IrregularLine e) {
				} catch (EndOfFileE e) {
					finishFile(dat, d1);
					d1.disconnect(); d2.disconnect();
					return dat;
				}
				d2.setFilePointer(d2.pomFilePointer);
			}
			int cmp = r1.compareTo(r2,this.crit);
			if (cmp == 0 && ignoreEqual){
				System.err.println("primary key violation ->"+r2);
				dat.addNewRow(r1, false);
				lastInserted = r1;
				r1 = null;
				r2 = null;
				continue;
			}
			if (cmp < 0) {
				dat.addNewRow(r1, false);
				lastInserted = r1;
				r1 = null;
			} else {
				dat.addNewRow(r2, false);
				lastInserted = r2;
				r2 = null;
			}

		} while (true);
	}
	

	private void finishFile(MSerialDat resdat, MSerialDat readDat) {
		while (true) {
			try {
				ResourceRow r1 = readDat.readRow();
				if (lastInserted != null && ignoreEqual && r1.compareTo(lastInserted, crit) == 0){
					System.err.println("primary key violation ->"+r1);
					continue;
				}
				resdat.addNewRow(r1, false);
				lastInserted = r1;
			} catch (IOException | IrregularLine e) {
			} catch (EndOfFileE e) {
				return;
			}
		}
	}

}
