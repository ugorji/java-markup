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
import net.ugorji.oxygen.markup.MarkupConstants;
import net.ugorji.oxygen.markup.MarkupMacroParameters;
import net.ugorji.oxygen.markup.MarkupRenderContext;

public class PIMacro extends GenericMarkupMacro {
  public static final String FREE_LINK = "freelink";
  public static final String ESCAPE_HTML = "escapehtml";
  public static final String CAMEL_CASE = "camelcase";
  public static final String SLASH_SEPARATED = "slashseparated";
  public static final String DECORATE_EXTERNAL_LINKS = "decoratelink";
  public static final String SINGLE_PAGE = "singlepage";

  public void doExecute(Writer writer, MarkupRenderContext rc, MarkupMacroParameters params)
      throws Exception {
    Map m = params.getParams();
    for (Iterator itr = m.entrySet().iterator(); itr.hasNext(); ) {
      Map.Entry me = (Map.Entry) itr.next();
      String s = (String) me.getKey();
      String sval = ((String) me.getValue()).trim();
      if (s.equals(FREE_LINK)) {
        rc.set(MarkupConstants.FREELINK_SUPPORTED_KEY, Boolean.valueOf("true".equals(sval)));
      } else if (s.equals(ESCAPE_HTML)) {
        if (rc.isHTMLTagsSupported()) {
          rc.set(MarkupConstants.ESCAPE_HTML_KEY, Boolean.valueOf("true".equals(sval)));
        }
      } else if (s.equals(CAMEL_CASE)) {
        rc.set(MarkupConstants.CAMEL_CASE_IS_LINK_KEY, Boolean.valueOf("true".equals(sval)));
      } else if (s.equals(SLASH_SEPARATED)) {
        rc.set(MarkupConstants.SLASH_SEPARATED_IS_LINK_KEY, Boolean.valueOf("true".equals(sval)));
      } else if (s.equals(DECORATE_EXTERNAL_LINKS)) {
        rc.set(MarkupConstants.DECORATE_EXTERNAL_LINKS_KEY, Boolean.valueOf("true".equals(sval)));
      } else if (s.equals(SINGLE_PAGE)) {
        rc.set(MarkupConstants.SINGLE_PAGE_KEY, Boolean.valueOf("true".equals(sval)));
      }
    }
  }
}
