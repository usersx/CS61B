import java.util.Properties;

public class GetEnvironmentVariables {

    //  Fill in the path to your sp21-s*** folder between the quotes
    public static String REPO_DIR = "";

    //  Fill in the path to your snaps-sp21-s*** folder between the quotes
    public static String SNAPS_DIR = "";

    // Fill in the type of your shell by running 'echo $0` in your terminal. It should be zsh or bash.
    public static String SHELL = "";

    /**
     * 主程序入口
     * 本程序用于根据用户操作系统的不同（如Catalina、Mac OS、Linux）设置环境变量
     * 它会自动检测当前系统，并根据系统类型和用户的shell类型（如果适用），生成相应的命令来设置环境变量
     * 这些环境变量包括REPO_DIR和SNAPS_DIR，它们对于系统上的某些操作和应用程序非常重要
     *
     * @param args 命令行参数，本程序不直接使用这些参数
     */
    public static void main(String[] args) {

        // 设置环境变量的命令模板，用于Catalina（zsh shell）、Mac OS和Linux
        String catalina = "echo 'export {variable}={value}' >> ~/.zprofile";
        String mac = "echo 'export {variable}={value}' >> ~/.bash_profile";
        String linux = "echo 'export {variable}={value}' >> ~/.bashrc";
        // 激活新设置的命令模板
        String catalinaSource = "source ~/.zprofile";
        String macSource = "source ~/.bash_profile";
        String linuxSource = "source ~/.bashrc";

        // 检测用户操作系统名称，将其转换为小写以便比较
        String yourOS = System.getProperty("os.name").toLowerCase();

        // 初始化用于存储特定环境变量设置命令的变量
        String repo = null;
        String snaps = null;
        String source = null;

        // 根据操作系统类型设置环境变量
        if (yourOS.contains("mac")) {
            // 对于Mac系统，进一步区分zsh和bash shell
            if (SHELL.equals("zsh")) {
                // 生成设置REPO_DIR和SNAPS_DIR环境变量的命令
                repo = catalina.replace("{variable}", "REPO_DIR").replace("{value}", REPO_DIR);
                snaps = catalina.replace("{variable}", "SNAPS_DIR").replace("{value}", SNAPS_DIR);
                // 设置激活命令
                source = catalinaSource;
            } else {
                // 对于bash shell，使用不同的配置文件
                repo = mac.replace("{variable}", "REPO_DIR").replace("{value}", REPO_DIR);
                snaps = mac.replace("{variable}", "SNAPS_DIR").replace("{value}", SNAPS_DIR);
                source = macSource;
            }
        } else if (yourOS.contains("nux")) {
            // 对于Linux系统，使用统一的配置文件
            repo = linux.replace("{variable}", "REPO_DIR").replace("{value}", REPO_DIR);
            snaps = linux.replace("{variable}", "SNAPS_DIR").replace("{value}", SNAPS_DIR);
            source = linuxSource;
        }

        // 如果无法识别用户的操作系统，给出提示信息
        if (repo == null) {
            System.out.println();
            System.out.println("Oops! We couldn't detect your OS. Please reach out to a lab TA or post on Ed so we can help you move forward");
            return;
        }

        // 输出设置环境变量的命令，指导用户下一步操作
        System.out.println();
        System.out.println("Keep reading the spec to know what to do with this output");
        System.out.println("----------------------------------------------------------");
        System.out.println(repo);
        System.out.println(snaps);
        System.out.println(source);
    }

}