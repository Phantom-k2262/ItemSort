package net.mcnimoses.daisukeearth.process;

/*==================================================================*/
//region インポート宣言
/*==================================================================*/
// 外部ライブラリ
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

// 標準ライブラリ
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;

//endregion インポート宣言

/**##################################################################*
 * インターフェース名 : SortAPI <br>
 * 役割              : ヘルプメッセージを表示する(未実装) <br>
 * 継承              : org.bukkit.event.Listener
 *###################################################################*/
public interface SortAPI extends Listener{
    // 関数型インターフェース
    /**
     * ソート処理 <br>
     * 引数   ItemStack[] <br>
     * 戻り値 ItemStack[]
     */
    Function<ItemStack[], ItemStack[]> sortProcess = items -> {
        // nullは必ず後ろにするためZZZZZ
        return Arrays.stream(items)                                              // 開始処理(stream変換)
                .sorted(Comparator.comparing(item ->
                            item != null ? item.getType().toString() : "ZZZZZ")) // 中間処理(ソート)
                .toArray(ItemStack[]::new);                                      // 終端処理(ソートしたリストを返す)
    };
}
