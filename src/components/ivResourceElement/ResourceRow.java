package components.ivResourceElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import components.ivResourceElement.interfaces.IivResourceElement;
import components.ivResourceElement.interfaces.IivResourceRow;
import components.metadata.MMetaData;
import components.metadata.MSingleMetaD;
import configs.AppConfigs;

public class ResourceRow implements IivResourceRow, Comparable<ResourceRow> {

	private ArrayList<AbstractResourceElement> elements = new ArrayList<>();

	public ResourceRow(ArrayList<AbstractResourceElement> elements) {
		super();
		this.elements = elements;
	}

	@Override
	public AbstractResourceElement getElement(String key) {
		/*
		 * for (AbstractResourceElement abstractFileElement : elements) { if
		 * (abstractFileElement.getMetaKey().equals(key)) return
		 * abstractFileElement; }
		 */
		return null;
	}

	@Override
	public AbstractResourceElement getElement(int i) {
		return elements.get(i);
	}

	public ArrayList<AbstractResourceElement> getAllElements() {
		return elements;
	}

	@Override
	public String toIvString() {
		StringBuilder row = new StringBuilder();
		for (AbstractResourceElement el : elements) {
			row.append(el.toString() + AppConfigs.FILE_DELIMITER);
		}
		return row.toString();
	}

	@Override
	public int getColCount() {
		return elements.size();
	}

	public int compareTo(ResourceRow r, int col) {
		setCompareCriteria(new int[][] { { col } });
		return compareTo(r);
	}

	/*
	 * svaki red predstavlja jedan kriterijum svaka ćelija u redu predstavlja
	 * kolone koje treba grupisati da bi se dobio kriterijum
	 */
	private int[][] compareCrit;

	public void setCompareCriteria(int[][] criterias) {
		this.compareCrit = criterias;
	}
	
	public int[][] getPrimaryCrit(MMetaData md){
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
	
	public int compareTo(ResourceRow r, int[][] compareCrit){
		this.setCompareCriteria(compareCrit);
		return this.compareTo(r);
	}

	/*
	 * ako je comparecrit == null onda poredi po prvom polju
	 * 
	 */

	@Override
	public int compareTo(ResourceRow o) {
		if (compareCrit == null || compareCrit.length == 0) {
			compareCrit = new int[][] { { 0 } };
		}
		if (o.getColCount() != this.getColCount())
			return -1;
		int pomRes = 0;
		for (int i = 0; i < compareCrit.length; i++) {
			if (compareCrit[i].length == 1) {
				pomRes = this.getElement(compareCrit[i][0]).compareTo(o.getElement(compareCrit[i][0]));
			} else {
				StringBuilder thisKey = new StringBuilder();
				StringBuilder key = new StringBuilder();
				for (int j = 0; j < compareCrit[i].length; j++) {
					try {
						thisKey.append(this.getElement(j).toString());
						key.append(o.getElement(j).toString());
					} catch (Exception outOfBound) {
						// van granica
						return -1;
					}
				}
				pomRes = thisKey.toString().compareTo(key.toString());
			}
		}
		return pomRes;
	}
	
	public boolean checkUnique(ResourceRow r, MMetaData m){
		int[] u = m.getUniqueCols();
		for (int i : u) {
			if (this.compareTo(r, i) == 0)
				return false;
		}
		return true;
	}

	@Override
	public String toString() {
		
		return elements.toString();
	}
	
	

}
