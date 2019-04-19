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
import java.util.regex.Pattern;
import net.ugorji.oxygen.markup.MarkupLink;
import net.ugorji.oxygen.markup.MarkupParser;
import net.ugorji.oxygen.markup.MarkupParserBase;
import net.ugorji.oxygen.util.StringUtils;

public class MarkupIndexingParserBase extends MarkupParserBase {

  // protected static Pattern pattern = Pattern.compile("[\\:\\-\\.\\{\\}\\(\\)]");
  protected static Pattern pattern = Pattern.compile("[\\:\\{\\}\\(\\)]");
  protected List tokenstrings = null;
  protected HashSet hs = null;
  protected MarkupParser mp;

  public MarkupIndexingParserBase(MarkupParser mp0, List _tokenstrings, HashSet _hs)
      throws Exception {
    mp = mp0;
    tokenstrings = _tokenstrings;
    hs = _hs;
  }

  public MarkupParser getMarkupParser() {
    return mp;
  }

  public void link(MarkupLink mlink, boolean doPrint) throws Exception {
    if (tokenstrings == null) return;
    tokenizeAndAdd(mlink.text);
    tokenizeAndAdd(mlink.text2);
    tokenstrings.add(mlink.urlpart);
  }

  public void word(String s, boolean isSlashSeperated, boolean doPrint) throws Exception {
    if (tokenstrings == null) return;
    String s2 = s;
    // List ll = StringUtils.tokens(s2, ":-.", true, true);
    List ll = StringUtils.tokens(s2, ":", true, true);
    tokenstrings.addAll(ll);
  }

  public void do_emoticon(String s) {}

  public void macro(String s, boolean hascontent) throws Exception {
    tokenizeAndAdd(s);
  }

  public void comment(String s) throws Exception {
    tokenizeAndAdd(s);
  }

  public void preformatted(String s) throws Exception {
    tokenizeAndAdd(s);
  }

  public void escapedWord(String s) throws Exception {
    tokenizeAndAdd(s);
  }

  private void tokenizeAndAdd(String s) throws Exception {
    if (tokenstrings == null || StringUtils.isBlank(s)) return;
    s = pattern.matcher(s).replaceAll(" ");
    List ll = StringUtils.tokens(s, " ", true, true);
    tokenstrings.addAll(ll);
  }
}

/*
  public void link(MarkupLink mlink, boolean doPrint) throws Exception {
    if(tokenstrings == null) return;
    if(!StringUtils.isBlank(mlink.text)) {
      MarkupParser mp0 = mp.newMarkupParser(new StringReader(mlink.text));
      MarkupIndexingParser wimap = new MarkupIndexingParser(mp0, rc, re, textSourceName, tokenstrings, hs);
      wimap.markupToHTML();
    }
    if(!StringUtils.isBlank(mlink.text2)) {
      MarkupParser mp0 = mp.newMarkupParser(new StringReader(mlink.text2));
      MarkupIndexingParser wimap = new MarkupIndexingParser(mp0, rc, re, textSourceName, tokenstrings, hs);
      wimap.markupToHTML();
    }
    tokenstrings.add(mlink.urlpart);
  }

*/
