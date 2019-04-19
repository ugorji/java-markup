/* <<< COPYRIGHT START >>>
 * Copyright 2006-Present OxygenSoftwareLibrary.com
 * Licensed under the GNU Lesser General Public License.
 * http://www.gnu.org/licenses/lgpl.html
 *
 * @author: Ugorji Nwoke
 * <<< COPYRIGHT END >>>
 */

package net.ugorji.oxygen.markup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class MarkupMacroParameters implements Serializable {
  private String macroName;
  private String content;
  private Map params = new HashMap();
  private List arglist = new ArrayList();
  private int length;
  private String paramStr;
  private MarkupRenderEngine engine;

  public MarkupMacroParameters(
      MarkupRenderEngine engine0, String macroName0, String paramStr0, String content0) {
    macroName = macroName0;
    content = content0;
    paramStr = paramStr0;
    engine = engine0;
    initmap();
  }

  public String getMacroName() {
    return macroName;
  }

  public String getArgsAsString() {
    return paramStr;
  }

  public int getLength() {
    return length;
  }

  public String getContent() {
    return content;
  }

  public String get(int idx) {
    return (String) arglist.get(idx);
  }

  public String get(String key) {
    return (String) params.get(key);
  }

  public Map getParams() {
    return params;
  }

  public List getArgList() {
    return arglist;
  }

  public MarkupRenderEngine getMarkupRenderEngine() {
    return engine;
  }

  public String toString() {
    return toString(false);
  }

  public String toString(boolean oneLineOnly) {
    StringBuffer buf = new StringBuffer().append("{").append(macroName);
    if (paramStr != null) {
      buf.append(":").append(paramStr);
    }
    if (content == null) {
      buf.append("/}");
    } else {
      buf.append("}");
      if (oneLineOnly) {
        buf.append("...");
      } else {
        buf.append(content);
      }
      buf.append("{/").append(macroName).append("}");
    }
    return buf.toString();
  }

  private void initmap() {
    if (paramStr == null) {
      return;
    }
    StringTokenizer st = new StringTokenizer(paramStr, "|");
    int i = -1;
    while (st.hasMoreTokens()) {
      i++;
      String value = st.nextToken();
      int index = -1;
      arglist.add(value);
      if ((index = value.indexOf("=")) != -1) {
        String key = value.substring(0, index);
        value = value.substring(index + 1);
        params.put(key, value);
      } else {
        String key = String.valueOf(i);
        params.put(key, value);
      }
    }
    length = i + 1;
  }
}
