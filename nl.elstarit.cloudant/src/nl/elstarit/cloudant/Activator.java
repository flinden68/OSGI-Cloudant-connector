package nl.elstarit.cloudant;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {
	public static final String PLUGIN_ID = Activator.class.getPackage().getName();
	public static final boolean _debug = false;

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}
	@Override
	public void start(final BundleContext context) throws Exception {
		Activator.context = context;

	}

	@Override
	public void stop(final BundleContext context) throws Exception {
		Activator.context = null;

	}

}