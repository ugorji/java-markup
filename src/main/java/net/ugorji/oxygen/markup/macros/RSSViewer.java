/* <<< COPYRIGHT START >>>
 * Copyright 2006-Present OxygenSoftwareLibrary.com
 * Licensed under the GNU Lesser General Public License.
 * http://www.gnu.org/licenses/lgpl.html
 *
 * @author: Ugorji Nwoke
 * <<< COPYRIGHT END >>>
 */

package net.ugorji.oxygen.markup.macros;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import net.ugorji.oxygen.markup.MarkupMacroParameters;
import net.ugorji.oxygen.markup.MarkupRenderContext;
import net.ugorji.oxygen.util.CloseUtils;
import net.ugorji.oxygen.util.OxygenUtils;

public class RSSViewer extends GenericMarkupMacro {
  private static String HEADER_PREFIX_KEY = "macro.rss.header_prefix";

  {
    onlyExecuteOnRealPageView = true;
  }

  public void doExecute(Writer writer, MarkupRenderContext rc, MarkupMacroParameters params)
      throws Exception {
    InputStream is = null;
    try {
      Map model = new HashMap();
      String s = null;
      int maxsize = Integer.MAX_VALUE;
      s = params.get("maxsize");
      if (s != null) {
        maxsize = Integer.parseInt(s);
      }

      s = "RSS Feed: ";
      try {
        s = i18n().str(HEADER_PREFIX_KEY);
      } catch (Exception exc) {
      } // ignore
      model.put("rss_id_string", s);

      s = params.get("url");
      URL url = new URL(s);
      is = OxygenUtils.getInputStream(url);
      SyndFeedInput input = new SyndFeedInput();
      SyndFeed feed = input.build(new InputStreamReader(is));
      model.put("rss", feed);
      model.put("maxsize", new Integer(maxsize));

      getFMTH().write("rssviewer.html", model, writer);
    } finally {
      CloseUtils.close(is);
    }
  }
}
