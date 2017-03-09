package components.settings.extensionManager.model;

import java.io.Serializable;

public class MExtension implements Serializable{
		
		private boolean isSelected=false;
		private String extension;
		
		public MExtension(boolean isSelected, String extension) {
			super();
			this.isSelected = isSelected;
			this.extension = extension;
		}
		
		public boolean isSelected() {
			return isSelected;
		}
		public void setSelected(boolean isSelected) {
			this.isSelected = isSelected;
		}
		public String getExtension() {
			return extension;
		}
	

}
