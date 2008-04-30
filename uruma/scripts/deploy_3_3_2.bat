@echo off

echo eclipse 3.3.2 関連の依存ライブラリを maven リポジトリへアップロードするためのバッチ
echo 【注意】Uruma プロジェクトのディレクトリ上で実行してください
echo maven アカウントを持たない方は実行できません

pause

@rem set MVN=call mvn deploy:deploy-file -Dpackaging=jar -DrepositoryId=maven.seasar.org -Durl=scp://www.seasar.org/home/groups/maven/maven/maven2
set MVN=call mvn deploy:deploy-file -Dpackaging=jar -DrepositoryId=maven.seasar.org -Durl=dav:https://www.seasar.org/maven/maven2
set GROUPID=-DgroupId=org.eclipse
set VER=-Dversion=3.3.2
set ECLIPSE=-Dfile=C:\eclipse\plugins\

%MVN% %GROUPID% %VER% -DartifactId=ui-workbench %ECLIPSE%org.eclipse.ui.workbench_3.3.2.M20080207-0800.jar
%MVN% %GROUPID% %VER% -DartifactId=ui %ECLIPSE%org.eclipse.ui_3.3.1.M20071128-0800.jar
%MVN% %GROUPID% %VER% -DartifactId=ui-texteditor %ECLIPSE%org.eclipse.ui.workbench.texteditor_3.3.2.r332_20080109-0800.jar
%MVN% %GROUPID% %VER% -DartifactId=core-commands %ECLIPSE%org.eclipse.core.commands_3.3.0.I20070605-0010.jar
%MVN% %GROUPID% %VER% -DartifactId=core-contenttype %ECLIPSE%org.eclipse.core.contenttype_3.2.100.v20070319.jar
%MVN% %GROUPID% %VER% -DartifactId=core-expressions %ECLIPSE%org.eclipse.core.expressions_3.3.0.v20070606-0010.jar
%MVN% %GROUPID% %VER% -DartifactId=core-filebuffers %ECLIPSE%org.eclipse.core.filebuffers_3.3.1.r331_v20070829.jar
%MVN% %GROUPID% %VER% -DartifactId=core-jobs %ECLIPSE%org.eclipse.core.jobs_3.3.1.R33x_v20070709.jar
%MVN% %GROUPID% %VER% -DartifactId=core-resources %ECLIPSE%org.eclipse.core.resources_3.3.1.R33x_v20080205.jar
%MVN% %GROUPID% %VER% -DartifactId=core-runtime %ECLIPSE%org.eclipse.core.runtime_3.3.100.v20070530.jar
%MVN% %GROUPID% %VER% -DartifactId=debug-core %ECLIPSE%org.eclipse.debug.core_3.3.2.v20080117_r332.jar
%MVN% %GROUPID% %VER% -DartifactId=equinox-app %ECLIPSE%org.eclipse.equinox.app_1.0.1.R33x_v20070828.jar
%MVN% %GROUPID% %VER% -DartifactId=equinox-common %ECLIPSE%org.eclipse.equinox.common_3.3.0.v20070426.jar
%MVN% %GROUPID% %VER% -DartifactId=equinox-preferences %ECLIPSE%org.eclipse.equinox.preferences_3.2.101.R33x_v20080117.jar
%MVN% %GROUPID% %VER% -DartifactId=equinox-registry %ECLIPSE%org.eclipse.equinox.registry_3.3.1.R33x_v20070802.jar
%MVN% %GROUPID% %VER% -DartifactId=jdt-core %ECLIPSE%org.eclipse.jdt.core_3.3.3.v_793_R33x.jar
%MVN% %GROUPID% %VER% -DartifactId=jdt-launching %ECLIPSE%org.eclipse.jdt.launching_3.3.2.v20080115_r332.jar
%MVN% %GROUPID% %VER% -DartifactId=jface %ECLIPSE%org.eclipse.jface_3.3.2.M20080207-0800.jar
%MVN% %GROUPID% %VER% -DartifactId=osgi %ECLIPSE%org.eclipse.osgi_3.3.2.R33x_v20080105.jar
%MVN% %GROUPID% %VER% -DartifactId=swt %ECLIPSE%org.eclipse.swt_3.3.2.v3349d.jar
%MVN% %GROUPID% %VER% -DartifactId=swt-win32-win32-x86 %ECLIPSE%org.eclipse.swt.win32.win32.x86_3.3.3.v3349.jar
%MVN% %GROUPID% %VER% -DartifactId=core-runtime-compatibility-registry %ECLIPSE%org.eclipse.core.runtime.compatibility.registry_3.2.100.v20070316\runtime_registry_compatibility.jar

