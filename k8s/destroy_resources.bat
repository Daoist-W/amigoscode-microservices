@echo off

for /d %%d in (*) do (
  echo directory: "%%d"
  cd "%%d"
  for /d %%d in (*) do (
       cd "%%d"
       for %%f in (*.yml) do (
         echo deleting "%%f"
         kubectl delete -f "%%f"
       )
       cd ..
  )
  cd ..
)
