package com.sunsetinteractive.miningworld.configuration;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;

import com.google.common.io.ByteStreams;

import com.sunsetinteractive.miningworld.MiningWorld;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class FileManager {

	private final MiningWorld miningWorld;
	private Map<String, Config> loadedConfigs = new HashMap<>();

	public FileManager(MiningWorld miningWorld) {
		this.miningWorld = miningWorld;

		loadConfigurations();
	}

	public void loadConfigurations() {
		if (!miningWorld.getDataFolder().exists()) {
			miningWorld.getDataFolder().mkdir();
		}

		if (!new File(miningWorld.getDataFolder().toString() + "/structures").exists()) {
			new File(miningWorld.getDataFolder().toString() + "/structures").mkdir();
		}
		
		if (!new File(miningWorld.getDataFolder().toString() + "/playerdata").exists()) {
			new File(miningWorld.getDataFolder().toString() + "/playerdata").mkdir();
		}

		Map<String, File> configFiles = new LinkedHashMap<>();
		configFiles.put("config.yml", new File(miningWorld.getDataFolder(), "config.yml"));
		configFiles.put("language.yml", new File(miningWorld.getDataFolder(), "language.yml"));
		//configFiles.put("upgrades.yml", new File(miningWorld.getDataFolder(), "upgrades.yml"));
		//configFiles.put("generation.yml", new File(miningWorld.getDataFolder(), "generation.yml"));
		configFiles.put("generation/default.structure",
			new File(miningWorld.getDataFolder().toString() + "/playerdata", "example.yml"));
		configFiles.put("generation/default.structure",
				new File(miningWorld.getDataFolder().toString() + "/structures", "default.structure"));

		for (String configFileList : configFiles.keySet()) {
			File configFile = configFiles.get(configFileList);

			if (configFile.exists()) {
				if (configFileList.equals("config.yml") || configFileList.equals("language.yml")
						|| configFileList.equals("settings.yml")) {
					FileVerifier fileChecker;

					if (configFileList.equals("config.yml")) {
						fileChecker = new FileVerifier(miningWorld, this, configFileList, true);
					} else {
						fileChecker = new FileVerifier(miningWorld, this, configFileList, false);
					}

					fileChecker.loadSections();
					fileChecker.compareFiles();
					fileChecker.saveChanges();
				}
			} else {
				try {
					configFile.createNewFile();
					try (InputStream is = miningWorld.getResource(configFileList);
						OutputStream os = new FileOutputStream(configFile)) {
						ByteStreams.copy(is, os);
					}

					if (configFileList.equals("worlds.yml")) {
						File mainConfigFile = new File(miningWorld.getDataFolder(), "config.yml");

						if (isFileExist(mainConfigFile)) {
							Config config = new Config(this, configFile);
							Config mainConfig = new Config(this, mainConfigFile);

							FileConfiguration configLoad = config.getFileConfiguration();
							FileConfiguration mainConfigLoad = mainConfig.getFileConfiguration();

							mainConfigLoad.set("World", null);

							configLoad.save(config.getFile());
							saveConfig(mainConfigLoad.saveToString(), mainConfig.getFile());
						}
					}
				} catch (IOException ex) {
					Bukkit.getServer().getLogger().log(Level.WARNING,
							"SkyBlock | Error: Unable to create configuration file.");
				}
			}
		}
	}

	public boolean isFileExist(File configPath) {
		return configPath.exists();
	}

	public void unloadConfig(File configPath) {
		loadedConfigs.remove(configPath.getPath());
	}

	public void deleteConfig(File configPath) {
		Config config = getConfig(configPath);
		config.getFile().delete();
		loadedConfigs.remove(configPath.getPath());
	}

	public Config getConfig(File configPath) {
		if (loadedConfigs.containsKey(configPath.getPath())) {
			return loadedConfigs.get(configPath.getPath());
		}

		Config config = new Config(this, configPath);
		loadedConfigs.put(configPath.getPath(), config);

		return config;
	}

	public Map<String, Config> getConfigs() {
		return loadedConfigs;
	}

	public boolean isConfigLoaded(java.io.File configPath) {
		return loadedConfigs.containsKey(configPath.getPath());
	}

	public InputStream getConfigContent(Reader reader) {
		try {
			String addLine, currentLine, pluginName = miningWorld.getDescription().getName();
			int commentNum = 0;

			StringBuilder whole = new StringBuilder("");
			BufferedReader bufferedReader = new BufferedReader(reader);

			while ((currentLine = bufferedReader.readLine()) != null) {
				if (currentLine.contains("#")) {
					addLine = currentLine.replace("[!]", "IMPORTANT").replace(":", "-").replaceFirst("#",
							pluginName + "_COMMENT_" + commentNum + ":");
					whole.append(addLine + "\n");
					commentNum++;
				} else {
					whole.append(currentLine + "\n");
				}
			}

			String config = whole.toString();
			InputStream configStream = new ByteArrayInputStream(config.getBytes(Charset.forName("UTF-8")));
			bufferedReader.close();

			return configStream;
		} catch (IOException e) {
			e.printStackTrace();

			return null;
		}
	}

	public InputStream getConfigContent(File configFile) {
		if (!configFile.exists()) {
			return null;
		}

		try {
			return getConfigContent(new FileReader(configFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}

	private String prepareConfigString(String configString) {
		String[] lines = configString.split("\n");
		StringBuilder config = new StringBuilder("");

		for (String line : lines) {
			if (line.contains(miningWorld.getDescription().getName() + "_COMMENT")) {
				config.append(line.replace("IMPORTANT", "[!]").replace("\n", "")
						.replace(miningWorld.getDescription().getName() + "_COMMENT_", "#").replaceAll("[0-9]+:", "")
						+ "\n");
			} else if (line.contains(":")) {
				config.append(line + "\n");
			}
		}

		return config.toString();
	}

	public void saveConfig(String configString, File configFile) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(configFile));
			writer.write(prepareConfigString(configString));
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static class Config {

		private File configFile;
		private FileConfiguration configLoad;

		public Config(FileManager fileManager, java.io.File configPath) {
			configFile = configPath;

			if (configPath.getName().equals("config.yml")) {
				configLoad = YamlConfiguration
						.loadConfiguration(new InputStreamReader(fileManager.getConfigContent(configFile)));
			} else {
				configLoad = YamlConfiguration.loadConfiguration(configPath);
			}
		}

		public File getFile() {
			return configFile;
		}

		public FileConfiguration getFileConfiguration() {
			return configLoad;
		}

		public FileConfiguration loadFile() {
			configLoad = YamlConfiguration.loadConfiguration(configFile);

			return configLoad;
		}
	}
}
