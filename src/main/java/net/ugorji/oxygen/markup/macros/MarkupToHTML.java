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
import net.ugorji.oxygen.markup.MarkupConstants;
import net.ugorji.oxygen.markup.MarkupMacroParameters;
import net.ugorji.oxygen.markup.MarkupRenderContext;

public class MarkupToHTML extends GenericMarkupMacro {
  public void doExecute(Writer writer, MarkupRenderContext rc, MarkupMacroParameters params)
      throws Exception {
    String content = params.getContent();
    Boolean singlePage = (Boolean) rc.get(MarkupConstants.SINGLE_PAGE_KEY);
    try {
      rc.set(MarkupConstants.SINGLE_PAGE_KEY, Boolean.FALSE);
      params
          .getMarkupRenderEngine()
          .render(writer, new StringReader(content), rc, Integer.MAX_VALUE);
    } finally {
      rc.set(MarkupConstants.SINGLE_PAGE_KEY, singlePage);
    }
  }
}
