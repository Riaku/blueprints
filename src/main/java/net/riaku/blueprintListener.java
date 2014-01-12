package net.riaku;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class blueprintListener implements Listener
{
    private blueprintUtils BU;
    private blueprint BP;

    public blueprintListener(blueprint bp)
    {
        this.BP = bp;
    }

    @EventHandler
    public void onCraft(CraftItemEvent event)
    {
        Player player = (Player) event.getWhoClicked();
        Material mat = event.getInventory().getResult().getType();
        if (!BU.hasBlueprint(mat, player) && !player.hasPermission("blueprint.bypass"))
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
            Integer ID = Integer.parseInt(IM.getLore().get(0));
            BU.addBlueprint(event.getPlayer(), ID);
            if(BP.getConfig().getBoolean("keepOnPickup")){
                ItemStack AIRstack = new ItemStack(Material.AIR);
                event.getItem().remove();
                event.setCancelled(true);
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
            ItemStack IS = BU.createBlueprint();
            System.out.println(IS.toString());
            event.getDrops().add(IS);
        }
    }
}