package components.fileNavigator.utilities;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import components.ivResource.utilities.interfaces.IivResource;

public class NavigatorListCellRender implements ListCellRenderer<IivResource> {

	private DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
	
	@Override
	public Component getListCellRendererComponent(JList<? extends IivResource> list, IivResource value, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index,
		        isSelected, cellHasFocus);
		if (value==null) return renderer;
			renderer.setIcon(value.getIcon());
		
		return renderer;
	}

}
