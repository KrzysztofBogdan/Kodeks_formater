# Formater kodeksów

Teksty kodeksów zostały pobrane z http://www.lex.pl/kodeksy

## Uruchomienie

W pl.codeweaver.kodeks.formater.regexp.EntryPoint trzeba uruchomić funkcję `main`

## Jak teksty zostały przygotowane?

Po wejściu na strone z kodeksem (np. http://www.lex.pl/akt-prawny/-/akt/dz-u-2015-583-u)

trzeba uruchomić skrypt:
```javascript
// Opakowuje indeks w []
var index = document.querySelectorAll('span[style*=vertical-align\\:super\\;font-size\\:smaller\\;]');

for (var i of index) {
  i.textContent = "[" + i.textContent + "]";
}
```

Zaznaczyć kursorem cały tekst kodeksu i skopiować. Przetestowane z przeglądarką Firefox.

Skopiowane teksty są w `src/main/resources`