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
import java.text.SimpleDateFormat;
import java.util.Date;
import net.ugorji.oxygen.markup.MarkupMacroParameters;
import net.ugorji.oxygen.markup.MarkupRenderContext;

/**
 * outputs the current time Usage is like: [[currtime "yyyy.MM.dd G 'at' HH:mm:ss z"]] or
 * [[currtime]]
 *
 * @author ugorji
 */
public class CurrentDateTime extends GenericMarkupMacro {

  public void doExecute(Writer writer, MarkupRenderContext rc, MarkupMacroParameters params)
      throws Exception {
    Date d = new Date();
    String datestr = null;
    if (params.getLength() > 1) {
      String formatstr = params.get(1);
      SimpleDateFormat sdf = new SimpleDateFormat(formatstr);
      datestr = sdf.format(d);
    } else {
      datestr = d.toString();
    }
    writer.write(datestr);
  }
}
