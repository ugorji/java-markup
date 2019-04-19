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
import net.ugorji.oxygen.markup.MarkupConstants;
import net.ugorji.oxygen.markup.MarkupMacro;
import net.ugorji.oxygen.markup.MarkupMacroParameters;
import net.ugorji.oxygen.markup.MarkupRenderContext;
import net.ugorji.oxygen.util.FreemarkerTemplateHelper;
import net.ugorji.oxygen.util.I18n;
import net.ugorji.oxygen.util.OxyLocal;

/** @author ugorjid */
public class GenericMarkupMacro implements MarkupMacro {
  private static FreemarkerTemplateHelper thdlr;

  protected boolean onlyExecuteOnRealPageView = false;

  static I18n i18n() {
    return (I18n) OxyLocal.get(I18n.class);
  }

  /**
   * The WikiRenderContext passed has the following attributes in it:<br>
   * MarkupConstants.ESCAPE_HTML_KEY = Boolean MarkupConstants.FREELINK_SUPPORTED_KEY = Boolean
   * MarkupConstants.CAMEL_CASE_IS_LINK_KEY = Boolean MarkupConstants.SLASH_SEPARATED_IS_LINK_KEY =
   * Boolean MarkupConstants.DECORATE_EXTERNAL_LINKS_KEY = Boolean
   *
   * <p>MarkupConstants.INLINE_REVIEWS_KEY = Boolean
   *
   * <p>MarkupConstants.REAL_PAGE_VIEW_KEY = Boolean
   *
   * <p>The following may not be set (depending of if run within the context of a request or not)
   * MarkupConstants.CATEGORYENGINE_KEY = WikiCategoryEngine (optionally set)
   * MarkupConstants.PAGE_KEY = WikiProvidedObject (optionally set)
   *
   * <p>MarkupConstants.WIKI_WEB_CONTEXT_KEY = WebInteractionContext (optionally set)
   */
  public void execute(Writer writer, MarkupRenderContext rc, MarkupMacroParameters params)
      throws Exception {
    Boolean bobj = (Boolean) rc.get(MarkupConstants.REAL_PAGE_VIEW_KEY);
    boolean realPageView = (bobj != null ? bobj.booleanValue() : true);
    if (!realPageView && onlyExecuteOnRealPageView) {
      I18n i18n = i18n();
      if (i18n != null) {
        writer.write(
            "<blockquote>"
                + i18n.str("macro.not_real_page_view_message", params.toString(true))
                + "</blockquote>");
      }
    } else {
      doExecute(writer, rc, params);
    }
  }

  public void close() {}

  // protected boolean isRealPageWebView(MarkupRenderContext rc) throws Exception {
  //  WebInteractionContext req = WebLocal.getWebInteractionContext();
  //  boolean realPageView = ((Boolean)rc.get(MarkupConstants.REAL_PAGE_VIEW_KEY)).booleanValue();
  //  return (realPageView && req != null);
  // }

  protected void doExecute(Writer writer, MarkupRenderContext rc, MarkupMacroParameters params)
      throws Exception {}

  protected static FreemarkerTemplateHelper getFMTH() {
    if (thdlr == null) {
      thdlr = new FreemarkerTemplateHelper(new String[] {"/net/ugorji/oxygen/markup/macros"}, null);
    }
    return thdlr;
  }
}
