# Ralc commands

- `echo` - prints out the element at the top of the stack (stack remains unchanged)
- `pop` - removes top element from the stack
- `dup` - duplicates top element (x => x x)
- `dup2` - duplicates top 2 elements (x y => x y x y)
- `swap` - swaps top 2 elements on the stack (x y => y x)

## Unary operations
- `char` - replaces top element with char that has its char code equal to the top element on the stack
- `even` - replaces top element with 1, if even, or 0 otherwise
- `odd` - replaces top element with 1, if odd, or 0 otherwise
- `!` - replaces top element with its factorial
- `len` - replaces top element with its length

## Binary operations
- `<>` - primerja zgornja dva elementa (x y) sklada 0 in na sklad porine 1 (če x <> y) ali 0 (če x == y)
- `<` - primerja zgornja dva elementa sklada 0 in na sklad porine 1 (če x < y) ali 0 (sicer)
- `<=` - primerja zgornja dva elementa sklada 0 in na sklad porine 1 (če x <= y) ali 0 (sicer)
- `==` - primerja zgornja dva elementa sklada 0 in na sklad porine 1 (če x == y) ali 0 (sicer)
- `>` - primerja zgornja dva elementa sklada 0 in na sklad porine 1 (če x > y) ali 0 (sicer)
- `>=` - primerja zgornja dva elementa sklada 0 in na sklad porine 1 (če x >= y) ali 0 (sicer)
- `+` - na sklad 0 porine vsoto vrhnjih dveh elementov sklada
- `-` - na sklad 0 porine razliko vrhnjih dveh elementov sklada
- `*` - na sklad 0 porine zmnožek vrhnjih dveh elementov sklada
- `/` - na sklad 0 porine kvocient (celoštevilsko deljenje) vrhnjih dveh elementov sklada
- `%` - na sklad 0 porine ostanek po deljenju elementa pod vrhom z elementom na vrh
- `.` - stakne (združi, zlepi) vrhnja dva elementa sklada 0 v en element (x y -> xy)
- `rnd` - na sklad 0 porine naključno število, ki ima vrednost >= x in <= y

## Conditional  operations
- `then`  - z glavnega sklada 0 vzame vrhnje število; če je to različno od 0, nastavi izpolnjenost pogoja na true, sicer pa na false
- `else` - zanika izpolnjenost pogoja
- `?...` - vsak ukaz, ki se začne z ?, se izpolni (ali pa ne) glede na prednastavljeno izpolnjenost pogoja

## Flow control and more
- `print` - v vrstici izpiše vsebino sklada (z indeksom, ki je podan na vrhu glavnega sklada 0) od dna do vrha
- `clear` - izprazne sklad (z indeksom, ki je podan na vrhu glavnega sklada 0)
- `run` - izvede vse ukaze na (pomožnem) skladu (z indeksom, ki je podan na vrhu glavnega sklada 0) od dna do vrha (sklad ostane nespremenjen)
- `loop` - izvede vse ukaze na (pomožnem) skladu (z indeksom, ki je podan na vrhu glavnega sklada 0) od dna do vrha (sklad ostane nespremenjen), pri čemer to ponovi tolikokrat, kot je podano s  številom pod vrhom sklada 0
- `fun` - na pomožni sklad  (z indeksom, ki je podan na vrhu glavnega sklada 0) zapiše toliko naslednjih ukazov, kolikor določa število pod vrhom glavnega sklada 0
- `move` - z glavnega sklada prenese na pomožni sklad  (z indeksom, ki je podan na vrhu glavnega sklada 0) toliko elementov, kolikor določa število pod vrhom glavnega sklada 0 (elementi se prenesejo eden za drugim)
- `reverse` - obrne vrstni red vseh elementov na skladu  (z indeksom, ki je podan na vrhu glavnega sklada 0) - u v x y z -> z y x v u