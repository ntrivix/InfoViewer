package components.izvestajniSistem;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.tree.DefaultMutableTreeNode;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class CGenerateReport extends MouseAdapter {

	
	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		if(e.getClickCount()==2){
			Object obj = e.getSource();
			if (obj instanceof VIzvestajnoStablo){
				obj = ((VIzvestajnoStablo)obj).getLastSelectedPathComponent();
				if (!(obj instanceof MIzvestaj))
					return;
				/*HashMap<String, Object> map = new HashMap<>();
				map.put("DR_OZNAKA", "%");
				map.put("TO_PERIOD_OD", "");
				map.put("TO_PERIOD_DO", "");*/
				VIzSistGenerator visg = new VIzSistGenerator((MIzvestaj)obj);
				JasperPrint print = ((MIzvestaj)obj).generateReport(visg.getHashMap());
				JasperViewer.viewReport(print, false);
			}
		}
	}
}
