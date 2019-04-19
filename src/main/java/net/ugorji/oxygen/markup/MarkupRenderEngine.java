/* <<< COPYRIGHT START >>>
 * Copyright 2006-Present OxygenSoftwareLibrary.com
 * Licensed under the GNU Lesser General Public License.
 * http://www.gnu.org/licenses/lgpl.html
 *
 * @author: Ugorji Nwoke
 * <<< COPYRIGHT END >>>
 */

package net.ugorji.oxygen.markup;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import net.ugorji.oxygen.util.Closeable;
import net.ugorji.oxygen.util.OxygenUtils;

/**
 * This is an encapsulation of rendering
 *
 * @author ugorjid
 */
public abstract class MarkupRenderEngine implements Closeable {
  protected String name = MarkupConstants.RENDER_ENGINE_NAME;

  public MarkupRenderEngine() {}

  public void setName(String s) {
    name = s;
  }

  public String getName() {
    return name;
  }

  public abstract void render(
      Writer out, Reader in, MarkupRenderContext context, int maxNumParagraphs) throws IOException;

  public void render(Writer out, Reader in, MarkupRenderContext context) throws Exception {
    render(out, in, context, Integer.MAX_VALUE);
  }

  protected void flushPW(PrintWriter pw) {
    if (pw != null) {
      try {
        pw.flush();
      } catch (Exception exc) {
        OxygenUtils.error(exc);
      }
    }
  }

  public void close() {}
}
