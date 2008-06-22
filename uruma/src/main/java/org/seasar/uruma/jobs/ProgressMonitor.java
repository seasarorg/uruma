/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.uruma.jobs;

/**
 * ジョブの進捗を通知するためのインタフェースです。<br />
 * 
 * @author y-komori
 */
public interface ProgressMonitor {
    /**
     * ジョブの総量が不明であることを示す定数です。<br />
     */
    public final static int UNKNOWN = -1;

    /**
     * メインタスクの開始を通知します。<br />
     * 
     * @param name
     *            メインタスクの名称
     * @param totalWork
     *            メインタスクの総量。値が <code>UNKNOWN</code> の場合、総量が不明であることを示します。
     */
    public void beginTask(String name, int totalWork);

    /**
     * メインタスクの終了を通知します。<br />
     */
    public void done();

    /**
     * タスクがキャンセルされたかどうかを返します。<br />
     * 
     * @return タスクがキャンセルされた場合、<code>true</code>。そうでない場合は <code>false</code>。
     * @see #setCanceled(boolean)
     */
    public boolean isCanceled();

    /**
     * キャンセル状態を設定します。<br />
     * 
     * @param value
     *            キャンセルが要求されている場合、<code>true</code>。そうでない場合、
     *            <code>false</code>。
     * @see #isCanceled()
     */
    public void setCanceled(boolean value);

    /**
     * タスク名称を設定します。<br />
     * 
     * @param name
     *            タスク名称
     * @see #beginTask(java.lang.String, int)
     */
    public void setTaskName(String name);

    /**
     * サブタスクの開始を通知します。<br />
     * サブタスクの利用はオプションですので、必ずしも利用する必要はありません。
     * 
     * @param name
     *            サブタスクの名称
     */
    public void subTask(String name);

    /**
     * 指定された値を、タスクの完了値として通知します。<br />
     * 
     * @param work
     *            完了したタスク量。(負でない整数)
     */
    public void worked(int work);

}
