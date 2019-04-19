/* <<< COPYRIGHT START >>>
 * Copyright 2006-Present OxygenSoftwareLibrary.com
 * Licensed under the GNU Lesser General Public License.
 * http://www.gnu.org/licenses/lgpl.html
 *
 * @author: Ugorji Nwoke
 * <<< COPYRIGHT END >>>
 */

package net.ugorji.oxygen.markup;

import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * Collection of shared utility functions
 *
 * @author ugorji
 */
public class MarkupUtils {

  public static final Pattern wikiWordPattern;
  public static final Pattern phrasePattern;
  public static final Pattern emailPattern;
  public static final Pattern imagePattern;
  public static final Pattern absoluteImageURLPattern;
  public static final Pattern genericURLPattern;
  public static final Pattern macroPatternNoContent;
  public static final Pattern macroPatternWithContent;

  public static final Pattern spacePattern;
  public static final Pattern punctPattern;

  public static final Random rand = new Random();
  public static final int RAND_MAX = Integer.MAX_VALUE / 2;

  static {
    wikiWordPattern = Pattern.compile("([A-Z]+[a-z_0-9]+)([A-Z][a-z_0-9]+)+");
    phrasePattern = Pattern.compile("[\\w\\.\\-]+([ ]+[\\w\\.\\-]+)*");
    emailPattern = Pattern.compile("[\\w\\.\\-]+@[\\w\\.\\-]+");
    macroPatternNoContent = Pattern.compile("\\{([^:}]+)(?::([^\\}]*))?\\/}", Pattern.DOTALL);
    macroPatternWithContent =
        Pattern.compile("\\{([^:}]+)(?::([^\\}]*))?\\}((.*?)\\{/\\1\\})?", Pattern.DOTALL);

    imagePattern =
        Pattern.compile(
            ".+?(/|\\\\).+?(.jpg|.jpeg|.png|.gif|.bmp)", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
    genericURLPattern =
        Pattern.compile(
            "(http|ftp|news|nntp|telnet|file)(s)?(://).*",
            Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
    absoluteImageURLPattern =
        Pattern.compile(
            "(\\w+://.+?/|/).+?(.jpg|.jpeg|.png|.gif|.bmp)",
            Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
    spacePattern = Pattern.compile("\\s+\\S");
    punctPattern = Pattern.compile("\\p{Punct}");
  }

  /**
   * return true if the string is a camelCase word
   *
   * @param s
   * @return
   */
  public static boolean isCamelCaseWord(String s) {
    return wikiWordPattern.matcher(s).matches();
  }

  /**
   * Takes a string of words, and returns a camel case representation suitable for a link
   *
   * @param pagerep
   * @return
   */
  public static String toCamelCase(String pagerep) {
    String pagerep2 = pagerep;
    if (phrasePattern.matcher(pagerep).matches()) {
      StringBuffer buf = new StringBuffer();
      StringTokenizer stz = new StringTokenizer(pagerep);
      if (stz.countTokens() > 1) {
        while (stz.hasMoreTokens()) {
          String s = stz.nextToken();
          buf.append(Character.toUpperCase(s.charAt(0)));
          buf.append(s.substring(1));
        }
        pagerep2 = buf.toString();
      }
    }
    return pagerep2;
  }

  public static boolean getBooleanFromRenderContext(MarkupRenderContext rc, String s, boolean b) {
    Boolean bObj = (Boolean) rc.get(s);
    if (bObj != null) {
      // System.out.println("For : " + s + " returning: " + bObj);
      return bObj.booleanValue();
    }
    return b;
  }
}
