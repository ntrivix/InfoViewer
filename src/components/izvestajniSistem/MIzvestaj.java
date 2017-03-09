package components.izvestajniSistem;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;

import components.dbManager.MDBManager;
import components.metadata.MSingleMetaD;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class MIzvestaj extends DefaultMutableTreeNode {

	private String izvName;
	private String podsistem, grupa, podgrupa, oznaka;
	private ArrayList<MSingleMetaD> parameters = new ArrayList<>();
	private ArrayList<String> labele = new ArrayList<>();

	public MIzvestaj(String izvName, String podsistem, String grupa, String podgrupa, String oznaka) {
		this.izvName = izvName;
		this.podsistem = podsistem;
		this.grupa = grupa;
		this.podgrupa = podgrupa;
		this.oznaka = oznaka;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {

		return izvName;
	}

	public void addParametar(MSingleMetaD msmd, String labela) {
		parameters.add(msmd);
		labele.add(labela);
		// this.add(msmd);
		this.add(new DefaultMutableTreeNode(labela));
	}

	public String getLabel(int i){
		return labele.get(i);
	}
	
	public MSingleMetaD getParameter(int i) {
		return parameters.get(i);
	}

	public int getParamCount() {
		return parameters.size();
	}

	public JasperPrint generateReport(Map<String, Object> params) {
		InputStream in = MIzvestaj.class.getResourceAsStream("/reports/" + podsistem + grupa + podgrupa + oznaka + ".jrxml");

		JasperReport jasperReport;
		JasperPrint jasperPrint = null;
		try {
			jasperReport = JasperCompileManager.compileReport(in);
			jasperPrint = JasperFillManager.fillReport(jasperReport, params,
					MDBManager.getLastInstance().getCon());
		} catch (JRException e) {
			e.printStackTrace();
		}

		return jasperPrint;
	}

	public JasperPrint generateReport() {
		return null;
	}
	
	public MSingleMetaD getParamMeta(String tableName){
		for (MSingleMetaD param : parameters) {
			if (tableName.equals(param.getTableName()))
				return param;
		}
		return null;
	}
}
