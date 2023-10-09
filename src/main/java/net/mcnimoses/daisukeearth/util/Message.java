package net.mcnimoses.daisukeearth.util;

/*==================================================================*/
// インポート宣言
/*==================================================================*/
// 外部ライブラリ
import net.mcnimoses.daisukeearth.util.local.*;

/**##################################################################*
 * 列挙体名 : Messages <br>
 * 役割     : 本プラグインのメッセージを定義 <br>
 *###################################################################*/
public enum Message {
    // 列挙体設定部
    ITEMSORT_M0001_I("%プラグイン名%を有効化しました。"),
    ITEMSORT_M0002_I("%プラグイン名%を無効化しました。"),
    ITEMSORT_M0003_I("コマンドセットアップ処理 開始"),
    ITEMSORT_M0004_I("コマンドセットアップ処理 正常終了"),
    ITEMSORT_M0005_I("イベントハンドルセットアップ処理 開始"),
    ITEMSORT_M0006_I("イベントハンドルセットアップ処理 正常終了"),
    ITEMSORT_M0001_E("コマンドセットアップ処理が異常終了しました。"),
    SORT_M0001_I("クリックしたチェストをソートしました。"),
    SORTCOMMAND_M0001_I("インベントリをソートしました。"),
    SORTCOMMAND_M0001_W("コンソールからは実行できません。");

    //region メンバ定数
    private final String MSG_JA;
    private final static Local DEFAULT_LOCAL;

    //endregion メンバ定数

    /*------------------------------*
     * staticブロック
     *------------------------------*/
    static{
        // デフォルトは日本語という方針は変えない
        DEFAULT_LOCAL = new Ja();
    }

    //region メソッド宣言部

    /**
     * コンストラクタ
     * @param japanese 列挙体第一引数(日本語)
     */
    Message(String japanese){
        this.MSG_JA = japanese;
    }

    /**
     * メッセージ取得メソッド<br>
     * ※デフォルトロケーションの文字列を戻します。
     * @return メッセージ文字列
     */
    public String getMessage(){
        // 主処理
        return getMessage(DEFAULT_LOCAL);
    }

    /**
     * メッセージ取得メソッド
     * @param local ロケーションマーカークラス
     * @return メッセージ文字列
     */
    public String getMessage(Local local){
        // 初期処理
        String returnStr;

        // 主処理
        if(local instanceof Ja){
            returnStr = this.MSG_JA;
        }
        // 今後言語を増やす場合は条件を追加する
        else{
            // --再起処理-- 設定ミスで存在しない言語だった場合でデフォルト言語で設定する
            returnStr = getMessage(DEFAULT_LOCAL);
        }

        // 事後処理
        return returnStr;
    }

    //endregion メソッド宣言部
}
