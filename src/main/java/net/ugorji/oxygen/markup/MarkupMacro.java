/* <<< COPYRIGHT START >>>
 * Copyright 2006-Present OxygenSoftwareLibrary.com
 * Licensed under the GNU Lesser General Public License.
 * http://www.gnu.org/licenses/lgpl.html
 *
 * @author: Ugorji Nwoke
 * <<< COPYRIGHT END >>>
 */

package net.ugorji.oxygen.markup;

import java.io.Writer;
import net.ugorji.oxygen.util.Closeable;

public interface MarkupMacro extends Closeable {
  public void execute(Writer writer, MarkupRenderContext rc, MarkupMacroParameters params)
      throws Exception;
}
