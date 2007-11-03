D:
cd \develop\uruma
call mvn install

rem copy C:\.m2\repository\org\seasar\container\s2-framework\2.4.14-SNAPSHOT\s2-framework-2.4.14-SNAPSHOT.jar D:\Develop\org.seasar.rcp.example.filemanager\lib
copy C:\.m2\repository\org\seasar\uruma\uruma\0.2.0-SNAPSHOT\uruma-0.2.0-SNAPSHOT.jar D:\Develop\org.seasar.uruma.example.filemanager\lib
copy C:\.m2\repository\org\seasar\uruma\uruma\0.2.0-SNAPSHOT\uruma-0.2.0-SNAPSHOT-sources.jar D:\Develop\org.seasar.uruma.example.filemanager\lib

pause
