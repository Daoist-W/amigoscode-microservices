@echo off

for /d %%d in (*) do (
  echo directory: "%%d"
  cd "%%d"
  for /d %%d in (*) do (
       cd "%%d"
       for %%f in (*.yml) do (
         echo creating "%%f"
         kubectl apply -f "%%f"
       )
       cd ..
  )
  cd ..
)