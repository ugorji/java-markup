/* <<< COPYRIGHT START >>>
 * Copyright 2006-Present OxygenSoftwareLibrary.com
 * Licensed under the GNU Lesser General Public License.
 * http://www.gnu.org/licenses/lgpl.html
 *
 * @author: Ugorji Nwoke
 * <<< COPYRIGHT END >>>
 */

package net.ugorji.oxygen.markup.parser;

import java.io.Reader;
import net.ugorji.oxygen.markup.MarkupParser;
import net.ugorji.oxygen.markup.MarkupParserFactory;

public class MarkupParser1Factory implements MarkupParserFactory {
  public MarkupParser newMarkupParser(Reader _r) throws Exception {
    return new MarkupParser1(_r);
  }
}
