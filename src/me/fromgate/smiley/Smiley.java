package me.fromgate.smiley;



import org.bukkit.plugin.java.JavaPlugin;


public class Smiley extends JavaPlugin {

	SUtil u;
	SListener l;
	SList smiles;

	// Конфигурация
	boolean vcheck = true;
	String language = "english";
	boolean language_save=false;

	
	String default_color = "&f";
	boolean colorsmiles = true;
	boolean smileconsole = true;
	boolean smilecmd = true;


	@Override
	public void onEnable() {
		loadCfg();
		saveCfg();
		u = new SUtil(this, vcheck, language_save, language, "smiley", "Smiley", "smile", "&b[&3Smiley&b]&f");
		l = new SListener (this);
		smiles = new SList (this);

		getCommand("smile").setExecutor(u);

		getServer().getPluginManager().registerEvents(l, this);

		try {
			MetricsLite metrics = new MetricsLite(this);
			metrics.start();
		} catch (Exception e) {
		} 		
	}

	public void loadCfg(){
		reloadConfig();
		vcheck = getConfig().getBoolean("general.check-updates",true);
		language = getConfig().getString("general.language","english");
		language_save = getConfig().getBoolean("general.language-save",false);
		smileconsole = getConfig().getBoolean("smiley.console",true);
		smilecmd = getConfig().getBoolean("smiley.commands",true);
		colorsmiles = getConfig().getBoolean("smiley.colors",true); 
		default_color = getConfig().getString("smiley.default-chat-color","&f");
	}

	private void saveCfg(){
		getConfig().set("general.check-updates",vcheck);
		getConfig().set("general.language",language);
		getConfig().set("general.language-save",language_save);
		getConfig().set("smiley.console",smileconsole);
		getConfig().set("smiley.commands",smilecmd);
		getConfig().set("smiley.colors",colorsmiles);
		getConfig().set("smiley.default-chat-color",default_color );
		saveConfig();
	}



}
