@echo off
setlocal enabledelayedexpansion

set /p "dir_path=Enter the directory path: "

for /d %%a in ("%dir_path%\*") do (
  set "subdir_path=%%a"
  echo Deleting from !subdir_path!
  kubectl delete -f "!subdir_path!"
)
