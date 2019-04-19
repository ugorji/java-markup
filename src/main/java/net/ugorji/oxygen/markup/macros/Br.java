/* <<< COPYRIGHT START >>>
 * Copyright 2006-Present OxygenSoftwareLibrary.com
 * Licensed under the GNU Lesser General Public License.
 * http://www.gnu.org/licenses/lgpl.html
 *
 * @author: Ugorji Nwoke
 * <<< COPYRIGHT END >>>
 */

package net.ugorji.oxygen.markup.macros;

import java.io.PrintWriter;
import java.io.Writer;
import net.ugorji.oxygen.markup.MarkupMacroParameters;
import net.ugorji.oxygen.markup.MarkupRenderContext;

/**
 * Outputs a &lt;br%gt; Usage: {br}
 *
 * @author ugorji
 */
public class Br extends GenericMarkupMacro {
  public void doExecute(Writer writer, MarkupRenderContext rc, MarkupMacroParameters params)
      throws Exception {
    PrintWriter pw = new PrintWriter(writer);
    pw.println("<br />");
    pw.flush();
  }
}