%MVN% %GROUPID% %VER% -DartifactId=ui-workbench-source %ECLIPSE%org.eclipse.rcp.source_3.3.3.r33x_r20080129-8y8eE9UEUWI6qujeED0xT7bc\src\org.eclipse.ui.workbench_3.3.2.M20080207-0800\src.zip
%MVN% %GROUPID% %VER% -DartifactId=ui-source %ECLIPSE%org.eclipse.rcp.source_3.3.3.r33x_r20080129-8y8eE9UEUWI6qujeED0xT7bc\src\org.eclipse.ui_3.3.1.M20071128-0800\src.zip
%MVN% %GROUPID% %VER% -DartifactId=ui-texteditor-source %ECLIPSE%org.eclipse.platform.source_3.3.3.r33x_r20080129-_19UEl7Ezk_gXF1kouft\src\org.eclipse.ui.workbench.texteditor_3.3.2.r332_20080109-0800\src.zip
%MVN% %GROUPID% %VER% -DartifactId=core-commands-source %ECLIPSE%org.eclipse.rcp.source_3.3.3.r33x_r20080129-8y8eE9UEUWI6qujeED0xT7bc\src\org.eclipse.core.commands_3.3.0.I20070605-0010\src.zip
%MVN% %GROUPID% %VER% -DartifactId=core-contenttype-source %ECLIPSE%org.eclipse.rcp.source_3.3.3.r33x_r20080129-8y8eE9UEUWI6qujeED0xT7bc\src\org.eclipse.core.contenttype_3.2.100.v20070319\src.zip
%MVN% %GROUPID% %VER% -DartifactId=core-expressions-source %ECLIPSE%org.eclipse.rcp.source_3.3.3.r33x_r20080129-8y8eE9UEUWI6qujeED0xT7bc\src\org.eclipse.core.expressions_3.3.0.v20070606-0010\src.zip
%MVN% %GROUPID% %VER% -DartifactId=core-jobs-source %ECLIPSE%org.eclipse.rcp.source_3.3.3.r33x_r20080129-8y8eE9UEUWI6qujeED0xT7bc\src\org.eclipse.core.jobs_3.3.1.R33x_v20070709\src.zip
%MVN% %GROUPID% %VER% -DartifactId=core-runtime-source %ECLIPSE%org.eclipse.rcp.source_3.3.3.r33x_r20080129-8y8eE9UEUWI6qujeED0xT7bc\src\org.eclipse.core.runtime_3.3.100.v20070530\src.zip
%MVN% %GROUPID% %VER% -DartifactId=equinox-app-source %ECLIPSE%org.eclipse.rcp.source_3.3.3.r33x_r20080129-8y8eE9UEUWI6qujeED0xT7bc\src\org.eclipse.equinox.app_1.0.1.R33x_v20070828\src.zip
%MVN% %GROUPID% %VER% -DartifactId=equinox-common-source %ECLIPSE%org.eclipse.rcp.source_3.3.3.r33x_r20080129-8y8eE9UEUWI6qujeED0xT7bc\src\org.eclipse.equinox.common_3.3.0.v20070426\src.zip
%MVN% %GROUPID% %VER% -DartifactId=equinox-preferences-source %ECLIPSE%org.eclipse.rcp.source_3.3.3.r33x_r20080129-8y8eE9UEUWI6qujeED0xT7bc\src\org.eclipse.equinox.preferences_3.2.101.R33x_v20080117\src.zip
%MVN% %GROUPID% %VER% -DartifactId=equinox-registry-source %ECLIPSE%org.eclipse.rcp.source_3.3.3.r33x_r20080129-8y8eE9UEUWI6qujeED0xT7bc\src\org.eclipse.equinox.registry_3.3.1.R33x_v20070802\src.zip
%MVN% %GROUPID% %VER% -DartifactId=jface-source %ECLIPSE%org.eclipse.rcp.source_3.3.3.r33x_r20080129-8y8eE9UEUWI6qujeED0xT7bc\src\org.eclipse.jface_3.3.2.M20080207-0800\src.zip
%MVN% %GROUPID% %VER% -DartifactId=osgi-source %ECLIPSE%org.eclipse.rcp.source_3.3.3.r33x_r20080129-8y8eE9UEUWI6qujeED0xT7bc\src\org.eclipse.osgi_3.3.2.R33x_v20080105\src.zip
%MVN% %GROUPID% %VER% -DartifactId=swt-win32-win32-x86-source %ECLIPSE%org.eclipse.rcp.source.win32.win32.x86_3.3.3.r33x_r20080129-8y8eE9UEUWI6qujeED0xT7bc\src\org.eclipse.swt.win32.win32.x86_3.3.3.v3349\src.zip
%MVN% %GROUPID% %VER% -DartifactId=core-filebuffers-source %ECLIPSE%org.eclipse.platform.source_3.3.3.r33x_r20080129-_19UEl7Ezk_gXF1kouft\src\org.eclipse.core.filebuffers_3.3.1.r331_v20070829\src.zip
%MVN% %GROUPID% %VER% -DartifactId=core-resources-source %ECLIPSE%org.eclipse.platform.source_3.3.3.r33x_r20080129-_19UEl7Ezk_gXF1kouft\src\org.eclipse.core.resources_3.3.1.R33x_v20080205\src.zip
%MVN% %GROUPID% %VER% -DartifactId=debug-core-source %ECLIPSE%org.eclipse.platform.source_3.3.3.r33x_r20080129-_19UEl7Ezk_gXF1kouft\src\org.eclipse.debug.core_3.3.2.v20080117_r332\src.zip
%MVN% %GROUPID% %VER% -DartifactId=jdt-core-source %ECLIPSE%org.eclipse.jdt.source_3.3.2.r33x_r20080129-7o7jE7_EDhYDiyVEnjb1pFd7ZGD7\src\org.eclipse.jdt.core_3.3.3.v_793_R33x\src.zip
%MVN% %GROUPID% %VER% -DartifactId=jdt-launching-source %ECLIPSE%org.eclipse.jdt.source_3.3.2.r33x_r20080129-7o7jE7_EDhYDiyVEnjb1pFd7ZGD7\src\org.eclipse.jdt.launching_3.3.2.v20080115_r332\src.zip

%MVN% %GROUPID% %VER% -DartifactId=core-runtime-compatibility-registry-source %ECLIPSE%org.eclipse.platform.source_3.3.3.r33x_r20080129-_19UEl7Ezk_gXF1kouft\src\org.eclipse.core.runtime.compatibility.registry_3.2.100.v20070316\runtime_registry_compatibilitysrc.zip
pause
