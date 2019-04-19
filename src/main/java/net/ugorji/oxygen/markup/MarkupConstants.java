/* <<< COPYRIGHT START >>>
 * Copyright 2006-Present OxygenSoftwareLibrary.com
 * Licensed under the GNU Lesser General Public License.
 * http://www.gnu.org/licenses/lgpl.html
 *
 * @author: Ugorji Nwoke
 * <<< COPYRIGHT END >>>
 */

package net.ugorji.oxygen.markup;

import net.ugorji.oxygen.util.OxygenConstants;

public interface MarkupConstants extends OxygenConstants {
  String RENDER_ENGINE_NAME = "oxywiki";
  // String FREE_LINK_ESCAPE_PREFIX = "`";
  String IMPLICIT_PAGE_HEADER_ANCHOR_PREFIX = "oxy";
  String ESCAPE_HTML_KEY = "net.ugorji.oxygen.wiki.html.escape";
  String FREELINK_SUPPORTED_KEY = "net.ugorji.oxygen.wiki.freelink.supported";
  String CAMEL_CASE_IS_LINK_KEY = "net.ugorji.oxygen.wiki.camelcaseword.is.link";
  String SLASH_SEPARATED_IS_LINK_KEY = "net.ugorji.oxygen.wiki.slashseparated.is.link";
  String SINGLE_PAGE_KEY = "net.ugorji.oxygen.wiki.single.page";
  String DECORATE_EXTERNAL_LINKS_KEY = "net.ugorji.oxygen.wiki.decorate.external.links";
  String AS_IS_SUPPORTED_KEY = "net.ugorji.oxygen.wiki.asis.supported";
  String CENSORED_WORD_KEY_PREFIX = "net.ugorji.oxygen.wiki.censoredword.";
  String EMOTICON_KEY_PREFIX = "net.ugorji.oxygen.wiki.emoticon.";
  String SHORTCUT_KEY_PREFIX = "net.ugorji.oxygen.wiki.extwiki.";
  // some options (affecting page view - set on the render context)
  String REAL_PAGE_VIEW_KEY = "net.ugorji.oxygen.wiki.realpageview";
  String INLINE_REVIEWS_KEY = "net.ugorji.oxygen.wiki.inlinereviews";
  String PARSER_FACTORY_CLASS_KEY = "net.ugorji.oxygen.markup.parserfactory";
}
