/* <<< COPYRIGHT START >>>
 * Copyright 2006-Present OxygenSoftwareLibrary.com
 * Licensed under the GNU Lesser General Public License.
 * http://www.gnu.org/licenses/lgpl.html
 *
 * @author: Ugorji Nwoke
 * <<< COPYRIGHT END >>>
 */

package net.ugorji.oxygen.markup.macros;

import java.io.ByteArrayInputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Properties;
import net.ugorji.oxygen.markup.MarkupMacroParameters;
import net.ugorji.oxygen.markup.MarkupRenderContext;

/**
 * Copies the text as is, into the response it is used especially to embed HTML text, etc
 *
 * @author ugorji
 */
public class PropertiesProcessor extends GenericMarkupMacro {
  public void doExecute(Writer writer, MarkupRenderContext rc, MarkupMacroParameters params)
      throws Exception {
    // RenderContext rc = params.getContext();
    // WikiCategoryEngine engine = (WikiCategoryEngine)rc.get(MarkupConstants.CATEGORYENGINE_KEY);
    PrintWriter pw = new PrintWriter(writer);
    String pagetext = params.getContent();
    ByteArrayInputStream is = new ByteArrayInputStream(pagetext.getBytes());
    Properties p = new Properties();
    p.load(is);
    is.close();
    pw.println("<table>");
    for (Enumeration enum0 = p.propertyNames(); enum0.hasMoreElements(); ) {
      pw.println("<tr>");
      String key = (String) enum0.nextElement();
      String value = p.getProperty(key);
      pw.println("<td>" + key + "</td>");
      pw.println("<td>" + value + "</td>");
      pw.println("</tr>");
    }
    pw.println("</table>");
    pw.flush();
  }
}
