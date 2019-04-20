package Color_yr.BungeeConnect;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.logging.Logger;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class BungeeConnect extends Plugin {
    public static String Version = "1.0.0";

    public static String Server1122A;
    public static String Server1122B;
    public static String Server1122C;
    public static String Server1132;

    public static String Servers;

    public static Configuration config;
    private static File FileName;

    public static Logger log = ProxyServer.getInstance().getLogger();

    public static void loadconfig() {
        log.info("[BungeeConnect]你的配置文件版本是：" + config.getString("Version"));

        Server1122A = config.getString("Server1122A", "heartage1");
        Server1122B = config.getString("Server1122B", "heartage2");
        Server1122C = config.getString("Server1122C", "heartage3");
        Server1132 = config.getString("Server1132", "heartage4");

        Servers = config.getString("Servers", "lobby");
    }

    public static void reloadConfig() {
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(FileName);
            loadconfig();
        } catch (Exception arg0) {
            log.warning("[BungeeConnect]配置文件读取错误：" + arg0.getMessage());
        }
    }

    public static Configuration getConfig() {
        return config;
    }

    public void setConfig() {
        FileName = new File(getDataFolder(), "config.yml");
        logs.file = new File(getDataFolder(), "logs.log");
        if (!getDataFolder().exists())
            getDataFolder().mkdir();
        if (!FileName.exists()) {
            try (InputStream in = getResourceAsStream("config.yml")) {
                Files.copy(in, FileName.toPath());
            } catch (IOException e) {
                log.warning("[BungeeConnect]配置文件创建错误：" + e.getMessage());
            }
        }
        try {
            if (!logs.file.exists()) {
                logs.file.createNewFile();
            }
        } catch (IOException e) {
            log.warning("§d[BungeeConnect]§c日志文件错误：" + e);
        }
    }

    @Override
    public void onEnable() {
        log.info("[BungeeConnect]正在启动，插件交流群：571239090欢迎加入");
        setConfig();
        reloadConfig();
        ProxyServer.getInstance().getPluginManager().registerListener(this, new Event());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new command());
        log.info("[BungeeConnect]1.12.2A服务器名字" + Server1122A);
        log.info("[BungeeConnect]1.12.2B服务器名字" + Server1122B);
        log.info("[BungeeConnect]1.12.2C服务器名字" + Server1122C);


        log.info("[BungeeConnect]大厅服务器名字" + Servers);
        log.info("[BungeeConnect]已启动，你运行的版本是：" + Version);
    }

    @Override
    public void onDisable() {
        log.info("[BungeeConnect]已停止运行，欢迎再次使用");
    }
}
