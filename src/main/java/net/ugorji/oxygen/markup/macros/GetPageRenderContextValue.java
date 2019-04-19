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
import net.ugorji.oxygen.markup.MarkupMacroParameters;
import net.ugorji.oxygen.markup.MarkupRenderContext;

public class GetPageRenderContextValue extends GenericMarkupMacro {
  static final String PREFIX = "page.";

  public void doExecute(Writer writer, MarkupRenderContext rc, MarkupMacroParameters params)
      throws Exception {
    String s = params.getArgsAsString().trim();
    // System.out.println("GetPageRenderContextValue: " + s + ", " + rc.getValues());
    writer.write(String.valueOf(rc.get(PREFIX + s)));
  }
}
