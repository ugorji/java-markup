/* <<< COPYRIGHT START >>>
 * Copyright 2006-Present OxygenSoftwareLibrary.com
 * Licensed under the GNU Lesser General Public License.
 * http://www.gnu.org/licenses/lgpl.html
 *
 * @author: Ugorji Nwoke
 * <<< COPYRIGHT END >>>
 */

package net.ugorji.oxygen.markup;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.ugorji.oxygen.util.NullWriter;
import net.ugorji.oxygen.util.StringUtils;

/**
 * Handler class for our markup parser, that has callbacks when different tokens are identified
 *
 * @author ugorji
 */
public class MarkupParserBase {
  public static final Object OL_TYPE = new Object();
  public static final Object UL_TYPE = new Object();
  public static final String linesep = StringUtils.LINE_SEP;
  public static final PrintWriter NULL_PW = new PrintWriter(new NullWriter());

  protected LinkedList listStack = new LinkedList();
  protected PrintWriter pw = NULL_PW;
  protected MarkupRenderContext rc;
  protected MarkupRenderEngine re;
  protected int headerNumber = 0;
  protected int currentHeaderLevel = 0;

  protected String textSourceName;
  protected StringBuffer membuf = new StringBuffer();
  protected boolean addToMemoryBuffer = false;

  protected int maxNumParagraphs = Integer.MAX_VALUE;

  private int numParagraphs = 0;

  public MarkupParserBase() {}

  public void setRenderContext(MarkupRenderContext _rc) throws Exception {
    rc = _rc;
  }

  public MarkupRenderContext getRenderContext() {
    return rc;
  }

  public void setRenderEngine(MarkupRenderEngine _re) throws Exception {
    re = _re;
  }

  public MarkupRenderEngine getRenderEngine() {
    return re;
  }

  public void setWriter(PrintWriter _pw) throws Exception {
    pw = _pw;
  }

  public void setTextSourceName(String _s) {
    textSourceName = _s;
  }

  public String getTextSourceName() {
    return textSourceName;
  }

  public Pattern getImagePattern() {
    return MarkupUtils.imagePattern;
  }

  public PrintWriter getWriter() {
    return pw;
  }

  public int getMaxNumParagraphs() {
    return maxNumParagraphs;
  }

  public void setMaxNumParagraphs(int maxNumParagraphs0) {
    maxNumParagraphs = maxNumParagraphs0;
  }

  public void flush() {
    pw.flush();
  }

  public void macro(String s, boolean hascontent) throws Exception {
    Matcher result = null;
    if (hascontent) {
      result = MarkupUtils.macroPatternWithContent.matcher(s);
    } else {
      result = MarkupUtils.macroPatternNoContent.matcher(s);
    }
    result.matches();
    String command = result.group(1).trim();
    String paramstr = result.group(2);
    String content = "";
    if (hascontent) {
      String scon = result.group(4);
      if (scon != null && scon.length() > 0) {
        content = scon;
      }
    }

    MarkupMacroParameters mParams = new MarkupMacroParameters(re, command, paramstr, content);
    macro(result.group(0), command, mParams);
  }

  public boolean isTableHeader(String s) throws Exception {
    s = s.trim();
    return (s.startsWith("-") || s.endsWith("-"));
  }

