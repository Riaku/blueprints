package net.riaku;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.Arrays;
import java.util.List;

public class blueprintListener implements Listener
{
    private blueprint BP;
    ItemStack IS = new ItemStack(Material.BED);

    public blueprintListener(blueprint bp)
    {
        this.BP = bp;
    }

    @EventHandler
    public void onCraft(CraftItemEvent event)
    {
        Player player = (Player) event.getWhoClicked();
        Material mat = event.getInventory().getResult().getType();
        if (!hasBlueprint(mat, player) && !player.hasPermission("blueprint.bypass"))
        {
            event.getInventory().getResult().setType(Material.AIR);
            event.setCancelled(true);
            player.updateInventory();
            player.sendRawMessage("You have not discovered this blueprint.");
        }
    }
    @EventHandler
    public void onPickup(PlayerPickupItemEvent event){
        Player p = event.getPlayer();
        ItemStack IS = event.getItem().getItemStack();
        ItemMeta IM = IS.getItemMeta();
        if (IS.getType() == Material.PAPER && IM.getDisplayName().equals("Blueprint"))
        {
            List<String> lore = IM.getLore();
            FileConfiguration blueprintConfig = BP.getConfig();
            List<Integer> playerCon = blueprintConfig.getIntegerList("players." + p.getName());
            if(!playerCon.contains(Integer.parseInt(lore.get(0)))){
                playerCon.add(Integer.parseInt(lore.get(0)));
                blueprintConfig.set("players." + p.getName(), playerCon);
                p.sendRawMessage("Adding recipe for: " + lore.get(0) + " - " + lore.get(1));
            }
            if(!BP.getConfig().getBoolean("keepOnPickup")){
                ItemStack AIRstack = new ItemStack(Material.AIR);
                event.getItem().setItemStack(AIRstack);
            }
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event)
    {
        int chance = BP.getConfig().getInt("dropChance");
        Integer rand = (int)(Math.random() * 100);
        if (rand <= chance)
        {
            ItemStack IS = new ItemStack(Material.PAPER);
            ItemMeta IM = IS.getItemMeta();
            IM.setDisplayName("Blueprint");
            List<String> ABP = BP.getConfig().getStringList("blueprints");
            Integer randItemID = Integer.parseInt(ABP.get((int) (Math.random() * ABP.size())));
            List<String> lore = Arrays.asList(randItemID.toString(), IDAPI.getMaterialById(randItemID).name());
            IM.setLore(lore);
            IS.setItemMeta(IM);
            event.getDrops().add(IS);

        }

    }

    private Boolean hasBlueprint(Material mat, Player p)
    {
        List<String> pidlist = BP.getConfig().getStringList("players." + p.getName());
        return pidlist.contains(String.valueOf(IDAPI.getIdOfMaterial(mat)));
    }
}