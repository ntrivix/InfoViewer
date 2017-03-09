package components.izvestajniSistem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import components.dbManager.MDBManager;
import components.metadata.MSingleMetaD;

public class MIZvestajnogSistema extends DefaultTreeModel {

	private static DefaultMutableTreeNode root;
	private Connection con;

	private ArrayList<MIzvestaj> izvestaji = new ArrayList<>();

	public MIZvestajnogSistema() {
		super(root = new DefaultMutableTreeNode("Izvestajni sistem"));
		this.con = MDBManager.getLastInstance().getCon();
		initialize();
	}

	private void initialize() {
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("EXEC izvestaji '%', '%', '%', '%'");
			String podsistem = "";
			String grupa = "";
			String podgrupa = "";
			String izvestaj = "";
			DefaultMutableTreeNode opodsistem = null;
			DefaultMutableTreeNode ogrupa = null;
			DefaultMutableTreeNode opodgrupa = null;
			MIzvestaj oizvestaj = null;
			while (rs.next()) {
				String t = rs.getString("PO_OZNAKA");
				if (!t.equals(podsistem)) {
					opodsistem = new DefaultMutableTreeNode(rs.getString("PO_NAZIV"));
					podsistem = t;
					root.add(opodsistem);
				}
				t = rs.getString("GI_OZNAKA");
				if (!t.equals(grupa)) {
					ogrupa = new DefaultMutableTreeNode(rs.getString("GI_NAZIV_GRUPE"));
					opodsistem.add(ogrupa);
				}
				grupa = t;
				t = rs.getString("PGI_OZNAKA");
				if (!t.equals(podgrupa)) {
					opodgrupa = new DefaultMutableTreeNode(rs.getString("PGI_NAZIV_PODGRUPE"));
					ogrupa.add(opodgrupa);
					podgrupa = t;
				}
				t = rs.getString("IZV_OZNAKA");
				if (!t.equals(izvestaj)) {
					if (oizvestaj != null)
						izvestaji.add(oizvestaj);
					oizvestaj = new MIzvestaj(rs.getString("IZV_NAZIV_IZVESTAJA"), podsistem, grupa, podgrupa, t);
					opodgrupa.add(oizvestaj);
					izvestaj = t;
				}
				// uzimamo IPA_BROJ, IPA_LABELA, IPA_TABELA, IPA_POLJE
				MSingleMetaD msmd = MDBManager.getLastInstance().getTableMetaData(rs.getString("IPA_TABELA"))
						.getMetaD(rs.getString("IPA_POLJE"));
				msmd.setTableName(rs.getString("IPA_TABELA"));
				oizvestaj.addParametar(msmd, rs.getString("IPA_LABELA"));
				// t = rs.getString("PGI_OZNAKA");

				// dodajemo parametar
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<MIzvestaj> getIzvestaji() {
		return izvestaji;
	}

}
