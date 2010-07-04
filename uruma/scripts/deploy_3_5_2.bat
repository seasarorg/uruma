@echo off

echo eclipse 3.5.2 関連の依存ライブラリを maven リポジトリへアップロードするためのバッチ
echo 【注意】Uruma プロジェクトのディレクトリ上で実行してください
echo maven アカウントを持たない方は実行できません

pause

set MVNJ=call mvn deploy:deploy-file -Dpackaging=jar -DrepositoryId=maven.seasar.org -Durl=dav:https://www.seasar.org/maven/maven2
set MVNS=call mvn deploy:deploy-file -Dpackaging=java-source -DgeneratePom=false -DrepositoryId=maven.seasar.org -Durl=dav:https://www.seasar.org/maven/maven2
set GROUPID=-DgroupId=org.eclipse
set VER=-Dversion=3.5.2
set ECLIPSE=-Dfile=C:\eclipse\plugins\

%MVNJ% %GROUPID% %VER% -DartifactId=ui %ECLIPSE%org.eclipse.ui_3.5.2.M20100120-0800.jar
%MVNS% %GROUPID% %VER% -DartifactId=ui %ECLIPSE%org.eclipse.ui.source_3.5.2.M20100120-0800.jar
%MVNJ% %GROUPID% %VER% -DartifactId=ui-workbench %ECLIPSE%org.eclipse.ui.workbench_3.5.2.M20100113-0800.jar
%MVNS% %GROUPID% %VER% -DartifactId=ui-workbench %ECLIPSE%org.eclipse.ui.workbench.source_3.5.2.M20100113-0800.jar
%MVNJ% %GROUPID% %VER% -DartifactId=ui-workbench-texteditor %ECLIPSE%org.eclipse.ui.workbench.texteditor_3.5.1.r352_v20100105.jar
%MVNS% %GROUPID% %VER% -DartifactId=ui-workbench-texteditor %ECLIPSE%org.eclipse.ui.workbench.texteditor.source_3.5.1.r352_v20100105.jar
%MVNJ% %GROUPID% %VER% -DartifactId=core-commands %ECLIPSE%org.eclipse.core.commands_3.5.0.I20090525-2000.jar
%MVNS% %GROUPID% %VER% -DartifactId=core-commands %ECLIPSE%org.eclipse.core.commands.source_3.5.0.I20090525-2000.jar
%MVNJ% %GROUPID% %VER% -DartifactId=core-contenttype %ECLIPSE%org.eclipse.core.contenttype_3.4.1.R35x_v20090826-0451.jar
%MVNS% %GROUPID% %VER% -DartifactId=core-contenttype %ECLIPSE%org.eclipse.core.contenttype.source_3.4.1.R35x_v20090826-0451.jar
%MVNJ% %GROUPID% %VER% -DartifactId=core-expressions %ECLIPSE%org.eclipse.core.expressions_3.4.101.R35x_v20100209.jar
%MVNS% %GROUPID% %VER% -DartifactId=core-expressions %ECLIPSE%org.eclipse.core.expressions.source_3.4.101.R35x_v20100209.jar
%MVNJ% %GROUPID% %VER% -DartifactId=core-filebuffers %ECLIPSE%org.eclipse.core.filebuffers_3.5.0.v20090526-2000.jar
%MVNS% %GROUPID% %VER% -DartifactId=core-filebuffers %ECLIPSE%org.eclipse.core.filebuffers.source_3.5.0.v20090526-2000.jar
%MVNJ% %GROUPID% %VER% -DartifactId=core-jobs %ECLIPSE%org.eclipse.core.jobs_3.4.100.v20090429-1800.jar
%MVNS% %GROUPID% %VER% -DartifactId=core-jobs %ECLIPSE%org.eclipse.core.jobs.source_3.4.100.v20090429-1800.jar
%MVNJ% %GROUPID% %VER% -DartifactId=core-resources %ECLIPSE%org.eclipse.core.resources_3.5.2.R35x_v20091203-1235.jar
%MVNS% %GROUPID% %VER% -DartifactId=core-resources %ECLIPSE%org.eclipse.core.resources.source_3.5.2.R35x_v20091203-1235.jar
%MVNJ% %GROUPID% %VER% -DartifactId=core-runtime %ECLIPSE%org.eclipse.core.runtime_3.5.0.v20090525.jar
%MVNS% %GROUPID% %VER% -DartifactId=core-runtime %ECLIPSE%org.eclipse.core.runtime.source_3.5.0.v20090525.jar
%MVNJ% %GROUPID% %VER% -DartifactId=debug-core %ECLIPSE%org.eclipse.debug.core_3.5.1.v20091103_r352.jar
%MVNS% %GROUPID% %VER% -DartifactId=debug-core %ECLIPSE%org.eclipse.debug.core.source_3.5.1.v20091103_r352.jar
%MVNJ% %GROUPID% %VER% -DartifactId=equinox-app %ECLIPSE%org.eclipse.equinox.app_1.2.1.R35x_v20091203.jar
%MVNS% %GROUPID% %VER% -DartifactId=equinox-app %ECLIPSE%org.eclipse.equinox.app.source_1.2.1.R35x_v20091203.jar
%MVNJ% %GROUPID% %VER% -DartifactId=equinox-common %ECLIPSE%org.eclipse.equinox.common_3.5.1.R35x_v20090807-1100.jar
%MVNS% %GROUPID% %VER% -DartifactId=equinox-common %ECLIPSE%org.eclipse.equinox.common.source_3.5.1.R35x_v20090807-1100.jar
%MVNJ% %GROUPID% %VER% -DartifactId=equinox-preferences %ECLIPSE%org.eclipse.equinox.preferences_3.2.301.R35x_v20091117.jar
%MVNS% %GROUPID% %VER% -DartifactId=equinox-preferences %ECLIPSE%org.eclipse.equinox.preferences.source_3.2.301.R35x_v20091117.jar
%MVNJ% %GROUPID% %VER% -DartifactId=equinox-registry %ECLIPSE%org.eclipse.equinox.registry_3.4.100.v20090520-1800.jar
%MVNS% %GROUPID% %VER% -DartifactId=equinox-registry %ECLIPSE%org.eclipse.equinox.registry.source_3.4.100.v20090520-1800.jar
%MVNJ% %GROUPID% %VER% -DartifactId=jdt-core %ECLIPSE%org.eclipse.jdt.core_3.5.2.v_981_R35x.jar
%MVNS% %GROUPID% %VER% -DartifactId=jdt-core %ECLIPSE%org.eclipse.jdt.core.source_3.5.2.v_981_R35x.jar
%MVNJ% %GROUPID% %VER% -DartifactId=jdt-launching %ECLIPSE%org.eclipse.jdt.launching_3.5.1.v20100108_r352.jar
%MVNS% %GROUPID% %VER% -DartifactId=jdt-launching %ECLIPSE%org.eclipse.jdt.launching.source_3.5.1.v20100108_r352.jar
%MVNJ% %GROUPID% %VER% -DartifactId=jface %ECLIPSE%org.eclipse.jface_3.5.2.M20100120-0800.jar
%MVNS% %GROUPID% %VER% -DartifactId=jface %ECLIPSE%org.eclipse.jface.source_3.5.2.M20100120-0800.jar
%MVNJ% %GROUPID% %VER% -DartifactId=osgi %ECLIPSE%org.eclipse.osgi_3.5.2.R35x_v20100126.jar
%MVNS% %GROUPID% %VER% -DartifactId=osgi %ECLIPSE%org.eclipse.osgi.source_3.5.2.R35x_v20100126.jar
%MVNJ% %GROUPID% %VER% -DartifactId=swt %ECLIPSE%org.eclipse.swt_3.5.2.v3557f.jar
%MVNJ% %GROUPID% %VER% -DartifactId=swt-win32-win32-x86 %ECLIPSE%org.eclipse.swt.win32.win32.x86_3.5.2.v3557f.jar
%MVNS% %GROUPID% %VER% -DartifactId=swt-win32-win32-x86 %ECLIPSE%org.eclipse.swt.win32.win32.x86.source_3.5.2.v3557f.jar
%MVNJ% %GROUPID% %VER% -DartifactId=core-runtime-compatibility-registry %ECLIPSE%org.eclipse.core.runtime.compatibility.registry_3.2.200.v20090429-1800\runtime_registry_compatibility.jar
%MVNS% %GROUPID% %VER% -DartifactId=core-runtime-compatibility-registry %ECLIPSE%org.eclipse.core.runtime.compatibility.registry.source_3.2.200.v20090429-1800.jar
pause
