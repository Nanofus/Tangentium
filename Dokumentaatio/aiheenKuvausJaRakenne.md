# Aihemäärittely

### Aihe: Roolipeli
Roolipelissä pelaaja ohjaa ruudukossa liikkuvaa hahmoa. Siirrot tapahtuvat vuorotellen tekoälyn ohjaamien vihollisten kanssa.
Niin pelaajalla kuin vihollisillakin on terveyspisteet. Hahmot kohtaavat taistellessaan, ja tekevät toiseen vahinkoa senhetkisen
aseensa vahinkomäärän verran, ottaen kuitenkin huomioon toisen vahinkotyyppivastustuksen. Vahinkotyyppejä onkin erilaisia,
ja eri aseet tekevät eri tyyppistä vahinkoa. Aseita voi kuitenkin kantaa vain yhtä kerralla, joten pelaajan on poimittava maasta
vaihtoehtoisia aseita kohdatessaan erilaisia vihollisia.

Pelikenttä on satunnaisgeneroitu labyrintti, jonka läpi pelaajan tulee päästä. Seinien läpi ei siis voi kulkea. Välillä pelaaja kohtaa tallennuspisteitä, joissa pelaajan tilanne tallennetaan tiedostoon ja joka voidaan palauttaa.

Pelaajan tulee selvitä sokkelon läpi tulematta mörköjen syömäksi.

### Käyttäjät: Pelaaja

#### Pelaajan toiminnot:
* Liikkuminen neljään suuntaan (ja samalla vihollisen lyöminen)
* Aseen poimiminen
* Tallentaminen
* Tallennetun pelin lataaminen
