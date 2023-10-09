package net.mcnimoses.daisukeearth.exception;

/**##################################################################*
 * クラス名 : NotCommandException <br>
 * 役割     : コマンドが見つからない場合の例外 <br>
 * 継承     : java.lang.RuntimeException
 *###################################################################*/
public class NotCommandException extends RuntimeException{
    public NotCommandException (){
        super("コマンドが見つかりません。クラスが存在しているか、ymlにコマンドの登録をしているか確認してください。");
    }
}

