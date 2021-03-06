package me.fromgate.smiley;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class SUtil extends FGUtilCore implements CommandExecutor  {

	Smiley plg;
	
	public SUtil(Smiley plugin, boolean vcheck, boolean savelng, String language, String devbukkitname, String version_name, String plgcmd, String px){
		super (plugin, vcheck, savelng, language, devbukkitname, version_name, plgcmd, px);
		this.plg = plugin;
		FillMSG();
		if (savelng) this.SaveMSG();
	}
	
	
	public void PrintCfg(CommandSender p){
		printMsg(p, "&6&lSmiles "+des.getVersion()+" &r&6| "+getMSG("msg_config",'6'));
		printMSG(p, "msg_language",plg.language);
		printEnDis(p, "msg_languagesave",plg.language_save);
		printEnDis(p, "msg_checkupdates",plg.vcheck);
		printEnDis(p, "msg_colorsmile",plg.colorsmiles);
		printEnDis(p, "msg_smilecmd",plg.smilecmd);
		printEnDis(p, "msg_smileconsole",plg.smileconsole);
		printMSG(p, "msg_defcolor",plg.default_color);
	}

	public void FillMSG(){
		addMSG ("msg_reloaded", "Settings and smiles are reloaded!");
		addMSG ("msg_language","Language: %1%");
		addMSG ("msg_languagesave","Save langugage file");
		addMSG ("msg_checkupdates","Check updates");
		addMSG ("msg_colorsmile","Colored smiles");
		addMSG ("msg_smilecmd","Smiles at commands");
		addMSG ("msg_smileconsole","Smiles at console");
		addMSG ("msg_defcolor","Default chat color: %1%");
		addMSG ("msg_smileylist","Smileys:");
	}
	
	public void printSmileyList(CommandSender sender, int page){
		List<String> sm = new ArrayList<String>();
		for (String key : plg.smiles.smiles.keySet())
			sm.add("&e"+key+" ⬩ "+plg.smiles.get(key));
		printPage(sender, sm, page, "msg_smileylist", "", true, 15);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		if ((sender instanceof Player)&&(!sender.hasPermission("smiley.config"))&&(!sender.hasPermission("smiley.list"))) return false;
		if (args.length == 0){
			if ((!sender.hasPermission("smiley.config"))&&(sender instanceof Player)) return false;
			PrintCfg(sender);
		} else if ((args.length>=1)&&(args[0].equalsIgnoreCase("list"))){
			if ((!sender.hasPermission("smiley.list"))&&(sender instanceof Player)) return false;
			int pnum = 1;
			if ((args.length==2)&&(isIntegerGZ(args[1]))) pnum = Integer.parseInt(args[1]);
			printSmileyList(sender,pnum);
		} else if ((args.length==1)&&(args[0].equalsIgnoreCase("reload"))){
			if ((!sender.hasPermission("smiley.config"))&&(sender instanceof Player)) return false;
			plg.loadCfg();
			plg.smiles.load();
			printMSG(sender, "msg_reloaded");
		} else return false;
		return true;
	}

	
}
