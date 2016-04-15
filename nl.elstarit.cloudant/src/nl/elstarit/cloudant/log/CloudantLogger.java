/**
 *
 */
package nl.elstarit.cloudant.log;

import com.ibm.commons.log.Log;
import com.ibm.commons.log.LogMgr;

/**
 * @author frankvanderlinden
 *
 */
public class CloudantLogger extends Log {
	public static LogMgr CLOUDANT  = load("nl.elstarit.cloudant");
}
