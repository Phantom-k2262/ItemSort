/*_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/*
 *_/ プラグイン名 : アイテムソート(ベータ版)
 *_/ システム名   : 建国鯖
 *_/ 機能         : 持ち物・チェストの中身をitemID順に並び替える
 *_/ 作成者       : エドガー大工ストラ
 *_/
 *_/-----------------------------------------------------------------
 *_/ 作成日       : 2023/MM/DD
 *_/ 最終更新日   : 2023/MM/DD (初版)
 *_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/*/
package net.mcnimoses.daisukeearth;

/*==================================================================*/
//region インポート宣言
/*==================================================================*/
// 外部ライブラリ
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.HandlerList;

// 自作ライブラリ
import net.mcnimoses.daisukeearth.exception.*;
import net.mcnimoses.daisukeearth.util.Message;
import net.mcnimoses.daisukeearth.process.Sort;
import net.mcnimoses.daisukeearth.commands.SortCommand;

// 標準ライブラリ
import java.util.logging.Level;
import java.util.function.BiConsumer;

//endregion インポート宣言

//region ItemSortクラス
/**##################################################################*
 * クラス名 : ItemSort <br>
 * 役割     : 本プラグインのエントリーポイント <br>
 * 継承     : org.bukkit.plugin.java.JavaPlugin <br>
 *###################################################################*/
public final class ItemSort extends JavaPlugin {
    // メンバ変数
    private static ItemSort pluginInstance;

    //region メソッド宣言部

    /**
     * プラグイン有効化時に実行されるメソッド
     */
    @Override
    public void onEnable() {
        // 初期処理
        // インスタンス定義
        ItemSort.pluginInstance = this;

        // 有効化メッセージ
        getServer().getLogger().info(Message.ITEMSORT_M0001_I.getMessage().replace("%プラグイン名%", getName()));

        //region 主処理
        setupEvents();                   // イベントハンドルセットアップ
        setupCommands();                 // コマンドセットアップ
        //endregion 主処理
    }

    /**
     * プラグイン無効化時に実行されるメソッド
     */
    @Override
    public void onDisable() {
        // 主処理
        // イベントハンドル無効化
        HandlerList.unregisterAll(this);

        // 事後処理
        // 無効化メッセージ
        getServer().getLogger().info(Message.ITEMSORT_M0002_I.getMessage().replace("%プラグイン名%", getName()));
    }

    /**
     * コマンドロードメソッド
     */
    private void setupCommands() {
        //region 初期処理
        // メッセージ出力
        getLogger().info(Message.ITEMSORT_M0003_I.getMessage());

        // ロジック定義　executorセット
        BiConsumer<String, CommandExecutor> loadProcess = (commandName, executor) -> {
            PluginCommand command = getCommand(commandName);

            if(command != null) {
                command.setExecutor(executor);
            } else {
                throw new NotCommandException();
            }
        };

        //endregion 初期処理

        // 主処理
        try {
            // ロードしたいコマンドをここに追記していく
            loadProcess.accept("Sort", new SortCommand(this));
        }
        catch (NotCommandException ex) {
            // プラグイン終了
            getServer().getLogger().log(Level.SEVERE, Message.ITEMSORT_M0001_E.getMessage(), ex);

            getServer().getPluginManager().disablePlugin(this);

            return;
        }

        // 事後処理
        // メッセージ出力
        getLogger().info(Message.ITEMSORT_M0004_I.getMessage());

    }

    /**
     * イベントハンドルロード処理
     */
    private void setupEvents(){
        // 初期処理
        getLogger().info(Message.ITEMSORT_M0005_I.getMessage());

        // ロードしたいコマンドをここに追記していく
        new Sort(this);

        // 終了処理
        getLogger().info(Message.ITEMSORT_M0006_I.getMessage());
    }
    //endregion メソッド宣言部
}
//endregion ItemSort
