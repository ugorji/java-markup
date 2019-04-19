/* <<< COPYRIGHT START >>>
 * Copyright 2006-Present OxygenSoftwareLibrary.com
 * Licensed under the GNU Lesser General Public License.
 * http://www.gnu.org/licenses/lgpl.html
 *
 * @author: Ugorji Nwoke
 * <<< COPYRIGHT END >>>
 */

package net.ugorji.oxygen.markup.macros;

import java.io.Writer;
import net.ugorji.oxygen.markup.MarkupConstants;
import net.ugorji.oxygen.markup.MarkupMacroParameters;
import net.ugorji.oxygen.markup.MarkupRenderContext;
import net.ugorji.oxygen.markup.MarkupUtils;

public class AsisMacro extends GenericMarkupMacro {
  public void doExecute(Writer writer, MarkupRenderContext rc, MarkupMacroParameters params)
      throws Exception {
    if (MarkupUtils.getBooleanFromRenderContext(rc, MarkupConstants.AS_IS_SUPPORTED_KEY, true)) {
      writer.write(params.getContent());
    } else {
      writer.write((i18n().str("general.content_unavailable")));
    }
  }
}
