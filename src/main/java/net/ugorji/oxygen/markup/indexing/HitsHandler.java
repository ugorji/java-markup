/* <<< COPYRIGHT START >>>
 * Copyright 2006-Present OxygenSoftwareLibrary.com
 * Licensed under the GNU Lesser General Public License.
 * http://www.gnu.org/licenses/lgpl.html
 *
 * @author: Ugorji Nwoke
 * <<< COPYRIGHT END >>>
 */

/** */
package net.ugorji.oxygen.markup.indexing;

import org.apache.lucene.search.Hits;

public interface HitsHandler {
  public void handleHits(Hits hits) throws Exception;
}
