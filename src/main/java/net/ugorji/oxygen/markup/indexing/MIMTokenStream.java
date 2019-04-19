/* <<< COPYRIGHT START >>>
 * Copyright 2006-Present OxygenSoftwareLibrary.com
 * Licensed under the GNU Lesser General Public License.
 * http://www.gnu.org/licenses/lgpl.html
 *
 * @author: Ugorji Nwoke
 * <<< COPYRIGHT END >>>
 */

package net.ugorji.oxygen.markup.indexing;

import java.util.Iterator;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;

class MIMTokenStream extends TokenStream {

  Iterator tokenitr;

  public void close() {
    tokenitr = null;
  }

  public Token next() {
    while (tokenitr != null && tokenitr.hasNext()) {
      return ((Token) tokenitr.next());
    }
    tokenitr = null;
    return null;
  }
}
