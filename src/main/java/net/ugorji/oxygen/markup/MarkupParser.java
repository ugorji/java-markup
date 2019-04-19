/* <<< COPYRIGHT START >>>
 * Copyright 2006-Present OxygenSoftwareLibrary.com
 * Licensed under the GNU Lesser General Public License.
 * http://www.gnu.org/licenses/lgpl.html
 *
 * @author: Ugorji Nwoke
 * <<< COPYRIGHT END >>>
 */

package net.ugorji.oxygen.markup;

public interface MarkupParser extends MarkupParserFactory {
  void markupToHTML() throws Exception;

  void setMarkupParserBase(MarkupParserBase mbase) throws Exception;

  MarkupParserBase getMarkupParserBase() throws Exception;
}
