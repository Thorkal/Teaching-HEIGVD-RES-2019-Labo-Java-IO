package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private int ligne = 0;
  private char lastChar = '\0';

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    for(int i = 0; i < len; ++i){
      write(str.charAt(i + off));
    }
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for(int i = 0; i < len; ++i){
      write(cbuf[i + off]);
    }
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(int c) throws IOException {
    if(ligne == 0){
      out.write(++ligne + "\t");
    }
    if(c == '\n'){
      out.write(c);
      out.write(++ligne + "\t");
    } else if(lastChar == '\r'){
     out.write(++ligne + "\t");
     out.write(c);
    } else {
      out.write(c);
    }
    lastChar = (char) c;
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

}
