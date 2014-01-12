package net.riaku;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;


public class blueprintUtils
{
    private blueprint BP;
    public void openBook(){
        System.out.println("openingBook");
    }

    public Boolean hasBlueprint(Material mat, Player p)
    {
        List<String> pidlist = BP.getConfig().getStringList("players." + p.getName());
        return pidlist.contains(String.valueOf(IDAPI.getIdOfMaterial(mat)));
    }

    public ItemStack createBlueprint(){
        ItemStack IS = new ItemStack(Material.PAPER);
        ItemMeta IM = IS.getItemMeta();
        IM.setDisplayName("Blueprint");
        System.out.println("1: "+IS);
        List<String> ABP = BP.getConfig().getStringList("blueprints");
        Integer randItemID = Integer.parseInt(ABP.get((int) (Math.random() * ABP.size())));
        List<String> lore = Arrays.asList(randItemID.toString(), IDAPI.getMaterialById(randItemID).name());
        IM.setLore(lore);
        IS.setItemMeta(IM);
        System.out.println("2: "+IS);
        return IS;
    }

    public void addBlueprint(Player p, int i){
        List<Integer> playerCon = BP.getConfig().getIntegerList("players."+p.getName());
        if(!playerCon.contains(i)){
            playerCon.add(i);
            BP.getConfig().set("players." + p.getName(), playerCon);
            p.sendMessage("Adding recipe for: " + i + " - " + IDAPI.getMaterialById(i));
            BP.saveConfig();
        }
    }
    public void removeBlueprint(Player p, int i){
        List<Integer> playerCon = BP.getConfig().getIntegerList("players."+p.getName());
        if(playerCon.contains(i)){
            int int1 = 0;
            while ( int1 < playerCon.size()) {
                if(playerCon.get(int1) == i){
                    playerCon.remove(int1);
                    BP.getConfig().set("players." + p.getName(), playerCon);
                    p.sendMessage("Removing recipe for: " + i + " - " + IDAPI.getMaterialById(i));
                    BP.saveConfig();
                }
                int1++;
            }
        }
    }
}
