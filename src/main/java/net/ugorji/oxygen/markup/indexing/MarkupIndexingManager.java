/* <<< COPYRIGHT START >>>
 * Copyright 2006-Present OxygenSoftwareLibrary.com
 * Licensed under the GNU Lesser General Public License.
 * http://www.gnu.org/licenses/lgpl.html
 *
 * @author: Ugorji Nwoke
 * <<< COPYRIGHT END >>>
 */

package net.ugorji.oxygen.markup.indexing;

import java.io.File;
import java.io.Reader;
import java.util.HashSet;
import java.util.List;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Searchable;
import net.ugorji.oxygen.util.Counter;
import net.ugorji.oxygen.util.OxygenUtils;

public abstract class MarkupIndexingManager {
  protected static final String[] EMPTY = new String[0];

  protected MarkupAnalyzer analyzer = null;
  protected MarkupAnalyzer analyzerForSearching = null;
  protected boolean onlyIndexPublishedPages = false;

  protected IndexReader ireader0 = null;
  protected IndexSearcher isearcher0 = null;
  protected Counter numIndexReadersCounter = new Counter(0, 0);

  protected synchronized IndexReader getIndexReader() throws Exception {
    // Thread.dumpStack();
    if (ireader0 == null) {
      ireader0 = IndexReader.open(getIndexDir());
      isearcher0 = new IndexSearcher(ireader0);
    }
    numIndexReadersCounter.increment();
    return ireader0;
  }

  protected void returnIndexReader(IndexReader ir) {
    if (ir != null) {
      numIndexReadersCounter.decrement();
    }
  }

  protected void setWriteDone() throws Exception {
    waitTillNoMoreReaders();
    close(ireader0);
    close(isearcher0);
    isearcher0 = null;
    ireader0 = null;
  }

  protected void waitTillNoMoreReaders() {
    numIndexReadersCounter.waitTillThreshhold();

    // int ii = numIndexReadersCounter.get();
    // if(ii > 0) {
    //  System.out.println("start of waitTillNoMoreReaders: section: " + wce.getName() + " ... " +
    // ii);
    //  numIndexReadersCounter.waitTillThreshhold();
    //  System.out.println("return from waitTillNoMoreReaders");
    // }

    // int numiters = 0;
    // while(numIndexReadersCounter.get() > 0) {
    //  if((numiters = ((numiters + 1) % 50)) == 0) {
    //    System.out.println("within waitTillNoMoreReaders: section: " + wce.getName() + " ... " +
    // numIndexReadersCounter.get());
    //  }
    //  OxygenUtils.sleep(100l);
    // }
  }

  protected static void close(Object o) {
    try {
      if (o == null) {
        return;
      } else if (o instanceof Searchable) {
        ((Searchable) o).close();
      } else if (o instanceof IndexReader) {
        ((IndexReader) o).close();
      } else if (o instanceof IndexWriter) {
        ((IndexWriter) o).close();
      }
    } catch (Exception exc) {
      OxygenUtils.error(exc);
    }
  }

  public void close() {
    close(isearcher0);
    close(ireader0);
    analyzer = null;
    analyzerForSearching = null;
    ireader0 = null;
    isearcher0 = null;
  }

  protected abstract MarkupIndexingParser newParser(
      String name, Reader r, List _tokenstrings, HashSet _hs) throws Exception;

  protected abstract File getIndexDir() throws Exception;
}
