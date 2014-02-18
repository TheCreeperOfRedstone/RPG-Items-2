package think.rpgitems.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import think.rpgitems.Plugin;

public class Update04To05 implements Updater {

    @Override
    public void update(ConfigurationSection section) {
        Plugin plugin = Plugin.plugin;
        
        try {
            FileInputStream in = null;
            YamlConfiguration itemStorage = null;
            try {
                File f = new File(plugin.getDataFolder(), "items.yml");
                in = new FileInputStream(f);
                byte[] data = new byte[(int) f.length()];
                in.read(data);
                itemStorage = new YamlConfiguration();
                String str = new String(data, "UTF-8");
                itemStorage.loadFromString(str);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidConfigurationException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (in != null)
                        in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            ConfigurationSection itemSection = itemStorage.getConfigurationSection("items");
            if (itemSection != null) {
                for (String itemKey : itemSection.getKeys(false)) {
                  ConfigurationSection s = itemSection.getConfigurationSection(itemKey);
                  if(!s.contains("haspermission")){
                	  s.set("haspermission", "false");
                  }
                  if(!s.contains("permission")){
                	  s.set("permission", "a.default.permission");
                  }
            }
            }
            } catch (Exception e) {
                e.printStackTrace();
        
        section.createSection("language");
        section.set("language", "en_GB");
        section.set("version", "0.5"); 
    }
  }
}
