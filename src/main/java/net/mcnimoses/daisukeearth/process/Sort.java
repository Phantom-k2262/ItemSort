package net.mcnimoses.daisukeearth.process;

/*==================================================================*/
//region インポート宣言
/*==================================================================*/
// 外部ライブラリ
import org.bukkit.plugin.Plugin;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

// 自作ライブラリ
import net.mcnimoses.daisukeearth.util.Message;

//endregion インポート宣言


/**##################################################################*
 * クラス名 : Sort <br>
 * 役割     : ソート処理を定義 <br>
 * 実装元   : net.mcnimoses.daisukeearth.commands.SortAPI
 *###################################################################*/
public class Sort implements SortAPI{
    // メンバ変数
    Plugin instance;

    //region メソッド宣言部
    /**
     * コンストラクタ
     * @param instance プラグインインスタンス
     */
    public Sort(Plugin instance){
        // インスタンスを取得
        this.instance = instance;

        // イベントを登録する
        this.instance.getServer().getPluginManager().registerEvents(this, this.instance);
    }

    /**
     * 棒で殴ったチェストを並び替える
     * @param event イベント
     */
    @EventHandler(priority = EventPriority.NORMAL)
    public void chestSort(BlockDamageEvent event){
        //region 初期処理
        Inventory items;                    // ソート対象
        ItemStack[] newItems;               // ソート後インベントリ
        Block block = event.getBlock();     // パンチ対象ブロック

        // 処理前提条件
        // 1.手に木の棒を持ってること
        if(event.getItemInHand().getType() != Material.STICK) {
            return;
        }

        // 2.チェスト、トラップチェスト、エンダーチェストであること
        if(block.getType() == Material.CHEST || block.getType() == Material.TRAPPED_CHEST) {
            // Chest型へキャスト
            Chest chest = (Chest)block.getState();

            // インベントリーを取得
            items = chest.getInventory();

        } else if(block.getType() == Material.ENDER_CHEST) {
            // インベントリーを取得(エンダーチェスト)
            items = event.getPlayer().getEnderChest();

        } else {
            return;

        }

        //endregion 初期処理

        //region 主処理
        // ソート処理
        newItems = this.sortProcess.apply(items.getContents());

        // ソート後のインベントリを再セットする
        items.setContents(newItems);

        //endregion 主処理

        //region 事後処理
        event.getPlayer().sendMessage(Color.GRAY + Message.SORT_M0001_I.getMessage());

        //endregion 事後処理
    }
    //endregion メソッド宣言部
}
