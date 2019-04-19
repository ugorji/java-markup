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
import java.util.Iterator;
import java.util.Map;
import org.radeox.api.engine.context.InitialRenderContext;
import org.radeox.api.engine.context.RenderContext;
import org.radeox.engine.BaseRenderEngine;
import org.radeox.engine.context.BaseInitialRenderContext;
import org.radeox.engine.context.BaseRenderContext;
import org.radeox.macro.Macro;
import org.radeox.macro.MacroRepository;
import org.radeox.macro.parameter.BaseMacroParameter;
import org.radeox.macro.parameter.MacroParameter;
import net.ugorji.oxygen.util.OxygenUtils;

public class RadeoxHelper {
  private static InitialRenderContext initctx;

  static {
    initctx = new BaseInitialRenderContext();
    MacroRepository.getInstance().setInitialContext(initctx);
    OxygenUtils.debug("Available Radeox Macros: " + MacroRepository.getInstance().getPlugins());
    // Thread.dumpStack();
    // MarkupUtils.info("Listing macro load urls ...");
    // Enumeration providerFiles =
    // Thread.currentThread().getContextClassLoader().getResources("META-INF/services/org.radeox.macro.Macro");
    // while(providerFiles.hasMoreElements()) {
    //  MarkupUtils.info("Macros load URL: " + providerFiles.nextElement());
    // }
    // do this, so it initializes itself
    // render("**Hi**", null);
  }

  public static RenderContext getRadeoxRenderContext(
          net.ugorji.oxygen.markup.MarkupRenderContext context, net.ugorji.oxygen.markup.MarkupRenderEngine re) {
    BaseRenderContext rc = new BaseRenderContext();
    Map m = context.getValues();
    for (Iterator itr = m.keySet().iterator(); itr.hasNext(); ) {
      String key = String.valueOf(itr.next());
      rc.set(key, m.get(key));
    }
    rc.setRenderEngine(new BaseRenderEngine(initctx));
    return rc;
  }

  public static boolean isMacroAvailable(String command) {
    return MacroRepository.getInstance().containsKey(command);
  }

  public static void handleMacro(
      String command,
      MarkupRenderEngine re,
      MarkupRenderContext context,
      Writer pw,
      MarkupMacroParameters params)
      throws Exception {
    RenderContext rc = getRadeoxRenderContext(context, re);
    MacroParameter mParams = new BaseMacroParameter(rc);
    mParams.setParams(params.getArgsAsString());
    mParams.setContent(params.getContent());
    Macro macro = (Macro) MacroRepository.getInstance().get(command);
    macro.execute(pw, mParams);

    //     mParams.setParams(result.group(2));
    //     if(hascontent) {
    //       String scon = result.group(4);
    //       if(scon != null && scon.length() > 0) {
    //         mParams.setContent(result.group(4));
    //         mParams.setContentStart(result.start(4));
    //         mParams.setContentEnd(result.end(4));
    //       }
    //     }
    //     mParams.setStart(result.start(0));
    //     mParams.setEnd(result.end(0));

  }
}
