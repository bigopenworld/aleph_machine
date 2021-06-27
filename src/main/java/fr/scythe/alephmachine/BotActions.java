/*
 * This code is provided by Scythe™. You can contact me by discord (Scythe#0356) or by email (ScytheD@outlook.fr).
 * Github : https://github.com/5CYTH3
 */

package fr.scythe.alephmachine;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class BotActions {
    static final        boolean    isWindows     = System.getProperty("os.name").toLowerCase().startsWith("windows");

    public static void botBuild(String botDir) throws IOException {

        if (isWindows) {
            String commandLine = "cmd /C go build " + botDir;
            System.out.println(botDir);

            Process p = Runtime.getRuntime().exec(commandLine, null, new File(botDir));


        } else {

            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.directory(new File(botDir));
            processBuilder.command("sh", "-a", "go", "build");
            processBuilder.start();

        }
        AlertBox.display("Alert - Task Started", "The command \"go build\" have been launched in :", botDir);
    }

    // Launch of the bot
    public static void botRun(String exeDir, String botDirLinux) throws IOException {

        if(isWindows) {
            System.out.println(exeDir);
            ProcessBuilder pBuilder = new ProcessBuilder(exeDir);
            pBuilder.redirectErrorStream(true);
            pBuilder.start();

        } else {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.directory(new File(exeDir));
            processBuilder.command("./" + botDirLinux);
            processBuilder.start();

        }

        AlertBox.display("Alert - Task Started", "The bot have been launched.", "(" + exeDir + ")");

    }


}
