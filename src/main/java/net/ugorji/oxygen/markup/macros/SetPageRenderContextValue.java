/* <<< COPYRIGHT START >>>
 * Copyright 2006-Present OxygenSoftwareLibrary.com
 * Licensed under the GNU Lesser General Public License.
 * http://www.gnu.org/licenses/lgpl.html
 *
 * @author: Ugorji Nwoke
 * <<< COPYRIGHT END >>>
 */

package net.ugorji.oxygen.markup.macros;

import java.io.Writer;
import java.util.Iterator;
import java.util.Map;
import net.ugorji.oxygen.markup.MarkupMacroParameters;
import net.ugorji.oxygen.markup.MarkupRenderContext;

public class SetPageRenderContextValue extends GenericMarkupMacro {
  public void doExecute(Writer writer, MarkupRenderContext rc, MarkupMacroParameters params)
      throws Exception {
    Map m = params.getParams();
    // System.out.println("SetPageRenderContextValue: " + m); System.out.println(rc.getValues());
    for (Iterator itr = m.keySet().iterator(); itr.hasNext(); ) {
      String s = (String) itr.next();
      String v = (String) m.get(s);
      rc.set(GetPageRenderContextValue.PREFIX + s, v);
    }
  }
}
