/* <<< COPYRIGHT START >>>
 * Copyright 2006-Present OxygenSoftwareLibrary.com
 * Licensed under the GNU Lesser General Public License.
 * http://www.gnu.org/licenses/lgpl.html
 *
 * @author: Ugorji Nwoke
 * <<< COPYRIGHT END >>>
 */

package net.ugorji.oxygen.markup;

import java.util.Map;

/**
 * Holds information which is used to control rendering. An instance of this is passed to every
 * macro.
 *
 * @author ugorjid
 */
public interface MarkupRenderContext {
  Object get(String key);

  void set(String key, Object value);

  Map getValues();

  MarkupMacro getMacro(String command);

  boolean isEmoticonShorthand(String s);

  String getEmoticonLink(String s);

  boolean isCensoredWord(String s);

  String getCensoredWordReplacement(String s);

  boolean isReferenced(String s);

  String getContentUnavailableString();

  boolean isHTMLTagsSupported();

  boolean isShorthandKey(String s);

  String getShorthandReplacement(String s, String replacestr);
}
