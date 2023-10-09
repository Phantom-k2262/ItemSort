/**
 * モジュール定義
 */
module net.mcnimoses.daisukeearth.ItemSort{
    // 公開モジュール
    exports net.mcnimoses.daisukeearth to org.bukkit;

    /* サブパッケージは基本非公開 */

    // 依存関係定義
    requires org.bukkit;                    // bukkitモジュール
    requires org.jetbrains.annotations;     // チェッカーフレームワーク(アノテーションのみ使用)
    requires net.kyori.adventure;           // チャットカラー用
    requires java.logging;

    /* bukkitはモジュール化対応していないため、暗黙的に作られるbukkitだけ要求する(自動モジュール) */

}