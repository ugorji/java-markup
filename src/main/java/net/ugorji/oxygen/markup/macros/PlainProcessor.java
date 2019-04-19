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

/**
 * Takes some text, and writes it out in plain
 *
 * @author ugorji
 */
public class PlainProcessor extends GenericMarkupMacro {
  public void doExecute(Writer writer, MarkupRenderContext rc, MarkupMacroParameters params)
      throws Exception {
    // RenderContext rc = params.getContext();
    // WikiCategoryEngine engine = (WikiCategoryEngine)rc.get(MarkupConstants.CATEGORYENGINE_KEY);
    String s = params.getContent();
    s = StringUtils.toHTMLEscape(s, true, true);
    writer.write(s);
    writer.flush();
  }
}
