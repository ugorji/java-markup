/* <<< COPYRIGHT START >>>
 * Copyright 2006-Present OxygenSoftwareLibrary.com
 * Licensed under the GNU Lesser General Public License.
 * http://www.gnu.org/licenses/lgpl.html
 *
 * @author: Ugorji Nwoke
 * <<< COPYRIGHT END >>>
 */

package net.ugorji.oxygen.markup.macros;

import java.io.StringReader;
import java.io.Writer;
import net.ugorji.oxygen.markup.MarkupMacroParameters;
import net.ugorji.oxygen.markup.MarkupRenderContext;
import net.ugorji.oxygen.util.StringUtils;

public class QuoteMacro extends GenericMarkupMacro {
  public void doExecute(Writer writer, MarkupRenderContext rc, MarkupMacroParameters params)
      throws Exception {
    writer.write("<blockquote>");
    String subj = params.get("subject");
    if (!StringUtils.isBlank(subj)) {
      writer.write("<b><i>" + subj + "</i></b><br/>");
    }
    params.getMarkupRenderEngine().render(writer, new StringReader(params.getContent()), rc);
    writer.write("</blockquote>");
  }
}
