package components.newFile;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;

import components.FileMetaGenerator.VChooser;
import components.FileMetaGenerator.view.VMetaGenerator;
import components.FileMetaGenerator.view.VMetaGenerators.VAbstractGenerator;
import components.ivResource.utilities.ResourceFactoryView;
import components.ivResource.ivFolder.model.MivFolder;
import components.ivResource.utilities.ResourceFactory;
import components.ivResource.utilities.abstractIvResource.model.AbstractMivResource;
import components.ivResource.utilities.abstractIvResource.viewer.VivResource;
import components.mainAppFrame.model.MMainAppFrame;
import components.mainAppFrame.view.VMainAppFrame;
import components.metadata.MMetaData;
import components.metadata.MSingleMetaD;
import components.settings.extensionManager.model.MExtensionManager;

public class CNewFile extends AbstractAction {

	private MMetaData md = null;
	private AbstractMivResource mfile = null;

	public CNewFile() {
		putValue(SMALL_ICON, utilities.ResourceLoader.getImageIcon("icon/new.png", 20, 20));
		putValue(SHORT_DESCRIPTION, "New File");
		putValue(SHORT_DESCRIPTION, "New File");
		putValue(NAME, "New File");
		putValue(MNEMONIC_KEY, new Integer('N'));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl N"));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		VFileChooser jf = new VFileChooser();
		// VMetaGenerator mtg = new VMetaGenerator(null);

		if (md == null) {
			VChooser vc = new VChooser();
			vc.setVisible(true);

			ArrayList<MSingleMetaD> mtd = vc.getMetaD();
			md = new MMetaData();
			if (mtd == null)
				return;
			for (MSingleMetaD mSingleMetaD : mtd) {
				md.addNewMetaD(mSingleMetaD);
			}
		}

		jf.setCurrentDirectory(MMainAppFrame.getInstance().getmFileNavigator().gettLocation());
		File projectFile;
		if (jf.showSaveDialog(VMainAppFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
			String name = jf.getSelectedFile().getName();

			String extension = name.substring(name.lastIndexOf('.') + 1, name.length());

			if (!MExtensionManager.getInstance().containsSelected(extension)) {
				// projectFile = new File(jf.getSelectedFile().getAbsolutePath()
				// + ".pf");
				// ova ekstenzija nije podržana
				System.out.println(
						"Ova ekstenzija nije podržana! Da bi je podržali dodajte ovu ekstenziju u Extension Manageru.");
			} else {
				projectFile = jf.getSelectedFile();
				try {
					projectFile.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mfile = ResourceFactory.create(projectFile);
				mfile.setMetaData(md);
				VivResource view = ResourceFactoryView.getViwer(mfile);
				VMainAppFrame.getInstance().getFileViewer().add(view);
				MMainAppFrame.getInstance().getmFileNavigator().refresh();
			}
		} else
			return;

	}

	public void setMd(MMetaData md) {
		this.md = md;
	}

	public AbstractMivResource getMfile() {
		return mfile;
	}
	
	

}
