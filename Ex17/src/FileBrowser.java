/**
 * Jerry Shultz's Filebrowser because I'm lazy
 */

import javax.swing.*;
import java.io.*;

class FileBrowser
{
  private static JFileChooser fileChooser = setup();

  private static JFileChooser setup()
  {
    JFileChooser fc = null;

    try{
      File file = new File(".");
      String path = file.getCanonicalPath();
      fc = new JFileChooser( file.getCanonicalPath() );
    }
    catch(Exception e)
    {
      System.out.println("Could not make a file chooser");
      System.exit(1);
    }
  
    return fc;
  }

  // utility to select a file from which to read or write
  static String chooseFile(boolean doRead)
  {
    int code;
    if( doRead )
      code = fileChooser.showOpenDialog( null );
    else
      code = fileChooser.showSaveDialog( null );
  
    if( code == JFileChooser.APPROVE_OPTION )
    {
      File file = fileChooser.getSelectedFile();
      return file.getAbsolutePath();
    }
    else
    {
      return "";
    }
  }
}
