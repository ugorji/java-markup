/* <<< COPYRIGHT START >>>
 * Copyright 2006-Present OxygenSoftwareLibrary.com
 * Licensed under the GNU Lesser General Public License.
 * http://www.gnu.org/licenses/lgpl.html
 *
 * @author: Ugorji Nwoke
 * <<< COPYRIGHT END >>>
 */

package net.ugorji.oxygen.markup;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Properties;
import net.ugorji.oxygen.util.Closeable;
import net.ugorji.oxygen.util.OxygenUtils;
import net.ugorji.oxygen.util.StringUtils;

public class EmoticonsManager implements Closeable {
  private Properties emoticons;
  private String[] arrayOfEmoticons;

  public EmoticonsManager(Properties p) throws Exception {
    Properties p2 = new Properties();
    OxygenUtils.extractProps(p, p2, MarkupConstants.EMOTICON_KEY_PREFIX, true);
    // clean up emoticons
    emoticons = new Properties();
    for (Enumeration enum0 = p2.propertyNames(); enum0.hasMoreElements(); ) {
      String s = (String) enum0.nextElement();
      String v = p2.getProperty(s);
      v = StringUtils.replacePropertyReferencesInString(v, p);
      emoticons.setProperty(s, v);
    }
    arrayOfEmoticons = (String[]) emoticons.keySet().toArray(new String[0]);
    Arrays.sort(arrayOfEmoticons);
  }

  public Properties getEmoticons() {
    return new Properties(emoticons);
  }

  public boolean isEmoticonShorthand(String s) {
    return (Arrays.binarySearch(arrayOfEmoticons, s) > -1);
  }

  public String getEmoticonLink(String name) {
    String s = emoticons.getProperty(name);
    if (StringUtils.isBlank(s)) {
      return name;
    }
    return s;
  }

  public void close() {
    emoticons.clear();
  }
}
