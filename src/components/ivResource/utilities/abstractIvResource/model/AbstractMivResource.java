package components.ivResource.utilities.abstractIvResource.model;

import components.ivResource.utilities.interfaces.IivResource;
import components.metadata.MMetaData;

public abstract class AbstractMivResource implements IivResource {

	protected MMetaData metaD;

	@Override
	public MMetaData getMetaData() {
		if (metaD == null)
			readMetaData();
		return metaD;
	}
	
	public void setMetaData(MMetaData md){
		this.metaD = md;
	}
	
	public abstract AbstractMivResource sortAll(int [][] crit);
	
}
