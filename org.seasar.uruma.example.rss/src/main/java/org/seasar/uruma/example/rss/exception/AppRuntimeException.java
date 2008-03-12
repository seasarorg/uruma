package org.seasar.uruma.example.rss.exception;

import org.seasar.uruma.exception.UrumaRuntimeException;

/**
 * Urumaアプリで利用する汎用ランタイム例外のクラスです。<br />
 * 
 * @author y-sugigami
 */
public class AppRuntimeException extends UrumaRuntimeException {

    /**
	 * UID
	 */
	private static final long serialVersionUID = 4514962502770365234L;

	/**
     * {@link UrumaRuntimeException} を構築します。<br />
     * 
     * @param messageCode
     *            メッセージコード
     */
    public AppRuntimeException(final String messageCode) {
        super(messageCode);
    }

    /**
     * {@link UrumaRuntimeException} を構築します。<br />
     * 
     * @param messageCode
     *            メッセージコード
     * @param cause
     *            原因となった例外オブジェクト
     * @param args
     *            メッセージ引数
     */
    public AppRuntimeException(final String messageCode,
            final Throwable cause, final Object... args) {
        super(messageCode, args, cause);
    }

    /**
     * {@link UrumaRuntimeException} を構築します。<br />
     * 
     * @param messageCode
     *            メッセージコード
     * @param args
     *            メッセージ引数
     */
    public AppRuntimeException(final String messageCode, final Object... args) {
        super(messageCode, args);
    }

}
