/* <<< COPYRIGHT START >>>
 * Copyright 2006-Present OxygenSoftwareLibrary.com
 * Licensed under the GNU Lesser General Public License.
 * http://www.gnu.org/licenses/lgpl.html
 *
 * @author: Ugorji Nwoke
 * <<< COPYRIGHT END >>>
 */

package net.ugorji.oxygen.markup;

import java.util.Properties;
import net.ugorji.oxygen.util.Closeable;
import net.ugorji.oxygen.util.OxygenUtils;
import net.ugorji.oxygen.util.StringUtils;

/**
 * This class supports the resource bundle style. i.e. a shorthand alias can be like "a man {0} was
 * {1} here {0}" At replacement time, assume arr[] is the replacement string array, we will replace
 * {0} with arr[0], {1} with arr[1], etc
 */
public class ShorthandManager implements Closeable {
  private Properties extwikis;

  public ShorthandManager(Properties p0) throws Exception {
    Properties p1 = new Properties();
    OxygenUtils.extractProps(p0, p1, MarkupConstants.SHORTCUT_KEY_PREFIX, true);
    extwikis = OxygenUtils.changeKeysToLowerCase(p1);
  }

  public Properties getAll() {
    return new Properties(extwikis);
  }

  public boolean hasKey(String name) {
    String val = extwikis.getProperty(name.toLowerCase());
    return (val != null);
  }

  public String getEvaluatedString(String name, String replacestr) {
    return getEvaluatedString(name, new String[] {replacestr});
  }

  public String getEvaluatedString(String name, String[] replacestr) {
    String val = extwikis.getProperty(name.toLowerCase());
    if (val != null) {
      val = StringUtils.replaceRBStyle(val, replacestr);
    }
    return val;
  }

  public void setShorthand(String s, String v) {
    extwikis.setProperty(s.toLowerCase(), v);
  }

  public void close() {
    extwikis.clear();
  }
}
