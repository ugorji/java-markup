/* <<< COPYRIGHT START >>>
 * Copyright 2006-Present OxygenSoftwareLibrary.com
 * Licensed under the GNU Lesser General Public License.
 * http://www.gnu.org/licenses/lgpl.html
 *
 * @author: Ugorji Nwoke
 * <<< COPYRIGHT END >>>
 */

package net.ugorji.oxygen.markup;

import java.util.*;
import net.ugorji.oxygen.util.Closeable;
import net.ugorji.oxygen.util.OxygenUtils;

public class CensoredWordManager implements Closeable {

  private static final String UNCENSORED = "NULL";

  private Properties censoredWords;
  private String[] arrayOfCensoredWords;

  public CensoredWordManager(Properties p) throws Exception {
    censoredWords = new Properties();
    OxygenUtils.extractProps(p, censoredWords, MarkupConstants.CENSORED_WORD_KEY_PREFIX, true);
    List l = new ArrayList();
    for (Enumeration enum0 = censoredWords.propertyNames(); enum0.hasMoreElements(); ) {
      String s = (String) enum0.nextElement();
      String k = censoredWords.getProperty(s);
      if (!UNCENSORED.equals(k)) {
        l.add(s);
      }
    }
    arrayOfCensoredWords = (String[]) l.toArray(new String[0]);
    Arrays.sort(arrayOfCensoredWords, String.CASE_INSENSITIVE_ORDER);
  }

  public boolean isCensoredWord(String s) {
    return (Arrays.binarySearch(arrayOfCensoredWords, s, String.CASE_INSENSITIVE_ORDER) > -1);
  }

  public String getCensoredWordReplacement(String s) {
    return (String) censoredWords.get(s.toLowerCase());
  }

  public void close() {
    censoredWords.clear();
    arrayOfCensoredWords = null;
  }
}
