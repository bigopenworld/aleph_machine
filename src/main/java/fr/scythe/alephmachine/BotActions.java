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
        File runnableDir = new File("runnable.bat");
        if (isWindows) {
            String commandLine = "cmd /C C:\\Users\\utilisateur\\go\\go1.16.3\\bin\\go.exe build";

            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.directory(new File(botDir));
            processBuilder.command(runnableDir.getAbsolutePath());
            processBuilder.redirectErrorStream(true);
            Process p = processBuilder.start();

            InputStream is = p.getInputStream();
            System.out.println("" + is.read());

        } else {

            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.directory(new File(botDir));
            processBuilder.command("sh", "-a", "go", "build");
            processBuilder.start();

        }
        AlertBox.display("Alert - Task Started", "The command \"go build\" have been launched in :", botDir);
    }

    // Launch of the bot
    public static void botRun(String botDir, String botDirLinux) throws IOException {

        if(isWindows) {
            System.out.println(botDir);
            ProcessBuilder pBuilder = new ProcessBuilder(botDir);
            pBuilder.redirectErrorStream(true);
            pBuilder.start();

        } else {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.directory(new File(botDir));
            processBuilder.command("./" + botDirLinux);
            processBuilder.start();

        }

        AlertBox.display("Alert - Task Started", "The bot have been launched.", "(" + botDir + ")");

    }


}
