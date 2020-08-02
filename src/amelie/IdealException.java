package amelie;

import java.io.Serializable;

public class IdealException extends Exception implements Serializable {
    private static final long serialVersionUID            = 1L;
    private int               errCd;
    private String            ERR_MSG[]                   = { "障害が発生しました",
            "データベース処理で例外が発生しました", "お客様ID、パスワードを確認してください",
            "管理者名、パスワードを確認してください", "ご指定された日時に、空席はございませんでした",
            "予約されているコースなので削除できません", "コースに登録されているメニューなので削除できません",
            "ご指定されたお客様IDは、すでに利用中のため登録できませんでした","ご予約情報がある為、退会できません。"           };
    public static final int   ERR_NO_EXCEPTION            = 0;
    public static final int   ERR_NO_DB_EXCEPTION         = 1;
    public static final int   ERR_NO_NOT_MEMBER_EXCEPTION = 2;
    public static final int   ERR_NO_ADMIN_EXCEPTION      = 3;
    public static final int   ERR_NO_NOT_VACANCY          = 4;
    public static final int   ERR_NO_NOT_RESERV_DELETE    = 5;
    public static final int   ERR_NO_NOT_MENU_DELETE      = 6;
    public static final int   ERR_ID_ZYUUHUKU             = 7;
    public static final int   ERR_NO_USER_DELETE             = 8;

    public IdealException() {
        super();
    }

    public IdealException(int errCd) {
        this.errCd = errCd;

    }

    public String getMsg() {
        return ERR_MSG[errCd];

    }

}
