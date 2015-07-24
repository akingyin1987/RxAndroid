package rx.android.samples.util;

import java.io.File;

/**
 *  
 * User: Geek_Soledad(msdx.android@qq.com) 
 * Date: 2015-07-24 
 * Time: 14:48 
 *  
 */
public class FileUtil {

    public  static    boolean    makeDirectory(String  Directory){
        File   file  =  new File(Directory);
        return  file.mkdirs();
    }
}
