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
import java.io.StringReader;
import java.io.Writer;
import java.util.Random;
import net.ugorji.oxygen.markup.MarkupConstants;
import net.ugorji.oxygen.markup.MarkupMacroParameters;
import net.ugorji.oxygen.markup.MarkupRenderContext;

/**
 * implements an anchor
 *
 * @author ugorji
 */
public class InlineReview extends GenericMarkupMacro {
  private static final String ID_PREFIX = "oxy_inline_review_";
  private static final int MAX_RAND = Integer.MAX_VALUE / 2;

  private Random rand = new Random();

  public void doExecute(Writer writer, MarkupRenderContext rc, MarkupMacroParameters params)
      throws Exception {
    String content = params.getContent();
    boolean showInlineReviews = true;
    Boolean singlePage = (Boolean) rc.get(MarkupConstants.SINGLE_PAGE_KEY);
    PrintWriter pw = new PrintWriter(writer);
    try {
      Boolean bObj = (Boolean) rc.get(MarkupConstants.INLINE_REVIEWS_KEY);
      if (bObj != null) {
        showInlineReviews = bObj.booleanValue();
      }
      rc.set(MarkupConstants.SINGLE_PAGE_KEY, Boolean.FALSE);
      pw.println(
          "<blockquote id=\""
              + ID_PREFIX
              + rand.nextInt(MAX_RAND)
              + "\" style=\"display:"
              + ((showInlineReviews) ? "block" : "none")
              + "\" >");
      params.getMarkupRenderEngine().render(pw, new StringReader(content), rc, Integer.MAX_VALUE);
      pw.println("</blockquote>");
    } finally {
      pw.flush();
      rc.set(MarkupConstants.SINGLE_PAGE_KEY, singlePage);
    }
  }
}
