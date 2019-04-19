/* <<< COPYRIGHT START >>>
 * Copyright 2006-Present OxygenSoftwareLibrary.com
 * Licensed under the GNU Lesser General Public License.
 * http://www.gnu.org/licenses/lgpl.html
 *
 * @author: Ugorji Nwoke
 * <<< COPYRIGHT END >>>
 */

package net.ugorji.oxygen.markup;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DefaultMarkupRenderContext implements MarkupRenderContext {
  private Map values = new HashMap();
  private Properties props = null;
  private CensoredWordManager censoredMgr;
  private EmoticonsManager emoticonsMgr;
  private ShorthandManager shorthandMgr;

  public DefaultMarkupRenderContext() {}

  public DefaultMarkupRenderContext(
      Properties p, CensoredWordManager cm, EmoticonsManager em, ShorthandManager sm) {
    props = p;
    censoredMgr = cm;
    emoticonsMgr = em;
    shorthandMgr = sm;
  }

  public Properties getProps() {
    return props;
  }

  public void setProps(Properties props) {
    this.props = props;
  }

  public ShorthandManager getShorthandMgr() {
    return shorthandMgr;
  }

  public void setShorthandMgr(ShorthandManager shorthandMgr) {
    this.shorthandMgr = shorthandMgr;
  }

  public CensoredWordManager getCensoredMgr() {
    return censoredMgr;
  }

  public void setCensoredMgr(CensoredWordManager censoredMgr) {
    this.censoredMgr = censoredMgr;
  }

  public EmoticonsManager getEmoticonsMgr() {
    return emoticonsMgr;
  }

  public void setEmoticonsMgr(EmoticonsManager emoticonsMgr) {
    this.emoticonsMgr = emoticonsMgr;
  }

  public Object get(String key) {
    return values.get(key);
  }

  public void set(String key, Object value) {
    values.put(key, value);
  }

  public Map getValues() {
    return new HashMap(values);
  }

  public boolean isEmoticonShorthand(String s) {
    if (emoticonsMgr == null) {
      return false;
    }
    return emoticonsMgr.isEmoticonShorthand(s);
  }

  public String getEmoticonLink(String s) {
    if (emoticonsMgr == null) {
      return s;
    }
    return emoticonsMgr.getEmoticonLink(s);
  }

  public boolean isCensoredWord(String s) {
    if (censoredMgr == null) {
      return false;
    }
    return censoredMgr.isCensoredWord(s);
  }

  public String getCensoredWordReplacement(String s) {
    if (censoredMgr == null) {
      return "****";
    }
    return censoredMgr.getCensoredWordReplacement(s);
  }

  public String getContentUnavailableString() {
    return "<pre>CONTENT IS UNAVAILABLE</pre>";
  }

  public MarkupMacro getMacro(String command) {
    return null;
  }

  public boolean isReferenced(String s) {
    return false;
  }

  public boolean isHTMLTagsSupported() {
    return true;
  }

  public boolean isShorthandKey(String s) {
    if (shorthandMgr == null) {
      return false;
    }
    return shorthandMgr.hasKey(s);
  }

  public String getShorthandReplacement(String s, String replacestr) {
    if (shorthandMgr == null) {
      return s + ":" + replacestr;
    }
    return shorthandMgr.getEvaluatedString(s, replacestr);
  }
}
