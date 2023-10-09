package net.mcnimoses.daisukeearth.commands;

/*==================================================================*/
//region インポート宣言
/*==================================================================*/
// 外部ライブラリ
import org.bukkit.plugin.Plugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

// 自作ライブラリ
import net.mcnimoses.daisukeearth.util.Message;
import net.mcnimoses.daisukeearth.process.SortAPI;

//endregion インポート宣言


/**##################################################################*
 * クラス名 : SortCommand <br>
 * 役割     : ヘルプメッセージを表示する(未実装) <br>
 * 継承     : org.bukkit.command.CommandExecutor;
 *###################################################################*/
public class SortCommand implements CommandExecutor, SortAPI{
    // メンバ変数
    Plugin instance;


    //region メソッド宣言部
    /**
     * コンストラクタ
     * @param instance 自プラグインインスタンス
     */
    public SortCommand(@NotNull Plugin instance){
        this.instance = instance;
    }


    /**
     * sortコマンド定義
     * @param sender Source of the command
     * @param command Command which was executed
     * @param label Alias of the command which was used
     * @param args Passed command arguments
     * @return コマンドの成功か否か
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // 初期処理
        // 前提条件
        // 1.コマンドがsortであること (大文字小文字不問)
        if(!(label.equalsIgnoreCase("sort"))){
            return true;
        }

        // 2.送信者がユーザであること
        if(!(sender instanceof Player)){
            this.instance.getLogger().warning(Message.SORTCOMMAND_M0001_W.getMessage());
            return true;
        }

        // 変数宣言
        Player player = (Player)sender;
        Inventory items = player.getInventory();  // ソート対象


        // 主処理
        // ソート処理
        ItemStack[] newItems = this.sortProcess.apply(items.getContents());

        // ソート後アイテム再定義
        items.setContents(newItems);


        // 事後処理
        player.sendMessage(Component.text(Message.SORTCOMMAND_M0001_I.getMessage(), NamedTextColor.GRAY));

        return true;
    }

    //endregion メソッド宣言部
}
