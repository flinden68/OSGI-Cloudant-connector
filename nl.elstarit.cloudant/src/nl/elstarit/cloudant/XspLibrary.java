package nl.elstarit.cloudant;

import com.ibm.xsp.library.AbstractXspLibrary;

public class XspLibrary extends AbstractXspLibrary {

	public static final String LIBRARY_ID = XspLibrary.class.getName();

	public XspLibrary() {
		System.out.println("Loading Cloudant Connector ("+getPluginId()+")");
	}

	@Override
	public String getLibraryId() {
		// TODO Auto-generated method stub
		return LIBRARY_ID;
	}

	@Override
	public String getPluginId() {
		return Activator.PLUGIN_ID;
	}

	@Override
	public String[] getDependencies() {
		return new String[] { "com.ibm.xsp.core.library",
				"com.ibm.xsp.extsn.library", "com.ibm.xsp.domino.library",
		"com.ibm.xsp.designer.library" };
	}

	@Override
	public String[] getXspConfigFiles() {
		final String[] files = new String[] { "META-INF/elstar.xsp-config"};

		return files;
	}

	@Override
	public String[] getFacesConfigFiles() {
		final String[] files = new String[] { "META-INF/elstar-faces-config.xml"};
		return files;
	}

}
