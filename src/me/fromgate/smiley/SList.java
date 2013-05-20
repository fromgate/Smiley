package me.fromgate.smiley;


import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;


public class SList {

	Smiley plg;
	
	

	public SList(Smiley plg){
		this.plg = plg;
		this.init();
		this.load();
		this.save();
		
	}
	
	
	public int getSmileIndex (String msg, String smilecode){
		return msg.indexOf(smilecode);
	}
	
	private String processSmile (String msg, String smilecode){
		String str = msg;
		int i = str.indexOf(smilecode);
		if (i<0) return str;
		String c = ChatColor.getLastColors(str.substring(0, i));
		if (c.isEmpty()) c = plg.default_color;
		str = str.replaceFirst(Pattern.quote(smilecode), smiles.get(smilecode)+c);
		str = ChatColor.translateAlternateColorCodes('&', str);
		return str;
	}
	
	public String processSmiles (String msg){
		String str = msg;
		for (String smilecode : smiles.keySet()){
			while (str.contains(smilecode))
				str = processSmile (str, smilecode);
		}
		return str;
	}

	public String processSmilecDecolor(String txt){
		String str = txt;
		for (String key : smiles.keySet())
			str = str.replace(key, smiles.get(key));
		return ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', str));
	}
	
	public void init(){
		 smiles.clear();
		 smiles.put(":(","&c☹");
		 smiles.put(":-(","&c☹");
		 smiles.put(":)","&6☺"); 
		 smiles.put(":D","&6☻");
		 smiles.put(":-)","&6☻");
		 smiles.put(";\\","&6ツ");
		 smiles.put(";)","&6㋡");
		 smiles.put("<3","&4♥");
		 smiles.put(":hurt:","&4♥");
		 smiles.put(":peace:","&e☮");  
		 smiles.put(":death:","&4☠");  
		 smiles.put(":nuke:","&6☢");   
		 smiles.put(":bio:","&5☣");   
		 smiles.put(":cccp:","&c☭");    
		 smiles.put(":ship:","&3☸");
		 smiles.put(":food:","&a♨");
		 smiles.put(":star:","&e☀");   
		 smiles.put(":cloud:","&b☁");   
		 smiles.put(":umbrella:","&3☂");    
		 smiles.put(":snowman:","&f☃");    
		 smiles.put(":sun:","&6☼");  
		 smiles.put(":moonr:","&a☽ ");
		 smiles.put(":moon:","&a☾");    
		 smiles.put(":rmb:","&6❖");
		 smiles.put(":hat:","&3〠");
		 smiles.put(":8:","&4♾");
	}
	
	Map<String,String> smiles = new HashMap<String,String>();
	
	public String get(String key){
		String s = (key.startsWith(":")&&key.endsWith(":")) ? key.substring(1, key.length()-1) : key;
		if (smiles.containsKey(s))
			return smiles.get(s);
		return key;
	}
	

	public void add(String key, String smile){
		smiles.put(key, smile);
	}

	public boolean remove(String key){
		if (!smiles.containsKey(key)) return false;
		smiles.remove(key);
		return true;
	}

	public void clear(){
		smiles.clear();
	}

	public void load(){
		try{
			File f = new File (plg.getDataFolder()+File.separator+"smiley.yml");
			if (!f.exists()) return;
			smiles.clear();
			YamlConfiguration sml = new YamlConfiguration();
			sml.load(f);
			for (String key: sml.getKeys(false)) add (key, sml.getString(key,key));
		} catch (Exception e){
		}
	}
	
	public void save(){
		try{
			File f = new File (plg.getDataFolder()+File.separator+"smiley.yml");
			if (!f.exists()) f.delete();
			f.createNewFile();
			if (smiles.isEmpty()) return;
			YamlConfiguration sml = new YamlConfiguration();
			for (String key : smiles.keySet())
				sml.set(key, smiles.get(key));
			sml.save(f);
		} catch (Exception e){
		}
	}



}
