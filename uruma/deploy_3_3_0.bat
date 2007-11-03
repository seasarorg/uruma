@rem eclipse 3.3 関連の依存ライブラリを maven リポジトリへアップロードするためのバッチ

@rem set MVN=call mvn deploy:deploy-file -Dpackaging=jar -DrepositoryId=maven.seasar.org -Durl=scp://www.seasar.org/home/groups/maven/maven/maven2
set MVN=call mvn deploy:deploy-file -Dpackaging=jar -DrepositoryId=maven.seasar.org -Durl=dav:http://www.seasar.org/maven/maven2
set GROUPID=-DgroupId=org.eclipse
set VER=-Dversion=3.3.0
set ECLIPSE=-Dfile=C:\eclipse\plugins\

@rem %MVN% %GROUPID% %VER% -DartifactId=ui-workbench %ECLIPSE%org.eclipse.ui.workbench_3.3.0.I20070608-1100.jar
@rem %MVN% %GROUPID% %VER% -DartifactId=ui %ECLIPSE%org.eclipse.ui_3.3.0.I20070614-0800.jar
@rem %MVN% %GROUPID% %VER% -DartifactId=ui-texteditor %ECLIPSE%org.eclipse.ui.workbench.texteditor_3.3.0.v20070606-0010.jar
@rem %MVN% %GROUPID% %VER% -DartifactId=core-commands %ECLIPSE%org.eclipse.core.commands_3.3.0.I20070605-0010.jar
@rem %MVN% %GROUPID% %VER% -DartifactId=core-contenttype %ECLIPSE%org.eclipse.core.contenttype_3.2.100.v20070319.jar
@rem %MVN% %GROUPID% %VER% -DartifactId=core-filebuffers %ECLIPSE%org.eclipse.core.filebuffers_3.3.0.v20070606-0010.jar
@rem %MVN% %GROUPID% %VER% -DartifactId=core-jobs %ECLIPSE%org.eclipse.core.jobs_3.3.0.v20070423.jar
@rem %MVN% %GROUPID% %VER% -DartifactId=core-resources %ECLIPSE%org.eclipse.core.resources_3.3.0.v20070604.jar
@rem %MVN% %GROUPID% %VER% -DartifactId=core-runtime %ECLIPSE%org.eclipse.core.runtime_3.3.100.v20070530.jar
@rem %MVN% %GROUPID% %VER% -DartifactId=debug-core %ECLIPSE%org.eclipse.debug.core_3.3.0.v20070607-1800.jar
%MVN% %GROUPID% %VER% -DartifactId=equinox-app %ECLIPSE%org.eclipse.equinox.app_1.0.0.v20070606.jar
@rem %MVN% %GROUPID% %VER% -DartifactId=equinox-common %ECLIPSE%org.eclipse.equinox.common_3.3.0.v20070426.jar
@rem %MVN% %GROUPID% %VER% -DartifactId=equinox-preferences %ECLIPSE%org.eclipse.equinox.preferences_3.2.100.v20070522.jar
@rem %MVN% %GROUPID% %VER% -DartifactId=equinox-registry %ECLIPSE%org.eclipse.equinox.registry_3.3.0.v20070522.jar
@rem %MVN% %GROUPID% %VER% -DartifactId=jdt-core %ECLIPSE%org.eclipse.jdt.core_3.3.0.v_771.jar
@rem %MVN% %GROUPID% %VER% -DartifactId=jdt-launching %ECLIPSE%org.eclipse.jdt.launching_3.3.0.v20070510.jar
@rem %MVN% %GROUPID% %VER% -DartifactId=jface %ECLIPSE%org.eclipse.jface_3.3.0.I20070606-0010.jar
@rem %MVN% %GROUPID% %VER% -DartifactId=osgi %ECLIPSE%org.eclipse.osgi_3.3.0.v20070530.jar
@rem %MVN% %GROUPID% %VER% -DartifactId=swt %ECLIPSE%org.eclipse.swt_3.3.0.v3346.jar
@rem %MVN% %GROUPID% %VER% -DartifactId=swt-win32-win32-x86 %ECLIPSE%org.eclipse.swt.win32.win32.x86_3.3.0.v3346.jar
@rem %MVN% %GROUPID% %VER% -DartifactId=core-runtime-compatibility-registry %ECLIPSE%org.eclipse.core.runtime.compatibility.registry_3.2.100.v20070316\runtime_registry_compatibility.jar
