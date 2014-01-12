package net.riaku;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class blueprintCommands implements CommandExecutor
{
    private blueprintUtils BU;
    private blueprint BP;
    private Player BPlayer;

    public blueprintCommands(blueprint bp)
    {
        this.BP = bp;
    }
    String c;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    c = cmd.getName();
        if (cmd.getName().equalsIgnoreCase("blueprints") && (sender instanceof Player)) {
            List<Integer> printList= BP.getConfig().getIntegerList("players."+sender.getName());
            Integer int1 = Integer.parseInt(args[1]);
            Integer hold = int1+10;

            while (int1 < printList.size() && int1 < hold) {
                String name = IDAPI.getMaterialById(printList.get(int1)).name();
                sender.sendMessage(printList.get(int1) + name);
                int1++;
            }
        }
        System.out.println("issued the command?:  "+cmd.getName());

        if (cmd.getName().equalsIgnoreCase("rembp") || cmd.getName().equalsIgnoreCase("removeblueprint")) {
            BU.removeBlueprint(BP.getServer().getPlayer(args[0]), Integer.parseInt(args[1]));
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("addbp") || cmd.getName().equalsIgnoreCase("addblueprint")) {
            BU.addBlueprint(BP.getServer().getPlayer(args[0]), Integer.parseInt(args[1]));
            return true;
        }
        return false;
    }

}
