/* <<< COPYRIGHT START >>>
 * Copyright 2006-Present OxygenSoftwareLibrary.com
 * Licensed under the GNU Lesser General Public License.
 * http://www.gnu.org/licenses/lgpl.html
 *
 * @author: Ugorji Nwoke
 * <<< COPYRIGHT END >>>
 */

package net.ugorji.oxygen.markup.indexing;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import net.ugorji.oxygen.util.OxygenUtils;

public class MarkupAnalyzer extends Analyzer {

  protected MarkupIndexingManager wim;
  protected Collection stopwords = Arrays.asList(StopAnalyzer.ENGLISH_STOP_WORDS);
  protected String textSourceName = null;
  protected List tokenstringlist = new ArrayList();

  public MarkupAnalyzer(MarkupIndexingManager manager) {
    setIndexingManager(manager);
  }

  public synchronized void setTextSourceName(String s) {
    textSourceName = s;
  }

  protected void setIndexingManager(MarkupIndexingManager manager) {
    wim = manager;
  }

  /* Always return something ... if there's an error, just catch it */
  public TokenStream tokenStream(String fieldName, Reader reader) {
    ArrayList list2 = new ArrayList();
    try {
      tokenstringlist.clear();
      // System.out.println("Getting TokenStream: textSourceName: " + textSourceName);
      MarkupIndexingParser wimap = wim.newParser(textSourceName, reader, tokenstringlist, null);
      wimap.markupToHTML();
      int indx = 0;
      for (Iterator itr = tokenstringlist.iterator(); itr.hasNext(); ) {
        String s = (String) itr.next();
        s = s.toLowerCase();
        if (stopwords.contains(s) || wimap.isCensoredWord(s)) {
          continue;
        }
        if (s.endsWith("'s")) {
          s = s.substring(0, s.length() - 2);
        }
        Token tt = new Token(s, indx, indx + s.length());
        // This is bogus. We're no longer in the context of the parser.
        // Token tt = new Token(s, wimap.token.beginColumn, wimap.token.endColumn);
        indx = indx + s.length() + 2;
        list2.add(tt);
      }
    } catch (Exception exc) {
      OxygenUtils.error("Error getting TokenStream: textSourceName: " + textSourceName, exc);
      // throw new RuntimeException(exc);
    }
    return newTokenStream(list2);
  }

  protected TokenStream newTokenStream(List list2) {
    MIMTokenStream wimts = new MIMTokenStream();
    wimts.tokenitr = list2.iterator();
    return wimts;
  }
}