  public int getTableColspan(String s) throws Exception {
    int numBars = 0;
    s = s.trim();
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '|') {
        numBars++;
      }
    }
    return (numBars / 2);
  }

  public void lt() throws Exception {
    // MarkupUtils.debug("MarkupParserBase.br");
    if (escapeHTML()) {
      write("&lt;");
    } else {
      write("<");
    }
  }

  public void gt() throws Exception {
    // MarkupUtils.debug("MarkupParserBase.br");
    if (escapeHTML()) {
      write("&gt;");
    } else {
      write(">");
    }
  }

  public void url(String s) throws Exception {
    word(s, false, true);
  }

  public void asis(String s) throws Exception {
    if (asisSupported()) {
      write(s);
    } else {
      write(rc.getContentUnavailableString());
    }
  }

  public void emailaddress(String s) throws Exception {
    // MarkupUtils.debug("MarkupParserBase.emailaddress: " + s);

    // Now, the escaped word is handled at the macro level (so no need to do it here)
    // if(s.startsWith(MarkupConstants.FREE_LINK_ESCAPE_PREFIX)) {
    //  write(s.substring(1));
    // } else {
    //  writeln("<a href=\"mailto:" + s + "\" >" + s + "</a>");
    // }
    writeln("<a href=\"mailto:" + s + "\" >" + s + "</a>");
  }

  public void list(String s, Object type) {
    s = strippedBeginningNewLines(s);

    int indentlevel = listIndentLevel(s);
    int stacksize = listStack.size();
    if (indentlevel == stacksize) {
      // no op
    } else if (indentlevel < stacksize) {
      int delta = stacksize - indentlevel;
      for (int i = 0; i < delta; i++) {
        deregisterList();
      }
    } else if (indentlevel > stacksize) {
      registerList(type);
    }
    // writeln();
    write("<li>");
  }

  // if "* abc", thatz 1st level indent
  // if " * abc", thatz 1st level indent
  // if "  * abc ", thatz 2nd level indent
  // if "   * abc ", thatz 2nd level indent
  private int listIndentLevel(String s) {
    int demarc = s.indexOf("*");
    if (demarc == -1) {
      demarc = s.indexOf("#");
    }
    demarc = demarc + 1;
    if ((demarc % 2) == 1) {
      demarc++;
    }
    return (demarc / 2);
  }

  private void registerList(Object type) {
    listStack.addLast(type);
    if (type == UL_TYPE) {
      writeln();
      writeln("<ul>");
    } else {
      writeln();
      writeln("<ol>");
    }
  }

  private void deregisterList() {
    if (listStack.size() == 0) {
      return;
    }
    Object o = listStack.removeLast();
    if (o == UL_TYPE) {
      writeln();
      writeln("</ul>");
    } else {
      writeln();
      writeln("</ol>");
    }
  }

  public void deregisterListFully() {
    int stacksize = listStack.size();
    for (int i = 0; i < stacksize; i++) {
      deregisterList();
    }
  }

  public void registerParagraphReceived() throws Exception {
    numParagraphs++;
    if (numParagraphs >= maxNumParagraphs) {
      throw new MaxParagraphsExceededSignal();
    }
  }

  public void macro(String fullstring, String command, MarkupMacroParameters mParams)
      throws Exception {
    if (rc.getMacro(command) != null) {
      rc.getMacro(command).execute(pw, rc, mParams);
    } else if (RadeoxHelper.isMacroAvailable(command)) {
      RadeoxHelper.handleMacro(command, re, rc, pw, mParams);
    } else {
      write(fullstring);
    }
  }

  public void do_emoticon(String s) {
    if (rc.isEmoticonShorthand(s)) {
      writeln(
          " <img id=\"mpb_emoticon_"
              + MarkupUtils.rand.nextInt(MarkupUtils.RAND_MAX)
              + "\" src=\""
              + rc.getEmoticonLink(s)
              + "\" alt=\""
              + s
              + "\" /> ");
    } else {
      writeln(s);
    }
  }

  public void link(MarkupLink mlink, boolean doPrint) throws Exception {
    StringBuffer buf = new StringBuffer();
    if (mlink.image) {
      buf.append(
              "<img id=\"mpb_link_" + MarkupUtils.rand.nextInt(MarkupUtils.RAND_MAX) + "\" src=\"")
          .append(getURL(mlink))
          .append("\" alt=\"")
          .append(mlink.text)
          .append("\" ")
          .append(StringUtils.nonNullString(mlink.text2))
          .append(" />");
    } else {
      buf.append("<a href=\"")
          .append(getURL(mlink))
          .append("\" >")
          .append(mlink.text)
          .append("</a>");
    }
    write(buf.toString());
  }

  public void preformatted(String s) throws Exception {
    s = StringUtils.toHTMLEscape(s, false, false);
    write("<pre>");
    write(s);
    write("</pre>");
  }

  // usually, plain text is non-words, so we don't care about them.
  public void plaintext(String s) throws Exception {
    write(s);
  }

  public void variable(String s) throws Exception {
    write("${");
    write(s);
    write("}");
  }

  public void comment(String s) throws Exception {
    write("<!-- ");
    write(s.trim());
    writeln(" -->");
  }

  public void word(String s, boolean isSlashSeperated, boolean doPrint) throws Exception {
    write(s);
  }

  public void escapedWord(String s) throws Exception {
    write(s);
  }

  public void headerstart(int level) {
    headerNumber++;
    currentHeaderLevel = level;
    if (singlePage()) {
      writeln(
          "<a name=\""
              + MarkupConstants.IMPLICIT_PAGE_HEADER_ANCHOR_PREFIX
              + headerNumber
              + "\" />");
    }
    write("<h" + currentHeaderLevel + ">");
    // flush();
  }

  public void headerend() {
    write("</h" + currentHeaderLevel + ">");
    // write("<span class=\"wikiheadertoplink\"><a href=\"#top\">[top]</a></span>");
    writeln();
    // flush();
  }

  public void write(String s) {
    pw.print(s);
    if (addToMemoryBuffer) {
      membuf.append(s);
    }
  }

  public void writeln(String s) {
    write(s);
    writeln();
  }

  public void writeln() {
    write(linesep);
  }

  public void pagestart() {
    // a no-op for now. There is usually a template around things, so writing top is bad
    // if(singlePage()) {
    //  writeln("<a name=\"top\"/>");
    // }
  }

  public boolean escapeHTML() {
    return _bool(MarkupConstants.ESCAPE_HTML_KEY, true);
  }

  public boolean freeLinkSupported() {
    return _bool(MarkupConstants.FREELINK_SUPPORTED_KEY, true);
  }

  public boolean camelCaseWordIsLink() {
    return _bool(MarkupConstants.CAMEL_CASE_IS_LINK_KEY, true);
  }

  public boolean slashSeparatedWordIsLink() {
    // Thread.currentThread().dumpStack();
    // System.out.println("slashSeparatedWordIsLink: " +
    // rc.get(MarkupConstants.SLASH_SEPARATED_IS_LINK_KEY));
    return _bool(MarkupConstants.SLASH_SEPARATED_IS_LINK_KEY, true);
  }

  public boolean decorateExternalLinks() {
    return _bool(MarkupConstants.DECORATE_EXTERNAL_LINKS_KEY, true);
  }

  public boolean asisSupported() {
    return _bool(MarkupConstants.AS_IS_SUPPORTED_KEY, true);
  }

  public boolean showInlineReviews() {
    return _bool(MarkupConstants.INLINE_REVIEWS_KEY, true);
  }

  public boolean singlePage() {
    return _bool(MarkupConstants.SINGLE_PAGE_KEY, true);
  }

  public boolean _bool(String s, boolean b) {
    return MarkupUtils.getBooleanFromRenderContext(rc, s, b);
  }

  public String getURL(MarkupLink mlink) {
    String url = mlink.urlpart;
    int idx = mlink.urlpart.indexOf(":");
    if (idx >= 0) {
      String protocol = mlink.urlpart.substring(0, idx);
      String linkstr = mlink.urlpart.substring(idx + 1);
      if (rc.isShorthandKey(protocol)) {
        url = rc.getShorthandReplacement(protocol, linkstr);
        // do not encodeURL ... e.g. encoding ymsgr:sendim?ugorji returns garbage
        // url = WebUtils.encodeURL(url);
      }
    } else if (MarkupUtils.emailPattern.matcher(mlink.urlpart).matches()) {
      url = "mailto:" + mlink.urlpart;
    }
    return url;
  }

  private String strippedBeginningNewLines(String s) {
    int idx = 0;
    int slen = s.length();
    for (int i = 0; i < slen; i++) {
      char c = s.charAt(i);
      if (c != '\r' && c != '\n') {
        idx = i;
        break;
      }
    }
    s = s.substring(idx);
    return s;
  }

  public static class MaxParagraphsExceededSignal extends RuntimeException {}
}
