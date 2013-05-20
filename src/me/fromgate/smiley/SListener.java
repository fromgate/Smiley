package me.fromgate.smiley;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerCommandEvent;

public class SListener implements Listener {
	Smiley plg;
	
	public SListener (Smiley plg){
		this.plg = plg;
	}

	
	@EventHandler(priority=EventPriority.HIGH, ignoreCancelled = true)
	public void onSmileChat (AsyncPlayerChatEvent event){
		if (!event.getPlayer().hasPermission("smiley.chat")) return;
		if (plg.colorsmiles) event.setMessage(plg.smiles.processSmiles(event.getMessage()));
		else event.setMessage(plg.smiles.processSmilecDecolor(event.getMessage()));
	}
	
	@EventHandler(priority=EventPriority.HIGH, ignoreCancelled = true)
	public void onSmileCmd(PlayerCommandPreprocessEvent event){
		if (!plg.smilecmd) return;
		if (!event.getPlayer().hasPermission("smiley.commands")) return;
		if (plg.colorsmiles) event.setMessage(plg.smiles.processSmiles(event.getMessage()));
		else event.setMessage(plg.smiles.processSmilecDecolor(event.getMessage()));
	}
	
	@EventHandler(priority=EventPriority.HIGH, ignoreCancelled = true)
	public void onRecodeConsoleCmd(ServerCommandEvent event) {
		if (!plg.smileconsole) return;
		if (plg.colorsmiles) event.setCommand(plg.smiles.processSmiles(event.getCommand()));
		else event.setCommand(plg.smiles.processSmilecDecolor(event.getCommand()));
	}
	
	@EventHandler(priority=EventPriority.HIGH, ignoreCancelled = true)
	public void onJoin (PlayerJoinEvent event){
		plg.u.UpdateMsg(event.getPlayer());
	}

}
