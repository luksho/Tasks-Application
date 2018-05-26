call runcrud
if "%ERRORLEVEL%" == "0" goto startBrowser
echo.
echo runcrud cannot start - breaking work
goto fail

:startBrowser
start chrome.exe http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto showTasksOk
echo Cannot open browser
goto fail

:showTasksOk
echo Showing tasks finished
goto end


:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.