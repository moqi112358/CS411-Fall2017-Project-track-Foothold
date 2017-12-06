package exception;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.IOException;
 public  class MyLog {

    static final String logPath = "/home/yichang3/log/log.txt";
    static Path fpath  = java.nio.file.Paths.get(logPath);

    public static void write(String content){
        try {
            Files.write(fpath, content.getBytes(),StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
