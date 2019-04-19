/* <<< COPYRIGHT START >>>
 * Copyright 2006-Present OxygenSoftwareLibrary.com
 * Licensed under the GNU Lesser General Public License.
 * http://www.gnu.org/licenses/lgpl.html
 *
 * @author: Ugorji Nwoke
 * <<< COPYRIGHT END >>>
 */

package net.ugorji.oxygen.markup.indexing;

import java.util.HashSet;
import java.util.List;
import net.ugorji.oxygen.markup.MarkupParser;
import net.ugorji.oxygen.markup.MarkupRenderContext;
import net.ugorji.oxygen.markup.MarkupRenderEngine;

public class MarkupIndexingParser {
  private MarkupIndexingParserBase mbase;
  private MarkupParser mp;

  public MarkupIndexingParser(
      MarkupParser mp0,
      MarkupRenderContext rc0,
      MarkupRenderEngine re0,
      String txtsrc0,
      List _tokenstrings,
      HashSet _hs)
      throws Exception {
    mp = mp0;
    mbase = new MarkupIndexingParserBase(mp, _tokenstrings, _hs);
    mbase.setRenderContext(rc0);
    mbase.setRenderEngine(re0);
    mbase.setTextSourceName(txtsrc0);

    mp.setMarkupParserBase(mbase);
  }

  public MarkupIndexingParser(MarkupParser mp0, MarkupIndexingParserBase mbase0) throws Exception {
    mp = mp0;
    mbase = mbase0;
    mp.setMarkupParserBase(mbase);
  }

  public void markupToHTML() throws Exception {
    mp.markupToHTML();
  }

  boolean isCensoredWord(String s) {
    return mbase.getRenderContext().isCensoredWord(s);
  }
}
