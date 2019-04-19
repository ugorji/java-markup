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
import net.ugorji.oxygen.util.StringUtils;

public class PreformattedMacro extends GenericMarkupMacro {

  public void doExecute(Writer writer, MarkupRenderContext rc, MarkupMacroParameters params)
      throws Exception {
    String content = params.getContent();
    StringBuffer sbuf = new StringBuffer();
    sbuf.append("<pre>");
    StringUtils.encode(content, sbuf);
    sbuf.append("</pre>");
    writer.write(sbuf.toString());
  }
}
