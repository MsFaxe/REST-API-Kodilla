call runcrud.bat
if "%ERRORLEVEL%" == "0" goto start
echo.
echo Cannot run runcrud.bat
goto fail

:start
cd "C:\Program Files (x86)\Google\Chrome\Application"
start chrome.exe http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo Cannot start browser
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.