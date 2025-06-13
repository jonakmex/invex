#!/bin/bash

BASE_DATE="2025-06-13"
START_HOUR=8
INCREMENT_MIN=10

echo "Recolectando commits..."
commits=$(git rev-list --reverse --topo-order --all)

# Crear archivo temporal para rebase
REBASE_SCRIPT=".git-rewrite-date.sh"
echo "#!/bin/bash" > $REBASE_SCRIPT
chmod +x $REBASE_SCRIPT

index=0

for commit in $commits; do
  total_minutes=$((START_HOUR * 60 + index * INCREMENT_MIN))

  hours=$((total_minutes / 60))
  minutes=$((total_minutes % 60))

  # Convertir a HH:MM
  time_formatted=$(printf "%02d:%02d" "$hours" "$minutes")

  # Crear fecha en formato para GIT_DATE
  formatted_date=$(date -u -j -f "%Y-%m-%d %H:%M" "$BASE_DATE $time_formatted" +"%a %b %d %H:%M:%S %Y +0000")

  echo "export GIT_COMMITTER_DATE=\"$formatted_date\"" >> $REBASE_SCRIPT
  echo "export GIT_AUTHOR_DATE=\"$formatted_date\"" >> $REBASE_SCRIPT
  echo "git commit --amend --no-edit --date=\"$formatted_date\"" >> $REBASE_SCRIPT

  index=$((index + 1))
done

echo "⏳ Iniciando rebase..."
GIT_SEQUENCE_EDITOR="true" git rebase --root -x "./$REBASE_SCRIPT"

rm $REBASE_SCRIPT

echo "✅ Fechas actualizadas. Revisa con:"
echo "git log --pretty=format:'%h %ad %s' --date=iso"

read -p "¿Hacer push --force al remoto? (si/no): " push_confirm
if [[ "$push_confirm" == "si" ]]; then
  branch=$(git rev-parse --abbrev-ref HEAD)
  git push origin "$branch" --force
  echo "✅ Push forzado exitoso."
else
  echo "⏹️  Push cancelado."
fi
