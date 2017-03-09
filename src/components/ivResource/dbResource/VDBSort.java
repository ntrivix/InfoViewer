package components.ivResource.dbResource;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import components.metadata.MSingleMetaD;

public class VDBSort extends JDialog{
	private DbTableModel model;

	ArrayList<SortElem> sl = new ArrayList<>();
	public VDBSort(DbTableModel model) {
		super();
		this.model = model;
		this.setLayout(new GridLayout(model.getColumnCount()+1, 1));
		int i = 0;
		for (MSingleMetaD  md: model.getMetaData().getAll()) {
			SortElem t = new SortElem(md,i++);
			sl.add(t);
			this.add(t);
		}
		JButton sort = new JButton("Sort");
		VDBSort self = this;
		sort.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Collections.sort(sl);
				int cmp[][] = new int[sl.size()][1];
				int i = 0;
				//int k = 0;
				for (i = 0; i <sl.size(); i++){
					if (sl.get(i).getVal() == 0)
						break;
					cmp[i][0] = sl.get(i).getPos();
					//k++;
				}
				int rcrit[][] = new int[i][1];
				for (int j = 0; j < i; j++){
					rcrit[j][0] = cmp[j][0];
				}
				model.sortAll(rcrit);
				self.setVisible(false);
			}
		});
		this.add(sort);
		this.pack();
		this.setVisible(true);
	}
	
	private class SortElem extends JPanel implements Comparable<SortElem> {
		private MSingleMetaD md;
		private int pos;
		JTextField fval;
		public SortElem(MSingleMetaD md, int pos) {
			super();
			this.md = md;
			this.pos = pos;
			this.add(new JLabel(md.getName()));
			fval = new JTextField("0");
			this.add(fval);
		}
		
		public int getVal(){
			try {
				return Integer.parseInt(fval.getText());
			} catch (Exception e){
				
			}
			return 0;
		}

		@Override
		public int compareTo(SortElem o) {
			if (o.getVal() == 0)
				return -1;
			if (this.getVal() == 0)
				return 1;
			if (this.getVal() > o.getVal())
				return +1;
			else
				return -1;
		}

		public int getPos() {
			return pos;
		}

		public void setPos(int pos) {
			this.pos = pos;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return md.getName()+" "+getVal();
		}
		
		
		
		
	}
	
	
}
